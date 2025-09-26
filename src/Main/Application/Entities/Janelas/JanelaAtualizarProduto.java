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

                comboPid.removeAllItems();
                comboPid.setBounds(250, 25, 270,40);
                comboPid.setFont(new Font("arial", Font.PLAIN, 25));


                if (textoProduto.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Valor inválido!");
                    return;
                }

                String namePesquisaP = (String) comboPesq.getSelectedItem();

                if (namePesquisaP.equals("ID")){

                    try {

                        String pesquisaP = textoProduto.getText();
                        int idP = Integer.parseInt(pesquisaP);

                        if (idP <= 0 || idP > janelaCadastroProdutos.getListaProdutos().size()){
                            JOptionPane.showMessageDialog(null, "Produto não encontrado");
                            return;
                        }

                        comboPid.addItem(janelaCadastroProdutos.getListaProdutos().get(idP-1).abaProdutos());

                        comboPid.setUI(new BasicComboBoxUI() {
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


                        comboPid.setEditable(true);
                        Component editor = comboPid.getEditor().getEditorComponent();
                        if (editor instanceof JTextField) {
                            JTextField campo = (JTextField) editor;
                            campo.setBorder(null);
                            campo.setBackground(null);
                            campo.setText("");
                            campo.setCaretColor(Color.WHITE);
                        }

                        SwingUtilities.invokeLater(comboPid::showPopup);
                        comboPid.setVisible(true);
                        add(comboPid);


                        revalidate();
                        repaint();


                        return;





                        // Mostra o popup



                    }catch (NumberFormatException numb){
                        JOptionPane.showMessageDialog(null, "ID invalido!");
                        return;
                    }
                }

                List<Produto> listaProdutos = new ArrayList<>();

                String pesquisaP = textoProduto.getText();
                char[] prodPesq = pesquisaP.toCharArray();

                Produto prodAdd = null;
                try {

                    for (Produto produto : janelaCadastroProdutos.getListaProdutos()) {
                        char[] arrayProd = produto.getNameProduct().toCharArray();
                        int cont = 0;

                        for (int i = 0; i < prodPesq.length; i++) {

                            if (cont > 0 && arrayProd.length < prodPesq.length){

                                break;

                            }

                            if (prodPesq[i] == arrayProd[i]) {
                                cont++;

                            }
                            else{

                            }

                        }


                        if (cont == prodPesq.length) {
                            listaProdutos.add(produto);

                        }

                    }

                    if (listaProdutos.isEmpty()){
                        JOptionPane.showMessageDialog(null, "Produtuuu");
                        return;
                    }



                }catch (ArrayIndexOutOfBoundsException ofBounds){
                    listaProdutos.add(prodAdd);

                }


                for (int i = 0; i < listaProdutos.size(); i++){
                    comboPid.addItem(listaProdutos.get(i).abaProdutos());
                }

                comboPid.setUI(new BasicComboBoxUI() {
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


                comboPid.setEditable(true);
                Component editor = comboPid.getEditor().getEditorComponent();
                if (editor instanceof JTextField) {
                    JTextField campo = (JTextField) editor;
                    campo.setBorder(null);
                    campo.setBackground(null);
                    campo.setText("");
                    campo.setCaretColor(Color.WHITE);
                }



                // Mostra o popup
                SwingUtilities.invokeLater(comboPid::showPopup);
                comboPid.setVisible(true);

                comboPid.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("oi");
                    }
                });


                add(comboPid);


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
