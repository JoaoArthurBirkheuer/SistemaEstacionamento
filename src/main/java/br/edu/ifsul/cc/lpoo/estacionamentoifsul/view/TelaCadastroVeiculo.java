package br.edu.ifsul.cc.lpoo.estacionamentoifsul.view;

import br.edu.ifsul.cc.lpoo.estacionamentoifsul.lpoo_sistemaestacionamentoifsul.dao.PersistenciaJPA;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import model.*;

public class TelaCadastroVeiculo extends JFrame {
    private JComboBox<Pessoa> cbProprietario;
    private JComboBox<Modelo> cbModelo;
    private JTextField txtPlaca, txtChassi, txtRenavan;
    private JCheckBox chkOficial;
    private JTable tabelaVeiculos;
    private JButton btnSalvar, btnEditar, btnExcluir, btnAdicionarModelo;

    public TelaCadastroVeiculo() {
        setTitle("Cadastro de Veículos");
        setSize(600, 400);
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(6, 2, 5, 5));

        // Proprietário
        formPanel.add(new JLabel("Proprietário:"));
        cbProprietario = new JComboBox<>();
        formPanel.add(cbProprietario);

        // Modelo
        formPanel.add(new JLabel("Modelo:"));
        JPanel modeloPanel = new JPanel(new BorderLayout());
        cbModelo = new JComboBox<>();
        modeloPanel.add(cbModelo, BorderLayout.CENTER);

        btnAdicionarModelo = new JButton("+");
        modeloPanel.add(btnAdicionarModelo, BorderLayout.EAST);
        formPanel.add(modeloPanel);

        // Placa
        formPanel.add(new JLabel("Placa:"));
        txtPlaca = new JTextField();
        formPanel.add(txtPlaca);

        // Oficial
        chkOficial = new JCheckBox("Veículo Oficial");
        chkOficial.addActionListener(e -> toggleCamposOficiais(chkOficial.isSelected()));
        formPanel.add(chkOficial);
        formPanel.add(new JLabel()); // Placeholder vazio

        // Chassi
        formPanel.add(new JLabel("Chassi:"));
        txtChassi = new JTextField();
        txtChassi.setEnabled(false);
        formPanel.add(txtChassi);

        // Renavan
        formPanel.add(new JLabel("Renavan:"));
        txtRenavan = new JTextField();
        txtRenavan.setEnabled(false);
        formPanel.add(txtRenavan);

        add(formPanel, BorderLayout.NORTH);

        // Tabela
        tabelaVeiculos = new JTable(new DefaultTableModel(new String[]{"Proprietário", "Modelo", "Placa", "Oficial"}, 0));
        add(new JScrollPane(tabelaVeiculos), BorderLayout.CENTER);

        // Botões
        JPanel buttonPanel = new JPanel();
        btnSalvar = new JButton("Salvar");
        btnEditar = new JButton("Editar");
        btnExcluir = new JButton("Excluir");
        buttonPanel.add(btnSalvar);
        buttonPanel.add(btnEditar);
        buttonPanel.add(btnExcluir);
        add(buttonPanel, BorderLayout.SOUTH);

        // Listeners
        btnSalvar.addActionListener(e -> salvarVeiculo());
        btnEditar.addActionListener(e -> editarVeiculo());
        btnExcluir.addActionListener(e -> excluirVeiculo());
        btnAdicionarModelo.addActionListener(e -> abrirCadastroModelo());

        carregarDados();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void carregarDados() {
        try {
            PersistenciaJPA persistencia = new PersistenciaJPA();

            // Carregar proprietários
            cbProprietario.removeAllItems();
            List<Pessoa> pessoas = persistencia.getPessoas();
            if (pessoas != null) {
                for (Pessoa p : pessoas) {
                    cbProprietario.addItem(p);
                }
            }

            // Carregar modelos
            cbModelo.removeAllItems();
            List<Modelo> modelos = persistencia.getModelos();
            if (modelos != null) {
                for (Modelo m : modelos) {
                    cbModelo.addItem(m);
                }
            }

            // Atualizar tabela
            atualizarTabelaVeiculos(persistencia);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar dados: " + e.getMessage());
        }
    }

    private void atualizarTabelaVeiculos(PersistenciaJPA persistencia) {
        try {
            DefaultTableModel model = (DefaultTableModel) tabelaVeiculos.getModel();
            model.setRowCount(0);
            List<Veiculo> veiculos = persistencia.getVeiculos();
            if (veiculos != null) {
                for (Veiculo v : veiculos) {
                    model.addRow(new Object[]{
                            v.getProprietario().getNome(),
                            v.getModelo().getDescricao(),
                            v.getPlaca(),
                            (v instanceof VeiOficial)
                    });
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar tabela: " + e.getMessage());
        }
    }

    private void salvarVeiculo() {
        try {
            PersistenciaJPA persistencia = new PersistenciaJPA();
            Veiculo veiculo;
            if (chkOficial.isSelected()) {
                veiculo = new VeiOficial();
                ((VeiOficial) veiculo).setChassi(txtChassi.getText());
                ((VeiOficial) veiculo).setRenavan(txtRenavan.getText());
            } else {
                veiculo = new Veiculo();
            }

            veiculo.setProprietario((Pessoa) cbProprietario.getSelectedItem());
            veiculo.setModelo((Modelo) cbModelo.getSelectedItem());
            veiculo.setPlaca(txtPlaca.getText());

            persistencia.persist(veiculo);
            JOptionPane.showMessageDialog(this, "Veículo salvo com sucesso!");
            carregarDados();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar veículo: " + e.getMessage());
        }
    }

    private void editarVeiculo() {
        JOptionPane.showMessageDialog(this, "Funcionalidade de edição ainda não implementada!");
    }

    private void excluirVeiculo() {
        JOptionPane.showMessageDialog(this, "Funcionalidade de exclusão ainda não implementada!");
    }

    private void abrirCadastroModelo() {
        TelaCadastroModelo tela = new TelaCadastroModelo();
        tela.setVisible(true);
    }

    private void toggleCamposOficiais(boolean isOficial) {
        txtChassi.setEnabled(isOficial);
        txtRenavan.setEnabled(isOficial);
    }
}