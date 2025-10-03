package Main.Application.Entities.Janelas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Objects;


public class JanelaPrincipal extends JFrame {

    protected JanelaCadastroProdutos janelaCadastrarP;
    protected JanelaListarProdutos janelaListarP;
    protected JanelaAtualizarProduto janelaAtualizarP;
    protected JanelaRemoverProdutos janelaRemoverP;




    public JanelaPrincipal(){

        this.janelaCadastrarP = new JanelaCadastroProdutos(this);
        this.janelaListarP = new JanelaListarProdutos(this, janelaCadastrarP);
        this.janelaAtualizarP = new JanelaAtualizarProduto(this, janelaCadastrarP);
        this.janelaRemoverP = new JanelaRemoverProdutos(this, janelaCadastrarP);

        ImageIcon iconeSistema01 = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Main/Application/Resources/Images/logoPrincipal02.png")));
        JLabel logoSistema01 = new JLabel(iconeSistema01);
        logoSistema01.setBounds(600, -40,400, 400);

        ImageIcon iconeSistema02 = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Main/Application/Resources/Images/logoPrincipal01.png")));
        JLabel logoSistema02 = new JLabel(iconeSistema02);
        logoSistema02.setBounds(-100, 120,450, 450);

        JLabel nomeSistema = new JLabel("SUPERMARKET JAVA");
        nomeSistema.setBounds(620, 350, 450, 48);
        nomeSistema.setFont(new Font("Calibri", Font.BOLD, 48));
        nomeSistema.setForeground(Color.blue);



        JLabel tituloCriadorSistema = new JLabel("DESENVOLVIDO POR:  KAUAN ARAÚJO                 EMAIL: kauanaquinogo@gmail.com");
        tituloCriadorSistema.setBounds(0,660,720,18);
        tituloCriadorSistema.setFont(new Font("arial", Font.PLAIN, 18));
        tituloCriadorSistema.setBackground(Color.cyan);

        setTitle("SISTEMA CADASTRO DE PRODUTOS");
        setBounds(0,0 ,1080,720);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);
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
        add(tituloCriadorSistema);
        add(logoSistema01);
        add(logoSistema02);
        add(nomeSistema);



        botaoCadastroProduto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                janelaCadastrarP.setVisible(true);
                setVisible(false);

            }
        });



        botaoListarProduto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                janelaListarP.setVisible(true);
                setVisible(false);

            }
        });



        botaoAtualizarProduto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                janelaAtualizarP.revalidate();
                janelaAtualizarP.setVisible(true);
                setVisible(false);


            }
        });



        botaoRemoverProduto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                janelaRemoverP.setVisible(true);
                setVisible(false);

            }
        });






    }

}