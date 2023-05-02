package pie.ilikepiefoo.abstractFactory.character;

import pie.ilikepiefoo.abstractFactory.Character;

public class Dwarf implements Character {
    private final String name;

    public Dwarf(final String name) {
        this.name = name;
    }


    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getRace() {
        return "Dwarf";
    }
}
