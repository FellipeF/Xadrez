package xadrez.pecas;

import jogo.Posicao;
import jogo.Tabuleiro;
import xadrez.Cor;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;

public class Rei extends PecaXadrez {

    private PartidaXadrez partida;

    public Rei(Cor cor, Tabuleiro tabuleiro, PartidaXadrez partida) {
        super(cor, tabuleiro);
        this.partida = partida;
    }

    @Override
    public String toString() {
        return "K";
    }

    private boolean podeMover(Posicao posicao) {
        PecaXadrez p = (PecaXadrez) getTabuleiro().getPeca(posicao);
        return p == null || p.getCor() != getCor();
    }

    //Teste com a torre: Ela está apta a roque?
    private boolean testarRoque(Posicao posicao) {
        PecaXadrez p = (PecaXadrez) getTabuleiro().getPeca(posicao);
        //A peça existe, é uma torre da mesma cor que o rei e ainda não se moveu?
        return p != null && p instanceof Torre && p.getCor() == getCor() && p.getQtdMovimentos() == 0;
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

        //Fazer o Roque
        if (getQtdMovimentos() == 0 && !partida.getCheck()) {
            //Roque do lado do Rei (à direita)
            Posicao posicaoTorreDireita = new Posicao(posicao.getLinha(), posicao.getColuna() + 3);
            if (testarRoque(posicaoTorreDireita)) {
                //As casas vizinhas estão vazias?
                Posicao p1 = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
                Posicao p2 = new Posicao(posicao.getLinha(), posicao.getColuna() + 2);
                if (getTabuleiro().getPeca(p1) == null && getTabuleiro().getPeca(p2) == null) {
                    matriz[posicao.getLinha()][posicao.getColuna() + 2] = true;
                }
            }
            //Roque do lado da Rainha (à esquerda)
            Posicao posicaoTorreEsquerda = new Posicao(posicao.getLinha(), posicao.getColuna() - 4);
            if (testarRoque(posicaoTorreEsquerda)) {
                //As casas vizinhas estão vazias?
                Posicao p1 = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
                Posicao p2 = new Posicao(posicao.getLinha(), posicao.getColuna() - 2);
                Posicao p3 = new Posicao(posicao.getLinha(), posicao.getColuna() - 3);
                if (getTabuleiro().getPeca(p1) == null && getTabuleiro().getPeca(p2) == null && getTabuleiro().getPeca(p3) == null) {
                    matriz[posicao.getLinha()][posicao.getColuna() - 2] = true;
                }
            }
        }
        return matriz;
    }
}
