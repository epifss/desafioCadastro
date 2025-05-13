import java.util.Scanner;

import Services.PetService;


public class Main {
    public static void main(String[] args) {
        menu();
    }


    public static void menu() {
        PetService ps = null;
        try {
            int option;
            ps = new PetService();
            Scanner sc = new Scanner(System.in);
            System.out.println("1-Cadastrar um novo Pet");
            System.out.println("2-Cadastro de Animal");
            System.out.println("3-Deletar um pet cadastrado");
            System.out.println("4-Listar todos os pets cadastrados");
            System.out.println("5-Listar pets por algum critério (idade, nome, raça)");
            System.out.println("6- Sair.");
            option = sc.nextInt();
            sc.nextLine();
            switch (option) {
                case 1:
                    ps.newPet();
                    menu();
                case 2:
                    //updatePet();
                    menu();
                case 3:
                    //deletePet();
                    menu();
                case 4:
                    ps.listPets();
                    menu();
                case 5:
                    //filterPet();
                    menu();
                case 6:
                    System.out.println("Obrigado por usar o sistema de cadastro de pets!");
                    System.exit(0);
                default:
                    System.out.println("Opção invalida!");
                    menu();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



