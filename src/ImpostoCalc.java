public class ImpostoCalc {

    public static double calcularAliquota(Produto produto) {
        var origem = produto.getOrigem();
        var categoria = produto.getCategoria();
        boolean usmca = produto.isUsmca();

        // Negócio do USMCA
        if (origem == CalculadoraImpostos.Origem.CANADA || origem == CalculadoraImpostos.Origem.MEXICO) {
            if (usmca) return 0.0;
            return (categoria == CalculadoraImpostos.Categoria.ENERGIA_MINERAIS) ? 0.10 : 0.25;
        }

        // Categorias especiais - sempre 10%
        if (categoria != CalculadoraImpostos.Categoria.GERAL) {
            return 0.10;
        }

        // Categoria Geral - tabela por país
        return switch (origem) {
            case CHINA -> 0.34;
            case UE -> 0.20;
            case JAPAO -> 0.24;
            case COREIA -> 0.26;
            case VIETNA -> 0.46;
            case TAILANDIA -> 0.37;
            default -> 0.10; // Inclusive Brasil
        };
    }

    public static double calcularImposto(Produto produto) {
        double aliquota = calcularAliquota(produto);
        return produto.getCIF() * aliquota;
    }
}
