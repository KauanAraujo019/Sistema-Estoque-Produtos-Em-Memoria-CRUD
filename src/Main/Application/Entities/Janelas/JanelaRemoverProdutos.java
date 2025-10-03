package Main.Application.Entities.Janelas;


import Main.Application.Entities.Produto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class JanelaRemoverProdutos extends JanelaAtualizarProduto {

    public JanelaRemoverProdutos(JanelaPrincipal janelaPrincipal, JanelaCadastroProdutos janelaCadastroProdutos) {
        super(janelaPrincipal, janelaCadastroProdutos);
        getjComboBox().setSelectedItem("ID");
        remove(getjComboBox());
        remove(getJlabelProduto());
        remove(getLogoAtualizarProduto());

        getTextoProduto().setBounds(280, 20, 100,40);
        getBotaoPesquisa().setBounds(390, 20, 40,40);

        JLabel labelProduto = new JLabel("PESQUISAR PRODUTO ID:");
        labelProduto.setBounds(20, 20, 300, 40);
        labelProduto.setFont(new Font("arial", Font.BOLD, 20));


        ImageIcon iconeRemoverProduto = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Main/Application/Resources/Images/logoRemoverProduto.png")));
        JLabel logoRemoverProduto = new JLabel(iconeRemoverProduto);
        logoRemoverProduto.setBounds(517, 0,563, 471);


        setResizable(false);


        add(labelProduto);
        add(logoRemoverProduto);

    }


    @Override
    public void pesquisaIDProduto(JTextField textoProduto, JButton botaoPesquisa){
        try {

            String pesquisaP = textoProduto.getText();
            int idP = Integer.parseInt(pesquisaP);

            if (idP <= 0){
                JOptionPane.showMessageDialog(null, "ID inválido!");
                return;
            }
            else if (idP > janelaCadastroProdutos.getListaProdutos().size()){
                JOptionPane.showMessageDialog(null, "Produto não encontrado!");
                return;
            }


            Produto produto = janelaCadastroProdutos.getListaProdutos().get(idP-1);

            textoProduto.setText("");

            try {

                String[] produtoFinal = produto.abaProdutos().split("-");
                String nameP = produtoFinal[0];
                String precoP = produtoFinal[1].substring(3);
                String quantP = produtoFinal[2].substring(4);
                String simbP = produtoFinal[3];


                JLabel labelNameProd = new JLabel("PRODUTO: ");
                labelNameProd.setBounds(10, 80, 150, 40);
                labelNameProd.setFont(new Font("arial", Font.BOLD, 20));

                JLabel textNameProd = new JLabel(nameP);
                textNameProd.setBounds(150, 80, 290, 35);
                textNameProd.setFont(new Font("arial", Font.PLAIN, 25));
                textNameProd.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));


                JLabel labelPrecoProd = new JLabel("PREÇO:     R$");
                labelPrecoProd.setBounds(10, 120, 150, 40);
                labelPrecoProd.setFont(new Font("arial", Font.BOLD, 20));

                JLabel textPrecoProd = new JLabel(precoP);
                textPrecoProd.setBounds(150, 120, 150, 35);
                textPrecoProd.setFont(new Font("arial", Font.PLAIN, 25));
                textPrecoProd.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));


                JLabel labelQuantProd = new JLabel("QUANTIDADE: ");
                labelQuantProd.setBounds(10, 160, 150, 40);
                labelQuantProd.setFont(new Font("arial", Font.BOLD, 20));

                JLabel textQuantProd = new JLabel(quantP);
                textQuantProd.setBounds(150, 160, 150, 35);
                textQuantProd.setFont(new Font("arial", Font.PLAIN, 25));
                textQuantProd.setBorder(BorderFactory.createLineBorder(Color.black, 1));


                JLabel labelSimbpProd = new JLabel("Cód-UOM:");
                labelSimbpProd.setBounds(10, 200, 120, 40);
                labelSimbpProd.setFont(new Font("arial", Font.BOLD, 20));

                JLabel textSimbpProd = new JLabel(simbP);
                textSimbpProd.setBounds(150, 200, 100, 35);
                textSimbpProd.setFont(new Font("arial", Font.PLAIN, 25));
                textSimbpProd.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));


                ImageIcon iconExcluir = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Main/Application/Resources/Images/lixeiraExcluirProduto.jpg")));
                JButton botaoExcluir = new JButton(iconExcluir);
                botaoExcluir.setBounds(450, 80, 40, 45);
                JLabel textAt = new JLabel("Excluir Produto");
                textAt.setFont(new Font("arial", Font.PLAIN, 20));
                textAt.setBounds(410, 120, 150, 40);


                Produto finalProduto = produto;

                botaoPesquisa.setVisible(false);



                JButton botaoDesfazer = new JButton();

                botaoExcluir.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        janelaCadastroProdutos.getListaProdutos().remove(finalProduto);


                        JOptionPane.showMessageDialog(null, "Produto excluído com sucesso!");

                        remove(labelNameProd);
                        remove(textNameProd);
                        remove(labelPrecoProd);
                        remove(textPrecoProd);
                        remove(labelQuantProd);
                        remove(textQuantProd);
                        remove(labelSimbpProd);
                        remove(textSimbpProd);
                        remove(botaoExcluir);
                        remove(textAt);

                        botaoPesquisa.setVisible(true);


                        revalidate();
                        repaint();

                    }
                });






                add(labelNameProd);
                add(textNameProd);
                add(labelPrecoProd);
                add(textPrecoProd);
                add(labelQuantProd);
                add(textQuantProd);
                add(labelSimbpProd);
                add(textSimbpProd);
                add(botaoExcluir);
                add(textAt);

                revalidate();
                repaint();


            }catch (NullPointerException n){
                revalidate();
                repaint();
            }



            revalidate();
            repaint();


        }catch (NumberFormatException numb){
            JOptionPane.showMessageDialog(null, "ID invalido!");

        }

    }




}



