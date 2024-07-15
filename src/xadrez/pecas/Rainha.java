package xadrez.pecas;

import jogo.Posicao;
import jogo.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Rainha extends PecaXadrez {

    public Rainha(Cor cor, Tabuleiro tabuleiro) {
        super(cor, tabuleiro);
    }

    @Override
    public String toString() {
        return "Q";
    }

    @Override
    public boolean[][] movimentosPossiveis() {
        boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

        Posicao p = new Posicao(0, 0);
        
        // ----------- LÓGICA DE TORRE -------------

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
        
        // ----------- LÓGICA DE BISPO -------------

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
}
