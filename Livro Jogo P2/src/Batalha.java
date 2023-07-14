import java.util.Random;
import java.util.Scanner;

public class Batalha extends GUtil {
    private Personagem Jogador;
    private Personagem Inimigo;
    private Scanner scan;

    public Batalha(Personagem J, Personagem I, Scanner s){
        this.Jogador = J;
        this.Inimigo = I;
        this.scan = s;
    }

    private int melhorResposta(int opcao){
        if (opcao == 1) return 1; else
        if (opcao == 2) return 3; else
        return 3;
    }

    private void dicaJogador(int atk){
        instaprint("Num surto de adrenalina, você percebe que:");
        if (atk == 1) instaprint("O inimigo vai lhe atacar com tudo"); else
        if (atk == 1) instaprint("O inimigo vai tentar evadir"); else
        instaprint("O inimigo decidu receber seu ataque de frente");
    }
    public void Run(){
        int cont = 0;
        int atkinimgo = 0;
        int resposta;
        int def;
        int lastEscolha = -1;
        int adrenalina = -1;
        String[] lista = {"Atacar","Esquivar","Defender"};
        Random rng = new Random(System.currentTimeMillis());
        while (Jogador.vivo() && Inimigo.vivo()){
            cont += 1;
            instaprint("Rodada "+cont);
            Jogador.showData();
            Inimigo.showData();
            atkinimgo = rng.nextInt(3) + 1;
            if (Jogador.get_hp() < 20){
                adrenalina = rng.nextInt(100);
                if (adrenalina < 20) this.dicaJogador(atkinimgo); else
                adrenalina = -1;
            }
            resposta = escolhas(scan,"O inimigo avança contra você:", lista);
            if ((lastEscolha == resposta) && (adrenalina < 0)) {
                atkinimgo = melhorResposta(lastEscolha);
                instaprint("O inimigo prever seu movimento e então:");
            } else lastEscolha = resposta;
            //print(resposta+"/"+lastEscolha);
            if (resposta == 1){
                if (atkinimgo == 1){
                    instaprint("Vocês trocam socos e ambos recebem dano");
                    Jogador.set_damage(Inimigo.get_atk());
                    Inimigo.set_damage(Jogador.get_atk());
                } else if (atkinimgo == 2) {
                    instaprint("O inimigo evita o golpe e contra ataca causando grandes danos");
                    Jogador.set_damage(Inimigo.get_atk()*2);
                } else {
                    instaprint("Você ataca e o inimigo tenta bloquear o dano");
                    def = (Inimigo.getDef() + 1)*5;
                    Inimigo.set_damage(Math.max(Jogador.get_atk() - def , 0));
                }
            } else if (resposta == 2){
                if (atkinimgo == 1){
                    instaprint("O inimigo tenta lhe ataca mas você esquiva e contra ataca, dando muito dano nele");
                    Inimigo.set_damage(Jogador.get_atk()*2);
                } else if (atkinimgo == 2) {
                    instaprint("Ambos não se movem, não acontecendo nada");
                } else {
                    instaprint("Você tenta esquiva mas o inimigo que estava numa posição de defesa aproveita para lhe acerta um dano");
                    Jogador.set_damage(Inimigo.get_atk());
                }
            } else {
                if (atkinimgo == 1){
                    instaprint("Você tenta bloquear o dano do inimigo");
                    def = (Jogador.getDef() + 1)*5;
                    Jogador.set_damage(Math.max(Inimigo.get_atk() - def , 0));
                } else if (atkinimgo == 2) {
                    instaprint("O inimigo tenta esquivar mas você contra ataca ele ja que estava pronto");
                    Inimigo.set_damage(Jogador.get_atk());
                } else {
                    instaprint("Ambos se atacam causando danos equivalentes");
                    Jogador.set_real_damage(15);
                    Inimigo.set_real_damage(10);
                }
            }
        }
        printCentral("Resultado do combate:");
        if (Jogador.vivo() && !Inimigo.vivo()){
            print("Vitoria !!");
            Jogador.set_Vitoria(Inimigo);
        } else if (!Jogador.vivo() && Inimigo.vivo()){
            print("Derrota!!");
        } else {
            print("Empate!!");
        }
        Jogador.showData();
        Inimigo.showData();
    }
}
