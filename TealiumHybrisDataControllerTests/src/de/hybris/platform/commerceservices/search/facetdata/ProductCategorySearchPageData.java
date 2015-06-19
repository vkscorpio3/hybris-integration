package de.hybris.platform.commerceservices.search.facetdata;

import de.hybris.platform.commerceservices.search.pagedata.PaginationData;

public class ProductCategorySearchPageData {

	public String getFreeTextSearch() {
		return "test search keywords";
	}

	public PaginationData getPagination() {
		return new PaginationData();
	}

}
