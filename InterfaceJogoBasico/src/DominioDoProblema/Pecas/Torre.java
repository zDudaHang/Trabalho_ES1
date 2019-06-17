package DominioDoProblema.Pecas;

import java.util.UUID;

public class Torre extends Peca {

	public Torre(int idJogadorLocal, boolean jogadorLocalComeca) {
		// A torre se move 4 casas por vez
		super(4, jogadorLocalComeca ? PecaIdentificacao.TORRE_BRANCA : PecaIdentificacao.TORRE_PRETA, idJogadorLocal);
	}

	@Override
	void movimentar() {
		// TODO Auto-generated method stub
		
	}

}
