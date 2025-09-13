package Main.Application.Entities.Janelas;

import Main.Application.Entities.Produto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class JanelaPrincipal extends JFrame {

    protected JanelaCadastroProdutos janelaCadastrarP;
    protected JanelaListarProdutos janelaListarP;
    protected JanelaAtualizarProduto janelaAtualizarP;




    public JanelaPrincipal(){

        this.janelaCadastrarP = new JanelaCadastroProdutos(this);
        this.janelaListarP = new JanelaListarProdutos(this, janelaCadastrarP);
        this.janelaAtualizarP = new JanelaAtualizarProduto(this, janelaCadastrarP);




        setTitle("SISTEMA CADASTRO DE PRODUTOS");
        setBounds(0,0 ,1080,720);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setVisible(false);


        JButton botaoCadastroProduto = new JButton("CADASTRAR PRODUTOS");
        botaoCadastroProduto.setBounds(20, 20, 200, 40);
        botaoCadastroProduto.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));

        JButton botaoListarProduto = new JButton("LISTAR PRODUTOS");
        botaoListarProduto.setBounds(20, 70, 200, 40);
        botaoListarProduto.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        JButton botaoAtualizarProduto = new JButton("ATUALIZAR PRODUTOS");
        botaoAtualizarProduto.setBounds(20, 120, 200, 40);
        botaoAtualizarProduto.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        JButton botaoRemoverProduto = new JButton("REMOVER PRODUTOS");
        botaoRemoverProduto.setBounds(20, 170, 200, 40);
        botaoRemoverProduto.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));



        add(botaoCadastroProduto);
        add(botaoListarProduto);
        add(botaoAtualizarProduto);
        add(botaoRemoverProduto);



        botaoCadastroProduto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                janelaCadastrarP.setVisible(true);

            }
        });



        botaoListarProduto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                janelaListarP.setVisible(true);

            }
        });



        botaoAtualizarProduto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                janelaAtualizarP.setVisible(true);


            }
        });


    }

}