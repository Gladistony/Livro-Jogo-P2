import java.util.ArrayList;
import java.util.List;

public class Inventario extends GUtil {
    private List<String> List;

    public String listar(){
        String ret = "";
        for (String s :List){
            ret = ret +" - " +  s;
        }
        return ret;
    }
    public void Add(String item){
        List.add(item);
    }
    public Inventario(){
        List = new ArrayList<String>();
    }
    public boolean temo_Item(String item){
        boolean resposta = false;
        for (String s :List){
            if (s.equalsIgnoreCase(item)){
                resposta = true;
                break;
            }
        }
        return resposta;
    }
}
