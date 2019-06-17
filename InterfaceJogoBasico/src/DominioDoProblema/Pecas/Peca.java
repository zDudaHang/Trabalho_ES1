package DominioDoProblema.Pecas;

import DominioDoProblema.Pecas.EstadoPeca;

public abstract class Peca {
	protected int numeroDeCasas;
	protected boolean podeSeMovimentar;
	protected EstadoPeca estado;
	protected PecaIdentificacao id;
	protected int idJogador;
	protected boolean habilitada;
//	protected PecaIdentificacao cor;
	/*
	 * Construtor padrão
	 */
	public Peca(int numeroDeCasas, PecaIdentificacao id, int idJogador) {
		this.id = id;
		
		this.habilitada = false;
		
		this.idJogador = idJogador;
		
		// Inicialmente, toda peça pode se mover
		this.podeSeMovimentar = true;
		
		// Inicialmente, toda peça criada vai para o tabuleiro
		this.estado = EstadoPeca.NO_TABULEIRO;
		
		// Seta número de casas
		this.numeroDeCasas = numeroDeCasas;
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

	public PecaIdentificacao getId() {
		return id;
	}

	public void setId(PecaIdentificacao id) {
		this.id = id;
	}

	public boolean isHabilitada() {
		return habilitada;
	}

	public void setHabilitada(boolean habilitada) {
		this.habilitada = habilitada;
	}

	public int getIdJogador() {
		return idJogador;
	}

	public void setIdJogador(int idJogador) {
		this.idJogador = idJogador;
	}
}
