package schools;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.*;
import static java.util.Comparator.*;


/**
 * Represents the region and serves as a facade class
 * for the package.
 * 
 * It provides factory methods for creating instances of
 * {@link Community}, {@link Municipality}, {@link School}, and {@link Branch}
 *
 */
public class Region {
	private String nome;
	private Collection<School> schools;
	private Collection<Community> communities;
	private Collection<Municipality> municipalities;
	// an alternative solution could store them in Maps 
	
	/**
	 * Creates a new region with the given name.
	 * @param name the name of the region
	 */
	public Region(String nome){
		this.nome = nome;
		schools = new LinkedList<>();
		communities = new LinkedList<>();
		municipalities = new LinkedList<>();
	}
	
	/**
	 * Getter method
	 * @return the name of the region
	 */
	public String getName(){return nome;}
	
	/**
	 * Retrieves all schools in the region
	 * @return collection of schools
	 */
	public Collection<School> getSchools() {
		return schools;
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
		return municipalities;
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
	public Community newCommunity(String name, Community.Type type){
		Community c = new Community(name, type);
		communities.add(c);
		return c;
	}

	/**
	 * Factory method that build a new municipality.
	 * 
	 * @param nome 		name of the municipality
	 * @param province 	province of the municipality
	 * @return the new created municipality
	 */
	public Municipality newMunicipality(String nome, String provincia){
		return newMunicipality( nome,  provincia,  null);
	}
	
	/**
	 * Factory methods, that build a new municipality that
	 * is part of a community.
	 * 
	 * @param name 		name of the municipality
	 * @param province 	province of the municipality
	 * @param community  community the municipality belongs to 
	 * @return the new created municipality
	 */
	public Municipality newMunicipality(String name, String province, Community community){
		Municipality c = new Municipality(name, province, community);
		municipalities.add(c);
		if(community!=null){
			community.add(c);
		}
		
		return c;
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
	public School newSchool(String name, String code, int grade,
			String description){
		School s = new School(name, code, grade, description);
		schools.add(s);
		
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
	public Branch newBranch(int regionalCode, Municipality municipality, String indirizzo, int cap,
							School school)	{
		Branch s = new Branch(regionalCode, indirizzo, cap, municipality, school);
		school.addBranch(s);
		// per facilitarmi la vita:
		municipality.addBranch(s);
		
		return s;
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
		try (BufferedReader in = new BufferedReader(new FileReader(file))) {
			lines = in.lines().collect(toList());
		} catch(IOException e) { 
			System.err.println(e.getMessage()); 
		}
				
		
		if(lines==null) return; 
		
		// extract headers and build the relative columns map 
		String[] headers = lines.remove(0).split(",");
		Map<String,Integer> header2index = new HashMap<>();
		for(int i=0; i<headers.length; ++i){
			header2index.put(headers[i], i);
		}
		lines
		.forEach( r -> {
			String[] row = r.split(",");
			Community community = getCommunity(header2index,row);
			Municipality comune = municipalities.stream()
					 .filter( c -> row[header2index.get("Comune")].equals(c.getName()))
					 .findFirst()
					 .orElseGet(() -> newMunicipality(row[header2index.get("Comune")],
							 						  row[header2index.get("Provincia")],
							 						  community
							 		  				 )
							    );
			
			School scuola = schools.stream()
							.filter( s -> s.getCode().equals(row[header2index.get("Cod Scuola")] ))
							.findFirst()
							.orElseGet(() -> newSchool(row[header2index.get("Denominazione Scuola")],
							                            row[header2index.get("Cod Scuola")],
							                            Integer.parseInt(row[header2index.get("Grado Scolastico")]),
											  		    row[header2index.get("Descrizione Scuola")]
													   )
									  )
							;
			/*Sede sede =*/ newBranch(Integer.parseInt(row[header2index.get("Cod Sede")]),
								  comune,
								  row[header2index.get("Indirizzo e n. civico")],
								  Integer.parseInt(row[header2index.get("C.A.P.")]),
								  scuola
					    	);
					}
				);
	}

	private Community getCommunity(Map<String,Integer> h2i,String[] row){
		int cci = h2i.get("Comunita Collinare");
		String collinare = (cci >= row.length ? "" : row[cci]);  /* indice della colonna
		 														  * della colunita' collinare
		 														  * maggiore o uguale al numero di 
		 														  * colonne
		 														  * -> allora e' collinare
		 														  * -> altrimenti void
		 														  */
		int cmi = h2i.get("Comunita Montana");
		String montana = (cmi >= row.length ? "" : row[cmi]);
		
		if(collinare.length() != 0) {  // se collinare
			return communities.stream()
					.filter( c -> c.getName().equals(collinare))
					.findFirst()  /* Optional -> se non trova la community restituisce
								   * quella che crea l'orElseGet */
					.orElseGet(() -> newCommunity(collinare, Community.Type.COLLINARE));
			// OR
//			Optional<Community> cc = communities.stream()
//									.filter( c -> c.getName().equals(collinare))
//									.findFirst();
//			if(cc.isPresent()) {
//				return cc.get();
//			} else {
//				return newCommunity(collinare, Community.Type.COLLINARE) ;
//			}
		} else if(montana.length() != 0) { // se montana
			return communities.stream()
					.filter( c -> c.getName().equals(montana))
					.findFirst()
					.orElseGet(() -> newCommunity(montana, Community.Type.MONTANA));
		} else {  // ne' collinare ne' montana
			return null;
		}
	}
	
	
	/**
	 * Counts how many schools there exist for each description
	 * @return a map of school count vs. description
	 */
	public Map<String,Long>countSchoolsPerDescription(){
		return schools.stream()
				/* versione della groupingBy con due parametri:
				 * 		un parametro che è la funzione per estrarre la chiave
				 * 		l'altro e' un collettore che mi dice come trattare gli 
				 * 		elementi trovati e.g. toList() o un altro groupingBy()
				 * 		oppure, come in questo caso counting() i.e. contali 
				 */
				.collect(groupingBy(s -> s.getDescription(),counting()));
		

		// OR
//		Map<String,Long> result = new HashMap<>();
//		for(School s : schools) {
//			String descr = s.getDescription();
//			if(result.containsKey(descr)) {
//				result.put(descr, result.get(descr) + 1);
//			}else {
//				//result.put(descr, 1 ); // error: not auto-boxing from int to Long
//				result.put(descr, 1L );
////			result.put(descr, (long)1 );
////			result.put(descr, new Long(1) );
//			}
//		}
//		return result;
	}

	
	/**
	 * Count how many school branches there exist for each municipality
	 * @return a map of branch count vs. municipality
	 */
	public Map<String,Long>countBranchesPerMunicipality(){
		return schools.stream()
//				.map(School::getBranches)  // insieme di sedi
//				.flatMap(Collection::stream)  // stream di sedi
				// OR: one line
				.flatMap(s -> s.getBranches().stream())
				.collect(groupingBy(s->s.getMunicipality().getName(), counting()));
	}

	
	/**
	 * Counts the number of school branches per municipality
	 * and groups them by province.
	 * @return a map of maps the inner reports count of branches vs. municipality
	 * 		   the outer reports provinces as keys
	 */
	public Map<String,Map<String,Long>>countBranchesPerMunicipalityPerProvince(){
		return schools.stream()
				.map(School::getBranches)
				.flatMap(Collection::stream)  // Stream<Branch>
				.collect(groupingBy( s->s.getMunicipality().getProvince(),  // key 
									groupingBy(  // value
												s->s.getMunicipality().getName(),  // key 
												counting()  // value
											  )
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
	public Collection<String> countSchoolsPerMunicipality(){
		/* per ogni comune devo vedere quante sedi ci sono,
		 * recuperare le scuole, eliminare i duplicati
		 */
		return schools.stream()  // Stream<School>
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
		
		// OR
//		return schools.stream()
//				.map(School::getBranches)
//				.flatMap(Collection::stream)
//				.collect(groupingBy( s -> s.getMunicipality().getName(),
//									 mapping(Branch::getSchool,toSet())
//									)
//						)
//				.entrySet().stream()
//				.map( e -> e.getValue().size()+ " - "+e.getKey())
//				.collect(toList())
//				;
		// OR
//		return municipalties.stream()
//				.collect(TreeMap::new,  // raccolgo i comuni dentro una TreeMap
//						 // definisco un collettore con: supplier, accumulator, merger
//						 (a,c) -> a.put(c.getName(),c.getBranches().stream().map(Branch::getSchool).collect(toSet()).size()),
//						 (a1,a2) -> a1.putAll(a2)  // merger
//						)
//				.entrySet().stream()
//				.map( e -> e.getValue()+ " - "+e.getKey())
//				.collect(toList())
//				;
	}
	
	/**
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
		return schools.stream()
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
