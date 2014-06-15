package paternObservable;

import java.util.ArrayList;

import model.GridSoldier;
import model.Troupe;

public interface Observateur {
	public void updateScreenAllie(Troupe t);
	public void updateScreenEnemys(ArrayList<GridSoldier> listeGrid);
}
