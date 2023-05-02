package pie.ilikepiefoo.abstractFactory;

public interface Character {
    default void introduce() {
        System.out.printf("Hello, my name is %s. I am a %s%n", this.getName(), this.getRace());
    }

    String getName();

    String getRace();
}
