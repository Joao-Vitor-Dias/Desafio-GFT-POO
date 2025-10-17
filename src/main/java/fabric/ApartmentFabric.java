package fabric;

import model.Apartment;
import model.Property;

public class ApartmentFabric implements PropertyFabric{

    @Override
    public Property createNewProperty() {
        return new Apartment();
    }
}
