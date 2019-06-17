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
	
	public TabuleiroView(ActionListener tabuleiroHandler) {
		this.setSize(480, 480);
		posicoes =  new PosicaoView[8][8];
		GridLayout layout = new GridLayout(8, 8);
		this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		this.setLayout(layout);
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				// Configura o click do botão para chamar um listener atrelado ao tabuleiro
				this.posicoes[x][y] = new PosicaoView(x, y, null, tabuleiroHandler);
				this.posicoes[x][y].setEnabled(false);
			}
		}
		for(int y = 0; y < 8; y++) {
			for(int x = 0; x < 8; x++) {
				this.add(this.posicoes[x][y]);
			}
		}
	}
	
	public void atualizarTabuleiro(Tabuleiro tabuleiro) {
		System.out.println("Atualizando o tabu fucking leiro");
		
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				Posicao pos = tabuleiro.pegarPosicao(x, y);
				Peca peca = pos.pegarPrimeiraPeca();
				
				if (peca != null) {
					this.posicoes[x][y].setEnabled(peca.isHabilitada());					
					this.posicoes[x][y].setIcon(new PecaView(peca.getId()).getIcon());
					this.posicoes[x][y].idPeca = peca.getId();

				} else {
					this.setEnabled(false);
				}
			}
		}
	}
}
