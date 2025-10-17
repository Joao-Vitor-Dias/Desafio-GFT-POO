package fabric;

import model.House;
import model.Property;

public class HouseFabric implements PropertyFabric{

    @Override
    public Property createNewProperty() {
        return new House();
    }
}
