package Service;
import Repository.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import Domain.*;

public class Service {
	private FileRepository repository; 
	private AngajatValidator validator;
	
	public Service(FileRepository repository, AngajatValidator validator ) {   // constructor cu parametri 
		this.repository = repository;
		this.validator = validator;
		}
	
	/*
	 * Operatii CRUD pentru ambele entitati.
	 */
	
	public void createAngajat(int id, String nume, String zi_nastere, String sex, int id_companie, double salariu, double rata, int numar_luni)  {
		Angajat angajat = new Angajat(id, nume, zi_nastere, sex, id_companie, salariu, rata, numar_luni);
		
		this.validator.validate(angajat);
		this.repository.createAngajat(angajat);
	}
	
	public void createCompanie(int id, String nume_companie){
		Companie companie = new Companie(id, nume_companie);
		this.repository.createCompanie(companie);
	}
	
	public ArrayList<Angajat> getAllAngajati() {
		return this.repository.getAllAngajat();
	}
	
	public ArrayList<Companie> getAllCompanii(){
		return this.repository.getAllCompanii();
	}
	
	public void updateAngajat(int id, String nume, String sex, int id_companie, double salariu, double rata, int numar_luni) {  // data nasterii nu se poate modifica
		Angajat angajat = this.repository.findByIdAngajat(id);
		
		angajat.setNume(nume);
		angajat.setSex(sex);
		angajat.setIdCompanie(id_companie);
		angajat.setSalariuBaza(salariu);
		angajat.setRataLunara(rata);
		angajat.setNumarLuni(numar_luni);

		this.repository.updateAngajat(angajat);
	}
	
	public void updateCompanie(int id, String nume_companie) {
		Companie companie = this.repository.findByIdCompanie(id);
		
		companie.setNumeCompanie(nume_companie);
		
		this.repository.updateCompanie(companie);
	}
	
	public void deleteAngajat(int id) {
		
		this.repository.deleteAngajat(id);
	}
	
	public void deleteCompanie(int id) {
		this.repository.deleteCompanie(id);
	}
	
	public String fluturasVenitLunar(int Id, String month, int sporuri, int retineri) {  // returneaza un string sub forma de tabel care reprezinta fluturasul cu venitul lunar al unui angajat
		
		String output;
		Angajat A = this.repository.findByIdAngajat(Id);
		Companie C = this.repository.findByIdCompanie(A.getIdCompanie());
		double salariu_brut = A.getSalariuBaza() + sporuri;		
		
		output = String.format("--------------FLUTURAS VENIT LUNAR----------------") + " \n";
		output += String.format("%-35s", month+ "/2021") + " \n";
		output += " \n" ;
		output += String.format("%-35s  %10s", "Nume firma ", C.getNumeCompanie() ) + " \n";
		output += String.format("%-35s  %10s","Nume salariat ", A.getNume()) + " \n";
		output += " \n";
		output += String.format("%-35s | %10s","Salariu de baza ", A.getSalariuBaza() + " lei" ) + " \n";
		output += String.format("%-35s | %10s", "Sporuri", sporuri + " lei" ) + " \n";
		output += String.format("%-35s | %10s", "Salariu brut", salariu_brut + " lei" ) + " \n";
		output += String.format("%-35s | %10s", "Asigurari sociale de sanatate 20%", (20*salariu_brut)/100 + " lei") + " \n";
		output += String.format("%-35s | %10s", "Impozit pe venit 10%", (10*salariu_brut)/100 + " lei" ) + " \n";
		output += String.format("%-35s | %10s" , "Total taxe",  (30*salariu_brut)/100 + " lei" ) + " \n";
		output += String.format("%-35s | %10s", "Salariu net", salariu_brut - ((30*salariu_brut )/100) + " lei" )+  " \n";
		output += String.format("%-35s | %10s", "Retineri", retineri + " lei" ) + " \n";   
		output += String.format("%-35s | %10s", "Rest de plata", salariu_brut - (30*salariu_brut/100) - retineri + " lei" ) + " \n";
		
		return output;
	}
	
	
	
	public String angajatiCuSalariuMic(int id) {  // returneaza un tabel cu angajatii care au salariul mai mic decat media salariilor firmei in care lucreaza
	
		Companie C = this.repository.findByIdCompanie(id);
		String output;
		double suma_salarii = 0 ; 
		int numar_angajati = 0;
		
		for (Angajat angajat: this.repository.getAllAngajat()) {
			if (angajat.getIdCompanie() == id) {
				suma_salarii += angajat.getSalariuBaza();
				numar_angajati += 1;
			}
			
		}
		
		double salariu_mediu = suma_salarii / numar_angajati;
		
		System.out.println("Salariul mediu al angajatilor companiei " + C.getNumeCompanie() + " este: " + String.format("%.2f", salariu_mediu) + " lei.");
		System.out.println();
		output = String.format("----------ANGAJATI CU SALARIU MIC----------") + " \n";
		output += String.format("%-19s %-15s", "Nume angajat", "Salariu") + " \n";
		output += " \n";
		for (Angajat angajat: this.repository.getAllAngajat()) {
			if (angajat.getIdCompanie() == id && angajat.getSalariuBaza() < salariu_mediu) {
				output += String.format("%-18s | %20s |", angajat.getNume(), angajat.getSalariuBaza() + " lei") + " \n";
			}
		}
		return output ; 
	}
	
	
	public String angajatiPensionati() {   // returneaza un string sub forma de tabel cu angajatii care au implinit varsta pensionarii
		String output;
		System.out.println();
		
		output = String.format("------------PENSIONARI-------------") + " \n";
		output += " \n";
		output += String.format("%-21s %-20s", "Nume angajat", "Varsta") + " \n";
		output += " \n";
		for (Angajat angajat: this.repository.getAllAngajat()) {
			if (angajat.trebuiePensionat()) {
				
				output += String.format("%-20s | %10s |", angajat.getNume(), Period.between(angajat.getZiNastere(), LocalDate.now()).getYears()) + " \n";
			}
			
		}
		
		return output ; 
	}
	
	public String angajatiCuRateLunare() {   // returneaza un string sub forma de tabel cu angajatii care au de platit rate lunare
		String output ; 
		double total_restante = 0.0;
		
		output = String.format("--------------------------------------PERSOANE CU RATE LUNARE-------------------------------") + " \n";
		output += String.format("%-25s %-25s %-25s %-20s", "Nume ", "Rata lunara", "Numar luni", "De platit"  ) + " \n";
		output += " \n";
		
		for (Angajat angajat: this.repository.getAllAngajat()) {
			if (angajat.getRataLunara() != 0) {
				total_restante += angajat.getRataLunara() * angajat.getNumarLuniRata();
				output += String.format("%-20s | %20s | %20s | %20s |", angajat.getNume(), angajat.getRataLunara() +" lei", angajat.getNumarLuniRata() + " luni", String.format("%.2f", angajat.getRataLunara() * angajat.getNumarLuniRata()) + " lei") + " \n";
			}
		}
		output += String.format("%89s", "Suma totala: " + String.format("%.3f", total_restante) + " lei");
		return output; 
	}
	
}
