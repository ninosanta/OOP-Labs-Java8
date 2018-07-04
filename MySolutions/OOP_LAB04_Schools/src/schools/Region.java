package schools;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import schools.Community.Type;

import static java.util.Comparator.comparing;
import static java.util.Comparator.reverseOrder;
import static java.util.stream.Collectors.*;


/**
 * Main class.
 * Represents the region and serves as a facade class
 * for the package.
 * 
 * It provides factory methods for creating instances of
 * {@link Community}, {@link Municipality}, {@link School}, and {@link Branch}
 *
 * @author ninosanta
 */
public class Region {
	
	private String name;
	private Map <String, Municipality> municipalities = new HashMap<>();
	private Map<String, Community> communities = new HashMap<>();
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
		return communities.values();
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
		Community c = new Community(name, type);
		communities.put(name, c);
		return c;
	}

	/**
	 * Factory method that build a new municipality.
	 * 
	 * @param nome 		name of the municipality
	 * @param province 	province of the municipality
	 * @return the new created municipality
	 */
	public Municipality newMunicipality(String name, String province) {
		Municipality m = new Municipality(name, province, null);
		municipalities.put(name, m);
		return m;
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
		if (community == null)
			return newMunicipality(name, province);
		Municipality m = new Municipality(name, province, community);
		municipalities.put(name, m);
		community.addMunicipality(m);
		return m;
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
		School s = new School(name, code, grade, description);
		schools.put(code, s);
		
		return s;		
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
		municipality.addBranch(branch);
		
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
//		lines.stream().skip(1)
//			 .forEach( line -> {
//				    String[] row = line.split(",");
//				    
//				    Municipality municipality;
//				    Community community = getCommunity(row);
//
//			    	municipality = (municipalities.containsKey(row[1]) ? 
//			    					municipalities.get(row[1]) 		   :
//			    					newMunicipality(row[1], row[0], community)); 
//				    
//				    School school = (schools.containsKey(row[5]) ? 
//				    				 schools.get(row[5])   	     :
//				    				 newSchool(row[6], row[5], Integer.parseInt(row[2]), row[3])); 
//				    
//				    newBranch(Integer.parseInt(row[4]), municipality, row[7],
//				    		  Integer.parseInt(row[8]), school);
//				    
//			 	}
//			 );
		
		//  I like more this:
		lines.stream().skip(1).forEach(line -> {
			
			String[]  field 	   = 					 line.split(",");
			String    province     =							field[0];
			String    municipality =							field[1];
			int       grade 	   = 		   Integer.parseInt(field[2]);
			String    description  = 							field[3];
			int       regionalCode = 		   Integer.parseInt(field[4]);
			String    code         = 							field[5];
			String    name         =							field[6];
			String    address      =							field[7];
			int 	  zipCode 	   =		   Integer.parseInt(field[8]);
			Community community    = 							null;
			
			if (field.length == 11) {
				Type mtype = Type.MONTANA;
				community = newCommunity(field[10], mtype);
			} else if (field.length == 10) {
				Type ctype = Type.COLLINARE;
				community = newCommunity(field[9], ctype);
			} else {
				community = null;
			}
			
			if (!municipalities.containsKey(municipality))
				newMunicipality(municipality, province, community);
//			municipalities.put(municipality, 
//					   		   new Municipality(municipality, province, community));
			
			School school;
			if(schools.containsKey(code))
				school = schools.get(code);
			else 
				school = newSchool(name, code, grade, description);
					
			if (!branches.containsKey(regionalCode))
				newBranch(regionalCode, municipalities.get(municipality), address, zipCode, school);
			
		});
		
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
		
		community = communities.values().stream()
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
		// Only for try some stuff, I complicate the job:
		Map<String, Integer> tmp = schools.values()
										  .stream()
										  .collect(groupingBy(School::getDescription, 
												   collectingAndThen(counting(), 
														             total -> total.intValue())
												   ));
		// If I had put groupingBy(School::getDescription, counting()) 
        // It would be fine already		             
		return tmp.entrySet().stream()
				  .collect(toMap(Map.Entry<String,Integer>::getKey, 
						         e -> Long.valueOf(e.getValue())));
				  
	}

	/**
	 * Count how many school branches there exist for each municipality
	 * @return a map of branch count vs. municipality
	 */
	public Map<String,Long>countBranchesPerMunicipality() {
		return municipalities.values()
				.stream()
				.collect(toMap(Municipality::getName, 
						 	   m -> Long.valueOf(m.getBranches().size())));
	}

	/**
	 * Counts the number of school branches per municipality
	 * and groups them by province.
	 * @return a map of maps the inner reports count of branches vs. municipality
	 * 		   the outer reports provinces as keys
	 */
	public Map<String, Map<String,Long>>countBranchesPerMunicipalityPerProvince() {
		return municipalities.values()
				.stream()
				.collect(groupingBy(Municipality::getProvince, 
									toMap(Municipality::getName, 
										  m -> Long.valueOf(m.getBranches().size()))
									)
						);
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
//		/* per ogni comune devo vedere quante sedi ci sono,
//		 * recuperare le scuole, eliminare i duplicati
//		 */
//		return 
//			schools.values().stream()  // Stream<School>
//			.map(School::getBranches)  // Stream<Collection<Branch>>
//			.flatMap(Collection::stream)  // Stream<Branch>
//			.collect(groupingBy(s -> s.getMunicipality().getName(),  // key -> nome comune della sede
//								// il mapping agisce a valle del ragruppamento di sedi per comune:
//								mapping(Branch::getSchool,  // dalla sede estraggo la scuola
//										// aggiungo la scuola ad un inisieme (collecting) e dopo
//										// (AndThen) chimero' il metodo Size sull'insieme:
//										collectingAndThen(toSet(),
//												          Set::size))
//								)
//					)  // Map<NomiComuni, DimensioneDegliInsiemiDiScuoleNelComune>
//			.entrySet().stream()  // creo uno stream delle Entry della mappa
//					   .map(m -> m.getValue() // numero di scuole
//							   	 + " - " + m.getKey()  // nome comune
//						   )
//					   .collect(toList());
		return branches.values()
				.stream()
				.collect(groupingBy(Branch::getMunicipality, 
						 mapping(Branch::getSchool, counting())))
				.entrySet().stream()
				.map(e -> e.getValue()+" - "+e.getKey().getName())
				.collect(toSet());
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
		// Debug
//		int size = schools.values().stream()
//				.map(School::getBranches)
//				.flatMap(Collection::stream)
//				.filter(br -> br.getMunicipality().getCommunity().isPresent())
//				.collect(summingInt(l -> +1));
//		System.out.println("DBG: \n" + size);
		
		return schools.values().stream()
				.map(School::getBranches)  // Stream <Collection<Branch>>
				.flatMap(Collection::stream)  // Stream<Branch>
				.filter(br -> br.getMunicipality().getCommunity().isPresent()) /* mantieni le sedi i
																				* cui comuni appartengono
																				* a comunita'
																				* isPresent e' fondamentale
																				* perche' Optional! */
				// come su
				.collect(groupingBy(br->br.getMunicipality().getCommunity().get().getName(),  // Optional.get() 
									 mapping(Branch::getSchool,
											 collectingAndThen(toSet(), 
//													 		   Set::size
													 		   set -> set.size())
											 )
									)
						)  // mappa: comunita' -> conteggio
				.entrySet().stream()
				.sorted(comparing(Map.Entry::getValue, reverseOrder()))
				.map(e -> e.getValue() + " - " + e.getKey())
				.collect(toList());
	}
	
}
