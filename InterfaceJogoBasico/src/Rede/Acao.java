package Rede;
import DominioDoProblema.Etapa;
import DominioDoProblema.Tabuleiro;
import DominioDoProblema.Cartas.CartaIdentificacao;
import br.ufsc.inf.leobr.cliente.Jogada;

public class Acao implements Jogada {

	private static final long serialVersionUID = -5797571886529867250L;

	private int qtdMao;
	private int qtdDeck;
	private int qtdDescarte;
	private CartaIdentificacao idCartaUsada;
	private Tabuleiro tabuleiroModificado;
	private Etapa etapa;
	private boolean usouSilencio;
	private boolean vitoriaOponente;
	/* 
	 * Objeto de apenas leitura, usado para sincronizar o jogador local
	 * e o jogador em rede a medida que as etapas passam
	 */
	public Acao(int qtdMao, int qtdDeck, int qtdDescarte, CartaIdentificacao idCartaUsada,
			Tabuleiro tabuleiroModificado, Etapa etapa, boolean usouSilencio, boolean vitoriaOponente) {
		super();
		this.qtdMao = qtdMao;
		this.qtdDeck = qtdDeck;
		this.qtdDescarte = qtdDescarte;
		this.idCartaUsada = idCartaUsada;
		this.tabuleiroModificado = tabuleiroModificado;
		this.etapa = etapa;
		this.usouSilencio = usouSilencio;
		this.vitoriaOponente = vitoriaOponente;
	}

	public int getQtdMao() {
		return qtdMao;
	}

	public int getQtdDeck() {
		return qtdDeck;
	}

	public int getQtdDescarte() {
		return qtdDescarte;
	}

	public CartaIdentificacao getIdCartaUsada() {
		return idCartaUsada;
	}

	public Tabuleiro getTabuleiroModificado() {
		return tabuleiroModificado;
	}

	public Etapa getEtapa() {
		return etapa;
	}

	public boolean isUsouSilencio() {
		return usouSilencio;
	}

	@Override
	public String toString() {
		return "Acao [qtdMao=" + qtdMao + ", qtdDeck=" + qtdDeck + ", qtdDescarte=" + qtdDescarte + ", idCartaUsada="
				+ idCartaUsada + ", tabuleiroModificado=" + tabuleiroModificado + ", etapa=" + etapa + ", usouSilencio="
				+ usouSilencio + "vitoriaOponente=" + vitoriaOponente +"]";
	}

	public boolean isVitoriaOponente() {
		return vitoriaOponente;
	}
	
	
}