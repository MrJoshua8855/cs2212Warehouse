package ca.uwo.pricingStrategies.individual;

public class IndividualPricingStrategy1 implements IndividualPricingStrategy {

	@Override
	public double calculate(int quantity, double price) {
		return (quantity * price);
	}

}
