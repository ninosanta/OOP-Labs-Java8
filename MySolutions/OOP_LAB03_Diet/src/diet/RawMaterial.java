package diet;

public class RawMaterial implements NutritionalElement {
	private String name;
	private double calories;
	private double proteins;
	private double carbs;
	private double fat;

	public RawMaterial(String name, double calories, double proteins,	double carbs, double fat) {
		this.name = name;
		this.calories = calories;
		this.proteins = proteins;
		this.carbs = carbs;
		this.fat = fat;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public double getCalories() {
		return calories;
	}

	public void setCalories(double calories) {
		this.calories = calories;
	}

	@Override
	public double getProteins() {
		return proteins;
	}

	public void setProteins(double proteins) {
		this.proteins = proteins;
	}

	@Override
	public double getCarbs() {
		return carbs;
	}

	public void setCarbs(double carbs) {
		this.carbs = carbs;
	}

	@Override
	public double getFat() {
		return fat;
	}

	public void setFat(double fat) {
		this.fat = fat;
	}

	@Override
	public boolean per100g() {
		// nutritional values are provided for 100g of raw material
		return true;
	}
}
