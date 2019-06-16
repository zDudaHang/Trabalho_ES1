package DominioDoProblema.Cartas;

import DominioDoProblema.Jogador;
import DominioDoProblema.Pecas.Peca;

public class CorredoresExperientes extends Carta {
	
	
	public CorredoresExperientes() {
		this.id = CartaIdentificacao.CORREDORES_EXPERIENTES;
		this.nome = "Corredores Experientes";
		this.descricao = "Uma peça poderá se deslocar 1 casa a mais em sua fase de movimentação durante o turno atual.";
		this.afetaPecaLocal = true;
	}

	@Override
	public void aplicarEfeito(Peca pecaLocal, Peca pecaAdversaria, Jogador jogadorLocal, Jogador jogadorAdversario) {
		// TODO Auto-generated method stub
		
	}

}
