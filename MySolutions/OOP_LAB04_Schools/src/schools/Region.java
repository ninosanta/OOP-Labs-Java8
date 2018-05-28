package schools;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import schools.Community.Type;

import static java.util.Comparator.comparing;
import static java.util.Comparator.reverseOrder;
import static java.util.stream.Collectors.*;

// Hint: to write compact stream expressions
// you can include the following
//import static java.util.stream.Collectors.*;
//import static java.util.Comparator.*;

/**
 * Represents the region and serves as a facade class
 * for the package.
 * 
 * It provides factory methods for creating instances of
 * {@link Community}, {@link Municipality}, {@link School}, and {@link Branch}
 *
 */
public class Region {
	
//	private static final int COM = 2;
	
	private String name;
	private Map <String, Municipality> municipalities = new HashMap<>();
//	private Collection<Community> communities = new ArrayList<>();
	private Collection<Community> communities = new HashSet<>();
	private Map<Integer, Branch> branches = new HashMap<>(); 
	private Map<String, School> schools = new HashMap<>(); 
	
	/**
	 * Creates a new region with the given name.
	 * @param name the name of the region
	 */
	public Region(String name) {
		this.name = name;
	}
	
	/**
	 * Getter method
	 * @return the name of the region
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * Retrieves all schools in the region
	 * @return collection of schools
	 */
	public Collection<School> getSchools() {
		return schools.values();
	}
	
	/**
	 * Retrieves all the communities
	 * @return the collection of all communities
	 */
	public Collection<Community> getCommunities() {
		return communities;
	}
	
	/**
	 * Retrieves all municipalities in the region
	 * @return the collection of municipalities
	 */
	public Collection<Municipality> getMunicipalies() {
		return municipalities.values();  // values() returns only values without keys
	}
	
	
	// factory methods
	
	/**
	 * Factory method that build a new community of the given type.
	 * The type is {@link Community.Type}
	 * 
	 * @param name name of the community
	 * @param type type of the community
	 * @return the new created community
	 */
	public Community newCommunity(String name, Community.Type type) {
		Community foo = new Community(name, type);
		communities.add(foo);
		return foo;
	}

	/**
	 * Factory method that build a new municipality.
	 * 
	 * @param nome 		name of the municipality
	 * @param province 	province of the municipality
	 * @return the new created municipality
	 */
	public Municipality newMunicipality(String name, String province) {
		Municipality foo = new Municipality(name, province, null);
		municipalities.put(name, foo);
		return foo;
	}
	
	/**
	 * Factory methods, that build a new municipality that
	 * is part of a community.
	 * 
	 * @param nome 		name of the municipality
	 * @param province 	province of the municipality
	 * @param comunita  community the municipality belongs to 
	 * @return the new created municipality
	 */
	public Municipality newMunicipality(String name, String province, Community community){
		Municipality foo = new Municipality(name, province, community);
		municipalities.put(name, foo);
		return foo;
	}
	
	/**
	 * Factory method that creates a new school
	 * 
	 * @param name    name of the school
	 * @param code    code of the school
	 * @param grade	  grade of the school (1 to 4)
	 * @param description  description of the school
	 * 
	 * @return a new school object
	 */
	public School newSchool(String name, String code, int grade, String description){
		School foo = new School(name, code, grade, description);
		schools.put(code, foo);
		return foo;		
	}
	
	/**
	 * Factory method that creates a new school branch
	 * 
	 * @param regionalCode	regional code of the branch
	 * @param municipality	municipality where the branch is located
	 * @param address		address of the branch
	 * @param zipCode		zip code of the branch
	 * @param school		school the branch is part of
	 * @return	the new created branch
	 */
	public Branch newBranch(int regionalCode, Municipality municipality, 
							String address, int zipCode, School school)	{
		Branch branch = new Branch(regionalCode, municipality, address, zipCode, school);
		school.addBranch(branch);
		branches.put(regionalCode, branch);
		
		return branch;
	}
	
	/**
	 * Load data from a file.
	 * 
	 * The file must be a CSV file and it is supposed to contain the following fields:
	 * <ul>
	 *  <li>{@code "Provincia"},   (province)
	 *  <li>{@code "Comune"},		(municipality)
	 *  <li>{@code "Grado Scolastico"}, (school grade)
	 *  <li>{@code "Descrizione Scuola"}, (school description)
	 *  <li>{@code "Cod Sede"}, (branch code)
	 *  <li>{@code "Cod Scuola"}, (school code)
	 *  <li>{@code "Denominazione Scuola"}, (name of the school)
	 *  <li>{@code "Indirizzo e n. civico"}, (address of the branch)
	 *  <li>{@code "C.A.P."}, (zip code of the branch)
	 *  <li>{@code "Comunita Collinare"}, (Hill community)
	 *  <li>{@code "Comunita Montana"},  (Mountain community)
	 * </ul>
	 * 
	 * @param file the path of the file
	 */
	public void readData(String file) {
		List<String> lines = null;
		
		try(BufferedReader in = new BufferedReader(new FileReader(file))) {
			lines = in.lines().collect(toList());
		} catch(IOException e) { 
			System.err.println(e.getMessage()); 
		}
		if (lines == null)
			return;
		/* lines now is a list that contains file lines 
		 * i.e. each item of the list contains a line of
		 * 		the CSV file that can be split using "," as separator
		 * The first row of the files contains the headings,the successive 
		 * ones contain the actual data. 
		 */		 
		
		/* in modo molto barbaro usero' gli indici di colonna (che nell'esempio sembrano 
		 * indici di riga) interi perche' so il formato del file
		 * -> per soluzioni piu' eleganti si veda la soluzione del prof oppure definire 
		 * 	  delle costanti
		 */
		lines.stream().skip(1)
			 .forEach( line -> {
				    String[] row = line.split(",");
				    
				    Municipality municipality;
				    Community community = getCommunity(row);

			    	municipality = (municipalities.containsKey(row[1]) ? 
			    					municipalities.get(row[1]) 		   :
			    					newMunicipality(row[1], row[0], community)); 
				    
				    School school = (schools.containsKey(row[5]) ? 
				    				 schools.get(row[5])   	     :
				    				 newSchool(row[6], row[5], Integer.parseInt(row[2]), row[3])); 
				    
				    newBranch(Integer.parseInt(row[4]), municipality, row[7],
				    		  Integer.parseInt(row[8]), school);
				    
			 	}
			 );
		
	}

	private Community getCommunity(String[] line) {
		String name;
		Community community;
		int fLen = line.length;
		Type type;
		
		if (fLen == 10) { 
			name = line[9];
			type = Type.COLLINARE;
		} else if (fLen == 11) {
			name = line[10];
			type = Type.MONTANA;
		} else { 
			return null;
		}
		
		community = communities.stream()
							   .filter(c -> c.getName().equals(name))
							   .findFirst()
							   .orElseGet(() -> newCommunity(name, type));		
		return community;
	}
	
	/**
	 * Counts how many schools there exist for each description
	 * @return a map of school count vs. description
	 */
	public Map<String,Long>countSchoolsPerDescription() {
		Map<String, Long> map = new HashMap<>(); 
		Collection<School> foo = schools.values();
		
		for (School k : foo) {
			String key = k.getDescription();
			if (map.containsKey(key))
				map.put(key, map.get(key) + 1);				
			else 
				map.put(key, (long) 1);
//				map.put(key, 1L);  // equivalente
//				map.put(key, new Long(1);  // equivalente
		}
		
		return map;
	}

	/**
	 * Count how many school branches there exist for each municipality
	 * @return a map of branch count vs. municipality
	 */
	public Map<String,Long>countBranchesPerMunicipality() {
		Map<String, Long> map = new HashMap<>(); 
		Collection<Branch> foo = branches.values();
		
		for (Branch k : foo) {
			String key = k.getMunicipality().getName();
			if (map.containsKey(key)) {
				long i = map.get(key);
				map.put(key, ++i);				
			} else {
				map.put(key, (long) 1);
			}
		}
		
		return map;
	}

	/**
	 * Counts the number of school branches per municipality
	 * and groups them by province.
	 * @return a map of maps the inner reports count of branches vs. municipality
	 * 		   the outer reports provinces as keys
	 */
	public Map<String, Map<String,Long>>countBranchesPerMunicipalityPerProvince() {
		Map<String, Map<String,Long>> map = new HashMap<>(); 
		Map<String, Long> bar = countBranchesPerMunicipality();
		
		for (Municipality m : municipalities.values()) {
			if (map.containsKey(m.getProvince()) == false) {
				// conterra' i comuni DELLA PROVINCIA con il loro numero di sedi
				Map<String, Long> pippo = new HashMap<>();
				pippo.put(m.getName(), bar.get(m.getName()));
				map.put(m.getProvince(), pippo);
			} else {
				map.get(m.getProvince()).put(m.getName(), bar.get(m.getName()));
			}
		}
		
		return map;
	}


	/**
	 * returns a list of strings with format
	 * {@code "### - XXXXXX"}, where 
	 * {@code ###} represents the number of schools (not branches)
	 * and {@code XXXXXX} represents the name of the municipality.
	 * If a school has more than one branch in a municipality
	 * it must be counted only once.
	 * 
	 * @return a collection of strings with the counts
	 */
	public Collection<String> countSchoolsPerMunicipality() {
		/* per ogni comune devo vedere quante sedi ci sono,
		 * recuperare le scuole, eliminare i duplicati
		 */
		return 
			schools.values().stream()  // Stream<School>
			.map(School::getBranches)  // Stream<Collection<Branch>>
			.flatMap(Collection::stream)  // Stream<Branch>
			.collect(groupingBy(s -> s.getMunicipality().getName(),  // key -> nome comune della sede
								// il mapping agisce a valle del ragruppamento di sedi per comune:
								mapping(Branch::getSchool,  // dalla sede estraggo la scuola
										// aggiungo la scuola ad un inisieme (collecting) e dopo
										// (AndThen) chimero' il metodo Size sull'insieme:
										collectingAndThen(toSet(),
												          Set::size))
								)
					)  // Map<NomiComuni, DimensioneDegliInsiemiDiScuoleNelComune>
			.entrySet().stream()  // creo uno stream delle Entry della mappa
					   .map(m -> m.getValue() // numero di scuole
							   	 + " - " + m.getKey()  // nome comune
						   )
					   .collect(toList());
	}
	
	/**.map(School::getBranches)
				.flatMap(Collection::stream)
				.collect(groupingBy( s->s.getMunicipality().getProvince(), 
									groupin
	 * returns a list of strings with format
	 * {@code "### - XXXXXX"}, where 
	 * {@code ###} represents the number of schools (not branches)
	 * and {@code XXXXXX} represents the name of the community.
	 * They are sorted by descending number of schools.
	 * The list must contain only schools having at least
	 * a branch in a municipality part of a community.
	 * 
	 * @return a collection of strings with the counts
	 */
	public List<String> countSchoolsPerCommunity() {
		return schools.values().stream()
				.map(School::getBranches)  // Stream <Collection<Branch>>
				.flatMap(Collection::stream)  // Stream<Branch>
				.filter( s -> s.getMunicipality().getCommunity().isPresent() )  /* mantieni le sedi i
																				 * cui comuni appartengono
																				 * a comunita' */
				// come su
				.collect(groupingBy( s->s.getMunicipality().getCommunity().get().getName(),
									 mapping(Branch::getSchool,collectingAndThen(toSet(), Set::size))
									)
						)  // mappa: comunita' -> conteggio
				.entrySet().stream()
				.sorted(comparing(Map.Entry::getValue, reverseOrder()))
				.map(e -> e.getValue() + " - " + e.getKey())
				.collect(toList());
	}
	
}
