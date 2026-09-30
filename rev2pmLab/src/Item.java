/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Windows 10
 */
public class Item {

    private Produto produto;
    private int quantidade;

    public Item(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValorTotal() {
        return produto.getPreco() * quantidade;
    }

    public void alterarQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void exibirItem() {
        System.out.printf(
                "%s | Quantidade: %d | Unitário: R$ %.2f | Total: R$ %.2f%n",
                produto.getNome(),
                quantidade,
                produto.getPreco(),
                getValorTotal()
        );
    }
}