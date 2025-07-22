package loja.model.nota;
import java.math.BigDecimal;

public class ItemNota {
    private String nome;
    private int quantidade;
    private BigDecimal price;
    private BigDecimal subTotal;

    public ItemNota(String nome, int quantidade, BigDecimal price){
        this.nome = nome;
        this.quantidade = quantidade;
        this.price = price;
        this.subTotal = BigDecimal.valueOf(quantidade).multiply(precoAtual);
    }

    public String getNome(){
        return nome;
    }
    public int getQtd(){
        return quantidade;
    }
    public BigDecimal getPrice(){
        return price;
    }
    public BigDecimal getSubTotal(){
        return subTotal;
    }
}
