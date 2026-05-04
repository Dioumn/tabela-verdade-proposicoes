package ui;

import javafx.scene.control.Label;
import javafx.scene.layout.*;
import model.*;

public class TabelaView extends GridPane {

    private Pocao pocao;

    private Label p, q, r, s, t;
    private Label p1, p2, p3, p4;
    private Label v1, v2, v3, v4;

    public TabelaView(Pocao pocao) {
        this.pocao = pocao;

        setHgap(5);
        setVgap(5);

        setStyle("""
            -fx-background-color: rgba(0, 0, 0, 0.25);
            -fx-border-color: rgba(255, 255, 255, 0.25);
            -fx-border-width: 2;
            -fx-padding: 10;
        """);

        add(header("p"), 0, 0);
        add(header("q"), 1, 0);
        add(header("r"), 2, 0);
        add(header("s"), 3, 0);
        add(header("t"), 4, 0);

        add(header(" (p∧t) "), 5, 0);
        add(header(" (q∧t) "), 6, 0);
        add(header(" (r∧t) "), 7, 0);
        add(header(" (s∧t) "), 8, 0);

        add(header(" (p∧t)∧¬(q∨r∨s) "), 9, 0);
        add(header(" (q∧t)∧¬(p∨r∨s) "), 10, 0);
        add(header(" (r∧t)∧¬(p∨q∨s) "), 11, 0);
        add(header(" (s∧t)∧¬(p∨q∨r) "), 12, 0);

        p = celula(); q = celula(); r = celula(); s = celula(); t = celula();
        p1 = celula(); p2 = celula(); p3 = celula(); p4 = celula();
        v1 = celula(); v2 = celula(); v3 = celula(); v4 = celula();

        add(p, 0, 1);
        add(q, 1, 1);
        add(r, 2, 1);
        add(s, 3, 1);
        add(t, 4, 1);

        add(v1, 5, 1);
        add(v2, 6, 1);
        add(v3, 7, 1);
        add(v4, 8, 1);

        add(p1, 9, 1);
        add(p2, 10, 1);
        add(p3, 11, 1);
        add(p4, 12, 1);

        atualizar();
    }

    private Label header(String txt) {
        Label l = new Label(txt);
        l.setMinSize(25, 25);

        l.setStyle("""
            -fx-text-fill: white;
            -fx-font-weight: bold;
            -fx-alignment: center;
        """);
        return l;
    }

    private Label celula() {
        Label l = new Label("-");
        l.setMinSize(25, 25);
        l.setMaxSize(150, 25);

        l.setStyle("""
            -fx-border-color: white;
            -fx-alignment: center;
            -fx-text-fill: white;
        """);
        return l;
    }

    private void setValor(Label l, boolean valor) {
        l.setText(valor ? "V" : "F");

        if (valor) {
            l.setStyle("""
                -fx-background-color: rgba(0,255,0,0.25);
                -fx-border-color: rgba(255, 255, 255, 0.25);
                -fx-alignment: center;
                -fx-text-fill: white;
            """);
        } else {
            l.setStyle("""
                -fx-background-color: rgba(255, 0, 0, 0.25);
                -fx-border-color: rgba(255, 255, 255, 0.25);
                -fx-alignment: center;
                -fx-text-fill: white;
            """);
        }
    }

    public void atualizar() {

        boolean P = pocao.getIngredientes().contains(Ingrediente.CENOURA_DOURADA);
        boolean Q = pocao.getIngredientes().contains(Ingrediente.ACUCAR);
        boolean R = pocao.getIngredientes().contains(Ingrediente.MELAO_RELUZENTE);
        boolean S = pocao.getIngredientes().contains(Ingrediente.OLHO_DE_ARANHA);
        boolean T = pocao.estaQuente();

        setValor(p, P);
        setValor(q, Q);
        setValor(r, R);
        setValor(s, S);
        setValor(t, T);

        setValor(v1, P && T);
        setValor(v2, Q && T);
        setValor(v3, R && T);
        setValor(v4, S && T);

        setValor(p1, P && T && !(Q || R || S));
        setValor(p2, Q && T && !(P || R || S));
        setValor(p3, R && T && !(P || Q || S));
        setValor(p4, S && T && !(P || Q || R));
    }
}