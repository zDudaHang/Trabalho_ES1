package DominioDoProblema.Cartas;

import java.util.List;

import DominioDoProblema.Etapa;
import DominioDoProblema.Jogador;
import DominioDoProblema.Posicao;
import DominioDoProblema.Tabuleiro;
import DominioDoProblema.Pecas.Peca;
import Rede.Acao;

public class MovimentoBrusco extends Carta{
	
	public MovimentoBrusco() {
		this.id = CartaIdentificacao.MOVIMENTO_BRUSCO;
		this.nome = "Movimento brusco";
		this.descricao = "Movimento brusco";
	}

	@Override
	public Acao aplicarEfeito(Tabuleiro tabuleiro, int idAdversario, List<Peca> pecasAfetadas, Posicao posicaoAlvo, Jogador jogadorLocal, Etapa etapa) {
		assert posicaoAlvo != null;
		assert pecasAfetadas.get(0).getIdJogador() == jogadorLocal.getIdJogador();
		
		boolean concluido = false;
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				Peca[] pecas = tabuleiro.pegarPecasDaPosicao(x, y);
				if (pecas.length == 0) {
					continue;
				}
				
				// Se tem uma peça na primeira posição
				if (pecas[0] != null) {
					// E essa peça é igual a peça afetada
					if (pecas[0].equals(pecasAfetadas.get(0))) {
						// Seta essa peça para null
						tabuleiro.setPrimeiraPecaPosicaoNull(x, y);

						concluido = true;
						break;
					// Se não, checa se a segunda peça é igual a peça afetada
					} else if (pecas[1] != null) {
						// Se for, seta essa peça para null
						if (pecas[1].equals(pecasAfetadas.get(0))) {
							tabuleiro.setSegundaPecaPosicaoNull(x, y);

							concluido = true;
							break;
						}
					}
				}
			}
		}
		
		assert concluido;
		
		posicaoAlvo.adicionarNovaPeca(pecasAfetadas.get(0));
		
		return new Acao(jogadorLocal.getCartasMao(),
				jogadorLocal.getCartasDeck(), 
				jogadorLocal.getCartasDescarte(),
				this.id, 
				tabuleiro,
				etapa,
				false, false);
	}
	
}
