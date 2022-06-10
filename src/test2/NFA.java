package test2;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class NFA<S, T> extends FA<S, T, Set<NFA.NFAState<S, T>>, NFA.NFAState<S, T>> {

    public static class NFAState<S, T> extends State<S, T, Set<NFAState<S, T>>> {

        public NFAState(S state) {
            super(state);
        }

        public void addNextState(T alpha, NFAState<S, T> state) {
            Set<NFAState<S, T>> stateSet = getNextState(alpha);
            if (stateSet == null) {
                stateSet = new HashSet<>();
                setNextState(alpha, stateSet);
            }
            stateSet.add(state);
        }

    }

    public NFA(FATask<Set<NFAState<S, T>>> task) {
        this(false, task);
    }

    public NFA(boolean loop, FATask<Set<NFAState<S, T>>> task) {
        super(loop, task);
    }

    @Override
    public void addTransfer(S from, T alpha, S to) {
        NFAState<S, T> state, nextState;
        state = addState(from);
        nextState = addState(to);
        state.addNextState(alpha, nextState);
    }

    public void addTransfer(S from, T[] alphas, S to) {
        NFAState<S, T> state, nextState;
        state = addState(from);
        nextState = addState(to);
        for (T alpha : alphas) {
            state.addNextState(alpha, nextState);
        }
    }

    @Override
    protected NFAState<S, T> addState(S state) {
        NFAState<S, T> addedState = getState(state);
        if (addedState != null) return addedState;
        addedState =  new NFAState<>(state);
        getStates().add(addedState);
        return addedState;
    }

    @Override
    protected Set<NFAState<S, T>> init() {
        Set<NFAState<S, T>> initSet = new HashSet<>();
        initSet.add(getInitState());
        return initSet;
    }

    @Override
    protected Set<NFAState<S, T>> transfer(Set<NFAState<S, T>> currentState, T alpha) {
        Set<NFAState<S, T>> stateSet = new HashSet<>();
        currentState.forEach(state -> {
            Set<NFAState<S, T>> nextStates = state.getNextState(alpha);
            if (nextStates != null) nextStates.forEach(stateSet::add);
        });
        if (stateSet.size() == 0) return null;
        return stateSet;
    }

    @Override
    protected boolean isAccept(Set<NFAState<S, T>> finalState) {
        Iterator<NFAState<S, T>> it = finalState.iterator();
        while (it.hasNext()) {
            if (getFinalStates().contains(it.next())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        getStates().forEach(state -> {
            sb.append(state)
              .append("((")
              .append(state)
              .append("))")
              .append('\n');
            state.getAcceptableAlphas().forEach(alpha -> {
                Set<NFAState<S, T>> nextStates = state.getNextState(alpha);
                nextStates.forEach(nextState -> 
                    sb.append(state.getState())
                      .append(" --")
                      .append(alpha)
                      .append("--> ")
                      .append(nextState.getState())
                      .append('\n')
                );
            });
        });
        return sb.toString();
    }

}
