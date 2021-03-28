import Domain.*;
import Repository.*;
import Service.*;
import UserInterface.*;

public class Main {

	public static void main(String[] args)  {  // functia driver a aplicatiei
		FileRepository repo = new FileRepository("C:\\Users\\George\\eclipse-workspace\\lab4\\Database");
		repo.loadFromFile();
		
		AngajatValidator validator = new AngajatValidator();
		
		Service service = new Service(repo, validator);
		
		Console console = new Console(service);
		
		console.runMenu();
	}
}


