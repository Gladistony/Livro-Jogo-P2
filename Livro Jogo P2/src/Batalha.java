import java.util.Random;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class Batalha extends GUtil {
    private Personagem Jogador;
    private Personagem Inimigo;

    public Batalha(Personagem J, Personagem I){
        this.Jogador = J;
        this.Inimigo = I;
    }
    private void atualizarData(Label jogador, Label inimigo, Dialog<Boolean> dialog){
        if (Jogador.vivo() && !Inimigo.vivo()){
            print("Vitoria !!");
            Jogador.set_Vitoria(Inimigo);
            dialog.setResult(true);
        } else if (!Jogador.vivo() && Inimigo.vivo()){
            print("Derrota!!");
            dialog.setResult(true);
        } else if (!Jogador.vivo() && !Inimigo.vivo()){
            print("Empate!!");
            dialog.setResult(true);
        }
        jogador.setText(Jogador.showData());
        inimigo.setText(Inimigo.showData());
    }

    public void printCombate(Label r,  String texto){
        r.setText(texto);
    }

    public Boolean RunOnDialog(){
        Dialog<Boolean> dialog = new Dialog<>();
        dialog.setTitle("Batalha entre "+Jogador.get_name()+" e "+Inimigo.get_name());
        dialog.setResizable(true);
        Random rng = new Random(System.currentTimeMillis());
        Label jogador = new Label();
        Label inimigo = new Label();
        atualizarData(jogador, inimigo, dialog);
        GridPane grid = new GridPane();
        grid.add(jogador, 0, 0);
        grid.add(inimigo, 0, 1);
        Label text = new Label("O que você deseja fazer?");
        Button atacar = new Button("Atacar");
        Label resultado = new Label();
        atacar.setOnAction((event) -> {
            int atkinimgo = rng.nextInt(3) + 1;
            if (atkinimgo == 1){
                printCombate(resultado,"Vocês trocam socos e ambos recebem dano");
                Jogador.set_damage(Inimigo.get_atk());
                Inimigo.set_damage(Jogador.get_atk());
            } else if (atkinimgo == 2) {
                printCombate(resultado,"O inimigo evita o golpe e contra ataca");
                Jogador.set_damage(Inimigo.get_atk()*2);
            } else {
                printCombate(resultado,"Você ataca e o inimigo tenta bloquear o dano");
                int def = (Inimigo.getDef() + 1)*5;
                Inimigo.set_damage(Math.max(Jogador.get_atk() - def , 0));
            }
            atualizarData(jogador, inimigo, dialog);
        });
        Button esquivar = new Button("Esquivar");
        esquivar.setOnAction((event) -> {
            int atkinimgo = rng.nextInt(3) + 1;
            if (atkinimgo == 1){
                printCombate(resultado,"você esquiva e contra ataca");
                Inimigo.set_damage(Jogador.get_atk()*2);
            } else if (atkinimgo == 2) {
                printCombate(resultado,"Ambos não se movem, não acontecendo nada");
            } else {
                printCombate(resultado,"Você tenta esquiva mas o inimigo lhe acerta");
                Jogador.set_damage(Inimigo.get_atk());
            }
            atualizarData(jogador, inimigo, dialog);
        });
        Button defender = new Button("Defender");
        defender.setOnAction((event) -> {
            int atkinimgo = rng.nextInt(3) + 1;
            if (atkinimgo == 1){
                printCombate(resultado,"Você tenta bloquear o dano do inimigo");
                int def = (Jogador.getDef() + 1)*5;
                Jogador.set_damage(Math.max(Inimigo.get_atk() - def , 0));
            } else if (atkinimgo == 2) {
                printCombate(resultado,"O inimigo tenta esquivar mas você contra ataca ele ja que estava pronto");
                Inimigo.set_damage(Jogador.get_atk());
            } else {
                printCombate(resultado,"Ambos se atacam causando danos equivalentes");
                Jogador.set_real_damage(15);
                Inimigo.set_real_damage(10);
            }
            atualizarData(jogador, inimigo, dialog);
        });
        grid.add(text, 0, 2);
        grid.add(atacar, 0, 3);
        grid.add(esquivar, 0, 4);
        grid.add(defender, 0, 5);
        grid.add(resultado, 0, 6);
        dialog.getDialogPane().setContent(grid);
        return dialog.showAndWait().orElse(false);
    }

}
