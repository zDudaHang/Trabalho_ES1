package DominioDoProblema.Cartas;

import java.util.List;

import DominioDoProblema.Etapa;
import DominioDoProblema.Jogador;
import DominioDoProblema.Posicao;
import DominioDoProblema.Tabuleiro;
import DominioDoProblema.Pecas.Peca;
import DominioDoProblema.Pecas.PecaIdentificacao;
import Rede.Acao;

public class SaveTheKing extends Carta  {
	public SaveTheKing() {
		this.id = CartaIdentificacao.SAVE_THE_KING;
		this.nome = "Save the King";
		this.descricao = "Save the King";
	}

	@Override
	public Acao aplicarEfeito(Tabuleiro tabuleiro, int idAdversario, List<Peca> pecasAfetadas, Posicao posicaoAlvo, Jogador jogadorLocal, Etapa etapa) {
		assert pecasAfetadas.get(0).getIdJogador() == jogadorLocal.getIdJogador();
		assert pecasAfetadas.get(1).getIdJogador() == jogadorLocal.getIdJogador();
		assert pecasAfetadas.get(0).getId() == PecaIdentificacao.REI_BRANCO || pecasAfetadas.get(0).getId() == PecaIdentificacao.REI_PRETO;
		
		Posicao posicaoRei = null;
		int camadaRei = 0;
		Posicao posicaoOutraPeca = null;
		int camadaOutraPeca = 0;
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				Posicao posicao = tabuleiro.pegarPosicao(x, y);
				
				int pecaEstaNaPosicao = posicao.pecaEstaNaPosicao(pecasAfetadas.get(0));
				if (pecaEstaNaPosicao != 0) {
					posicaoRei = posicao;
					camadaRei = pecaEstaNaPosicao;
				} 
				
				pecaEstaNaPosicao = posicao.pecaEstaNaPosicao(pecasAfetadas.get(1));
				if (pecaEstaNaPosicao != 0) {
					posicaoOutraPeca = posicao;
					camadaOutraPeca = pecaEstaNaPosicao;
				}
			}
		}
		
		assert camadaRei != 0;
		assert camadaOutraPeca != 0;
		
		if (camadaRei == 1) {
			posicaoRei.atualizarPrimeiraPeca(pecasAfetadas.get(1));
		} else {
			posicaoRei.atualizarSegundaPeca(pecasAfetadas.get(1));
		}
		
		if (camadaOutraPeca == 1) {
			posicaoOutraPeca.atualizarPrimeiraPeca(pecasAfetadas.get(0));
		} else {
			posicaoOutraPeca.atualizarSegundaPeca(pecasAfetadas.get(0));
		}
		
		return new Acao(jogadorLocal.getCartasMao(),
				jogadorLocal.getCartasDeck(), 
				jogadorLocal.getCartasDescarte(),
				this.id, 
				tabuleiro,
				etapa,
				false, false);	
	}
}
