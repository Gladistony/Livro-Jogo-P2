import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Capitulo extends GUtil {
    private List<String> Dialogo;
    private List<Escolha> Escolha;
    private String nameNPC;
    private Scanner scan;
    private Personagem pjPrincipal;

    private void mostrar(){
        String temp;
        for (String s: this.Dialogo){
            temp = String.copyValueOf(s.toCharArray());
            temp = temp.replace("NPC", nameNPC);
            temp = temp.replace("PLAYER", pjPrincipal.get_name());
            print(temp);
        }
        for (Escolha e: this.Escolha){
            if (e.verificar_escolha_possivel()) print("> "+e.get_texto());
        }

    }
    public String get_NpcName(){
        return this.nameNPC;
    }
    public void set_NpcName(String str){
        this.nameNPC = str;
    }
    public void addEscolha(String str){
        Escolha.add(new Escolha(str));
    }
    public void addDialogo(String dia){
        this.Dialogo.add(dia);
    }
    public Capitulo(Scanner s, Personagem pj){
        this.Dialogo = new ArrayList<String>();
        this.Escolha = new ArrayList<Escolha>();
        this.scan = s;
        this.pjPrincipal = pj;
    }
    public void executar(){
        this.mostrar();
    }
}
