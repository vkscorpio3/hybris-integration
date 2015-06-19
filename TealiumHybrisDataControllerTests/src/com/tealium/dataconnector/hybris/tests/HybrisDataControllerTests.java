package com.tealium.dataconnector.hybris.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.tealium.dataconnector.hybris.HybrisDataController;
import com.tealium.dataconnector.hybris.TealiumCustomDataController;

import de.hybris.platform.util.Config;


public class HybrisDataControllerTests {
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Before
	public void beforeEach(){
		Config.setDefaultProperties();
		HybrisDataController.unregisterCustomDataClass("tlm");
	}
	
	@Test
	public void getSyncTagEnabledTest() {
		assertEquals("expected script not rendering\n", 
				"<script type=\"text/javascript\" src=\"//tags.tiqcdn.com/utag/test-account/test-profile/dev/utag.sync.js\"></script>\n", 
				HybrisDataController.getSyncTag());
	}
	
	@Test
	public void getSyncTagDisabledTest() {
		Config.setParameter("tealiumiqaddon.utagSyncEnabled", "0");
		assertEquals("should return empty string\n", 
				"",
		HybrisDataController.getSyncTag());
		Config.setParameter("tealiumiqaddon.utagSyncEnabled", null);
		assertEquals("should return empty string\n", 
				"",
				HybrisDataController.getSyncTag());
	}
	
	@Test
	public void allScriptsFailWithNullSettings(){
		Config.setDefaultProperties();
		Config.setParameter("tealiumiqaddon.account", null);
		runAllScripts("tealiumiqaddon.account");
		
		Config.setDefaultProperties();
		Config.setParameter("tealiumiqaddon.profile", null);
		runAllScripts("tealiumiqaddon.profile");
		
		Config.setDefaultProperties();
		Config.setParameter("tealiumiqaddon.target", null);
		runAllScripts("tealiumiqaddon.target");

	}
	
	private void runAllScripts(String propertyTypeString) {
		try {
			HybrisDataController.getSyncTag();
			fail("null "+propertyTypeString+" property should throw exception with getSync()");
		} catch (Exception e) {}
		try {
			HybrisDataController.getHomeScript();
			fail("null "+propertyTypeString+" property should throw exception with getHomeScript()");
		} catch (Exception e) {}
		try {
			HybrisDataController.getCartScript();
			fail("null "+propertyTypeString+" property should throw exception with getCartScript()");
		} catch (Exception e) {}
		try {
			HybrisDataController.getCategoryScript();
			fail("null "+propertyTypeString+" property should throw exception with getCategoryScript()");
		} catch (Exception e) {}
		try {
			HybrisDataController.getCustomerDetailScript();
			fail("null "+propertyTypeString+" property should throw exception with getCustomerDetailScript()");
		} catch (Exception e) {}
		try {
			HybrisDataController.getGenericPageScript();
			fail("null "+propertyTypeString+" property should throw exception with getGenericPageScript()");
		} catch (Exception e) {}
		try {
			HybrisDataController.getOrderConfirmationScript();
			fail("null "+propertyTypeString+" property should throw exception with getOrderConfirmationScript()");
		} catch (Exception e) {}
		try {
			HybrisDataController.getProductPageScript();
			fail("null "+propertyTypeString+" property should throw exception with getProductPageScript()");
		} catch (Exception e) {}
		try {
			HybrisDataController.getSearchPageScript();
			fail("null "+propertyTypeString+" property should throw exception with getSearchPageScript()");
		} catch (Exception e) {}
		
	}
	
	@Test
	public void getHomeScriptTest(){
		try {
			assertEquals("expected script not rendering",
					"<script type='text/javascript'>\n	var utag_data = \n{\n	\"site_currency\":\"test currency\",\n	\"site_region\":\"test language\",\n	\"page_type\":\"home\",\n	\"page_name\":\"test page\"\n}\n</script>\n<script type='text/javascript'>\n	(function(a,b,c,d){\n	a='//tags.tiqcdn.com/utag/test-account/test-profile/dev/utag.js';\n	b=document;c='script';d=b.createElement(c);d.src=a;d.type='text/java'+c;d.async=true;\n	a=b.getElementsByTagName(c)[0];a.parentNode.insertBefore(d,a);\n	})();\n</script>\n",
					HybrisDataController.getHomeScript());
		} catch (Exception e) {
			fail("exception thrown: " + e.toString());
		}
	}
	
	@Test
	public void getCustomHomeScriptTest(){
		try {
			TealiumCustomDataController tealiumCustomData = new TealiumCustomDataController();
			HybrisDataController.registerCustomDataClass("tlm", tealiumCustomData);
			assertEquals("expected script not rendering",
					"<script type='text/javascript'>\n	var utag_data = \n{\n	\"site_currency\":\"test currency\",\n	\"site_region\":\"test language\",\n	\"page_type\":\"home\",\n	\"page_name\":\"new home page name\",\n	\"custom_key\":\"custom_value\"\n}\n</script>\n<script type='text/javascript'>\n	(function(a,b,c,d){\n	a='//tags.tiqcdn.com/utag/test-account/test-profile/dev/utag.js';\n	b=document;c='script';d=b.createElement(c);d.src=a;d.type='text/java'+c;d.async=true;\n	a=b.getElementsByTagName(c)[0];a.parentNode.insertBefore(d,a);\n	})();\n</script>\n",
					HybrisDataController.getHomeScript());
		} catch (Exception e) {
			fail("exception thrown: " + e.toString());
		}
	}
	
	@Test
	public void getCartScriptTest(){
		try {
			assertEquals("expected script not rendering",
					"<script type='text/javascript'>\n	var utag_data = \n{\n	\"product_category\":[\"test category\"],\n	\"product_id\":[\"\"],\n	\"product_unit_price\":[\"\"],\n	\"product_sku\":[\"test code\"],\n	\"site_currency\":\"test currency\",\n	\"product_name\":[\"test name\"],\n	\"product_quantity\":[\"1\"],\n	\"product_brand\":[\"\"],\n	\"site_region\":\"test language\",\n	\"cart_total\":\"test price\",\n	\"page_type\":\"checkout\",\n	\"page_name\":\"test page\",\n	\"product_list_price\":[\"test price\"]\n}\n</script>\n<script type='text/javascript'>\n	(function(a,b,c,d){\n	a='//tags.tiqcdn.com/utag/test-account/test-profile/dev/utag.js';\n	b=document;c='script';d=b.createElement(c);d.src=a;d.type='text/java'+c;d.async=true;\n	a=b.getElementsByTagName(c)[0];a.parentNode.insertBefore(d,a);\n	})();\n</script>\n",
					HybrisDataController.getCartScript());
		} catch (Exception e) {
			fail("exception thrown: " + e.toString());
		}
	}
	
	@Test
	public void getCustomCartScriptTest(){
		try {
			TealiumCustomDataController tealiumCustomData = new TealiumCustomDataController();
			HybrisDataController.registerCustomDataClass("tlm", tealiumCustomData);
			assertEquals("expected script not rendering",
					"<script type='text/javascript'>\n	var utag_data = \n{\n	\"product_id\":[\"\"],\n	\"product_quantity\":[\"1\"],\n	\"product_name\":[\"test name\"],\n	\"cart_total\":\"test price\",\n	\"site_region\":\"test language\",\n	\"custom_key\":\"custom_value\",\n	\"product_unit_price\":[\"\"],\n	\"product_category\":[\"test category\"],\n	\"site_currency\":\"test currency\",\n	\"product_sku\":[\"test code\"],\n	\"product_brand\":[\"\"],\n	\"page_type\":\"checkout\",\n	\"page_name\":\"new cart page name\",\n	\"product_list_price\":[\"test price\"]\n}\n</script>\n<script type='text/javascript'>\n	(function(a,b,c,d){\n	a='//tags.tiqcdn.com/utag/test-account/test-profile/dev/utag.js';\n	b=document;c='script';d=b.createElement(c);d.src=a;d.type='text/java'+c;d.async=true;\n	a=b.getElementsByTagName(c)[0];a.parentNode.insertBefore(d,a);\n	})();\n</script>\n",					HybrisDataController.getCartScript());
		} catch (Exception e) {
			fail("exception thrown: " + e.toString());
		}
	}
	
	@Test
	public void getCategoryScriptTest(){
		try {
			assertEquals("expected script not rendering",
					"<script type='text/javascript'>\n	var utag_data = \n{\n	\"page_category_name\":\"test category\",\n	\"page_subcategory_name\":\"\",\n	\"site_currency\":\"test currency\",\n	\"site_region\":\"test language\",\n	\"page_type\":\"product\",\n	\"page_section_name\":\"\",\n	\"page_name\":\"test page\"\n}\n</script>\n<script type='text/javascript'>\n	(function(a,b,c,d){\n	a='//tags.tiqcdn.com/utag/test-account/test-profile/dev/utag.js';\n	b=document;c='script';d=b.createElement(c);d.src=a;d.type='text/java'+c;d.async=true;\n	a=b.getElementsByTagName(c)[0];a.parentNode.insertBefore(d,a);\n	})();\n</script>\n",
					HybrisDataController.getCategoryScript());
		} catch (Exception e) {
			fail("exception thrown: " + e.toString());
		}
	}
	
	@Test
	public void getCustomCategoryScriptTest(){
		try {
			TealiumCustomDataController tealiumCustomData = new TealiumCustomDataController();
			HybrisDataController.registerCustomDataClass("tlm", tealiumCustomData);
			assertEquals("expected script not rendering",
					"<script type='text/javascript'>\n	var utag_data = \n{\n	\"page_category_name\":\"test category\",\n	\"page_subcategory_name\":\"\",\n	\"site_currency\":\"test currency\",\n	\"site_region\":\"test language\",\n	\"page_type\":\"product\",\n	\"page_section_name\":\"\",\n	\"page_name\":\"new category page name\",\n	\"custom_key\":\"custom_value\"\n}\n</script>\n<script type='text/javascript'>\n	(function(a,b,c,d){\n	a='//tags.tiqcdn.com/utag/test-account/test-profile/dev/utag.js';\n	b=document;c='script';d=b.createElement(c);d.src=a;d.type='text/java'+c;d.async=true;\n	a=b.getElementsByTagName(c)[0];a.parentNode.insertBefore(d,a);\n	})();\n</script>\n",
					HybrisDataController.getCategoryScript());
		} catch (Exception e) {
			fail("exception thrown: " + e.toString());
		}
	}
	
	@Test
	public void getCustomerDetailScriptTest(){
		try {
			assertEquals("expected script not rendering",
					"<script type='text/javascript'>\n	var utag_data = \n{\n	\"site_currency\":\"test currency\",\n	\"customer_email\":\"test email\",\n	\"customer_name\":\"John Doe\",\n	\"site_region\":\"test language\",\n	\"gender\":\"male\",\n	\"page_type\":\"customer\",\n	\"page_name\":\"test page\",\n	\"customer_type\":\"\",\n	\"customer_id\":\"\"\n}\n</script>\n<script type='text/javascript'>\n	(function(a,b,c,d){\n	a='//tags.tiqcdn.com/utag/test-account/test-profile/dev/utag.js';\n	b=document;c='script';d=b.createElement(c);d.src=a;d.type='text/java'+c;d.async=true;\n	a=b.getElementsByTagName(c)[0];a.parentNode.insertBefore(d,a);\n	})();\n</script>\n",
					HybrisDataController.getCustomerDetailScript());
		} catch (Exception e) {
			fail("exception thrown: " + e.toString());
		}
	}
	
	@Test
	public void getCustomCustomerDetailScriptTest(){
		try {
			TealiumCustomDataController tealiumCustomData = new TealiumCustomDataController();
			HybrisDataController.registerCustomDataClass("tlm", tealiumCustomData);
			assertEquals("expected script not rendering",
					"<script type='text/javascript'>\n	var utag_data = \n{\n	\"site_currency\":\"test currency\",\n	\"customer_email\":\"test email\",\n	\"customer_name\":\"John Doe\",\n	\"site_region\":\"test language\",\n	\"gender\":\"male\",\n	\"page_type\":\"customer\",\n	\"page_name\":\"new customer page name\",\n	\"custom_key\":\"custom_value\",\n	\"customer_type\":\"\",\n	\"customer_id\":\"\"\n}\n</script>\n<script type='text/javascript'>\n	(function(a,b,c,d){\n	a='//tags.tiqcdn.com/utag/test-account/test-profile/dev/utag.js';\n	b=document;c='script';d=b.createElement(c);d.src=a;d.type='text/java'+c;d.async=true;\n	a=b.getElementsByTagName(c)[0];a.parentNode.insertBefore(d,a);\n	})();\n</script>\n",
					HybrisDataController.getCustomerDetailScript());
		} catch (Exception e) {
			fail("exception thrown: " + e.toString());
		}
	}
	
	@Test
	public void getGenericPageScriptTest(){
		try {
			assertEquals("expected script not rendering",
					"<script type='text/javascript'>\n	var utag_data = \n{\n	\"site_currency\":\"test currency\",\n	\"site_region\":\"test language\",\n	\"page_type\":\"generic\",\n	\"page_name\":\"test page\"\n}\n</script>\n<script type='text/javascript'>\n	(function(a,b,c,d){\n	a='//tags.tiqcdn.com/utag/test-account/test-profile/dev/utag.js';\n	b=document;c='script';d=b.createElement(c);d.src=a;d.type='text/java'+c;d.async=true;\n	a=b.getElementsByTagName(c)[0];a.parentNode.insertBefore(d,a);\n	})();\n</script>\n",
					HybrisDataController.getGenericPageScript());
		} catch (Exception e) {
			fail("exception thrown: " + e.toString());
		}
	}
	
	@Test
	public void getCustomGenericPageScriptTest(){
		try {
			TealiumCustomDataController tealiumCustomData = new TealiumCustomDataController();
			HybrisDataController.registerCustomDataClass("tlm", tealiumCustomData);
			assertEquals("expected script not rendering",
					"<script type='text/javascript'>\n	var utag_data = \n{\n	\"site_currency\":\"test currency\",\n	\"site_region\":\"test language\",\n	\"page_type\":\"generic\",\n	\"page_name\":\"new generic page name\",\n	\"custom_key\":\"custom_value\"\n}\n</script>\n<script type='text/javascript'>\n	(function(a,b,c,d){\n	a='//tags.tiqcdn.com/utag/test-account/test-profile/dev/utag.js';\n	b=document;c='script';d=b.createElement(c);d.src=a;d.type='text/java'+c;d.async=true;\n	a=b.getElementsByTagName(c)[0];a.parentNode.insertBefore(d,a);\n	})();\n</script>\n",
					HybrisDataController.getGenericPageScript());
		} catch (Exception e) {
			fail("exception thrown: " + e.toString());
		}
	}
	
	
	@Test
	public void getOrderConfirmationScriptTest(){
		try {
			assertEquals("expected script not rendering",
					"<script type='text/javascript'>\n	var utag_data = \n{\n	\"order_total\":\"test price\",\n	\"order_currency\":\"test currency\",\n	\"order_shipping\":\"test price\",\n	\"product_id\":[\"\"],\n	\"order_payment_type\":\"test card\",\n	\"product_name\":[\"test name\"],\n	\"product_quantity\":[\"1\"],\n	\"customer_email\":\"test.email\",\n	\"site_region\":\"test language\",\n	\"order_id\":\"test order\",\n	\"order_subtotal\":\"test price\",\n	\"order_tax\":\"test price\",\n	\"product_unit_price\":[\"\"],\n	\"product_category\":[\"test category\"],\n	\"product_sku\":[\"test code\"],\n	\"site_currency\":\"test currency\",\n	\"product_brand\":[\"\"],\n	\"page_type\":\"checkout\",\n	\"page_name\":\"test page\",\n	\"product_list_price\":[\"test price\"],\n	\"order_discount\":\"test price\",\n	\"customer_id\":\"\",\n	\"product_discount\":[\"\"]\n}\n</script>\n<script type='text/javascript'>\n	(function(a,b,c,d){\n	a='//tags.tiqcdn.com/utag/test-account/test-profile/dev/utag.js';\n	b=document;c='script';d=b.createElement(c);d.src=a;d.type='text/java'+c;d.async=true;\n	a=b.getElementsByTagName(c)[0];a.parentNode.insertBefore(d,a);\n	})();\n</script>\n",
					HybrisDataController.getOrderConfirmationScript());
		} catch (Exception e) {
			fail("exception thrown: " + e.toString());
		}
	}
	
	@Test
	public void getCustomOrderConfirmationScriptTest(){
		try {
			TealiumCustomDataController tealiumCustomData = new TealiumCustomDataController();
			HybrisDataController.registerCustomDataClass("tlm", tealiumCustomData);
			assertEquals("expected script not rendering",
					"<script type='text/javascript'>\n	var utag_data = \n{\n	\"order_total\":\"test price\",\n	\"order_currency\":\"test currency\",\n	\"order_shipping\":\"test price\",\n	\"product_id\":[\"\"],\n	\"order_payment_type\":\"test card\",\n	\"product_name\":[\"test name\"],\n	\"product_quantity\":[\"1\"],\n	\"customer_email\":\"test.email\",\n	\"site_region\":\"test language\",\n	\"order_id\":\"test order\",\n	\"custom_key\":\"custom_value\",\n	\"order_subtotal\":\"test price\",\n	\"order_tax\":\"test price\",\n	\"product_unit_price\":[\"\"],\n	\"product_category\":[\"test category\"],\n	\"product_sku\":[\"test code\"],\n	\"site_currency\":\"test currency\",\n	\"product_brand\":[\"\"],\n	\"page_type\":\"checkout\",\n	\"page_name\":\"new confirmation page name\",\n	\"product_list_price\":[\"test price\"],\n	\"order_discount\":\"test price\",\n	\"customer_id\":\"\",\n	\"product_discount\":[\"\"]\n}\n</script>\n<script type='text/javascript'>\n	(function(a,b,c,d){\n	a='//tags.tiqcdn.com/utag/test-account/test-profile/dev/utag.js';\n	b=document;c='script';d=b.createElement(c);d.src=a;d.type='text/java'+c;d.async=true;\n	a=b.getElementsByTagName(c)[0];a.parentNode.insertBefore(d,a);\n	})();\n</script>\n",
					HybrisDataController.getOrderConfirmationScript());
		} catch (Exception e) {
			fail("exception thrown: " + e.toString());
		}
	}
	
	@Test
	public void getProductPageScriptTest(){
		try {
			assertEquals("expected script not rendering",
					"<script type='text/javascript'>\n	var utag_data = \n{\n	\"page_category_name\":\"\",\n	\"page_subcategory_name\":\"\",\n	\"product_id\":[\"\"],\n	\"product_name\":[\"test name\"],\n	\"site_region\":\"test language\",\n	\"page_section_name\":\"\",\n	\"product_unit_price\":[\"\"],\n	\"product_category\":[\"test category\"],\n	\"site_currency\":\"test currency\",\n	\"product_sku\":[\"test code\"],\n	\"product_brand\":[\"\"],\n	\"page_type\":\"product\",\n	\"page_name\":\"test page\",\n	\"product_list_price\":[\"test price\"]\n}\n</script>\n<script type='text/javascript'>\n	(function(a,b,c,d){\n	a='//tags.tiqcdn.com/utag/test-account/test-profile/dev/utag.js';\n	b=document;c='script';d=b.createElement(c);d.src=a;d.type='text/java'+c;d.async=true;\n	a=b.getElementsByTagName(c)[0];a.parentNode.insertBefore(d,a);\n	})();\n</script>\n",
					HybrisDataController.getProductPageScript());
		} catch (Exception e) {
			fail("exception thrown: " + e.toString());
		}
	}
	
	@Test
	public void getCustomProductPageScriptTest(){
		try {
			TealiumCustomDataController tealiumCustomData = new TealiumCustomDataController();
			HybrisDataController.registerCustomDataClass("tlm", tealiumCustomData);
			assertEquals("expected script not rendering",
					"<script type='text/javascript'>\n	var utag_data = \n{\n	\"page_category_name\":\"\",\n	\"page_subcategory_name\":\"\",\n	\"product_id\":[\"\"],\n	\"product_name\":[\"test name\"],\n	\"site_region\":\"test language\",\n	\"page_section_name\":\"\",\n	\"custom_key\":\"custom_value\",\n	\"product_unit_price\":[\"\"],\n	\"product_category\":[\"test category\"],\n	\"site_currency\":\"test currency\",\n	\"product_sku\":[\"test code\"],\n	\"product_brand\":[\"\"],\n	\"page_type\":\"product\",\n	\"page_name\":\"new product page name\",\n	\"product_list_price\":[\"test price\"]\n}\n</script>\n<script type='text/javascript'>\n	(function(a,b,c,d){\n	a='//tags.tiqcdn.com/utag/test-account/test-profile/dev/utag.js';\n	b=document;c='script';d=b.createElement(c);d.src=a;d.type='text/java'+c;d.async=true;\n	a=b.getElementsByTagName(c)[0];a.parentNode.insertBefore(d,a);\n	})();\n</script>\n",
					HybrisDataController.getProductPageScript());
		} catch (Exception e) {
			fail("exception thrown: " + e.toString());
		}
	}
	
	@Test
	public void getSearchPageScriptTest(){
		try {
			assertEquals("expected script not rendering",
					"<script type='text/javascript'>\n	var utag_data = \n{\n	\"site_currency\":\"test currency\",\n	\"site_region\":\"test language\",\n	\"page_type\":\"search\",\n	\"page_name\":\"test page\",\n	\"search_results\":\"1\",\n	\"search_keyword\":\"test search keywords\"\n}\n</script>\n<script type='text/javascript'>\n	(function(a,b,c,d){\n	a='//tags.tiqcdn.com/utag/test-account/test-profile/dev/utag.js';\n	b=document;c='script';d=b.createElement(c);d.src=a;d.type='text/java'+c;d.async=true;\n	a=b.getElementsByTagName(c)[0];a.parentNode.insertBefore(d,a);\n	})();\n</script>\n",
					HybrisDataController.getSearchPageScript());
		} catch (Exception e) {
			fail("exception thrown: " + e.toString());
		}
	}
	
	@Test
	public void getCustomSearchPageScriptTest(){
		try {
			TealiumCustomDataController tealiumCustomData = new TealiumCustomDataController();
			HybrisDataController.registerCustomDataClass("tlm", tealiumCustomData);
			assertEquals("expected script not rendering",
					"<script type='text/javascript'>\n	var utag_data = \n{\n	\"site_currency\":\"test currency\",\n	\"site_region\":\"test language\",\n	\"page_type\":\"search\",\n	\"page_name\":\"new search page name\",\n	\"search_results\":\"1\",\n	\"custom_key\":\"custom_value\",\n	\"search_keyword\":\"test search keywords\"\n}\n</script>\n<script type='text/javascript'>\n	(function(a,b,c,d){\n	a='//tags.tiqcdn.com/utag/test-account/test-profile/dev/utag.js';\n	b=document;c='script';d=b.createElement(c);d.src=a;d.type='text/java'+c;d.async=true;\n	a=b.getElementsByTagName(c)[0];a.parentNode.insertBefore(d,a);\n	})();\n</script>\n",
					HybrisDataController.getSearchPageScript());
		} catch (Exception e) {
			fail("exception thrown: " + e.toString());
		}
	}
	
	@Test
	public void getCustomPage1ScriptTest(){
		try {
			TealiumCustomDataController tealiumCustomData = new TealiumCustomDataController();
			HybrisDataController.registerCustomDataClass("tlm", tealiumCustomData);
			assertEquals("expected script not rendering",
					"<script type='text/javascript'>\n	var utag_data = \n{\n	\"site_currency\":\"test currency\",\n	\"site_region\":\"test language\",\n	\"page_type\":\"custom_one\",\n	\"page_name\":\"custom page 1\",\n	\"custom_page1_key\":\"custom value\"\n}\n</script>\n<script type='text/javascript'>\n	(function(a,b,c,d){\n	a='//tags.tiqcdn.com/utag/test-account/test-profile/dev/utag.js';\n	b=document;c='script';d=b.createElement(c);d.src=a;d.type='text/java'+c;d.async=true;\n	a=b.getElementsByTagName(c)[0];a.parentNode.insertBefore(d,a);\n	})();\n</script>\n",
					HybrisDataController.getCustomPageScript("custom_one"));
		} catch (Exception e) {
			fail("exception thrown: " + e.toString());
		}
	}
	
	@Test
	public void getCustomPage2ScriptTest(){
		try {
			TealiumCustomDataController tealiumCustomData = new TealiumCustomDataController();
			HybrisDataController.registerCustomDataClass("tlm", tealiumCustomData);
			assertEquals("expected script not rendering",
					"<script type='text/javascript'>\n	var utag_data = \n{\n	\"custom_page2_key\":\"custom value\",\n	\"site_currency\":\"test currency\",\n	\"site_region\":\"test language\",\n	\"page_type\":\"custom_two\",\n	\"page_name\":\"custom page 2\"\n}\n</script>\n<script type='text/javascript'>\n	(function(a,b,c,d){\n	a='//tags.tiqcdn.com/utag/test-account/test-profile/dev/utag.js';\n	b=document;c='script';d=b.createElement(c);d.src=a;d.type='text/java'+c;d.async=true;\n	a=b.getElementsByTagName(c)[0];a.parentNode.insertBefore(d,a);\n	})();\n</script>\n",
					HybrisDataController.getCustomPageScript("custom_two"));
		} catch (Exception e) {
			fail("exception thrown: " + e.toString());
		}
	}
	
	@Test
	public void getCustomPageScriptNonExistantTest(){
		try {
			TealiumCustomDataController tealiumCustomData = new TealiumCustomDataController();
			HybrisDataController.registerCustomDataClass("tlm", tealiumCustomData);
			assertEquals("expected script not rendering",
					"<script type='text/javascript'>\n	var utag_data = \n{\n	\"site_currency\":\"test currency\",\n	\"site_region\":\"test language\",\n	\"page_type\":\"non_existant\",\n	\"page_name\":\"test page\"\n}\n</script>\n<script type='text/javascript'>\n	(function(a,b,c,d){\n	a='//tags.tiqcdn.com/utag/test-account/test-profile/dev/utag.js';\n	b=document;c='script';d=b.createElement(c);d.src=a;d.type='text/java'+c;d.async=true;\n	a=b.getElementsByTagName(c)[0];a.parentNode.insertBefore(d,a);\n	})();\n</script>\n",
					HybrisDataController.getCustomPageScript("non_existant"));
		} catch (Exception e) {
			fail("exception thrown: " + e.toString());
		}
	}
	
}
