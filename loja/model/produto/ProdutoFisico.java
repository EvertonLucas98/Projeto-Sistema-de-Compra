package loja.model.produto;
import java.math.BigDecimal;

public class ProdutoFisico extends Produto {
    private BigDecimal peso;

    public ProdutoFisico(int codigo, String nome, BigDecimal preco, int estoque, BigDecimal peso) {
        super(codigo, nome, preco, estoque);
        this.peso=peso;
    }
    
    public BigDecimal getPeso(){
        return this.peso;
    }

    public void printarDados() {
        super.printarDados();
        System.out.printf("Produto fisico-Peso: %.2f\n",peso);
    }
}
