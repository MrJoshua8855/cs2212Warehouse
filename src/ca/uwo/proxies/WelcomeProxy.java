package ca.uwo.proxies;

import java.util.Map;

import ca.uwo.client.Buyer;
import ca.uwo.client.Supplier;
//import ca.uwo.frontend.Facade;

public class WelcomeProxy extends Proxy {

	private static WelcomeProxy instance = null;
	
	/**
	 * there should be only one instance using the Singleton Design Pattern
	 * @return the instance.
	 */
	public static WelcomeProxy getInstance() {
		if(instance == null) {
			instance = new WelcomeProxy();
		}
		return instance;
	}
	
	/**
	 * constructor for WelcomeProxy class.
	 */
	private WelcomeProxy() {
	}
	

	@Override
	public void placeOrder(Map<String, Integer> orderDetails, Buyer buyer) {
		
		SupplierProxy proxy = SupplierProxy.getInstance();
		proxy.placeOrder(orderDetails, buyer);

	}

	@Override
	public void restock(Map<String, Integer> restockDetails, Supplier supplier) {

		SupplierProxy proxy = SupplierProxy.getInstance();
		proxy.restock(restockDetails, supplier);

	}

}
