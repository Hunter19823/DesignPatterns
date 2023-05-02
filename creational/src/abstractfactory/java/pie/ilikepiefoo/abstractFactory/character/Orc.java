package pie.ilikepiefoo.abstractFactory.character;

import pie.ilikepiefoo.abstractFactory.Character;

public class Orc implements Character {
    private final String name;

    public Orc(final String name) {
        this.name = name;
    }


    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getRace() {
        return "Orc";
    }
}
