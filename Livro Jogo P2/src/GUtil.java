import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;


public abstract class GUtil {

    private static Personagem mainPJ;
    private static Map<String, Personagem> LNpcs = new HashMap<String, Personagem>();
    private static List<Capitulo> ListaCapitulos = new ArrayList<Capitulo>();
    private static Boolean Noprint = true;

    public static void startPrint(){
        Noprint = false;
    }

    public static Boolean jogoContinua(){
        return mainPJ.vivo();
    }
    public static void setProgresso(Capitulo cap){
        for (int i = 0; i < ListaCapitulos.size(); i++) {
            if (ListaCapitulos.get(i) == cap){
                mainPJ.setIDHistoria(i);
                break;
            }
        }
        //mainPJ.setIDHistoria(id);
    }
    public static Capitulo getCapitulos(int id) {
        return ListaCapitulos.get(id);
    }
    public static void criarPJPrincipal(String nome){
        mainPJ.criarJogaodor(nome);
    }
    public static void loadPJPrincipal(){
        mainPJ.loadData();
    }
    public static void loadData(String caminho){
        String caminhoreal = caminho+"/Npcs/npc";
        File arq;
        List<String> filedata;
        Personagem temppj;
        Capitulo tempcap;
        int modo;
        GUtil.mainPJ = new Personagem();
        for (int index = 0; index < 999999; index++) {
                arq = new File(caminhoreal+ String.valueOf(index) + ".txt");
            if (arq.exists()){
                filedata = GUtil.lerArquivo(arq);
                temppj = new Personagem();
                temppj.set_name(filedata.get(0));
                temppj.set_hp_str(filedata.get(1));
                temppj.set_drop(filedata.get(2));
                temppj.set_att(filedata.get(3), filedata.get(4));
                GUtil.LNpcs.put(filedata.get(0), temppj);
            } else {
                break;
            }
        }
        caminhoreal = caminho+"/Capi/capitulo";
        for (int index = 0; index < 999999; index++) {
            arq = new File(caminhoreal+ String.valueOf(index) + ".txt");
            if (arq.exists()){
                filedata = GUtil.lerArquivo(arq);
                tempcap = new Capitulo(GUtil.mainPJ);
                String imageString = "";
                modo = 0;
                for (String str: filedata){
                    if (str.equals("#")) modo++;
                    else if (modo == 0){
                        tempcap.addDialogo(str);
                    } else if (modo == 1) {
                        tempcap.addEscolha(str);
                    } else if (modo == 2) {
                        tempcap.set_NpcName(LNpcs.get(str));
                    } else if (modo == 3) {
                        imageString += str + "\n";
                    }
                }
                if (imageString.length() >= 1 ){
                    CapituloImagem tempcapimg = new CapituloImagem(GUtil.mainPJ);
                    tempcapimg.receberCapitulo(tempcap);
                    tempcapimg.set_imagem(imageString);
                    tempcap = tempcapimg;
                }
                GUtil.ListaCapitulos.add(tempcap);
            }
            else{
                break;
            }
        }
        for (Capitulo cap: GUtil.ListaCapitulos){
            cap.ativarEscolhas(ListaCapitulos);
        }
        //print("Carregamento de dados bem sucedido");
    }

    public static void print(String txt){
        if (Noprint) return;
        System.out.println(txt);
        var alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("Livro Jogo");
        alert.setContentText(txt);
        alert.getButtonTypes().addAll(ButtonType.OK);
        alert.show();
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
    public static int getIDHistoria() {
        return mainPJ.getIDHistoriaPJ();
    }
}
