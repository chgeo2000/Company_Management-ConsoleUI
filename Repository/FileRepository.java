package Repository;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import Domain.* ;

public class FileRepository {

private ArrayList<Angajat> angajati = new ArrayList<Angajat>();
private ArrayList<Companie> companii = new ArrayList<Companie>();
private String dbFilePath; 

public FileRepository(String dbFilePath) {   // constructor cu parametri 
	this.dbFilePath = dbFilePath;
}

public void loadFromFile()  {           // incarca array-listul "angajati" cu ce avem in fisierul angajati.csv si array-listul "companii" cu ce avem in companii.csv
	String angajatiFilePath = dbFilePath + "\\angajati.csv";
	String companiiFilePath = dbFilePath + "\\companii.csv";
	
	try (BufferedReader bReader = new BufferedReader(new FileReader(angajatiFilePath))){
		String line;
		while((line = bReader.readLine()) != null) {
			String[] data = line.split(",");
			Angajat angajat = new Angajat(data);
			this.createAngajat(angajat);
		}
		
	} catch (IOException e) {
		System.out.println(e.getMessage());
	}
	
	try (BufferedReader bReader = new BufferedReader(new FileReader(companiiFilePath))){
		String line;
		while((line = bReader.readLine()) != null) {
			String[] data = line.split(",");
			Companie companie = new Companie(data);
			this.createCompanie(companie);
		}
		
	} catch (IOException e) {
		System.out.println(e.getMessage());
	}
	
}

public void saveAngajati() {   // salveaza datele din array list-ul angajati in fisierul angajati.csv
	
	String angajatiFilePath = dbFilePath + "\\angajati.csv";
	try (BufferedWriter bWriter = new BufferedWriter(new FileWriter(angajatiFilePath))){
		
		for (Angajat angajat: angajati) {
			bWriter.append(angajat.toCSV() + "\n");
		}
	} catch (IOException e) {
		System.out.println(e.getMessage());
	}
	
}

public void saveCompanie() {  // salveaza companiile din array list-ul companii in fisierul companii.csv
	String companiiFilePath = dbFilePath + "\\companii.csv";
	try(BufferedWriter bWriter = new BufferedWriter (new FileWriter(companiiFilePath))){
		for (Companie companie: companii) {
			bWriter.append(companie.toCSV() + "\n");
		}
	} catch (IOException e ) {
		System.out.println(e.getMessage());
	}
}

public void createAngajat(Angajat angajat) throws IllegalArgumentException {  // creeaza un obiect de tip angajat
	
	Angajat A = this.findByIdAngajat(angajat.getIdAngajat());
	if (A.getIdAngajat() == -1) {
		angajati.add(angajat);
		this.saveAngajati();
	}
	else {
		throw new IllegalArgumentException("Angajatul cu id-ul " + angajat.getIdAngajat() + " deja exista");
	}
	
}

public void createCompanie(Companie companie) throws IllegalArgumentException {  // creeaza un obiect de tip companie
	Companie C = this.findByIdCompanie(companie.getId());
	if (C.getId() == -1) {
		companii.add(companie);
		this.saveCompanie();
	}
	else {
		throw new IllegalArgumentException("Compania cu id-ul " + companie.getId() + " deja exista");
	}
	
}


public Angajat findByIdAngajat (int id_angajat) {  // returneaza angajatul care are id-ul dat ca parametru
	
	for (Angajat angajat: angajati) {
		if (angajat.getIdAngajat() == id_angajat) {
			return angajat ; 
		}
			
	}
	return new Angajat();
}

public Companie findByIdCompanie (int id) {   // returneaza compania care are id-ul dat ca parametru
	
	for (Companie companie: companii) {
		if (companie.getId() == id) {
			return companie ; 
		}
			
	}
	return new Companie();
}

public ArrayList<Angajat> getAllAngajat() {  // returneaza toti angajatii
	return this.angajati;
}

public ArrayList<Companie> getAllCompanii() {  // returneaza toate companiile
	return this.companii;
}

public void deleteAngajat(int id) {  // sterge angajatul care are id-ul dat ca parametru
	ArrayList<Integer> angajati_de_sters = new ArrayList<Integer>();
	for (Angajat angajat: angajati) {
		if (angajat.getIdAngajat() == id) {
			angajati_de_sters.add(id);
		}
	}
	for (Integer elem: angajati_de_sters) {
		angajati.remove(this.findByIdAngajat(elem));
		this.saveAngajati();
	}
}

public void deleteCompanie(int id) {  // sterge compania care are id-ul dat ca parametru
	ArrayList<Integer> companii_de_sters = new ArrayList<Integer>();
	for (Companie companie: companii) {
		if(companie.getId() == id) {
			companii_de_sters.add(id);
		}		
	}
	for (Integer elem: companii_de_sters) {
		companii.remove(this.findByIdCompanie(elem));
		this.saveCompanie();
	}
}

public void updateAngajat(Angajat angajat) throws IllegalArgumentException{  // modifica atributele unui obiect de tip "angajat"
		
		Angajat A = this.findByIdAngajat(angajat.getIdAngajat());
		if (A.getIdAngajat() != -1) {
			int index = -1; 
			for (Angajat Angajat: angajati ) {
				if (Angajat.getIdAngajat() == angajat.getIdAngajat()) {
					index = angajati.indexOf(Angajat);
				}
			
			}
			angajati.set(index,  angajat);
			this.saveAngajati();
		}
		
		else {
			throw new IllegalArgumentException("Nu putem modifica angajatul cu id-ul " + angajat.getIdAngajat() + " pentru ca acesta nu exista. ");
		}
}
	
	


public void updateCompanie(Companie companie) {   // modifica atributele unui obiect de tip "companie"
	Companie C = this.findByIdCompanie(companie.getId());
	if (C.getId() == -1) {
		int index = -1 ; 
		for (Companie Companie: companii) {
			if (Companie.getId() == companie.getId())
				index = companii.indexOf(Companie);
		}
		companii.set(index, companie);
		this.saveCompanie();
	}
	else {
		throw new IllegalArgumentException("Nu putem modifica compania cu id-ul " + companie.getId() + " pentru ca aceasta nu exista. ");
	}
		
	}

}
