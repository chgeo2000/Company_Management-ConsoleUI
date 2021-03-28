package UserInterface;
import Service.*;
import java.util.Scanner;

import Domain.Angajat;
import Domain.Companie;


public class Console {
	private Service service;
	private Scanner scan;
	
	public Console(Service s) {   // constructor cu parametri
		this.service = s;
		this.scan = new Scanner(System.in);
	}
	
	
	public static void showMenu() {  // metoda care afiseaza meniul problemei
        System.out.println("1.  CRUD angajat");
        System.out.println("2.  CRUD companie");
        System.out.println("3.  Afisare tabel fluturasii cu venit lunar");
        System.out.println("4.  Afisare tabel cu angajatii cu salariu mic");
        System.out.println("5.  Afisare tabel cu angajatii care trebuie sa se pensioneze");
        System.out.println("6.  Afisare tabel cu angajatii care au de platit rate lunare");
        System.out.println("x.  Inchide");
	}
	
	public void runMenu() {  // functia driver a consolei 
		boolean run = true;
		String optiune;
		
		while(run) {
			
			showMenu();
			System.out.print("Alegeti optiunea: ");
			optiune = this.scan.next();
			System.out.println();
			
			switch(optiune){
			
			case "1" : this.runCRUDAngajat();
					   break;
					   
			case "2" : this.runCRUDCompanie();
					   break;
					   
			case "3" : this.handleShowFluturasiVenitLunar();
					   break; 
					   
			case "4" : this.handleShowAngajatCuSalariuMic();
					   break;
					   
			case "5" : this.handleShowAngajatiPensionati();
					   break;
					   
			case "6" : this.handleShowAngajatiCuRateLunare();
					   break;
				
			case "x" : run = false;
					   break;
			
			default : System.out.println("Optiune inexistenta. Incearca din nou.");
					  break;
				
				
				
			}
			
		}
		
	}
	
	public void runCRUDAngajat(){  // functia driver pentru operatiile "CRUD" angajat
		boolean run = true;
		String optiune;
		
		while(run) {
			
			System.out.println("1. Adauga angajat.");
			System.out.println("2. Afiseaza toti angajatii");
			System.out.println("3. Modifica angajat");
			System.out.println("4. Sterge angajat");
			System.out.println("b. Back");
			
			System.out.print("Alegeti optiunea: ");
			optiune = this.scan.next();
			System.out.println();
			
			switch(optiune) {
			
			case "1": this.handleAddAngajat();
					  break;
					  
			case "2": this.handleShowAllAngajati();
					  break;
					  
			case "3":this.handleUpdateAngajat();
					 break;
					 
			case "4":this.handleDeleteAngajat();
			         break;
			         
			case "b": run = false;
					  break;
			
			default : System.out.println("Optiune inexistenta. Incearca din nou.");
					 break;
			}
	}
	
	}
	
	public void runCRUDCompanie() {   // functia driver pentru operatiile "CRUD" companie
		boolean run = true;
		String optiune;
		
		while(run) {
			
			System.out.println("1. Adauga companie.");
			System.out.println("2. Afiseaza toate companiile");
			System.out.println("3. Modifica companie");
			System.out.println("4. Sterge companie");
			System.out.println("b. Back");
			
			System.out.print("Alegeti optiunea: ");
			optiune = this.scan.next();
			System.out.println();
			
			switch(optiune) {
			
			case "1": this.handleAddCompanie();
					  break;
					  
			case "2": this.handleShowAllCompanii();
					  break;
					  
			case "3":this.handleUpdateCompanie();
					 break;
					 
			case "4":this.handleDeleteCompanie();
			         break;
			         
			case "b": run = false;
					  break;
			
			default : System.out.println("Optiune inexistenta. Incearca din nou.");
					 break;
			}
	}
	}
	
	public void handleAddAngajat() {  // handler pentru adaugarea unui angajat
		try{
			
		System.out.print("Dati id-ul angajatului: ");

		int id = this.scan.nextInt();
		
		System.out.print("Dati numele angajatului: ");
		this.scan.nextLine();
		String nume = this.scan.nextLine();
		
		System.out.print("Dati data nasterii angajatului: ");
		String zi_nastere = this.scan.nextLine();
		
		System.out.print("Dati sexul angajatului: ");
		String sex = this.scan.nextLine();
		
		
		System.out.print("Dati id-ul companiei la care lucreaza angajatul: ");
		int id_companie = this.scan.nextInt();
		
		System.out.print("Dati salariul angajatului: ");
		double salariu = this.scan.nextDouble();
		
		System.out.print("Dati rata lunara: ");
		double rata = this.scan.nextDouble();
		
		System.out.print("Dati numarul de luni: ");
		int numar_luni = this.scan.nextInt();
		
				
		this.service.createAngajat(id,  nume,  zi_nastere, sex, id_companie, salariu, rata, numar_luni);
		 
			} catch(IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		
	}
	
	public void handleShowAllAngajati() {  // handler pentru afisarea tuturor angajatilor
		String output;
		System.out.println();
		
		output = String.format("---------------------------------------------------------------------TABEL ANGAJATI-----------------------------------------------------------------") + " \n";
		System.out.println();
		output += String.format("%10s %20s %30s %10s %15s %13s %20s %20s", "Id angajat", "Nume angajat", "Data nasterii", "Sex", "Id companie", "Salariu", "Rata lunara", "Numar luni rata") + " \n";
		output += " \n";
		for (Angajat angajat: this.service.getAllAngajati()) {
			output += String.format("%14s | %-30s | %-12s | %10s | %10s | %10s | %20s | %20s |", angajat.getIdAngajat(), angajat.getNume(), angajat.getZiNastere(), angajat.getSex(), angajat.getIdCompanie(), angajat.getSalariuBaza(), angajat.getRataLunara(), angajat.getNumarLuniRata()) + " \n";
	
		}
		System.out.println(output);
		System.out.println();
	}
	
	public void handleUpdateAngajat() {  // handler pentru modificarea unui angajat
		try{
			System.out.print("Dati id-ul angajatului ale carui atribute doriti sa le modificati: ");
			int id = this.scan.nextInt();
		
			System.out.print("Dati un nume nou: ");
			this.scan.nextLine();
			String nume = this.scan.nextLine();
			
			System.out.print("Dati sex nou: ");
			String sex = this.scan.nextLine();
			
			
			System.out.print("Dati un nou id de companie: ");
			int id_companie = this.scan.nextInt();
			
			System.out.print("Dati un nou salariu: ");
			double salariu = this.scan.nextDouble();
			
			System.out.print("Dati o noua rata lunara: ");
			double rata = this.scan.nextDouble();
			
			System.out.print("Dati un alt numar de luni: ");
			int numar_luni = this.scan.nextInt();
			
			this.service.updateAngajat(id, nume, sex, id_companie, salariu, rata, numar_luni);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void handleDeleteAngajat() {  // handler pentru stergerea unui angajat
		System.out.print("Dati id-ul angajatului pe care doriti sa-l stergeti: ");
		int id = this.scan.nextInt();
		
		this.service.deleteAngajat(id);
	}
	
	
	
	public void handleAddCompanie() {  // handler pentru adaugare companie
		try{
			System.out.print("Dati id-ul companiei:");
			int id = this.scan.nextInt();
			
			System.out.print("Dati numele companiei: ");
			this.scan.nextLine();
			String nume = this.scan.nextLine();
			
			this.service.createCompanie(id, nume);
		   } catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public void handleShowAllCompanii() {  // handler pentru afisarea tuturor companiilor
		String output;
		System.out.println();
		
		output = String.format("----------- COMPANII-------------") + " \n";
		output += String.format("%-9s %-16s", "Id", "Nume companie") + " \n";
		output += "\n";
		for (Companie companie: this.service.getAllCompanii()) {
			output += String.format("%8s | %-20s |", companie.getId(), companie.getNumeCompanie()) + " \n";
		}
		
		System.out.println(output);
		System.out.println();
	}
	
	public void handleUpdateCompanie() {  // handler pentru modificarea unei companii
		try {
			System.out.println("Dati id-ul companiei pe care doriti sa o modificati: ");
			int id = this.scan.nextInt();
			
			System.out.println("Dati noul nume al companiei: ");
			String nume = this.scan.nextLine();
			
			this.service.updateCompanie(id, nume);
	} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void handleDeleteCompanie() {   // handler pentru stergerea unei companii
		System.out.println("Dati id-ul companiei pe care doriti sa-l stergeti: ");
		int id = this.scan.nextInt();
		
		this.service.deleteCompanie(id);
	}
		
	
	public void handleShowFluturasiVenitLunar() {  // handler pentru afisarea fluturasului cu venit lunar
		System.out.print("Dati id-ul angajatului al carui fluturas de salariu doriti sa-l vedeti: ");
		int id = this.scan.nextInt();
		
		System.out.print("Dati luna pentru care doriti sa vedeti fluturasul de salariu: ");
		this.scan.nextLine();
		String month = this.scan.nextLine();
		
		System.out.print("Dati sporurile angajatului pe luna respectiva: ");
		int sporuri = this.scan.nextInt();
		
		System.out.print("Dati retinerile angajatului pe luna respectiva: ");
		int retineri = this.scan.nextInt();
		
		System.out.println();
		System.out.println(this.service.fluturasVenitLunar(id, month, sporuri, retineri));
	}
	
	public void handleShowAngajatCuSalariuMic() {  // handler pentru afisarea angajatilor cu salariu mai mic decat media salariilor firmei unde lucreaza
		
		System.out.print("Dati id-ul firmei ai carei angajati prost platiti doriti sa-i vizualizati: ") ;
		int id = this.scan.nextInt();
		
		System.out.println();
		System.out.println(this.service.angajatiCuSalariuMic(id));
	}
	
	public void handleShowAngajatiPensionati() {  // handler pentru afisarea angajatilor care au implinit varsta pensionarii
		System.out.println("Varsta de pensionare este: 65 ani la barbati si 63 ani la femei.");
		System.out.println();
		System.out.println(this.service.angajatiPensionati());
		
	}
	
	public void handleShowAngajatiCuRateLunare() {  // handler pentru afisarea anagajatilor care au de platit rate lunare 
		System.out.println(this.service.angajatiCuRateLunare());
	}
		
}
