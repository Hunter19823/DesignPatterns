package pie.ilikepiefoo.abstractFactory.factory;

import pie.ilikepiefoo.abstractFactory.AbstractCharacterFactory;
import pie.ilikepiefoo.abstractFactory.Character;
import pie.ilikepiefoo.abstractFactory.character.Elf;

public class ElfFactory extends AbstractCharacterFactory {

    @Override
    public Character createCharacter(final String name) {
        return new Elf(name);
    }
}
