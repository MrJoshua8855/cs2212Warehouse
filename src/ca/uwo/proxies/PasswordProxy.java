package ca.uwo.proxies;


import ca.uwo.client.Buyer;
import ca.uwo.dataAccess.BuyerManager;

public class PasswordProxy implements IValidate{
	
	private Buyer _buyer;
	private IValidate _next;
	
	public PasswordProxy(Buyer buyer) {
		_buyer = buyer;		
	}
	
	
	public boolean Validate() {
	
		if(_buyer.getUserName() != "" && _buyer.getPassword() != "") {
			BuyerManager buyerRepo = BuyerManager.getInstance();
			int buyerID = buyerRepo.getBuyerByPassword(_buyer.getUserName(), _buyer.getPassword());
			if(buyerID > 0) return true;
				return false;
		}
		else {
			_next = new PinProxy(_buyer);
			return _next.Validate();
		}
	}
	
	
	
	

}
