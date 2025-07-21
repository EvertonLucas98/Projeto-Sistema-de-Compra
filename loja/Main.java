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

        ProdutoFisico[] produtosFisicos = new ProdutoFisico[100];
        ProdutoDigital[] produtosDigitais = new ProdutoDigital[100];
        ProdutoPerecivel[] produtosPereciveis = new ProdutoPerecivel[100];
        Cliente[] clientes = new Cliente[100];
        int totProdFis=0, totProdDig=0, totProdPer=0, totalClientes=0, opcao, tipo;

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
                System.out.print("\n");
                int codigo = InputUtils.lerInt("Código: ");
                String nome = InputUtils.lerString("Nome ");
                BigDecimal preco = InputUtils.lerBigDecimal("Preço: ");
                int estoque = InputUtils.lerInt("Estoque: ");
                tipo = InputUtils.lerIntNumIntervalo("Tipo do Produto\n\t1. Digital\n\t2. Fisico\n\t3. Perecível\n\tTipo: ", 1, 3);

                // Dobra o tamanho do array se tiver cheio
                if(totProdFis == 100) {
                    // Cria um novo array com o dobro de tamanho do array cheio
                    ProdutoFisico[] arrayExtendido = new ProdutoFisico[(produtosFisicos.length)*2];
                    // Copia todos os elementos do array cheio para o array extendido
                    for(int i=0; i<produtosFisicos.length; i++)
                        arrayExtendido[i] = produtosFisicos[i];
                    // O array que estava cheio agora tem o dobro de tamanho
                    produtosFisicos = arrayExtendido;
                } else if(totProdDig == 100) {
                    // Cria um novo array com o dobro de tamanho do array cheio
                    ProdutoDigital[] arrayExtendido = new ProdutoDigital[(produtosDigitais.length)*2];
                    // Copia todos os elementos do array cheio para o array extendido
                    for(int i=0; i<produtosDigitais.length; i++)
                        arrayExtendido[i] = produtosDigitais[i];
                    // O array que estava cheio agora tem o dobro de tamanho
                    produtosDigitais = arrayExtendido;
                } else if(totProdPer == 100) {
                    // Cria um novo array com o dobro de tamanho do array cheio
                    ProdutoPerecivel[] arrayExtendido = new ProdutoPerecivel[(produtosPereciveis.length)*2];
                    // Copia todos os elementos do array cheio para o array extendido
                    for(int i=0; i<produtosPereciveis.length; i++)
                        arrayExtendido[i] = produtosPereciveis[i];
                    // O array que estava cheio agora tem o dobro de tamanho
                    produtosPereciveis = arrayExtendido;
                }

                // Cria um novo produto
                if(tipo==1){
                    String categoria=InputUtils.lerString("Categoria: ");
                    ProdutoDigital novoProduto = new ProdutoDigital(codigo, nome, preco, estoque, categoria);
                    //adiciona novo produto no array
                    produtosDigitais[totProdDig] = novoProduto;
                    // Variavel de controle para o tamanho do array
                    totProdDig++;
                } else if(tipo==2){
                    BigDecimal peso=InputUtils.lerBigDecimal("Peso: ");
                    ProdutoFisico novoProduto = new ProdutoFisico(codigo, nome, preco, estoque, peso);
                    //adiciona novo produto no array
                    produtosFisicos[totProdFis] = novoProduto;
                    // Variavel de controle para o tamanho do array
                    totProdFis++;
                } else {
                    BigDecimal peso=InputUtils.lerBigDecimal("Peso: ");
                    LocalDate data=InputUtils.lerData("Validade (dd/mm/yyyy): ");
                    ProdutoPerecivel novoProduto = new ProdutoPerecivel(codigo, nome, preco, estoque, peso, data);
                    //adiciona novo produto no array
                    produtosPereciveis[totProdPer] = novoProduto;
                    // Variavel de controle para o tamanho do array
                    totProdPer++;
                }
            } else if(opcao == 2) { // Alterar Produto
                System.out.println("Tipo do Produto");
                tipo = InputUtils.lerIntNumIntervalo("1. Digital, 2. Fisico ou 3. Perecível: ", 1, 3);
                if(tipo == 1) {
                    if (produtosDigitais[0] != null) {
                        System.out.print("\tProduto: ");
                        String nomeProduto = sc.nextLine();
                        Produto produto = buscarProduto(produtosDigitais, nomeProduto);
                        if(produto != null)
                            alterarProduto(produto);
                        else
                            System.out.println("\tProduto não encontrado!");
                    } else
                        System.out.println("\nNenhum produto cadastrado!");
                } else if(tipo == 2) {
                    if (produtosFisicos[0] != null) {
                        System.out.print("\tProduto: ");
                        String nomeProduto = sc.nextLine();
                        Produto produto = buscarProduto(produtosFisicos, nomeProduto);
                        if(produto != null)
                            alterarProduto(produto);
                        else
                            System.out.println("\tProduto não encontrado!");
                    } else
                        System.out.println("\nNenhum produto cadastrado!");
                } else {
                    if (produtosPereciveis[0] != null) {
                        System.out.print("\tProduto: ");
                        String nomeProduto = sc.nextLine();
                        Produto produto = buscarProduto(produtosPereciveis, nomeProduto);
                        if(produto != null)
                            alterarProduto(produto);
                        else
                            System.out.println("\tProduto não encontrado!");
                    } else
                        System.out.println("\nNenhum produto cadastrado!");
                }
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
                if (produtosDigitais[0] != null)
                    listarProdutos(produtosDigitais);
                if (produtosFisicos[0] != null)
                    listarProdutos(produtosFisicos);
                if (produtosPereciveis[0] != null)
                    listarProdutos(produtosPereciveis);
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
            if(produtos[i] != null)
                if(produtos[i].getNome().equals(nomeProduto))
                    return produtos[i];
        }
        return null;
    }

    private static void alterarProduto(Produto produto) {
        if(produto != null) {
            int opcaoProduto;
            
            do {
                System.out.println("\n\t1. Alterar Nome");
                System.out.println("\t2. Alterar Preço");
                System.out.println("\t3. Alterar Estoque");
                System.out.println("\t0. Sair");
                opcaoProduto = InputUtils.lerIntNumIntervalo("\n\tOpção: ", 0, 3);

                if(opcaoProduto == 0) {
                    System.out.println("\tSaindo das alterações...");
                } else if(opcaoProduto == 1) {
                    String novoNome = InputUtils.lerString("\tNovo Nome: ");
                    produto.setNome(novoNome);
                    System.out.println("\tNome alterado com sucesso!");
                } else if(opcaoProduto == 2) {
                    BigDecimal novoPreco = InputUtils.lerBigDecimal("\tNovo Preço: ");
                    produto.setPreco(novoPreco);
                    System.out.println("\tPreço alterado com sucesso!");
                } else if(opcaoProduto == 3) {
                    int novoEstoque = InputUtils.lerInt("\tNovo Estoque: ");
                    produto.setEstoque(novoEstoque);
                    System.out.println("\tEstoque alterado com sucesso!");
                } else // Opcao invalida
                    System.out.println("\nOpção Inválida!");
            } while(opcaoProduto != 0);
        } else
            System.out.println("Produto não encontrado!");
    }
}
