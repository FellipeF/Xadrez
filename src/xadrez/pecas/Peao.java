package xadrez.pecas;

import jogo.Posicao;
import jogo.Tabuleiro;
import xadrez.Cor;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;

public class Peao extends PecaXadrez {

    private PartidaXadrez partida;

    public Peao(Cor cor, Tabuleiro tabuleiro, PartidaXadrez partida) {
        super(cor, tabuleiro);
        this.partida = partida;
    }

    @Override
    public boolean[][] movimentosPossiveis() {
        boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

        Posicao p = new Posicao(0, 0);

        if (getCor() == Cor.BRANCA) {
            p.setValores(posicao.getLinha() - 1, posicao.getColuna());
            if (getTabuleiro().existePosicao(p) && !getTabuleiro().existePeca(p)) {
                matriz[p.getLinha()][p.getColuna()] = true;
            }
            //Na primeira jogada do peão:
            p.setValores(posicao.getLinha() - 2, posicao.getColuna());

            Posicao p2 = new Posicao(posicao.getLinha() - 1, posicao.getColuna());

            //As duas posições estão desocupadas?
            if (getTabuleiro().existePosicao(p) && !getTabuleiro().existePeca(p) && getTabuleiro().existePosicao(p2) && !getTabuleiro().existePeca(p) && getQtdMovimentos() == 0) {
                matriz[p.getLinha()][p.getColuna()] = true;
            }
            //Captura de peças pretas nas diagonais
            p.setValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
            if (getTabuleiro().existePosicao(p) && isPecaOponenteLocal(p)) {
                matriz[p.getLinha()][p.getColuna()] = true;
            }
            p.setValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
            if (getTabuleiro().existePosicao(p) && isPecaOponenteLocal(p)) {
                matriz[p.getLinha()][p.getColuna()] = true;
            }
            //En Passant
            if (posicao.getLinha() == 3) {
                Posicao esquerda = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
                if (getTabuleiro().existePosicao(esquerda) && isPecaOponenteLocal(esquerda) && getTabuleiro().getPeca(esquerda) == partida.getVulneravelEnPassant()) {
                    matriz[esquerda.getLinha() - 1][esquerda.getColuna()] = true;
                }
                Posicao direita = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
                if (getTabuleiro().existePosicao(direita) && isPecaOponenteLocal(direita) && getTabuleiro().getPeca(direita) == partida.getVulneravelEnPassant()) {
                    matriz[direita.getLinha() - 1][direita.getColuna()] = true;
                }
            }
        } else {
            p.setValores(posicao.getLinha() + 1, posicao.getColuna());
            if (getTabuleiro().existePosicao(p) && !getTabuleiro().existePeca(p)) {
                matriz[p.getLinha()][p.getColuna()] = true;
            }
            //Na primeira jogada do peão:
            p.setValores(posicao.getLinha() + 2, posicao.getColuna());

            Posicao p2 = new Posicao(posicao.getLinha() + 1, posicao.getColuna());

            //As duas posições estão desocupadas?
            if (getTabuleiro().existePosicao(p) && !getTabuleiro().existePeca(p) && getTabuleiro().existePosicao(p2) && !getTabuleiro().existePeca(p) && getQtdMovimentos() == 0) {
                matriz[p.getLinha()][p.getColuna()] = true;
            }
            //Captura de peças brancas nas diagonais
            p.setValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
            if (getTabuleiro().existePosicao(p) && isPecaOponenteLocal(p)) {
                matriz[p.getLinha()][p.getColuna()] = true;
            }
            p.setValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
            if (getTabuleiro().existePosicao(p) && isPecaOponenteLocal(p)) {
                matriz[p.getLinha()][p.getColuna()] = true;
            }
            //En Passant
            if (posicao.getLinha() == 4) {
                Posicao esquerda = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
                if (getTabuleiro().existePosicao(esquerda) && isPecaOponenteLocal(esquerda) && getTabuleiro().getPeca(esquerda) == partida.getVulneravelEnPassant()) {
                    matriz[esquerda.getLinha() + 1][esquerda.getColuna()] = true;
                }
                Posicao direita = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
                if (getTabuleiro().existePosicao(direita) && isPecaOponenteLocal(direita) && getTabuleiro().getPeca(direita) == partida.getVulneravelEnPassant()) {
                    matriz[direita.getLinha() + 1][direita.getColuna()] = true;
                }
            }
        }

        return matriz;
    }

    @Override
    public String toString() {
        return "P";
    }

}
