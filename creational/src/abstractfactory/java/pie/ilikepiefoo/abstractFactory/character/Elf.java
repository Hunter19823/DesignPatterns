package pie.ilikepiefoo.abstractFactory.character;

import pie.ilikepiefoo.abstractFactory.Character;

public class Elf implements Character {
    private final String name;

    public Elf(final String name) {
        this.name = name;
    }


    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getRace() {
        return "Elf";
    }
}
