package de.hybris.platform.util;

public class Config {
	static String accountString = null;
	static String profileString = null;
	static String targetString = null;
	static String syncString = null; 
	
	public static String getParameter(String string) {

		String returnString = "";
		switch (string) {
		case "tealiumiqaddon.account":
			returnString = accountString;
			break;
		case "tealiumiqaddon.profile":
			returnString = profileString;
			break;
		case "tealiumiqaddon.target":
			returnString = targetString;
			break;
		case "tealiumiqaddon.utagSyncEnabled":
			returnString = syncString;
			break;
		default:
			break;
		}
		return returnString;
	}

	public static void setParameter(String property, String string) {

		switch (property) {
		case "tealiumiqaddon.account":
			accountString = string;
			break;
		case "tealiumiqaddon.profile":
			profileString = string;
			break;
		case "tealiumiqaddon.target":
			targetString = string;
			break;
		case "tealiumiqaddon.utagSyncEnabled":
			syncString = string;
			break;
		default:
			break;
		}

	}
	
	public static void setDefaultProperties(){
		accountString = "test-account";
		profileString = "test-profile";
		targetString = "dev";
		syncString = "1"; 
	}
	
}
