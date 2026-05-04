package model;

import java.util.ArrayList;
import java.util.List;

public class Pocao {

    private int calor = 0;
    private List<Ingrediente> ingredientes = new ArrayList<>();

    public int getCalor() {
        return calor;
    }

    public List<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public void esquentar() {
        if (calor < 100) calor += 10;
    }

    public void esfriarNatural() {
        if (calor > 0) calor -= 1;
    }

    public boolean estaQuente() {
        return calor >= 60;
    }

    public void adicionar(Ingrediente ing) {
        if (ingredientes.size() < 4) {
            ingredientes.add(ing);
        }
    }

    public void remover(int index) {
        if (index >= 0 && index < ingredientes.size()) {
            ingredientes.remove(index);
        }
    }

    public void reset() {
        ingredientes.clear();
    }

    // Lógica de resultado baseada nos ingredientes e no calor
    public List<String> resultado() {
        boolean P = ingredientes.contains(Ingrediente.CENOURA_DOURADA);
        boolean Q = ingredientes.contains(Ingrediente.ACUCAR);
        boolean R = ingredientes.contains(Ingrediente.MELAO_RELUZENTE);
        boolean S = ingredientes.contains(Ingrediente.OLHO_DE_ARANHA);
        boolean T = estaQuente();

        List<String> res = new ArrayList<>();

        if (!T) {
            res.add("falhou, o caldeirão está muito frio!");
            return res;
        }

        boolean r1 = P && !(Q || R || S);
        boolean r2 = Q && !(P || R || S);
        boolean r3 = R && !(P || Q || S);
        boolean r4 = S && !(P || Q || R);

        if (r1) res.add("de Visao Noturna");
        if (r2) res.add("de Velocidade");
        if (r3) res.add("de Cura");
        if (r4) res.add("de Veneno");

        if (res.isEmpty()) {
            res.add("Desconhecida");
        }

        return res;
    }

    public List<String> finalizar() {
        List<String> r = resultado();
        reset();
        return r;
    }

}