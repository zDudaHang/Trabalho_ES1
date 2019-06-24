package InterfaceGrafica;

import DominioDoProblema.Etapa;
import DominioDoProblema.Jogo;
import DominioDoProblema.Respostas;
import DominioDoProblema.Cartas.CartaIdentificacao;
import Rede.Acao;
import Rede.InterfaceNetgames;
import br.ufsc.inf.leobr.cliente.exception.NaoJogandoException;

public class AtorJogador {
	
	protected InterfaceNetgames ngServer;
	protected InterfaceJogo interfaceJogo;
	protected Jogo jogo;

	public AtorJogador(InterfaceJogo interfaceJogo) {
		this.interfaceJogo = interfaceJogo;
		ngServer = new InterfaceNetgames(this);
		jogo = new Jogo();
	}

	public String conectar(String string, String string2) {
		String mensagem = "Condicao para conexao nao atendida (defina qual)";
		boolean permitido = jogo.permitidoConectar();
		if (permitido) {
			mensagem = ngServer.conectar(string, string2);
			if (mensagem.equals("Sucesso: conectado a Netgames Server")) {
				jogo.definirConectado(true);
			}
		}
		return mensagem;
	}
	
	public String desconectar() {
		String mensagem = "Condicao para desconexao nao atendida (defina qual)";
		boolean permitido = jogo.permitidoDesconectar();
		if (permitido) {
			mensagem = ngServer.desconectar();
			if (mensagem.equals("Sucesso: desconectado de Netgames Server")) {
				jogo.definirConectado(false);
			}
		}
		return mensagem;
	}
	
	public String iniciarPartida() {
		String mensagem = "Condicao para iniciar partida nao atendida (defina qual)";
		boolean permitido = jogo.permitidoIniciarPartida();
		if (permitido) {
			mensagem = ngServer.iniciarPartida();
		}
		return mensagem;
	}

	public void iniciarNovaPartida(Integer posicao, String nomeAdversario, String nomeJogadorLocal) {
		this.jogo.iniciarNovaPartida(posicao, nomeAdversario, nomeJogadorLocal);
		this.interfaceJogo.atualizarTabuleiro(this.jogo.getTabuleiro());
		this.interfaceJogo.informacoes.setNomeJogador(nomeJogadorLocal);
		this.interfaceJogo.informacoes.setNomeOponente(nomeAdversario);
		this.interfaceJogo.setEtapaDoTurno(posicao == 1 ? Etapa.USO_CARTA_COMECO : Etapa.AGUARDANDO_ADVERSARIO);
		this.interfaceJogo.atualizarTextos(posicao == 1 ? Respostas.USAR_CARTA : Respostas.AGUARDAR_ADVERSARIO);
		this.interfaceJogo.atualizarCartas(this.jogo.idCartasMaoJogadorLocal());
		
	}

	public void receberJogada(Acao jogada) {
		// Implementar recebimento da jogada
		Acao acao = (Acao) jogada;
		Respostas r = this.jogo.recebeJogada(jogada);
		this.interfaceJogo.atualizarTabuleiro(acao.getTabuleiroModificado());
		
		if (r == Respostas.VITORIA_DO_OPONENTE) {
			this.jogo.finalizarJogo();

		} else if (r == Respostas.USAR_CARTA || r == Respostas.SELECIONAR_UMA_PECA_LOCAL) {
			this.interfaceJogo.atualizarTextos(r);
			this.interfaceJogo.setEtapaDoTurno(this.jogo.getEtapaAtual());
		}
		
		
		if (jogada.isVitoriaOponente()) {
			this.interfaceJogo.finalizarJogoComVitoria();
		}
	}

	public Respostas cartaClicada(CartaIdentificacao id) {
		Respostas retorno = this.jogo.cartaClicada(id);

		this.interfaceJogo.atualizarTabuleiro(this.jogo.getTabuleiro());
		
		if (retorno == Respostas.ENVIAR_JOGADA) {
			Acao jogada = this.jogo.passarEtapa();
			
			if (jogada.isVitoriaOponente()) {
				this.interfaceJogo.finalizarJogoComDerrota();
			}

			try {
				this.interfaceJogo.atualizarCartas(this.jogo.idCartasMaoJogadorLocal());
				this.ngServer.enviarJogada(jogada);
			} catch (NaoJogandoException e) {
				e.printStackTrace();
			}
			
			this.interfaceJogo.setEtapaDoTurno(this.jogo.getEtapaAtual());
			switch (this.jogo.getEtapaAtual()) {
			case AGUARDANDO_ADVERSARIO:
				return Respostas.AGUARDAR_ADVERSARIO;
			case MOVIMENTO:
				return Respostas.SELECIONAR_UMA_PECA_LOCAL;
				
			case USO_CARTA_COMECO:
				return Respostas.USAR_CARTA;
				
			case USO_CARTA_FIM:
				return Respostas.USAR_CARTA;
			}
		}
		
		// Atualiza as cartas e tabuleiro do jogador
		this.interfaceJogo.atualizarTabuleiro(this.jogo.getTabuleiro());
		this.interfaceJogo.atualizarCartas(this.jogo.idCartasMaoJogadorLocal());
		return retorno;
	}

	public Respostas posicaoClicada(int x, int y) {
		Respostas resposta = this.jogo.posicaoClicada(x, y);
		this.interfaceJogo.atualizarTextos(resposta);
		// Criar a jogada para ser enviada
		if (resposta == Respostas.ENVIAR_JOGADA) {
			Acao jogada = this.jogo.passarEtapa();
			try {
				this.interfaceJogo.atualizarCartas(this.jogo.idCartasMaoJogadorLocal());
				this.ngServer.enviarJogada(jogada);
			} catch (NaoJogandoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			this.interfaceJogo.atualizarTabuleiro(this.jogo.getTabuleiro());
			
			this.interfaceJogo.setEtapaDoTurno(this.jogo.getEtapaAtual());
			switch (this.jogo.getEtapaAtual()) {
			case AGUARDANDO_ADVERSARIO:
				return Respostas.AGUARDAR_ADVERSARIO;

			case MOVIMENTO:
				return Respostas.SELECIONAR_UMA_PECA_LOCAL;
				
			case USO_CARTA_COMECO:
				return Respostas.USAR_CARTA;
				
			case USO_CARTA_FIM:
				return Respostas.USAR_CARTA;
			}
		}
		this.interfaceJogo.atualizarTabuleiro(this.jogo.getTabuleiro());
		return resposta;
	}

	public Respostas botaoPassarEtapa() {
		Respostas retorno = null;
		Acao jogada = this.jogo.botaoPassarEtapa();

		try {
			// Se o jogador local não estiver em modo de espera, enviar a jogada
			if (jogada.getEtapa() != Etapa.AGUARDANDO_ADVERSARIO) {
				this.interfaceJogo.atualizarCartas(this.jogo.idCartasMaoJogadorLocal());
				this.ngServer.enviarJogada(jogada);
			}
			
			switch (jogada.getEtapa()) {
			case AGUARDANDO_ADVERSARIO:
				// Jogador não devia estar tentando passar etapa.
				retorno = Respostas.NAO_EH_O_MOMENTO;
				break;
			case MOVIMENTO:
				// Uso carta fim do turno
				retorno = Respostas.USAR_CARTA;
				this.interfaceJogo.setEtapaDoTurno(Etapa.USO_CARTA_FIM);
				break;
			case USO_CARTA_COMECO:
				// Hora de movimentar
				retorno = Respostas.SELECIONAR_UMA_PECA_LOCAL;
				this.interfaceJogo.setEtapaDoTurno(Etapa.MOVIMENTO);
				break;
			case USO_CARTA_FIM:
				// Turno foi finalizado
				retorno = Respostas.AGUARDAR_ADVERSARIO;
				this.interfaceJogo.setEtapaDoTurno(Etapa.AGUARDANDO_ADVERSARIO);
				break;
			
			}
		} catch (NaoJogandoException e) {
			e.printStackTrace();
		}
		
		this.interfaceJogo.atualizarTabuleiro(this.jogo.getTabuleiro());
		return retorno;
		
	}
}
