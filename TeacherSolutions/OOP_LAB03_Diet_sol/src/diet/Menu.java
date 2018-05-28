package diet;


public class Menu implements NutritionalElement {

	private String name;
	private Food food;
	private double calories = 0.0;
	private double proteins = 0.0;
	private double carbs = 0.0;
	private double fat = 0.0;

	/**
	 * Creates a menu
	 * 
	 * @param name
	 *            the name of the menu
	 * @param food
	 *            the food object containing the information about raw materials
	 */
	public Menu(String name, Food food) {
		this.name = name;
		this.food = food;
	}

	public void addRecipe(String recipe, double quantity) {
		NutritionalElement ric = food.getRecipe(recipe);

		calories += ric.getCalories(quantity);
		proteins += ric.getProteins(quantity);
		carbs += ric.getCarbs(quantity);
		fat += ric.getFat(quantity);
	}

	public void addProduct(String product) {
		NutritionalElement prod = food.getProduct(product);

		calories += prod.getCalories();
		proteins += prod.getProteins();
		carbs += prod.getCarbs();
		fat += prod.getFat();
	}

	@Override
	public String getName() {
		return name;
	}

	/**
	 * Total KCal in the menu
	 */
	@Override
	public double getCalories() {
		return calories;
	}

	/**
	 * Total proteins in the menu
	 */
	@Override
	public double getProteins() {
		return proteins;
	}

	/**
	 * Total carbs in the menu
	 */
	@Override
	public double getCarbs() {
		return carbs;
	}

	/**
	 * Total fats in the menu
	 */
	@Override
	public double getFat() {
		return fat;
	}

	/**
	 * Indicates whether the nutritional values returned by the other methods
	 * refer to a conventional 100g quantity of nutritional element,
	 * or to a unit of element.
	 * 
	 * For the {@link Menu} class it must always return {@code false}:
	 * nutritional values are provided for the whole menu.
	 * 
	 * @return boolean indicator
	 */
	@Override
	public boolean per100g() {
		return false;
	}
}
