package DominioDoProblema.Pecas;

public class Bispo extends Peca {

	public Bispo(boolean jogadorLocal, boolean jogadorLocalComeca) {
		// O bispo se move 2 casas por vez
		super(2, jogadorLocal, jogadorLocalComeca ? PecaIdentificacao.BISPO_BRANCO : PecaIdentificacao.BISPO_PRETO);
	}

	@Override
	void movimentar() {
		// TODO Auto-generated method stub
		
	}

}
