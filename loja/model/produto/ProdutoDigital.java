package loja.model.produto;
import java.math.BigDecimal;

public class ProdutoDigital extends Produto {
    private String categoria;

    public ProdutoDigital(int codigo, String nome, BigDecimal preco, int estoque, String categoria) {
        super(codigo, nome, preco, estoque);
        this.categoria=categoria;
    }
    
    public void PrintarDados() {
        super.printarDados();
        System.out.println("Produto digital-Categoria: "+categoria);
    }

}
