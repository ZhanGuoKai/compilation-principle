package test2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public abstract class FA<S, K, V, T extends State<S, K, V>> {

    private boolean loop;
    private T initState;
    private FATask<V> task;
    private Set<T> states;
    private Set<T> finalStates;

    @FunctionalInterface
    public static interface FATask<T> {

        public void execute(T state, List<T> stateList);

    }

    public FA(FATask<V> task) {
        this(false, task);
    }

    public FA(boolean loop, FATask<V> task) {
        this.loop = loop;
        if (task == null) this.task = (s, l) -> {};
        else this.task = task;
        initState = null;
        states = new HashSet<>();
        finalStates = new HashSet<>();
    }

    public boolean hasState(S state) {
        Iterator<T> it = states.iterator();
        while (it.hasNext()) {
            if (it.next().getState().equals(state)) {
                return true;
            }
        }
        return false;
    }

    public void setInitState(S state) {
        T init = addState(state);
        initState = init;
    }

    public void setInitState(T state) {
        initState = state;
    }

    public void addFinalState(S state) {
        T finalState = addState(state);
        finalStates.add(finalState);
    }

    public void addFinalState(T state) {
        finalStates.add(state);
    }

    public T getState(S state) {
        Iterator<T> it = states.iterator();
        T next;
        while (it.hasNext()) {
            next = it.next();
            if (next.getState().equals(state)) {
                return next;
            }
        }
        return null;
    }

    public boolean test(List<K> input) {
        boolean accept = true;
        V currentState = init();
        Iterator<K> it = input.iterator();
        K next;
        List<V> stateList = new ArrayList<>();
        while (it.hasNext()) {
            next = it.next();
            currentState = transfer(currentState, next);
            if (currentState != null) {
                stateList.add(currentState);
                continue;
            }
            accept = false;
            if (!loop) break;
            task.execute(currentState, stateList);
            stateList.clear();
            currentState = init();
            currentState = transfer(currentState, next);
            if (currentState != null) {
                stateList.add(currentState);
                continue;
            }
            currentState = init();
        }
        task.execute(currentState, stateList);
        return accept && isAccept(currentState);
    }

    public V getLastState(List<K> input) {
        V currentState = init();
        Iterator<K> it = input.iterator();
        K next;
        List<V> stateList = new ArrayList<>();
        while (it.hasNext()) {
            next = it.next();
            currentState = transfer(currentState, next);
            if (currentState != null) {
                stateList.add(currentState);
                continue;
            }
            if (!loop) break;
            task.execute(currentState, stateList);
            stateList.clear();
            currentState = init();
            currentState = transfer(currentState, next);
            if (currentState != null) {
                stateList.add(currentState);
                continue;
            }
            currentState = init();
        }
        task.execute(currentState, stateList);
        return currentState;
    }

    public T getInitState() {
        return initState;
    }

    public Set<T> getStates() {
        return states;
    }

    public Set<T> getFinalStates() {
        return finalStates;
    }

    protected abstract void addTransfer(S from, K alpha, S to);

    protected abstract T addState(S state);

    protected abstract V init();

    protected abstract V transfer(V currentState, K alpha);

    protected abstract boolean isAccept(V finalState);

}
