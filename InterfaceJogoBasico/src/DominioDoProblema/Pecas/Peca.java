package DominioDoProblema.Pecas;

import DominioDoProblema.Posicao;

public abstract class Peca {
	protected Posicao posicao;
	protected int numeroDeCasas;
	protected boolean podeSeMovimentar;
	
	abstract void movimentar();

	public Posicao getPosicao() {
		return posicao;
	}

	public void setPosicao(Posicao posicao) {
		this.posicao = posicao;
	}

	public int getNumeroDeCasas() {
		return numeroDeCasas;
	}

	public void setNumeroDeCasas(int numeroDeCasas) {
		this.numeroDeCasas = numeroDeCasas;
	}

	public boolean isPodeSeMovimentar() {
		return podeSeMovimentar;
	}

	public void setPodeSeMovimentar(boolean podeSeMovimentar) {
		this.podeSeMovimentar = podeSeMovimentar;
	}
}
