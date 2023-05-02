package pie.ilikepiefoo.abstractFactory.factory;

import pie.ilikepiefoo.abstractFactory.AbstractCharacterFactory;
import pie.ilikepiefoo.abstractFactory.Character;
import pie.ilikepiefoo.abstractFactory.character.Orc;

public class OrcFactory extends AbstractCharacterFactory {

    @Override
    public Character createCharacter(final String name) {
        return new Orc(name);
    }
}
