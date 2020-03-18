package ca.uwo.model.item.states;

import ca.uwo.controller.OperationInterface;
import ca.uwo.dataAccess.DataManager;
import ca.uwo.model.Item;
import ca.uwo.model.ItemRepository;
import ca.uwo.utils.ItemResult;
import ca.uwo.utils.OrderItem;
import ca.uwo.utils.ResponseCode;


public class LowStockState implements ItemState {

	@Override
	public ItemResult deplete(Item item, int quantity) {
		ItemResult result;
		if (item.getAvailableQuantity() >= quantity) {
			item.setAvailableQuantity(item.getAvailableQuantity() - quantity);
			result = new ItemResult("AVAILABLE", ResponseCode.Completed);
		}
		else {
			result = new ItemResult("OUT OF STOCK", ResponseCode.Not_Completed);
			item.notifyViewers();
		}
		item.setState();
		return result;		
	}

	@Override
	public ItemResult replenish(Item item, int quantity) {
		int availableQuantity = item.getAvailableQuantity();
		availableQuantity += quantity;
		item.setAvailableQuantity(availableQuantity);
		ItemResult itemResult = new ItemResult("RESTOCKED", ResponseCode.Completed);
		item.setState();
		return itemResult;
	}

}
