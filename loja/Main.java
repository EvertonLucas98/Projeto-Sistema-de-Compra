package loja;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcao;
        do {
            System.out.println("1. Cadastrar Produto");
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
            
            if(opcao == 0) {
                System.out.println("Saindo...");
            } else if(opcao == 1) {
                // Cadastrar Produto
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
            } else if(opcao == 7) {
                // Listar Produtos
            } else if(opcao == 8) {
                // Listar Clientes
            } else {
                System.out.println("Opção Inválida!\n");
            }
        } while(opcao != 0);
        sc.close();
    }
}
