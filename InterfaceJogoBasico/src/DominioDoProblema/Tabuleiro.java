package DominioDoProblema;


import java.util.UUID;

import DominioDoProblema.Pecas.Bispo;
import DominioDoProblema.Pecas.Peca;
import DominioDoProblema.Pecas.PecaIdentificacao;
import DominioDoProblema.Pecas.Rei;
import DominioDoProblema.Pecas.Torre;

public class Tabuleiro {
	protected Posicao[][] posicoes;

	public Tabuleiro() {
		// Create the 8x8 board
		this.posicoes = new Posicao[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				this.posicoes[j][i] = new Posicao(j, i);
			}
		}
	}
	
	public void configurarTabuleiro(boolean jogadorLocalComeca, int posicaoJogadorLocal) {
		// Configura posição das peças
		int posicaoAdversario = posicaoJogadorLocal == 1 ? 2 : 1;
		Bispo bispoLocal1 = new Bispo(posicaoJogadorLocal, jogadorLocalComeca);
		Bispo bispoLocal2 = new Bispo(posicaoJogadorLocal, jogadorLocalComeca);
		Torre torreLocal1 = new Torre(posicaoJogadorLocal, jogadorLocalComeca);
		Torre torreLocal2 = new Torre(posicaoJogadorLocal, jogadorLocalComeca);
		Rei reiLocal = new Rei(posicaoJogadorLocal, jogadorLocalComeca);
		System.out.println(reiLocal.getIdJogador() + " " + reiLocal.getId());
		
		Bispo bispoRem1 = new Bispo(posicaoAdversario, !jogadorLocalComeca);
		Bispo bispoRem2 = new Bispo(posicaoAdversario, !jogadorLocalComeca);
		Torre torreRem1 = new Torre(posicaoAdversario, !jogadorLocalComeca);
		Torre torreRem2 = new Torre(posicaoAdversario, !jogadorLocalComeca);
		Rei reiRem = new Rei(posicaoAdversario, !jogadorLocalComeca);
		
		System.out.println(reiRem.getIdJogador() + " " + reiRem.getId());
	
		this.pegarPosicao(0, 7).atualizarPrimeiraPeca(torreLocal1);
		this.pegarPosicao(7, 7).atualizarPrimeiraPeca(torreLocal2);
		this.pegarPosicao(2, 7).atualizarPrimeiraPeca(bispoLocal1);
		this.pegarPosicao(5, 7).atualizarPrimeiraPeca(bispoLocal2);
		
		this.pegarPosicao(0, 0).atualizarPrimeiraPeca(torreRem1);
		this.pegarPosicao(7, 0).atualizarPrimeiraPeca(torreRem2);
		this.pegarPosicao(2, 0).atualizarPrimeiraPeca(bispoRem1);
		this.pegarPosicao(5, 0).atualizarPrimeiraPeca(bispoRem2);
		
		// Rei branco (jogador que começa) na casa preta, rei preto na casa branca
		if (jogadorLocalComeca) {
			this.pegarPosicao(4, 7).atualizarPrimeiraPeca(reiLocal);
			this.pegarPosicao(3, 0).atualizarPrimeiraPeca(reiRem);

		} else {
			this.pegarPosicao(3, 7).atualizarPrimeiraPeca(reiLocal);
			this.pegarPosicao(4, 0).atualizarPrimeiraPeca(reiRem);
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

	public void marcarPecasAdversariasNaoRei(int posicaoLocal) {
		System.out.println("Marcando peças adversárias sem ser rei");
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Peca peca = this.pegarPosicao(i,j).pegarPrimeiraPeca();
				
				if (peca != null && 
					peca.getIdJogador() != posicaoLocal && 
					peca.getId() != PecaIdentificacao.REI_BRANCO &&
					peca.getId() != PecaIdentificacao.REI_PRETO) {
					peca.setHabilitada(true);
				}
			}
		}
	}

	public void marcarPecasLocaisNaoRei(int posicaoLocal) {
		System.out.println("Marcando peças não rei");
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Peca peca = this.pegarPosicao(i,j).pegarPrimeiraPeca();
				
				if (peca != null && 
					peca.getIdJogador() == posicaoLocal && 
					peca.getId() != PecaIdentificacao.REI_BRANCO &&
					peca.getId() != PecaIdentificacao.REI_PRETO) {
					peca.setHabilitada(true);
				}
			}
		}
	}

	public void marcarPecasAdversarias(int posicaoLocal) {
		System.out.println("Marcando peças adversárias");
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Peca peca = this.pegarPosicao(i,j).pegarPrimeiraPeca();
				
				if (peca != null && 
					peca.getIdJogador() != posicaoLocal) {
					peca.setHabilitada(true);
				}
			}
		}
	}

	public void marcarPecasLocais(int posicaoLocal) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Peca peca = this.pegarPosicao(i,j).pegarPrimeiraPeca();
				
				if (peca != null && 
					peca.getIdJogador() == posicaoLocal) {
					peca.setHabilitada(true);
				}
			}
		}
	}
}
