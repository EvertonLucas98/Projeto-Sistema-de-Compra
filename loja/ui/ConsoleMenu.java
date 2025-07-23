package loja.ui;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;
import loja.model.cliente.*;
import loja.model.nota.*;
import loja.model.produto.*;

public class ConsoleMenu {
    String titulo;
    String[] opcoes;
    int totalOpcoes;

    public ConsoleMenu(String titulo, int maxOpcoes) {
        this.titulo = titulo;
        this.opcoes = new String[maxOpcoes];
        this.totalOpcoes = 0;
    }

    public void adicionarOpcao(String opcao) {
        if (totalOpcoes < opcoes.length) {
            opcoes[totalOpcoes] = opcao;
            totalOpcoes++;
        } else {
            System.out.println("Erro: Número máximo de opções atingido!");
        }
    }

    public void mostrarMenu() {
        System.out.println("\n========== "+titulo+" ==========");
        for(int i=0; i<opcoes.length; i++) {
            System.out.println((i+1)+". "+opcoes[i]);
        }
        System.out.println("0. Sair");
        System.out.println("==========================");
    }
    
    public int obterOpcao(Scanner sc) {
        return InputUtils.lerIntNumIntervalo("Opcao: ", 0, totalOpcoes);
    }

    public static void executarMenu(){
        Scanner sc = new Scanner(System.in);

        Produto[] produtos = new Produto[100];
        Cliente[] clientes = new Cliente[100];
        Nota[] notas = new Nota[100];
        ItemNota[] itens = new ItemNota[100];
        int totProd=0;
        int totCliente=0, totalItens=0;
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
                if(totProd == produtos.length) {
                    // Cria um novo array com o dobro de tamanho do array cheio
                    Produto[] arrayExtendido = new Produto[(produtos.length)*2];
                    // Copia todos os elementos do array cheio para o array extendido
                    for(int i=0; i<produtos.length; i++)
                        arrayExtendido[i] = produtos[i];
                    // O array que estava cheio agora tem o dobro de tamanho
                    produtos = arrayExtendido;              
                }
                // Cria um novo produto
                if(tipo==1){
                    String categoria=InputUtils.lerString("Categoria: ");
                    ProdutoDigital novoProduto = new ProdutoDigital(codigo, nome, preco, estoque, categoria);
                    //adiciona novo produto no array
                    produtos[totProd] = (Produto)novoProduto;
                    // Variavel de controle para o tamanho do array
                    totProd++;
                } else if(tipo==2){
                    BigDecimal peso=InputUtils.lerBigDecimal("Peso: ");
                    ProdutoFisico novoProduto = new ProdutoFisico(codigo, nome, preco, estoque, peso);
                    //adiciona novo produto no array
                    produtos[totProd] = (Produto)novoProduto;
                    // Variavel de controle para o tamanho do array
                    totProd++;
                } else {
                    BigDecimal peso=InputUtils.lerBigDecimal("Peso: ");
                    LocalDate data=InputUtils.lerData("Validade (dd/mm/yyyy): ");
                    ProdutoPerecivel novoProduto = new ProdutoPerecivel(codigo, nome, preco, estoque, peso, data);
                    //adiciona novo produto no array
                    produtos[totProd] = (Produto)novoProduto;
                    // Variavel de controle para o tamanho do array
                    totProd++;
                }
                
            } else if(opcao == 2) { // Alterar Produto               
                if (produtos[0] != null) {
                    String nomeProduto = InputUtils.lerString("\tProduto: ");
                    Produto produto = buscarProduto(produtos, nomeProduto);
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
                if(totCliente == clientes.length) {
                    // Cria um novo array com o dobro de tamanho do array cheio
                    Cliente[] arrayExtendido = new Cliente[(clientes.length)*2];
                    // Copia todos os elementos do array cheio para o array extendido
                    for(int i=0; i<clientes.length; i++)
                        arrayExtendido[i] = clientes[i];
                    // O array que estava cheio agora tem o dobro de tamanho
                    clientes = arrayExtendido;
                }
                // Cria um novo cliente
                if(tipo==1){
                    String cpf = InputUtils.lerString("CPF: ");
                    LocalDate dataNascimento = InputUtils.lerData("Data de Nascimento(dd/mm/yyy): ");
                    PessoaFisica novoCliente = new PessoaFisica(id, nome, endereco, telefone, cpf, dataNascimento);
                    //adiciona novo cliente no array
                    clientes[totCliente] = novoCliente;
                    // Variavel de controle para o tamanho do array
                    totCliente++;
                } else {
                    String cnpj = InputUtils.lerString("CNPJ: ");
                    PessoaJuridica novoCliente = new PessoaJuridica(id, nome, endereco, telefone, cnpj);
                    //adiciona novo cliente no array
                    clientes[totCliente] = novoCliente;
                    // Variavel de controle para o tamanho do array
                    totCliente++;
                }
                
            } else if(opcao == 4) { // Alterar Cliente
                System.out.println("Selecione");
                tipo = InputUtils.lerIntNumIntervalo("1. Pessoa Fisica ou 2. Pessoa Jurídica", 1, 2);
                
                if (clientes[0] != null) {
                    String nomeCliente = InputUtils.lerString("\tNome: ");
                    Cliente cliente = buscarCliente(clientes, nomeCliente);
                    if(cliente != null)
                        alterarCliente(cliente);
                    else
                        System.out.println("\tCliente não encontrado!");
                    } else
                    System.out.println("\nNenhum cliente cadastrado!");
                
            } else if(opcao == 5) { // Criar Nota de Compra
                if (produtos[0] != null) {
                    listarClientes(clientes);                   
                    String nomeCliente = InputUtils.lerString("\tNome do Cliente: ");
                    Cliente cliente = buscarCliente(clientes, nomeCliente);
                    // Adicionar itens
                    if(cliente != null) {
                        String numero = InputUtils.lerString("Numero: ");
                        LocalDate data = InputUtils.lerData("Data (dd/mm/yyyy): ");
                        int opcaoCarrinho;
                        do {
                            listarProdutos(produtos);
                            String nomeProduto = InputUtils.lerString("\tNome do Produto: ");
                            int qtdProdutos = InputUtils.lerInt("\tQuantidade: ");
                            Produto produto = buscarProduto(produtos, nomeProduto);
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
                if (clientes[0] != null) {
                    listarClientes(clientes);
                    String nomeCliente = InputUtils.lerString("\tNome do Cliente: ");
                    Cliente cliente = buscarCliente(clientes, nomeCliente);
                    if (cliente.getItemNota()[0] != null) {
                        cliente.printarNota();
                    } else {
                        System.out.println("Nenhuma nota registrada!");
                    }
                } else
                    System.out.println("Nenhum cliente cadastrado!");
                
            } else if(opcao == 7) { // Listar Produtos
                System.out.print("\n");
                if (produtos[0] != null) {
                    listarProdutos(produtos);
                } else
                    System.out.println("Nenhum produto cadastrado!");
            } else if(opcao == 8) { // Listar Clientes
                System.out.print("\n");
                if (clientes[0] != null) {
                    listarClientes(clientes);
                } else
                    System.out.println("Nenhum cliente cadastrado!");
            } else // Opcao invalida
                System.out.println("\nOpção Inválida!");
        } while(opcao != 0);
        sc.close();
        }
 

    private static void listarProdutos(Produto[] produtos) {
        System.out.println("================ Lista de Produtos ================");
        for(int i=0; i<produtos.length; i++) {
            if(produtos[i] != null)
                produtos[i].printarDados();
        }
        System.out.println("===================================================");
    }
    
    private static void listarClientes(Cliente[] clientes) {
        System.out.println("================ Lista de Clientes ================");
        for(int i=0; i<clientes.length; i++) {
            if(clientes[i] != null)
                clientes[i].printarDados();
        }
        System.out.println("===================================================");
    }

    private static Produto buscarProduto(Produto[] produtos,String nomeProduto) {     
        for(int i=0; i<produtos.length; i++)
                if(produtos[i] != null)
                    if(produtos[i].getNome().equals(nomeProduto))
                        return produtos[i];
        
        return null;
    }

    private static Cliente buscarCliente(Cliente[] clientes, String nomeCliente) {
        
            for(int i=0; i<clientes.length; i++){
                if(clientes[i] != null)
                    if(clientes[i].getNome().equals(nomeCliente))
                        return clientes[i];
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
                if(produto instanceof ProdutoDigital) {System.out.println("\t4. Alterar Categoria");}
                if(produto instanceof ProdutoFisico) {System.out.println("\t4. Alterar Peso");}
                if(produto instanceof ProdutoPerecivel) {System.out.println("\t5. Alterar Validade");}
                System.out.println("\t0. Sair");
                opcaoProduto = InputUtils.lerIntNumIntervalo("\n\tOpção: ", 0, 5);

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
                } else if(opcaoProduto==4) {
                	if(produto instanceof ProdutoDigital) {
                		String novaCategoria = InputUtils.lerString("\tNova Categoria: ");
                		((ProdutoDigital) produto).setCategoria(novaCategoria);
                		System.out.println("\tCategoria alterada com sucesso!");
                	}
                	if(produto instanceof ProdutoFisico) {
                		BigDecimal novoPeso = InputUtils.lerBigDecimal("\tNovo Peso: ");
                		((ProdutoFisico) produto).setPeso(novoPeso);
                		System.out.println("\tPeso alterado com sucesso!");
                	}
                } else if(opcaoProduto==5 && produto instanceof ProdutoPerecivel) {
                	LocalDate novaData =  InputUtils.lerData("\tNova Data: ");
                	((ProdutoPerecivel)produto).setValidade(novaData);
                	System.out.println("\tValidade alterada com sucesso!");
                }else
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
