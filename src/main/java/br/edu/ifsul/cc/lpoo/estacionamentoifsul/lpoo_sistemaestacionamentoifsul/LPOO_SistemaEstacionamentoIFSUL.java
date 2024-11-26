/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package br.edu.ifsul.cc.lpoo.estacionamentoifsul.lpoo_sistemaestacionamentoifsul;

import br.edu.ifsul.cc.lpoo.estacionamentoifsul.lpoo_sistemaestacionamentoifsul.dao.PersistenciaJPA;


public class LPOO_SistemaEstacionamentoIFSUL {

    public static void main(String[] args) {
        try {
        System.out.println("Iniciando JPA...");
        PersistenciaJPA persistencia = new PersistenciaJPA();
        persistencia.conexaoAberta();
        System.out.println("Conexão aberta com sucesso!");
        persistencia.fecharConexao();
        System.out.println("Conexão fechada.");
    } catch (Exception e) {
        e.printStackTrace();
    }
    }
}
