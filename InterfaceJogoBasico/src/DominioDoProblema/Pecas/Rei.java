package DominioDoProblema.Pecas;

import DominioDoProblema.Cartas.EstadoPeca;

public class Rei extends Peca  {
	
	public Rei(boolean jogadorLocal) {
		// O rei só se mover 1 casa por vez
		super(1, jogadorLocal);
	}
	
	public boolean estaMorto() {
		return this.estado == EstadoPeca.REMOVIDA;
	}
	
	@Override
	void movimentar() {
		// TODO Auto-generated method stub
		
	}

}
