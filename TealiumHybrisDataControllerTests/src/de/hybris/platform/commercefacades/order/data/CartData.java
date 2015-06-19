package de.hybris.platform.commercefacades.order.data;

import java.util.ArrayList;

import de.hybris.platform.commercefacades.product.data.PriceData;

public class CartData {

	public PriceData getTotalPrice() {

		return new PriceData();
	}

	public ArrayList<OrderEntryData> getEntries() {
		ArrayList<OrderEntryData> returnArrayList = new ArrayList<>();
		returnArrayList.add(new OrderEntryData());
		return returnArrayList;
	}

}
