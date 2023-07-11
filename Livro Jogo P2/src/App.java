import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App extends GUtil {
    public static void main(String[] args) throws Exception {
        //Criar Variaveis
        String caminho = "Capi/capitulo";
        File arq;
        List<String> filedata;
        int modo;
        Capitulo tempcap;
        Scanner scan = new Scanner(System.in);
        Personagem MainPj = new Personagem();
        //Inicializar variaveis
        List<Capitulo> ListaCapitulos = new ArrayList<Capitulo>();
        //Loop de leitura de todos os capitulos
        for (int index = 0; index < 999999; index++) {
            arq = new File(caminho+ String.valueOf(index) + ".txt");
            if (arq.exists()){
                filedata = lerArquivo(arq);
                tempcap = new Capitulo(scan, MainPj);
                modo = 0;
                for (String str: filedata){
                    if (str.equals("#")) modo++;
                    else if (modo == 0){
                        tempcap.addDialogo(str);
                    } else if (modo == 1) {
                        tempcap.addEscolha(str);
                    } else {
                        tempcap.set_NpcName(str);
                    }
                }
                ListaCapitulos.add(tempcap);
            }
            else{
                break;
            }
        }
        ListaCapitulos.get(0).mostrar();
    }
}
