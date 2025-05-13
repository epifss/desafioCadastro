package Services;

import Entities.Pet;
import Enums.Sexo;
import Enums.Tipo;
import Exceptions.PetException;

import java.io.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PetService {
    private static List<Pet> pets = new ArrayList<Pet>();
    public void newPet() {
        File formulario = new File("formulario.txt");
        Scanner sc = null;
        try ( BufferedReader br = new BufferedReader(new FileReader(formulario))) {
            String[] questions = new String[10];
            sc = new Scanner(System.in);
            int i = 0;
            while (true) {
                questions[i] = br.readLine();
                i++;
                if (br.readLine()==null){
                    break;
                }
            }

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
            savePet(pet);
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
    public void savePet(Pet pet) {
        LocalDateTime now = LocalDateTime.now();
        String date = (now.format(DateTimeFormatter.ofPattern("YYYYMMDD'T'HHmm")));
        String name = pet.getNome().toUpperCase().replaceAll(" ", "");


        try (BufferedWriter bw = new BufferedWriter(new FileWriter("Saved_Pets\\"+date+"-"+name+".txt"));){
            bw.write("1-"+pet.getNome());
            bw.newLine();
            bw.write("2-"+pet.getTipo());
            bw.newLine();
            bw.write("3-"+pet.getSexo());
            bw.newLine();
            bw.write("4-"+pet.getEndereço());
            bw.newLine();
            bw.write("5-"+pet.getIdade()+" anos");
            bw.newLine();
            bw.write("6-"+pet.getPeso()+"Kg");
            bw.newLine();
            bw.write("7- "+pet.getRaça());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
