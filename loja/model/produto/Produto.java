package loja.model.produto;
import java.math.BigDecimal;

public class Produto {
    private int codigo;
    private String nome;
    private BigDecimal preco;
    private int estoque;

    public Produto(int codigo, String nome, BigDecimal preco, int estoque) {
        this.codigo=codigo;
        this.nome=nome;
        this.preco=preco;
        this.estoque=estoque;
    }

    public int getCodigo() {
        return codigo;
    }
    
    public String getNome() {
        return nome;
    }
    public int getEstoque() {
        return this.estoque;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPreco(BigDecimal novoPreco) {
        this.preco=novoPreco;
    }

    public void setEstoque(int novoEstoque) {
        this.estoque=novoEstoque;
    }
    
    public void printarDados() {
        System.out.printf("Produto %d: %s | R$ %s | %d unidades\n", codigo, nome, preco, estoque);
    }
}
