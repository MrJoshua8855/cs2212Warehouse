package ca.uwo.proxies;

import ca.uwo.client.Buyer;

public class TelephoneProxy implements IValidate{
	
	private Buyer _buyer;
	
	public TelephoneProxy(Buyer buyer) {
		_buyer = buyer;
	}
	
	public boolean Validate() {
		//todo
		return false;
	}
}
