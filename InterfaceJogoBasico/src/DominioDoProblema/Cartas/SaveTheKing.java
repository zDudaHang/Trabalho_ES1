package DominioDoProblema.Cartas;

import DominioDoProblema.Jogador;
import DominioDoProblema.Pecas.Peca;

public class SaveTheKing extends Carta  {
	public SaveTheKing() {
		this.id = CartaIdentificacao.SAVE_THE_KING;
		this.nome = "Save the King";
		this.descricao = "Save the King";
		this.afetaPecaLocal = true;
	}

	@Override
	public void aplicarEfeito(Peca pecaLocal, Peca pecaAdversaria, Jogador jogadorLocal, Jogador jogadorAdversario) {
		// TODO Auto-generated method stub
		
	}
}
