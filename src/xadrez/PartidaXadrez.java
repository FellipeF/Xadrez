package xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import jogo.Peca;
import jogo.Posicao;
import jogo.Tabuleiro;
import xadrez.pecas.Bispo;
import xadrez.pecas.Cavalo;
import xadrez.pecas.Peao;
import xadrez.pecas.Rainha;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

//Definição das regras da partida
public class PartidaXadrez {

    private int turno;
    private Cor jogadorAtual;
    private Tabuleiro tabuleiro;
    private boolean check;
    private boolean checkmate;

    private List<Peca> pecasTabuleiro = new ArrayList<>();
    private List<Peca> pecasCapturadas = new ArrayList<>();

    public PartidaXadrez() {
        tabuleiro = new Tabuleiro(8, 8);
        turno = 1;
        jogadorAtual = Cor.BRANCA;
        setup();
    }

    public int getTurno() {
        return turno;
    }

    public Cor getJogadorAtual() {
        return jogadorAtual;
    }

    public boolean getCheck() {
        return check;
    }

    public boolean getCheckmate() {
        return checkmate;
    }

    public PecaXadrez[][] getPecas() {
        PecaXadrez[][] matriz = new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];

        for (int i = 0; i < tabuleiro.getLinhas(); i++) {
            for (int j = 0; j < tabuleiro.getColunas(); j++) {
                matriz[i][j] = (PecaXadrez) tabuleiro.getPeca(i, j);
            }
        }

        return matriz;
    }

    //Método para permitir a impressão na aplicação dos possíveis destinos da peça
    public boolean[][] movimentosPossiveis(PosicaoXadrez origem) {
        Posicao p = origem.toPosicao();
        validarOrigem(p);
        return tabuleiro.getPeca(p).movimentosPossiveis();
    }

    public PecaXadrez fazerMovimentoXadrez(PosicaoXadrez origem, PosicaoXadrez destino) {
        Posicao orig = origem.toPosicao();
        Posicao dest = destino.toPosicao();

        validarOrigem(orig);
        validarDestino(orig, dest);
        Peca pecaCapturada = movimentar(orig, dest);

        if (testarCheck(jogadorAtual)) {
            desfazerMovimento(orig, dest, pecaCapturada);
            throw new ExcecaoXadrez("O movimento efetuada causa check no Rei");
        }

        check = (testarCheck(verificaOponente(jogadorAtual))) ? true : false;

        if (testarCheckmate(verificaOponente(jogadorAtual))) {
            checkmate = true;
        } else {
            proximoTurno();
        }

        return (PecaXadrez) pecaCapturada;
    }

    private Peca movimentar(Posicao origem, Posicao destino) {
        PecaXadrez p = (PecaXadrez) tabuleiro.removerPeca(origem);
        p.incrementarQtdMovimentos();
        Peca pecaCapturada = tabuleiro.removerPeca(destino);

        tabuleiro.colocarPeca(p, destino);

        if (pecaCapturada != null) {
            pecasTabuleiro.remove(pecaCapturada);
            pecasCapturadas.add(pecaCapturada);
        }

        return pecaCapturada;
    }

    //Previne o xeque causado pelo próprio jogador, desfazendo a jogada
    private void desfazerMovimento(Posicao origem, Posicao destino, Peca capturada) {
        PecaXadrez p = (PecaXadrez) tabuleiro.removerPeca(destino);
        p.decrementarQtdMovimentos();
        tabuleiro.colocarPeca(p, origem);

        if (capturada != null) {
            tabuleiro.colocarPeca(capturada, destino);
            pecasCapturadas.remove(capturada);
            pecasTabuleiro.add(capturada);
        }
    }

    private void validarOrigem(Posicao posicao) {
        if (!tabuleiro.existePeca(posicao)) {
            throw new ExcecaoXadrez("Nao existe peca na posicao de origem");
        }
        if (jogadorAtual != ((PecaXadrez) tabuleiro.getPeca(posicao)).getCor()) {
            throw new ExcecaoXadrez("A peca escolhida nao e do jogador atual");
        }
        if (!tabuleiro.getPeca(posicao).isAlgumMovimentoPossivel()) {
            throw new ExcecaoXadrez("Nao existem movimentos possiveis para a peca escolhida");
        }
    }

    private void validarDestino(Posicao origem, Posicao destino) {
        if (!tabuleiro.getPeca(origem).movimentoPossivel(destino)) {
            throw new ExcecaoXadrez("A peca escolhida nao pode ser movida para a posicao de destino");
        }
    }

    private void proximoTurno() {
        turno++;
        jogadorAtual = (jogadorAtual == Cor.BRANCA) ? Cor.PRETA : Cor.BRANCA;
    }

    private Cor verificaOponente(Cor cor) {
        return (cor == Cor.BRANCA) ? Cor.PRETA : Cor.BRANCA;
    }

    private PecaXadrez localizaRei(Cor cor) {
        List<Peca> lista = pecasTabuleiro.stream().filter(x -> ((PecaXadrez) x).getCor() == cor).collect(Collectors.toList());
        for (Peca p : lista) {
            if (p instanceof Rei) {
                return (PecaXadrez) p;
            }
        }
        throw new IllegalStateException("Nao existe rei da cor " + cor + " no tabuleiro");
    }

    private boolean testarCheck(Cor cor) {
        Posicao posicaoRei = localizaRei(cor).getPosicaoXadrez().toPosicao();
        List<Peca> pecasOponente = pecasTabuleiro.stream().filter(x -> ((PecaXadrez) x).getCor() == verificaOponente(cor)).collect(Collectors.toList());

        for (Peca p : pecasOponente) {
            boolean[][] matriz = p.movimentosPossiveis();
            if (matriz[posicaoRei.getLinha()][posicaoRei.getColuna()]) //O rei está na casa aonde estão os movimentos possíveis do adversário?
            {
                return true;
            }
        }

        return false;
    }

    private boolean testarCheckmate(Cor cor) {
        if (!testarCheck(cor)) {
            return false;
        }
        List<Peca> lista = pecasTabuleiro.stream().filter(x -> ((PecaXadrez) x).getCor() == cor).collect(Collectors.toList());

        for (Peca p : lista) {
            boolean[][] matriz = p.movimentosPossiveis();
            for (int i = 0; i < tabuleiro.getLinhas(); i++) {
                for (int j = 0; j < tabuleiro.getColunas(); j++) {
                    if (matriz[i][j]) {
                        Posicao origem = ((PecaXadrez) p).getPosicaoXadrez().toPosicao();
                        Posicao destino = new Posicao(i, j);
                        Peca capturada = movimentar(origem, destino);

                        //Moveu a peça da origem ao destino. Ainda está em cheque?
                        boolean testarCheck = testarCheck(cor);
                        desfazerMovimento(origem, destino, capturada);
                        if (!testarCheck) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    //Passando nas coordenadas de xadrez ao invés de na posição da matriz
    private void colocarNovaPeca(char coluna, int linha, PecaXadrez peca) {
        tabuleiro.colocarPeca(peca, new PosicaoXadrez(coluna, linha).toPosicao());
        pecasTabuleiro.add(peca);
    }

    //Inicialização da partida, colocando as peças no tabuleiro
    private void setup() {

        //BRANCAS
        colocarNovaPeca('a', 2, new Peao(Cor.BRANCA, tabuleiro));
        colocarNovaPeca('b', 2, new Peao(Cor.BRANCA, tabuleiro));
        colocarNovaPeca('c', 2, new Peao(Cor.BRANCA, tabuleiro));
        colocarNovaPeca('d', 2, new Peao(Cor.BRANCA, tabuleiro));
        colocarNovaPeca('e', 2, new Peao(Cor.BRANCA, tabuleiro));
        colocarNovaPeca('f', 2, new Peao(Cor.BRANCA, tabuleiro));
        colocarNovaPeca('g', 2, new Peao(Cor.BRANCA, tabuleiro));
        colocarNovaPeca('h', 2, new Peao(Cor.BRANCA, tabuleiro));

        colocarNovaPeca('a', 1, new Torre(Cor.BRANCA, tabuleiro));
        colocarNovaPeca('h', 1, new Torre(Cor.BRANCA, tabuleiro));

        colocarNovaPeca('b', 1, new Cavalo(Cor.BRANCA, tabuleiro));
        colocarNovaPeca('g', 1, new Cavalo(Cor.BRANCA, tabuleiro));

        colocarNovaPeca('c', 1, new Bispo(Cor.BRANCA, tabuleiro));
        colocarNovaPeca('f', 1, new Bispo(Cor.BRANCA, tabuleiro));
        
        colocarNovaPeca('d', 1, new Rainha(Cor.BRANCA, tabuleiro));

        colocarNovaPeca('e', 1, new Rei(Cor.BRANCA, tabuleiro));

        //PRETAS
        colocarNovaPeca('a', 7, new Peao(Cor.PRETA, tabuleiro));
        colocarNovaPeca('b', 7, new Peao(Cor.PRETA, tabuleiro));
        colocarNovaPeca('c', 7, new Peao(Cor.PRETA, tabuleiro));
        colocarNovaPeca('d', 7, new Peao(Cor.PRETA, tabuleiro));
        colocarNovaPeca('e', 7, new Peao(Cor.PRETA, tabuleiro));
        colocarNovaPeca('f', 7, new Peao(Cor.PRETA, tabuleiro));
        colocarNovaPeca('g', 7, new Peao(Cor.PRETA, tabuleiro));
        colocarNovaPeca('h', 7, new Peao(Cor.PRETA, tabuleiro));

        colocarNovaPeca('a', 8, new Torre(Cor.PRETA, tabuleiro));
        colocarNovaPeca('h', 8, new Torre(Cor.PRETA, tabuleiro));
        
        colocarNovaPeca('b', 8, new Cavalo(Cor.PRETA, tabuleiro));
        colocarNovaPeca('g', 8, new Cavalo(Cor.PRETA, tabuleiro));

        colocarNovaPeca('c', 8, new Bispo(Cor.PRETA, tabuleiro));
        colocarNovaPeca('f', 8, new Bispo(Cor.PRETA, tabuleiro));
        
        colocarNovaPeca('d', 8, new Rainha(Cor.PRETA, tabuleiro));

        colocarNovaPeca('e', 8, new Rei(Cor.PRETA, tabuleiro));
    }
}
