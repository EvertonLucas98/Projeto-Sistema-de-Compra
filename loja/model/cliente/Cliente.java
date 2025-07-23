package loja.model.cliente;
import loja.ui.Imprimir;
import loja.model.nota.*;

public abstract class Cliente implements Imprimir {
    protected String id;
    protected String nome;
    protected String endereco;
    protected String telefone;
    protected ItemNota[] itemNota;
    protected Nota nota;
    protected int totalItens;

    public Cliente(String id, String nome, String endereco, String telefone) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.itemNota = new ItemNota[100];
        this.nota = new Nota();
        this.totalItens = 0;
    }

    public String getId() {
        return id;
    }
    
    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public ItemNota[] getItemNota() {
        return itemNota;
    }

    public Nota getNota() {
        return nota;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setItemNota(ItemNota[] itemNota) {
        this.itemNota = itemNota;
    }

    public void setNota(Nota nota) {
        this.nota = nota;
    }

    public void printarDados() {
        System.out.printf("ID: %s | Nome: %s | Endereço: %s | Telefone: %s | ", id, nome, endereco, telefone);
    }

    public int getTotalItens() {
        return totalItens;
    }

    public void setTotalItens(int totalItens) {
        this.totalItens += totalItens;
    }

    public void printarNota() {
        System.out.println("\n\tNota Fiscal #");
        for(int i=0; i<getTotalItens(); i++) {
            if(itemNota[i]!=null) {
            System.out.print("\tProduto: "+itemNota[i].getProduto().getNome()+" | Preço: "+itemNota[i].getProduto().getPreco()+"");
            System.out.println(" | Quantidade: "+itemNota[i].getQuantidade()+" | Subtotal: "+itemNota[i].getSubtotal()+"");
        	}
        }
    }
}
