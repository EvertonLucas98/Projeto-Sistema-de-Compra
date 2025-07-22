package loja;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;
import loja.model.cliente.*;
import loja.model.nota.*;
import loja.model.produto.*;
import loja.ui.ConsoleMenu;
import loja.ui.InputUtils;

public class Main {
    public
     static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ProdutoFisico[] produtosFisicos = new ProdutoFisico[2];
        ProdutoDigital[] produtosDigitais = new ProdutoDigital[2];
        ProdutoPerecivel[] produtosPereciveis = new ProdutoPerecivel[2];
        PessoaFisica[] clientesFisicos = new PessoaFisica[100];
        PessoaJuridica[] clientesJuridicos = new PessoaJuridica[100];
        Nota[] notas = new Nota[100];
        ItemNota[] itens = new ItemNota[100];
        int totProdFis=0, totProdDig=0, totProdPer=0;
        int totClienteFis=0, totClienteJur=0, totalItens=0;
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
                String nome = InputUtils.lerString("Nome: ");
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
                tipo = InputUtils.lerIntNumIntervalo("Tipo do Produto\n\t1. Digital\n\t2. Fisico\n\t3. Perecível\n\tTipo: ", 1, 3);
                
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
                tipo = InputUtils.lerIntNumIntervalo("Tipo de Cliente\n\t1. Pessoa Fisica\n\t2. Pessoa Juridica\n\tTipo: ", 1, 2);

                // Dobra o tamanho do array se tiver cheio
                if(totClienteFis == clientesFisicos.length) {
                    // Cria um novo array com o dobro de tamanho do array cheio
                    PessoaFisica[] arrayExtendido = new PessoaFisica[(clientesFisicos.length)*2];
                    // Copia todos os elementos do array cheio para o array extendido
                    for(int i=0; i<clientesFisicos.length; i++)
                        arrayExtendido[i] = clientesFisicos[i];
                    // O array que estava cheio agora tem o dobro de tamanho
                    clientesFisicos = arrayExtendido;
                } else if(totClienteJur == clientesJuridicos.length) {
                    // Cria um novo array com o dobro de tamanho do array cheio
                    PessoaJuridica[] arrayExtendido = new PessoaJuridica[(clientesJuridicos.length)*2];
                    // Copia todos os elementos do array cheio para o array extendido
                    for(int i=0; i<clientesJuridicos.length; i++)
                        arrayExtendido[i] = clientesJuridicos[i];
                    // O array que estava cheio agora tem o dobro de tamanho
                    clientesJuridicos = arrayExtendido;
                }

                // Cria um novo cliente
                if(tipo==1){
                    String cpf = InputUtils.lerString("CPF: ");
                    LocalDate dataNascimento = InputUtils.lerData("Data de Nascimento(dd/mm/yyy): ");
                    PessoaFisica novoCliente = new PessoaFisica(id, nome, endereco, telefone, cpf, dataNascimento);
                    //adiciona novo cliente no array
                    clientesFisicos[totClienteFis] = novoCliente;
                    // Variavel de controle para o tamanho do array
                    totClienteFis++;
                } else {
                    String cnpj = InputUtils.lerString("CNPJ: ");
                    PessoaJuridica novoCliente = new PessoaJuridica(id, nome, endereco, telefone, cnpj);
                    //adiciona novo cliente no array
                    clientesJuridicos[totClienteJur] = novoCliente;
                    // Variavel de controle para o tamanho do array
                    totClienteJur++;
                }
            } else if(opcao == 4) { // Alterar Cliente
                System.out.println("Selecione");
                tipo = InputUtils.lerIntNumIntervalo("1. Pessoa Fisica ou 2. Pessoa Jurídica", 1, 2);
                
                if (clientesFisicos[0] != null | clientesJuridicos[0] != null) {
                    String nomeCliente = InputUtils.lerString("\tNome: ");
                    Cliente cliente = buscarCliente(tipo, clientesFisicos, clientesJuridicos, nomeCliente);
                    if(cliente != null)
                        alterarCliente(cliente);
                    else
                        System.out.println("\tCliente não encontrado!");
                    } else
                    System.out.println("\nNenhum cliente cadastrado!");
                
            } else if(opcao == 5) { // Criar Nota de Compra
                if (produtosDigitais[0] != null || produtosFisicos[0] != null || produtosPereciveis[0] != null) {
                    listarClientes(clientesFisicos, clientesJuridicos);
                    int tipoCliente = InputUtils.lerIntNumIntervalo("Tipo de Cliente\n\t1. Pessoa Fisica\n\t2. Pessoa Juridica\n\tTipo: ", 1, 2);
                    String nomeCliente = InputUtils.lerString("\tNome do Cliente: ");
                    Cliente cliente = buscarCliente(tipoCliente, clientesFisicos, clientesJuridicos, nomeCliente);
                    // Adicionar itens
                    if(cliente != null) {
                        String numero = InputUtils.lerString("Numero: ");
                        LocalDate data = InputUtils.lerData("Data (dd/mm/yyyy): ");
                        int opcaoCarrinho;
                        do {
                            listarProdutos(produtosDigitais, produtosFisicos, produtosPereciveis);
                            int tipoProduto = InputUtils.lerIntNumIntervalo("Tipo do Produto\n\t1. Digital\n\t2. Fisico\n\t3. Perecível\n\tTipo: ", 1, 3);
                            String nomeProduto = InputUtils.lerString("\tNome do Produto: ");
                            int qtdProdutos = InputUtils.lerInt("\tQuantidade: ");
                            Produto produto = buscarProduto(tipoProduto, produtosFisicos, produtosDigitais, produtosPereciveis, nomeProduto);
                            Nota novaNota = new Nota(numero, data, cliente);
                            if(produto != null && produto.getEstoque() >= qtdProdutos) {
                                produto.setEstoque(produto.getEstoque()-qtdProdutos);
                                ItemNota novoItemNota = new ItemNota(produto, qtdProdutos, produto.getPreco());
                                if (totalItens == itens.length) {
                                    ItemNota[] arrayExtendido = new ItemNota[(itens.length)*2];
                                    for(int i=0; i<itens.length; i++)
                                        arrayExtendido[i] = itens[i];
                                    itens = arrayExtendido;
                                }
                                itens[totalItens] = novoItemNota;
                                totalItens++;
                                System.out.println("\tSubtotal: "+novoItemNota.getSubtotal());
                                BigDecimal total = BigDecimal.ZERO;
                                for (int i = 0; i < totalItens; i++) {
                                    total = total.add(itens[i].getSubtotal());
                                }
                                System.out.println("\tTotal: "+total);
                            } else {
                                System.out.println("\tNão foi possível realizar a operação!");
                            }
                            cliente.setTotalItens(1);
                            opcaoCarrinho = InputUtils.lerIntNumIntervalo("Carrinho\n\t1. Adicionar Produto\n\t0. Confirmar\n\tOpcao: ", 0, 1);
                            if (opcaoCarrinho == 0) {
                                cliente.setItemNota(itens);
                                cliente.setNota(novaNota);
                            }
                        } while (opcaoCarrinho != 0);
                    } else {
                        System.out.println("Cliente não encontrado!");
                    }
                } else
                    System.out.println("\nNenhum produto cadastrado!");
            } else if(opcao == 6) { // Listar Notas Emitidas
                if (clientesFisicos[0] != null || clientesJuridicos[0] != null) {
                    listarClientes(clientesFisicos, clientesJuridicos);
                    tipo = InputUtils.lerIntNumIntervalo("Tipo de Cliente\n\t1. Pessoa Fisica\n\t2. Pessoa Juridica\n\tTipo: ", 1, 2);
                    String nomeCliente = InputUtils.lerString("\tNome do Cliente: ");
                    Cliente cliente = buscarCliente(tipo, clientesFisicos, clientesJuridicos, nomeCliente);
                    if (cliente.getItemNota()[0] != null) {
                        cliente.printarNota();
                    } else {
                        System.out.println("Nenhuma nota registrada!");
                    }
                } else
                    System.out.println("Nenhum cliente cadastrado!");
                
            } else if(opcao == 7) { // Listar Produtos
                System.out.print("\n");
                if (produtosDigitais[0] != null || produtosFisicos[0] != null || produtosPereciveis[0] != null) {
                    listarProdutos(produtosDigitais, produtosFisicos, produtosPereciveis);
                } else
                    System.out.println("Nenhum produto cadastrado!");
            } else if(opcao == 8) { // Listar Clientes
                System.out.print("\n");
                if (clientesFisicos[0] != null || clientesJuridicos[0] != null) {
                    listarClientes(clientesFisicos, clientesJuridicos);
                } else
                    System.out.println("Nenhum cliente cadastrado!");
            } else // Opcao invalida
                System.out.println("\nOpção Inválida!");
        } while(opcao != 0);
        sc.close();
    }

    private static void listarProdutos(Produto[] produtosDigitais, Produto[] produtosFisicos, Produto[] produtosPereciveis) {
        System.out.println("================ Lista de Produtos ================");
        for(int i=0; i<produtosDigitais.length; i++) {
            if(produtosDigitais[i] != null)
                produtosDigitais[i].printarDados();
        }
        for(int i=0; i<produtosFisicos.length; i++) {
            if(produtosFisicos[i] != null)
                produtosFisicos[i].printarDados();
        }
        for(int i=0; i<produtosPereciveis.length; i++) {
            if(produtosPereciveis[i] != null)
                produtosPereciveis[i].printarDados();
        }
        System.out.println("===================================================");
    }
    
    private static void listarClientes(Cliente[] clientesFisicos, Cliente[] clientesJuridicos) {
        System.out.println("================ Lista de Clientes ================");
        for(int i=0; i<clientesFisicos.length; i++) {
            if(clientesFisicos[i] != null)
                clientesFisicos[i].printarDados();
        }
        for(int i=0; i<clientesJuridicos.length; i++) {
            if(clientesJuridicos[i] != null)
                clientesJuridicos[i].printarDados();
        }
        System.out.println("===================================================");
    }

    private static Produto buscarProduto(int tipo, Produto[] produtosFisicos, Produto[] produtosDigitais, Produto[] produtosPereciveis, String nomeProduto) {
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

    private static Cliente buscarCliente(int tipo, Cliente[] clientesFisicos, Cliente[] clientesJuridicos, String nomeCliente) {
        if(tipo == 1) {
            for(int i=0; i<clientesFisicos.length; i++)
                if(clientesFisicos[i] != null)
                    if(clientesFisicos[i].getNome().equals(nomeCliente))
                        return clientesFisicos[i];
        } else {
            for(int i=0; i<clientesJuridicos.length; i++)
                if(clientesJuridicos[i] != null)
                    if(clientesJuridicos[i].getNome().equals(nomeCliente))
                        return clientesJuridicos[i];
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
    private static void alterarCliente(Cliente cliente) {
        if(cliente != null) {
            int opcaoCliente;
            
            do {
                System.out.println("\n\t1. Alterar Nome");
                System.out.println("\t2. Alterar Endereço");
                System.out.println("\t3. Alterar Telefone");
                System.out.println("\t0. Sair");
                opcaoCliente = InputUtils.lerIntNumIntervalo("\n\tOpção: ", 0, 3);

                if(opcaoCliente == 0) {
                    System.out.println("\tSaindo das alterações...");
                } else if(opcaoCliente == 1) {
                    String novoNome = InputUtils.lerString("\tNovo Nome: ");
                    cliente.setNome(novoNome);
                    System.out.println("\tNome alterado com sucesso!");
                } else if(opcaoCliente == 2) {
                    String novoEndereco = InputUtils.lerString("\tNovo Endereco: ");
                    cliente.setEndereco(novoEndereco);
                    System.out.println("\tEndereco alterado com sucesso!");
                } else if(opcaoCliente == 3) {
                    String novoTelefone = InputUtils.lerString("\tNovo Telefone: ");
                    cliente.setTelefone(novoTelefone);
                    System.out.println("\tTelefone alterado com sucesso!");
                } else // Opcao invalida
                    System.out.println("\nOpção Inválida!");
            } while(opcaoCliente != 0);
        } else
            System.out.println("Cliente não encontrado!");
    }
}
