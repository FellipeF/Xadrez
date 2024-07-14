package xadrez.pecas;

import jogo.Posicao;
import jogo.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Rei extends PecaXadrez {

    public Rei(Cor cor, Tabuleiro tabuleiro) {
        super(cor, tabuleiro);
    }

    @Override
    public String toString() {
        return "K";
    }

    private boolean podeMover(Posicao posicao) {
        PecaXadrez p = (PecaXadrez) getTabuleiro().getPeca(posicao);
        return p == null || p.getCor() != getCor();
    }

    @Override
    public boolean[][] movimentosPossiveis() {
        boolean[][] matriz = new boolean[getTabuleiro().getColunas()][getTabuleiro().getColunas()];
        Posicao p = new Posicao(0, 0);

        //Acima do Rei
        p.setValores(posicao.getLinha() - 1, posicao.getColuna());
        if (getTabuleiro().existePosicao(p) && podeMover(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }

        //Abaixo do Rei
        p.setValores(posicao.getLinha() + 1, posicao.getColuna());
        if (getTabuleiro().existePosicao(p) && podeMover(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }

        //Esquerda
        p.setValores(posicao.getLinha(), posicao.getColuna() - 1);
        if (getTabuleiro().existePosicao(p) && podeMover(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }

        //Direita
        p.setValores(posicao.getLinha(), posicao.getColuna() + 1);
        if (getTabuleiro().existePosicao(p) && podeMover(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }

        //Diagonal Superior Esquerda
        p.setValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
        if (getTabuleiro().existePosicao(p) && podeMover(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }

        //Diagonal Superior Direita
        p.setValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
        if (getTabuleiro().existePosicao(p) && podeMover(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }

        //Diagonal Inferior Esquerda
        p.setValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
        if (getTabuleiro().existePosicao(p) && podeMover(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }

        //Diagonal Inferior Direita
        p.setValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
        if (getTabuleiro().existePosicao(p) && podeMover(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }

        return matriz;
    }
}
