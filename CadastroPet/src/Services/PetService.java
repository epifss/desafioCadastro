package Services;

import Entities.Pet;
import Enums.Sexo;
import Enums.Tipo;
import Exceptions.PetException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PetService {
    private static List<Pet> pets = new ArrayList<Pet>();
    public void newPet() {
        Scanner sc = null;
        File formulario = new File("formulario.txt");
        try ( BufferedReader br = new BufferedReader(new FileReader(formulario))){
            String[] questions = new String[10];
            int i = 0;
            while (true) {
                questions[i] = br.readLine();
                i++;
                if (br.readLine()==null){
                    break;
                }
            }
            sc = new Scanner(System.in);
            System.out.println(questions[0]);
            String nome=sc.nextLine();

            System.out.println(questions[1]);
            String tp=sc.nextLine();
            Tipo tipo = Tipo.valueOf(tp.toUpperCase());

            System.out.println(questions[2]);
            String sx = sc.nextLine();
            Sexo sexo = Sexo.valueOf(sx.toUpperCase());

            System.out.println(questions[3]);
            System.out.println("numero da casa: ");
            String num = sc.nextLine();
            System.out.println("Cidade: ");
            String cidade = sc.nextLine();
            System.out.println("Rua: ");
            String rua = sc.nextLine();
            String endereço= (rua+", "+num+", "+cidade);

            System.out.println(questions[4]);
            double idade = sc.nextDouble();
            sc.nextLine();

            System.out.println(questions[5]);
            double peso = sc.nextDouble();
            sc.nextLine();

            System.out.println(questions[6]);
            String raça = sc.nextLine();
            Pet pet = new Pet(nome, tipo, sexo, endereço, idade, peso, raça);
            pets.add(pet);
            System.out.println("Pet cadastrado com sucesso!");

        } catch (FileNotFoundException e) {
            e.getMessage();
        }
        catch (PetException e){
            System.out.println(e.getMessage());
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        catch (IllegalArgumentException e){
            System.out.println("Erro: " + e.getMessage());
        }
    }
    public void listPets() {
        int i=0;
    for (Pet pet : pets) {
        i++;
        System.out.println(i+"-"+ pet.getNome()+","+ pet.getIdade()+","+ pet.getSexo());
    }
    }
}
