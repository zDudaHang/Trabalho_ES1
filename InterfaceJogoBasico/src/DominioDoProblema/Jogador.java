package DominioDoProblema;


public class Jogador {
	protected String nome;
	protected ControladorDeCartas controlador;
	protected boolean podeUsarCarta;
	protected boolean usouCarta;
	protected boolean ehVencedor;
	protected boolean ehJogadorDaVez;
	protected boolean jogaPrimeiro;
	protected boolean ehBranco;
	
	public Jogador(boolean jogaPrimeiro, String nome) {
		this.jogaPrimeiro = jogaPrimeiro;
		this.nome = nome;
		
		// Controlador para lidar com as cartas do jogador
		this.controlador = new ControladorDeCartas();
		
		// Configurações iniciais
		this.podeUsarCarta = true;
		this.usouCarta = false;
		this.ehJogadorDaVez = jogaPrimeiro;
		this.ehBranco = jogaPrimeiro;
	}
	
	public ControladorDeCartas getControlador() {
		return this.controlador;
	}
	
	public boolean isBranco() {
		return ehBranco;
	}
	
	public boolean isJogadorDaVez() {
		return ehJogadorDaVez;
	}
}
