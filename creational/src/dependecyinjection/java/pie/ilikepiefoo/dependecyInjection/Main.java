
package pie.ilikepiefoo.dependecyInjection;

public class Main {

    @DependencyProvider(name = "x")
    public static final int xDependency = 10;
    @DependencyProvider(name = "y")
    public static final int yDependency = 100;
    @DependencyProvider(name = "name")
    public static String nameDependency = "Bob";

    public static void main(final String[] args) {
        System.out.println("Creating dependencies...");
        final Dependencies dependencies = new Dependencies();
        dependencies.scanDependencies(Main.class);

        System.out.println("Creating a new Dependency injector...");
        final Injector injector = new Injector(dependencies);

        System.out.println("Creating a new instance of Person using the dependency injector...");
        final Person person = injector.instantiate(Person.class);

        System.out.println("Calling the introduce method on the person...");
        person.introduce();

        System.out.println("Scanning the person object as a dependency...");
        dependencies.scanDependencies(person);

        System.out.println("Calling example method with the person as a dependency...");
        injector.executeMethod(Main.class, void.class, "exampleMethod", null);
    }

    public static void exampleMethod(
            @Dependency(name = "person") final Person person,
            @Dependency(name = "x") final int x,
            @Dependency(name = "y") final int y,
            @Dependency(name = "personID") final int id,
            @Dependency(name = "magicPersonID") final int magicID
    ) {
        System.out.println("Hello, my name is " + person.getName() + " and I am " + x + " years old and I have " + y + " dollars.");
        System.out.println("My ID is: " + id);
        System.out.println("My magic ID is: " + magicID);
    }
}
