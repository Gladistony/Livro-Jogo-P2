import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class GUtil {
    public static void print(String txt){
        System.out.println(txt);
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            System.out.println("Erro no tempo");
        }
    }
    public static void instaprint(String txt){
        System.out.println(txt);
    }

    public static void printCentral(String txt) {
        int tam = 25 - txt.length() / 2;
        System.out.println("--------------------------------------------------");
        for (int i = 0; i < tam; i++){
            System.out.print(" ");
        }
        System.out.print(txt);System.out.println();
        System.out.println("--------------------------------------------------");
    }

    public static List<String> lerArquivo(File arq){
        List<String> retorno = new ArrayList<String>();
        try{
            Scanner scan = new Scanner(arq, "UTF-8");
            while (scan.hasNextLine()) {
                retorno.add(scan.nextLine());
            }
            scan.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        

        return retorno;
    }
}
