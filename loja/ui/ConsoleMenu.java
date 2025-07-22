package loja.ui;

import java.util.Scanner;

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
}
