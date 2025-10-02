package Main.Application.Entities.Janelas;


import javax.swing.*;

public class JanelaRemoverProdutos extends JanelaAtualizarProduto {

    public JanelaRemoverProdutos(JanelaPrincipal janelaPrincipal, JanelaCadastroProdutos janelaCadastroProdutos) {
        super(janelaPrincipal, janelaCadastroProdutos);

    }

    @Override
    public void pesquisaNomeProduto(JTextField textoProduto, JButton botaoPesquisa) {
        System.out.println("macaquin");


    }

    @Override
    public void pesquisaIDProduto(JTextField textoProduto, JButton botaoPesquisa){
        System.out.println("viadin");
    }


}

