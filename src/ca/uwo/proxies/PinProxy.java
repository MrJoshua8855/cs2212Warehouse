package ca.uwo.proxies;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class PinProxy extends Proxy implements IValidate{

	
	private Buyer _buyer;
	
	public PinProxy(Buyer buyer){
		_buyer = buyer;
	}
	
	public int Validate() {
		//TODO
		return false;
	}
}
