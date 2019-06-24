package DominioDoProblema.Cartas;

import java.util.List;

import DominioDoProblema.Etapa;
import DominioDoProblema.Jogador;
import DominioDoProblema.Posicao;
import DominioDoProblema.Tabuleiro;
import DominioDoProblema.Pecas.Peca;
import Rede.Acao;

public class Escudos extends Carta {

	public Escudos() {
		this.id = CartaIdentificacao.ESCUDOS;
		this.nome = "Escudos";
		this.descricao = "Não ocorre dano neste turno";
	}

	@Override
	public Acao aplicarEfeito(Tabuleiro tabuleiro, int idAdversario, List<Peca> pecasAfetadas, Posicao posicaoAlvo, Jogador jogadorLocal, Etapa etapa) {
		jogadorLocal.setPodeLevarDano(false);
		
		return new Acao(jogadorLocal.getCartasMao(),
				jogadorLocal.getCartasDeck(), 
				jogadorLocal.getCartasDescarte(),
				this.id, 
				tabuleiro,
				etapa,
				false, false);
	}
	

}
