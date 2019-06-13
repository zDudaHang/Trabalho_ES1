package Rede;
import DominioDoProblema.Tabuleiro;
import br.ufsc.inf.leobr.cliente.Jogada;

public class Acao implements Jogada {
	private int qtdMao;
	private int qtdDeck;
	private int qtdDescarte;
	private Tabuleiro tabuleiroModificado;
	private Etapa etapa;

	public Acao(int numeroCarasMao, int numeroCarasDeck, int numeroCarasDescarte, Tabuleiro tabuleiroModificado,
			Etapa etapa) {
		super();
		this.qtdMao = numeroCarasMao;
		this.qtdDeck = numeroCarasDeck;
		this.qtdDescarte = numeroCarasDescarte;
		this.tabuleiroModificado = tabuleiroModificado;
		this.etapa = etapa;
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

	public Tabuleiro getTabuleiroModificado() {
		return tabuleiroModificado;
	}

	public Etapa getEtapa() {
		return etapa;
	}
}