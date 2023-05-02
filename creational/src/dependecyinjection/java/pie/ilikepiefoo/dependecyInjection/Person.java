package pie.ilikepiefoo.dependecyInjection;

import java.util.Objects;

@DependencyProvider(name = "person")
public class Person {
    private final String name;

    public Person(
            @Dependency(name = "name") final String name
    ) {
        this.name = name;
    }

    @DependencyProvider(name = "magicPersonID")
    public static int getPersonID() {
        return 123456;
    }

    public String getName() {
        return this.name;
    }

    @DependencyProvider(name = "personID")
    public int getID() {
        return Objects.hash(this.name);
    }

    public void introduce() {
        System.out.println("Hello, my name is " + this.name);
    }
}
