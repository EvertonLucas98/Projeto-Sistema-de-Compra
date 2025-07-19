package loja;

import java.math.BigDecimal;
import java.util.Scanner;
import loja.model.produto.Produto;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Produto[] produtos = new Produto[100];
        int totalProdutos=0, opcao;

        do {
            // Menu
            System.out.println("\n1. Cadastrar Produto");
            System.out.println("2. Alterar Produto");
            System.out.println("3. Cadastrar Cliente");
            System.out.println("4. Alterar Cliente");
            System.out.println("5. Criar Nota de Compra");
            System.out.println("6. Listar Notas Emitidas");
            System.out.println("7. Listar Produtos");
            System.out.println("8. Listar Clientes");
            System.out.println("0. Sair\n");

            System.out.print("Opcao: ");
            opcao = sc.nextInt();
            
            // Encerrar programa
            if(opcao == 0) {
                System.out.println("Saindo...");
            }
            
            // Cadastrar Produto
            if(opcao == 1) {
                System.out.print("Código: ");
                int codigo = sc.nextInt();
                sc.nextLine(); // Limpa o buffer

                System.out.print("Nome: ");
                String nome = sc.nextLine();

                System.out.print("Preço: ");
                BigDecimal preco = new BigDecimal(sc.nextDouble());

                System.out.print("Estoque: ");
                int estoque = sc.nextInt();

                // Dobra o tamanho do array se tiver cheio
                if(totalProdutos == 100) {
                    // Cria um novo array com o dobro de tamanho do array cheio
                    Produto[] produtosExtendido = new Produto[(produtos.length)*2];
                    // Copia todos os elementos do array cheio para o array extendido
                    for(int i=0; i<produtos.length; i++)
                        produtosExtendido[i] = produtos[i];
                    // O array que estava cheio agora tem o dobro de tamanho
                    produtos = produtosExtendido;
                }

                // Cria um novo produto
                Produto novoProduto = new Produto(codigo, nome, preco, estoque);
                // Adiciona o novo produto no array
                produtos[totalProdutos] = novoProduto;
                // Variavel de controle para o tamanho do array
                totalProdutos++;
            } else if(opcao == 2) {
                // Alterar Produto
            } else if(opcao == 3) {
                // Cadastrar Cliente
            } else if(opcao == 4) {
                // Alterar Cliente
            } else if(opcao == 5) {
                // Criar Nota de Compra
            } else if(opcao == 6) {
                // Listar Notas Emitidas
            // Listar Produtos
            } else if(opcao == 7) {
                listarProdutos(produtos);
            } else if(opcao == 8) {
                // Listar Clientes
            } else {
                System.out.println("Opção Inválida!\n");
            }
        } while(opcao != 0);
        sc.close();
    }

    private static void listarProdutos(Produto[] produtos) {
        for(int i=0; i<produtos.length; i++) {
            if(produtos[i] != null)
                produtos[i].printarDados();
        }
    }
}
