import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class App extends GUtil {
    public static void main(String[] args) throws Exception {
        //Criar Variaveis
        String caminho = "Capi/captilo";
        File arq;
        List<String> filedata;
        int modo;
        Capitulo tempcap;
        //Inicializar variaveis
        List<Capitulo> ListaCapitulos = new ArrayList<Capitulo>();
        //Loop de leitura de todos os capitulos
        for (int index = 0; index < 999999; index++) {
            arq = new File(caminho+ String.valueOf(index) + ".txt");
            if (arq.exists()){
                filedata = lerArquivo(arq);
                tempcap = new Capitulo();
                modo = 0;
                for (String str: filedata){
                    if (str.equals("#")) modo++;
                    if (modo == 0){
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
        print("Finalizado");
    }
}
