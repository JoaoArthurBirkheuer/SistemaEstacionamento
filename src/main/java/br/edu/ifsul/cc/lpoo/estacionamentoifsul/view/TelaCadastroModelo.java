package br.edu.ifsul.cc.lpoo.estacionamentoifsul.view;

import br.edu.ifsul.cc.lpoo.estacionamentoifsul.lpoo_sistemaestacionamentoifsul.dao.PersistenciaJPA;
import javax.swing.*;
import java.awt.*;
import model.Modelo;
import model.Marca;

public class TelaCadastroModelo extends JFrame {
    private JTextField txtDescricao;
    private JComboBox<Marca> cbMarca;
    private JButton btnSalvar;

    public TelaCadastroModelo() {
        setTitle("Cadastro de Modelo");
        setSize(300, 200);
        setLayout(new GridLayout(3, 2, 5, 5));

        add(new JLabel("Descrição:"));
        txtDescricao = new JTextField();
        add(txtDescricao);

        add(new JLabel("Marca:"));
        cbMarca = new JComboBox<>(Marca.values());
        add(cbMarca);

        btnSalvar = new JButton("Salvar");
        add(btnSalvar);

        btnSalvar.addActionListener(e -> salvarModelo());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void salvarModelo() {
        try {
            PersistenciaJPA persistencia = new PersistenciaJPA();
            Modelo modelo = new Modelo();
            modelo.setDescricao(txtDescricao.getText());
            modelo.setMarca((Marca) cbMarca.getSelectedItem());

            persistencia.persist(modelo);
            JOptionPane.showMessageDialog(this, "Modelo salvo com sucesso!");
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage());
        }
    }
}