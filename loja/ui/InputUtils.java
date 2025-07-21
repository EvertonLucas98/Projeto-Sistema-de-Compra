package loja.ui;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class InputUtils {
    private static Scanner e = new Scanner(System.in);

    public static String lerString(String texto){
        System.out.print(texto);
        return e.nextLine();
    }

    public static int lerInt(String texto){
        System.out.print(texto);
        int num = e.nextInt();
        e.nextLine(); // Limpa o buffer
        return num;
    }
    
    public static int lerIntNumIntervalo(String texto, int min, int max){
        while (true) {
            System.out.print(texto);
            int input = e.nextInt();
            e.nextLine(); // Limpa o buffer
            if (input >= min && input <= max) {
                return input;
            } else {
                System.out.println("\tErro: Digite um número entre " + min + " e " + max + ".");
            }
        }
    }
    
    public static BigDecimal lerBigDecimal(String texto){
        System.out.print(texto);
        return new BigDecimal(e.nextLine());
    }

    public static LocalDate lerData(String texto){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.print(texto);
        return LocalDate.parse(e.nextLine(), formato);
    }
}
