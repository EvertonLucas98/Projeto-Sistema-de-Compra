package loja;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;
import loja.model.cliente.*;
import loja.model.produto.*;
import loja.ui.ConsoleMenu;
import loja.ui.InputUtils;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ProdutoFisico[] produtosFisicos = new ProdutoFisico[2];
        ProdutoDigital[] produtosDigitais = new ProdutoDigital[2];
        ProdutoPerecivel[] produtosPereciveis = new ProdutoPerecivel[2];
        PessoaFisica[] clienteFisico = new PessoaFisica[100];
        PessoaJuridica[] clienteJuridico = new PessoaJuridica[100];
        int totProdFis=0, totProdDig=0, totProdPer=0;
        int totClienteFis=0, totClienteJur=0;
        int opcao, tipo;

        do {
            // Menu
            ConsoleMenu menu = new ConsoleMenu("Menu", 8);
            menu.adicionarOpcao("Cadastrar Produto");
            menu.adicionarOpcao("Alterar Produto");
            menu.adicionarOpcao("Cadastrar Cliente");
            menu.adicionarOpcao("Alterar Cliente");
            menu.adicionarOpcao("Criar Nota de Compra");
            menu.adicionarOpcao("Listar Notas Emitidas");
            menu.adicionarOpcao("Listar Produtos");
            menu.adicionarOpcao("Listar Clientes");
            menu.mostrarMenu();
            opcao = InputUtils.lerIntNumIntervalo("\nOpcao: ", 0, 8);
            
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
                if(totProdFis == produtosFisicos.length) {
                    // Cria um novo array com o dobro de tamanho do array cheio
                    ProdutoFisico[] arrayExtendido = new ProdutoFisico[(produtosFisicos.length)*2];
                    // Copia todos os elementos do array cheio para o array extendido
                    for(int i=0; i<produtosFisicos.length; i++)
                        arrayExtendido[i] = produtosFisicos[i];
                    // O array que estava cheio agora tem o dobro de tamanho
                    produtosFisicos = arrayExtendido;
                } else if(totProdDig == produtosDigitais.length) {
                    // Cria um novo array com o dobro de tamanho do array cheio
                    ProdutoDigital[] arrayExtendido = new ProdutoDigital[(produtosDigitais.length)*2];
                    // Copia todos os elementos do array cheio para o array extendido
                    for(int i=0; i<produtosDigitais.length; i++)
                        arrayExtendido[i] = produtosDigitais[i];
                    // O array que estava cheio agora tem o dobro de tamanho
                    produtosDigitais = arrayExtendido;
                } else if(totProdPer == produtosPereciveis.length) {
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
                
                if (produtosFisicos[0] != null | produtosDigitais[0] != null | produtosPereciveis[0] != null) {
                    String nomeProduto = InputUtils.lerString("\tProduto: ");
                    Produto produto = buscarProduto(tipo, produtosFisicos, produtosDigitais, produtosPereciveis, nomeProduto);
                    if(produto != null)
                        alterarProduto(produto);
                    else
                        System.out.println("\tProduto não encontrado!");
                } else
                    System.out.println("\nNenhum produto cadastrado!");
            } else if(opcao == 3) { // Cadastrar Cliente
                String id = InputUtils.lerString("\nID: ");
                String nome = InputUtils.lerString("Nome: ");
                String endereco = InputUtils.lerString("Endereço: ");
                String telefone = InputUtils.lerString("Telefone: ");
                tipo = InputUtils.lerIntNumIntervalo("Tipo de Cliente\n\t1. Pessoa Fisica\n\t2. Pessoa Juridica", 1, 2);

                // Dobra o tamanho do array se tiver cheio
                if(totClienteFis == clienteFisico.length) {
                    // Cria um novo array com o dobro de tamanho do array cheio
                    PessoaFisica[] arrayExtendido = new PessoaFisica[(clienteFisico.length)*2];
                    // Copia todos os elementos do array cheio para o array extendido
                    for(int i=0; i<clienteFisico.length; i++)
                        arrayExtendido[i] = clienteFisico[i];
                    // O array que estava cheio agora tem o dobro de tamanho
                    clienteFisico = arrayExtendido;
                } else if(totClienteJur == clienteJuridico.length) {
                    // Cria um novo array com o dobro de tamanho do array cheio
                    PessoaJuridica[] arrayExtendido = new PessoaJuridica[(clienteJuridico.length)*2];
                    // Copia todos os elementos do array cheio para o array extendido
                    for(int i=0; i<clienteJuridico.length; i++)
                        arrayExtendido[i] = clienteJuridico[i];
                    // O array que estava cheio agora tem o dobro de tamanho
                    clienteJuridico = arrayExtendido;
                }
            } else if(opcao == 4) { // Alterar Cliente (NÃO IMPLEMENTADO)
                
            } else if(opcao == 5) { // Criar Nota de Compra
                // Listando apenas os nomes dos clientes
                if (totClienteFis == 0 && totClienteJur == 0){
                    System.out.println("Nenhum cliente foi cadastrado.");
                } else {
                    if (totClienteFis > 0){
                        System.out.println("================ Lista de Clientes ================");
                        for (int i = 1; i <PessoaFisica.length+1; i++){
                        Cliente clienteAtendido = PessoaFisica[i-1];
                        System.out.println(i+". "+clienteAtendido.getNome());
                    }
                    if (totClienteJur > 0){
                        for (int i = 1; i < PessoaJuridica.length+1; i++){
                            Cliente clienteAtendido = PessoaJuridica[i-1];
                            int indice = PessoaFisica.length + i;
                            System.out.println(indice+". "+clienteAtendido.getNome());
                        }
                    }
                }
                int numCliente = InputUtils.lerInt("Digite o numero referente ao cliente: ");
                // Ajustando o valor do indice para acessar o cliente certo na lista certa
                if (numCliente > PessoaFisica.length){
                    numCliente -= PessoaFisica.length;
                    Cliente clienteAtendido = PessoaJuridica[numCliente-1];
                } else{
                    Cliente clienteAtendido = PessoaFisica[numCliente-1];
                }

                Nota novaNota = new Nota(clienteAtendido.getNome(), clienteAtendido.getId());
                novaNota.registrarCompras();
                novaNota.printaNota();
            } else if(opcao == 6) { // Listar Notas Emitidas (NÃO IMPLEMENTADO)
                
            } else if(opcao == 7) { // Listar Produtos
                System.out.print("\n");
                if (produtosDigitais[0] != null || produtosFisicos[0] != null || produtosPereciveis[0] != null) {
                    System.out.println("================ Lista de Produtos ================");
                    listarProdutos(produtosDigitais);
                    listarProdutos(produtosFisicos);
                    listarProdutos(produtosPereciveis);
                }
            } else if(opcao == 8) { // Listar Clientes
                /*if (clienteFisico[0] != null)
                    listarClientes(produtosPereciveis);
                if (clienteJuridico[0] != null)
                    listarClientes(produtosPereciveis);*/
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

    private static Produto buscarProduto(int tipo, ProdutoFisico[] produtosFisicos, ProdutoDigital[] produtosDigitais, ProdutoPerecivel[] produtosPereciveis, String nomeProduto) {
        if(tipo == 1) {
            for(int i=0; i<produtosDigitais.length; i++)
                if(produtosDigitais[i] != null)
                    if(produtosDigitais[i].getNome().equals(nomeProduto))
                        return produtosDigitais[i];
        } else if(tipo == 2) {
            for(int i=0; i<produtosFisicos.length; i++)
                if(produtosFisicos[i] != null)
                    if(produtosFisicos[i].getNome().equals(nomeProduto))
                        return produtosFisicos[i];
        } else {
            for(int i=0; i<produtosPereciveis.length; i++)
                if(produtosPereciveis[i] != null)
                    if(produtosPereciveis[i].getNome().equals(nomeProduto))
                        return produtosPereciveis[i];
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
