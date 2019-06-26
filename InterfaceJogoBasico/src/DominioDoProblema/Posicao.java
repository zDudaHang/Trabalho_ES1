package DominioDoProblema;

import DominioDoProblema.Pecas.Peca;
import br.ufsc.inf.leobr.cliente.Jogada;

public class Posicao implements Jogada {
	protected Peca primeiraPeca;
	protected Peca segundaPeca;
	protected boolean habilitada;
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

	public void adicionarNovaPeca(Peca peca) {
		if (this.primeiraPeca == null) {
			this.atualizarPrimeiraPeca(peca);
		} else if (this.segundaPeca == null){
			this.atualizarSegundaPeca(peca);
		} else {
			System.err.println("POSIÇÃO INVÁLIDA PARA POSICIONAMENTO DE PEÇA");
		}
	}

	public void anularPrimeiraPeca() {
		this.primeiraPeca = this.segundaPeca;
		this.segundaPeca = null;
	}

	public void anularSegundaPeca() {
		this.segundaPeca = null;
	}

	public int pecaEstaNaPosicao(Peca peca) {
		if (peca.equals(this.primeiraPeca)) {
			return 1;
		} else if (peca.equals(this.segundaPeca)) {
			return 2;
		} 
		
		return 0;
	}

	public boolean isHabilitada() {
		return habilitada;
	}

	public void setHabilitada(boolean habilitada) {
		this.habilitada = habilitada;
	}
	
	@Override
	public String toString() {
		return "[Posição]: Primeira peça = " + this.primeiraPeca + ", Segunda peça = " + this.segundaPeca;
	}

	public Peca pegarSegundaPeca() {
		return this.segundaPeca;
	}
}
