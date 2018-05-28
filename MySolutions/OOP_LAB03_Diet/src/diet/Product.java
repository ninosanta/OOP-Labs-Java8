package diet;

public class Product extends RawMaterial {
	
	public Product(String name, double calories, double proteins, double carbs, double fat) {
		super(name, calories, proteins, carbs, fat);
	}

	@Override
	public boolean per100g() {
		// nutritional values are provided for 100g of raw material
		return false;
	}
	
	
}
