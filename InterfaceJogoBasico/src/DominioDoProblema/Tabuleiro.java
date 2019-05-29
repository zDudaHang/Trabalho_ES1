package DominioDoProblema;

import org.omg.CORBA.ExceptionList;

import DominioDoProblema.Pecas.Peca;

public class Tabuleiro {
	protected Posicao[][] posicoes;

	public Tabuleiro() {
		// Create the 8x8 board
		this.posicoes = new Posicao[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				this.posicoes[i][j] = new Posicao(i, j);
			}
		}
	}
	
	public Posicao pegarPosicao(int x, int y) {
		try {
			return this.posicoes[x][y];
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Peca[] pegarPecasDaPosicao(int x, int y) {
		Posicao pos = this.pegarPosicao(x, y);
		return pos.pegarPecas();
	}
}
