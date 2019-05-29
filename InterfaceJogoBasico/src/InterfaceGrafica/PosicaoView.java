package InterfaceGrafica;

import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;

import DominioDoProblema.Pecas.PecaIdentificacao;

public class PosicaoView extends JButton {
	protected int x;
	protected int y;
	
	public PosicaoView(int x, int y, PecaView pecaview, ActionListener action) {
		super();
		
		this.setBorderPainted(false);
		
		// Define posição no tabuleiro
		this.x = x;
		this.y = y;
		if (x%2 == y%2) {
			this.setBackground(new Color(255, 255, 255));
		} else {
			this.setBackground(new Color(0, 0, 0));
		}
		
		// Fixa tamanho do botão
		this.setSize(60, 60);
		
		this.addActionListener(action);
		
		
		if (pecaview != null) {
			this.setIcon(pecaview.getIcon());
		}
	}
}
