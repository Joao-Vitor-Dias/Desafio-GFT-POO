package model;

public class House extends Property{

    public House() {

    }

    public House(String address, String propertyNumber, boolean isRent, Owner owner, int rentalValue) {
        super(address, propertyNumber, isRent, owner, rentalValue);
    }

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
