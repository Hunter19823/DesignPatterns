package pie.ilikepiefoo.dependecyInjection;

public class UnknownDependencyException extends Exception {
    public UnknownDependencyException(final String message) {
        super(message);
    }
}
