package ca.uwo.pricingStrategies.individual;

public class IndividualPricingStrategyFactory {

	public static IndividualPricingStrategy create(String type) {
		switch(type) {
		case "test":
			return new IndividualPricingStrategy2();
		default:
			return new IndividualPricingStrategy1();
		}
	}
}
