package uk.co.martbox.fruitshop;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Provides prices of items in the shop and sum of a list of items
 *
 */
public class Shop 
{
	
	private Map<String, BigDecimal> items = new HashMap<String, BigDecimal>(); 
	
	public Shop() {
		items.put("apple", new BigDecimal("0.60"));
		items.put("orange", new BigDecimal("0.25"));
	}
	
	/**
	 * Returns the price of a named item or 0.00 of not found
	 * @param itemName
	 * @return
	 */
    public BigDecimal getPriceOfItem(String itemName) {
    	BigDecimal price = this.items.get(itemName);
    	return price==null ? BigDecimal.ZERO : price;
    }
    
    /**
     * Returns the total price of items
     * @param itemNames
     * @return 
     */
    public BigDecimal getTotalPrice(List<String> itemNames) {
    	itemNames = itemNames==null ? new ArrayList<String>() : itemNames;    	
    	BigDecimal total = itemNames.stream().map(itemName->this.getPriceOfItem(itemName)).reduce(BigDecimal.ZERO, (a,b)->a.add(b));
    	return total;
    }
    
    
}
