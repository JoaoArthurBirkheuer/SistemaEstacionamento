package br.edu.ifsul.cc.lpoo.estacionamentoifsul.view;

import javax.swing.*;

public class TelaCadastroVeiculo extends javax.swing.JFrame {

    private Object veiculo;

    public TelaCadastroVeiculo(Object veiculo) {
        this.veiculo = veiculo;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        lblPlaca = new JLabel("Placa:");
        txtPlaca = new JTextField();
        lblModelo = new JLabel("Modelo:");
        txtModelo = new JTextField();
        lblCor = new JLabel("Cor:");
        txtCor = new JTextField();
        lblTipoVeiculo = new JLabel("Tipo:");
        cbTipoVeiculo = new JComboBox<>(new String[]{"CARRO", "MOTOCICLETA"});
        btnSalvar = new JButton("Salvar");
        btnCancelar = new JButton("Cancelar");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(veiculo == null ? "Novo Veículo" : "Editar Veículo");

        // Configuração do layout
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(lblPlaca)
                            .addComponent(lblModelo)
                            .addComponent(lblCor)
                            .addComponent(lblTipoVeiculo))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(txtPlaca)
                            .addComponent(txtModelo)
                            .addComponent(txtCor)
                            .addComponent(cbTipoVeiculo, 0, 200, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSalvar)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCancelar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPlaca)
                    .addComponent(txtPlaca, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblModelo)
                    .addComponent(txtModelo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCor)
                    .addComponent(txtCor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTipoVeiculo)
                    .addComponent(cbTipoVeiculo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvar)
                    .addComponent(btnCancelar))
                .addContainerGap())
        );

        // Ações dos botões
        btnSalvar.addActionListener(evt -> salvarVeiculo());
        btnCancelar.addActionListener(evt -> dispose());

        pack();
    }

    private void salvarVeiculo() {
        // Lógica para salvar ou editar o veículo
        JOptionPane.showMessageDialog(this, "Veículo salvo!");
        dispose();
    }

    private JLabel lblPlaca;
    private JTextField txtPlaca;
    private JLabel lblModelo;
    private JTextField txtModelo;
    private JLabel lblCor;
    private JTextField txtCor;
    private JLabel lblTipoVeiculo;
    private JComboBox<String> cbTipoVeiculo;
    private JButton btnSalvar;
    private JButton btnCancelar;
}
