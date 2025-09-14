//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

/*
Alunos: Davi Lemos da Silva, André Correa Martins

PS.: Professor, acabei nao me tocando e fiz pelo intellij, espero que nao seja problema e por isso estou
enviando o arquivo compactado para o senhor imporatar para o netbeans sem problemas :D
 */

import java.util.Scanner;

public class CalculadoraImpostos {

    // Enums principais
    enum Origem {
        CHINA, UE, JAPAO, COREIA, VIETNA, TAILANDIA, CANADA, MEXICO, OUTROS, BRASIL
    }

    enum Categoria {
        GERAL, FARMACOS, SEMICONDUTORES, COBRE, MADEIRA, ENERGIA_MINERAIS, BULLION
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nome do Produto: ");
        String descricao = sc.nextLine();

        System.out.print("Origem (1-China, 2-UE, 3-Japão, 4-Coreia, 5-Vietnã, 6-Tailândia, 7-Canadá, 8-México, 9-Outros, 10-Brasil): ");
        Origem origem = intToOrigem(sc.nextInt());

        System.out.print("Categoria (1-Geral, 2-Fármacos, 3-Semicondutores, 4-Cobre, 5-Madeira, 6-Energia/Minerais, 7-Bullion): ");
        Categoria categoria = intToCategoria(sc.nextInt());

        System.out.print("Valor da Mercadoria (USD): ");
        double valorMercadoria = sc.nextDouble();

        System.out.print("Valor do Frete (USD): ");
        double valorFrete = sc.nextDouble();

        boolean usmca = false;
        if (origem == Origem.CANADA || origem == Origem.MEXICO) {
            System.out.print("Vem do Canadá ou México? (true/false): ");
            usmca = sc.nextBoolean();
        }

        sc.close();

        // Criar Produto - objeto
        Produto produto = new Produto(descricao, origem, categoria, valorMercadoria, valorFrete, usmca);

        // Calcular imposto
        double aliquota = ImpostoCalc.calcularAliquota(produto);
        double imposto = ImpostoCalc.calcularImposto(produto);

        // Saída final
        System.out.println("\n==== Resultado ====");
        System.out.println("Produto: " + produto.getDescricao());
        System.out.println("Origem: " + origemToString(origem));
        System.out.println("Categoria: " + categoriaToString(categoria));
        System.out.printf("CIF (USD): %.2f%n", produto.getCIF());
        System.out.printf("Alíquota aplicada: %.0f%%%n", aliquota * 100);
        System.out.printf("Imposto (USD): %.2f%n", imposto);
    }

    // Conversores pra enum
    private static Origem intToOrigem(int i) {
        return switch (i) {
            case 1 -> Origem.CHINA;
            case 2 -> Origem.UE;
            case 3 -> Origem.JAPAO;
            case 4 -> Origem.COREIA;
            case 5 -> Origem.VIETNA;
            case 6 -> Origem.TAILANDIA;
            case 7 -> Origem.CANADA;
            case 8 -> Origem.MEXICO;
            case 10 -> Origem.BRASIL;
            default -> Origem.OUTROS;
        };
    }

    private static Categoria intToCategoria(int i) {
        return switch (i) {
            case 2 -> Categoria.FARMACOS;
            case 3 -> Categoria.SEMICONDUTORES;
            case 4 -> Categoria.COBRE;
            case 5 -> Categoria.MADEIRA;
            case 6 -> Categoria.ENERGIA_MINERAIS;
            case 7 -> Categoria.BULLION;
            default -> Categoria.GERAL;
        };
    }

    // Isso aqui é pra formatar o texto
    private static String origemToString(Origem origem) {
        return switch (origem) {
            case CHINA -> "China";
            case UE -> "União Europeia";
            case JAPAO -> "Japão";
            case COREIA -> "Coreia do Sul";
            case VIETNA -> "Vietnã";
            case TAILANDIA -> "Tailândia";
            case CANADA -> "Canadá";
            case MEXICO -> "México";
            case BRASIL -> "Brasil";
            default -> "Outros";
        };
    }

    private static String categoriaToString(Categoria categoria) {
        return switch (categoria) {
            case FARMACOS -> "Fármacos";
            case SEMICONDUTORES -> "Semicondutores";
            case COBRE -> "Cobre";
            case MADEIRA -> "Madeira";
            case ENERGIA_MINERAIS -> "Energia/Minerais";
            case BULLION -> "Bullion";
            default -> "Geral";
        };
    }
}
