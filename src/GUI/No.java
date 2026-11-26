/**
 * Representa um ponto considerado pelo algoritmo A*, guardando sua posição,
 * o nó anterior no caminho e os custos usados para ordenar a busca.
 */
public class No implements Comparable<No> {
    /** Posição do mapa representada por este nó. */
    private Posicao posicao;
    /** Nó anterior que levou até esta posição. */
    private No pai;
    /** Custo do caminho percorrido desde a origem até este nó. */
    private int custoG;
    /** Estimativa da distância restante até o destino. */
    private int custoH;

    /**
     * Cria um nó com os dados utilizados pelo algoritmo de busca.
     *
     * @param posicao posição representada pelo nó
     * @param pai nó anterior no caminho, ou {@code null} para o nó inicial
     * @param custoG custo acumulado desde a origem
     * @param custoH estimativa do custo até o destino
     */
    public No(Posicao posicao, No pai, int custoG, int custoH) {
        this.posicao = posicao;
        this.pai = pai;
        this.custoG = custoG;
        this.custoH = custoH;
    }

    /**
     * Retorna a posição representada pelo nó.
     *
     * @return posição do nó
     */
    public Posicao getPosicao() {
        return posicao;
    }

    /**
     * Retorna o nó anterior no caminho.
     *
     * @return nó pai, ou {@code null} se este for o nó inicial
     */
    public No getPai() {
        return pai;
    }

    /**
     * Retorna o custo acumulado desde a origem.
     *
     * @return custo G
     */
    public int getCustoG() {
        return custoG;
    }

    /**
     * Retorna a estimativa de custo até o destino.
     *
     * @return custo H
     */
    public int getCustoH() {
        return custoH;
    }

    /**
     * Calcula a soma do custo percorrido com a estimativa até o destino.
     *
     * @return custo total F, calculado por G + H
     */
    public int getCustoF() {
        return custoG + custoH;
    }

    /**
     * Compara este nó com outro usando o custo total F.
     * Nós com menor custo total são considerados prioritários.
     *
     * @param outro nó que será comparado com este
     * @return valor negativo se este nó tiver menor custo, positivo se tiver
     *         maior custo ou zero se os custos forem iguais
     */
    @Override
    public int compareTo(No outro) {
        if (this.getCustoF() < outro.getCustoF()) {
            return -1;
        } else if (this.getCustoF() > outro.getCustoF()) {
            return 1;
        }
        return 0;
    }
}