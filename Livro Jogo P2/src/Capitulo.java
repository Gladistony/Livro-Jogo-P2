import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Capitulo extends GUtil {
    private List<String> Dialogo;
    private List<Escolha> Escolha;
    private Personagem nameNPC;
    private Scanner scan;
    private Personagem pjPrincipal;


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
        Batalha luta = new Batalha(pjPrincipal, nameNPC, scan);
        luta.Run();
    }

    public void aplicarDano(double d){
        this.pjPrincipal.set_damage(Math.round(d*nameNPC.get_atk()));
    }
    private void mostrar(){
        String temp;
        for (String s: this.Dialogo){
            temp = String.copyValueOf(s.toCharArray());
            temp = temp.replace("NPC", nameNPC.get_name());
            temp = temp.replace("PLAYER", pjPrincipal.get_name());
            print(temp);
        }
        for (Escolha e: this.Escolha){
            if (e.verificar_escolha_possivel()) print("> "+e.get_texto());
        }
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
    public Capitulo(Scanner s, Personagem pj){
        this.Dialogo = new ArrayList<String>();
        this.Escolha = new ArrayList<Escolha>();
        this.scan = s;
        this.pjPrincipal = pj;
    }
    private int verificar_id(String s){
        int cont = 0;
        for (Escolha esc: this.Escolha){
            if (esc.get_texto().equalsIgnoreCase(s) && esc.verificar_escolha_possivel()){
                return cont;
            } else {
                cont++;
            }
        }
        return -1;
    }
    private int escolher(){
        if (this.Escolha.size() > 0){
            String ler = this.scan.nextLine();
            int id = verificar_id(ler);
            while (id < 0){
                print("Opção invalida, tente novamente!!");
                ler = this.scan.nextLine();
                id = verificar_id(ler);
            }
            return id;
        } else {
            return -1;
        }
        
    }
    public void executar(){
        this.mostrar();
        int retorno = this.escolher();
        if (retorno >= 0) {
            this.pjPrincipal.setIDHistoria(this.Escolha.get(retorno).get_id_capitulo());
            this.Escolha.get(retorno).next();
        }
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
}
