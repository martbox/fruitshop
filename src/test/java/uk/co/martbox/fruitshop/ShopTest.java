package uk.co.martbox.fruitshop;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class ShopTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public ShopTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()  {
        return new TestSuite( ShopTest.class );
    }

    
    public void testReturnZeroForUnknownItem() {
    	Shop shop = new Shop();
    	assertEquals(BigDecimal.ZERO, shop.getPriceOfItem("banana"));
    	assertEquals(BigDecimal.ZERO, shop.getPriceOfItem(null));
    }
    
    public void testAnAppleIs60Pence() {
    	Shop shop = new Shop();
    	assertEquals(new BigDecimal("0.60"), shop.getPriceOfItem("apple"));
    }
    
    public void testAnOrangeIs25Pence() {
    	Shop shop = new Shop();
    	assertEquals(new BigDecimal("0.25"), shop.getPriceOfItem("orange"));
    }
    
    public void testTotalOfItems() {
    	Shop shop = new Shop();
    	List<String> items = Arrays.asList("orange", "apple", "apple", "");
    	assertEquals(new BigDecimal("1.45"), shop.getTotalPrice(items));
    }
}
