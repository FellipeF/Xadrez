package aplicacao;

import java.util.InputMismatchException;
import java.util.Scanner;
import xadrez.ExcecaoXadrez;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;

public class Xadrez {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        PartidaXadrez partida = new PartidaXadrez();

        while (true) {
            try {
                UI.limparTela();

                UI.imprimirTabuleiro(partida.getPecas());
                System.out.println("");
                System.out.println("");
                System.out.print("Origem: ");
                PosicaoXadrez origem = UI.lerPosicaoXadrez(input);

                System.out.println("");
                System.out.print("Destino: ");
                PosicaoXadrez destino = UI.lerPosicaoXadrez(input);

                PecaXadrez pecaCapturada = partida.fazerMovimentoXadrez(origem, destino);
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
