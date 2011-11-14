package org.mushikago.tombo.model;

public abstract class TomboException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public TomboException(String msg) {
		super(msg);
	}
}
