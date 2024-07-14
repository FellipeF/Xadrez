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
        List <PecaXadrez> capturadas = new ArrayList<>();

        while (true) {
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
                
                if(pecaCapturada != null)
                {
                    capturadas.add(pecaCapturada);
                }
            } catch (ExcecaoXadrez e) {
                System.out.println(e.getMessage());
                input.nextLine();
            } catch (InputMismatchException e)
            {
                System.out.println(e.getMessage());
                input.nextLine();
            }
        }
    }

}
