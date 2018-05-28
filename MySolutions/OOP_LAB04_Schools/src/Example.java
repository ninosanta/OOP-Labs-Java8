
import schools.Branch;
import schools.Community;
import schools.Municipality;
import schools.Region;
import schools.School;
import static java.util.stream.Collectors.*;


public class Example {

	public static void main(String[] args) throws Exception {
		
		/* tryna figure out ho split() allocate arrays */
//		String howSplitWork = "AL,ACQUI TERME,1,SCUOLA D'INFANZIA,60011002,"
//							+ "AL1A00100R,MOISO,VIA F.LLI MOISO 28,15011,,pippo";
//		String[] splitted = howSplitWork.split(",");
//		System.out.println(splitted.length + "\n\t" 
//						   + splitted[splitted.length-2] + " "
//						   + splitted[splitted.length-1]);
		

		Region region = new Region("Piemonte");
		
		Community cc = region.newCommunity("PoliTo Hills", Community.Type.COLLINARE);
		
		Municipality to = region.newMunicipality("Torino", "TO", cc);
		region.newMunicipality("Cuneo", "CN");

		for(Municipality m : region.getMunicipalies()){
			System.out.println("Created municipality " + m.getName() + 
							" part of community " + m.getCommunity().map(Community::getName).orElse("<none>"));
		}
		
		School s = region.newSchool("Liceum","TOLC01", 4, "Liceum school");
		region.newBranch(1234, to, "C.so Duca", 10129, s);
		
		System.out.println("Created school " + s.getName() + " with branches " +
							s.getBranches().stream().collect(mapping(Branch::getAddress,joining(",")))
				);
				

		Region r = new Region("Piemonte");
		
		r.readData("schools.csv");
		
		System.out.println("Loaded " + r.getSchools().size() + " schools");
		
		System.out.println("Count of schools by description");
		r.countSchoolsPerDescription().forEach( (k,v) -> System.out.println(k + ":" + v));

		System.out.println("Count branches per municipality");
		r.countBranchesPerMunicipality().forEach( (k,v) -> System.out.println(k + ":" + v));

		System.out.println("Count branches per municipality per province");
		r.countBranchesPerMunicipalityPerProvince().forEach( (k,v) -> System.out.println(k + ":" + v));

		System.out.println("Count schools per municipality");
		r.countSchoolsPerMunicipality().forEach( System.out::println);

		System.out.println("Count schools by community");
		r.countSchoolsPerCommunity().forEach( System.out::println);
	}

}
