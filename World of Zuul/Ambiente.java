/**
 * Classe Ambiente - um ambiente em um jogo adventure.
 *
 * Esta classe eh parte da aplicacao "World of Zuul".
 * "World of Zuul" eh um jogo de aventura muito simples, baseado em texto.  
 *
 * Um "Ambiente" representa uma localizacao no cenario do jogo. Ele eh
 * conectado aos outros ambientes atraves de saidas. As saidas sao
 * nomeadas como norte, sul, leste e oeste. Para cada direcao, o ambiente
 * guarda uma referencia para o ambiente vizinho, ou null se nao ha
 * saida naquela direcao.
 * 
 * @author  Michael Kölling and David J. Barnes (traduzido por Julio Cesar Alves)
 * @version 2011.07.31 (2016.02.01)
 */
public class Ambiente 
{
    private String descricao;
    private Ambiente saidaNorte;
    private Ambiente saidaSul;
    private Ambiente saidaLeste;
    private Ambiente saidaOeste;

    /**
     * Cria um ambiente com a "descricao" passada. Inicialmente, ele
     * nao tem saidas. "descricao" eh algo como "uma cozinha" ou jardim"
     * @param descricao A descricao do ambiente.
     */
    public Ambiente(String descricao) 
    {
        this.descricao = descricao;
    }

    /**
     * Define as saidas do ambiente. Cada direcao ou leva a um
     * outro ambiente ou eh null (nenhuma saida para la).
     * @param norte A saida norte.
     * @param leste A saida leste.
     * @param sul A saida sul.
     * @param oeste A saida oeste.
     */
    public void ajustarSaidas(Ambiente norte, Ambiente leste, Ambiente sul, Ambiente oeste) 
    {
        if(norte != null)
            saidaNorte = norte;
        if(leste != null)
            saidaLeste = leste;
        if(sul != null)
            saidaSul = sul;
        if(oeste != null)
            saidaOeste = oeste;
    }

    /**
     * @return A descricao do ambiente.
     */
    public String getDescricao()
    {
        return descricao;
    }

    /**
     * @return Retorna as saídas do Ambiente.
    */
    public Ambiente getSaidaNorte(){
        return saidaNorte;
    }
    public Ambiente getSaidaSul(){
        return saidaSul;
    }
    public Ambiente getSaidaLeste(){
        return saidaLeste;
    }
    public Ambiente getSaidaOeste(){
        return saidaOeste;
    }

    /**
     * Funcao que retorna o ambiente que fica na direcao informada
     * @param direcao A direcao que deseja ir
     * @return O ambiente vizinho dessa direcao ou null caso nao
     * haja saida
     */
    public Ambiente getSaida(String direcao){
        if(direcao.equals("norte")) return getSaidaNorte();
        if(direcao.equals("leste")) return getSaidaLeste();
        if(direcao.equals("sul")) return getSaidaSul();
        if(direcao.equals("oeste")) return getSaidaOeste();
        return null;
    }
}