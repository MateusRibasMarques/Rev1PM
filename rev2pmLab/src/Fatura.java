/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Windows 10
 */
import java.util.ArrayList;

public class Fatura {

    private ArrayList<Item> itens;

    public Fatura() {
        itens = new ArrayList<>();
    }

    public ArrayList<Item> getItens() {
        return itens;
    }

    public void adicionarItem(Item item) {
        itens.add(item);
    }

    public void removerItem(int indice) {
        if (indice >= 0 && indice < itens.size()) {
            itens.remove(indice);
        }
    }

    public void alterarItem(int indice, int novaQuantidade) {
        if (indice >= 0 && indice < itens.size()) {
            itens.get(indice).alterarQuantidade(novaQuantidade);
        }
    }

    public double calcularTotal() {
        double total = 0;

        for (Item item : itens) {
            total += item.getValorTotal();
        }

        return total;
    }

    public void exibirFatura() {

        if (itens.isEmpty()) {
            System.out.println("\nA fatura está vazia.");
            return;
        }

        System.out.println("\n========== FATURA ==========");

        for (int i = 0; i < itens.size(); i++) {
            System.out.print((i + 1) + " - ");
            itens.get(i).exibirItem();
        }

        System.out.println("----------------------------");
        System.out.printf("VALOR TOTAL: R$ %.2f%n", calcularTotal());
        System.out.println("============================");
    }
}
