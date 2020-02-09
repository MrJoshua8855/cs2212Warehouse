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
		//If the username or password is blank, 
		//this proxy does not know how to handle that scenario, 
		//pass to the next in the chain of responsibility
		if(_buyer.getUserName() != "" && _buyer.getPassword() != "") {
			
			//get singleton instance of Buyer Repository
			BuyerManager buyerRepo = BuyerManager.getInstance();
			//Query to match username and password to a registered buyer
			int buyerID = buyerRepo.getBuyerByPassword(_buyer.getUserName(), _buyer.getPassword());
			
			if(buyerID > 0) {  //if the username and password combo returns a user id return true
				System.out.println("Validated from Password Proxy");
				return true;
			}
			else {
				return false;
			}				
		}
		else {
			_next = new PinProxy(_buyer);
			return _next.Validate();
		}
	}
	
	
	
	

}
