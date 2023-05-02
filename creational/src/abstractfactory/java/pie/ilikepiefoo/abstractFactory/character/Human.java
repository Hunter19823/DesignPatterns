package pie.ilikepiefoo.abstractFactory.character;

import pie.ilikepiefoo.abstractFactory.Character;

public class Human implements Character {
    private final String name;

    public Human(final String name) {
        this.name = name;
    }


    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getRace() {
        return "Human";
    }
}
