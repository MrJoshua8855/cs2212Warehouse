package ca.uwo.model.item.states;

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
		
		ItemState result = null;
		switch(type) {
		
		case "InStockState":
			result = new InStockState();
			break;
		case "LowStockState":
			result = new LowStockState();
			break;
		case "OutOfStockState":
			result = new OutOfStockState();
			break;
		}
		return result;
	}
}