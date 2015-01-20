package listener;

import java.util.EventListener;

import event.CandyEvent;

public interface Observerateur extends EventListener {
		public void updates(CandyEvent e);

}
