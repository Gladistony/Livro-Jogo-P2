import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Escolha extends GUtil{
    private String texto, opcao;
    private Capitulo pai;
    private Capitulo proximo;
    private int modo, chance;
    private boolean condicional;
    private ArrayList<String> configEscolhas;

    public Capitulo getProximo() {
        next();
        if (GUtil.jogoContinua()) return proximo;
        else {
            print("Game Over!!!!");
            return null;
        }
    }

    public boolean verificar_escolha_possivel(){
        if (!condicional){
            if (this.modo == 6){
                return this.chance < 25;
            }
            return true;
        }
        else {
            return pai.jogador_TemoItem(configEscolhas.get(1));
        }
    }
    public void set_Capitulo(Capitulo cap){
        this.proximo = cap;
    }
    public String get_npc_nome(){
        return this.pai.get_NpcName();
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
        if (this.modo == 1) {
            this.pai.add_Drop();
        } else if (this.modo == 2){
            this.pai.start_Combate();
        } else if (this.modo == 3){
            this.pai.excluirItemInventario(this.configEscolhas.get(1));
        } else if (this.modo == 4){
            this.pai.aplicarDano(1);
        } else if (this.modo == 6){
            this.newRandom();
        } else if (this.modo == 5){
            this.pai.aplicarDano(0.5);
        } else if (this.modo == 7){
            this.pai.currarMonstro();
        } else if (this.modo == 8){
            this.pai.bonus_Atk();
        } else if (this.modo == 9){
            this.pai.bonus_Def();
        } else if (this.modo == 10){
            this.pai.bonus_HP();
        }
        this.pai.salvarInventario();
        //Verificar se o personagem ainda está vivo antes de passar pra o proximo capitulo
        //if (this.pai.ainda_TemJogo()) this.proximo.executar(); else {
        //    printCentral("Game Over!!!!");
        //}
    }
    private void newRandom(){
        if (this.modo == 6){
            Random rng = new Random(System.currentTimeMillis());
            this.chance = rng.nextInt(100);
        }
    }
    public Escolha(String str, Capitulo pai){
        //Formato da escolha: Escolha#Modo#opção  -> Modo 0: Leva a um capitulo opção @ 1: Adiciona o item no npc e leva ao capitulo opção
        //                                                2: Leva a um combate com NPC e depois ao capitulo opção 3:Opcao condicional
        //                                                4: Causar dano e levar a um capitulo 5: Causar Metade do dano
        //                                                6: Escolha com 25% de chance de acontecer 7#Curar o jogador em 5x do atk do monstro
        List<String> myList = new ArrayList<String>(Arrays.asList(str.split("#")));
        this.texto = myList.get(0);
        this.modo = Integer.parseInt(myList.get(1));
        this.opcao = myList.get(2);
        this.condicional = this.modo == 3;
        this.pai = pai;
        if (this.condicional) this.configEscolhas = new ArrayList<String>(Arrays.asList(this.opcao.split("@")));
        newRandom();
    }
}
