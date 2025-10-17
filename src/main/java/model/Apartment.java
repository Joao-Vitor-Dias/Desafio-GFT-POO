package model;

public class Apartment extends Property{


    // Construtor sem os argumentos
    public Apartment(){

    }

    // Construtor com todos os argumentos, usando do construtor da classe pai
    public Apartment(String address, String propertyNumber, boolean isRent, Owner owner, int rentalValue) {
        super(address, propertyNumber, isRent, owner, rentalValue);
    }

    // Metodo abstrato da classe pai que esta sendo implementado aqui, e adicionando sua propria logica
    @Override
    public boolean isRented(){

        if (super.isRent){
            System.out.println("O apartamento de número " + super.propertyNumber + " está alugado");
        }else {
            System.out.println("O apartamento de número " + super.propertyNumber + " está disponível");
        }

        return super.isRent;

    }

}
