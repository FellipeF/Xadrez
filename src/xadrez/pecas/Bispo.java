package xadrez.pecas;

import jogo.Posicao;
import jogo.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Bispo extends PecaXadrez {

    public Bispo(Cor cor, Tabuleiro tabuleiro) {
        super(cor, tabuleiro);
    }

    @Override
    public boolean[][] movimentosPossiveis() {
        boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

        Posicao p = new Posicao(0, 0);

        //Verificando esquerda superior:
        p.setValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
        while (getTabuleiro().existePosicao(p) && !getTabuleiro().existePeca(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
            p.setValores(p.getLinha() - 1, p.getColuna() - 1);
        }
        if (getTabuleiro().existePosicao(p) && isPecaOponenteLocal(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }

        //Verificando direita superior:
        p.setValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
        while (getTabuleiro().existePosicao(p) && !getTabuleiro().existePeca(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
            p.setValores(p.getLinha() - 1, p.getColuna() + 1);
        }
        if (getTabuleiro().existePosicao(p) && isPecaOponenteLocal(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }

        //Verificando esquerda inferior:
        p.setValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
        while (getTabuleiro().existePosicao(p) && !getTabuleiro().existePeca(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
            p.setValores(p.getLinha() + 1, p.getColuna() - 1);
        }
        if (getTabuleiro().existePosicao(p) && isPecaOponenteLocal(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }

        //Verificando a direita inferior:
        p.setValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
        while (getTabuleiro().existePosicao(p) && !getTabuleiro().existePeca(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
            p.setValores(p.getLinha() + 1, p.getColuna() + 1);
        }
        if (getTabuleiro().existePosicao(p) && isPecaOponenteLocal(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }

        return matriz;
    }

    @Override
    public String toString() {
        return "B";
    }
}
