package ca.uwo.proxies;

import java.util.Map;

import ca.uwo.client.Buyer;
import ca.uwo.client.Supplier;
//import ca.uwo.frontend.Facade;

public class WelcomeProxy extends Proxy {
	/**
	 * constructor for WelcomeProxy class.
	 */
	public WelcomeProxy() {
	}

	@Override
	public void placeOrder(Map<String, Integer> orderDetails, Buyer buyer) {
		
		SupplierProxy proxy = new SupplierProxy();
		proxy.placeOrder(orderDetails, buyer);

	}

	@Override
	public void restock(Map<String, Integer> restockDetails, Supplier supplier) {

		SupplierProxy proxy = new SupplierProxy();
		proxy.restock(restockDetails, supplier);

	}

}
