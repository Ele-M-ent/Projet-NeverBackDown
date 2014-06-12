package paternObservable;

import model.GridSoldier;
import model.Troupe;

public interface Observateur {
	public void updateScreen( Troupe t, GridSoldier gs);
}
