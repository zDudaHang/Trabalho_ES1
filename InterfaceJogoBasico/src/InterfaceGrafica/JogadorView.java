package InterfaceGrafica;

import java.awt.event.MouseAdapter;

public class JogadorView {	
	protected InformacoesDeCartas info;
	protected String nome;
//	protected pecas Peca;

	public JogadorView(MouseAdapter cardHandler) {
		// Inicializa controlador de cartas do jogador
		info = new InformacoesDeCartas(cardHandler);
		info.setLocation(10, 520);
	}

	public InformacoesDeCartas getInfo() {
		return info;
	}

	public void habilitarCartas(boolean habilitar) {
		for (int i = 0; i < this.info.mao.size(); i++) {
			this.info.mao.get(i).setEnabled(habilitar);
		}
	}
}
