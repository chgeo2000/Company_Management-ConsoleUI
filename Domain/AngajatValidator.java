package Domain;
import java.util.ArrayList;

public class AngajatValidator {

private ArrayList<String> erori_posibile = new ArrayList<String>();

public AngajatValidator() {        // constructor implicit
		
	this.erori_posibile.clear();
}
public void validate(Angajat angajat) throws IllegalArgumentException {  // validator pentru obiect de tip "Angajat"
	
	if ( (angajat.getRataLunara() == 0 && angajat.getNumarLuniRata() != 0) || (angajat.getRataLunara() != 0 && angajat.getNumarLuniRata() == 0)) {
		this.erori_posibile.add("Eroare");
	}
		
	if (this.erori_posibile.size() == 1)
		throw new IllegalArgumentException("Daca rata lunara este 0, atunci numarul de luni trebuie sa fie 0 si viceversa.");
}

}