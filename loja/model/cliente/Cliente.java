package loja.model.cliente;

public class Cliente {
    private String cpf, nome, endereco, fone;

    public Cliente(String cpf, String nome, String endereco, String fone) {
        this.cpf = cpf;
        this.nome = nome;
        this.endereco = endereco;
        this.fone = fone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public void printarDados() {
        System.out.printf("CPF: %s | Nome: %s | Endereço: %s | Telefone: %s\n", cpf, nome, endereco, fone);
    }
}
