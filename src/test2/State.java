package test2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class State<T, K, V> {

    private T state;
    private boolean end;
    private Set<K> acceptableAlphas;
    private Map<K, V> transfer;
    private V defaultTransferState;

    public State(T state) {
        this(state, null);
    }

    public State(T state, boolean end) {
        this(state, null, end);
    }

    public State(T state, V defaultTransferState) {
        this(state, defaultTransferState, false);
    }

    public State(T state, V defaultTransferState, boolean end) {
        this.state = state;
        this.end = end;
        acceptableAlphas = new HashSet<>();
        transfer = new HashMap<>();
        this.defaultTransferState = defaultTransferState;
    }

    public State<T, K, V> setNextState(K alpha, V nextState) {
        transfer.put(alpha, nextState);
        acceptableAlphas.add(alpha);
        return this;
    }

    public V getNextState(K alpha) {
        V nextState = transfer.get(alpha);
        if (nextState == null) return defaultTransferState;
        return nextState;
    }

    public void setDefaultNextState(V state) {
        defaultTransferState = state;
    }

    public T getState() {
        return state;
    }

    public Set<K> getAcceptableAlphas() {
        return acceptableAlphas;
    }

    public boolean isFinalState() {
        return end;
    }

    @Override
    public String toString() {
        return state.toString();
    }

}
