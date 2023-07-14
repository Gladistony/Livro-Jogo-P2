public class Personagem extends GUtil{
    private String nome = "X";
    private String drop;
    private int HpAtual, HpMax;
    private int Atk, Def;
    private Inventario Bag;
    
    
    public Personagem() {
        this.Bag = new Inventario();
    }
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
    public String[] get_premiacao(){
        String[] Res = new String[4];
        Res[0] = Integer.toString(HpMax);
        Res[1] = Integer.toString(Atk);
        Res[2] = Integer.toString(Def);
        Res[3] = drop;
        return Res;
    }


    public void set_Vitoria(Personagem inimigo){
        String [] loot = inimigo.get_premiacao();
        Bag.Add(loot[3]);
        int ganhoMax = Math.round(Integer.parseInt(loot[0]) / 10 ); 
        this.HpMax += ganhoMax;
        this.HpAtual += ganhoMax;
        this.Atk += Math.round(Integer.parseInt(loot[1]) / 5 ); 
        this.Def += Math.round(Integer.parseInt(loot[2]) / 5 ); 
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
    public boolean vivo() {
        return this.HpAtual > 0;
    }
    public void showData() {
        if (vivo()){
            print(this.nome +" - "+ this.HpAtual + " / "+ this.HpMax +" Inventario: "+Bag.listar());
        } else {
            print(this.nome + " está morto");
        }
    }
}
