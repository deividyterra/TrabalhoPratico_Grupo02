import java.util.List;

/**
 * Representa o cenário da simulação e armazena jogadores, paredes e letras
 * em matrizes separadas.
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 */
public class Mapa {
    /** Matriz que registra a posição dos jogadores no mapa. */
    private Jogador[][] jogador;
    /** Matriz que registra as paredes que bloqueiam a passagem. */
    private Parede[][] parede;
    /** Matriz que registra as letras que identificam os ambientes. */
    private Letra[][] letreiro;
    /** Quantidade de posições na horizontal do mapa. */
    private int largura;
    /** Quantidade de posições na vertical do mapa. */
    private int altura;
    
    /** Largura utilizada quando o mapa é criado sem dimensões informadas. */
    private static final int LARGURA_PADRAO = 20;
    /** Altura utilizada quando o mapa é criado sem dimensões informadas. */
    private static final int ALTURA_PADRAO = 17;
    
    /**
     * Cria mapa para alocar itens da simulacao.
     * @param largura: largura da área de simulacao.
     * @param altura: altura da área de simulação.
     */
    public Mapa(int largura, int altura) {
        this.largura = largura;
        this.altura = altura;
        jogador = new Jogador[largura][altura];
        parede = new Parede[largura][altura];
        letreiro = new Letra[largura][altura];
        adicionarParede();
        adicionarLetreiro();
    }

    /**
     * Cria e posiciona as letras que identificam os cômodos e o corredor.
     * As coordenadas e os caracteres definem o letreiro apresentado no mapa.
     */
    private void adicionarLetreiro() {
        // Adiciona letras do letreiro
        Posicao[] posicoes = {
            //QUARTO 1
            new Posicao(1, 2), new Posicao(2, 2), new Posicao(3, 2),
            new Posicao(4, 2), new Posicao(5, 2), new Posicao(6, 2),
            new Posicao(7, 2),
            //QUARTO 2
            new Posicao(12, 2), new Posicao(13, 2), new Posicao(14, 2),
            new Posicao(15, 2), new Posicao(16, 2), new Posicao(17, 2),
            new Posicao(18, 2),
            //WC 1
            new Posicao(2, 6), new Posicao(3, 6),
            new Posicao(4, 6), 
            //WC 2
            new Posicao(14, 6),
            new Posicao(15, 6), new Posicao(16, 6),
            //SALA JANTAR
            new Posicao(2, 9), new Posicao(3, 9), new Posicao(4, 9), new Posicao(5, 9),
            new Posicao(1, 10), new Posicao(2, 10), new Posicao(3, 10),
            new Posicao(4, 10), new Posicao(5, 10), new Posicao(6, 10),
            // COZINHA
            new Posicao(12, 10), new Posicao(13, 10), new Posicao(14, 10),
            new Posicao(15, 10), new Posicao(16, 10), new Posicao(17, 10),
            new Posicao(18, 10),
            //SALA TV
            new Posicao(1, 14), new Posicao(2, 14), new Posicao(3, 14), new Posicao(4, 14), 
            new Posicao(6, 14), new Posicao(7, 14),
            //AREA SERVIÇO
            new Posicao(14, 13), new Posicao(15, 13), new Posicao(16, 13), new Posicao(17, 13),
            new Posicao(12, 14), new Posicao(13, 14), new Posicao(14, 14), new Posicao(15, 14),new Posicao(16, 14),new Posicao(17, 14),new Posicao(18, 14),
            //CORREDOR
            new Posicao(9, 2),new Posicao(9, 3),new Posicao(9, 4), new Posicao(9, 5), new Posicao(9, 6), new Posicao(9, 7), new Posicao(9, 8),new Posicao(9, 9)

        };

        char[] letras = {'Q', 'U', 'A', 'R', 'T', 'O', '1','Q', 'U', 'A', 'R', 'T', 'O', '2','W','C','1','W','C','2','S','A','L','A','J', 'A', 'N', 'T', 'A', 'R', 'C', 'O', 'Z', 'I', 'N', 'H', 'A', 'S', 'A', 'L', 'A','T','V','A','R','E','A','S','E','R','V','I','Ç','O','C', 'O', 'R', 'R', 'E', 'D', 'O', 'R'};

        for (int i = 0; i < posicoes.length; i++) {
            letreiro[posicoes[i].getX()][posicoes[i].getY()] = new Letra(posicoes[i], letras[i]);
        }
    }

    /**
     * Cria e posiciona todas as paredes que formam os limites e divisões
     * do cenário.
     */
    private void adicionarParede() {
        // Adiciona paredes
        Posicao[] posicoes2 = {
            new Posicao(0,0), new Posicao(1,0), new Posicao(2,0), new Posicao(3,0),
            new Posicao(4,0), new Posicao(5,0), new Posicao(6,0), new Posicao(7,0),
            new Posicao(8,0), new Posicao(9,0), new Posicao(10,0), new Posicao(11,0),
            new Posicao(12,0), new Posicao(13,0), new Posicao(14,0), new Posicao(15,0),
            new Posicao(16,0), new Posicao(17,0), new Posicao(18,0), new Posicao(19,0),

            new Posicao(0,1), new Posicao(7,1), new Posicao(12,1), new Posicao(19,1),

            new Posicao(0,2), new Posicao(19,2),

            new Posicao(0,3), new Posicao(7,3), new Posicao(12,3), new Posicao(19,3),

            new Posicao(0,4), new Posicao(1,4), new Posicao(2,4), new Posicao(3,4),
            new Posicao(4,4), new Posicao(5,4), new Posicao(6,4), new Posicao(7,4),
            new Posicao(12,4), new Posicao(13,4), new Posicao(14,4), new Posicao(15,4),
            new Posicao(16,4), new Posicao(17,4), new Posicao(18,4), new Posicao(19,4),

            new Posicao(0,5), new Posicao(7,5), new Posicao(12,5), new Posicao(19,5),

            new Posicao(0,6), new Posicao(19,6),

            new Posicao(0,7), new Posicao(7,7), new Posicao(12,7), new Posicao(19,7),

            new Posicao(0,8), new Posicao(1,8), new Posicao(2,8), new Posicao(3,8),
            new Posicao(4,8), new Posicao(5,8), new Posicao(6,8), new Posicao(7,8),
            new Posicao(12,8), new Posicao(13,8), new Posicao(14,8),
            new Posicao(15,8), new Posicao(16,8), new Posicao(17,8),
            new Posicao(18,8), new Posicao(19,8),

            new Posicao(0,9), new Posicao(7,9), new Posicao(12,9), new Posicao(19,9),

            new Posicao(0,10), new Posicao(19,10),

            new Posicao(0,11), new Posicao(7,11), new Posicao(12,11), new Posicao(19,11),

            new Posicao(0,12), new Posicao(1,12),
            new Posicao(7,12), new Posicao(8,12),
            new Posicao(9,12), new Posicao(10,12), new Posicao(11,12),
            new Posicao(12,12), new Posicao(18,12), new Posicao(19,12),

            new Posicao(0,13), new Posicao(9,13), new Posicao(19,13),

            new Posicao(0,14), new Posicao(9,14),new Posicao(19,14),

            new Posicao(0,15), new Posicao(9,15), new Posicao(19,15),

            new Posicao(0,16), new Posicao(1,16), new Posicao(2,16),
            new Posicao(3,16), new Posicao(4,16), new Posicao(5,16),
            new Posicao(6,16), new Posicao(7,16), new Posicao(8,16),
            new Posicao(9,16), new Posicao(10,16), new Posicao(11,16),
            new Posicao(12,16), new Posicao(13,16), new Posicao(14,16),
            new Posicao(15,16), new Posicao(16,16), new Posicao(17,16),
            new Posicao(18,16), new Posicao(19,16)
        };

        for (Posicao posicao : posicoes2) {
            System.out.println("Adicionando parede em: " + posicao.getX() + ", " + posicao.getY());
            parede[posicao.getX()][posicao.getY()] = new Parede(posicao);
        }
    }
    /**
     * Cria mapa com tamanho padrao.
     */
    /**
     * Cria um mapa utilizando a largura e a altura padrão.
     */
    public Mapa(){
        this(LARGURA_PADRAO,ALTURA_PADRAO);
    }
    
    /**
     * Registra o jogador na matriz correspondente à sua posição atual.
     *
     * @param j jogador que será adicionado ao mapa
     */
    public void adicionarItem(Jogador j){
        jogador[j.getPosicaoOrigem().getX()][j.getPosicaoOrigem().getY()] = j;
    }
    
    /**
     * Remove o jogador da posição que ele ocupa atualmente na matriz.
     *
     * @param j jogador que será removido do mapa
     */
    public void removerItem(Jogador j){
        jogador[j.getPosicaoOrigem().getX()][j.getPosicaoOrigem().getY()] = null;
    }
    
    /**
     * Atualiza o registro do jogador no mapa, removendo sua posição anterior
     * e adicionando-o novamente na posição atual.
     *
     * @param j jogador cuja posição será atualizada
     */
    public void atualizarMapa(Jogador j){
        removerItem(j);
        adicionarItem(j);
    }
    
    /**
     * Retorna o jogador registrado em uma coordenada.
     *
     * @param x coordenada horizontal
     * @param y coordenada vertical
     * @return jogador na posição indicada ou {@code null} se não houver jogador
     */
    public Jogador getJogador(int x, int y){
        return jogador[x][y];
    }

    /**
     * Retorna a parede registrada em uma coordenada.
     *
     * @param x coordenada horizontal
     * @param y coordenada vertical
     * @return parede na posição indicada ou {@code null} se não houver parede
     */
    public Parede getParede(int x, int y){
        return parede[x][y];
    }

    /**
     * Retorna a letra registrada em uma coordenada.
     *
     * @param x coordenada horizontal
     * @param y coordenada vertical
     * @return letra na posição indicada ou {@code null} se não houver letra
     */
    public Letra getLetra(int x, int y){
        return letreiro[x][y];
    }

    /**
     * Retorna a largura do mapa.
     *
     * @return número de posições na horizontal
     */
    public int getLargura() {
        return largura;
    }

    /**
     * Retorna a altura do mapa.
     *
     * @return número de posições na vertical
     */
    public int getAltura() {
        return altura;
    }

    /**
     * Verifica se uma coordenada está dentro do mapa e não contém uma parede.
     *
     * @param x coordenada horizontal a verificar
     * @param y coordenada vertical a verificar
     * @return {@code true} se for possível passar pela posição; caso contrário,
     *         {@code false}
     */
    public boolean podePassar(int x, int y) {
        return estaDentro(x, y) && !temParede(x, y);
    }

    /**
     * Verifica se existe uma parede na coordenada informada.
     * Este método pressupõe que a coordenada já foi validada.
     *
     * @param x coordenada horizontal
     * @param y coordenada vertical
     * @return {@code true} se houver uma parede na posição
     */
    private boolean temParede(int x, int y) {
        return parede[x][y] != null;
    }
    /**
     * Verifica se uma coordenada pertence aos limites do mapa.
     *
     * @param x coordenada horizontal
     * @param y coordenada vertical
     * @return {@code true} se a coordenada estiver dentro do mapa
     */
    public boolean estaDentro(int x, int y) {
        return x >= 0 && x < largura
            && y >= 0 && y < altura;
    }

    /**
     * Solicita ao algoritmo A* um caminho entre duas posições do mapa.
     *
     * @param origem posição inicial do caminho
     * @param destino posição final desejada
     * @return lista de posições que formam o caminho, ou uma lista vazia caso
     *         não seja encontrado um caminho
     */
    public List<Posicao> encontrarCaminho(
            Posicao origem,
            Posicao destino) {

        HeuristicaAEstrela heuristica =
            new HeuristicaAEstrela(this);

        return heuristica.encontrarCaminho(
            origem,
            destino
        );
    }
    
}
