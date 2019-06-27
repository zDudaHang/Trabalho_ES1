package DominioDoProblema;

import java.util.ArrayList;
import java.util.List;

import DominioDoProblema.Cartas.Carta;
import DominioDoProblema.Cartas.CartaIdentificacao;
import DominioDoProblema.Pecas.Peca;
import Rede.Acao;

public class Jogo {
	
	protected boolean conectado = false;
	protected boolean partidaAndamento = false;
	
	protected Jogador jogadorLocal;
	protected Tabuleiro tabuleiro;
	protected Etapa etapaAtual;
	protected EstadoJogo estadoAtual;
	
	protected List<Peca> pecasSelecionadas;
	protected CartaIdentificacao cartaUsada;
	protected Posicao posicaoSelecionada;
	
	public Jogo() {
		this.pecasSelecionadas = new ArrayList<>();
		this.cartaUsada = CartaIdentificacao.NENHUMA;
		this.posicaoSelecionada = null;
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
		
		if (posicao == 1) {
			this.setupInicioTurno();
		} else {
			this.setupEspera();
		}
	}

	private Respostas setupEspera() {
		this.setEtapaAtual(Etapa.AGUARDANDO_ADVERSARIO);
		this.setEstadoAtual(EstadoJogo.AGUARDANDO_ADVERSARIO);
		this.jogadorLocal.setEhJogadorDaVez(false);
		this.jogadorLocal.setPodeLevarDano(true);
		this.jogadorLocal.setUsouCarta(false);
		this.jogadorLocal.setPodeUsarCarta(true);
		this.tabuleiro.resetPecasJogador(this.jogadorLocal.getIdJogador());
		this.pecasSelecionadas = new ArrayList<>();
		this.cartaUsada = CartaIdentificacao.NENHUMA;
		this.jogadorLocal.setPodeUsarCarta(true);
		this.jogadorLocal.setEhJogadorDaVez(false);
		
		return Respostas.OK;
	}

	private Respostas setupInicioTurno() {
		// Checa se o jogador tem que comprar carta
		this.jogadorLocal.setEhJogadorDaVez(true);

		if (this.jogadorLocal.estaNoEstadoDeCompra()) {
			boolean conseguiu = this.jogadorLocal.comprarCarta();
			if (!conseguiu) {
				return Respostas.VITORIA_DO_OPONENTE;
			}
		}
		
		this.setEtapaAtual(Etapa.USO_CARTA_COMECO);
		this.setEstadoAtual(EstadoJogo.AGUARDANDO_USO_CARTA);	
	
		if (!this.jogadorLocal.isPodeUsarCarta()) {
			return Respostas.ENVIAR_JOGADA;
		}
		return Respostas.USAR_CARTA;
	}
	
	private Respostas setupMovimento() {
		this.cartaUsada = CartaIdentificacao.NENHUMA;
		this.pecasSelecionadas.clear();
		this.posicaoSelecionada = null;
		
		this.setEtapaAtual(Etapa.MOVIMENTO);
		this.setEstadoAtual(EstadoJogo.AGUARDANDO_SELECIONAR_PECA_MOVIMENTO);
		
		this.tabuleiro.habilitarEtapaDeMovimento(this.jogadorLocal.getIdJogador());
		
		return Respostas.OK;
	}
	
	private Respostas executaEtapaDano() {
		Respostas retorno = Respostas.OK;
		// TODO fazer algoritmo para executar o dano, retornar
		// vitoria para o oponente caso o rei local for removido
		if (this.jogadorLocal.isPodeLevarDano()) {
			retorno = this.tabuleiro.executarDano();

		} else {
			// Jogador local usou escudos, então não levará dano. As peças
			// sobrepostas desse jogador, agora invertem e sobrepõem
			this.tabuleiro.inverterPecas();
		}
		return retorno;
	}
	
	private Respostas setupFimTurno() {
		if (this.jogadorLocal.isPodeUsarCarta()) {
			this.setEtapaAtual(Etapa.USO_CARTA_FIM);
			this.setEstadoAtual(EstadoJogo.AGUARDANDO_USO_CARTA);
			
		} else {
			this.setupEspera();
			this.setEstadoAtual(EstadoJogo.ENVIANDO_JOGADA);
			return Respostas.ENVIAR_JOGADA;
		}
		
		return Respostas.OK;
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

	public void setEtapaAtual(Etapa novaEtapa) {
		this.etapaAtual = novaEtapa;
	}

	public Respostas cartaClicada(CartaIdentificacao id) {
		if (this.getEstadoAtual() != EstadoJogo.AGUARDANDO_USO_CARTA) {
			return Respostas.NAO_EH_O_MOMENTO;
		}
		this.cartaUsada = id;
		// Jogador usou carta
		this.jogadorLocal.setPodeUsarCarta(false);
		
		Respostas retorno = null;
		switch (id) {
		case MOVIMENTO_BRUSCO:
		case CORREDORES_EXPERIENTES:
		case SAIR_PELA_TANGENTE:
			this.setEstadoAtual(EstadoJogo.AGUARDNADO_SELECIONAR_PECA_LOCAL);
			this.tabuleiro.habilitarPecasLocais(this.jogadorLocal.getIdJogador());
			retorno = Respostas.SELECIONAR_UMA_PECA_LOCAL;
			
			break;
		
		case SAVE_THE_KING:
			this.setEstadoAtual(EstadoJogo.AGUARDANDO_SELECIONAR_PECA_LOCAL_NAO_REI);
			this.tabuleiro.habilitarPecasLocaisNaoRei(this.jogadorLocal.getIdJogador());
			retorno = Respostas.SELECIONAR_UMA_PECA_LOCAL_NAO_REI;
			break;
			
			
		case MAOS_AO_ALTO:
			this.setEstadoAtual(EstadoJogo.AGUARDANDO_SELECIONAR_PECA_ADVERSARIA);
			this.tabuleiro.habilitarPecasAdversarias(this.jogadorLocal.getIdJogador());
			retorno = Respostas.SELECIONAR_UMA_PECA_ADVERSARIA;
			break;
	
		case SACRIFICIO:
			this.setEstadoAtual(EstadoJogo.AGUARDANDO_SELECIONAR_PECA_LOCAL_NAO_REI);
			this.tabuleiro.habilitarPecasLocaisNaoRei(this.jogadorLocal.getIdJogador());
			retorno = Respostas.SELECIONAR_UMA_PECA_LOCAL_NAO_REI;
			break;
			
		case ESCUDOS:
		case SILENCIO_POR_FAVOR:
			this.setEstadoAtual(EstadoJogo.ENVIANDO_JOGADA);
			retorno = Respostas.ENVIAR_JOGADA;
			break;

		case NENHUMA:
			retorno = Respostas.SEM_CARTA_USADA;
			break;
		}
		
		return retorno;
	}
	
	public Acao passarEtapa() {
		this.tabuleiro.desabilitarTodasAsCasas();
		Acao retorno = null;
		Respostas r;
		switch(this.etapaAtual) {
		case AGUARDANDO_ADVERSARIO:
			// Executa passos da etapa de compra, e envia a jogada
			r = this.setupInicioTurno();
			// Se ao fazer o setup do inicio do turno, o jogador tiver que comprar uma carta, 
			// Mas não houver cartas no deck, ele perde o jogo
			if (r == Respostas.VITORIA_DO_OPONENTE) {
				return this.finalizarJogo();
			}
			
			retorno = new Acao(this.jogadorLocal.getCartasMao(),
							   this.jogadorLocal.getCartasDeck(),
							   this.jogadorLocal.getCartasDescarte(), 
							   null,
							   this.tabuleiro,
							   null,
							   false, false);
			break;

		case USO_CARTA_COMECO:
			if (this.getEstadoAtual() != EstadoJogo.ENVIANDO_JOGADA) {
				this.cartaUsada = CartaIdentificacao.NENHUMA;
			}
			retorno = this.criarAcaoUsoDeCarta();
			r = this.executaEtapaDano();
	
			if (r == Respostas.VITORIA_DO_OPONENTE) {
				return this.finalizarJogo();
			}

			this.setupMovimento();
			break;

		case MOVIMENTO:
			r = this.setupFimTurno();
			if (r == Respostas.OK) {
				retorno = new Acao(this.jogadorLocal.getCartasMao(),
						this.jogadorLocal.getCartasDeck(),
						this.jogadorLocal.getCartasDescarte(), 
						null,
						this.tabuleiro,
						Etapa.MOVIMENTO,
						false, false);
			} else {
				// Caso o jogador  já tenha usado carta, passa a vez.
				retorno = new Acao(this.jogadorLocal.getCartasMao(),
						this.jogadorLocal.getCartasDeck(),
						this.jogadorLocal.getCartasDescarte(), 
						null,
						this.tabuleiro,
						Etapa.USO_CARTA_FIM,
						false, false);
			}
			break;

		case USO_CARTA_FIM:
			// Setup para passagem do turno para o adversário
			if (this.getEstadoAtual() != EstadoJogo.ENVIANDO_JOGADA) {
				this.cartaUsada = CartaIdentificacao.NENHUMA;
			}
			retorno = this.criarAcaoUsoDeCarta();
			this.setupEspera();		
		}
		return retorno;
	}

	public Acao finalizarJogo() {
		// TODO Executa finalização do jogo
		this.partidaAndamento = false;
		
		return new Acao(0, 0, 0, null, this.getTabuleiro(), null, false, true);
	}

	private Acao criarAcaoUsoDeCarta() {
		Acao retorno;
		if (this.cartaUsada != CartaIdentificacao.NENHUMA) {		
			Carta carta = this.jogadorLocal.pegarCartaDaMao(cartaUsada);
			retorno = carta.aplicarEfeito(this.tabuleiro, 
							this.jogadorLocal.getIdJogador() == 1 ? 2 : 1, 
							this.pecasSelecionadas,
							this.posicaoSelecionada, 
							this.jogadorLocal, 
							this.etapaAtual);
			this.tabuleiro = retorno.getTabuleiroModificado();
		} else {
			retorno = new Acao(this.jogadorLocal.getCartasMao(), 
							   this.jogadorLocal.getCartasDeck(),
							   this.jogadorLocal.getCartasDescarte(),
							   this.cartaUsada,
							   this.tabuleiro,
							   this.etapaAtual, 
							   false, false);
		}	
		
		return retorno;
	}

	public Respostas posicaoClicada(int x, int y) {
		this.tabuleiro.desabilitarTodasAsCasas();
		switch (this.etapaAtual) {
		case MOVIMENTO:
			return this.posicaoClicadaMovimentoPeca(x, y);
		case USO_CARTA_COMECO:
		case USO_CARTA_FIM:
			return this.posicaoClicadaUsoDeCarta(x, y);	
		case AGUARDANDO_ADVERSARIO:
		default:
			return Respostas.NAO_EH_O_MOMENTO;
		}
	}

	private Respostas posicaoClicadaMovimentoPeca(int x, int y) {
		if (this.getEstadoAtual() == EstadoJogo.AGUARDANDO_SELECIONAR_PECA_MOVIMENTO) {
			Peca peca = this.tabuleiro.pegarPecaDoJogadorNaPosicao(this.jogadorLocal.getIdJogador(), x, y);
			this.pecasSelecionadas.add(peca);
			
			switch(peca.getId()) {
			case BISPO_BRANCO:
			case BISPO_PRETO:
				this.tabuleiro.habilitarMovimentoBispo(peca.getNumeroDeCasas(), x, y, this.jogadorLocal.getIdJogador());
				break;
			case REI_BRANCO:
			case REI_PRETO:
				this.tabuleiro.habilitarMovimentoRei(peca.getNumeroDeCasas(), x, y, this.jogadorLocal.getIdJogador());
				break;
			case TORRE_BRANCA:
			case TORRE_PRETA:
				this.tabuleiro.habilitarMovimentoTorre(peca.getNumeroDeCasas(), x, y, this.jogadorLocal.getIdJogador());
				break;			
			}
			
			this.setEstadoAtual(EstadoJogo.AGUARDANDO_SELECIONAR_POSICAO_MOVIMENTO);
			
			return Respostas.SELECIONAR_POSICAO;

		} else if(this.getEstadoAtual() == EstadoJogo.AGUARDANDO_SELECIONAR_POSICAO_MOVIMENTO) {
			this.tabuleiro.moverPecaParaPosicao(this.pecasSelecionadas.remove(0), x, y);
			
			this.setEstadoAtual(EstadoJogo.ENVIANDO_JOGADA);
			return Respostas.ENVIAR_JOGADA;
		}
		return null;
	}

	private Respostas posicaoClicadaUsoDeCarta(int x, int y) {
		switch(this.getEstadoAtual()) {
		case AGUARDANDO_SELECIONAR_PECA_ADVERSARIA:
			this.pecasSelecionadas.add(this.tabuleiro.pegarPecaDoJogadorNaPosicao(this.jogadorLocal.getIdJogador(), x, y));
			this.setEstadoAtual(EstadoJogo.ENVIANDO_JOGADA);
			return Respostas.ENVIAR_JOGADA;
		
		case AGUARDANDO_SELECIONAR_POSICAO:
			this.posicaoSelecionada = this.tabuleiro.pegarPosicao(x, y);
			this.setEstadoAtual(EstadoJogo.ENVIANDO_JOGADA);
			return Respostas.ENVIAR_JOGADA;

			
		case AGUARDNADO_SELECIONAR_PECA_LOCAL:
			switch(this.cartaUsada) {
			case CORREDORES_EXPERIENTES:
			case SAVE_THE_KING:
				this.pecasSelecionadas.add(this.tabuleiro.pegarPecaDoJogadorNaPosicao(this.jogadorLocal.getIdJogador(), x, y));
				this.setEstadoAtual(EstadoJogo.ENVIANDO_JOGADA);
				return Respostas.ENVIAR_JOGADA;

			case MOVIMENTO_BRUSCO:
				this.pecasSelecionadas.add(this.tabuleiro.pegarPecaDoJogadorNaPosicao(this.jogadorLocal.getIdJogador(), x, y));
				this.tabuleiro.habilitarMovimentoBrusco(x, y, this.jogadorLocal.getIdJogador());
				this.setEstadoAtual(EstadoJogo.AGUARDANDO_SELECIONAR_POSICAO);
				return Respostas.SELECIONAR_POSICAO;

			case SAIR_PELA_TANGENTE:
				this.pecasSelecionadas.add(this.tabuleiro.pegarPecaDoJogadorNaPosicao(this.jogadorLocal.getIdJogador(), x, y));
				this.tabuleiro.habilitarSairPelaTangente(x, y, this.jogadorLocal.getIdJogador());
				this.setEstadoAtual(EstadoJogo.AGUARDANDO_SELECIONAR_POSICAO);
				return Respostas.SELECIONAR_POSICAO;

			case SACRIFICIO:				
			case ESCUDOS:
			case MAOS_AO_ALTO:
			case SILENCIO_POR_FAVOR:
			case NENHUMA:
				this.setEstadoAtual(EstadoJogo.ENVIANDO_JOGADA);
				return Respostas.ENVIAR_JOGADA;
			}
			
			break;		
		case AGUARDANDO_SELECIONAR_PECA_ADVERSARIA_NAO_REI:
			// Só pode ser o segundo click da carta Sacrifício necessário
			int idAdversario = this.jogadorLocal.getIdJogador() == 1 ? 2 : 1;
			this.pecasSelecionadas.add(this.tabuleiro.pegarPecaDoJogadorNaPosicao(idAdversario, x, y));
			this.setEstadoAtual(EstadoJogo.ENVIANDO_JOGADA);
			return Respostas.ENVIAR_JOGADA;
	
		case AGUARDANDO_SELECIONAR_PECA_LOCAL_NAO_REI:
			switch (this.cartaUsada) {
			case SAVE_THE_KING:
				this.pecasSelecionadas.add(this.pegarReiDoJogador(this.jogadorLocal.getIdJogador()));
				this.pecasSelecionadas.add(this.tabuleiro.pegarPecaDoJogadorNaPosicao(this.jogadorLocal.getIdJogador(), x, y));
				this.setEstadoAtual(EstadoJogo.ENVIANDO_JOGADA);
				return Respostas.ENVIAR_JOGADA;

			case SACRIFICIO:
				this.pecasSelecionadas.add(this.tabuleiro.pegarPecaDoJogadorNaPosicao(this.jogadorLocal.getIdJogador(), x, y));
				this.tabuleiro.habilitarPecasAdversariasNaoRei(this.jogadorLocal.getIdJogador());
				this.setEstadoAtual(EstadoJogo.AGUARDANDO_SELECIONAR_PECA_ADVERSARIA_NAO_REI);
				return Respostas.SELECIONAR_POSICAO;

			case CORREDORES_EXPERIENTES:
			case ESCUDOS:
			case MAOS_AO_ALTO:
			case MOVIMENTO_BRUSCO:
			case NENHUMA:
			case SAIR_PELA_TANGENTE:
			case SILENCIO_POR_FAVOR:
				return Respostas.OK;
			}

		case AGUARDANDO_USO_CARTA:
		case AGUARDANDO_SELECIONAR_PECA_MOVIMENTO:
		case AGUARDANDO_SELECIONAR_POSICAO_MOVIMENTO:
		case AGUARDANDO_ADVERSARIO:
		case ENVIANDO_JOGADA:
			return Respostas.OK;
		}
		
		return null;
	}

	public Respostas recebeJogada(Acao jogada) {
		// Atualiza o tabuleiro
		this.tabuleiro = jogada.getTabuleiroModificado();
		this.tabuleiro.girarTabuleiro();
		this.tabuleiro.desabilitarTodasAsCasas();
		
		// Caso o oponente tenha usado silêncio, desativa uso de cartas do
		// jogador local
		if (jogada.isUsouSilencio() ) {
			this.jogadorLocal.setPodeUsarCarta(false);
		}

		// TODO interpretar cada etapa
		
		// Caso seja o fim do turno do oponente, inicia o turno do
		// jogador local
		if (jogada.getEtapa() == Etapa.USO_CARTA_FIM) {
			this.passarEtapa();
			
			if (this.getEstadoAtual() == EstadoJogo.AGUARDANDO_USO_CARTA) {
				return Respostas.USAR_CARTA;
			} else { // Etapa de movimento
				return Respostas.SELECIONAR_UMA_PECA_LOCAL;
			}
		}
		
		return Respostas.OK;		
	}

	public CartaIdentificacao[] idCartasMaoJogadorLocal() {
		return this.jogadorLocal.idCartasMao();
	}

	public Acao botaoPassarEtapa() {
		return this.passarEtapa();
	}

	public EstadoJogo getEstadoAtual() {
		return estadoAtual;
	}

	public void setEstadoAtual(EstadoJogo estadoAtual) {
		this.estadoAtual = estadoAtual;
	}
	
	public Peca pegarReiDoJogador(int idJogador) {
		return this.tabuleiro.pegarReidoJogador(idJogador);
	}

	public int getCartasDeckJogadorLocal() {
		return this.jogadorLocal.getCartasDeck();
	}

	public int getCartasDescarteJogadorLocal() {
		return this.jogadorLocal.getCartasDescarte();
	}

	public int getCartasMaoJogadorLocal() {
		return this.jogadorLocal.getCartasMao();
	}

	public String[] getDescricaoCartasDaMao() {
		return this.jogadorLocal.getDescricaoCartasDaMao();
	}
}
