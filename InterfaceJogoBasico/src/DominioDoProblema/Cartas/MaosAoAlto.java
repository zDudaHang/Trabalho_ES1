package DominioDoProblema.Cartas;

import DominioDoProblema.Jogador;
import DominioDoProblema.Pecas.Peca;

public class MaosAoAlto extends Carta {
	
	public MaosAoAlto () {
		this.id = CartaIdentificacao.MAOS_AO_ALTO;
		this.nome = "Mãos ao alto";
		this.descricao = "Mãos ao alto";
		this.afetaPecaAdversaria = true;
	}

	@Override
	public void aplicarEfeito(Peca pecaLocal, Peca pecaAdversaria, Jogador jogadorLocal, Jogador jogadorAdversario) {
		// TODO Auto-generated method stub
		
	}

}
