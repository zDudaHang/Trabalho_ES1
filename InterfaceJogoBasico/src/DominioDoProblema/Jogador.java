package DominioDoProblema;


public class Jogador {
	protected String nome;
	protected ControladorDeCartas controlador;
	protected boolean podeUsarCarta;
	protected boolean usouCarta;
	protected boolean ehVencedor;
	protected boolean ehJogadorDaVez;
	protected boolean jogaPrimeiro;
	protected int posicao;
	
	public Jogador(boolean jogaPrimeiro, String nome) {
		this.jogaPrimeiro = jogaPrimeiro;
		this.nome = nome;
		
		// Controlador para lidar com as cartas do jogador
		this.controlador = new ControladorDeCartas();
		
		// Configurações iniciais
		this.podeUsarCarta = true;
		this.usouCarta = false;
		this.ehJogadorDaVez = jogaPrimeiro;
		
		this.posicao = jogaPrimeiro ? 1 : 2;
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

	public int getPosicao() {
		return posicao;
	}

	public void setPosicao(int posicao) {
		this.posicao = posicao;
	}

	public void setControlador(ControladorDeCartas controlador) {
		this.controlador = controlador;
	}
}
