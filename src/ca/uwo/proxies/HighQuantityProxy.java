package ca.uwo.proxies;

import java.util.Map;
import java.util.Scanner;

import ca.uwo.client.Buyer;
import ca.uwo.client.Supplier;
import ca.uwo.frontend.Facade;

public class HighQuantityProxy extends Proxy {

	public void placeOrder(Map<String, Integer> orderDetails, Buyer buyer) {

		/* this if authenticates the user's credentials and, if successful, handles their request */
		if (authenticate(buyer)) {
			Facade facade = new Facade();
			facade.placeOrder(orderDetails, buyer);
		}

		/* this else is if the user makes a mistake entering their credentials */
		else {
			System.out.println("Please try again.");

			/* try again, handle request upon success */
			if (authenticate(buyer)) {
				Facade facade = new Facade();
				facade.placeOrder(orderDetails, buyer);
			}

			/* exit if second attempt fails */
			else {
				System.out.println("Sorry, but your password is incorrect. Goodbye.");
				System.exit();
			}	
		}	

	}
	
	/* this method should never be called but is required by Proxy */
	public void restock(Map<String, Integer> restockDetails, Supplier supplier) {
		System.out.println("FAILURE IN: HighQuantityProxy\nREASON: This method should never be called.");
		System.exit();
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
