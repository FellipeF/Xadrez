package xadrez.pecas;

import jogo.Posicao;
import jogo.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Torre extends PecaXadrez {

    public Torre(Cor cor, Tabuleiro tabuleiro) {
        super(cor, tabuleiro);
    }

    @Override
    public String toString() {
        return "T";
    }

    @Override
    public boolean[][] movimentosPossiveis() {
        boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

        Posicao p = new Posicao(0, 0);

        //Verificando acima da peça:
        p.setValores(posicao.getLinha() - 1, posicao.getColuna());
        while (getTabuleiro().existePosicao(p) && !getTabuleiro().existePeca(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
            p.setLinha(p.getLinha() - 1);
        }
        if (getTabuleiro().existePosicao(p) && isPecaOponenteLocal(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }

        //Verificando abaixo da peça:
        p.setValores(posicao.getLinha() + 1, posicao.getColuna());
        while (getTabuleiro().existePosicao(p) && !getTabuleiro().existePeca(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
            p.setLinha(p.getLinha() + 1);
        }
        if (getTabuleiro().existePosicao(p) && isPecaOponenteLocal(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }

        //Verificando a esquerda da peça:
        p.setValores(posicao.getLinha(), posicao.getColuna() - 1);
        while (getTabuleiro().existePosicao(p) && !getTabuleiro().existePeca(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
            p.setColuna(p.getColuna() - 1);
        }
        if (getTabuleiro().existePosicao(p) && isPecaOponenteLocal(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }

        //Verificando a direita da peça:
        p.setValores(posicao.getLinha(), posicao.getColuna() + 1);
        while (getTabuleiro().existePosicao(p) && !getTabuleiro().existePeca(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
            p.setColuna(p.getColuna() + 1);
        }
        if (getTabuleiro().existePosicao(p) && isPecaOponenteLocal(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }

        return matriz;
    }
}
