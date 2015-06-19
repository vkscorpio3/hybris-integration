package de.hybris.platform.commercefacades.order.data;

import de.hybris.platform.commercefacades.product.data.PriceData;
import de.hybris.platform.commercefacades.product.data.ProductData;

public class OrderEntryData {

	public ProductData getProduct() {
		
		return new ProductData();
	}

	public Integer getQuantity() {
		return 1;
	}

	public PriceData getBasePrice() {
		return new PriceData();
	}

}
