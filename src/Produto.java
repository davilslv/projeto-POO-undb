public class Produto {
    private String descricao;
    private CalculadoraImpostos.Origem origem;
    private CalculadoraImpostos.Categoria categoria;
    private double valorMercadoria;
    private double valorFrete;
    private boolean usmca;

    public Produto(String descricao, CalculadoraImpostos.Origem origem,
                   CalculadoraImpostos.Categoria categoria,
                   double valorMercadoria, double valorFrete,
                   boolean usmca) {
        this.descricao = descricao;
        this.origem = origem;
        this.categoria = categoria;
        this.valorMercadoria = valorMercadoria;
        this.valorFrete = valorFrete;
        this.usmca = usmca;
    }

    public String getDescricao() { return descricao; }
    public CalculadoraImpostos.Origem getOrigem() { return origem; }
    public CalculadoraImpostos.Categoria getCategoria() { return categoria; }
    public double getValorMercadoria() { return valorMercadoria; }
    public double getValorFrete() { return valorFrete; }
    public boolean isUsmca() { return usmca; }

    public double getCIF() {
        return valorMercadoria + valorFrete;
    }
}
