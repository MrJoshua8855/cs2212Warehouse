package ca.uwo.viewer.restock.strategies;

import ca.uwo.pricingStrategies.aggregate.AggregatePricingStrategy;

public class RestockStrategyFactory {
	
	/**
	 * create strategy for restocking.
	 * @param each type is attached to one strategy.
	 * @return one concrete implementation of {@link RestockStrategy}.
	 */
	
	public static RestockStrategy create(String type) {
		switch(type) {
		case "strategy1":
			return new RestockStrategy1();
		case "weird":
			return new RestockStrategy2();
		default:
			return new RestockStrategy1();
		}
	}

}
