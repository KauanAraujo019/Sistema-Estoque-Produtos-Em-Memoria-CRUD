package Main.Application.Entities.Janelas;

import Main.Application.Entities.ActionsListeners.BotaoPesquisaID;
import Main.Application.Entities.ActionsListeners.ServiceActionListeners;
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
    protected JanelaPrincipal janelaPrincipal;
    protected JanelaCadastroProdutos janelaCadastroProdutos;

    protected JanelaAtualizarProduto jan = this;


    public JanelaAtualizarProduto(JanelaPrincipal janelaPrincipal, JanelaCadastroProdutos janelaCadastroProdutos){
        this.janelaPrincipal = janelaPrincipal;
        this.janelaCadastroProdutos = janelaCadastroProdutos;





        setTitle("SISTEMA CADASTRO DE PRODUTOS");
        setBounds(0, 0, 1080, 720);
        setLayout(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        revalidate();
        repaint();


        JLabel labelProduto = new JLabel("PESQUISAR PRODUTO:");
        labelProduto.setBounds(20, 20, 250, 40);
        labelProduto.setFont(new Font("arial", Font.BOLD, 20));

        JTextField textoProduto = new JTextField();
        textoProduto.setBounds(320, 20, 200,40);
        textoProduto.setFont(new Font("arial", Font.PLAIN ,25));




        JButton botaoVoltar = new JButton("VOLTAR");
        botaoVoltar.setBounds(20, 380, 130, 40);
        botaoVoltar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));


        JComboBox comboPid = new JComboBox();
        botaoVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                janelaPrincipal.setVisible(true);
                comboPid.setVisible(false);
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

                if (namePesquisaP.equals("ID")){

                    comboPid.removeAllItems();
                    comboPid.setBounds(250, 25, 320,40);
                    comboPid.setFont(new Font("arial", Font.PLAIN, 25));

                    ServiceActionListeners botaoPesquisaID = new BotaoPesquisaID(namePesquisaP, comboPid, textoProduto, janelaCadastroProdutos, jan);

                    botaoPesquisaID.runProgram();


                }
                else {

                    JComboBox comboPid02 = new JComboBox();

                    comboPid02.setSelectedItem(null);
                    comboPid02.removeAllItems();
                    comboPid02.setBounds(250, 25, 320,40);
                    comboPid02.setFont(new Font("arial", Font.PLAIN, 25));

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

                                                JOptionPane.showMessageDialog(null, "Produto Atualizado com sucesso!");

                                                remove(nameProd);
                                                remove(priceProd);
                                                remove(quantProd);
                                                remove(comboP);

                                                remove(labelNameProd);
                                                remove(labelPrecoProd);
                                                remove(labelQuantProd);
                                                remove(labelSimbpProd);

                                                remove(textNameProd);
                                                remove(textPrecoProd);
                                                remove(textSimbpProd);
                                                remove(textQuantProd);

                                                botaoSalvar.setVisible(false);
                                                textSalv.setVisible(false);

                                                botaoAtualizar.setVisible(false);
                                                textAt.setVisible(false);

                                                botaoPesquisa.setVisible(true);


                                                revalidate();
                                                repaint();

                                            }
                                        });


                                        add(nameProd);
                                        add(priceProd);
                                        add(comboP);
                                        add(quantProd);
                                        add(botaoSalvar);
                                        add(textSalv);


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
                                add(botaoAtualizar);
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
        });




        add(labelProduto);
        add(textoProduto);
        add(botaoVoltar);
        add(botaoPesquisa);
        add(comboPesq);

        setVisible(false);





    }

}
