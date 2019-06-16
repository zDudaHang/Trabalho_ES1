package DominioDoProblema.Cartas;

import DominioDoProblema.Jogador;
import DominioDoProblema.Pecas.Peca;

public class Espiar extends Carta {

	public Espiar() {
		this.id = CartaIdentificacao.ESPIAR;
		this.nome = "Espiar";
		this.descricao = "Espiar";
		this.afetaJogadorLocal = true;
		this.afetaJogadorAdversario = true;
	}

	@Override
	public void aplicarEfeito(Peca pecaLocal, Peca pecaAdversaria, Jogador jogadorLocal, Jogador jogadorAdversario) {
		// TODO Auto-generated method stub
		
	}

}
