import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * Representa o jogador que se movimenta pelo mapa.
 */
public class Jogador extends ElementoMapa {
    /** Imagem utilizada para desenhar o jogador. */
    private static final Image IMAGEM = new ImageIcon(Jogador.class.getResource("/Imagens/pessoa.png")).getImage();

    /** Posição para a qual o jogador deve se deslocar. */
    private Posicao posicaoDestino;

    /**
     * Cria um jogador sem definir inicialmente uma posição de origem.
     */
    public Jogador() {
        super(null, IMAGEM);
        posicaoDestino = null;
    }

    /**
     * Cria um jogador na posição informada.
     *
     * @param localizacao posição inicial do jogador
     */
    public Jogador(Posicao localizacao) {
        super(localizacao, IMAGEM);
        posicaoDestino = null;
    }

    /**
     * Atualiza a posição atual do jogador.
     *
     * @param localizacaoAtual nova posição do jogador
     */
    public void setPosicaoOrigem(Posicao localizacaoAtual) {
        super.setPosicaoOrigem(localizacaoAtual);
    }

    /**
     * Retorna a posição de destino definida para o jogador.
     *
     * @return posição de destino, ou {@code null} caso não tenha sido definida
     */
    public Posicao getPosicaoDestino() {
        return posicaoDestino;
    }

    /**
     * Define a posição para a qual o jogador deverá se deslocar.
     *
     * @param localizacaoDestino posição de destino
     */
    public void setPosicaoDestino(Posicao localizacaoDestino) {
        this.posicaoDestino = localizacaoDestino;
    }

    /**
     * Atualiza a posição do jogador para a próxima posição do caminho,
     * desde que exista uma posição de destino definida.
     *
     * @param proximaLocalizacao próxima posição do jogador
     */
    public void executarAcao(Posicao proximaLocalizacao) {
        Posicao destino = getPosicaoDestino();
        if (destino != null) {
            setPosicaoOrigem(proximaLocalizacao);
        }
    }
}