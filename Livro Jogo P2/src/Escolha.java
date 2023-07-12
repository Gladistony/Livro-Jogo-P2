import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Escolha extends GUtil{
    private String texto, npc, opcao;
    private Capitulo proximo;
    private int modo;
    private boolean condicional;

    public boolean verificar_escolha_possivel(){
        if (!condicional) return true;
        else {
            return false;
            //Implementar codigo de verificação da escolha
        }
    }
    public void set_Capitulo(Capitulo cap){
        this.proximo = cap;
    }
    public String get_npc_nome(){
        return this.npc;
    }
    public String get_texto(){
        return this.texto;
    }
    public int get_id_capitulo(){
        if (modo != 3) return Integer.parseInt(this.opcao);
        else {
            int pos = this.opcao.indexOf("@");
            return Integer.parseInt(this.opcao.substring(0, pos));
        }
    }
    public void next(){
        this.proximo.executar();
    }
    public Escolha(String str){
        //Formato da escolha: Escolha#Modo#opção  -> Modo 0: Leva a um capitulo opção @ 1: Adiciona o item no npc e leva ao capitulo opção
        //                                                2: Leva a um combate com NPC e depois ao capitulo opção 3:Opcao condicional
        //                                                4: Causar dano e levar a um capitulo
        List<String> myList = new ArrayList<String>(Arrays.asList(str.split("#")));
        this.texto = myList.get(0);
        this.modo = Integer.parseInt(myList.get(1));
        this.opcao = myList.get(2);
        this.condicional = this.modo == 3;
    }
}
