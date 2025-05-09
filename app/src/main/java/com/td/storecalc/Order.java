
import java.text.*;
import android.icu.number.*;public class Order
 {
    private int id;
    private String title;
    private String itemName;
    private double itemPrice;
    private int itemQuantity;
    private double itemTotalPrice;
    private double orderTotalPrice;
	
    public Order() {}

    public Order(int id, String title, String itemName, double itemPrice, int itemQuantity, double itemTotalPrice, double orderTotalPrice) {
        this.id = id;
        this.title = title;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemQuantity = itemQuantity;
        this.itemTotalPrice = itemTotalPrice;
        this.orderTotalPrice = orderTotalPrice;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public double getItemTotalPrice() {
        return itemTotalPrice;
    }

    public void setItemTotalPrice(double itemTotalPrice) {
        this.itemTotalPrice = itemTotalPrice;
    }

    public double getOrderTotalPrice() {
        return orderTotalPrice;
    }

    public void setOrderTotalPrice(double orderTotalPrice) {
        this.orderTotalPrice = orderTotalPrice;
    }
	@Override
    public String toString() {
		DecimalFormat decimalFormat = new DecimalFormat("$#,##0.00");
        return itemQuantity + " " + itemName + "  x  " + decimalFormat.format(itemPrice) + "  =  " + decimalFormat.format(itemTotalPrice);
    }
}

