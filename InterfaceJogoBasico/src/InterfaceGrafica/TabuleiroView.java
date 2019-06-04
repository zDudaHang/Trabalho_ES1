package InterfaceGrafica;

import java.awt.ComponentOrientation;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import DominioDoProblema.Posicao;
import DominioDoProblema.Tabuleiro;
import DominioDoProblema.Pecas.Peca;

public class TabuleiroView extends JPanel {
	protected PosicaoView posicoes[][];
	
	public TabuleiroView() {
		this.setSize(480, 480);
		posicoes =  new PosicaoView[8][8];
		GridLayout layout = new GridLayout(8, 8);
		this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
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
		System.out.println("(" + x + ", " + y + ")");
	}
	
	public void atualizarTabuleiro(Tabuleiro tabuleiro) {
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				Posicao pos = tabuleiro.pegarPosicao(x, y);
				Peca peca = pos.pegarPrimeiraPeca();
				
				if (peca != null) {
					this.posicoes[x][y].setIcon(new PecaView(peca.getId()).getIcon());
				}

			}
		}
	}
}
