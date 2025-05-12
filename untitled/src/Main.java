import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

    File formulario = new File("C:\\Users\\lucas\\Documents\\GitHub\\desafioCadastro\\formulario.txt");
    Scanner sc = new Scanner(formulario);
    while (sc.hasNextLine()) {
        System.out.println(sc.nextLine());
        
    }


    }
}