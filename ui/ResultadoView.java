package ui;

import java.util.List;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ResultadoView extends VBox {

    private Label titulo;
    private HBox imagens;

    private ImageView pocaoImg;
    private ImageView simboloImg;

    public ResultadoView() {
        setStyle("""
            -fx-border-color: rgba(255, 255, 255, 0.5);
            -fx-padding: 5;
            -fx-border-width: 2;
        """);

        titulo = new Label("Resultado:");
        titulo.setStyle("""
            -fx-text-fill: white;
            -fx-font-size: 16px;
            -fx-font-weight: bold;
        """);

        pocaoImg = new ImageView();
        simboloImg = new ImageView();

        pocaoImg.setFitWidth(150);
        pocaoImg.setFitHeight(150);

        simboloImg.setFitWidth(150);
        simboloImg.setFitHeight(150);

        imagens = new HBox(pocaoImg, simboloImg);
        imagens.setAlignment(Pos.CENTER);

        getChildren().addAll(titulo, imagens);
    }

    public void atualizar(List<String> nomes) {

        imagens.getChildren().clear();

        if (nomes == null || nomes.isEmpty()) {
            titulo.setText("Poção de base vazia!");
            return;
        }

        titulo.setText("Poção " + String.join(" + ", nomes));

        for (String nome : nomes) {

            if (nome == null || nome.length() <= 3) {
                continue; // Evitar erro caso no futuro haja um nome inválido
            }

            // Remove os 3 primeiros caracteres
            String nomeSemPrefixo = nome.substring(3).trim();

            String base = nomeSemPrefixo.toLowerCase().replace(" ", "_");

            ImageView pocao = new ImageView(
                new Image("file:resources/pocoes/pocao_" + base + ".png")
            );
            pocao.setFitWidth(150);
            pocao.setFitHeight(150);

            ImageView simbolo = new ImageView(
                new Image("file:resources/simbolos/simbolo_" + base + ".png")
            );
            simbolo.setFitWidth(150);
            simbolo.setFitHeight(150);

            HBox bloco = new HBox(pocao, simbolo);
            bloco.setAlignment(Pos.CENTER);

            imagens.getChildren().add(bloco);
        }
    }
}