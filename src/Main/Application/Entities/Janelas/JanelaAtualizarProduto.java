package Main.Application.Entities.Janelas;

import Main.Application.Entities.BotoesPesquisaProduto.BotaoPesquisaID;
import Main.Application.Entities.BotoesPesquisaProduto.BotaoPesquisaNome;
import Main.Application.Entities.BotoesPesquisaProduto.ServiceActionListeners;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class JanelaAtualizarProduto extends JFrame {
    protected JanelaPrincipal janelaPrincipal;
    protected JanelaCadastroProdutos janelaCadastroProdutos;

    protected JanelaAtualizarProduto jan = this;

    protected JComboBox comboPesq;
    protected JLabel labelProduto;
    protected JTextField textoProduto;
    protected JButton botaoPesquisa;
    protected JLabel logoAtualizarProduto;

    public JComboBox getjComboBox(){
        return comboPesq;
    }

    public JLabel getJlabelProduto(){
        return labelProduto;
    }

    public JTextField getTextoProduto(){
        return textoProduto;
    }

    public JButton getBotaoPesquisa(){
        return botaoPesquisa;
    }

    public JLabel getLogoAtualizarProduto(){
        return logoAtualizarProduto;
    }





    public JanelaAtualizarProduto(JanelaPrincipal janelaPrincipal, JanelaCadastroProdutos janelaCadastroProdutos){
        this.janelaPrincipal = janelaPrincipal;
        this.janelaCadastroProdutos = janelaCadastroProdutos;



        setTitle("SISTEMA CADASTRO DE PRODUTOS");
        setBounds(0, 0, 1080, 720);
        setLayout(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        revalidate();
        repaint();


        ImageIcon iconeAtualizarP = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Main/Application/Resources/Images/logoEditarProduto.png")));
        logoAtualizarProduto = new JLabel(iconeAtualizarP);
        logoAtualizarProduto.setBounds(650, 0,400, 400);




        labelProduto = new JLabel("PESQUISAR PRODUTO:");
        labelProduto.setBounds(20, 20, 250, 40);
        labelProduto.setFont(new Font("arial", Font.BOLD, 20));

        textoProduto = new JTextField();
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
        botaoPesquisa = new JButton(icon);
        botaoPesquisa.setBounds(530, 20, 40,40);

        comboPesq = new JComboBox();
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

                    pesquisaIDProduto(textoProduto, botaoPesquisa);

                }
                else {
                    pesquisaNomeProduto(textoProduto, botaoPesquisa);

                }


            }
        });


        add(labelProduto);
        add(textoProduto);
        add(botaoVoltar);
        add(botaoPesquisa);
        add(comboPesq);
        add(logoAtualizarProduto);

        setVisible(false);

    }

    public void pesquisaIDProduto(JTextField textoProduto, JButton botaoPesquisa){


        ServiceActionListeners botaoPesquisaID = new BotaoPesquisaID(textoProduto, janelaCadastroProdutos, jan, botaoPesquisa);

        botaoPesquisaID.runProgram();

    }

    public void pesquisaNomeProduto(JTextField textoProduto, JButton botaoPesquisa){
        JComboBox comboPid02 = new JComboBox();
        comboPid02.setSelectedItem(null);
        comboPid02.removeAllItems();
        comboPid02.setBounds(250, 25, 320,40);
        comboPid02.setFont(new Font("arial", Font.PLAIN, 25));

        ServiceActionListeners botaoPesquisaNome = new BotaoPesquisaNome(textoProduto, janelaCadastroProdutos, comboPid02, botaoPesquisa, jan);

        botaoPesquisaNome.runProgram();


    }




}
