package ca.uwo.proxies;

import java.util.Map;

import ca.uwo.client.Buyer;
import ca.uwo.client.Supplier;
import ca.uwo.frontend.Facade;

public class SupplierProxy extends Proxy {

	/* pass request along proxy chain if it is not a restock request */
	public void placeOrder(Map<String, Integer> orderDetails, Buyer buyer) {
		LowQuantityProxy proxy = new LowQuantityProxy();
		proxy.placeOrder(orderDetails, buyer);
	}
	
	/* handle supply requests */
	public void restock(Map<String, Integer> restockDetails, Supplier supplier) {
		Facade facade = new Facade();
		facade.restock(restockDetails, supplier);
	}
}
