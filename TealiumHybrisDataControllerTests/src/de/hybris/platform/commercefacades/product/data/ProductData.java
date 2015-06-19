package de.hybris.platform.commercefacades.product.data;

import java.util.ArrayList;


public class ProductData {

	public ArrayList<CategoryData> getCategories() {
		ArrayList<CategoryData> returnArrayList = new ArrayList<>();
		returnArrayList.add(new CategoryData());
		return returnArrayList;
	}

	public String getCode() {
		return "test code";
	}

	public PriceData getPrice() {
		return new PriceData();
	}

	public String getName() {
		return "test name";
	}


}
