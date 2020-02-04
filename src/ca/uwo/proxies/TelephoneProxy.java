package ca.uwo.proxies;


public class TelephoneProxy extends Proxy implements IValidate{
	
	private Buyer _buyer;
	
	public TelephoneProxt(Buyer buyer) {
		_buyer = buyer;
	}
	
	public boolean Validate() {
		//todo
		return false;
	}
}
