package itbamteste.bean;

import java.io.Serializable;

public class ProdutosParseJson implements Serializable {

    private ListaProdutoBean data;

    public ListaProdutoBean getData() {
        return data;
    }

    public void setData(ListaProdutoBean data) {
        this.data = data;
    }

}
