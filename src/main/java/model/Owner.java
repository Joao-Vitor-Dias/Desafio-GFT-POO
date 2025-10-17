package model;

public class Owner {

    private String name;
    private String number;
    private String cpf;


    // Construtor com todos os argumentos
    public Owner(String name, String number, String cpf) {
        this.name = name;
        this.number = number;
        this.cpf = cpf;
    }


    // Getters e Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

}
