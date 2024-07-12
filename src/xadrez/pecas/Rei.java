package xadrez.pecas;

import jogo.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Rei extends PecaXadrez {

    public Rei(Cor cor, Tabuleiro tabuleiro) {
        super(cor, tabuleiro);
    }

    @Override
    public String toString()
    {
        return "K";
    }
    
    @Override
    public boolean[][] movimentosPossiveis()
    {
        boolean[][] matriz = new boolean[getTabuleiro().getColunas()][getTabuleiro().getColunas()];
        return matriz;
    }
}
