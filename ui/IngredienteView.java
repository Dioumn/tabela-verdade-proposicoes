package ui;

import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.layout.VBox;
import model.Ingrediente;

public class IngredienteView extends VBox {

    public IngredienteView() {
        setSpacing(60);

        for (Ingrediente ing : Ingrediente.values()) {
            getChildren().add(criarItem(ing));
        }
        setStyle("""
            -fx-border-color: rgba(255, 255, 255, 0.5);
            -fx-padding: 15;
            -fx-border-width: 2;
        """);
    }

    private ImageView criarItem(Ingrediente ing) {
        ImageView view = new ImageView(
            new Image("file:resources/ingredientes/" + ing.name().toLowerCase() + ".png")
        );

        view.setFitWidth(70);
        view.setFitHeight(70);

        view.setOnDragDetected(e -> {
            Dragboard db = view.startDragAndDrop(TransferMode.COPY);
            ClipboardContent content = new ClipboardContent();
            content.putString(ing.name());
            db.setContent(content);
        });

        return view;
    }
}