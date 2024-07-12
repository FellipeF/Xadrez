package xadrez;

import jogo.Peca;
import jogo.Tabuleiro;

//A implementação do movimento é realizada nas peças específicas

public abstract class PecaXadrez extends Peca{
    
    private Cor cor;

    public PecaXadrez(Tabuleiro tabuleiro) {
        super(tabuleiro);
    }

    public PecaXadrez(Cor cor, Tabuleiro tabuleiro) {
        super(tabuleiro);
        this.cor = cor;
    }

    public Cor getCor() {
        return cor;
    }
}
