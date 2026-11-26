import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Implementa o algoritmo A* para encontrar um caminho entre duas posições
 * transitáveis do mapa.
 */
public class HeuristicaAEstrela {
    /** Mapa usado para verificar quais posições podem ser atravessadas. */
    private Mapa mapa;

    /**
     * Cria o algoritmo de busca associado a um mapa.
     *
     * @param mapa mapa no qual o caminho será procurado
     */
    public HeuristicaAEstrela(Mapa mapa) {
        this.mapa = mapa;
    }

    /**
     * Procura um caminho entre a origem e o destino usando o algoritmo A*.
     * A busca considera movimentos horizontais e verticais de uma posição.
     *
     * @param origem posição inicial da busca
     * @param destino posição que se deseja alcançar
     * @return lista de posições do caminho, da origem ao destino; retorna uma
     *         lista vazia quando não existe caminho
     */
    public List<Posicao> encontrarCaminho(
            Posicao origem,
            Posicao destino) {

        PriorityQueue<No> abertos = new PriorityQueue<>();

        Set<Posicao> fechados = new HashSet<>();

        Map<Posicao, Integer> melhoresCustos = new HashMap<>();

        No noInicial = new No(
                origem,
                null,
                0,
                distanciaManhattan(origem, destino));

        abertos.add(noInicial);
        melhoresCustos.put(origem, 0);

        while (!abertos.isEmpty()) {
            No atual = abertos.poll();

            Posicao posicaoAtual = atual.getPosicao();

            if (posicaoAtual.equals(destino)) {
                return reconstruirCaminho(atual);
            }

            fechados.add(posicaoAtual);

            for (Posicao vizinho : obterVizinhos(posicaoAtual)) {

                if (fechados.contains(vizinho)) {
                    continue;
                }

                int novoCustoG = atual.getCustoG() + 1;

                Integer melhorCusto = melhoresCustos.get(vizinho);

                if (melhorCusto == null ||
                        novoCustoG < melhorCusto) {

                    melhoresCustos.put(
                            vizinho,
                            novoCustoG);

                    int custoH = distanciaManhattan(
                            vizinho,
                            destino);

                    No novoNo = new No(
                            vizinho,
                            atual,
                            novoCustoG,
                            custoH);

                    abertos.add(novoNo);
                }
            }
        }

        // Não existe caminho
        return Collections.emptyList();
    }

    /**
     * Reconstrói o caminho percorrendo os nós desde o destino até a origem.
     *
     * @param no nó final encontrado pela busca
     * @return posições que formam o caminho na ordem da origem ao destino
     */
    private List<Posicao> reconstruirCaminho(No no) {

        List<Posicao> caminho = new ArrayList<>();

        while (no != null) {
            caminho.add(no.getPosicao());
            no = no.getPai();
        }

        Collections.reverse(caminho);

        return caminho;
    }

    /**
     * Calcula a distância Manhattan entre duas posições, somando as diferenças
     * absolutas das coordenadas horizontal e vertical.
     *
     * @param a primeira posição
     * @param b segunda posição
     * @return distância Manhattan entre as posições
     */
    private int distanciaManhattan(
            Posicao a,
            Posicao b) {

        return Math.abs(a.getX() - b.getX())
                + Math.abs(a.getY() - b.getY());
    }

    /**
     * Obtém as posições vizinhas que podem ser alcançadas a partir de uma
     * posição, considerando os quatro sentidos de movimento.
     *
     * @param posicao posição cujos vizinhos serão verificados
     * @return lista de posições vizinhas transitáveis
     */
    private List<Posicao> obterVizinhos(
            Posicao posicao) {

        List<Posicao> vizinhos = new ArrayList<>();

        int x = posicao.getX();
        int y = posicao.getY();

        adicionarSePossivel(vizinhos, x + 1, y);
        adicionarSePossivel(vizinhos, x - 1, y);
        adicionarSePossivel(vizinhos, x, y + 1);
        adicionarSePossivel(vizinhos, x, y - 1);

        return vizinhos;
    }

    /**
     * Adiciona uma coordenada à lista de vizinhos quando ela está dentro do
     * mapa e não possui uma parede.
     *
     * @param vizinhos lista que receberá a nova posição, caso seja permitida
     * @param x coordenada horizontal a verificar
     * @param y coordenada vertical a verificar
     */
    private void adicionarSePossivel(
            List<Posicao> vizinhos,
            int x,
            int y) {

        if (mapa.podePassar(x, y)) {
            vizinhos.add(new Posicao(x, y));
        }
    }
}