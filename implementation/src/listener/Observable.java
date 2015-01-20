package listener;

import listener.Observerateur;

public interface Observable {
	void addListener(Observerateur l) ;
	void removeListener(Observerateur l) ;


}
