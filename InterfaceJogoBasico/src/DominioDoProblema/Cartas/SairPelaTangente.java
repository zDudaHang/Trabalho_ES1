package DominioDoProblema.Cartas;

import DominioDoProblema.Jogador;
import DominioDoProblema.Pecas.Peca;

public class SairPelaTangente extends Carta {
	public SairPelaTangente() {
		this.id = CartaIdentificacao.SAIR_PELA_TANGENTE;
		this.nome = "Sair pela Tangente";
		this.descricao = "Sair pela Tangente";
		this.afetaPecaLocal = true;
	}
	
	@Override
	public void aplicarEfeito(Peca pecaLocal, Peca pecaAdversaria, Jogador jogadorLocal, Jogador jogadorAdversario) {
		// TODO Auto-generated method stub
		
	}

}
