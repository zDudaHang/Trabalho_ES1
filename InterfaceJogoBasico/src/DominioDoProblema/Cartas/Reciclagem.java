package DominioDoProblema.Cartas;

import DominioDoProblema.Jogador;
import DominioDoProblema.Pecas.Peca;

public class Reciclagem extends Carta {

	public Reciclagem() {
		this.id = CartaIdentificacao.RECICLAGEM;
		this.nome = "Reciclagem";
		this.descricao = "Reciclagem";
		this.afetaJogadorLocal = true;
	}

	@Override
	public void aplicarEfeito(Peca pecaLocal, Peca pecaAdversaria, Jogador jogadorLocal, Jogador jogadorAdversario) {
		// TODO Auto-generated method stub
		
	}

}
