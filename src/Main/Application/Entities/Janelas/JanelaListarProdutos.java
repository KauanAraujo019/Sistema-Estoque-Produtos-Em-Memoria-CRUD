package Main.Application.Entities.Janelas;

import Main.Application.Entities.Produto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;


public class JanelaListarProdutos extends JFrame {

    protected JanelaPrincipal janelaPrincipal;
    protected JanelaCadastroProdutos janelaCadastroProdutos;


    public JanelaListarProdutos(JanelaPrincipal janelaPrincipal, JanelaCadastroProdutos janelaCadastroProdutos){

        this.janelaPrincipal = janelaPrincipal;
        this.janelaCadastroProdutos = janelaCadastroProdutos;


        JPanel listagemP = new JPanel();
        listagemP.setLayout(null);

        JScrollPane jScrollPane = new JScrollPane(listagemP);
        jScrollPane.setVisible(false);

        ImageIcon iconeListagemP = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Main/Application/Resources/Images/logoListagemProduto.png")));
        JLabel logoListagemProduto = new JLabel(iconeListagemP);
        logoListagemProduto.setBounds(517, 0,563, 471);



        setTitle("SISTEMA CADASTRO DE PRODUTOS");
        setBounds(0, 0, 1080, 720);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);




        JButton btnListar = new JButton("LISTAR PRODUTOS ");

        JButton btnVoltar = new JButton("VOLTAR ");
        btnVoltar.setBounds(20, 380, 130, 40);
        btnVoltar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                janelaPrincipal.setVisible(true);
                setVisible(false);
                listagemP.removeAll();
                listagemP.setVisible(false);
                btnListar.setVisible(true);
                jScrollPane.setVisible(false);

            }
        });


        btnListar.setBounds(20, 330, 130, 40);
        btnListar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));




        btnListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JLabel listId = new JLabel("ID");
                listId.setBounds(0, 0, 90, 40);
                listId.setFont(new Font("Arial", Font.BOLD,26));
                listId.setHorizontalAlignment(SwingConstants.LEFT);
                listId.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));



                JLabel listNome = new JLabel("PRODUTO");
                listNome.setBounds(90, 0, 250, 40);
                listNome.setFont(new Font("Arial", Font.BOLD,26));
                listNome.setHorizontalAlignment(SwingConstants.LEFT);
                listNome.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));


                JLabel listPreco = new JLabel("PREÇO");
                listPreco.setBounds(340, 0, 150, 40);
                listPreco.setFont(new Font("arial", Font.BOLD ,26));
                listPreco.setHorizontalAlignment(SwingConstants.LEFT);
                listPreco.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));


                JLabel uomLabel = new JLabel("UOM");
                uomLabel.setBounds(490, 0, 100, 40);
                uomLabel.setFont(new Font("arial", Font.BOLD, 26));
                uomLabel.setHorizontalAlignment(SwingConstants.LEFT);
                uomLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));


                JLabel listQuant = new JLabel("UN");
                listQuant.setBounds(590,0,160,40);
                listQuant.setFont(new Font("arial", Font.BOLD, 26));
                listQuant.setHorizontalAlignment(SwingConstants.LEFT);
                listQuant.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));



                listagemP.add(listId);
                listagemP.add(listNome);
                listagemP.add(listPreco);
                listagemP.add(uomLabel);
                listagemP.add(listQuant);


                int j = 0;
                for (Produto produto : janelaCadastroProdutos.getListaProdutos()) {

                    String idProd = String.valueOf(produto.getIdProduto());
                    JLabel idLabelP = new JLabel(idProd);
                    idLabelP.setBounds(0, 40 + j, 90, 40);
                    idLabelP.setFont(new Font("arial", Font.PLAIN, 20));
                    idLabelP.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                    listagemP.add(idLabelP);

                    JLabel nomeLabelP = new JLabel(produto.getNameProduct());
                    nomeLabelP.setBounds(90, 40 + j, 250, 40);
                    nomeLabelP.setFont(new Font("arial", Font.PLAIN, 20));
                    nomeLabelP.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                    listagemP.add(nomeLabelP);

                    String precoProd = String.format("%,.2f", produto.getPriceProduct());
                    JLabel precoLabelP = new JLabel("R$ " + precoProd);
                    precoLabelP.setBounds(340, 40 + j, 150, 40);
                    precoLabelP.setFont(new Font("arial", Font.PLAIN, 20));
                    precoLabelP.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                    listagemP.add(precoLabelP);

                    String uomprod = produto.getSimbolPreco();
                    JLabel uomLabelP = new JLabel(uomprod);
                    uomLabelP.setBounds(490, 40 + j, 100, 40);
                    uomLabelP.setFont(new Font("arial", Font.BOLD, 20));
                    uomLabelP.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                    listagemP.add(uomLabelP);

                    String idProduto = String.valueOf(produto.getQuantityProduct());
                    JLabel quantLabelP = new JLabel(idProduto);
                    quantLabelP.setBounds(590, 40 + j, 150, 40);
                    quantLabelP.setFont(new Font("arial", Font.PLAIN, 20));
                    quantLabelP.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                    listagemP.add(quantLabelP);

                    j = j + 40;
                    System.out.println(produto.getNameProduct());

                }

                listagemP.setPreferredSize(new Dimension(600, j+40));
                listagemP.setBackground(Color.WHITE);
                listagemP.setVisible(false);

                if (janelaCadastroProdutos.getListaProdutos().isEmpty()){
                    JOptionPane.showMessageDialog(null,"LISTA VAZIA!");
                    listagemP.setVisible(false);
                }
                else{
                    listagemP.setVisible(true);
                    btnListar.setVisible(false);
                    jScrollPane.setVisible(true);
                }


            }

        });


        jScrollPane.setBounds(385, 0, 680, 680);
        jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane.getVerticalScrollBar().setUnitIncrement(30);

        add(jScrollPane);
        add(btnVoltar);
        add(btnListar);
        add(logoListagemProduto);

        setVisible(false);

    }

}
