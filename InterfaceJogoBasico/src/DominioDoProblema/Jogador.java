package DominioDoProblema;

import DominioDoProblema.Cartas.Carta;
import DominioDoProblema.Cartas.CartaIdentificacao;
import Rede.Acao;

public class Jogador {
	protected String nome;
	protected ControladorDeCartas controlador;
	protected boolean podeUsarCarta;
	protected boolean usouCarta;
	protected boolean ehVencedor;
	protected boolean ehJogadorDaVez;
	protected boolean jogaPrimeiro;
	protected int idJogador;
	protected boolean podeLevarDano;
	
	public Jogador(boolean jogaPrimeiro, String nome) {
		this.jogaPrimeiro = jogaPrimeiro;
		this.nome = nome;
		
		// Controlador para lidar com as cartas do jogador
		this.controlador = new ControladorDeCartas();
		this.controlador.inicializar();
		
		// Configurações iniciais
		this.podeUsarCarta = true;
		this.usouCarta = false;
		this.ehJogadorDaVez = jogaPrimeiro;
		
		this.idJogador = jogaPrimeiro ? 1 : 2;
		
		this.podeLevarDano = true;
	}
	
	public ControladorDeCartas getControlador() {
		return this.controlador;
	}

	
	public boolean isJogadorDaVez() {
		return ehJogadorDaVez;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isPodeUsarCarta() {
		return podeUsarCarta;
	}

	public void setPodeUsarCarta(boolean podeUsarCarta) {
		this.podeUsarCarta = podeUsarCarta;
	}

	public boolean isUsouCarta() {
		return usouCarta;
	}

	public void setUsouCarta(boolean usouCarta) {
		this.usouCarta = usouCarta;
	}

	public boolean isEhVencedor() {
		return ehVencedor;
	}

	public void setEhVencedor(boolean ehVencedor) {
		this.ehVencedor = ehVencedor;
	}

	public boolean isEhJogadorDaVez() {
		return ehJogadorDaVez;
	}

	public void setEhJogadorDaVez(boolean ehJogadorDaVez) {
		this.ehJogadorDaVez = ehJogadorDaVez;
	}

	public boolean isJogaPrimeiro() {
		return jogaPrimeiro;
	}

	public void setJogaPrimeiro(boolean jogaPrimeiro) {
		this.jogaPrimeiro = jogaPrimeiro;
	}

	public int getIdJogador() {
		return idJogador;
	}

	public void setPosicao(int posicao) {
		this.idJogador = posicao;
	}

	public void setControlador(ControladorDeCartas controlador) {
		this.controlador = controlador;
	}

	public int getCartasMao() {
		return this.controlador.getTamanhoMao();
	}

	public int getCartasDeck() {
		return this.controlador.getTamanhoDeck();
	}

	public int getCartasDescarte() {
		return this.controlador.getTamanhoDescarte();
	}

	public boolean isPodeLevarDano() {
		return podeLevarDano;
	}

	public void setPodeLevarDano(boolean podeLevarDano) {
		this.podeLevarDano = podeLevarDano;
	}

	public Carta pegarCartaDaMao(CartaIdentificacao cartaUsada) {
		// Encontra carta na mão do jogador
		Carta carta = this.controlador.getCartaDaMao(cartaUsada);
		
		return carta;
	}

	public boolean estaNoEstadoDeCompra() {
		return this.getCartasDescarte() >= 5;
	}

	public boolean comprarCarta() {
		return this.controlador.comprarCarta();
	}

	public CartaIdentificacao[] idCartasMao() {
		return this.controlador.idCartasMao();
	}
}
