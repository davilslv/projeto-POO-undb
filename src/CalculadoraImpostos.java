//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
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
        switch (i) {
            case 1: return Origem.CHINA;
            case 2: return Origem.UE;
            case 3: return Origem.JAPAO;
            case 4: return Origem.COREIA;
            case 5: return Origem.VIETNA;
            case 6: return Origem.TAILANDIA;
            case 7: return Origem.CANADA;
            case 8: return Origem.MEXICO;
            case 10: return Origem.BRASIL;
            default: return Origem.OUTROS;
        }
    }

    private static Categoria intToCategoria(int i) {
        switch (i) {
            case 1: return Categoria.GERAL;
            case 2: return Categoria.FARMACOS;
            case 3: return Categoria.SEMICONDUTORES;
            case 4: return Categoria.COBRE;
            case 5: return Categoria.MADEIRA;
            case 6: return Categoria.ENERGIA_MINERAIS;
            case 7: return Categoria.BULLION;
            default: return Categoria.GERAL;
        }
    }

    // Isso aqui é pra formatar o texto
    private static String origemToString(Origem origem) {
        switch (origem) {
            case CHINA: return "China";
            case UE: return "União Europeia";
            case JAPAO: return "Japão";
            case COREIA: return "Coreia do Sul";
            case VIETNA: return "Vietnã";
            case TAILANDIA: return "Tailândia";
            case CANADA: return "Canadá";
            case MEXICO: return "México";
            case BRASIL: return "Brasil";
            default: return "Outros";
        }
    }

    private static String categoriaToString(Categoria categoria) {
        switch (categoria) {
            case GERAL: return "Geral";
            case FARMACOS: return "Fármacos";
            case SEMICONDUTORES: return "Semicondutores";
            case COBRE: return "Cobre";
            case MADEIRA: return "Madeira";
            case ENERGIA_MINERAIS: return "Energia/Minerais";
            case BULLION: return "Bullion";
            default: return "Geral";
        }
    }
}
