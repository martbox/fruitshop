package uk.co.martbox.fruitshop;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Provides prices of items in the shop and sum of a list of items
 *
 */
public class Shop 
{
	
	private Map<String, BigDecimal> items = new HashMap<String, BigDecimal>();
	private List<Function<List<String>, BigDecimal>> offers = new ArrayList<Function<List<String>, BigDecimal>>();
	
	/**
	 * Takes list of item names and returns calculated discount for buy on get one free on apples offer
	 */
	private Function<List<String>, BigDecimal> bogofApplesOffer = (itemNames)->{
		long numOfApples = itemNames.stream().filter(itemName->"apple".equals(itemName)).count();
		BigDecimal discount = this.getPriceOfItem("apple").multiply(new BigDecimal(numOfApples/2).setScale(2));
        return discount;
	};

	/**
	 * Takes list of item names and returns calculated discount for three for two on oranges offer.
	 * (Every 3rd orange is free)
	 */
	private Function<List<String>, BigDecimal> threeForTwoOrangesOffer = (itemNames)->{
		long numOfOranges = itemNames.stream().filter(itemName->"orange".equals(itemName)).count();
		BigDecimal discount = this.getPriceOfItem("orange").multiply(new BigDecimal(numOfOranges/3).setScale(2));
        return discount;
	};
		
	
	public Shop() {
		items.put("apple", new BigDecimal("0.60"));		
		items.put("orange", new BigDecimal("0.25"));
		
		offers.add(bogofApplesOffer);
		offers.add(threeForTwoOrangesOffer);
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
     * Returns the total price of items with discount offers applied
     * @param itemNames
     * @return 
     */
    public BigDecimal getTotalPrice(final List<String> _itemNames) {
    	final List<String> itemNames = _itemNames==null ? new ArrayList<String>() : _itemNames;    	
    	BigDecimal total = itemNames.stream()
    			.map(itemName->this.getPriceOfItem(itemName))
    			.reduce(BigDecimal.ZERO, (a,b)->a.add(b));
    	
    	BigDecimal discount = this.offers.stream()
    			.map(offer->offer.apply(itemNames))
    			.reduce(BigDecimal.ZERO, (a,b)->a.add(b));
    	
    	return total.subtract(discount).setScale(2);
    }
    
    
}
