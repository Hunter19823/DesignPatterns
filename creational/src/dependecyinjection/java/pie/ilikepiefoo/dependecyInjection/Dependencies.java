package pie.ilikepiefoo.dependecyInjection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Dependencies {
    private final Map<Class<?>, Map<String, Object>> dependencies;

    public Dependencies() {
        this(new HashMap<>());
    }

    public Dependencies(final Map<Class<?>, Map<String, Object>> dependencies) {
        this.dependencies = dependencies;
    }

    public Object getDependency(final Class<?> clazz, final String name) {
        if (!this.dependencies.containsKey(clazz)) {
            throw new RuntimeException("No dependencies for class: " + clazz.getName());
        }
        if (!this.dependencies.get(clazz).containsKey(name)) {
            if (!this.dependencies.get(clazz).containsKey("")) {
                throw new RuntimeException("No dependency with name: " + name + " for class: " + clazz.getName());
            }
            return this.dependencies.get(clazz).get("");
        }
        return this.dependencies.get(clazz).get(name);
    }

    public void scanDependencies(final Class<?> clazz) {
        System.out.println("Scanning static dependencies for class: " + clazz.getName());
        Arrays.stream(clazz.getFields())
                .filter(field -> Modifier.isStatic(field.getModifiers()))
                .filter(field -> Modifier.isPublic(field.getModifiers()))
                .filter(field -> field.isAnnotationPresent(DependencyProvider.class))
                .forEach(field -> this.addFieldDependency(field, null)
                );

        Arrays.stream(clazz.getMethods())
                .filter(method -> Modifier.isPublic(method.getModifiers()))
                .filter(method -> Modifier.isStatic(method.getModifiers()))
                .filter(method -> 0 == method.getParameterCount())
                .filter(method -> method.isAnnotationPresent(DependencyProvider.class))
                .forEach(method -> this.addGetterMethodDependency(method, null));
    }

    private void addFieldDependency(final Field field, final Object object) {
        final Class<?> type = field.getType();
        final DependencyProvider dependency = field.getAnnotation(DependencyProvider.class);
        final String name = dependency.name();
        try {
            final Object value = field.get(object);
            this.addDependency(type, name, value);
        } catch (final IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private void addGetterMethodDependency(final Method method, final Object object) {
        final Class<?> type = method.getReturnType();
        final DependencyProvider dependency = method.getAnnotation(DependencyProvider.class);
        final String name = dependency.name();
        try {
            final Object value = method.invoke(object);
            this.addDependency(type, name, value);
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void addDependency(final Class<?> clazz, final String name, final Object dependency) {
        if (!this.dependencies.containsKey(clazz)) {
            this.dependencies.put(clazz, new HashMap<>());
        }
        this.dependencies.get(clazz).put(name, dependency);
        System.out.printf("Successfully added dependency named '%s' of type '%s', and assigning it the value '%s'...%n", name, clazz.getName(), dependency.toString());
    }

    public void scanDependencies(final Object instance) {
        if (null == instance) {
            throw new RuntimeException("Cannot scan dependencies for null instance.");
        }
        if (instance.getClass().isAnnotationPresent(DependencyProvider.class)) {
            System.out.println("Instance: " + instance + " of class: " + instance.getClass().getName() + " is annotated with @DependencyProvider. Adding it as a dependency.");
            final DependencyProvider dependency = instance.getClass().getAnnotation(DependencyProvider.class);
            this.addDependency(instance.getClass(), dependency.name(), instance);
        }

        System.out.println("Scanning non-static dependencies for instance: " + instance + " of class: " + instance.getClass().getName());
        Arrays.stream(instance.getClass().getFields())
                .filter(field -> Modifier.isPublic(field.getModifiers()))
                .filter(field -> !Modifier.isStatic(field.getModifiers()))
                .filter(field -> field.isAnnotationPresent(DependencyProvider.class))
                .forEach(field -> this.addFieldDependency(field, instance)
                );

        Arrays.stream(instance.getClass().getMethods())
                .filter(method -> Modifier.isPublic(method.getModifiers()))
                .filter(method -> 0 == method.getParameterCount())
                .filter(method -> method.isAnnotationPresent(DependencyProvider.class))
                .forEach(method -> this.addGetterMethodDependency(method, instance));
    }


}
