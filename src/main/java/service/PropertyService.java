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


    public static final HashMap<Integer, PropertyFabric> propertyOptions = new HashMap<>();
    private static final List<Property> properties = new ArrayList<>();

    static {
        propertyOptions.put(1, new HouseFabric());
        propertyOptions.put(2, new ApartmentFabric());
    }

    public void addProperty(int op,String address, String propertyNumber, boolean isRent, Owner owner, int rentalValuePerMonth){

        PropertyFabric propertyFabric = propertyOptions.get(op);

        Property property = propertyFabric.createNewProperty();

        property.setAddress(address);
        property.setPropertyNumber(propertyNumber);
        property.setRent(isRent);
        property.setOwner(owner);
        property.setRentalValuePerMonth(rentalValuePerMonth);

        properties.add(property);

    }

    public void removeProperty(int i){

        properties.remove(i);

    }


    public static List<Property> getProperties() {
        return properties;
    }

    public Property findPropertyById(int id){
        return properties.get(id);
    }

}
