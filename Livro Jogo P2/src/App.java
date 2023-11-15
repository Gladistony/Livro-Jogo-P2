import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class App extends GUtil {
    public static void main(String[] args) throws Exception {
        //Criar Variaveis
        String caminho;
        File arq;
        List<String> filedata;
        int modo;
        Capitulo tempcap;
        Personagem temppj;
        Scanner scan = new Scanner(System.in);
        Personagem MainPj = new Personagem();
        Map<String, Personagem> LNpcs = new HashMap<String, Personagem>();
        String[] lista = {"Sim", "Nao"};
        int resposta;
        //Inicializar variaveis
        List<Capitulo> ListaCapitulos = new ArrayList<Capitulo>();
        // Ler os personagens 
        caminho = "Rsc/Npcs/npc";
        for (int index = 0; index < 999999; index++) {
                arq = new File(caminho+ String.valueOf(index) + ".txt");
            if (arq.exists()){
                filedata = lerArquivo(arq);
                temppj = new Personagem();
                temppj.set_name(filedata.get(0));
                temppj.set_hp_str(filedata.get(1));
                temppj.set_drop(filedata.get(2));
                temppj.set_att(filedata.get(3), filedata.get(4));
                LNpcs.put(filedata.get(0), temppj);
            } else {
                break;
            }
        }
        //print("Numero de NPC carregados: "+LNpcs.size());
        //Loop de leitura de todos os capitulos
        caminho = "Rsc/Capi/capitulo";
        for (int index = 0; index < 999999; index++) {
            arq = new File(caminho+ String.valueOf(index) + ".txt");
            if (arq.exists()){
                filedata = lerArquivo(arq);
                tempcap = new Capitulo(scan, MainPj);
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
                    CapituloImagem tempcapimg = new CapituloImagem(scan, MainPj);
                    tempcapimg.receberCapitulo(tempcap);
                    tempcapimg.set_imagem(imageString);
                    tempcap = tempcapimg;
                }
                ListaCapitulos.add(tempcap);
            }
            else{
                break;
            }
        }
        // Criar lista ligada de escolha em cada capitulo
        for (Capitulo cap: ListaCapitulos){
            cap.ativarEscolhas(ListaCapitulos);
        }
        //Verifiar se existe jogo salvo
        arq = new File("dadosSalvo.dat");
        if (arq.exists()){
            resposta = escolhas(scan,"Um arquivo salvo foi detectado, deseja carregar os dados do mesmo?", lista);
            if (resposta == 1) {
                MainPj.loadData();
                ListaCapitulos.get(MainPj.getIDHistoria()).executar();
            } else {
                //Iniciar a execulção do programa
                MainPj.criarJogaodor(scan);
                ListaCapitulos.get(0).executar();
            }
        } else {
            //Iniciar a execulção do programa
            MainPj.criarJogaodor(scan);
            ListaCapitulos.get(0).executar();
        }
        //Loop de retorna ao jogo
        //Finalização/Opção de reiniciar o jogo
        print("Obrigado por jogar");
        resposta = escolhas(scan,"Deseja começar de novo?", lista);
        if (resposta == 1) main(args);
        scan.close();
    }
}
