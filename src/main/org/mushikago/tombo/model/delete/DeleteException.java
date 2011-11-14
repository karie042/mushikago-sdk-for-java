package org.mushikago.tombo.model.delete;

import org.mushikago.tombo.model.TomboException;

public class DeleteException extends TomboException {
	
	private static final long serialVersionUID = 1L;
	
	public DeleteException(String msg) { super(msg); }
}
