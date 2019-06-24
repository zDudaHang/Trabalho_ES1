package InterfaceGrafica;

import javax.swing.JLabel;

public class AutoBreakLineLabel extends JLabel {
	@Override
	public void setText(String text) {
		super.setText("<html><p style=\"width:100px\">"+text+"</p></html>");
	}
}
