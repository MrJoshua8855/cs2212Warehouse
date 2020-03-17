package ca.uwo.model.item.states;

import ca.uwo.model.*;
import ca.uwo.utils.ItemResult;
import ca.uwo.utils.OrderItem;
import ca.uwo.utils.ResponseCode;



public class OutOfStockState implements ItemState {

	@Override
	public ItemResult deplete(Item item, int quantity) {
		//prepare itemResult values and return
		String message = "Out of Stock";
		ResponseCode code = ResponseCode.Not_Completed;
		item.NotifyViewers();
		return new ItemResult(message, code);
	}

	@Override
	public ItemResult replenish(Item item, int quantity) {
		ItemRepository repo = new ItemRepository();
		
		OrderItem curItem = new OrderItem(item.getName(), quantity);
		repo.replenishItemStock(curItem);
		
		return curItem.getItemResult();
	}

}
