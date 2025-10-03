package Main.Application.Entities.BotoesPesquisaProduto;

import Main.Application.Entities.Janelas.JanelaAtualizarProduto;
import Main.Application.Entities.Janelas.JanelaCadastroProdutos;
import Main.Application.Entities.Produto;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class BotaoPesquisaID implements ServiceActionListeners{


    JTextField textoProduto;
    JanelaCadastroProdutos janelaCadastroProdutos;
    JanelaAtualizarProduto janelaAtualizarProduto;
    JButton botaoPesquisa;


    public BotaoPesquisaID(JTextField textoProduto, JanelaCadastroProdutos janelaCadastroProdutos, JanelaAtualizarProduto janelaAtualizarProduto, JButton botaoPesquisa){


        this.textoProduto = textoProduto;
        this.janelaCadastroProdutos = janelaCadastroProdutos;
        this.janelaAtualizarProduto = janelaAtualizarProduto;
        this.botaoPesquisa = botaoPesquisa;

    }


    public void runProgram(){

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


                    ImageIcon iconAtualizar = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Main/Application/Resources/Images/iconeEditarProduto.png")));
                    JButton botaoAtualizar = new JButton(iconAtualizar);
                    botaoAtualizar.setBounds(450, 80, 40, 40);
                    JLabel textAt = new JLabel("Editar Produto");
                    textAt.setFont(new Font("arial", Font.PLAIN, 20));
                    textAt.setBounds(420, 120, 150, 40);


                    Produto finalProduto = produto;

                    botaoPesquisa.setVisible(false);

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

                                    JOptionPane.showMessageDialog(null, "Produto Atualizado com sucesso!");


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


                }catch (NullPointerException n){
                    janelaAtualizarProduto.revalidate();
                    janelaAtualizarProduto.repaint();
                }



                janelaAtualizarProduto.revalidate();
                janelaAtualizarProduto.repaint();


            }catch (NumberFormatException numb){
                JOptionPane.showMessageDialog(null, "ID invalido!");

            }



    }
















}
