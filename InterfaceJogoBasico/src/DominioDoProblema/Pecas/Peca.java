package DominioDoProblema.Pecas;

import DominioDoProblema.Pecas.EstadoPeca;
import br.ufsc.inf.leobr.cliente.Jogada;

public abstract class Peca implements Jogada {
	private static final long serialVersionUID = 1L;
	
	protected int numeroDeCasas;
	protected boolean podeSeMovimentar;
	protected EstadoPeca estado;
	protected PecaIdentificacao id;
	protected int idJogador;
	/*
	 * Construtor padrão
	 */
	public Peca(int numeroDeCasas, PecaIdentificacao id, int idJogador) {
		this.id = id;
		
		this.idJogador = idJogador;
		
		// Inicialmente, toda peça pode se mover
		this.podeSeMovimentar = true;
		
		// Inicialmente, toda peça criada vai para o tabuleiro
		this.estado = EstadoPeca.NO_TABULEIRO;
		
		// Seta número de casas
		this.numeroDeCasas = numeroDeCasas;
	}

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
	public boolean podeSeMovimentar() {
		return podeSeMovimentar;
	}
	
	/*
	 * Modifica capacidade da peça se movimentar
	 */
	public void setPodeSeMovimentar(boolean podeSeMovimentar) {
		this.podeSeMovimentar = podeSeMovimentar;
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

	public int getIdJogador() {
		return idJogador;
	}

	public void setIdJogador(int idJogador) {
		this.idJogador = idJogador;
	}
	
	@Override
	public String toString() {
		return this.id + " idJogador:" + this.idJogador;
	}
}
