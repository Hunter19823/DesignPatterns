package pie.ilikepiefoo.abstractFactory.factory;

import pie.ilikepiefoo.abstractFactory.AbstractCharacterFactory;
import pie.ilikepiefoo.abstractFactory.Character;
import pie.ilikepiefoo.abstractFactory.character.Human;

public class HumanFactory extends AbstractCharacterFactory {

    @Override
    public Character createCharacter(final String name) {
        return new Human(name);
    }
}
