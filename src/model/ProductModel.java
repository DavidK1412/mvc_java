package model;

public class ProductModel {
    private int prod_id;
    private String prod_titulo;

    public ProductModel(){}

    public ProductModel(int prod_id, String prod_titulo) {
        this.prod_id = prod_id;
        this.prod_titulo = prod_titulo;
    }

    public int getProd_id() {
        return prod_id;
    }

    public void setProd_id(int prod_id) {
        this.prod_id = prod_id;
    }

    public String getProd_titulo() {
        return prod_titulo;
    }

    public void setProd_titulo(String prod_titulo) {
        this.prod_titulo = prod_titulo;
    }
}
