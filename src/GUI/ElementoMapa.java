import java.awt.Image;

/**
 * Representa um elemento que possui uma posicao e uma imagem no mapa.
 */
public abstract class ElementoMapa {
    /** Posição atual do elemento no mapa. */
    private Posicao posicaoOrigem;
    /** Imagem utilizada para representar visualmente o elemento. */
    private Image imagem;

    /**
     * Cria um elemento do mapa com uma posição e uma imagem.
     *
     * @param posicao posição inicial do elemento no mapa
     * @param imagem imagem usada para desenhar o elemento
     */
    public ElementoMapa(Posicao posicao, Image imagem) {
        this.posicaoOrigem = posicao;
        this.imagem = imagem;
    }

    /**
     * Retorna a posição atual do elemento.
     *
     * @return posição atual do elemento
     */
    public Posicao getPosicaoOrigem() {
        return posicaoOrigem;
    }

    /**
     * Altera a posição atual do elemento.
     * Este método é protegido para permitir a atualização pelas subclasses.
     *
     * @param posicao nova posição do elemento
     */
    protected void setPosicaoOrigem(Posicao posicao) {
        this.posicaoOrigem = posicao;
    }

    /**
     * Retorna a imagem usada para representar o elemento.
     *
     * @return imagem do elemento
     */
    public Image getImagem() {
        return imagem;
    }
}