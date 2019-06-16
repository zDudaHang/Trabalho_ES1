package DominioDoProblema.Cartas;

import DominioDoProblema.Jogador;
import DominioDoProblema.Pecas.Peca;

public class SacrificioNecessario extends Carta {
	public SacrificioNecessario() {
		this.id = CartaIdentificacao.SACRIFICIO;
		this.nome = "Sacrifício Necessário";
		this.descricao = "Sacrifício Necessário";
		this.afetaPecaAdversaria = true;
		this.afetaPecaLocal = true;
	}
	
	@Override
	public void aplicarEfeito(Peca pecaLocal, Peca pecaAdversaria, Jogador jogadorLocal, Jogador jogadorAdversario) {
		// TODO Auto-generated method stub
		
	}

}
