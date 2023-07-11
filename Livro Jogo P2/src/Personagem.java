public class Personagem extends GUtil{
    private String nome = "X";
    private String drop;
    private int HpAtual, HpMax;
    private int Atk, Def;
    


    public String get_name(){
        return nome;
    }
    public void set_name(String n){
        this.nome = n;
    }
    public void set_hp_str(String n){
        this.HpMax = Integer.parseInt(n);
        this.HpAtual = this.HpMax;
    }
    public void set_drop(String n){
        this.drop = n;
    }
    public void set_att(String a, String b){
        this.Atk = Integer.parseInt(a);
        this.Def = Integer.parseInt(b);
    }
}
