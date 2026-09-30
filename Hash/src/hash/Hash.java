/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package hash;

/**
 *
 * @author Windows 10
 */
import java.util.Scanner;

public class Hash {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       Scanner scanner = new Scanner(System.in);

        Estoque estoque = new Estoque();

        Fatura fatura = new Fatura();

        // Produtos iniciais
        Produto produto1 = new Produto(
                "Arroz 5kg",
                1,
                25.90,
                10
        );

        Produto produto2 = new Produto(
                "Feijão 1kg",
                2,
                8.50,
                8
        );

        Produto produto3 = new Produto(
                "Macarrão 500g",
                3,
                5.99,
                3
        );

        estoque.adicionarProduto(produto1);
        estoque.adicionarProduto(produto2);
        estoque.adicionarProduto(produto3);

        int opcao;

        do {

            System.out.println("\n===== SISTEMA DA LOJA =====");
            System.out.println("1 - Comprar");
            System.out.println("2 - Ver Fatura");
            System.out.println("3 - Excluir item");
            System.out.println("4 - Alterar item");
            System.out.println("5 - Consultar Produto");
            System.out.println("6 - Adicionar Produto ao Estoque");
            System.out.println("7 - Remover Produto");
            System.out.println("8 - Repor Estoque");
            System.out.println("9 - Produtos com Estoque Baixo");
            System.out.println("10 - Finalizar");

            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();

            switch (opcao) {

                case 1:

                    comprar(scanner, estoque, fatura);

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

                    consultarProduto(scanner, estoque);

                    break;

                case 6:

                    adicionarProduto(scanner, estoque);

                    break;

                case 7:

                    removerProduto(scanner, estoque);

                    break;

                case 8:

                    reporEstoque(scanner, estoque);

                    break;

                case 9:

                    estoque.produtosEstoqueBaixo();

                    break;

                case 10:

                    System.out.println("\nCompra finalizada!");

                    System.out.printf(
                            "Valor final: R$ %.2f%n",
                            fatura.calcularTotal()
                    );

                    break;

                default:

                    System.out.println("Opção inválida!");
            }

        } while (opcao != 10);

        scanner.close();
    }

    public static void comprar(
            Scanner scanner,
            Estoque estoque,
            Fatura fatura) {

        estoque.listarProdutos();

        System.out.println("0 - Voltar");

        System.out.print("Digite o código do produto: ");

        int codigo = scanner.nextInt();

        if (codigo == 0) {
            return;
        }

        Produto produto = estoque.buscarProduto(codigo);

        if (produto == null) {

            System.out.println("Produto não encontrado!");

            return;
        }

        System.out.println(
                "Produto selecionado: "
                + produto.getNome()
        );

        System.out.println(
                "Estoque disponível: "
                + produto.getQuantidadeEstoque()
        );

        System.out.print("Digite a quantidade: ");

        int quantidade = scanner.nextInt();

        if (quantidade <= 0) {

            System.out.println("Quantidade inválida!");

            return;
        }

        if (!produto.retirarEstoque(quantidade)) {

            System.out.println("Quantidade insuficiente em estoque!");

            return;
        }

        Item item = new Item(produto, quantidade);

        fatura.adicionarItem(item);

        System.out.println("Compra realizada com sucesso!");
    }

    public static void excluirItem(
            Scanner scanner,
            Fatura fatura) {

        if (fatura.getItens().isEmpty()) {

            System.out.println("A fatura está vazia.");

            return;
        }

        fatura.exibirFatura();

        System.out.println("0 - Voltar");

        System.out.print("Escolha o item que deseja excluir: ");

        int escolha = scanner.nextInt();

        if (escolha == 0) {
            return;
        }

        if (escolha >= 1
                && escolha <= fatura.getItens().size()) {

            fatura.removerItem(escolha - 1);

            System.out.println("Item excluído!");

        } else {

            System.out.println("Item inválido!");
        }
    }

    public static void alterarItem(
            Scanner scanner,
            Fatura fatura) {

        if (fatura.getItens().isEmpty()) {

            System.out.println("A fatura está vazia.");

            return;
        }

        fatura.exibirFatura();

        System.out.println("0 - Voltar");

        System.out.print("Escolha o item: ");

        int escolha = scanner.nextInt();

        if (escolha == 0) {
            return;
        }

        if (escolha >= 1
                && escolha <= fatura.getItens().size()) {

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

            System.out.println("Item alterado!");

        } else {

            System.out.println("Item inválido!");
        }
    }

    public static void consultarProduto(
            Scanner scanner,
            Estoque estoque) {

        System.out.println("\n===== CONSULTAR PRODUTO =====");

        System.out.println("0 - Voltar");

        System.out.print("Digite o código: ");

        int codigo = scanner.nextInt();

        if (codigo == 0) {
            return;
        }

        Produto produto = estoque.buscarProduto(codigo);

        if (produto == null) {

            System.out.println("Produto não encontrado!");

        } else {

            produto.exibirProduto();
        }
    }

    public static void adicionarProduto(
            Scanner scanner,
            Estoque estoque) {

        System.out.println("\n===== ADICIONAR PRODUTO =====");

        System.out.println("0 - Voltar");

        System.out.print("Digite o código: ");

        int codigo = scanner.nextInt();

        if (codigo == 0) {
            return;
        }

        if (estoque.verificarExistencia(codigo)) {

            System.out.println(
                    "Já existe um produto com esse código!"
            );

            return;
        }

        scanner.nextLine();

        System.out.print("Digite o nome: ");

        String nome = scanner.nextLine();

        System.out.print("Digite o preço: ");

        double preco = scanner.nextDouble();

        System.out.print("Digite a quantidade inicial: ");

        int quantidade = scanner.nextInt();

        if (quantidade < 0) {

            System.out.println("Quantidade inválida!");

            return;
        }

        Produto produto = new Produto(
                nome,
                codigo,
                preco,
                quantidade
        );

        if (estoque.adicionarProduto(produto)) {

            System.out.println(
                    "Produto adicionado com sucesso!"
            );

        } else {

            System.out.println(
                    "Não foi possível adicionar."
            );
        }
    }

    public static void removerProduto(
            Scanner scanner,
            Estoque estoque) {

        System.out.println("\n===== REMOVER PRODUTO =====");

        System.out.println("0 - Voltar");

        System.out.print("Digite o código: ");

        int codigo = scanner.nextInt();

        if (codigo == 0) {
            return;
        }

        if (estoque.removerProduto(codigo)) {

            System.out.println(
                    "Produto removido com sucesso!"
            );

        } else {

            System.out.println(
                    "Produto não encontrado!"
            );
        }
    }

    public static void reporEstoque(
            Scanner scanner,
            Estoque estoque) {

        System.out.println("\n===== REPOR ESTOQUE =====");

        System.out.println("0 - Voltar");

        System.out.print("Digite o código do produto: ");

        int codigo = scanner.nextInt();

        if (codigo == 0) {
            return;
        }

        Produto produto = estoque.buscarProduto(codigo);

        if (produto == null) {

            System.out.println(
                    "Produto não encontrado!"
            );

            return;
        }

        System.out.print(
                "Digite a quantidade a adicionar: "
        );

        int quantidade = scanner.nextInt();

        if (quantidade <= 0) {

            System.out.println(
                    "Quantidade inválida!"
            );

            return;
        }

        produto.adicionarEstoque(quantidade);

        System.out.println(
                "Estoque atualizado!"
        );

        System.out.println(
                "Novo estoque: "
                + produto.getQuantidadeEstoque()
        );
    }
    }
    

