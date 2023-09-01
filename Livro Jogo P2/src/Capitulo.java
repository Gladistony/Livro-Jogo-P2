import java.util.ArrayList;
import java.util.List;

public class Capitulo extends GUtil {
    protected List<String> Dialogo;
    protected List<Escolha> Escolha;
    protected Personagem nameNPC;
    protected Personagem pjPrincipal;


    public void bonus_Atk(){
        this.pjPrincipal.add_Atk(nameNPC.get_atk());
    }
    public void bonus_Def(){
        this.pjPrincipal.add_Def(nameNPC.getDef());
    }
    public void bonus_HP(){
        this.pjPrincipal.add_Hp(nameNPC.getHpMax());
    }
    public void excluirItemInventario(String item){
        this.pjPrincipal.apagarItem(item);
    }
    public void salvarInventario(){
        pjPrincipal.saveData();
    }

    public boolean jogador_TemoItem(String item){
        return pjPrincipal.verificarIventario(item);
    }

    public boolean ainda_TemJogo(){
        return pjPrincipal.vivo();
    }

    public void add_Drop(){
        pjPrincipal.set_Item(nameNPC.get_drop());
    }
    public void start_Combate(){
        Batalha luta = new Batalha(pjPrincipal, nameNPC);
        luta.RunOnDialog();
    }

    public void aplicarDano(double d){
        this.pjPrincipal.set_damage(Math.round(d*nameNPC.get_atk()));
    }
    public String get_NpcName(){
        return this.nameNPC.get_name();
    }
    public void set_NpcName(Personagem str){
        this.nameNPC = str;
    }
    public void addEscolha(String str){
        Escolha.add(new Escolha(str, this));
    }
    public void addDialogo(String dia){
        this.Dialogo.add(dia);
    }
    public Capitulo(Personagem pj){
        this.Dialogo = new ArrayList<String>();
        this.Escolha = new ArrayList<Escolha>();
        //this.scan = s;
        this.pjPrincipal = pj;
    }
    
    public String dialogoCapitulo(){
        String retorno = "";
        for (String s: this.Dialogo){
            retorno += String.copyValueOf(s.toCharArray()) + "\n";
        }
        retorno = retorno.replace("NPC", nameNPC.get_name());
        retorno = retorno.replace("PLAYER", pjPrincipal.get_name());
        return retorno;
    }
    public ArrayList<String> get_Escolhas(){
        ArrayList<String> retorno = new ArrayList<String>();
        for (Escolha esc: this.Escolha){
            if (esc.verificar_escolha_possivel()) retorno.add(esc.get_texto());
        }
        return retorno;
    }
    public void ativarEscolhas(List<Capitulo> ListaCapitulos){
        for (Escolha esc: this.Escolha){
            esc.set_Capitulo(ListaCapitulos.get(esc.get_id_capitulo()));
        }
    }
    public void currarMonstro() {
        this.pjPrincipal.recuperar_Vida(this.nameNPC.get_atk()*5);
        this.pjPrincipal.showData();
    }
    public Capitulo getProximoCapitulo(String txt){
        for (Escolha esc: this.Escolha){
            if (esc.get_texto().equals(txt)) return esc.getProximo();
        }
        return null;
    }
}
