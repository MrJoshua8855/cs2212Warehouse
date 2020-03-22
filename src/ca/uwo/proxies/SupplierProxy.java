package ca.uwo.proxies;

import java.util.Map;

import ca.uwo.client.Buyer;
import ca.uwo.client.Supplier;
import ca.uwo.frontend.Facade;

public class SupplierProxy extends Proxy {
	
	private static SupplierProxy instance = null;
	
	/**
	 * there should be only one instance using the Singleton Design Pattern
	 * @return the instance.
	 */
	public static SupplierProxy getInstance() {
		if(instance == null) {
			instance = new SupplierProxy();
		}
		return instance;
	}
	
	private SupplierProxy() {
	}

	/* pass request along proxy chain if it is not a restock request */
	public void placeOrder(Map<String, Integer> orderDetails, Buyer buyer) {
		LowQuantityProxy proxy = LowQuantityProxy.getInstance();
		proxy.placeOrder(orderDetails, buyer);
	}
	
	/* handle supply requests */
	public void restock(Map<String, Integer> restockDetails, Supplier supplier) {
		Facade facade = Facade.getInstance();
		facade.restock(restockDetails, supplier);
	}
}
