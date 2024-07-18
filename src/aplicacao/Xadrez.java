package aplicacao;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import xadrez.ExcecaoXadrez;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;

public class Xadrez {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        PartidaXadrez partida = new PartidaXadrez();
        List<PecaXadrez> capturadas = new ArrayList<>();

        while (!partida.getCheckmate()) {
            try {
                UI.limparTela();

                UI.imprimirPartida(partida, capturadas);
                System.out.println("");
                System.out.print("Origem: ");
                PosicaoXadrez origem = UI.lerPosicaoXadrez(input);

                boolean[][] movimentosPossiveis = partida.movimentosPossiveis(origem);
                UI.limparTela();
                UI.imprimirTabuleiro(partida.getPecas(), movimentosPossiveis);

                System.out.println("");
                System.out.print("Destino: ");
                PosicaoXadrez destino = UI.lerPosicaoXadrez(input);

                PecaXadrez pecaCapturada = partida.fazerMovimentoXadrez(origem, destino);

                if (pecaCapturada != null) {
                    capturadas.add(pecaCapturada);
                }

                if (partida.getPromovida() != null) {
                    System.out.print("Digite a peca de sua escolha para promocao (B/C/Q/T): ");
                    String tipo = input.nextLine().toUpperCase();
                    while (!tipo.equals("B") && !tipo.equals("C") && !tipo.equals("T") && !tipo.equals("Q")) {
                        System.out.print("Escolha invalida! Digite a peca de sua escolha para promocao (B/C/Q/T): ");
                        tipo = input.nextLine().toUpperCase();
                    }
                    partida.trocarPecaPromovida(tipo);
                }
            } catch (ExcecaoXadrez e) {
                System.out.println(e.getMessage());
                input.nextLine();
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
                input.nextLine();
            }
        }
        UI.limparTela();
        UI.imprimirPartida(partida, capturadas);
    }

}
