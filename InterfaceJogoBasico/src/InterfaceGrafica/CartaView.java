package InterfaceGrafica;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class CartaView extends JLabel {
	public CartaView() {
		this.setHorizontalAlignment(CENTER);
		this.setVerticalAlignment(CENTER);

		// TODO
		URL url = getClass().getResource("/backcard.png");
		this.setToolTipText("This is a default card, for testing purposes. If you use this card, not will actually happen.");
		ImageIcon  img = new ImageIcon(url);
		this.setIcon(img);
		
		
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				usarCarta();
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				setBorder(null);
			}
		});
		
	}
	protected void usarCarta() {
		// TODO
		JOptionPane.showMessageDialog(this, "Well, as I said, nothing happens.");
	}
}
