package fabric;

import model.Property;

// Interface que vai servir de referencia para outras de classes que seram fabricas de criacao de objetos
public interface PropertyFabric {

    Property createNewProperty();

}
