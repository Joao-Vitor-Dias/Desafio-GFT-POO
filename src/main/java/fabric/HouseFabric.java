package fabric;

import model.House;
import model.Property;

// Implementa fabrica de imoveis, logo e obrigada a implementar o metodo da classe pai
public class HouseFabric implements PropertyFabric{


    // Metodo da classe pai sendo implementado aqui, e toda vez que for chama retornara um novo objetp Casa
    @Override
    public Property createNewProperty() {
        return new House();
    }
}
