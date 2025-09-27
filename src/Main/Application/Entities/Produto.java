package Main.Application.Entities;


public class Produto {
    private final int idProduto;
    private String nameProduct;
    private double priceProduct;
    private int quantityProduct;
    private String simbolPreco;

    public Produto(int ID, String nomep, double precop, int quantP){
        this.nameProduct = nomep;
        this.priceProduct = precop;
        this.quantityProduct = quantP;
        this.idProduto = ID;
    }






    @Override
    public String toString() {
        return "ID: "+idProduto+" PRODUTO: "+getNameProduct()+"   PREÇO: "+String.format("%.2f", getPriceProduct())+"   QUANTIDADE: "+getQuantityProduct();
    }

    public String abaProdutos(){

        return getNameProduct()+" - R$"+String.format("%.2f", getPriceProduct())+" - UN:"+getQuantityProduct();

    }

    static int id = 0;
    public static int adicionarIdP(){
        id++;
        return id;

    }



    public String getSimbolPreco(){
        return simbolPreco;
    }


    public void setSimbolPreco(String simb){
        this.simbolPreco = simb;
    }



    public int getIdProduto() {
        return idProduto;
    }



    public String getNameProduct() {
        return nameProduct;
    }


    public double getPriceProduct() {
        return priceProduct;
    }


    public int getQuantityProduct() {
        return quantityProduct;
    }



}
