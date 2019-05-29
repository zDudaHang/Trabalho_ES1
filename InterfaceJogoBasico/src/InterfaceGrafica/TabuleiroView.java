package InterfaceGrafica;

import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class TabuleiroView extends JPanel {
	protected PosicaoView posicoes[][] = new PosicaoView[8][8];
	
	public TabuleiroView() {
		this.setSize(480, 480);
		GridLayout layout = new GridLayout(8, 8);
		this.setLayout(layout);
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				final int X = x;
				final int Y = y;
				// Configura o click do botão para chamar um listener atrelado ao tabuleiro
				this.posicoes[x][y] = new PosicaoView(x, y, null, new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						buttonClicked(X, Y);					
					}
				});

				this.add(this.posicoes[x][y]);
			}
		}
	}
	
	public void buttonClicked(int x, int y) {
		
	}
}
