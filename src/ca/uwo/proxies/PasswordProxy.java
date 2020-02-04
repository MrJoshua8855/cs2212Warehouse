package ca.uwo.proxies;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PasswordProxy extends Proxy implements IValidate{
	
	private HashMap<> buyersList;
	private Buyer _buyer;
	private IValidate _next;
	
	public PasswordProxy(Buyer buyer) {
		_buyer = buyer;		
	}
	
	
	public boolean Validate() {
	
		if(_buyer.getUserName != "" && _buyer.getPassword != "") {
			BuyerManager buyerRepo = new BuyerManager.getInstance();
			int buyerID = buyerRepo.getBuyerByPassword(_buyer.getUserName, _buyer.getPassword);
			if(buyerID != null return true)
				return false;
		}
		else {
			_next = new PinProxy(_buyer);
			return _next.Validate();
		}
	}
	
	
	
	

}
