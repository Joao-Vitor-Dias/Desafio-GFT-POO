package model;

public class Apartment extends Property{

    public Apartment(){

    }

    public Apartment(String address, String propertyNumber, boolean isRent, Owner owner, int rentalValue) {
        super(address, propertyNumber, isRent, owner, rentalValue);
    }

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
