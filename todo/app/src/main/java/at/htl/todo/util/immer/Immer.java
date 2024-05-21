package at.htl.todo.util.immer;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.function.Consumer;

import at.htl.todo.util.mapper.Mapper;

@Singleton
public class Immer<T> {
    public final Mapper<T> mapper;

    @Inject
    public Immer(Class<? extends T> type) {
        mapper = new Mapper<T>(type);
    }

    public T produce(final T readonlyState, Consumer<T> recipe) {
        var nextState = mapper.clone(readonlyState);
        recipe.accept(nextState);
        return nextState;
    }
}