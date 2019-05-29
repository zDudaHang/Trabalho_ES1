package InterfaceGrafica;

import java.awt.GridLayout;

public class InformacoesDeCartas extends PanelArredondado {
	// TODO na porra toda
	protected CartaView[] deck = new CartaView[10];
	protected CartaView[] mao = new CartaView[5];
	protected CartaView[] descarte = new CartaView[10];

	public InformacoesDeCartas() {
		super();
		GridLayout layout = new GridLayout(1, 5);
		layout.setVgap(2);
		layout.setHgap(5);
		this.setLayout(layout);
		
		this.setSize(680, 180);
		
		// Gambiarrinha só pra mostrar pro professor
		for (int i = 0; i < mao.length; i++) {
			this.mao[i] = new CartaView();
			this.add(this.mao[i], 0, i);
		}
	}
}
