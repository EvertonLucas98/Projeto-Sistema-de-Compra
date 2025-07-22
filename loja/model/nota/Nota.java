package loja.model.nota;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Random;

import loja.model.produto.Produto;

public class Nota {
    private String clienteNome;
    private String clienteID;
    private int notaID;
    public LocalDate notaData;
    public ItemNota[] carrinho;
    public BigDecimal valorTotal;

    // gerando id da nota
    Random random = new Random();
    int id = random.nextInt(10000);
    
    public Nota(String clienteNome, String clienteID){
        this.clienteNome = clienteNome;
        this.clieteID = clienteID;
        this.notaID = id;
    }

    public String getNome(){
        return clienteNome;
    }
    public String getClienteID(){
        return clienteID;
    }
    public int getNotaID(){
        return notaID;
    }
    public LocalDate getData(){
        return notaData;
    }
    public ItemNota getItens(){
        return carrinho;
    }
    public BigDecimal getValorTotal(){
        return valorTotal;
    }

    public registrarCompras(){
        if (produtosDigitais[0] != null || produtosFisicos[0] != null || produtosPereciveis[0] != null) {
            System.out.println("================ Lista de Produtos ================");
            listarProdutos(produtosDigitais);
            listarProdutos(produtosFisicos);
            listarProdutos(produtosPereciveis);

            int qtdTotal = InputUtils.lerInt("Quantos produtos deseja adicionar? ");
            if (qtdTotal > 0){
                carrinho = new ItemNota[qtdTotal];
            } else {
                return "Saindo..";
            }
            
            for (int i=0; i<qtdTotal; i++) {
                System.out.println("Escolha um tipo do Produto:");
                tipo = InputUtils.lerIntNumIntervalo("1. Digital, 2. Fisico ou 3. Perecível: ", 1, 3);
                String nomeProduto = InputUtils.lerString("\tNome do produto: ");
                int quantidade = InputUtils.lerInt("Quantas unidades? ");
                
                Produto atual = buscarProduto(tipo, produtosFisicos, produtosDigitais, produtosPereciveis, nomeProduto);

                ItemNota novoItem = new ItemNota(atual.getNome(), quantidade, atual.getPreco());
                carrinho[i] = novoItem;
                valorTotal = BigDecimal.valueOf(novoItem.getSubTotal()).add(valorTotal);
            }

            notaData = InputUtils.lerData("Data: ");

            return carrinho;
        } else {
            return "Nenhum produto foi cadastrado.";
        }
    }

    public void printaNota(){
        System.out.println("================ Nota Fiscal ================");
        System.out.println(getNotaID() + "          " + getData());
        System.out.println("\n");
        System.out.println(getNome() + "          " + getClienteID());

        for (int i=0; i<carrinho.length; i++){
            ItemNota prodAtual = carrinho[i];
            System.out.printf("\nNome: %s  Preco: R$%s  Subtotal: R$%s\n", prodAtual.getNome(), prodAtual.getPrice(), prodAtual.getSubTotal());
        }
        System.out.println("Valor total: R$" + valorTotal);
        System.out.println("\n=============================================");
    }
}
