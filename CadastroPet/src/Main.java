
import Entities.Pet;
import Exceptions.PetException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
     while (true) {
         try {
             int option;
             Scanner sc = new Scanner(System.in);
             System.out.println("1-Cadastrar um novo Pet");
             System.out.println("2-Cadastro de Animal");
             System.out.println("3-Deletar um pet cadastrado");
             System.out.println("4-Listar todos os pets cadastrados");
             System.out.println("5-Listar pets por algum critério (idade, nome, raça)");
             System.out.println("6- Sair.");
             option = sc.nextInt();
             switch (option) {
                 case 1:
                     newPet();
                 case 2:
                     //updatePet();
                     break;
                 case 3:
                     //deletePet();
                     break;
                 case 4:
                     //listAllPets();
                     break;
                 case 5:
                     //filterPet();
                     break;
                 case 6:
                     System.out.println("Obrigado por usar o sistema de cadastro de pets!");
                     System.exit(0);
                     break;
                 default:
                     System.out.println("Opção invalida!");
             }

         } catch (Exception e) {}

     }

    }
    public static void newPet() {
        Scanner sc = null;
        try {
            File formulario = new File("C:/Users/lucas/Documents/GitHub/desafioCadastro/formulario.txt");
            sc = new Scanner(formulario);

            System.out.println(sc.nextLine());
            String nome=sc.nextLine();
            System.out.println(sc.nextLine());
            String tipo =sc.nextLine();
            System.out.println(sc.nextLine());
            String sexo =sc.nextLine();
            System.out.println(sc.nextLine());
            System.out.println("numero da casa: ");
            String num = sc.nextLine();
            System.out.println("Cidade: ");
            String cidade = sc.nextLine();
            System.out.println("Rua: ");
            String rua = sc.nextLine();
            String endereço= (rua+", "+num+", "+cidade);
            double idade = sc.nextDouble();
            sc.nextLine();



            //Pet pet = new Pet(nome, tipo, sexo, endereço, idade, peso, raça);
        } catch (FileNotFoundException e) {
            e.getMessage();
       // }catch (PetException e){
         //   e.getMessage();
        }
        finally {
            sc.close();
        }

    }
}
