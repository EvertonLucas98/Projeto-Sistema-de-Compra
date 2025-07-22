package loja.model.cliente;

public class PessoaFisica extends Cliente{
    private String cpf;

    public PessoaFisica (String id, String nome, String endereco, String telefone,String cpf){
        super(id, nome,endereco,telefone);
        cpf=this.cpf;
    }

    public String getCpf(){
        return this.cpf;
    }

    public void printarDados() {
        super.printarDados();
        System.out.printf(" CPF: %s",this.cpf);
    }
}
