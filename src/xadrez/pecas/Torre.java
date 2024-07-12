package xadrez.pecas;

import jogo.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Torre extends PecaXadrez {

    public Torre(Cor cor, Tabuleiro tabuleiro) {
        super(cor, tabuleiro);
    }
    
    @Override
    public String toString()
    {
        return "T";
    }
    
    @Override
    public boolean[][] movimentosPossiveis()
    {
        boolean[][] matriz = new boolean[getTabuleiro().getColunas()][getTabuleiro().getColunas()];
        return matriz;
    }
}
