import java.io.File;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) throws Exception {
        launch(args); 
    }
    @Override
    public void start(Stage primStage) throws Exception {
        primStage.setTitle("Livro Jogo, Projeto de P2");
        GUtil.loadData("Rsc");
        File arq = new File("dadosSalvo.dat");
        Scene scene;
        if (arq.exists()){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("BaseScene.fxml"));
            Parent root = loader.load();
            scene = new Scene(root);
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CriarPersonagemScene.fxml"));
            Parent root = loader.load();
            scene = new Scene(root);
        }
        primStage.setScene(scene);
        primStage.show();
    }
}