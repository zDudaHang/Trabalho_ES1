package DominioDoProblema.Cartas;

import DominioDoProblema.Jogador;
import DominioDoProblema.Pecas.Peca;

public class SilencioPorFavor extends Carta {
	public SilencioPorFavor() {
		this.id = CartaIdentificacao.SILENCIO_POR_FAVOR;
		this.nome = "Silêncio, por favor";
		this.descricao = "Silêncio, por favor";
		this.afetaJogadorAdversario = true;
	}

	@Override
	public void aplicarEfeito(Peca pecaLocal, Peca pecaAdversaria, Jogador jogadorLocal, Jogador jogadorAdversario) {
		// TODO Auto-generated method stub
		
	}

}
