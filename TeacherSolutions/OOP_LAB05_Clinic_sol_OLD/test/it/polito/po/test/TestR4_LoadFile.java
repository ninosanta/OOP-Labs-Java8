package it.polito.po.test;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import clinic.*;

import junit.framework.TestCase;



public class TestR4_LoadFile extends TestCase {

  public TestR4_LoadFile(String arg0) {
    super(arg0);
  }
  
//  private static void writeFile(String fileName,String content) {
//  	try{
//  		FileOutputStream fos = new FileOutputStream(fileName);
//  		fos.write(content.getBytes());
//  		fos.close();
//	}catch(IOException ioe){
//		throw new RuntimeException(ioe.getMessage());
//	}
//  }
  
  /**
   * Create a new temporary file and write the content
   * @param content
   * @return the path of the new file.
   * @throws IOException
   */
  private static String writeFile(String content) throws IOException {          
          File f = File.createTempFile("off","txt");
          FileOutputStream fos = new FileOutputStream(f);
          fos.write(content.getBytes());
          fos.close();
          return f.getAbsolutePath();
    }

  
  public void testLoading() throws NoSuchPatient, NoSuchDoctor, IOException{
  	String regular = "P;Giovanni;Rossi;RSSGNN33B30F316I\n" +
					"P;Giuseppe;Verdi;VRDGPP76F09B666I\n" + 
					"M;345;Mario;Bianchi;BNCMRA44C99A320Z;Surgeon";
  	
  	String file = writeFile(regular);
  	Clinic s = new Clinic();
  	s.loadData(file);
  	Person p = s.getPatient("VRDGPP76F09B666I");
  	assertEquals("Verdi",p.getLast());

	Doctor m = s.getDoctor(345);
	assertEquals("Bianchi",m.getLast());
  }

//	public void testNumeroLinee() throws NoSuchPatient, NoSuchDoctor, IOException{
//		String normale = "P;Giovanni;Rossi;RSSGNN33B30F316I\n" +
//					"P;Giuseppe;Verdi;VRDGPP76F09B666I\n" + 
//					"M;345;Mario;Bianchi;BNCMRA44C99A320Z;Surgeon";
//  	
//	  	String file = writeFile(normale);
//	  	Clinic s = new Clinic();
//	  	s.loadData(file);
//		
//	}
//  
  public void testTrivialErrors() throws IOException, NoSuchPatient, NoSuchDoctor{
	String normale = "P;Giovanni;Rossi;RSSGNN33B30F316I\n" +
				"P;Giuseppe;Verdi;VRDGPP76F09B666I\n" + 
				"P;Giuseppe;VRDGPP76F09B444I\n" + // missing last name 
				"M;Mario;Bianchi;BNCMRA44C99A320Y;Surgeon\n" + // missing id
				"M;345;Mario;Bianchi;BNCMRA44C99A320Z;Surgeon\n";

  	String file = writeFile(normale);
  	Clinic s = new Clinic();
  	s.loadData(file);
	Person p = s.getPatient("VRDGPP76F09B666I");
	assertEquals("Verdi",p.getLast());

	Doctor m = s.getDoctor(345);
	assertEquals("Bianchi",m.getLast());
  }
  
  public void testSpecialErrorsExtraBlanks() throws IOException {
		String normale = "P;Giovanni;Rossi;RSSGNN33B30F316I\n" +
					"P;Giuseppe; Verdi ; VRDGPP76F09B666I \n" + // added spaces
					"M;345;Mario;Bianchi;BNCMRA44C99A320Z;Surgeon\n";

	  	String file = writeFile(normale);
	  	Clinic s = new Clinic();
	  	s.loadData(file);
	  	try{
	  		Person p = s.getPatient("VRDGPP76F09B666I");
	  		assertEquals("Nome non corretto","Verdi",p.getLast().trim());
	  	}catch(NoSuchPatient e){
	  		fail("Il codice fiscale deve essere letto eliminando gli spazi.");
	  	}

  }

  public void testSpecialErrorsEmptyLine() throws IOException {
		String normale = "P;Giovanni;Rossi;RSSGNN33B30F316I\n" +
					"P;Giuseppe; Verdi;VRDGPP76F09B666I\n" + 
					"\n" + // empty line 
					"M;345;Mario;Bianchi;BNCMRA44C99A320Z;Surgeon\n";

	  	String file = writeFile(normale);
	  	Clinic s = new Clinic();
	  	s.loadData(file);
	  	try{
			Doctor m = s.getDoctor(345);
			assertEquals("Cognome del dottore errato","Bianchi",m.getLast());
	  	}catch(NoSuchDoctor e){
	  		fail("Le linee anomale devono essere saltate.");
	  	}

  }
}
