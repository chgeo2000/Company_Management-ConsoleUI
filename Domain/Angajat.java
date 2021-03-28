package Domain;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Angajat {
	private int idAngajat;
	private String nume;
	private LocalDate zi_nastere;
	private String sex;
	private int id_companie;
	private double salariu_baza;
	private double rata_lunara;
	private int numar_luni_rata;
	
	
	public Angajat() {  // constructor implicit
		this.idAngajat =-1;  
	}
	public Angajat(int id, String nume, String zi_nastere, String sex, int id_companie, double salariu, double rata, int numar_luni) {  // constructor cu parametri
		
		String format = "yyyy-MM-dd";
		DateTimeFormatter date_time_formatter = DateTimeFormatter.ofPattern(format);
		
		this.idAngajat = id;
		this.nume = nume;
		this.zi_nastere = LocalDate.parse(zi_nastere, date_time_formatter);
		this.sex = sex;
		this.id_companie = id_companie;
		this.salariu_baza = salariu;
		this.rata_lunara = rata;
		this.numar_luni_rata = numar_luni;
		
	}

	public Angajat(String[] data) {   // constructor care transforma un array de stringuri intr-un obiect de tip "Angajat"
		String format = "yyyy-MM-dd";
		DateTimeFormatter date_time_formatter = DateTimeFormatter.ofPattern(format);
		
		this.idAngajat = Integer.parseInt(data[0]);
		this.nume = data[1];
		this.zi_nastere = LocalDate.parse(data[2], date_time_formatter);
		this.sex = data[3];
		this.id_companie = Integer.parseInt(data[4]);
		this.salariu_baza = Double.parseDouble(data[5]);
		this.rata_lunara = Double.parseDouble(data[6]);
		this.numar_luni_rata = Integer.parseInt(data[7]);
		
	}
	
	public int getIdAngajat() {  // getter
		return this.idAngajat;
	}
	public String getNume() {  // getter
		return this.nume;
	}
	
	public  LocalDate getZiNastere() {   // getter
		return this.zi_nastere;
		}
	
	public String getSex() {    // getter
		return this.sex;
	}
	
	public int getIdCompanie() {  // getter
		return this.id_companie;
	}
	
	public double getSalariuBaza() {  // getter
		return this.salariu_baza;
	}
	
	public double getRataLunara() {  // getter
		return this.rata_lunara;
	}
	
	public int getNumarLuniRata() {   // getter
		return this.numar_luni_rata;
	}
	
	public void setNume(String new_name) {  // setter
		this.nume = new_name;
	}
	
	public void setZiNastere(String new_zi_nastere) {  // setter
		String format = "yyyy-MM-dd";
		DateTimeFormatter date_time_formatter = DateTimeFormatter.ofPattern(format);
		
		this.zi_nastere = LocalDate.parse(new_zi_nastere, date_time_formatter);
	}
	
	public void setSex(String new_sex) {   // setter
		this.sex = new_sex;
	}
	
	public void setIdCompanie(int new_id_companie) {   // setter
		this.id_companie = new_id_companie;
	}
	
	public void setSalariuBaza(double new_salariu_baza) {    // setter
		this.salariu_baza = new_salariu_baza;
	}
	
	public void setRataLunara(double new_rata_lunara) {    // setter
		this.rata_lunara = new_rata_lunara;
		}
	
	public void setNumarLuni(int new_numar_luni) {    // setter
		this.numar_luni_rata = new_numar_luni;
	}
	
	public boolean trebuiePensionat() {    // metoda care afiseaza daca un angajat a ajuns la varsta de pensionare sau nu.
		if( (this.getSex().equals("masculin") && Period.between(this.getZiNastere(), LocalDate.now()).getYears() >= 65) || (this.getSex().equals("feminin")   && Period.between(this.getZiNastere(), LocalDate.now()).getYears() >= 63)) 
			{
				return true;
			}
			
		else
			{
				return false;
			}
			
	}
	
	
	public String toCSV() {    // // metoda care returneaza un string de tip csv cu informatiile angajatului
		return this.idAngajat + "," + this.nume + "," + this.zi_nastere+ "," + this.sex+ "," + this.id_companie + "," + this.salariu_baza +  "," + this.rata_lunara+ "," + this.numar_luni_rata;
	}
	
}
