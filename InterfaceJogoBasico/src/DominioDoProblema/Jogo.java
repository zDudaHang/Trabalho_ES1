package DominioDoProblema;

import DominioDoProblema.Cartas.CartaIdentificacao;
import Rede.Etapa;

public class Jogo {
	
	protected boolean conectado = false;
	protected boolean partidaAndamento = false;
	
	protected Jogador jogadorLocal;
	protected Tabuleiro tabuleiro;
	protected Etapa etapaAtual;
	protected EstadoJogo estadoAtual;

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
		this.tabuleiro.configurarTabuleiro(posicao == 1, posicao);
		
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

	public Respostas cartaClicada(CartaIdentificacao id) {
		if (this.etapaAtual != Etapa.USO_CARTA_COMECO && this.etapaAtual != Etapa.USO_CARTA_FIM) {
			return Respostas.NAO_EH_O_MOMENTO;
		}
		
		Respostas retorno;
		switch (id) {
		case MOVIMENTO_BRUSCO:
		case CORREDORES_EXPERIENTES:
		case SAIR_PELA_TANGENTE:
			this.estadoAtual = EstadoJogo.AGUARDNADO_SELECIONAR_PECA_LOCAL;
			this.marcarPecasLocais();
			retorno = Respostas.SELECIONAR_UMA_PECA_LOCAL;
			break;
		
		case SAVE_THE_KING:
			this.estadoAtual = EstadoJogo.AGUARDNADO_SELECIONAR_PECA_LOCAL;
			this.marcarPecasLocaisNaoRei();
			retorno = Respostas.SELECIONAR_UMA_PECA_LOCAL_NAO_REI;
			break;
			
		case ESCUDOS:
			retorno = Respostas.NADA;
			break;
			
		case MAOS_AO_ALTO:
			this.estadoAtual = EstadoJogo.AGUARDANDO_SELECIONAR_PECA_ADVERSARIO;
			this.marcarPecasAdversarias();
			retorno = Respostas.SELECIONAR_UMA_PECA_ADVERSARIA;
			break;
	
		case SACRIFICIO:
			this.estadoAtual = EstadoJogo.AGUARDANDO_SELECIONAR_DUAS_PECAS;
			this.marcarPecasAdversariasNaoRei();
			this.marcarPecasLocaisNaoRei();
			retorno = Respostas.SELECIONAR_LOCAL_E_ADVERSARIA_NAO_REI;
			break;
			
		case SILENCIO_POR_FAVOR:
			retorno = Respostas.NADA;
			break;

		default:
			retorno = Respostas.NADA;
			break;
		}
		
		return retorno;
	}

	private void marcarPecasAdversariasNaoRei() {
		this.tabuleiro.marcarPecasAdversariasNaoRei(this.jogadorLocal.getPosicao());		
	}

	private void marcarPecasAdversarias() {
		this.tabuleiro.marcarPecasAdversarias(this.jogadorLocal.getPosicao());
	}

	private void marcarPecasLocaisNaoRei() {
		this.tabuleiro.marcarPecasLocaisNaoRei(this.jogadorLocal.getPosicao());
	}

	private void marcarPecasLocais() {
		this.tabuleiro.marcarPecasLocais(this.jogadorLocal.getPosicao());	
	}

}
