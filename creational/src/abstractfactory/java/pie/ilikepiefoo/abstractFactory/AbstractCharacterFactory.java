package pie.ilikepiefoo.abstractFactory;

import pie.ilikepiefoo.abstractFactory.factory.DwarfFactory;
import pie.ilikepiefoo.abstractFactory.factory.ElfFactory;
import pie.ilikepiefoo.abstractFactory.factory.HumanFactory;
import pie.ilikepiefoo.abstractFactory.factory.OrcFactory;

import java.util.Locale;

public abstract class AbstractCharacterFactory {

    public static AbstractCharacterFactory getCharacterFactory(final String race) throws NotImplementedException {
        switch (race.toLowerCase(Locale.ROOT)) {
            case "human":
                return new HumanFactory();
            case "elf":
                return new ElfFactory();
            case "orc":
                return new OrcFactory();
            case "dwarf":
                return new DwarfFactory();
            default:
                throw new NotImplementedException(String.format("%s is not implemented.", race));
        }
    }

    public abstract Character createCharacter(String name);
}
