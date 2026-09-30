/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author Windows 10
 */
import java.util.Scanner;
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

        // Produtos cadastrados
        Produto produto1 = new Produto("Arroz 5kg", 1, 25.90);
        Produto produto2 = new Produto("Feijão 1kg", 2, 8.50);
        Produto produto3 = new Produto("Macarrão 500g", 3, 5.99);

        Fatura fatura = new Fatura();

        int opcao;

        do {

            System.out.println("\n===== CARRINHO DE COMPRAS =====");
            System.out.println("1 - Comprar");
            System.out.println("2 - Ver Fatura");
            System.out.println("3 - Excluir item");
            System.out.println("4 - Alterar item");
            System.out.println("5 - Finalizar");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();

            switch (opcao) {

                case 1:
                    comprar(scanner, fatura, produto1, produto2, produto3);
                    break;

                case 2:
                    fatura.exibirFatura();
                    break;

                case 3:
                    excluirItem(scanner, fatura);
                    break;

                case 4:
                    alterarItem(scanner, fatura);
                    break;

                case 5:
                    System.out.println("\nCompra finalizada!");
                    System.out.printf(
                            "Valor final: R$ %.2f%n",
                            fatura.calcularTotal()
                    );
                    break;

                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 5);

        scanner.close();
    }

    public static void comprar(
            Scanner scanner,
            Fatura fatura,
            Produto produto1,
            Produto produto2,
            Produto produto3) {

        int codigo;
        int quantidade;

        do {

            System.out.println("\n========== PRODUTOS ==========");

            produto1.exibirProduto();
            produto2.exibirProduto();
            produto3.exibirProduto();

            System.out.println("0 - Voltar");

            System.out.print("Digite o código do produto: ");
            codigo = scanner.nextInt();

            if (codigo == 0) {
                return;
            }

            Produto produtoEscolhido = null;

            if (codigo == produto1.getCodigo()) {

                produtoEscolhido = produto1;

            } else if (codigo == produto2.getCodigo()) {

                produtoEscolhido = produto2;

            } else if (codigo == produto3.getCodigo()) {

                produtoEscolhido = produto3;
            }

            if (produtoEscolhido == null) {
                System.out.println("Código inválido!");
                continue;
            }

            System.out.print("Digite a quantidade: ");
            quantidade = scanner.nextInt();

            if (quantidade <= 0) {
                System.out.println("Quantidade inválida!");
                continue;
            }

            Item item = new Item(produtoEscolhido, quantidade);

            fatura.adicionarItem(item);

            System.out.println("Produto adicionado à fatura!");

            return;

        } while (true);
    }

    public static void excluirItem(
            Scanner scanner,
            Fatura fatura) {

        if (fatura.getItens().isEmpty()) {

            System.out.println("\nA fatura está vazia.");
            return;
        }

        System.out.println("\n========== EXCLUIR ITEM ==========");

        for (int i = 0; i < fatura.getItens().size(); i++) {

            System.out.print((i + 1) + " - ");

            fatura.getItens().get(i).exibirItem();
        }

        System.out.println("0 - Voltar");

        System.out.print("Escolha o item que deseja excluir: ");
        int escolha = scanner.nextInt();

        if (escolha == 0) {
            return;
        }

        if (escolha >= 1 && escolha <= fatura.getItens().size()) {

            fatura.removerItem(escolha - 1);

            System.out.println("Item excluído com sucesso!");

        } else {

            System.out.println("Item inválido!");
        }
    }

    public static void alterarItem(
            Scanner scanner,
            Fatura fatura) {

        if (fatura.getItens().isEmpty()) {

            System.out.println("\nA fatura está vazia.");
            return;
        }

        System.out.println("\n========== ALTERAR ITEM ==========");

        for (int i = 0; i < fatura.getItens().size(); i++) {

            System.out.print((i + 1) + " - ");

            fatura.getItens().get(i).exibirItem();
        }

        System.out.println("0 - Voltar");

        System.out.print("Escolha o item que deseja alterar: ");
        int escolha = scanner.nextInt();

        if (escolha == 0) {
            return;
        }

        if (escolha >= 1 && escolha <= fatura.getItens().size()) {

            System.out.print("Digite a nova quantidade: ");
            int novaQuantidade = scanner.nextInt();

            if (novaQuantidade <= 0) {

                System.out.println("Quantidade inválida!");
                return;
            }

            fatura.alterarItem(
                    escolha - 1,
                    novaQuantidade
            );

            System.out.println("Item alterado com sucesso!");

        } else {

            System.out.println("Item inválido!");
        }
    }
    
}
