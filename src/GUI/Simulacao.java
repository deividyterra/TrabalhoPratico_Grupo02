import java.util.List;
/**
 * Coordena a movimentação do jogador pelo mapa e atualiza a janela durante
 * a execução da simulação.
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 */
public class Simulacao {
    /** Jogador que será movimentado durante a simulação. */
    private Jogador jogador;
    /** Janela que apresenta visualmente o estado do mapa. */
    private JanelaSimulacao janelaSimulacao;
    /** Mapa no qual a simulação acontece. */
    private Mapa mapa;
    
    /**
     * Inicializa o mapa, cria o jogador e abre a janela de visualização.
     */
    public Simulacao(){ 
        mapa = new Mapa();
        jogador = new Jogador();//Cria jogador
        janelaSimulacao = new JanelaSimulacao(mapa);
    }
    
    /**
     * Executa a movimentação do jogador entre duas posições, procurando
     * primeiro um caminho no mapa e atualizando a tela a cada passo.
     *
     * @param origem posição inicial do jogador
     * @param destino posição que o jogador deverá alcançar
     */
    public void executarSimulacao(Posicao origem, Posicao destino){
        jogador.setPosicaoOrigem(origem);//Define a posicao origem do jogador
        jogador.setPosicaoDestino(destino);//Define a posicao destino do jogador
        mapa.adicionarItem(jogador);//Inicializando o mapa com o jogador
        janelaSimulacao.executarAcao();
        List<Posicao> caminho = mapa.encontrarCaminho(jogador.getPosicaoOrigem(), jogador.getPosicaoDestino());
        if (caminho != null) {
            for (int i = 0; i < caminho.size(); i++) {
                executarUmPasso(caminho.get(i));
                esperar(100);
            }
            mapa.removerItem(jogador);
        }else{
            System.out.println("Não foi possível encontrar um caminho para o destino.");
        }
        System.out.println("Movimentação concluída.");
    }

    /**
     * Executa um passo da movimentação: remove o jogador da posição anterior,
     * atualiza sua posição, registra-o novamente e redesenha a janela.
     *
     * @param proximaLocalizacao posição que o jogador ocupará neste passo
     */
    private void executarUmPasso(Posicao proximaLocalizacao) {
        mapa.removerItem(jogador);
        jogador.executarAcao(proximaLocalizacao);
        mapa.adicionarItem(jogador);
        janelaSimulacao.executarAcao();
    }
    
    /**
     * Faz a thread atual aguardar pelo intervalo informado entre os passos.
     *
     * @param milisegundos duração da espera em milissegundos
     */
    private void esperar(int milisegundos){
        try{
            Thread.sleep(milisegundos);
        }catch(InterruptedException e){
            System.out.println(e.getMessage());
        }
    }
    
}
