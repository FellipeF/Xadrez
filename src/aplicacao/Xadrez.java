package aplicacao;

import xadrez.PartidaXadrez;

public class Xadrez {

    public static void main(String[] args) {
        
        PartidaXadrez partida = new PartidaXadrez();
        UI.imprimirTabuleiro(partida.getPecas());
        
        System.out.println();
        
    }
    
}
