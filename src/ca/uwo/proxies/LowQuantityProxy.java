package ca.uwo.proxies;

import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import ca.uwo.client.Buyer;
import ca.uwo.client.Supplier;
import ca.uwo.frontend.Facade;

public class LowQuantityProxy extends Proxy {
	
private static LowQuantityProxy instance = null;
	
	/**
	 * there should be only one instance using the Singleton Design Pattern
	 * @return the instance.
	 */
	public static LowQuantityProxy getInstance() {
		if(instance == null) {
			instance = new LowQuantityProxy();
		}
		return instance;
	}
	
	private LowQuantityProxy() {
	}

	public void placeOrder(Map<String, Integer> orderDetails, Buyer buyer) {

		/* order values returns a Collections<Integer> object from the Map<String, Integer>,
			then we get an Iterator from that which holds the quantities*/
		Iterator<Integer> iter = orderDetails.values().iterator();
		int count = 0;

		/* add up order quantities */
		while (iter.hasNext())
			count += iter.next();

		/* case: this proxy cannot handle the request, pass it on */
		if (count > 10) {
			HighQuantityProxy proxy = HighQuantityProxy.getInstance();
			proxy.placeOrder(orderDetails, buyer);
		}

		/* otherwise, this proxy can handle the request, and so it does */
		else {

			/* this if authenticates the user's credentials and, if successful, handles their request */
			if (true) {//(authenticate(buyer)) {
				Facade facade = Facade.getInstance();
				facade.placeOrder(orderDetails, buyer);
			}

			/* this else is if the user makes a mistake entering their credentials */
			else {
				System.out.println("Please try again.");

				/* try again, handle request upon success */
				if (authenticate(buyer)) {
					Facade facade = Facade.getInstance();
					facade.placeOrder(orderDetails, buyer);
				}

				/* exit if second attempt fails */
				else {
					System.out.println("Sorry, but your password is incorrect. Goodbye.");
					System.exit(0);
				}
			}
		}

	}
	
	/* this should never be called but is required by Proxy */
	public void restock(Map<String, Integer> restockDetails, Supplier supplier) {
		System.out.println("FAILURE IN: LowQuantityProxy\nREASON: This method should never be called.");
		System.exit(0);
	}

	/* method for authenticating user credentials */
	private boolean authenticate(Buyer buyer) {

		/* prompt for username and password */
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter your username:");
		String username = input.next();
		System.out.println("Please enter your password:");
		String password = input.next();

		/* test input vs known values, return true if both match, false otherwise */
		if (username.equals(buyer.getUserName())) {
			if (password.equals(buyer.getPassword()))
				return true;
			else {
				System.out.println("The password you have provided is incorrect.");
				return false;
			}
		}

		else {
			System.out.println("The username you typed is incorrect.");
			return false;
		}
	}

}