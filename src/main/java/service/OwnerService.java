package service;

import model.Owner;

import java.util.ArrayList;
import java.util.List;

public class OwnerService {

    public static List<Owner> owners = new ArrayList<>();

    public void addOwner(String name, String number, String cpf){

        owners.add(new Owner(name,number,cpf));

    }

    public Owner getOwnerByCpf(String cpf){

        for (Owner owner: owners){
            if (owner.getCpf().equalsIgnoreCase(cpf)){
                return owner;
            }
        }

        throw new RuntimeException();

    }

    public List<Owner> getAllOwners(){
        return owners;
    }

}
