package DominioDoProblema.Cartas;

import DominioDoProblema.Jogador;
import DominioDoProblema.Pecas.Peca;

public class MovimentoBrusco extends Carta{
	
	public MovimentoBrusco() {
		this.id = CartaIdentificacao.MOVIMENTO_BRUSCO;
		this.nome = "Movimento brusco";
		this.descricao = "Movimento brusco";
		this.afetaPecaLocal = true;
	}

	@Override
	public void aplicarEfeito(Peca pecaLocal, Peca pecaAdversaria, Jogador jogadorLocal, Jogador jogadorAdversario) {
		// TODO Auto-generated method stub
		
	}
	
}
