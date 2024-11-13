/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */

import br.edu.ifsul.cc.lpoo.estacionamentoifsul.lpoo_sistemaestacionamentoifsul.dao.PersistenciaJPA;
import model.EntradaSaida;
import model.Marca;
import model.Modelo;
import model.Pessoa;
import model.TipoMovimentacao;
import model.TipoVeiculo;
import model.VeiOficial;
import model.Veiculo;
import model.VinculoPessoa;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author vanessalagomachado
 */
public class TestePersistencia {
    PersistenciaJPA jpa = new PersistenciaJPA();
    
    public TestePersistencia() {
    }
    
    @Before
    public void setUp() {
        jpa.conexaoAberta();
    }
    
    @After
    public void tearDown() {
        jpa.fecharConexao();
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void testePersistencia() {
        Modelo m = new Modelo();
        m.setDescricao("fox");
        m.setMarca(Marca.FORD);
        
        Veiculo t = new Veiculo();
        t.setModelo(m);
        t.setCor("PRATA");
        t.setPlaca("ABC4T67");
        t.setTipoVeiculo(TipoVeiculo.CARRO);
        
        EntradaSaida es = new EntradaSaida(TipoMovimentacao.SAIDA, t);
        
        Pessoa p = new Pessoa();
        p.setNome("Juka");
        p.setVinculoPessoa(VinculoPessoa.VISITANTE);
        
        t.setProprietario(p);
        
        try{
            jpa.persist(m);
            jpa.persist(t);
            jpa.persist(es); 
            jpa.persist(p); 
            
            jpa.remover(p);
            
        } catch(Exception e){
            System.err.println("Erro ao persistir modelo: "+m);
        }
        
    }
}
