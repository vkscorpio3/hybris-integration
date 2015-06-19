package de.hybris.platform.commercefacades.product.data;

public class PriceData {

	public ThisString getValue() {
		return new ThisString();
	}
	
	public class ThisString {
		public String toPlainString() {
			return "test price";
		}
	}
}
