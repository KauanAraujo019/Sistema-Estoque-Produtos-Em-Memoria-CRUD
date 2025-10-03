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

public class JanelaRemoverProdutos extends JanelaAtualizarProduto {

    public JanelaRemoverProdutos(JanelaPrincipal janelaPrincipal, JanelaCadastroProdutos janelaCadastroProdutos) {
        super(janelaPrincipal, janelaCadastroProdutos);

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


    @Override
    public void pesquisaNomeProduto(JTextField textoProduto, JButton botaoPesquisa) {
        List<Produto> listaProdutos = new ArrayList<>();
        Produto prodAdd = null;

        try {

            String pesquisaP = textoProduto.getText();
            char[] prodPesq = pesquisaP.toCharArray();

            for (Produto produto : janelaCadastroProdutos.getListaProdutos()) {
                char[] arrayProd = produto.getNameProduct().toCharArray();
                int cont = 0;

                for (int i = 0; i < prodPesq.length; i++) {

                    if (cont > 0 && arrayProd.length < prodPesq.length) {

                        break;

                    }

                    if (prodPesq[i] == arrayProd[i]) {
                        cont++;

                    }

                }

                if (cont == prodPesq.length) {
                    listaProdutos.add(produto);

                }

            }

            if (listaProdutos.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Produto não encontrado!");
                return;
            }


        } catch (ArrayIndexOutOfBoundsException ofBounds) {
            listaProdutos.add(prodAdd);

        }

        JComboBox comboPid02 = new JComboBox();


        for (Produto nameP : listaProdutos){
            comboPid02.addItem(nameP.getNameProduct());
        }

        comboPid02.setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                JButton botao = new JButton();
                botao.setPreferredSize(new Dimension(0, 0)); //Oculta
                botao.setEnabled(false);                     //Desativa
                botao.setFocusable(false);
                botao.setVisible(false);
                return botao;

            }
        });


        comboPid02.setEditable(true);
        Component editor = comboPid02.getEditor().getEditorComponent();
        if (editor instanceof JTextField) {
            JTextField campo = (JTextField) editor;
            campo.setBorder(null);
            campo.setBackground(null);
            campo.setText("");
            campo.setCaretColor(Color.WHITE);
        }


        // Mostra o popup
        SwingUtilities.invokeLater(comboPid02::showPopup);
        comboPid02.setVisible(true);



        comboPid02.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                revalidate();

                JTextField textoProd = textoProduto;
                textoProd.setText("");

                botaoPesquisa.setVisible(false);

                if (comboPid02.getSelectedItem() == null){
                    return;
                }


                Produto produto = null;
                String prodAba = null;

                for (Produto p : janelaCadastroProdutos.getListaProdutos()) {
                    if (Objects.equals(comboPid02.getSelectedItem(), p.getNameProduct())) {
                        produto = p;
                        prodAba = p.abaProdutos();
                    }

                }

                textoProduto.setName("");

                try {

                    comboPid02.setVisible(false);


                    String[] produtoFinal = prodAba.split("-");
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


                    ImageIcon iconExcluir = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Main/Application/Resources/Images/lixeiraExcluirProduto.png")));
                    JButton botaoExcluir = new JButton(iconExcluir);
                    botaoExcluir.setBounds(450, 80, 40, 45);
                    JLabel textAt = new JLabel("Editar Produto");
                    textAt.setFont(new Font("arial", Font.PLAIN, 20));
                    textAt.setBounds(420, 120, 150, 40);


                    Produto finalProduto = produto;

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

                } catch (NullPointerException n) {
                    revalidate();
                    repaint();
                }


            }
        });

        add(comboPid02);



    }






}



