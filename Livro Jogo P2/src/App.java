import java.io.File;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) throws Exception {
        launch(args);
        //Criar Variaveis
       /*  String caminho;
        //Verifiar se existe jogo salvo
        arq = new File("dadosSalvo.dat");
        if (arq.exists()){
            resposta = GUtil.escolhas(scan,"Um arquivo salvo foi detectado, deseja carregar os dados do mesmo?", lista);
            if (resposta == 1) {
                MainPj.loadData();
                ListaCapitulos.get(MainPj.getIDHistoria()).executar();
            } else {
                //Iniciar a execulção do programa
                MainPj.criarJogaodor(scan);
                ListaCapitulos.get(0).executar();
            }
        } else {
            //Iniciar a execulção do programa
            MainPj.criarJogaodor(scan);
            ListaCapitulos.get(0).executar();
        }
        //Loop de retorna ao jogo
        //Finalização/Opção de reiniciar o jogo
        GUtil.print("Obrigado por jogar");
        resposta = GUtil.escolhas(scan,"Deseja começar de novo?", lista);
        if (resposta == 1) main(args);
        scan.close(); */
    }

    @Override
    public void start(Stage primStage) throws Exception {
        primStage.setTitle("Livro Jogo, Projeto de P2");
        GUtil.loadData("Rsc");
        /*StackPane root = new StackPane();
        primStage.setScene(new Scene(root, 300, 250));
        primStage.show(); */
        File arq = new File("dadosSalvo.dat");
        Scene scene;
        if (arq.exists()){
            //Arquivo existe, então mostra a Scene de carregamento
            FXMLLoader loader = new FXMLLoader(getClass().getResource("BaseScene.fxml"));
            Parent root = loader.load();
            scene = new Scene(root);
        } else {
            //Arquivo não existe, então mostra a Scene de criação de personagem
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CriarPersonagemScene.fxml"));
            Parent root = loader.load();
            scene = new Scene(root);
        }
        primStage.setScene(scene);
        primStage.show();
    }
}
