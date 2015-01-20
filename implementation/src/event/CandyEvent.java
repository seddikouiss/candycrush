package event;

import java.util.EventObject;

public abstract class CandyEvent extends EventObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Object value;
	public CandyEvent(Object source, Object value) {
		super(source);
		this.value = value ;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
}
