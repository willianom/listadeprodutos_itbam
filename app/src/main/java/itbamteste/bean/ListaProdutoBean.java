package itbamteste.bean;

import java.io.Serializable;
import java.util.List;

public class ListaProdutoBean implements Serializable {

    private String errors;
    private List<ProdutosBean> products;

    public String getErrors() {
        return errors;
    }

    public void setErrors(String errors) {
        this.errors = errors;
    }

    public List<ProdutosBean> getProducts() {
        return products;
    }

    public void setProducts(List<ProdutosBean> products) {
        this.products = products;
    }
}
