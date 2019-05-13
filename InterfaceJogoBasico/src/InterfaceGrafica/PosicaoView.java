package InterfaceGrafica;

import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;

public class PosicaoView extends JButton {
	protected int x;
	protected int y;
	
	public PosicaoView(int x, int y, ActionListener action) {
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
		this.setToolTipText("(" + x + ", " + y + ")");
		
		this.addActionListener(action);
	}

	public PosicaoView(Icon arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public PosicaoView(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public PosicaoView(Action arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public PosicaoView(String arg0, Icon arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

}
