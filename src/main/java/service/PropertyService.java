package service;

import fabric.ApartmentFabric;
import fabric.HouseFabric;
import fabric.PropertyFabric;
import model.Owner;
import model.Property;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PropertyService {

    /*
    * Criei uma "fabrica" de imoveis, para deixar o codigo mais escalavel, dai caso queria adicionar um novo imovel fica mais
    * facil
    * */
    public static final HashMap<Integer, PropertyFabric> propertyOptions = new HashMap<>();
    private static final List<Property> properties = new ArrayList<>();

    // Comeco com um bloco de inicializacao estatico com todas as opcoes que tem no programa para imoveis
    static {
        // id: que vai ser usado como o numero para o usuario escolher.Caso ele escolha 1, vai fazer uma casa, e assim por diante
        // propertyOptions: e a classe onde tem a funcao para criacao de um objeto.
        propertyOptions.put(1, new HouseFabric());
        propertyOptions.put(2, new ApartmentFabric());
    }

    public void addProperty(int op,String address, String propertyNumber, boolean isRent, Owner owner, int rentalValuePerMonth){

        // Chamo a fabrica de imoveis certa com base na opcao que o usuario vai mandar
        PropertyFabric propertyFabric = propertyOptions.get(op);

        // Crio um novo objeto propriedade com a fabrica
        Property property = propertyFabric.createNewProperty();

        // Atribuo ao novo objeto criado as caracteristicas que foram passadas pelo usuario
        property.setAddress(address);
        property.setPropertyNumber(propertyNumber);
        property.setRent(isRent);
        property.setOwner(owner);
        property.setRentalValuePerMonth(rentalValuePerMonth);

        // Adiciono para a lista de propriedades
        properties.add(property);

    }

    public void removeProperty(int i){

        properties.remove(i);

    }


    public static List<Property> getProperties() {
        return properties;
    }

    public static int getQuantiyOfOptions(){
        return propertyOptions.size();
    }

    public Property findPropertyById(int id){
        return properties.get(id);
    }

}
