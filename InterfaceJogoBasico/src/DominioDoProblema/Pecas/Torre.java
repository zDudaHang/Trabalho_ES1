package DominioDoProblema.Pecas;

public class Torre extends Peca {

	public Torre(boolean jogadorLocal, boolean jogadorLocalComeca) {
		// A torre se move 4 casas por vez
		super(4, jogadorLocal, jogadorLocalComeca ? PecaIdentificacao.TORRE_BRANCA : PecaIdentificacao.TORRE_PRETA);
		// TODO Auto-generated constructor stub
	}

	@Override
	void movimentar() {
		// TODO Auto-generated method stub
		
	}

}
