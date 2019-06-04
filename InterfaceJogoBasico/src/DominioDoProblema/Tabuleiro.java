package DominioDoProblema;


import DominioDoProblema.Pecas.Bispo;
import DominioDoProblema.Pecas.Peca;
import DominioDoProblema.Pecas.Rei;
import DominioDoProblema.Pecas.Torre;

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
	
	public void configurarTabuleiro(boolean jogadorLocalComeca) {
		// Configura posição das peças
		Bispo bispoLocal1 = new Bispo(true, jogadorLocalComeca);
		Bispo bispoLocal2 = new Bispo(true, jogadorLocalComeca);
		Torre torreLocal1 = new Torre(true, jogadorLocalComeca);
		Torre torreLocal2 = new Torre(true, jogadorLocalComeca);
		Rei reiLocal = new Rei(true, jogadorLocalComeca);
		
		Bispo bispoRem1 = new Bispo(false, jogadorLocalComeca);
		Bispo bispoRem2 = new Bispo(false, jogadorLocalComeca);
		Torre torreRem1 = new Torre(false, jogadorLocalComeca);
		Torre torreRem2 = new Torre(false, jogadorLocalComeca);
		Rei reiRem = new Rei(false, jogadorLocalComeca);
	
		this.pegarPosicao(7, 0).atualizarPrimeiraPeca(torreLocal1);
		this.pegarPosicao(7, 7).atualizarPrimeiraPeca(torreLocal2);
		this.pegarPosicao(7, 2).atualizarPrimeiraPeca(bispoLocal1);
		this.pegarPosicao(7, 5).atualizarPrimeiraPeca(bispoLocal2);
		
		this.pegarPosicao(0, 0).atualizarPrimeiraPeca(torreRem1);
		this.pegarPosicao(0, 7).atualizarPrimeiraPeca(torreRem2);
		this.pegarPosicao(7, 2).atualizarPrimeiraPeca(bispoRem1);
		this.pegarPosicao(7, 5).atualizarPrimeiraPeca(bispoRem2);
		
		// Rei branco (jogador que começa) na casa preta, rei preto na casa branca
		if (jogadorLocalComeca) {
			this.pegarPosicao(7, 4).atualizarPrimeiraPeca(reiLocal);
			this.pegarPosicao(0, 3).atualizarPrimeiraPeca(reiRem);

		} else {
			this.pegarPosicao(7, 3).atualizarPrimeiraPeca(reiLocal);
			this.pegarPosicao(0, 4).atualizarPrimeiraPeca(reiRem);
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
