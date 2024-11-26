package br.edu.ifsul.cc.lpoo.estacionamentoifsul.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import br.edu.ifsul.cc.lpoo.estacionamentoifsul.lpoo_sistemaestacionamentoifsul.dao.PersistenciaJPA;
import model.TipoVeiculo;
import model.Veiculo;

public class TelaVeiculo extends javax.swing.JFrame {

    PersistenciaJPA jpa;

    // Variables declaration
    private javax.swing.JPanel areaBotoes;
    private javax.swing.JPanel areaFiltros;
    private javax.swing.JPanel areaListagem;

    private javax.swing.JButton btnEditarVeiculo;
    private javax.swing.JButton btnNovoVeiculo;
    private javax.swing.JButton btnRemoverVeiculo;

    private javax.swing.JComboBox<String> cmbTipoVeiculo;

    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> lstVeiculos;

    private javax.swing.JLabel lblBuscaPlaca;
    private javax.swing.JLabel lblBuscaTipo;
    private javax.swing.JLabel lblTitulo;

    private javax.swing.JTextField txtBuscaPlaca;
    // End of variables declaration

    public TelaVeiculo() {
        initComponents();
        if (jpa == null) {
            jpa = new PersistenciaJPA();
        }
        carregarVeiculosCadastrados();
        btnNovoVeiculo.addActionListener(e -> abrirTelaCadastro(e));

        // Adicionando Listener ao campo de texto
        txtBuscaPlaca.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                carregarVeiculosCadastrados();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                carregarVeiculosCadastrados();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                carregarVeiculosCadastrados();
            }
        });

        // Adicionando Listener ao ComboBox de tipo de veículo
        cmbTipoVeiculo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carregarVeiculosCadastrados();
            }
        });

        // Inicializando a lista ao carregar a tela
        carregarVeiculosCadastrados();
    }

    public void carregarVeiculosCadastrados() {
        try {
            DefaultListModel<String> modelo = new DefaultListModel<>();
            modelo.clear();

            // Obtendo valores do filtro
            String placaFiltro = txtBuscaPlaca.getText().toLowerCase();
            TipoVeiculo tipoSelecionado = TipoVeiculo.valueOf(
                    cmbTipoVeiculo.getSelectedItem().toString().toUpperCase());

            // Buscar veículos no banco de dados
            List<Veiculo> veiculos = jpa.buscarVeiculosPorTipo(tipoSelecionado);

            if (veiculos != null && !veiculos.isEmpty()) {
                for (Veiculo v : veiculos) {
                    if (v.getPlaca().toLowerCase().contains(placaFiltro)) {
                        modelo.addElement(v.getPlaca() + " - " + v.getTipoVeiculo().getDescricao());
                    }
                }
            }

            if (modelo.isEmpty()) {
                modelo.addElement("Nenhum veículo encontrado.");
            }

            lstVeiculos.setModel(modelo);
        } catch (Exception e) {
            Logger.getLogger(TelaVeiculo.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        areaFiltros = new javax.swing.JPanel();
        lblBuscaPlaca = new javax.swing.JLabel();
        cmbTipoVeiculo = new javax.swing.JComboBox<>();
        lblBuscaTipo = new javax.swing.JLabel();
        txtBuscaPlaca = new javax.swing.JTextField();
        areaListagem = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstVeiculos = new javax.swing.JList<>();
        areaBotoes = new javax.swing.JPanel();
        btnNovoVeiculo = new javax.swing.JButton();
        btnEditarVeiculo = new javax.swing.JButton();
        btnRemoverVeiculo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblTitulo.setFont(new java.awt.Font("Helvetica Neue", 1, 14));
        lblTitulo.setText("Veículos Cadastrados");

        lblBuscaPlaca.setText("Placa:");

        cmbTipoVeiculo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Carro", "Moto", "Caminhão"}));

        lblBuscaTipo.setText("Tipo:");

        javax.swing.GroupLayout areaFiltrosLayout = new javax.swing.GroupLayout(areaFiltros);
        areaFiltros.setLayout(areaFiltrosLayout);
        areaFiltrosLayout.setHorizontalGroup(
                areaFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(areaFiltrosLayout.createSequentialGroup()
                                .addComponent(lblBuscaPlaca)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtBuscaPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblBuscaTipo)
                                .addGap(18, 18, 18)
                                .addComponent(cmbTipoVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(15, Short.MAX_VALUE))
        );
        areaFiltrosLayout.setVerticalGroup(
                areaFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(areaFiltrosLayout.createSequentialGroup()
                                .addGroup(areaFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblBuscaPlaca)
                                        .addComponent(txtBuscaPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblBuscaTipo)
                                        .addComponent(cmbTipoVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(15, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(lstVeiculos);

        btnNovoVeiculo.setText("Novo");

        btnEditarVeiculo.setText("Editar");

        btnRemoverVeiculo.setText("Remover");

        javax.swing.GroupLayout areaBotoesLayout = new javax.swing.GroupLayout(areaBotoes);
        areaBotoes.setLayout(areaBotoesLayout);
        areaBotoesLayout.setHorizontalGroup(
                areaBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(areaBotoesLayout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(btnNovoVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnEditarVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnRemoverVeiculo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        areaBotoesLayout.setVerticalGroup(
                areaBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(areaBotoesLayout.createSequentialGroup()
                                .addGroup(areaBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnNovoVeiculo)
                                        .addComponent(btnEditarVeiculo)
                                        .addComponent(btnRemoverVeiculo))
                                .addContainerGap(20, Short.MAX_VALUE))
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
                                        .addComponent(lblTitulo)
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
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(areaBotoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        pack();
    }


    private void abrirTelaCadastro(Object veiculo) {
        TelaCadastroVeiculo telaCadastro = new TelaCadastroVeiculo(veiculo);
        telaCadastro.setVisible(true);
    }

    private Object obterVeiculoSelecionado() {
        int selectedRow = tblVeiculos.getSelectedRow();
        if (selectedRow != -1) {
            // Retorne o veículo da linha selecionada
        }
        return null;
    }

    private void excluirVeiculo() {
        // Lógica para exclusão
        JOptionPane.showMessageDialog(this, "Veículo excluído!");
    }

    private JLabel lblFiltro;
    private JTextField txtFiltro;
    private JButton btnFiltrar;
    private JTable tblVeiculos;
    private JScrollPane scrollPane;
    private JButton btnNovo;
    private JButton btnEditar;
    private JButton btnExcluir;
    private JButton btnVoltar;
}
