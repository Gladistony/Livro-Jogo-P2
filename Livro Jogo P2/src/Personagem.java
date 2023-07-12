public class Personagem extends GUtil{
    private String nome = "X";
    private String drop;
    private int HpAtual, HpMax;
    private int Atk, Def;
    
    public String get_drop(){
        return this.drop;
    }
    public int get_hp(){
        return HpAtual;
    }
    public String get_name(){
        return nome;
    }
    public int get_atk(){
        return this.Atk;
    }


    public void set_damage(long l){
        this.HpAtual -= Math.max(0, l-this.Def);
        if (this.HpAtual < 0){
            this.HpAtual = 0;
        }
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
