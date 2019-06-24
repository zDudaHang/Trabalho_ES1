package DominioDoProblema.Cartas;

import java.util.List;

import DominioDoProblema.Etapa;
import DominioDoProblema.Jogador;
import DominioDoProblema.Posicao;
import DominioDoProblema.Tabuleiro;
import DominioDoProblema.Cartas.CartaIdentificacao;
import DominioDoProblema.Pecas.Peca;
import Rede.Acao;

public abstract class Carta {
	protected String descricao;
	
	protected String nome;
	
	protected CartaIdentificacao id;
	
	public CartaIdentificacao getId() {
		return this.id;
	}
	
	public abstract Acao aplicarEfeito(Tabuleiro tabuleiro, int idAdversario, List<Peca> pecasSelecionadas, Posicao posicaoAlvo, Jogador jogadorLocal, Etapa etapa);
	
	public String getDescricao() {
		return this.descricao;
	}
	
	public String getNome() {
		return this.nome;
	}

}
