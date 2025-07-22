package loja.model.cliente;

import java.time.LocalDate;

public class PessoaJuridica extends Cliente {
    private String cnpj;

    // Construtor
    public PessoaJuridica(String id, String nome, String endereco, String telefone, String cnpj) {
        super(id, nome, endereco, telefone);
        this.cnpj = cnpj;
    }

    // Getters e Setters
    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public void printarDados() {
        super.printarDados();
        System.out.println("CNPJ: "+cnpj);
    }
}
