package loja.ui;
import java.math.BigDecimal;
import java.util.Scanner;


public class InputUtils {
    static Scanner e = new Scanner(System.in);

    public static String lerString(String texto){
        System.out.println(texto);
        return e.nextLine();      
    }

    public static int lerInt(String texto){
        System.out.println(texto);
        return e.nextInt();      
    }

    public static BigDecimal lerBigDecimal(String texto){
        System.out.println(texto);
        return new BigDecimal(e.next());
    }
}
