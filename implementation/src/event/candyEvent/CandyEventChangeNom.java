package event.candyEvent;

import event.CandyEvent;

public class CandyEventChangeNom extends CandyEvent {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CandyEventChangeNom(Object source, Object value) {
		super(source,value);
	}
}
