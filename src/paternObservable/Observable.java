
package paternObservable;


public interface Observable {

		public void addObservateur(Observateur obs);
		public void deleteObservateurs();
		public void notyfyAllieChange();
		public void notyfyEnnemyChange();
}
