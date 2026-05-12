import javafx.application.Application;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import model.Pocao;
import ui.*;

public class Main extends Application {

    @Override
    public void start(Stage stage) {

        // MODEL
        Pocao pocao = new Pocao();

        // VIEWS
        ResultadoView resultadoView = new ResultadoView();
        TabelaView tabela = new TabelaView(pocao);
        InventarioView inventario = new InventarioView(pocao, tabela);
        CaldeiraoView caldeirao = new CaldeiraoView(pocao, inventario, tabela, resultadoView);
        IngredienteView ingredientes = new IngredienteView();

        // DIREITA: inventário + tabela + resultado
        VBox direita = new VBox(10, inventario, resultadoView);

        // ROOT: ingredientes | caldeirão | direita
        HBox topo = new HBox(10, ingredientes, caldeirao, direita);
        VBox root = new VBox(10, topo, tabela);
        root.setPadding(new Insets(10));

        // FUNDO (imagem repetida)
        root.setStyle("""
            -fx-background-image: url('file:resources/fx/fundo.png');
            -fx-background-repeat: stretch;
        """);

        // SCENE
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("Propotion Potions");
        stage.setResizable(false); // tela fixa
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}