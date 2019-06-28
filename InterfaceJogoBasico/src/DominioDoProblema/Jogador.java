package DominioDoProblema;

import DominioDoProblema.Cartas.Carta;
import DominioDoProblema.Cartas.CartaIdentificacao;

public class Jogador {
	protected String nome;
	protected ControladorDeCartas controlador;
	protected boolean podeUsarCarta;
	protected boolean ehJogadorDaVez;
	protected int idJogador;
	protected boolean podeLevarDano;
	
	public Jogador(boolean jogaPrimeiro, String nome) {
		this.nome = nome;
		
		// Controlador para lidar com as cartas do jogador
		this.controlador = new ControladorDeCartas();
		this.controlador.inicializar();
		
		// Configurações iniciais
		this.podeUsarCarta = true;
		this.ehJogadorDaVez = jogaPrimeiro;
		
		this.idJogador = jogaPrimeiro ? 1 : 2;
		
		this.podeLevarDano = true;
	}
	
	public ControladorDeCartas getControlador() {
		return this.controlador;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean podeUsarCarta() {
		return podeUsarCarta;
	}

	public void setPodeUsarCarta(boolean podeUsarCarta) {
		this.podeUsarCarta = podeUsarCarta;
	}

	public boolean ehJogadorDaVez() {
		return ehJogadorDaVez;
	}

	public void setJogadorDaVez(boolean ehJogadorDaVez) {
		this.ehJogadorDaVez = ehJogadorDaVez;
	}

	public int getIdJogador() {
		return idJogador;
	}

	public void setIdJogador(int id) {
		this.idJogador = id;
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

	public boolean podeLevarDano() {
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
		return this.getCartasMao() == 0 || this.getCartasDeck() < 5;
	}

	public boolean comprarCarta() {
		return this.controlador.comprarCarta();
	}

	public CartaIdentificacao[] idCartasMao() {
		return this.controlador.idCartasMao();
	}

	public String[] getDescricaoCartasDaMao() {
		return this.controlador.getDescricaoCartasDaMao();
	}
}
