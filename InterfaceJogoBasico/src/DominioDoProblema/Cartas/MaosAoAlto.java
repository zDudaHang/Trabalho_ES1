package DominioDoProblema.Cartas;

import java.util.List;

import DominioDoProblema.Etapa;
import DominioDoProblema.Jogador;
import DominioDoProblema.Posicao;
import DominioDoProblema.Tabuleiro;
import DominioDoProblema.Pecas.Peca;
import Rede.Acao;

public class MaosAoAlto extends Carta {
	
	public MaosAoAlto () {
		this.id = CartaIdentificacao.MAOS_AO_ALTO;
		this.nome = "Mãos ao alto";
		this.descricao = "Durante o próximo turno, uma peça não pode se mover de nenhuma forma.";
	}

	@Override
	public Acao aplicarEfeito(Tabuleiro tabuleiro, int idAdversario, List<Peca> pecasAfetadas, Posicao posicaoAlvo, Jogador jogadorLocal, Etapa etapa) {
		assert pecasAfetadas.get(0).getIdJogador() == idAdversario;
		
		pecasAfetadas.get(0).setPodeSeMovimentar(false);
		
		return new Acao(jogadorLocal.getCartasMao(),
				jogadorLocal.getCartasDeck(), 
				jogadorLocal.getCartasDescarte(),
				this.id, 
				tabuleiro,
				etapa,
				false, false);
	}

}
