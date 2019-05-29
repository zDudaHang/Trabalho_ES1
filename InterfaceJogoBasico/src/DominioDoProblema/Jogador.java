package DominioDoProblema;

import DominioDoProblema.Pecas.Peca;

public class Jogador {
	protected String nome;
	protected ControladorDeCartas controlador;
	protected boolean podeUsarCarta;
	protected boolean usouCarta;
//	protected Peca[] pecas;
	protected boolean ehVencedor;
	protected boolean ehJogadorDaVez;
	protected boolean jogaPrimeiro;
	
	public Jogador(boolean jogaPrimeiro, String nome) {
		this.jogaPrimeiro = jogaPrimeiro;
		this.nome = nome;
		
		// Controlador para lidar com as cartas do jogador
		this.controlador = new ControladorDeCartas();
		
		// Configurações iniciais
		this.podeUsarCarta = true;
		this.usouCarta = false;
		this.ehJogadorDaVez = jogaPrimeiro;
	}
}
