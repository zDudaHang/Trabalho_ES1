package InterfaceGrafica;

import DominioDoProblema.ControladorDeCartas;
import DominioDoProblema.Jogo;
import DominioDoProblema.Respostas;
import DominioDoProblema.Cartas.Carta;
import DominioDoProblema.Cartas.CartaIdentificacao;
import DominioDoProblema.Pecas.PecaIdentificacao;
import Rede.Acao;
import Rede.Etapa;
import Rede.InterfaceNetgames;
import br.ufsc.inf.leobr.cliente.Jogada;

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
		this.interfaceJogo.setFaseDoTurno(posicao == 1 ? Etapa.USO_CARTA_COMECO : Etapa.AGUARDANDO_ADVERSARIO);
		this.interfaceJogo.setJogadorAtivo(posicao == 1);
		ControladorDeCartas controlador = this.jogo.getJogadorLocal().getControlador();
		controlador.inicializar();
		this.interfaceJogo.atualizarCartas(controlador);
		
	}

	public void receberJogada(Jogada jogada) {
		// Implementar recebimento da jogada
		Acao acao = (Acao) jogada;
		this.jogo.setTabuleiro(acao.getTabuleiroModificado());
//		this.interfaceJogo.atualizarInformacoes(acao.getQtdDeck(), acao.getQtdDescarte(), acao.getQtdMao());
	}

	public Respostas cartaClicada(CartaIdentificacao id) {
		Respostas retorno = this.jogo.cartaClicada(id);
		this.interfaceJogo.atualizarTabuleiro(this.jogo.getTabuleiro());
		this.interfaceJogo.habilitarCartas(false);
		return retorno;
	}
}
