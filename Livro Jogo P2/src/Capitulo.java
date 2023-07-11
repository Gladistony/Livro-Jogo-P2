import java.util.ArrayList;
import java.util.List;

public class Capitulo extends GUtil {
    private List<String> Dialogo;
    private List<String> Escolha;
    private String nameNPC;


    public void set_NpcName(String str){
        this.nameNPC = str;
    }
    public void addEscolha(String str){
        this.Escolha.add(str);
    }
    public void addDialogo(String dia){
        this.Dialogo.add(dia);
    }
    public Capitulo(){
        this.Dialogo = new ArrayList<String>();
        this.Escolha = new ArrayList<String>();
    }
}
