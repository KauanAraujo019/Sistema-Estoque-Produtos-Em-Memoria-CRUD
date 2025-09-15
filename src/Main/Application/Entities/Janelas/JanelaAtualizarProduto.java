package Main.Application.Entities.Janelas;

import Main.Application.Entities.Produto;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class JanelaAtualizarProduto extends JFrame {
    JanelaPrincipal janelaPrincipal;
    JanelaCadastroProdutos janelaCadastroProdutos;



    public JanelaAtualizarProduto(){

    }

    public JanelaAtualizarProduto(JanelaPrincipal janelaPrincipal, JanelaCadastroProdutos janelaCadastroProdutos){
        this.janelaPrincipal = janelaPrincipal;
        this.janelaCadastroProdutos = janelaCadastroProdutos;


        setTitle("SISTEMA CADASTRO DE PRODUTOS");
        setBounds(0, 0, 1080, 720);
        setLayout(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        JLabel labelProduto = new JLabel("PESQUISAR PRODUTO:");
        labelProduto.setBounds(20, 20, 250, 40);
        labelProduto.setFont(new Font("arial", Font.BOLD, 20));

        JTextField textoProduto = new JTextField();
        textoProduto.setBounds(320, 20, 200,40);
        textoProduto.setFont(new Font("arial", Font.PLAIN ,25));




        JButton botaoVoltar = new JButton("VOLTAR");
        botaoVoltar.setBounds(20, 380, 130, 40);
        botaoVoltar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        botaoVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                janelaPrincipal.setVisible(true);
                setVisible(false);
            }
        });



        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Main/Application/Resources/Images/lupaPesquisaProduto.png")));
        JButton botaoPesquisa = new JButton(icon);
        botaoPesquisa.setBounds(530, 20, 40,40);

        JComboBox comboPesq = new JComboBox();
        comboPesq.setBounds(250, 20, 60, 40);
        comboPesq.addItem("nome");
        comboPesq.addItem("ID");



        botaoPesquisa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textoProduto.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Valor inválido!");
                    return;
                }
                String namePesquisaP = (String) comboPesq.getSelectedItem();


                if (namePesquisaP == "ID"){

                    try {
                        String pesquisaP = textoProduto.getText();
                        int idP = Integer.parseInt(pesquisaP);


                        for (int i = 0; i < janelaCadastroProdutos.getListaProdutos().size(); i++) {

                            if (janelaCadastroProdutos.getListaProdutos().get(i).getIdProduto() == idP) {

                                return;

                            }

                        }

                        JOptionPane.showMessageDialog(null, "Produto não encontrado");
                        return;

                    }catch (NumberFormatException numb){
                        JOptionPane.showMessageDialog(null, "ID invalido!");
                        return;
                    }
                }


                String pesquisaP = textoProduto.getText();
                char[] prodPesq = pesquisaP.toCharArray();

                try {

                    for (Produto produto : janelaCadastroProdutos.getListaProdutos()) {
                        char[] arrayProd = produto.getNameProduct().toCharArray();
                        int cont = 0;

                        for (int i = 0; i < prodPesq.length; i++) {
                            if (prodPesq[i] == arrayProd[i]) {
                                cont++;

                            }
                        }


                        if (cont == prodPesq.length) {
                        //prod
                        }
                        else {

                        }


                    }


                }catch (ArrayIndexOutOfBoundsException ofBounds){
                    System.out.println("NÃO");

                }



            }
        });




        add(labelProduto);
        add(textoProduto);
        add(botaoVoltar);
        add(botaoPesquisa);
        add(comboPesq);

        setVisible(false);





    }




}
