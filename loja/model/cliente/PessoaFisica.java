package loja.model.cliente;

import java.time.LocalDate;

public class PessoaFisica extends Cliente {
    private String cpf;
    private LocalDate dataNascimento;

    // Construtor
    public PessoaFisica(String id, String nome, String endereco, String telefone, String cpf, LocalDate dataNascimento) {
        super(id, nome, endereco, telefone);
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }

    // Getters e Setters
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void printarDados() {
        super.printarDados();
        System.out.println("CPF: "+cpf+" | Data de Nascimento: "+dataNascimento);
    }
}
