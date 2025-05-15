import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import Services.PetService;

import static Services.PetService.*;


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            menu(sc);
        }

    }

    public static void menu(Scanner sc) {
        PetService ps = null;
            try {
                String option;
                System.out.println("1-Cadastrar um novo Pet");
                System.out.println("2-Cadastro de Animal");
                System.out.println("3-Deletar um pet cadastrado");
                System.out.println("4-Listar todos os pets cadastrados");
                System.out.println("5-Listar pets por algum critério (idade, nome, raça)");
                System.out.println("6- Sair.");
                option = sc.next();
                sc.nextLine();
                switch (option) {
                    case "1":
                        newPet(sc);
                        break;
                    case "2":
                        //updatePet();
                        break;
                    case "3":
                        //deletePet();
                        break;
                    case "4":
                        listPets(sc);
                        break;
                    case "5":
                        buscaPet(sc);
                        break;
                    case "6":
                        System.out.println("Obrigado por usar o sistema de cadastro de pets!");
                        System.exit(0);
                    default:
                        System.out.println("Opção invalida!");
                        break;
                }

            } catch (InputMismatchException e) {
                System.out.println("opção invalida!");
            } catch (NoSuchElementException e) {
                System.out.println(e.getMessage());
            }
        }
    }





