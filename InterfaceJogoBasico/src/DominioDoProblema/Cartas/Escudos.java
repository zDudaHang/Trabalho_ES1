package DominioDoProblema.Cartas;

import DominioDoProblema.Jogador;
import DominioDoProblema.Pecas.Peca;

public class Escudos extends Carta {

	public Escudos() {
		this.id = CartaIdentificacao.ESCUDOS;
		this.nome = "Escudos";
		this.descricao = "Não ocorre dano neste turno";
		this.afetaJogadorLocal = true;

	}

	@Override
	public void aplicarEfeito(Peca pecaLocal, Peca pecaAdversaria, Jogador jogadorLocal, Jogador jogadorAdversario) {
		// TODO Auto-generated method stub
		
	}
	

}
