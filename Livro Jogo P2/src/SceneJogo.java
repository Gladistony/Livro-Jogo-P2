import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

public class SceneJogo extends GUtil implements Initializable {

    @FXML
    private VBox listaBotoes;

    @FXML
    private Button load;

    @FXML
    private TextArea telaJogo;

    @FXML
    void loadEvent(ActionEvent event) {
        
    }

    public void initialize(){
        load.setVisible(false);
        Capitulo tempcap = GUtil.getCapitulos(GUtil.getIDHistoria());
        GUtil.startPrint();
        mostraCapitulo(tempcap);
    }


    public void mostraCapitulo(Capitulo exibir){
        telaJogo.clear();
        listaBotoes.getChildren().clear();
        if (exibir == null) return;
        GUtil.setProgresso(exibir);
        telaJogo.setText(exibir.dialogoCapitulo());
        for (String s: exibir.get_Escolhas()){
            Button temp = new Button(s);
            temp.setOnAction((ActionEvent event) -> {
                mostraCapitulo(exibir.getProximoCapitulo(s));
            });
            listaBotoes.getChildren().add(temp);
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initialize();
    }

}
