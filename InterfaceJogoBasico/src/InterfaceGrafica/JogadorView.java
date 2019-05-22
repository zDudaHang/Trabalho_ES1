package InterfaceGrafica;

public class JogadorView {	
	protected ControladorDeCartas controlador;
	protected String nome;
//	protected pecas Peca;

	public JogadorView() {
		// Inicializa controlador de cartas do jogador
		controlador = new ControladorDeCartas();
		controlador.setLocation(10, 520);
	}


	public ControladorDeCartas getControlador() {
		return controlador;
	}
}
