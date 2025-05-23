package Entities;
import Enums.Sexo;
import Enums.Tipo;
import Exceptions.PetException;

import java.io.File;

public class Pet {
    private String nome;
    private Tipo tipo;
    private Sexo sexo;
    private String endereço;
    private double idade;
    private double peso;
    private String raça;
    private File arquivo;

    public Pet() {}

    public Pet(String nome, Tipo tipo, Sexo sexo, String endereço, double idade, double peso, String raça) throws PetException {
        if (!nome.contains(" ")) {
            throw new PetException("É necessário cadastrar nome e sobrenome!");
        }
        this.nome = nome;
        this.tipo = tipo;
        this.sexo = sexo;
        this.endereço = endereço;
        if (idade< 0|| idade > 20) {
            throw new PetException("Idade invalida");
        }
        this.idade = idade;
        if (peso < 0.5|| peso > 60) {
            throw new PetException("Peso invalido");
        }
        this.peso = peso;
        this.raça = raça;

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) throws PetException {
        if (!nome.contains(" ")) {
            throw new PetException("É necessário cadastrar nome e sobrenome!");
        }
        this.nome = nome;
    }

    public File getArquivo() {
        return arquivo;
    }

    public void setArquivo(File arquivo) {
        this.arquivo = arquivo;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public Sexo getSexo() {
        return sexo;
    }


    public String getEndereço() {
        return endereço;
    }

    public void setEndereço(String endereço) {
        this.endereço = endereço;
    }

    public double getIdade() {
        return idade;
    }

    public void setIdade(double idade) throws PetException {
        if (idade< 0|| idade > 20) {
            throw new PetException("Idade invalida");
        }
        this.idade = idade;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) throws PetException {
        if (peso < 0.5|| peso > 60) {
            throw new PetException("Peso invalido");
        }
        this.peso = peso;
    }

    public String getRaça() {
        return raça;
    }

    public void setRaça(String raça) {
        this.raça = raça;
    }

    @Override
    public String toString() {
        return String.format(
                "%s - %s - %s - %s - %s anos - %.0fkg - %s",
                nome,
                tipo,
                sexo,
                endereço,
                idade,
                peso,
                raça
        );
    }
}
