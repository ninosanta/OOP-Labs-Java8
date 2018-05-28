package diet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


/**
 * Facade class for the diet management.
 * It allows defining and retrieving raw materials and products.
 *
 */
public class Food {
	
	private List<RawMaterial> materials;
	private List<Product> products;
	private List<Recipe> recipes;
	
	
	public Food() {
		materials = new ArrayList<>();
		products = new ArrayList<>();
		recipes = new ArrayList<>();
	}
	
	/**
	 * Define a new raw material.
	 * The nutritional values are specified for a conventional 100g quantity
	 * @param name unique name of the raw material
	 * @param calories calories per 100g
	 * @param proteins proteins per 100g
	 * @param carbs carbs per 100g
	 * @param fat fats per 100g
	 */
	public void defineRawMaterial(String name,
									  double calories,
									  double proteins,
									  double carbs,
									  double fat) {
		RawMaterial e = new RawMaterial(name, calories, proteins, carbs, fat); 
		materials.add(e);
	}
	
	/**
	 * Retrieves the collection of all defined raw materials
	 * @return collection of raw materials though the {@link NutritionalElement} interface
	 */
	public Collection<NutritionalElement> rawMaterials(){
		List<NutritionalElement> sortMaterials = new ArrayList<>();
		
		sortMaterials.addAll(materials); 
		Collections.sort(sortMaterials, (a,b) -> a.getName().compareTo(b.getName()));
		return sortMaterials;
	}
	
	/**
	 * Retrieves a specific raw material, given its name
	 * @param name name of the raw material
	 * @return  a raw material though the {@link NutritionalElement} interface
	 */
	public NutritionalElement getRawMaterial(String name){
		for (RawMaterial m : materials) {
			if (m.getName().compareTo(name) == 0)
				return m;
		}
		return null;
	}

	/**
	 * Define a new packaged product.
	 * The nutritional values are specified for a unit of the product
	 * @param name unique name of the product
	 * @param calories calories for a product unit
	 * @param proteins proteins for a product unit
	 * @param carbs carbs for a product unit
	 * @param fat fats for a product unit
	 */
	public void defineProduct(String name,
								  double calories,
								  double proteins,
								  double carbs,
								  double fat) {
		products.add(new Product(name, calories, proteins, carbs, fat));
	}
	
	/**
	 * Retrieves the collection of all defined products
	 * @return collection of products though the {@link NutritionalElement} interface
	 */
	public Collection<NutritionalElement> products() {
		List<NutritionalElement> sortProducts = new ArrayList<>();
		
		sortProducts.addAll(products); 
		Collections.sort(sortProducts, (a,b) -> a.getName().compareTo(b.getName()));
		return sortProducts;
	}
	
	/**
	 * Retrieves a specific product, given its name
	 * @param name  name of the product
	 * @return  a product though the {@link NutritionalElement} interface
	 */
	public NutritionalElement getProduct(String name){
		for (Product p : products) {
			if (p.getName().compareTo(name) == 0)
				return p;
		}
		return null;
	}
	
	public void defineRecipe(Recipe recipe) {
		recipes.add(recipe);
	}
	
	/**
	 * Retrieves the collection of all defined recipes
	 * @return collection of recipes though the {@link NutritionalElement} interface
	 */
	public Collection<NutritionalElement> recipes(){
		List<NutritionalElement> sortRecipes = new ArrayList<>();
		
		sortRecipes.addAll(recipes); 
		Collections.sort(sortRecipes, (a,b) -> a.getName().compareTo(b.getName()));
		return sortRecipes;
	}
	
	/**
	 * Retrieves a specific recipe, given its name
	 * @param name  name of the recipe
	 * @return  a recipe though the {@link NutritionalElement} interface
	 */
	public NutritionalElement getRecipe(String name){		
		for (Recipe r : recipes) {
			if (r.getName().compareTo(name) == 0)
				return r;
		}
		return null;
	}
	
}
