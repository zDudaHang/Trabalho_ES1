package DominioDoProblema.Cartas;

import DominioDoProblema.Jogador;
import DominioDoProblema.Cartas.CartaIdentificacao;
import DominioDoProblema.Pecas.Peca;

public abstract class Carta {

	public boolean isAfetaPecaLocal() {
		return afetaPecaLocal;
	}

	public boolean isAfetaPecaAdversaria() {
		return afetaPecaAdversaria;
	}

	public boolean isAfetaJogadorLocal() {
		return afetaJogadorLocal;
	}

	public boolean isAfetaJogadorAdversario() {
		return afetaJogadorAdversario;
	}
	
	protected boolean afetaPecaLocal = false;

	protected boolean afetaPecaAdversaria = false;
	
	protected boolean afetaJogadorLocal = false;
	
	protected boolean afetaJogadorAdversario = false;
	
	protected String descricao;
	
	protected String nome;
	
	protected CartaIdentificacao id;
	
	public CartaIdentificacao getId() {
		return this.id;
	}
	
	public abstract void aplicarEfeito(Peca pecaLocal, Peca pecaAdversaria, Jogador jogadorLocal, Jogador jogadorAdversario);
	
	public String getDescricao() {
		return this.descricao;
	}
	
	public String getNome() {
		return this.nome;
	}

}
