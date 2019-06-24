package DominioDoProblema;


import java.util.logging.Logger;

import DominioDoProblema.Pecas.Bispo;
import DominioDoProblema.Pecas.Peca;
import DominioDoProblema.Pecas.PecaIdentificacao;
import DominioDoProblema.Pecas.Rei;
import DominioDoProblema.Pecas.Torre;
import br.ufsc.inf.leobr.cliente.Jogada;

public class Tabuleiro implements Jogada {
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
		
		Bispo bispoRem1 = new Bispo(posicaoAdversario, !jogadorLocalComeca);
		Bispo bispoRem2 = new Bispo(posicaoAdversario, !jogadorLocalComeca);
		Torre torreRem1 = new Torre(posicaoAdversario, !jogadorLocalComeca);
		Torre torreRem2 = new Torre(posicaoAdversario, !jogadorLocalComeca);
		Rei reiRem = new Rei(posicaoAdversario, !jogadorLocalComeca);
	
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

	public void habilitarPecasAdversariasNaoRei(int posicaoLocal) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Peca peca = this.pegarPosicao(i,j).pegarPrimeiraPeca();
				
				if (peca != null && 
					peca.getIdJogador() != posicaoLocal && 
					peca.getId() != PecaIdentificacao.REI_BRANCO &&
					peca.getId() != PecaIdentificacao.REI_PRETO) {
					this.posicoes[i][j].setHabilitada(true);
				}
			}
		}
	}

	public void habilitarPecasLocaisNaoRei(int posicaoLocal) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Peca peca = this.pegarPosicao(i,j).pegarPrimeiraPeca();
				
				if (peca != null && 
					peca.getIdJogador() == posicaoLocal && 
					peca.getId() != PecaIdentificacao.REI_BRANCO &&
					peca.getId() != PecaIdentificacao.REI_PRETO) {
					this.posicoes[i][j].setHabilitada(true);
				}
			}
		}
	}

	public void habilitarPecasAdversarias(int posicaoLocal) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Peca peca = this.pegarPosicao(i,j).pegarPrimeiraPeca();
				
				if (peca != null && 
					peca.getIdJogador() != posicaoLocal) {
					this.posicoes[i][j].setHabilitada(true);
				}
			}
		}
	}

	public void habilitarPecasLocais(int posicaoLocal) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Peca peca = this.pegarPosicao(i,j).pegarPrimeiraPeca();
				
				if (peca != null && 
					peca.getIdJogador() == posicaoLocal) {
					this.posicoes[i][j].setHabilitada(true);
				}
			}
		}
	}

	public void setPrimeiraPecaPosicaoNull(int x, int y) {
		Posicao posicao = this.pegarPosicao(x, y);
		posicao.anularPrimeiraPeca();
	}

	public void setSegundaPecaPosicaoNull(int x, int y) {
		Posicao posicao = this.pegarPosicao(x, y);
		posicao.anularSegundaPeca();
	}

	public void habilitarMovimentoBrusco(int x, int y) {
		int nx, ny;
		
		// Lateral direita
		nx = x+1;
		ny = y;
		if(nx <= 7 && this.posicaoPodeReceberPeca(nx, ny)) {
			this.pegarPosicao(nx, ny).setHabilitada(true);
		}
		
		// Lateral esquerda
		nx = x-1;
		ny = y;
		if(nx >= 0 && this.posicaoPodeReceberPeca(nx, ny)) {
			this.pegarPosicao(nx, ny).setHabilitada(true);
		}
		
		// Em cima
		nx = x;
		ny = y-1;
		if(ny >= 0 && this.posicaoPodeReceberPeca(nx, ny)) {
			this.pegarPosicao(nx, ny).setHabilitada(true);
		}
		
		// Em baixo
		nx = x;
		ny = y+1;
		if(ny <= 7 && this.posicaoPodeReceberPeca(nx, ny)) {
			this.pegarPosicao(nx, ny).setHabilitada(true);
		}
		
	}
	
	private boolean posicaoPodeReceberPeca(int nx, int ny) {
		return this.pegarPecasDaPosicao(nx, ny)[1] == null;
	}

	public void desabilitarTodasAsCasas() {
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				this.pegarPosicao(x, y).setHabilitada(false);
			}
		}
	}

	public void habilitarSairPelaTangente(int x, int y) {
		int nx, ny;
		
		// Pra cima, direita
		nx = x+1;
		ny = y-1;
		if(nx <= 7 && ny >= 0 && this.posicaoPodeReceberPeca(nx, ny)) {
			this.pegarPosicao(nx, ny).setHabilitada(true);
		}
		
		// Pra cima, esquerda
		nx = x-1;
		ny = y-1;
		if(nx >= 0 && ny >= 0 && this.posicaoPodeReceberPeca(nx, ny)) {
			this.pegarPosicao(nx, ny).setHabilitada(true);
		}
		
		//Em baixo, esquerda
		nx = x-1;
		ny = y+1;
		if(nx >= 0 && ny <= 7 && this.posicaoPodeReceberPeca(nx, ny)) {
			this.pegarPosicao(nx, ny).setHabilitada(true);
		}
		
		// Em baixo, direita
		nx = x+1;
		ny = y+1;
		if(nx <= 7 && ny <= 7 && this.posicaoPodeReceberPeca(nx, ny)) {
			this.pegarPosicao(nx, ny).setHabilitada(true);
		}	
	}
	
	@Override
	public String toString() {
		String tab = "";
		for (int x = 7; x >= 0; x--) {
			for (int y = 7; y >= 0; y--) {
				tab += this.pegarPosicao(x, y).isHabilitada() + ", ";
			}
			tab += "\n";
		}
		return tab;
	}

	public Peca pegarPecaDoJogadorNaPosicao(int idJogador, int x, int y) {
		Posicao pos = this.pegarPosicao(x, y);
		Peca[] pecas = pos.pegarPecas();
		
		if (pecas[1] == null) {
			return pecas[0];
		} else {
			if (pecas[0].getIdJogador() == idJogador) {
				return pecas[0];
			} else {
				return pecas[1];
			}
		}
	}

	public void resetPecasJogador(int idJogador) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Peca peca = this.pegarPosicao(i,j).pegarPrimeiraPeca();
				
				if (peca != null && 
					peca.getIdJogador() == idJogador) {
					peca.setPodeSeMovimentar(true);
				}
			}
		}
		
	}

	public void girarTabuleiro() {
		// Girar tabuleiro para não mostrar peças do oponente no 
		// lado do jogador local
		Posicao[][] espelhadoEmX = new Posicao[8][8];
		for (int x = 0; x < 8; x++ ) {
			for (int y = 0; y < 8; y++) {
				espelhadoEmX[x][y] = this.pegarPosicao(7-x, y);
			}
		}
		
		Posicao[][] espelhadoEmY = new Posicao[8][8];
		for (int x = 0; x < 8; x++ ) {
			for (int y = 0; y < 8; y++) {
				espelhadoEmY[x][y] = espelhadoEmX[x][7-y];
			}
		}
		
		this.posicoes = espelhadoEmY;
	}

	public void habilitarEtapaDeMovimento(int idJogador) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Peca peca = this.pegarPosicao(i,j).pegarPrimeiraPeca();
				
				if (peca != null && 
					peca.getIdJogador() == idJogador &&
					peca.isPodeSeMovimentar()) {
					this.posicoes[i][j].setHabilitada(true);
				}
			}
		}
	}

	public void habilitarMovimentoBispo(int numeroDeCasas, int x, int y) {
		for (int i = 0; i < numeroDeCasas; i++) {
			int nx, ny;
			// Pra cima, direita
			nx = x+1+i;
			ny = y-1-i;
			if(nx <= 7 && ny >= 0 && this.posicaoPodeReceberPeca(nx, ny)) {
				this.pegarPosicao(nx, ny).setHabilitada(true);
			}
			
			// Pra cima, esquerda
			nx = x-1-i;
			ny = y-1-i;
			if(nx >= 0 && ny >= 0 && this.posicaoPodeReceberPeca(nx, ny)) {
				this.pegarPosicao(nx, ny).setHabilitada(true);
			}
			
			//Em baixo, esquerda
			nx = x-1-i;
			ny = y+1+i;
			if(nx >= 0 && ny <= 7 && this.posicaoPodeReceberPeca(nx, ny)) {
				this.pegarPosicao(nx, ny).setHabilitada(true);
			}
			
			// Em baixo, direita
			nx = x+1+i;
			ny = y+1+i;
			if(nx <= 7 && ny <= 7 && this.posicaoPodeReceberPeca(nx, ny)) {
				this.pegarPosicao(nx, ny).setHabilitada(true);
			}
		}
	}

	public void habilitarMovimentoRei(int numeroDeCasas, int x, int y) {
		this.habilitarMovimentoBispo(numeroDeCasas, x, y);
		this.habilitarMovimentoTorre(numeroDeCasas, x, y);
	}

	public void habilitarMovimentoTorre(int numeroDeCasas, int x, int y) {
		for (int i = 0; i < numeroDeCasas; i++) {
			int nx, ny;
			
			// Lateral direita
			nx = x+1+i;
			ny = y;
			if(nx <= 7 && this.posicaoPodeReceberPeca(nx, ny)) {
				this.pegarPosicao(nx, ny).setHabilitada(true);
			}
			
			// Lateral esquerda
			nx = x-1-i;
			ny = y;
			if(nx >= 0 && this.posicaoPodeReceberPeca(nx, ny)) {
				this.pegarPosicao(nx, ny).setHabilitada(true);
			}
			
			// Em cima
			nx = x;
			ny = y-1-i;
			if(ny >= 0 && this.posicaoPodeReceberPeca(nx, ny)) {
				this.pegarPosicao(nx, ny).setHabilitada(true);
			}
			
			// Em baixo
			nx = x;
			ny = y+1+i;
			if(ny <= 7 && this.posicaoPodeReceberPeca(nx, ny)) {
				this.pegarPosicao(nx, ny).setHabilitada(true);
			}
		}
	}

	public void moverPecaParaPosicao(Peca peca, int x, int y) {
		boolean concluido = false;
		// Tirar peça de onde ela está
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Peca[] pecas = this.pegarPecasDaPosicao(i, j);
				
				if (pecas[0] != null  && pecas[0].equals(peca)) {
					this.setPrimeiraPecaPosicaoNull(i, j);
					concluido = true;
						
				} else if (pecas[1] != null && pecas[1].equals(peca)) {
					this.setSegundaPecaPosicaoNull(i, j);
					concluido = true;
				}
			}
			
			if (concluido) {
				break;
			}
		}
		
		assert concluido;
		
		// Colocar peça na posição alvo
		this.pegarPosicao(x, y).adicionarNovaPeca(peca);
		
	}

	public Respostas executarDano() {
		Respostas retorno = Respostas.OK;
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				Peca[] pecas = this.pegarPecasDaPosicao(x, y);
				// Evita remoção de peças do mesmo jogador
				if (pecas[0] != null && pecas[1] != null) {
					if (pecas[0].getIdJogador() == pecas[1].getIdJogador()) {
						continue;
					}
				}
					
				if (pecas[1] != null) {
					this.pegarPosicao(x, y).atualizarPrimeiraPeca(pecas[1]);
					this.setSegundaPecaPosicaoNull(x, y);
					
					if (pecas[0].getId() == PecaIdentificacao.REI_BRANCO ||
							pecas[0].getId() == PecaIdentificacao.REI_PRETO) {
						retorno = Respostas.VITORIA_DO_OPONENTE;
					}
				}
				
			}
		}
		
		return retorno;
	}

	public void inverterPecas() {
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				Peca[] pecas = this.pegarPecasDaPosicao(x, y);
				if (pecas[1] != null) {
					this.pegarPosicao(x, y).atualizarPrimeiraPeca(pecas[1]);
					this.pegarPosicao(x, y).atualizarSegundaPeca(pecas[0]);
				}
			}
		}
	}
}
