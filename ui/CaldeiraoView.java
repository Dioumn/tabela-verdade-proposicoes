package ui;

import java.util.List;
import javafx.animation.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.util.Duration;
import model.*;

public class CaldeiraoView extends VBox {

    private ImageView fogo;

    public CaldeiraoView(Pocao pocao, InventarioView inventario, TabelaView tabela, ResultadoView resultadoView) {

        setStyle("""
            -fx-border-color: rgba(255, 255, 255, 0.5);
            -fx-padding: 25;
            -fx-border-width: 2;
        """);

        setAlignment(Pos.CENTER); // centraliza conteúdo geral

        fogo = new ImageView(new Image("file:resources/fx/fogo.gif"));
        fogo.setScaleX(0.5);
        fogo.setScaleY(0.5);

        ImageView caldeirao = new ImageView(new Image("file:resources/fx/caldeirao.png"));
        caldeirao.setFitWidth(200);
        caldeirao.setFitHeight(200);

        StackPane area = new StackPane(fogo, caldeirao);
        StackPane.setAlignment(fogo, Pos.BOTTOM_CENTER);

        caldeirao.setOnDragOver(e -> {
            if (e.getDragboard().hasString()) {
                e.acceptTransferModes(TransferMode.COPY);
            }
        });

        caldeirao.setOnDragDropped(e -> {
            Ingrediente ing = Ingrediente.valueOf(e.getDragboard().getString());
            pocao.adicionar(ing);
            inventario.atualizar();
            tabela.atualizar();
        });

        Button esquentar = new Button("Esquentar");
        Button finalizar = new Button("Finalizar");

        // estilo dos botões
        String estiloBotao = "-fx-font-size: 16px; -fx-font-weight: bold;";
        esquentar.setStyle(estiloBotao);
        finalizar.setStyle(estiloBotao);

        // tamanho dos botões
        esquentar.setPrefSize(150, 50);
        finalizar.setPrefSize(150, 50);

        esquentar.setOnAction(e -> {
            pocao.esquentar();
            atualizar(pocao);
            tabela.atualizar();
        });

        finalizar.setOnAction(e -> {
            List<String> r = pocao.finalizar();
            resultadoView.atualizar(r);
            inventario.atualizar();
            atualizar(pocao);
            tabela.atualizar();
        });

        // container dos botões centralizado
        VBox botoes = new VBox(15, esquentar, finalizar);
        botoes.setAlignment(Pos.CENTER);

        getChildren().addAll(area, botoes);

        Timeline t = new Timeline(
            new KeyFrame(Duration.seconds(0.7), e -> {
                pocao.esfriarNatural();
                atualizar(pocao);
                tabela.atualizar();
            })
        );
        t.setCycleCount(Timeline.INDEFINITE);
        t.play();
    }

    private void atualizar(Pocao p) {
        double escala = 0.4 + (p.getCalor() / 250.0);
        fogo.setScaleX(escala);
        fogo.setScaleY(escala);
    }
}