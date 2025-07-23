package loja.model.produto;
import java.math.BigDecimal;
import java.time.LocalDate;

public class ProdutoPerecivel extends ProdutoFisico {
    LocalDate validade;

    public ProdutoPerecivel(int codigo, String nome, BigDecimal preco, int estoque, BigDecimal peso, LocalDate validade) {
        super(codigo, nome, preco, estoque, peso);
        this.validade=validade;
    }

    public LocalDate getValidade() {
        return validade;
    }
    
    public void setValidade(LocalDate validade) {
    	this.validade=validade;
    }

    public void printarDados() {
        super.printarDadosPereciveis();
        System.out.println(" | Validade: "+validade);
    }
}
