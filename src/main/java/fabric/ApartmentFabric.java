package fabric;

import model.Apartment;
import model.Property;

// Implementa fabrica de imoveis, logo e obrigada a implementar o metodo da classe pai
public class ApartmentFabric implements PropertyFabric{

    // Metodo da classe pai sendo implementado aqui, e toda vez que for chama retornara um novo objetp Apartamento
    @Override
    public Property createNewProperty() {
        return new Apartment();
    }
}
