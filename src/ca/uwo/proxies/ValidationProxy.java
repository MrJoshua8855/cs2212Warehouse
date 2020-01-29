package ca.uwo.proxies;


public class ValidationProxy extends Proxy implements IValidate {
	
	
	private IValidate _next;
	/**
	 * constructor for ValidationProxy class.
	 */
	public void ValidationProxy(IValidate next) {
		_next = next;
	}
	
	public bool Validate(Buyer buyer) {
		
	}

}
