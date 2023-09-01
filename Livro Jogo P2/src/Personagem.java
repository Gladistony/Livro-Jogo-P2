import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class Personagem implements Serializable{
    private String nome = "X";
    private String drop;
    private int HpAtual, HpMax;
    private int Atk, Def;
    private Inventario Bag;
    private int IDHistoria = 0;
    private String dataInventario;
    
    public String getDataInventario() {
        return dataInventario;
    }

    public int getIDHistoriaPJ() {
        return IDHistoria;
    }
    public void apagarItem(String i){
        this.Bag.apagarItem(i);
    }

    public void setIDHistoria(int iDHistoria) {
        IDHistoria = iDHistoria;
    }
    public int getDef() {
        return Def;
    }
    public int getHpMax() {
        return HpMax;
    }
    public void saveData(){
        this.dataInventario = this.Bag.get_Data();
        try {
            FileOutputStream file = new FileOutputStream("dadosSalvo.dat");
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(this);
            out.close();
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void add_Atk( int v){
        this.Atk += v;   
    }
    public void add_Def(int v){
        this.Def += v;
    }
    public void add_Hp(int v){
        this.HpAtual += v;
        this.HpMax += v;
    }

    public List<String> getBoxItem(){
        return Arrays.asList(Bag.get_Data().split("#"));
    }
    public void loadData(){
        Personagem ret;
        try {
            FileInputStream file = new FileInputStream("dadosSalvo.dat");
            ObjectInputStream in = new ObjectInputStream(file);
            ret = (Personagem) in.readObject();
            this.nome = ret.get_name();
            this.HpMax = ret.getHpMax();
            this.HpAtual = ret.get_hp();
            this.Atk = ret.get_atk();
            this.Def = ret.getDef();
            this.IDHistoria = ret.getIDHistoriaPJ();
            for (String item : ret.getBoxItem()) {
                this.Bag.Add(item);
            }
            in.close();
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public Personagem() {
        this.Bag = new Inventario();
        this.IDHistoria = 0;
    }

    public void criarJogaodor(String scan){
        //print("Digite o nome do seu personagem principal");
        this.nome = scan;//.nextLine();
        this.Atk = 20;
        this.HpMax = 100;
        this.HpAtual = this.HpMax;
        this.Def = 0;
        this.drop = "";
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
    public boolean verificarIventario(String i){
        return Bag.temo_Item(i);
    }

    public void set_Item(String d){
        this.Bag.Add(d);
    }
    public void set_Vitoria(Personagem inimigo){
        String [] loot = inimigo.get_premiacao();
        Bag.Add(loot[3]);
        int ganhoMax = Math.max(Math.round((Integer.parseInt(loot[0]) - this.HpMax) / 10),0); 
        this.HpMax += ganhoMax;
        this.HpAtual += ganhoMax;
        this.Atk += Math.max(Math.round((Integer.parseInt(loot[1]) - this.Atk) / 3), 0); 
        this.Def += Math.max(Math.round((Integer.parseInt(loot[2]) - this.Def) / 3), 0);
    }
    //------------------Funções de ajuste de HP---------------------------------


    public void set_real_damage(long l){
        GUtil.print(this.nome + " recebeu "+ l + " pontos de dano");
        this.HpAtual -= l;
        if (this.HpAtual < 0){
            this.HpAtual = 0;
        }
    }
    public void set_damage(long l){
        long danorec = Math.max(0, l-this.Def);
        GUtil.print(this.nome + " recebeu "+ danorec + " pontos de dano");
        this.HpAtual -= danorec;
        if (this.HpAtual < 0){
            this.HpAtual = 0;
        }
    }
    public void recuperar_Vida(int valor){
        this.HpAtual += valor;
        if (this.HpAtual > this.HpMax) this.HpAtual = this.HpMax;
        GUtil.print(nome + " recuperou "+ valor +" pontos de vida");
    }

    //------------------------------------------------------------------------
    
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
    public String showData() {
        if (vivo()){
            Bag.compactar();
            return (this.nome +" - "+ this.HpAtual + " / "+ this.HpMax +" Inventario: "+Bag.listar());
        } else {
            return (this.nome + " está morto");
        }
    }
}
