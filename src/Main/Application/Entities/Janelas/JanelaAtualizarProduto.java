package Main.Application.Entities.Janelas;

import Main.Application.Entities.ActionsListenersJanelaAtualizarProdutos.BotaoPesquisaID;
import Main.Application.Entities.ActionsListenersJanelaAtualizarProdutos.BotaoPesquisaNome;
import Main.Application.Entities.ActionsListenersJanelaAtualizarProdutos.ServiceActionListeners;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

                    ServiceActionListeners botaoPesquisaNome = new BotaoPesquisaNome(textoProduto, janelaCadastroProdutos, botaoPesquisa, jan);

                    botaoPesquisaNome.runProgram();

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
