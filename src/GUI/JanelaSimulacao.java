import java.awt.*;
import javax.swing.*;

/**
 * Janela responsável por apresentar graficamente o mapa e os elementos
 * presentes nele.
 *
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 */
public class JanelaSimulacao extends JFrame {
    /** Mapa cujos elementos serão desenhados na janela. */
    private Mapa mapa;
    /** Componente responsável por desenhar a grade e as imagens do mapa. */
    private VisaoMapa visaoMapa;

    /**
     * Cria a janela de visualização associada ao mapa informado.
     *
     * @param mapa mapa que será exibido
     */
    public JanelaSimulacao(Mapa mapa) {
        this.mapa = mapa;
        visaoMapa = new VisaoMapa(mapa.getLargura(), mapa.getAltura());
        getContentPane().add(visaoMapa);
        setTitle("World of Zuul");
        setSize(500, 500);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * Mostra o estado atual do mapa.
     */
    /**
     * Atualiza o desenho da janela de acordo com os elementos registrados
     * atualmente no mapa.
     */
    public void executarAcao() {
        visaoMapa.preparePaint();

        for (int i = 0; i < mapa.getLargura(); i++) {
            for (int j = 0; j < mapa.getAltura(); j++) {
                if (mapa.getJogador(i, j) != null) {
                    Jogador jogador = mapa.getJogador(i, j);
                    Posicao localizacao = jogador.getPosicaoOrigem();
                    visaoMapa.desenharImagem(
                            localizacao.getX(),
                            localizacao.getY(),
                            jogador.getImagem());
                } else if (mapa.getParede(i, j) != null) {
                    Parede parede = mapa.getParede(i, j);
                    Posicao localizacao = parede.getPosicaoOrigem();
                    visaoMapa.desenharImagem(
                            localizacao.getX(),
                            localizacao.getY(),
                            parede.getImagem());
                } else if (mapa.getLetra(i, j) != null) {
                    Letra letra = mapa.getLetra(i, j);
                    Posicao localizacao = letra.getPosicaoOrigem();
                    visaoMapa.desenharImagem(
                            localizacao.getX(),
                            localizacao.getY(),
                            letra.getImagem());
                }
            }
        }

        visaoMapa.repaint();
    }

    /**
     * Fornece uma visualizacao grafica do mapa.
     */
    /**
     * Painel interno que desenha a grade do mapa e as imagens dos elementos.
     */
    private class VisaoMapa extends JPanel {

        /** Fator mínimo usado para definir o tamanho visual das células. */
        private final int VIEW_SCALING_FACTOR = 6;
        /** Dimensões do mapa em número de células. */
        private int larguraMapa, alturaMapa;
        /** Tamanho, em pixels, de cada célula nos eixos horizontal e vertical. */
        private int xScale, yScale;
        /** Último tamanho do painel usado para preparar a imagem do mapa. */
        private Dimension tamanho;
        /** Contexto gráfico usado para desenhar na imagem intermediária. */
        private Graphics g;
        /** Imagem intermediária que contém a grade e os elementos desenhados. */
        private Image imagemMapa;

        /**
         * Cria o painel com as dimensões lógicas do mapa.
         *
         * @param largura largura do mapa em células
         * @param altura altura do mapa em células
         */
        public VisaoMapa(int largura, int altura) {
            larguraMapa = largura;
            alturaMapa = altura;
            setBackground(Color.white);
            tamanho = new Dimension(0, 0);
        }

        /**
         * Calcula o tamanho preferencial do painel com base nas dimensões
         * do mapa e no fator de escala.
         *
         * @return tamanho preferencial do painel em pixels
         */
        public Dimension getPreferredSize() {
            return new Dimension(
                    larguraMapa * VIEW_SCALING_FACTOR,
                    alturaMapa * VIEW_SCALING_FACTOR);
        }

        /**
         * Prepara a imagem intermediária, limpa o desenho anterior e desenha
         * a grade que representa as células do mapa.
         */
        public void preparePaint() {
            if (!tamanho.equals(getSize())) {
                tamanho = getSize();
                imagemMapa = visaoMapa.createImage(
                        tamanho.width,
                        tamanho.height);
                g = imagemMapa.getGraphics();

                xScale = tamanho.width / larguraMapa;
                if (xScale < 1) {
                    xScale = VIEW_SCALING_FACTOR;
                }

                yScale = tamanho.height / alturaMapa;
                if (yScale < 1) {
                    yScale = VIEW_SCALING_FACTOR;
                }
            }

            g.setColor(Color.white);
            g.fillRect(0, 0, tamanho.width, tamanho.height);
            g.setColor(Color.gray);

            for (int i = 0, x = 0; x < tamanho.width; i++, x = i * xScale) {
                g.drawLine(x, 0, x, tamanho.height - 1);
            }

            for (int i = 0, y = 0; y < tamanho.height; i++, y = i * yScale) {
                g.drawLine(0, y, tamanho.width - 1, y);
            }
        }

        /**
         * Desenha uma imagem na célula correspondente às coordenadas informadas.
         *
         * @param x coordenada horizontal da célula
         * @param y coordenada vertical da célula
         * @param image imagem que será desenhada
         */
        public void desenharImagem(int x, int y, Image image) {
            g.drawImage(
                    image,
                    x * xScale + 1,
                    y * yScale + 1,
                    xScale - 1,
                    yScale - 1,
                    this);
        }

        /**
         * Desenha a imagem intermediária na superfície do painel.
         *
         * @param g contexto gráfico fornecido pelo Swing para a pintura
         */
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            if (imagemMapa != null) {
                g.drawImage(imagemMapa, 0, 0, null);
            }
        }
    }
}