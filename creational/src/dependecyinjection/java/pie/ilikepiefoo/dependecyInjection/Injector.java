package pie.ilikepiefoo.dependecyInjection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.Arrays;

public class Injector {
    private final Dependencies dependencies;

    public Injector(final Dependencies dependencies) {
        this.dependencies = dependencies;
    }

    public <T> T instantiate(final Class<T> clazz) {
        final Constructor<?> constructor =
                Arrays.stream(clazz.getConstructors())
                        .filter(cons -> Modifier.isPublic(cons.getModifiers()))
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("No public constructor found for class: " + clazz.getName()));
        final Object[] parameters = this.prepareParameters(constructor);
        try {
            final Object object = constructor.newInstance(parameters);
            final T castedObject = clazz.cast(object);
            return castedObject;
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Object[] prepareParameters(final Executable executable) {
        final Class<?>[] parameterTypes = executable.getParameterTypes();
        final Parameter[] requiredParameters = executable.getParameters();
        final Object[] parameters = new Object[parameterTypes.length];
        for (int i = 0; i < parameterTypes.length; i++) {
            final Class<?> parameterType = parameterTypes[i];
            final Parameter requiredParameter = requiredParameters[i];
            final Dependency dependency = requiredParameter.getDeclaredAnnotation(Dependency.class);
            if (null == dependency) {
                throw new RuntimeException("No dependency annotation found for parameter: " + requiredParameter.getName() + " of type: " + parameterType.getName() + " for executable: " + executable.getName() + " of class: " + executable.getClass().getName() + ".");
            }
            final String name = dependency.name();
            parameters[i] = this.dependencies.getDependency(parameterType, name);
        }
        return parameters;
    }

    public <T> T executeMethod(final Class<?> enclosingClass, final Class<T> returnType, final String methodName, final Object object) {
        if (null == methodName) {
            throw new RuntimeException("Method name cannot be null.");
        }
        final Method method =
                Arrays.stream(enclosingClass.getMethods())
                        .filter(m -> Modifier.isPublic(m.getModifiers()))
                        .filter(m -> Modifier.isStatic(m.getModifiers()) == (null == object))
                        .filter(m -> methodName.equals(m.getName()))
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("No method with name: " + methodName + " found for class: " + enclosingClass.getName()));
        System.out.println("Found method: " + method.getName() + " for class: " + enclosingClass.getName());
        return this.executeMethod(returnType, method, object);
    }

    public <T> T executeMethod(final Class<T> returnType, final Method method, final Object object) {
        final Object[] parameters = this.prepareParameters(method);
        try {
            final Object result = method.invoke(object, parameters);
            final T castedResult = returnType.cast(result);
            return castedResult;
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }
}
