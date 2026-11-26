import java.util.Random;

/**
 * Representa uma coordenada (x, y) dentro do mapa.
 */
public class Posicao {
    /** Coordenada horizontal da posição. */
    private int x;
    /** Coordenada vertical da posição. */
    private int y;
    /** Gerador aleatório usado para escolher entre movimentos possíveis. */
    private static Random rand = new Random();

    /**
     * Cria uma posição a partir das coordenadas informadas.
     *
     * @param x coordenada horizontal
     * @param y coordenada vertical
     */
    public Posicao(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Retorna a coordenada horizontal.
     *
     * @return valor da coordenada x
     */
    public int getX() {
        return x;
    }

    /**
     * Retorna a coordenada vertical.
     *
     * @return valor da coordenada y
     */
    public int getY() {
        return y;
    }

    /**
     * Calcula uma posição vizinha que aproxima a posição atual do destino.
     * Quando há deslocamento horizontal e vertical possível, escolhe um deles
     * aleatoriamente.
     *
     * @param localizacaoDestino posição que se deseja alcançar
     * @return próxima posição escolhida em direção ao destino
     */
    public Posicao proximaLocalizacao(Posicao localizacaoDestino) {
        if (localizacaoDestino.equals(this)) {
            return localizacaoDestino;
        } else {
            int destX = localizacaoDestino.getX();
            int destY = localizacaoDestino.getY();
            int deslocX = x < destX ? 1 : x > destX ? -1 : 0;
            int deslocY = y < destY ? 1 : y > destY ? -1 : 0;
            Posicao novaLocalizacao;

            if (deslocX != 0 && deslocY != 0) {
                if (rand.nextInt(2) == 0) {
                    novaLocalizacao = new Posicao(x + deslocX, y);
                } else {
                    novaLocalizacao = new Posicao(x, y + deslocY);
                }
            } else {
                if (deslocX != 0) {
                    novaLocalizacao = new Posicao(x + deslocX, y);
                } else {
                    novaLocalizacao = new Posicao(x, y + deslocY);
                }
            }
            return novaLocalizacao;
        }
    }

    /**
     * Verifica se outra posição possui as mesmas coordenadas x e y.
     *
     * @param obj objeto que será comparado com esta posição
     * @return {@code true} se as coordenadas forem iguais; caso contrário,
     *         {@code false}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (!(obj instanceof Posicao)) {
            return false;
        } else {
            Posicao outra = (Posicao) obj;
            return x == outra.x && y == outra.y;
        }
    }

    /**
     * Retorna um código associado às coordenadas da posição.
     * O cálculo é compatível com o método {@link #equals(Object)}.
     *
     * @return código calculado a partir das coordenadas x e y
     */
    @Override
    public int hashCode() {
        return 31 * x + y;
    }

    /**
     * Retorna as coordenadas da posição em formato de texto.
     *
     * @return coordenadas no formato {@code (x, y)}
     */
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}