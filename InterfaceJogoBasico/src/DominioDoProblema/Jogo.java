package DominioDoProblema;

import Rede.Etapa;

public class Jogo {
	
	protected boolean conectado = false;
	protected boolean partidaAndamento = false;
	
	protected Jogador jogadorLocal;
	protected Tabuleiro tabuleiro;
	protected Etapa etapaAtual;

	public Jogo() {
		// TODO Auto-generated constructor stub
	}

	public void definirConectado(boolean valor) {
		conectado = valor;
	}
	
	public boolean estaConectado() {
		return conectado;
	}
	
	public void definirPartidaAndamento(boolean valor) {
		partidaAndamento = valor;
	}
	
	public boolean informarPartidaAndamento() {
		return partidaAndamento;
	}
	
	public boolean permitidoConectar() {
		return !conectado;
		// defina a l�gica do seu jogo
	}
	
	public boolean permitidoDesconectar() {
		return conectado;
		// defina a l�gica do seu jogo
	}

	public boolean permitidoIniciarPartida() {
		return !partidaAndamento;
		// defina a l�gica do seu jogo
	}

	public void iniciarNovaPartida(Integer posicao, String nomeAdversario, String nomeJogadorLocal) {
		this.tabuleiro = new Tabuleiro();
		this.tabuleiro.configurarTabuleiro(posicao == 1);
		
		this.jogadorLocal = new Jogador(posicao == 1, nomeJogadorLocal);
		
		this.etapaAtual = posicao == 1 ? Etapa.USO_CARTA_COMECO : Etapa.AGUARDANDO_ADVERSARIO;
	}

	public boolean isConectado() {
		return conectado;
	}

	public void setConectado(boolean conectado) {
		this.conectado = conectado;
	}

	public boolean isPartidaAndamento() {
		return partidaAndamento;
	}

	public void setPartidaAndamento(boolean partidaAndamento) {
		this.partidaAndamento = partidaAndamento;
	}

	public Jogador getJogadorLocal() {
		return jogadorLocal;
	}

	public void setJogadorLocal(Jogador jogadorLocal) {
		this.jogadorLocal = jogadorLocal;
	}

	public Tabuleiro getTabuleiro() {
		return tabuleiro;
	}

	public void setTabuleiro(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
	}

	public Etapa getEtapaAtual() {
		return etapaAtual;
	}

	public void setEtapaAtual(Etapa etapaAtual) {
		this.etapaAtual = etapaAtual;
	}
}
