package DominioDoProblema.Pecas;

public class Bispo extends Peca {
	private static final long serialVersionUID = 8052837986849770747L;

	public Bispo(int idJogador, boolean jogadorLocalComeca) {
		// O bispo se move 2 casas por vez
		super(2, jogadorLocalComeca ? PecaIdentificacao.BISPO_BRANCO : PecaIdentificacao.BISPO_PRETO, idJogador);
	}
}
