import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NovoPersonagem extends GUtil {

    @FXML
    private Button botao;

    @FXML
    private TextField nomePJ;

    @FXML
    void novoPersonagem(ActionEvent event) {
        String personagem = nomePJ.getText();
        GUtil.criarPJPrincipal(personagem);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SceneJogo.fxml"));
            Parent root = loader.load();
            Stage  stage = (Stage) botao.getScene().getWindow();
            stage.getScene().setRoot(root);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
