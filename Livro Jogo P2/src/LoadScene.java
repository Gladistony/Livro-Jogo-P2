import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class LoadScene extends GUtil {

    @FXML
    private Button carregar;

    @FXML
    private Button novo;

    @FXML
    void carregarJogo(ActionEvent event) {
        try {
            GUtil.loadPJPrincipal();
            //System.out.println(GUtil.getIDHistoria());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SceneJogo.fxml"));
            Parent root = loader.load();
            Stage  stage = (Stage) novo.getScene().getWindow();
            stage.getScene().setRoot(root);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @FXML
    void novoJogo(ActionEvent event) {
        try {
            File arq = new File("dadosSalvo.dat");
            arq.delete();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CriarPersonagemScene.fxml"));
            Parent root = loader.load();
            Stage  stage = (Stage) novo.getScene().getWindow();
            stage.getScene().setRoot(root);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
