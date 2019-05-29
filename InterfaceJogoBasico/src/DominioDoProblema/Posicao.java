package DominioDoProblema;

import DominioDoProblema.Pecas.Peca;

public class Posicao {
	protected Peca primeiraPeca;
	protected Peca segundaPeca;
	protected int x;
	protected int y;
	
	public Posicao(int x, int y) {
		this.x = x;
		this.y = y;
		
		this.primeiraPeca = null;
		this.segundaPeca = null;
	}
	
	public int pegarX() {
		return this.x;
	}
	
	public int pegarY() {
		return this.y;
	}

	public Peca pegarPrimeiraPeca() {
		return this.primeiraPeca;
	}


	public Peca[] pegarPecas() {
		return new Peca[] {this.primeiraPeca, this.segundaPeca};
	}
	
	public boolean temConflito() {
		return (this.primeiraPeca != null) && (this.segundaPeca != null);
	}
	
	public void atualizarPrimeiraPeca(Peca peca) {
		this.primeiraPeca = peca;
	}
	
	public void atualizarSegundaPeca(Peca peca) {
		this.segundaPeca = peca;
	}
}
