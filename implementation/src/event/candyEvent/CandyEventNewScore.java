package event.candyEvent;

import event.CandyEvent;


public class CandyEventNewScore extends CandyEvent{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CandyEventNewScore(Object source, Object value) {
		super(source,value);
	}
}
