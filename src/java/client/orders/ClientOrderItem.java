package client.orders;

import util.Item;

public class ClientOrderItem
{
	Item item;
        int quantity,cost,discount;
	
	public ClientOrderItem(Item item,int quantity,int cost,int discount)
	{
            this.item = item;
            this.quantity = quantity;
            this.cost = cost;
            this.discount = discount;
	}
	
	public Item getItem()
	{
		return item;
	}
	
	public int getQuanity()
	{
		return quantity;
	}
	
	public int getCost()
	{
		return cost;
	}
	
	public int getDiscount()
	{
		return discount;
	}
}
