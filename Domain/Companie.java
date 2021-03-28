package Domain;

public class Companie {

	private int id;
	private String nume_companie;
	
	public Companie() {   // constructor implicit
		this.id = -1;
	}
	
	public Companie(int id, String nume_companie ) {   // constructor cu parametri
		this.id =id ;
		this.nume_companie = nume_companie;
	}
	
	public Companie(String[] data) {   // constructor care transforma un array de stringuri intr-un obiect de tip "Companie"
		this.id = Integer.parseInt(data[0]);
		this.nume_companie = data[1];
	}
	
	public int getId() {    // getter
		return this.id;
	}
	
	public String getNumeCompanie() {    // getter
		return this.nume_companie;
	}
	
	public void setNumeCompanie(String new_nume) {   // setter
		this.nume_companie = new_nume ;
		}
	
	
	public String toCSV() {    // metoda care returneaza un string de tip csv cu informatiile companiei
		return this.id+","+ this.nume_companie;
	}
}