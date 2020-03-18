package ca.uwo.model.item.states;

import ca.uwo.dataAccess.DataManager;
import ca.uwo.model.*;
import ca.uwo.utils.ItemResult;
import ca.uwo.utils.OrderItem;
import ca.uwo.utils.ResponseCode;



public class OutOfStockState implements ItemState {

	@Override
	public ItemResult deplete(Item item, int quantity) {
		item.notifyViewers();
		item.setState();
		return new ItemResult("OUT OF STOCK", ResponseCode.Not_Completed);
	}

	@Override
	public ItemResult replenish(Item item, int quantity) {
		int availableQuantity = item.getAvailableQuantity();
		availableQuantity += quantity;
		item.setAvailableQuantity(availableQuantity);
		ItemResult itemResult = new ItemResult("RESTOCKED", ResponseCode.Completed);
		//DataManager repo = DataManager.getInstance();
		//repo.updateItem(item);
		item.setState();
		return itemResult;
	}

}
