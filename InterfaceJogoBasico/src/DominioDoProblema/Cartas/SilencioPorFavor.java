package DominioDoProblema.Cartas;

import java.util.List;

import DominioDoProblema.Etapa;
import DominioDoProblema.Jogador;
import DominioDoProblema.Posicao;
import DominioDoProblema.Tabuleiro;
import DominioDoProblema.Pecas.Peca;
import Rede.Acao;

public class SilencioPorFavor extends Carta {
	public SilencioPorFavor() {
		this.id = CartaIdentificacao.SILENCIO_POR_FAVOR;
		this.nome = "Silêncio, por favor";
		this.descricao = "Silêncio, por favor";
	}

	@Override
	public Acao aplicarEfeito(Tabuleiro tabuleiro, int idAdversario, List<Peca> pecasAfetadas, Posicao posicaoAlvo, Jogador jogadorLocal, Etapa etapa) {
		return new Acao(jogadorLocal.getCartasMao(),
				jogadorLocal.getCartasDeck(), 
				jogadorLocal.getCartasDescarte(),
				this.id, 
				tabuleiro,
				etapa,
				true, false);	
		
	}

}
