package DominioDoProblema.Pecas;

import java.util.UUID;

public class Bispo extends Peca {

	public Bispo(int idJogador, boolean jogadorLocalComeca) {
		// O bispo se move 2 casas por vez
		super(2, jogadorLocalComeca ? PecaIdentificacao.BISPO_BRANCO : PecaIdentificacao.BISPO_PRETO, idJogador);
	}

	@Override
	void movimentar() {
		// TODO Auto-generated method stub
		
	}

}
