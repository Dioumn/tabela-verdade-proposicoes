package ui;

import javafx.scene.image.*;
import javafx.scene.layout.GridPane;
import model.*;

public class InventarioView extends GridPane {

    private Pocao pocao;
    private TabelaView tabela;

    public InventarioView(Pocao pocao, TabelaView tabela) {
        this.pocao = pocao;
        this.tabela = tabela;

        setPrefHeight(100);
        setStyle("""
            -fx-border-color: rgba(255, 255, 255, 0.5);
            -fx-padding: 15;
            -fx-border-width: 2;
        """);
        atualizar();
    }


    public void atualizar() {
        getChildren().clear();

        // Exibe os ingredientes atuais da poção em 2x2
        for (int i = 0; i < 4; i++) {

            if (i < pocao.getIngredientes().size()) {
                String nome = pocao.getIngredientes().get(i).name().toLowerCase();

                ImageView slot = new ImageView(
                    new Image("file:resources/ingredientes/" + nome + ".png")
                );

                slot.setFitWidth(150);
                slot.setFitHeight(150);

                int index = i;
                slot.setOnMouseClicked(e -> {
                    pocao.remover(index);
                    atualizar();
                    tabela.atualizar();
                });

                add(slot, i % 2, i / 2);

            } else {
                ImageView vazio = new ImageView();
                vazio.setFitWidth(150);
                vazio.setFitHeight(150);
                add(vazio, i % 2, i / 2);
            }
        }
    }
}