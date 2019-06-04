package DominioDoProblema.Pecas;

import DominioDoProblema.Jogador;
import DominioDoProblema.Cartas.EstadoPeca;

public abstract class Peca {
	protected int numeroDeCasas;
	protected boolean podeSeMovimentar;
	protected EstadoPeca estado;
	protected boolean jogadorLocal;
	protected PecaIdentificacao id;
	/*
	 * Construtor padrão
	 */
	public Peca(int numeroDeCasas, boolean jogadorLocal, PecaIdentificacao id) {
		this.id = id;
		
		// Inicialmente, toda peça pode se mover
		this.podeSeMovimentar = true;
		
		// Inicialmente, toda peça criada vai para o tabuleiro
		this.estado = EstadoPeca.NO_TABULEIRO;
		
		// Seta número de casas
		this.numeroDeCasas = numeroDeCasas;
		
		// Seta o dono da peça
		this.jogadorLocal = jogadorLocal;
	}
	
	/*
	 * Descreve como as peças devem se mover
	 */
	abstract void movimentar();
	
	/*
	 * Diz quantas casa uma peça pode se mover
	 */
	public int getNumeroDeCasas() {
		return numeroDeCasas;
	}
	
	/*
	 * Modifica número de casas que uma peça pode se mover
	 */
	public void setNumeroDeCasas(int numeroDeCasas) {
		this.numeroDeCasas = numeroDeCasas;
	}
	
	/*
	 * Checa se a peça é capaz de se mover
	 */
	public boolean isPodeSeMovimentar() {
		return podeSeMovimentar;
	}
	
	/*
	 * Modifica capacidade da peça se movimentar
	 */
	public void setPodeSeMovimentar(boolean podeSeMovimentar) {
		this.podeSeMovimentar = podeSeMovimentar;
	}
	
	/*
	 * Coloca estado da peça para removida, para ser retirada do tabuleiro
	 */
	public void removePeca() {
		this.estado = EstadoPeca.REMOVIDA;
	}

	public EstadoPeca getEstado() {
		return estado;
	}

	public void setEstado(EstadoPeca estado) {
		this.estado = estado;
	}

	public boolean isJogadorLocal() {
		return jogadorLocal;
	}

	public void setJogadorLocal(boolean jogadorLocal) {
		this.jogadorLocal = jogadorLocal;
	}

	public PecaIdentificacao getId() {
		return id;
	}

	public void setId(PecaIdentificacao id) {
		this.id = id;
	}
}
