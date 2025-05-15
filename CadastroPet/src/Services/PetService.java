package Services;

import Entities.Pet;
import Enums.Sexo;
import Enums.Tipo;
import Exceptions.PetException;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PetService {
    private static final List<Pet> pets = new ArrayList<>();

    public static void newPet(Scanner sc) {
        File formulario = new File("formulario.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(formulario))) {
            String[] questions = new String[10];
            sc = new Scanner(System.in);
            int i = 0;
            do {
                questions[i] = br.readLine();
                i++;
            } while (br.readLine() != null);

            System.out.println(questions[0]);
            String nome = sc.nextLine();

            System.out.println(questions[1]);
            String tp = sc.nextLine();
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
            String endereço = (rua + ", " + num + ", " + cidade);

            System.out.println(questions[4]);
            double idade = sc.nextDouble();
            sc.nextLine();

            System.out.println(questions[5]);
            double peso = sc.nextDouble();
            sc.nextLine();

            System.out.println(questions[6]);
            String raça = sc.nextLine();
            Pet pet = new Pet(nome.toLowerCase(), tipo, sexo, endereço.toLowerCase(), idade, peso, raça.toLowerCase());
            savePet(pet);
            pets.add(pet);
            System.out.println("Pet cadastrado com sucesso!");

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (PetException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public static void listPets(Scanner sc) {
        int i = 0;
        for (Pet pet : pets) {
            i++;
            System.out.println(i + "-" + pet.getNome() + "," + pet.getIdade() + "," + pet.getSexo());
        }

    }

    public static void savePet(Pet pet) {
        LocalDateTime now = LocalDateTime.now();
        String date = (now.format(DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmm")));
        String name = pet.getNome().toUpperCase().replaceAll(" ", "");
        BufferedWriter bw;
        File arquivo;

        try {
            if (!(pet.getArquivo()==null)) {
                arquivo = pet.getArquivo();
                bw = new BufferedWriter(new FileWriter(pet.getArquivo().getPath()));
            } else {
                arquivo = new File(("Saved_Pets\\" + date + "-" + name + ".txt"));
                bw = new BufferedWriter(new FileWriter(arquivo));
            }
            bw.write("1-" + pet.getNome().toUpperCase());
            bw.newLine();
            bw.write("2-" + pet.getTipo());
            bw.newLine();
            bw.write("3-" + pet.getSexo());
            bw.newLine();
            bw.write("4-" + pet.getEndereço().toUpperCase());
            bw.newLine();
            bw.write("5-" + pet.getIdade() + " anos");
            bw.newLine();
            bw.write("6-" + pet.getPeso() + "Kg");
            bw.newLine();
            bw.write("7- " + pet.getRaça().toUpperCase());
            pet.setArquivo(arquivo);
            System.out.println(pet.getArquivo());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void buscaPet(Scanner sc) {
        List <Integer> busca= filterPet(sc);
        System.out.println("Resultado da busca: ");
        for (Integer index : busca) {
            System.out.println((index+1)+"-"+pets.get(index));
        }


    }

    public static List<Integer> filterPet(Scanner sc) {
        List<Integer> petsIndex = new ArrayList<>();
        System.out.println("Escolha o critério de busca:");
        System.out.println("1 - Nome ou sobrenome");
        System.out.println("2 - Sexo");
        System.out.println("3 - Idade");
        System.out.println("4 - Peso");
        System.out.println("5 - Raça");
        System.out.println("6 - Endereço");
        int opcao = sc.nextInt();
        sc.nextLine();
        /*System.out.println("Escolha o 2° critério de busca:");
        System.out.println("1 - Nome ou sobrenome");
        System.out.println("2 - Sexo");
        System.out.println("3 - Idade");
        System.out.println("4 - Peso");
        System.out.println("5 - Raça");
        System.out.println("6 - Endereço");
        int opcao2 = sc1.nextInt();
        sc1.nextLine();*/
        List <Integer> resultado;
        switch (opcao) {
            case 1:
                System.out.println("Digite o nome do pet: ");
                String nome = sc.nextLine();

                System.out.println("selecione o tipo:\n1-cachorro\n2-gato ");
                int opcao1 = sc.nextInt();
                sc.nextLine();
                Tipo tipo = null;
                switch (opcao1) {
                    case 1:
                        tipo = Tipo.CACHORRO;
                        break;
                    case 2:
                        tipo = Tipo.GATO;
                        break;
                    default:
                        System.out.println("opção invalida");
                        break;
                }
                resultado = filtraPorNome(nome, tipo,sc);
                for (Integer index : resultado) {
                    System.out.println(pets.get(index));
                }
                return resultado;
            case 2:
                System.out.println("Digite o sexo do pet (MACHO/FEMEA): ");
                String sx = sc.nextLine();
                Sexo sexo = Sexo.valueOf(sx.toUpperCase());

                System.out.println("selecione o tipo:\n1-cachorro\n2-gato ");
                int opcao2 = sc.nextInt();
                sc.nextLine();
                tipo = null;
                switch (opcao2) {
                    case 1:
                        tipo = Tipo.CACHORRO;
                        break;
                    case 2:
                        tipo = Tipo.GATO;
                        break;
                    default:
                        System.out.println("opção invalida");
                        break;
                }
                resultado = filtraPorSexo(sexo, tipo, sc);
                for (Integer index : resultado) {
                    System.out.println(pets.get(index));
                }
                return resultado;

            case 3:
                System.out.println("Digite a idade do pet: ");
                double idade = sc.nextDouble();
                sc.nextLine();

                System.out.println("selecione o tipo:\n1-cachorro\n2-gato ");
                int opcao3 = sc.nextInt();
                sc.nextLine();
                tipo = null;
                switch (opcao3) {
                    case 1:
                        tipo = Tipo.CACHORRO;
                        break;
                    case 2:
                        tipo = Tipo.GATO;
                        break;
                    default:
                        System.out.println("opção invalida");
                        break;
                }
                resultado = filtraPorIdade(idade, tipo, sc);
                for (Integer index : resultado) {
                    System.out.println(pets.get(index));
                }
                return resultado;

            case 4:
                System.out.println("Digite o peso do pet: ");
                double peso = sc.nextDouble();
                sc.nextLine();

                System.out.println("selecione o tipo:\n1-cachorro\n2-gato ");
                int opcao4 = sc.nextInt();
                sc.nextLine();
                tipo = null;
                switch (opcao4) {
                    case 1:
                        tipo = Tipo.CACHORRO;
                        break;
                    case 2:
                        tipo = Tipo.GATO;
                        break;
                    default:
                        System.out.println("opção invalida");
                        break;
                }
                resultado = filtraPorPeso(peso, tipo, sc);
                for (Integer index : resultado) {
                    System.out.println(pets.get(index));
                }
                return resultado;

            case 5:
                System.out.println("Digite a raça do pet: ");
                String raça = sc.nextLine();

                System.out.println("selecione o tipo:\n1-cachorro\n2-gato ");
                int opçao = sc.nextInt();
                sc.nextLine();
                tipo = null;
                switch (opçao) {
                    case 1:
                        tipo = Tipo.CACHORRO;
                        break;
                    case 2:
                        tipo = Tipo.GATO;
                        break;
                    default:
                        System.out.println("opção invalida");
                        break;
                }
                resultado = filtraPorRaça(raça, tipo,sc);
                return resultado;
            case 6:
                System.out.println("Digite o endereço do pet (rua, número, cidade): ");
                String endereco = sc.nextLine().toLowerCase();

                System.out.println("selecione o tipo:\n1-cachorro\n2-gato ");
                int opcao6 = sc.nextInt();
                sc.nextLine();
                tipo = null;
                switch (opcao6) {
                    case 1:
                        tipo = Tipo.CACHORRO;
                        break;
                    case 2:
                        tipo = Tipo.GATO;
                        break;
                    default:
                        System.out.println("opção invalida");
                        break;
                }
                resultado = filtraPorEndereco(endereco, tipo, sc);
                for (Integer index : resultado) {
                    System.out.println(pets.get(index));
                }
                return resultado;
        }
        return null;
    }

    public static List<Integer> filtraPorNome(String nome, Tipo tipo,Scanner sc) {
        List<Integer> petsPorNome = new ArrayList<>();
        for (Pet pet : pets) {
            if (pet.getNome().toLowerCase().contains(nome) && pet.getTipo() == tipo) {
                petsPorNome.add(pets.indexOf(pet));
            }
        }
        return petsPorNome;
    }
    public static List<Integer> filtraPorSexo(Sexo sexo, Tipo tipo, Scanner sc) {
        List<Integer> petsPorSexo = new ArrayList<>();
        for (Pet pet : pets) {
            if (pet.getSexo() == sexo && pet.getTipo() == tipo) {
                petsPorSexo.add(pets.indexOf(pet));
            }
        }
        return petsPorSexo;
    }

    public static List<Integer> filtraPorIdade(double idade, Tipo tipo, Scanner sc) {
        List<Integer> petsPorIdade = new ArrayList<>();
        for (Pet pet : pets) {
            if (pet.getIdade() == idade && pet.getTipo() == tipo) {
                petsPorIdade.add(pets.indexOf(pet));
            }
        }
        return petsPorIdade;
    }

    public static List<Integer> filtraPorPeso(double peso, Tipo tipo, Scanner sc) {
        List<Integer> petsPorPeso = new ArrayList<>();
        for (Pet pet : pets) {
            if (pet.getPeso() == peso && pet.getTipo() == tipo) {
                petsPorPeso.add(pets.indexOf(pet));
            }
        }
        return petsPorPeso;
    }

    public static List<Integer> filtraPorEndereço(String endereco, Tipo tipo, Scanner sc) {
        List<Integer> petsPorEndereco = new ArrayList<>();
        for (Pet pet : pets) {
            if (pet.getEndereço().toLowerCase().contains(endereco.toLowerCase()) && pet.getTipo() == tipo) {
                petsPorEndereco.add(pets.indexOf(pet));
            }
        }
        return petsPorEndereco;
    }
    public static List<Integer> filtraPorRaça(String raça, Tipo tipo,Scanner sc) {
        List<Integer> petsPorRaça = new ArrayList<>();
        for (Pet pet : pets) {
            if (pet.getRaça().toLowerCase().contains(raça) && pet.getTipo() == tipo) {
                petsPorRaça.add(pets.indexOf(pet));
                pets.indexOf(pet);

            }
        }
        return petsPorRaça;
    }
    public static List<Integer> filtraPorEndereco(String endereco, Tipo tipo, Scanner sc) {
        List<Integer> petsPorEndereco = new ArrayList<>();
        for (Pet pet : pets) {
            if (pet.getEndereço().toLowerCase().contains(endereco) && pet.getTipo() == tipo) {
                petsPorEndereco.add(pets.indexOf(pet));
            }
        }
        return petsPorEndereco;
    }


}
