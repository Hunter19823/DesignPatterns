
package pie.ilikepiefoo.abstractFactory;

public class Main {
    public static void main(final String[] args) throws NotImplementedException {
        // Create a Dwarf Factory
        System.out.println("Creating a Dwarf Factory");
        final AbstractCharacterFactory dwarfFactory = AbstractCharacterFactory.getCharacterFactory("Dwarf");

        // Create a Human Factory
        System.out.println("Creating a Human Factory");
        final AbstractCharacterFactory humanFactory = AbstractCharacterFactory.getCharacterFactory("Human");

        // Create an Elf Factory
        System.out.println("Creating an Elf Factory");
        final AbstractCharacterFactory elfFactory = AbstractCharacterFactory.getCharacterFactory("Elf");

        // Create an Orc Factory
        System.out.println("Creating an Orc Factory");
        final AbstractCharacterFactory orcFactory = AbstractCharacterFactory.getCharacterFactory("Orc");

        // Create a Dwarf named Bob
        System.out.println("Creating a Dwarf named Bob");
        final Character bob = dwarfFactory.createCharacter("Bob");
        bob.introduce();

        // Create a Human named Joe
        System.out.println("Creating a Human named Joe");
        final Character joe = humanFactory.createCharacter("Joe");
        joe.introduce();

        // Create an Elf named Steve
        System.out.println("Creating an Elf named Steve");
        final Character steve = elfFactory.createCharacter("Steve");
        steve.introduce();

        // Create an Orc named Jeff
        System.out.println("Creating an Orc named Jeff");
        final Character jeff = orcFactory.createCharacter("Jeff");
        jeff.introduce();
    }
}
