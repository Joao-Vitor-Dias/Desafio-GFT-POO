package model;

public abstract class Property {

    protected String address;
    protected String propertyNumber;
    protected boolean isRent;
    public Owner owner;
    private int rentalValuePerMonth;

    public Property() {
    }

    public Property(String address, String propertyNumber, boolean isRent, Owner owner, int rentalValuePerMonth) {
        this.address = address;
        this.propertyNumber = propertyNumber;
        this.isRent = isRent;
        this.owner = owner;
        this.rentalValuePerMonth = rentalValuePerMonth;
    }

    public abstract boolean isRented();

    public String getOwnerContact(){

        return owner.getNumber();

    }

    public int calculateRental(int periodOfTimeInMonth){

        return periodOfTimeInMonth * this.rentalValuePerMonth;

    }

    public double calculateDiscount(int periodOfTimeInMonth){

        if (periodOfTimeInMonth >= 36){
            return 0.10d;
        } else if (periodOfTimeInMonth >= 24) {
            return 0.08d;
        } else if (periodOfTimeInMonth >= 12) {
            return 0.05d;
        } else {
            return 0;
        }

    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public boolean isRent() {
        return isRent;
    }

    public void setRent(boolean rent) {
        isRent = rent;
    }

    public String getPropertyNumber() {
        return propertyNumber;
    }

    public void setPropertyNumber(String propertyNumber) {
        this.propertyNumber = propertyNumber;
    }

    public double getRentalValuePerMonth() {
        return rentalValuePerMonth;
    }

    public void setRentalValuePerMonth(int rentalValuePerMonth) {
        this.rentalValuePerMonth = rentalValuePerMonth;
    }
}
