package DominioDoProblema.Pecas;


public class Torre extends Peca {
	private static final long serialVersionUID = -2603025445499140528L;

	public Torre(int idJogadorLocal, boolean jogadorLocalComeca) {
		// A torre se move 4 casas por vez
		super(4, jogadorLocalComeca ? PecaIdentificacao.TORRE_BRANCA : PecaIdentificacao.TORRE_PRETA, idJogadorLocal);
	}
}
