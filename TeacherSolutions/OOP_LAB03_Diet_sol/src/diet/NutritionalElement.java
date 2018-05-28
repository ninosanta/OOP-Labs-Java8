package diet;


public interface NutritionalElement {
	public String getName();

	public double getCalories();

	public double getProteins();

	public double getCarbs();

	public double getFat();

	public boolean per100g();

	// default methods added to simplify code
	// without compromising the original interface
	default double getCalories(double quantity) {
		return getCalories() * quantity / (per100g() ? 100 : 1);
	}

	default double getProteins(double quantity) {
		return getProteins() * quantity / (per100g() ? 100 : 1);
	}

	default double getCarbs(double quantity) {
		return getCarbs() * quantity / (per100g() ? 100 : 1);
	}

	default double getFat(double quantity) {
		return getFat() * quantity / (per100g() ? 100 : 1);
	}

}
