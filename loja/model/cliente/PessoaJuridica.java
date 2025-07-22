package loja.model.cliente;

public class PessoaJuridica extends Cliente{
    private String cnpj;

    public PessoaJuridica (String id, String nome, String endereco, String telefone,String cnpj) {
        super(id,nome,endereco,telefone);
        cnpj=this.cnpj;
    }

    public String getCnpj(){
        return this.cnpj;
    }

    public void printarDados() {
        super.printarDados();
        System.out.printf(" CNPJ: %s",this.cnpj);
    }
}
