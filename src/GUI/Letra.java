import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 * Representa uma letra utilizada para identificar os ambientes no mapa.
 */
public class Letra extends ElementoMapa {

    /**
     * Cria uma letra na posição informada.
     *
     * @param posicao posição da letra no mapa
     * @param letra caractere que será exibido
     */
    public Letra(Posicao posicao, char letra) {
        super(posicao, criarImagem(letra));
    }

    /**
     * Desenha o caractere em uma imagem que pode ser exibida na tela.
     *
     * @param letra caractere que será desenhado
     * @return imagem contendo o caractere
     */
    private static Image criarImagem(char letra) {
        int largura = 40;
        int altura = 40;

        BufferedImage imagem = new BufferedImage(
                largura,
                altura,
                BufferedImage.TYPE_INT_ARGB);

        Graphics2D g = imagem.createGraphics();
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.setColor(Color.BLACK);
        g.drawString(String.valueOf(letra), 5, 30);
        g.dispose();

        return imagem;
    }

    /**
     * Converte um caractere em uma imagem.
     * Mantém disponível o método utilizado na versão original da classe.
     *
     * @param letra caractere que será convertido
     * @return imagem correspondente ao caractere
     */
    public Image charParaImagem(char letra) {
        return criarImagem(letra);
    }
}