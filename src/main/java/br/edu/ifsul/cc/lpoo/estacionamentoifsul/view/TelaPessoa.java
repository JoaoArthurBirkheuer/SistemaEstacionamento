package br.edu.ifsul.cc.lpoo.estacionamentoifsul.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultListModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import br.edu.ifsul.cc.lpoo.estacionamentoifsul.lpoo_sistemaestacionamentoifsul.dao.PersistenciaJPA;
import model.Pessoa;
import model.VinculoPessoa;

public class TelaPessoa extends javax.swing.JFrame {
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaPessoa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPessoa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPessoa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPessoa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPessoa().setVisible(true);
            }
        });
    }

    PersistenciaJPA jpa;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel areaBotoes;

    private javax.swing.JPanel areaFiltros;

    private javax.swing.JPanel areaListagem;

    private javax.swing.JButton btnEditarPessoa;
    
    private javax.swing.JButton btnNovaPessoa;
    private javax.swing.JButton btnRemoverPessoa;
    private javax.swing.JComboBox<String> cmbVinculoPessoa;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> lstPessoas;
    private javax.swing.JLabel lblBuscaNome;
    private javax.swing.JLabel lblBuscaVinculo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTextField txtBuscaNome;
    // End of variables declaration//GEN-END:variables
    /**
     * Creates new form TelaPessoa
     */
    public TelaPessoa() {
        initComponents();
        if (jpa == null) {
            jpa = new PersistenciaJPA();
        }
        carregarPessoasCadastradas();

        // Adicionando Listener para o campo de texto
    txtBuscaNome.getDocument().addDocumentListener(new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent e) {
            carregarPessoasCadastradas();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            carregarPessoasCadastradas();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            carregarPessoasCadastradas();
        }
    });

    // Adicionando Listener para o ComboBox de vínculo
    cmbVinculoPessoa.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            carregarPessoasCadastradas();
        }
    });

    // Inicializando a lista ao carregar a tela
    carregarPessoasCadastradas();
    }
    public void carregarPessoasCadastradas() {
        try {
            // Modelo para a lista
            DefaultListModel<String> modelo = new DefaultListModel<>();
            modelo.clear();
            
            // Obter valores do campo de texto e combo box
            String nomeFiltro = txtBuscaNome.getText().toLowerCase();
            VinculoPessoa vinculoSelecionado = VinculoPessoa.valueOf(
                cmbVinculoPessoa.getSelectedItem().toString().toUpperCase()
            );
            
            // Buscar pessoas no banco de dados com base no vínculo
            List<Pessoa> pessoas = jpa.buscarPessoasPorVinculo(vinculoSelecionado);
            
            if (pessoas != null && !pessoas.isEmpty()) {
                for (Pessoa p : pessoas) {
                    // Verifica se o nome corresponde ao filtro
                    if (p.getNome().toLowerCase().contains(nomeFiltro)) {
                        modelo.addElement(p.getNome() + " (" + p.getVinculoPessoa().getDescricao() + ")");
                    }
                }
            }
            
            // Exibe mensagem se nenhum resultado for encontrado
            if (modelo.isEmpty()) {
                modelo.addElement("Nenhum resultado encontrado.");
            }
            
            // Atualizar a lista
            lstPessoas.setModel(modelo);
        } catch (Exception e) {
            Logger.getLogger(TelaPessoa.class.getName()).log(Level.SEVERE, null, e);
        }
    }    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        areaFiltros = new javax.swing.JPanel();
        lblBuscaNome = new javax.swing.JLabel();
        cmbVinculoPessoa = new javax.swing.JComboBox<>();
        lblBuscaVinculo = new javax.swing.JLabel();
        txtBuscaNome = new javax.swing.JTextField();
        areaListagem = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstPessoas = new javax.swing.JList<>();
        areaBotoes = new javax.swing.JPanel();
        btnNovaPessoa = new javax.swing.JButton();
        btnEditarPessoa = new javax.swing.JButton();
        btnRemoverPessoa = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblTitulo.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        lblTitulo.setText("Pessoas Cadastradas");

        lblBuscaNome.setText("Nome: ");

        cmbVinculoPessoa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Servidor", "Aluno", "Terceirizado", "Visitante" }));

        lblBuscaVinculo.setText("Vínculo:");

        javax.swing.GroupLayout areaFiltrosLayout = new javax.swing.GroupLayout(areaFiltros);
        areaFiltros.setLayout(areaFiltrosLayout);
        areaFiltrosLayout.setHorizontalGroup(
            areaFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, areaFiltrosLayout.createSequentialGroup()
                .addComponent(lblBuscaNome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBuscaNome, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblBuscaVinculo)
                .addGap(18, 18, 18)
                .addComponent(cmbVinculoPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        areaFiltrosLayout.setVerticalGroup(
            areaFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(areaFiltrosLayout.createSequentialGroup()
                .addContainerGap(37, Short.MAX_VALUE)
                .addGroup(areaFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBuscaNome)
                    .addComponent(cmbVinculoPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBuscaNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBuscaVinculo))
                .addContainerGap())
        );

        jScrollPane1.setViewportView(lstPessoas);

        javax.swing.GroupLayout areaListagemLayout = new javax.swing.GroupLayout(areaListagem);
        areaListagem.setLayout(areaListagemLayout);
        areaListagemLayout.setHorizontalGroup(
            areaListagemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(areaListagemLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        areaListagemLayout.setVerticalGroup(
            areaListagemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(areaListagemLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        btnNovaPessoa.setText("Novo");
        btnNovaPessoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovaPessoaActionPerformed(evt);
            }
        });

        btnEditarPessoa.setText("Editar");

        btnEditarPessoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jpa == null || !jpa.conexaoAberta()) {
                    javax.swing.JOptionPane.showMessageDialog(TelaPessoa.this,
                            "Erro ao acessar o banco de dados. Verifique a conexão.",
                            "Erro", javax.swing.JOptionPane.ERROR_MESSAGE);
                    return;
                }
        
                int index = lstPessoas.getSelectedIndex();
                if (index != -1) {
                    try {
                        String nomeSelecionado = lstPessoas.getModel().getElementAt(index);
                        Pessoa pessoa = jpa.buscarPessoaPorNome(nomeSelecionado.split(" \\(")[0]);
        
                        TelaCadastroPessoa telaCadastro = new TelaCadastroPessoa(TelaPessoa.this, true, pessoa);
                        telaCadastro.setVisible(true);
        
                        carregarPessoasCadastradas();
                    } catch (Exception ex) {
                        javax.swing.JOptionPane.showMessageDialog(TelaPessoa.this,
                                "Erro ao buscar ou editar a pessoa: " + ex.getMessage(),
                                "Erro", javax.swing.JOptionPane.ERROR_MESSAGE);
                        ex.printStackTrace();
                    }
                } else {
                    javax.swing.JOptionPane.showMessageDialog(TelaPessoa.this,
                            "Selecione uma pessoa para editar.",
                            "Atenção", javax.swing.JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        btnRemoverPessoa.setText("Remover");

        btnRemoverPessoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = lstPessoas.getSelectedIndex(); // Obter índice da pessoa selecionada na lista
                if (index != -1) {
                    String nomeSelecionado = lstPessoas.getModel().getElementAt(index);
                    Pessoa pessoa = jpa.buscarPessoaPorNome(nomeSelecionado.split(" \\(")[0]);
                    
                    // Exibir confirmação de remoção
                    int confirmacao = javax.swing.JOptionPane.showConfirmDialog(TelaPessoa.this, 
                            "Deseja realmente remover esta pessoa?", 
                            "Confirmação", javax.swing.JOptionPane.YES_NO_OPTION);
                    
                    if (confirmacao == javax.swing.JOptionPane.YES_OPTION) {
                        try {
                            jpa.removerPessoa(pessoa); // Método no DAO para remover
                            carregarPessoasCadastradas();
                        } catch (Exception ex) {
                            Logger.getLogger(TelaPessoa.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } else {
                    javax.swing.JOptionPane.showMessageDialog(TelaPessoa.this, 
                            "Selecione uma pessoa para remover.", 
                            "Atenção", javax.swing.JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        javax.swing.GroupLayout areaBotoesLayout = new javax.swing.GroupLayout(areaBotoes);
        areaBotoes.setLayout(areaBotoesLayout);
        areaBotoesLayout.setHorizontalGroup(
            areaBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(areaBotoesLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(btnNovaPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEditarPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnRemoverPessoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        areaBotoesLayout.setVerticalGroup(
            areaBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, areaBotoesLayout.createSequentialGroup()
                .addContainerGap(48, Short.MAX_VALUE)
                .addGroup(areaBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNovaPessoa)
                    .addComponent(btnEditarPessoa)
                    .addComponent(btnRemoverPessoa))
                .addGap(29, 29, 29))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(areaFiltros, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(areaListagem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblTitulo)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(areaBotoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(lblTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(areaFiltros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(areaListagem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(areaBotoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void btnNovaPessoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovaPessoaActionPerformed
        TelaCadastroPessoa telaCadastro = 
                new TelaCadastroPessoa(this, rootPaneCheckingEnabled);
        telaCadastro.setVisible(true);

        carregarPessoasCadastradas();
    }//GEN-LAST:event_btnNovaPessoaActionPerformed

    private void btnEditarPessoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarPessoaActionPerformed
    int index = lstPessoas.getSelectedIndex();
    if (index >= 0) {
        try {
            // Obtenha a pessoa selecionada
            String itemSelecionado = lstPessoas.getSelectedValue();
            String nomePessoa = itemSelecionado.split("\\(")[0].trim(); // Extrair o nome da pessoa

            Pessoa pessoa = jpa.buscarPessoaPorNome(nomePessoa); // Substitua por sua lógica de busca

            if (pessoa != null) {
                TelaCadastroPessoa telaCadastro = 
                        new TelaCadastroPessoa(this, true, pessoa); // Crie um construtor que aceite um objeto Pessoa
                telaCadastro.setVisible(true);

                carregarPessoasCadastradas(); // Atualizar a lista após edição
            } else {
                javax.swing.JOptionPane.showMessageDialog(this, 
                        "Erro ao encontrar a pessoa no banco de dados.", 
                        "Erro", 
                        javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            Logger.getLogger(TelaPessoa.class.getName()).log(Level.SEVERE, null, e);
            javax.swing.JOptionPane.showMessageDialog(this, 
                    "Erro ao editar a pessoa.", 
                    "Erro", 
                    javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    } else {
        javax.swing.JOptionPane.showMessageDialog(this, 
                "Selecione uma pessoa para editar.", 
                "Atenção", 
                javax.swing.JOptionPane.WARNING_MESSAGE);
    }
}//GEN-LAST:event_btnEditarPessoaActionPerformed

}
