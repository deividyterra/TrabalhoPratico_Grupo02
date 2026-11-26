import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * Representa uma parede fixa do cenário, que impede a passagem do jogador.
 */
public class Parede extends ElementoMapa {
    /** Imagem compartilhada por todas as paredes. */
    private static final Image IMAGEM = new ImageIcon(Parede.class.getResource("/Imagens/tijolo.png")).getImage();

    /**
     * Cria uma parede na posição informada.
     *
     * @param posicao posição onde a parede será colocada
     */
    public Parede(Posicao posicao) {
        super(posicao, IMAGEM);
    }
}