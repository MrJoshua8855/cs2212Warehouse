package ca.uwo.model.item.states;

import ca.uwo.model.Item;
import ca.uwo.model.ItemRepository;
import ca.uwo.utils.ItemResult;
import ca.uwo.utils.OrderItem;


public class LowStockState implements ItemState {

	@Override
	public ItemResult deplete(Item item, int quantity) {
		ItemRepository repo = new ItemRepository();
		
		OrderItem curItem = new OrderItem(item.getName(), quantity);
		repo.depleteItemStock(curItem);
		
		return curItem.getItemResult();
	}

	@Override
	public ItemResult replenish(Item item, int quantity) {
		ItemRepository repo = new ItemRepository();
		
		OrderItem curItem = new OrderItem(item.getName(), quantity);
		repo.replenishItemStock(curItem);
		
		return curItem.getItemResult();
	}

}
