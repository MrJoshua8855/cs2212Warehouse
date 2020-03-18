package ca.uwo.model;

import java.util.ArrayList;
import java.util.List;

import ca.uwo.model.item.states.ItemState;
import ca.uwo.model.item.states.ItemStateFactory;
import ca.uwo.utils.ItemResult;
import ca.uwo.utils.ResponseCode;
import ca.uwo.viewer.Messenger;
import ca.uwo.viewer.StockManager;
import ca.uwo.viewer.Viewer;

/**
 * @author kkontog, ktsiouni, mgrigori This class represents the data objects
 *         (instances of Item class) in the database.
 */
public class Item {
	private int id;
	private String name;
	private int availableQuantity;
	private double price;
	// This is the attribute that references the object's state
	private ItemState state;
	private List<Viewer> viewers;

	/**
	 * constructor for the Item class.
	 * 
	 * @param id
	 *            identifier of the item.
	 * @param name
	 *            name of the item.
	 * @param quantity
	 *            quantity of the item.
	 * @param price
	 *            price of the item.
	 */
	public Item(int id, String name, int quantity, double price) {
		super();
		this.id = id;
		this.name = name;
		this.availableQuantity = quantity;
		this.price = price;
		this.viewers = new ArrayList<Viewer>();
		
		// Adding viewers thus implementing part of the Observer design pattern
		this.viewers.add(StockManager.getInstance());
		this.viewers.add(Messenger.getInstance());

		// When you add states to items make sure you
		// initialize them using the proper STATE!!!!
		this.setState();

	}

	/**
	 * @return id of the item.
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return name of the item.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return available quantity of the item.
	 */
	public int getAvailableQuantity() {
		return availableQuantity;
	}

	/**
	 * @param availableQuantity
	 *            available quantity of the item in stock.
	 */
	public void setAvailableQuantity(int availableQuantity) {
		this.availableQuantity = availableQuantity;
	}

	/**
	 * @return price of the item.
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * deplete the item.
	 * 
	 * @param quantity
	 *            the quantity of item to deplete.
	 * @return execution result of the deplete action.
	 */
	public ItemResult deplete(int quantity) {
		// Deplete the item with quantity and return the execution result of
		// deplete action.
		return state.deplete(this, quantity);
	}

	/**
	 * replenish the item.
	 * 
	 * @param quantity
	 *            the quantity of item to replenish.
	 * @return execution result of the replenish action.
	 */
	public ItemResult replenish(int quantity) {
		// Replenish the item with quantity and return the execution result of
		// replenish action.		
		return state.replenish(this, quantity);		
	}
	
	/**
	 * Add a viewer to this item.
	 * 
	 * @param The viewer to be notified of changes
	 *            
	 */
	public void addViewer(Viewer v) {
		if(!this.viewers.contains(v)) {
			this.viewers.add(v);
		}		
	}
	
	/**
	 * Remove a viewer from this item.
	 * 
	 * @param The viewer to be removed
	 *            
	 */
	public void removeViewer(Viewer v) {
		if(this.viewers.contains(v)) {
			this.viewers.remove(v);
		}
		
	}
	
	/**
	 * Notify all viewers of a change
	 * 
	 */
	public void notifyViewers() {
		for(Viewer v : this.viewers){
			v.inform(this);
		}
	}
	

	
	/**
	 * Set the appropriate current state for this item
	 * 
	 */
	public void setState() {
		int availableQuantity = this.getAvailableQuantity();
		
		if(availableQuantity <= 0) {
			this.state = ItemStateFactory.create("OutOfStockState");
		}
		else if(availableQuantity < 10) {
			this.state = ItemStateFactory.create("LowStockState");
		}
		else {
			this.state = ItemStateFactory.create("InStockState");
		}
	}

}
