public class CapituloImagem extends Capitulo {
    protected String imagem;
    public CapituloImagem(Personagem pj) {
        super(pj);
    }
    public void set_imagem(String img){
        this.imagem = img;
    }
    @Override
    public String dialogoCapitulo(){
        String ori = this.imagem;
        ori += "\n" + super.dialogoCapitulo();
        return ori;
        //super.mostrar();
    }
    public void receberCapitulo(Capitulo cap){
        this.Dialogo = cap.Dialogo;
        this.Escolha = cap.Escolha;
        this.nameNPC = cap.nameNPC;
    }
}
