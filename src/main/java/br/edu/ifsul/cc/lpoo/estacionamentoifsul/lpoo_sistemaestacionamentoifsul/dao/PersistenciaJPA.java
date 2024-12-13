/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsul.cc.lpoo.estacionamentoifsul.lpoo_sistemaestacionamentoifsul.dao;

import java.lang.System.Logger;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import javax.management.Query;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Modelo;
import model.Pessoa;
import model.TipoVeiculo;
import model.Veiculo;
import model.VinculoPessoa;


public class PersistenciaJPA implements InterfaceBD {

    EntityManager entity;
    EntityManagerFactory factory;

    public PersistenciaJPA() {
        //parametro: é o nome da unidade de persistencia (Persistence Unit)
        factory
                = Persistence.createEntityManagerFactory("persistenceUnit");
        //conecta no bd e executa a estratégia de geração.
        entity = factory.createEntityManager();
    }

    @Override
    public Boolean conexaoAberta() {

        return entity.isOpen();
    }

    @Override
    public void fecharConexao() {
        entity.close();
    }

    @Override
    public Object find(Class c, Object id) throws Exception {
        try {
            return entity.find(c, id); // Busca pelo ID
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Retorna null caso haja algum problema
        }
    }

    @Override
    public void persist(Object o) throws Exception {
        entity = getEntityManager();
        try {
            entity.getTransaction().begin();
            entity.persist(o);
            entity.getTransaction().commit();
        } catch (Exception e) {
            if (entity.getTransaction().isActive()) {
                entity.getTransaction().rollback();
            }
        }
    }

    @Override
    public void remover(Object o) throws Exception {
        entity = getEntityManager();
        try {
            entity.getTransaction().begin();
            entity.remove(o);
            entity.getTransaction().commit();
        } catch (Exception e) {
            if (entity.getTransaction().isActive()) {
                entity.getTransaction().rollback();
            }
        }
    }

    /*
    Todos os métodos agora chamam getEntityManager() 
    para garantir que o EntityManager esteja sempre aberto e 
    pronto para uso.
     */
    public EntityManager getEntityManager() {
        if (entity == null || !entity.isOpen()) {
            entity = factory.createEntityManager();
        }
        return entity;
    }

    // funções para listar dados 
    public List<Pessoa> getPessoas() {
        entity = getEntityManager();

        try {
            TypedQuery<Pessoa> query
                    = entity.createQuery("Select p from Pessoa p", Pessoa.class);
            return query.getResultList();
        } catch (Exception e) {
            System.err.println("ErAro ao buscar Pessoas: " + e);
            return null;
        }

    }
    public List<Pessoa> buscarPessoasPorVinculo(VinculoPessoa vinculo) {
    try {
        TypedQuery<Pessoa> query = entity.createQuery(
            "SELECT p FROM Pessoa p WHERE p.vinculoPessoa = :vinculo", Pessoa.class);
        query.setParameter("vinculo", vinculo);
        List<Pessoa> pessoas = query.getResultList();
        
        // Retorna uma lista vazia caso não haja resultados
        return (pessoas != null) ? pessoas : new ArrayList<>(); 
    } catch (Exception e) {
        e.printStackTrace();
        return new ArrayList<>(); // Retorna uma lista vazia em caso de erro
    }
}

public List<Pessoa> buscarPessoasPorNome(String nome) {
    try {
        // Verifica se o nome não é nulo ou vazio para construir a consulta dinamicamente
        if (nome == null || nome.trim().isEmpty()) {
            return new ArrayList<>(); // Retorna uma lista vazia se o nome for inválido
        }

        // Criando uma consulta para buscar pessoas cujo nome contenha a string fornecida
        TypedQuery<Pessoa> query = entity.createQuery(
            "SELECT p FROM Pessoa p WHERE p.nome LIKE :nome", Pessoa.class
        );
        
        // Utilizando LIKE para buscar por nome com o "%" antes e depois, permitindo busca parcial
        query.setParameter("nome", "%" + nome.trim() + "%");
        
        // Retorna os resultados da consulta
        return query.getResultList();
    } catch (Exception e) {
        // Exibe o erro completo no log para análise
        e.printStackTrace();
        //Logger.getLogger(PersistenciaJPA.class.getName()).log(Level.SEVERE, "Erro ao buscar pessoas por nome", e);
        return new ArrayList<>(); // Retorna uma lista vazia em caso de erro
    }
}

public int buscarMaxIdPessoa() {
    try {
        TypedQuery query = (TypedQuery) entity.createQuery("SELECT COALESCE(MAX(p.id), 0) FROM Pessoa p");
        return (int) query.getSingleResult();
    } catch (Exception e) {
        e.printStackTrace();
        return 0; // Caso haja erro, retorna 0.
    }
}


public void removerPessoa(Pessoa pessoa) {
    entity.getTransaction().begin();
    Pessoa pessoaRemover = entity.find(Pessoa.class, pessoa.getId());
    if (pessoaRemover != null) {
        entity.remove(pessoaRemover);
    }
    entity.getTransaction().commit();
}

public Pessoa buscarPessoaPorNome(String nome) {
    return entity.createQuery("SELECT p FROM Pessoa p WHERE LOWER(p.nome) = :nome", Pessoa.class)
            .setParameter("nome", nome.toLowerCase())
            .getSingleResult();
}

public List<Veiculo> buscarVeiculosPorTipo(TipoVeiculo tipoVeiculo) throws Exception {
    if (tipoVeiculo == null) {
        throw new IllegalArgumentException("Tipo de veículo não pode ser nulo!");
    }

    return entity.createQuery("SELECT v FROM Veiculo v WHERE v.tipoVeiculo = :tipo", Veiculo.class)
             .setParameter("tipo", tipoVeiculo)
             .getResultList();
}

public List<Veiculo> getVeiculos() {
        try {
            return entity.createQuery("SELECT v FROM Veiculo v", Veiculo.class).getResultList();
        } catch (Exception e) {
            System.err.println("Erro ao listar veículos: " + e.getMessage());
            return null;
        }
    }

    public List<Veiculo> getVeiculosPorPlaca(String placa) {
        try {
            return entity.createQuery("SELECT v FROM Veiculo v WHERE lower(v.placa) LIKE :placa", Veiculo.class)
                    .setParameter("placa", "%" + placa.toLowerCase() + "%")
                    .getResultList();
        } catch (Exception e) {
            System.err.println("Erro ao filtrar veículos por placa: " + e.getMessage());
            return null;
        }
    }
    
    public List<Veiculo> getVeiculosOficiais(boolean oficial) {
        try {
            String query = oficial ? "SELECT v FROM VeiOficial v" : "SELECT v FROM Veiculo v WHERE v NOT IN (SELECT o FROM VeiOficial o)";
            return entity.createQuery(query, Veiculo.class).getResultList();
        } catch (Exception e) {
            System.err.println("Erro ao filtrar veículos oficiais: " + e.getMessage());
            return null;
        }
    }
     
    public List<Modelo> getModelos() {
        try {
            return entity.createQuery("SELECT m FROM Modelo m", Modelo.class).getResultList();
        } catch (Exception e) {
            System.err.println("Erro ao listar modelos: " + e.getMessage());
            return null;
        }
    }

    public Veiculo findVeiculoPorPlaca(String placa) {
    return entity.createQuery("SELECT v FROM Veiculo v WHERE v.placa = :placa", Veiculo.class)
                        .setParameter("placa", placa)
                        .getSingleResult();
}

}
