import java.util.Scanner;

public class CapituloImagem extends Capitulo {
    protected String imagem;
    public CapituloImagem(Scanner scan, Personagem pj) {
        super(scan, pj);
    }
    public void set_imagem(String img){
        this.imagem = img;
    }
    @Override
    public void mostrar(){
        print(this.imagem);
        super.mostrar();
    }
    public void receberCapitulo(Capitulo cap){
        this.Dialogo = cap.Dialogo;
        this.Escolha = cap.Escolha;
        this.nameNPC = cap.nameNPC;
    }
}
