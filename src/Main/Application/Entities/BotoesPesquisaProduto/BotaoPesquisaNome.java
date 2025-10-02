package Main.Application.Entities.BotoesPesquisaProduto;

import Main.Application.Entities.Janelas.JanelaAtualizarProduto;
import Main.Application.Entities.Janelas.JanelaCadastroProdutos;
import Main.Application.Entities.Produto;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BotaoPesquisaNome implements ServiceActionListeners{

    JTextField textoProduto;
    JanelaCadastroProdutos janelaCadastroProdutos;
    JButton botaoPesquisa;
    JComboBox comboPid02;
    JanelaAtualizarProduto janelaAtualizarProduto;

    public BotaoPesquisaNome(JTextField textoProduto, JanelaCadastroProdutos janelaCadastroProdutos,JComboBox comboPid02, JButton botaoPesquisa, JanelaAtualizarProduto janelaAtualizarProduto){
        this.textoProduto = textoProduto;
        this.janelaCadastroProdutos = janelaCadastroProdutos;
        this.botaoPesquisa = botaoPesquisa;
        this.janelaAtualizarProduto = janelaAtualizarProduto;
        this.comboPid02 = comboPid02;
    }



    @Override
    public void runProgram() {
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
                janelaAtualizarProduto.revalidate();


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


                    ImageIcon iconAtualizar = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Main/Application/Resources/Images/iconeEditarProduto.png")));
                    JButton botaoAtualizar = new JButton(iconAtualizar);
                    botaoAtualizar.setBounds(450, 80, 40, 40);
                    JLabel textAt = new JLabel("Editar Produto");
                    textAt.setFont(new Font("arial", Font.PLAIN, 20));
                    textAt.setBounds(420, 120, 150, 40);



                    Produto finalProduto = produto;
                    botaoAtualizar.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                            textNameProd.setVisible(false);
                            textPrecoProd.setVisible(false);
                            textSimbpProd.setVisible(false);
                            textQuantProd.setVisible(false);


                            JTextField nameProd = new JTextField(nameP);
                            nameProd.setBounds(150, 80, 290, 35);
                            nameProd.setFont(new Font("arial", Font.PLAIN, 25));


                            JTextField priceProd = new JTextField(precoP);
                            priceProd.setBounds(150, 120, 150, 35);
                            priceProd.setFont(new Font("arial", Font.PLAIN, 25));


                            JTextField quantProd = new JTextField(quantP);
                            quantProd.setBounds(150, 160, 150, 35);
                            quantProd.setFont(new Font("arial", Font.PLAIN, 25));

                            JComboBox comboP = new JComboBox();
                            comboP.setBounds(150, 200, 60, 35);
                            comboP.addItem(" UN");
                            comboP.addItem(" CX");
                            comboP.addItem(" KG");
                            comboP.addItem(" G");
                            comboP.addItem(" PCT");
                            comboP.addItem(" MT");

                            comboP.setSelectedItem(simbP);


                            botaoAtualizar.setVisible(false);
                            textAt.setVisible(false);

                            ImageIcon iconSave = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Main/Application/Resources/Images/SalvarAlteracaoProduto.png")));

                            JButton botaoSalvar = new JButton(iconSave);
                            botaoSalvar.setBounds(450, 80, 40, 40);
                            JLabel textSalv = new JLabel("Salvar");
                            textSalv.setFont(new Font("arial", Font.PLAIN, 20));
                            textSalv.setBounds(445, 120, 150, 40);


                            botaoSalvar.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    String nomeProd = nameProd.getText();
                                    double precoProd = Double.parseDouble(priceProd.getText());
                                    int quantProduto = Integer.parseInt(quantProd.getText());
                                    String simbProd = String.valueOf(comboP.getSelectedItem());

                                    finalProduto.atualizarProduto(nomeProd, precoProd, quantProduto, simbProd);

                                    JOptionPane.showMessageDialog(null, "Produto "+nomeProd+" Atualizado com sucesso!");

                                    janelaAtualizarProduto.remove(nameProd);
                                    janelaAtualizarProduto.remove(priceProd);
                                    janelaAtualizarProduto.remove(quantProd);
                                    janelaAtualizarProduto.remove(comboP);

                                    janelaAtualizarProduto.remove(labelNameProd);
                                    janelaAtualizarProduto.remove(labelPrecoProd);
                                    janelaAtualizarProduto.remove(labelQuantProd);
                                    janelaAtualizarProduto.remove(labelSimbpProd);

                                    janelaAtualizarProduto.remove(textNameProd);
                                    janelaAtualizarProduto.remove(textPrecoProd);
                                    janelaAtualizarProduto.remove(textSimbpProd);
                                    janelaAtualizarProduto.remove(textQuantProd);

                                    botaoSalvar.setVisible(false);
                                    textSalv.setVisible(false);

                                    botaoAtualizar.setVisible(false);
                                    textAt.setVisible(false);

                                    botaoPesquisa.setVisible(true);


                                    janelaAtualizarProduto.revalidate();
                                    janelaAtualizarProduto.repaint();

                                }
                            });


                            janelaAtualizarProduto.add(nameProd);
                            janelaAtualizarProduto.add(priceProd);
                            janelaAtualizarProduto.add(comboP);
                            janelaAtualizarProduto.add(quantProd);
                            janelaAtualizarProduto.add(botaoSalvar);
                            janelaAtualizarProduto.add(textSalv);


                            janelaAtualizarProduto.repaint();

                        }
                    });


                    janelaAtualizarProduto.add(labelNameProd);
                    janelaAtualizarProduto.add(textNameProd);
                    janelaAtualizarProduto.add(labelPrecoProd);
                    janelaAtualizarProduto.add(textPrecoProd);
                    janelaAtualizarProduto.add(labelQuantProd);
                    janelaAtualizarProduto.add(textQuantProd);
                    janelaAtualizarProduto.add(labelSimbpProd);
                    janelaAtualizarProduto.add(textSimbpProd);
                    janelaAtualizarProduto.add(botaoAtualizar);
                    janelaAtualizarProduto.add(textAt);

                    janelaAtualizarProduto.revalidate();
                    janelaAtualizarProduto.repaint();

                } catch (NullPointerException n) {
                    janelaAtualizarProduto.revalidate();
                    janelaAtualizarProduto.repaint();
                }


            }
        });

        janelaAtualizarProduto.add(comboPid02);



    }



}
