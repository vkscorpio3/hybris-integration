package javax.servlet.http;

import de.hybris.platform.cms2.model.pages.ContentPageModel;
import de.hybris.platform.commercefacades.order.data.CCPaymentInfoData;
import de.hybris.platform.commercefacades.order.data.CartData;
import de.hybris.platform.commercefacades.order.data.OrderData;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.storesession.data.CurrencyData;
import de.hybris.platform.commercefacades.storesession.data.LanguageData;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.commerceservices.search.facetdata.ProductCategorySearchPageData;

public class HttpServletRequest {

	public Object getAttribute(String string) {
		Object returnObject = null;
		switch (string) {
		case "currentCurrency":
			returnObject = new CurrencyData();
			break;
		case "currentLanguage":
			returnObject = new LanguageData();
			break;
		case "pageType":
			returnObject = "test page";
			break;
		case "cmsPage":
			returnObject = new ContentPageModel();
			break;
		case "cartData":
			returnObject = new CartData();
			break;
		case "categoryName":
			returnObject = "test category";
			break;
		case "customerData":
			returnObject = new CustomerData();
			break;
		case "paymentInfo":
			returnObject = new CCPaymentInfoData();
			break;
		case "orderData":
			returnObject = new OrderData();
			break;
		case "currencyData":
			returnObject = new CurrencyData();
			break;
		case "email":
			returnObject = "test.email";
			break;
		case "product":
			returnObject = new ProductData();
			break;
		case "searchPageData":
			returnObject = new ProductCategorySearchPageData();
			break;
		default:
			break;
		}
		return returnObject;
	}

}
