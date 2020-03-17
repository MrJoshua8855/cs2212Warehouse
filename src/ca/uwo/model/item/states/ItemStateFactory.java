package ca.uwo.model.item.states;

import ca.uwo.pricingStrategies.aggregate.AggregatePricingStrategy;

/**
 * This class is responsible for creating different states (using Factory design pattern) 
 * which implement {@link ItemState} interface based on different contexts.
 */
public class ItemStateFactory {
	
	/**
	 * @param type each type is attached to one state.
	 * @return one concrete implementation of {@link ItemState}.
	 */
	public static ItemState create(String type) {
		
		switch(type) {
		
		case "InStockState":
			return new InStockState();
			break;
			
		case "LowStockState":
			return new LowStockState();
			break;
			
		case "OutOfStockState":
			return new OutOfStockState();
			break;
			
		}
	}
}