package xadrez;

import jogo.Posicao;

public class PosicaoXadrez {
    private char coluna;
    private int linha;

    public PosicaoXadrez(char coluna, int linha) {
        if (coluna < 'a'|| coluna > 'h' || linha < 1 || linha > 8)
        {
            throw new ExcecaoXadrez("Erro instanciando Posicao. Valores validos: a1-h8.");
        }
        this.coluna = coluna;
        this.linha = linha;
    }

    public char getColuna() {
        return coluna;
    }

    public int getLinha() {
        return linha;
    }
    
    //Convertendo Posição de Xadrez para a posição na matriz
    
    protected Posicao toPosicao()
    {
        return new Posicao(8 - linha, coluna - 'a');
    }
    
    protected static PosicaoXadrez fromPosicao(Posicao posicao)
    {
        return new PosicaoXadrez((char)('a' + posicao.getColuna()), 8 - posicao.getLinha());
    }
    
    @Override
    public String toString()
    {
        return "" + coluna + linha;
    }
    
}
