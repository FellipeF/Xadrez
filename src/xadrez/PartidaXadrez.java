package xadrez;

import jogo.Posicao;
import jogo.Tabuleiro;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

//Definição das regras da partida

public class PartidaXadrez {
    private Tabuleiro tabuleiro;

    public PartidaXadrez()
    {
        tabuleiro = new Tabuleiro(8,8);
        setup();
    }
    
    public PecaXadrez[][] getPecas()
    {
        PecaXadrez[][] matriz = new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
        
        for (int i = 0; i < tabuleiro.getLinhas(); i++)
        {
            for (int j = 0; j < tabuleiro.getColunas(); j++)
            {
                matriz[i][j] = (PecaXadrez) tabuleiro.getPeca(i, j);
            }
        }
        
        return matriz;
    }
    
    //Inicialização da partida, colocando as peças no tabuleiro
    private void setup()
    {
        tabuleiro.colocarPeca(new Torre(Cor.BRANCA, tabuleiro), new Posicao(7,0));
        tabuleiro.colocarPeca(new Rei(Cor.PRETA, tabuleiro), new Posicao(0, 4));
        tabuleiro.colocarPeca(new Rei(Cor.BRANCA, tabuleiro), new Posicao(7,4));
    }
}
