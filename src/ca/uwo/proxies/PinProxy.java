package ca.uwo.proxies;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import ca.uwo.client.Buyer;

public class PinProxy implements IValidate{

	
	private Buyer _buyer;
	
	public PinProxy(Buyer buyer){
		_buyer = buyer;
	}
	
	public boolean Validate() {
		//TODO
		return false;
	}
}
