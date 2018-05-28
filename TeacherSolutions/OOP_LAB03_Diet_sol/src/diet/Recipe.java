package diet;

public class Recipe implements NutritionalElement {
	private String name;
	private Food food;
	//private LinkedList<Ingredient> ingredients = new LinkedList<Ingredient>();
	private double calories = 0.0;
	private double proteins = 0.0;
	private double carbs = 0.0;
	private double fat = 0.0;
	private double weight = 0.0;

	private class Ingredient {
		NutritionalElement en;
		double qty;
	}

	public Recipe(String name, Food food) {
		this.name = name;
		food.addRecipe(this);
		this.food = food;
	}

	public void addIngredient(String material, double quantity) {
		Ingredient ing = new Ingredient();
		ing.qty = quantity;
		ing.en = food.getRawMaterial(material);

		calories += ing.en.getCalories(ing.qty);
		proteins += ing.en.getProteins(ing.qty);
		carbs += ing.en.getCarbs(ing.qty);
		fat += ing.en.getFat(ing.qty);
		weight += quantity;

		//ingredients.add(ing);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public double getCalories() {
		return calories * 100 / weight;
	}

	@Override
	public double getProteins() {
		return proteins * 100 / weight;
	}

	@Override
	public double getCarbs() {
		return carbs * 100 / weight;
	}

	@Override
	public double getFat() {
		return fat * 100 / weight;
	}

	/**
	 * Indicates whether the nutritional values returned by the other methods
	 * refer to a conventional 100g quantity of nutritional element,
	 * or to a unit of element.
	 * 
	 * For the {@link Recipe} class it must always return {@code true}:
	 * a recipe expressed nutritional values per 100g
	 * 
	 * @return boolean indicator
	 */
	@Override
	public boolean per100g() {
		return true;
	}

}
