package Main.Application.Entities.Janelas;

import Main.Application.Entities.Produto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class JanelaCadastroProdutos extends JFrame {
    protected JanelaPrincipal janelaPrincipal;
    protected JanelaListarProdutos listarProdutos;
    protected List<Produto> listaProdutos = new ArrayList<>();
    protected JComboBox comboBox = new JComboBox();



    public List<Produto> getListaProdutos(){
        return listaProdutos;
    }

    public JComboBox getComboBox(){
        return comboBox;
    }

    public JanelaCadastroProdutos(JanelaPrincipal janelaPrincipal){

        this.janelaPrincipal = janelaPrincipal;

        setTitle("SISTEMA CADASTRO DE PRODUTOS");
        setBounds(0, 0, 1080, 720);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        ImageIcon iconeCadastroProduto = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Main/Application/Resources/Images/cadastroProduto.png")));
        JLabel logoCadastroProduto = new JLabel(iconeCadastroProduto);
        logoCadastroProduto.setBounds(517, 0,563, 471);



        JLabel labelProduto = new JLabel("PRODUTO:");
        labelProduto.setBounds(20,50,120,35);
        labelProduto.setFont(new Font("arial", Font.BOLD, 20));

        JTextField textProduto = new JTextField();
        textProduto.setBounds(160, 50, 250, 35);
        textProduto.setFont(new Font("arial", Font.PLAIN, 25));


        JLabel labelPreco = new JLabel("PREÇO:");
        labelPreco.setBounds(20,100,120,35);
        labelPreco.setFont(new Font("arial", Font.BOLD, 20));
        JLabel simbRealP = new JLabel("R$");
        simbRealP.setBounds(133, 100, 25,35);
        simbRealP.setFont(new Font("arial", Font.BOLD, 20));

        JTextField textPreco = new JTextField();
        textPreco.setBounds(160,100,150,35);
        textPreco.setFont(new Font("arial", Font.PLAIN ,25));


        comboBox.setBounds(318, 100, 60, 35);
        comboBox.addItem(" UN");
        comboBox.addItem(" CX");
        comboBox.addItem(" KG");
        comboBox.addItem(" G");
        comboBox.addItem(" PCT");
        comboBox.addItem(" MT");


        JLabel labelQuant = new JLabel("QUANTIDADE:");
        labelQuant.setBounds(20, 150, 150,35);
        labelQuant.setFont(new Font("arial", Font.BOLD, 20));

        JTextField textQuant = new JTextField();
        textQuant.setBounds(160, 150, 100,35);
        textQuant.setFont(new Font("arial", Font.PLAIN, 25));



        JButton btnVoltar = new JButton("VOLTAR ");
        btnVoltar.setBounds(20, 220, 130, 40);
        btnVoltar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                janelaPrincipal.setVisible(true);
                setVisible(false);
            }
        });


        JButton btnCadastrar = new JButton("CADASTRAR");
        btnCadastrar.setBounds(160, 220, 130, 40);
        btnCadastrar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (textProduto.getText().trim().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Digite um PRODUTO válido!");
                    return;
                }

               try{
                    String nomeProduto = textProduto.getText();

                    double precoProduto = Double.parseDouble(textPreco.getText());


                    int quantProduto = Integer.parseInt(textQuant.getText());

                    if (precoProduto <= 0 || quantProduto <=0){
                        JOptionPane.showMessageDialog(null, "ERROR! valor inválido!");
                    }
                    else{
                        Produto produto = new Produto(Produto.adicionarIdP(),nomeProduto, precoProduto, quantProduto);

                        String simbPreco = (String) comboBox.getSelectedItem();
                        produto.setSimbolPreco(simbPreco);

                        listaProdutos.add(produto);

                        JOptionPane.showMessageDialog(null, "Produto "+nomeProduto+" Cadastrado!");

                        textProduto.setText("");
                        textPreco.setText("");
                        textQuant.setText("");

                        comboBox.setSelectedItem(" UN");



                    }

                }catch (NumberFormatException errorPrecoP){

                       if (!textPreco.getText().trim().isEmpty() || !textQuant.getText().trim().isEmpty()) {
                           JOptionPane.showMessageDialog(null, "ERROR! campo inválido!");

                       }

                       if (textPreco.getText().trim().isEmpty() && textQuant.getText().trim().isEmpty()){
                           JOptionPane.showMessageDialog(null, "ERROR! campos inválidos");
                       }
                }
            }
        });


        add(btnVoltar);
        add(labelProduto);
        add(textProduto);
        add(labelPreco);
        add(simbRealP);
        add(textPreco);
        add(comboBox);
        add(labelQuant);
        add(textQuant);
        add(btnCadastrar);
        add(logoCadastroProduto);
        setVisible(false);


    }

}