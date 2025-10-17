package model;

public class House extends Property{

    // Construtor sem os argumentos
    public House() {

    }

    // Construtor com todos os argumentos, usando do construtor da classe pai
    public House(String address, String propertyNumber, boolean isRent, Owner owner, int rentalValue) {
        super(address, propertyNumber, isRent, owner, rentalValue);
    }

    // Metodo abstrato da classe pai que esta sendo implementado aqui, e adicionando sua propria logica
    @Override
    public boolean isRented(){

        if (super.isRent){
            System.out.println("A casa está alugada");
        }else {
            System.out.println("A casa está disponível");
        }

        return super.isRent;

    }


}
