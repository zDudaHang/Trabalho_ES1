package DominioDoProblema.Pecas;

import java.util.UUID;

import DominioDoProblema.Pecas.EstadoPeca;

public class Rei extends Peca  {
	
	public Rei(int idJogador, boolean jogadorLocalComeca) {
		// O rei só se mover 1 casa por vez
		super(1, jogadorLocalComeca ? PecaIdentificacao.REI_BRANCO : PecaIdentificacao.REI_PRETO, idJogador);
	}
	
	public boolean estaMorto() {
		return this.estado == EstadoPeca.REMOVIDA;
	}
	
	@Override
	void movimentar() {
		// TODO Auto-generated method stub
		
	}

}
