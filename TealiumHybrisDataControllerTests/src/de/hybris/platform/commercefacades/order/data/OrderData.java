package de.hybris.platform.commercefacades.order.data;

import java.util.ArrayList;

import de.hybris.platform.commercefacades.product.data.PriceData;

public class OrderData {

	public Object getTotalPrice() {
		return new PriceData();
	}

	public String getCode() {
		return "test order";
	}

	public Object getDeliveryCost() {
		return new PriceData();
	}

	public Object getTotalDiscounts() {
		return new PriceData();
	}

	public Object getSubTotal() {
		return new PriceData();
	}

	public ArrayList<OrderEntryData> getEntries() {
		ArrayList<OrderEntryData> returnArrayList = new ArrayList<>();
		returnArrayList.add(new OrderEntryData());
		return returnArrayList;
	}

}
