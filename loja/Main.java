package loja;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;
import loja.model.cliente.*;
import loja.model.produto.*;
import loja.ui.InputUtils;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Produto[] produtos = new Produto[100];
        Cliente[] clientes = new Cliente[100];
        int totalProdutos=0, totalClientes=0, opcao;

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
            sc.nextLine();
            
            if(opcao == 0) { // Encerrar programa
                System.out.println("\nSaindo...\n");
            } else if(opcao == 1) { // Cadastrar Produto
                System.out.print("\nCódigo: ");
                int codigo = sc.nextInt();
                sc.nextLine(); // Limpa o buffer

                System.out.print("Nome: ");
                String nome = sc.nextLine();

                System.out.print("Preço: ");
                BigDecimal preco = new BigDecimal(sc.nextLine());

                System.out.print("Estoque: ");
                int estoque = sc.nextInt();

                System.out.print("Tipo(1.Digital - 2.Fisico - 3.Perecível): ");
                int tipo = sc.nextInt();

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
                if(tipo==1){
                    String categoria=InputUtils.lerString("Categoria:");
                    Produto novoProduto = new ProdutoDigital(codigo, nome, preco, estoque, categoria);
                    //adiciona novo produto no array
                    produtos[totalProdutos] = novoProduto;
                    // Variavel de controle para o tamanho do array
                    totalProdutos++;
                }
                if(tipo==2){
                    BigDecimal peso=InputUtils.lerBigDecimal("Peso:");
                    Produto novoProduto = new ProdutoFisico(codigo, nome, preco, estoque, peso);
                    //adiciona novo produto no array
                    produtos[totalProdutos] = novoProduto;
                    // Variavel de controle para o tamanho do array
                    totalProdutos++;
                }
                if(tipo==3){
                    BigDecimal peso=InputUtils.lerBigDecimal("Peso:");
                    LocalDate data=InputUtils.lerData("Validade:");
                    Produto novoProduto = new ProdutoPerecivel(codigo, nome, preco, estoque, peso, data);
                    //adiciona novo produto no array
                    produtos[totalProdutos] = novoProduto;
                    // Variavel de controle para o tamanho do array
                    totalProdutos++;
                }
              
            } else if(opcao == 2) { // Alterar Produto
                if (produtos[0] != null) {
                    System.out.print("\tProduto: ");
                    String nomeProduto = sc.nextLine();
                    Produto produto = buscarProduto(produtos, nomeProduto);
                    alterarProduto(produto);
                } else
                    System.out.println("\nNenhum produto cadastrado!");
            } else if(opcao == 3) { // Cadastrar Cliente
                System.out.print("\nCPF: ");
                sc.nextLine();
                String cpf = sc.nextLine();
                
                System.out.print("Nome: ");
                String nome = sc.nextLine();
                
                System.out.print("Endereco: ");
                String endereco = sc.nextLine();
                
                System.out.print("Telefone: ");
                String fone = sc.nextLine();

                // Dobra o tamanho do array se tiver cheio
                if(totalClientes == 100) {
                    // Cria um novo array com o dobro de tamanho do array cheio
                    Cliente[] clientesExtendido = new Cliente[(clientes.length)*2];
                    // Copia todos os elementos do array cheio para o array extendido
                    for(int i=0; i<clientes.length; i++)
                        clientesExtendido[i] = clientes[i];
                    // O array que estava cheio agora tem o dobro de tamanho
                    clientes = clientesExtendido;
                }

                // Cria um novo cliente
                Cliente novoCliente = new Cliente(cpf, nome, endereco, fone);
                // Adiciona o novo cliente no array
                clientes[totalClientes] = novoCliente;
                // Variavel de controle para o tamanho do array
                totalClientes++;
            } else if(opcao == 4) { // Alterar Cliente (NÃO IMPLEMENTADO)
                
            } else if(opcao == 5) { // Criar Nota de Compra (NÃO IMPLEMENTADO)
                
            } else if(opcao == 6) { // Listar Notas Emitidas (NÃO IMPLEMENTADO)
                
            } else if(opcao == 7) { // Listar Produtos
                System.out.print("\n");
                listarProdutos(produtos);
            } else if(opcao == 8) { // Listar Clientes
                System.out.print("\n");
                listarClientes(clientes);
            } else // Opcao invalida
                System.out.println("\nOpção Inválida!");
        } while(opcao != 0);
        sc.close();
    }

    private static void listarProdutos(Produto[] produtos) {
        for(int i=0; i<produtos.length; i++) {
            if(produtos[i] != null)
                produtos[i].printarDados();
        }
    }
    
    private static void listarClientes(Cliente[] clientes) {
        for(int i=0; i<clientes.length; i++) {
            if(clientes[i] != null)
                clientes[i].printarDados();
        }
    }

    private static Produto buscarProduto(Produto[] produtos, String nomeProduto) {
        for(int i=0; i<produtos.length; i++) {
            if(produtos[i].getNome().equals(nomeProduto))
                return produtos[i];
        }
        return null;
    }

    private static void alterarProduto(Produto produto) {
        if(produto != null) {
            Scanner sc = new Scanner(System.in);
            int opcaoProduto;
            
            do {
                System.out.println("\n\t1. Alterar Nome");
                System.out.println("\t2. Alterar Preço");
                System.out.println("\t3. Alterar Estoque");
                System.out.println("\t0. Sair");
                System.out.print("\n\tOpção: ");
                opcaoProduto = sc.nextInt();
                sc.nextLine();

                if(opcaoProduto == 0) {
                    System.out.println("\tSaindo das alterações...");
                } else if(opcaoProduto == 1) {
                    System.out.print("\tNovo Nome: ");
                    String novoNome = sc.nextLine();
                    produto.setNome(novoNome);
                    System.out.println("\tNome alterado com sucesso!");
                } else if(opcaoProduto == 2) {
                    System.out.print("\tNovo Preço: ");
                    BigDecimal novoPreco = new BigDecimal(sc.nextLine());
                    produto.setPreco(novoPreco);
                    System.out.println("\tPreço alterado com sucesso!");
                } else if(opcaoProduto == 3) {
                    System.out.print("\tNovo Estoque: ");
                    int novoEstoque = sc.nextInt();
                    produto.setEstoque(novoEstoque);
                    System.out.println("\tEstoque alterado com sucesso!");
                } else // Opcao invalida
                    System.out.println("\nOpção Inválida!");
            } while(opcaoProduto != 0);
        } else
            System.out.println("Produto não encontrado!");
    }
}
