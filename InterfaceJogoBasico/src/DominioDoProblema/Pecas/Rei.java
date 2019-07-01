package DominioDoProblema.Pecas;


public class Rei extends Peca  {
	private static final long serialVersionUID = 3862471870272399047L;

	public Rei(int idJogador, boolean jogadorLocalComeca) {
		// O rei só se mover 1 casa por vez
		super(1, jogadorLocalComeca ? PecaIdentificacao.REI_BRANCO : PecaIdentificacao.REI_PRETO, idJogador);
	}

	@Override
	public void resetarPeca() {
		this.numeroDeCasas = 1;
		this.podeSeMovimentar = true;
	}
}
