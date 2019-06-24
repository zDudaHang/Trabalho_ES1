package DominioDoProblema.Cartas;

import java.util.List;

import DominioDoProblema.Etapa;
import DominioDoProblema.Jogador;
import DominioDoProblema.Posicao;
import DominioDoProblema.Tabuleiro;
import DominioDoProblema.Pecas.Peca;
import Rede.Acao;

public class CorredoresExperientes extends Carta {
	public CorredoresExperientes() {
		this.id = CartaIdentificacao.CORREDORES_EXPERIENTES;
		this.nome = "Corredores Experientes";
		this.descricao = "Uma peça poderá se deslocar 1 casa a mais em sua fase de movimentação durante o turno atual.";
	}

	@Override
	public Acao aplicarEfeito(Tabuleiro tabuleiro, int idAdversario, List<Peca> pecasAfetadas, Posicao posicaoAlvo, Jogador jogadorLocal, Etapa etapa) {
		assert pecasAfetadas.get(0).getIdJogador() == jogadorLocal.getIdJogador();
		
		pecasAfetadas.get(0).setNumeroDeCasas(pecasAfetadas.get(0).getNumeroDeCasas() + 1);
		
		return new Acao(jogadorLocal.getCartasMao(),
						jogadorLocal.getCartasDeck(), 
						jogadorLocal.getCartasDescarte(),
						this.id, 
						tabuleiro,
						etapa,
						false, false);
	}

}
