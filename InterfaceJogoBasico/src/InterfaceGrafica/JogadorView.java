package InterfaceGrafica;

public class JogadorView {	
	protected InformacoesDeCartas info;
	protected String nome;
//	protected pecas Peca;

	public JogadorView() {
		// Inicializa controlador de cartas do jogador
		info = new InformacoesDeCartas();
		info.setLocation(10, 520);
	}

	public InformacoesDeCartas getInfo() {
		return info;
	}
}
