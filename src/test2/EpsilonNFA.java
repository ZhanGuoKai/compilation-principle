package test2;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;


public class EpsilonNFA<S, T> extends FA<S, T, Set<EpsilonNFA.EpsilonNFAState<S, T>>, EpsilonNFA.EpsilonNFAState<S, T>> {

    public static class EpsilonNFAState<S, T> extends State<S, T, Set<EpsilonNFAState<S, T>>> {

        private Set<EpsilonNFAState<S, T>> epsilonTransferStates;

        public EpsilonNFAState(S state) {
            super(state);
            epsilonTransferStates = new HashSet<>();
        }

        public void addNextState(T alpha, EpsilonNFAState<S, T> state) {
            Set<EpsilonNFAState<S, T>> stateSet = getNextState(alpha);
            if (stateSet == null) {
                stateSet = new HashSet<>();
                setNextState(alpha, stateSet);
            }
            stateSet.add(state);
        }

        public void addEpsilonTransfer(EpsilonNFAState<S, T> state) {
            epsilonTransferStates.add(state);
        }

        public Set<EpsilonNFAState<S, T>> getAllEpsilonTransferStates() {
            Queue<EpsilonNFAState<S, T>> stateQueue = new LinkedList<>();
            Set<EpsilonNFAState<S, T>> added = new HashSet<>();
            Set<EpsilonNFAState<S, T>> temp = new HashSet<>();
            stateQueue.addAll(epsilonTransferStates);
            added.addAll(epsilonTransferStates);
            temp.addAll(epsilonTransferStates);
            EpsilonNFAState<S, T> state;
            while (!stateQueue.isEmpty()) {
                state = stateQueue.poll();
                state.epsilonTransferStates.forEach(epsilonTransferState -> {
                    if (added.contains(epsilonTransferState)) return;
                    stateQueue.add(epsilonTransferState);
                    added.add(epsilonTransferState);
                });
                temp.addAll(state.epsilonTransferStates);
            }
            return temp;
        }

        public Set<EpsilonNFAState<S, T>> getEpsilonTransferStates() {
            return epsilonTransferStates;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            Queue<EpsilonNFAState<S, T>> stateQueue = new LinkedList<>();
            Set<EpsilonNFAState<S, T>> shown = new HashSet<>();
            stateQueue.add(this);
            shown.add(this);
            while (!stateQueue.isEmpty()) {
                EpsilonNFAState<S, T> state = stateQueue.poll();
                sb.append(state.getState())
                  .append("((")
                  .append(state.getState())
                  .append("))")
                  .append('\n');
                state.getAcceptableAlphas().forEach(alpha -> {
                    Set<EpsilonNFAState<S, T>> nextStates = state.getNextState(alpha);
                    if (nextStates == null) return;
                    nextStates.forEach(nextState -> {
                        if (!shown.contains(nextState)) {
                            stateQueue.add(nextState);
                            shown.add(nextState);
                        }
                        sb.append(state.getState())
                          .append(" --")
                          .append(alpha)
                          .append("--> ")
                          .append(nextState.getState())
                          .append('\n');
                    });
                });
                state.epsilonTransferStates.forEach(epsilonTransferState -> {
                    if (!shown.contains(epsilonTransferState)) {
                        stateQueue.add(epsilonTransferState);
                        shown.add(epsilonTransferState);
                    }
                    sb.append(state.getState())
                      .append(" --ε--> ")
                      .append(epsilonTransferState.getState())
                      .append('\n');
                });
            }
            return sb.toString();
        }
    
    }

    public EpsilonNFA(FATask<Set<EpsilonNFAState<S, T>>> task) {
        this(false, task);
    }

    public EpsilonNFA(boolean loop, FATask<Set<EpsilonNFAState<S, T>>> task) {
        super(loop, task);
    }

    public Set<EpsilonNFAState<S, T>> getEpsilonTransferStates(Set<EpsilonNFAState<S, T>> states) {
        if (states == null || states.size() == 0) return null;
        Set<EpsilonNFAState<S, T>> result = new HashSet<>();
        states.forEach(state -> result.addAll(state.getAllEpsilonTransferStates()));
        return result;
    }

    @Override
    public void addTransfer(S from, T alpha, S to) {
        EpsilonNFAState<S, T> state, nextState;
        state = addState(from);
        nextState = addState(to);
        state.addNextState(alpha, nextState);
    }

    public void addTransfer(S from, T[] alphas, S to) {
        EpsilonNFAState<S, T> state, nextState;
        state = addState(from);
        nextState = addState(to);
        for (T alpha : alphas) {
            state.addNextState(alpha, nextState);
        }
    }

    public void addEpsilonTransfer(S from, S to) {
        EpsilonNFAState<S, T> state, nextState;
        state = addState(from);
        nextState = addState(to);
        state.addEpsilonTransfer(nextState);
    }

    @Override
    public EpsilonNFAState<S, T> addState(S state) {
        EpsilonNFAState<S, T> addedState = getState(state);
        if (addedState != null) return addedState;
        addedState =  new EpsilonNFAState<>(state);
        getStates().add(addedState);
        return addedState;
    }

    @Override
    public Set<EpsilonNFAState<S, T>> init() {
        Set<EpsilonNFAState<S, T>> initSet = new HashSet<>();
        initSet.add(getInitState());
        return initSet;
    }

    @Override
    public Set<EpsilonNFAState<S, T>> transfer(Set<EpsilonNFAState<S, T>> currentStates, T alpha) {
        if (currentStates == null) return null;
        Set<EpsilonNFAState<S, T>> temp = new HashSet<>();
        currentStates.addAll(getEpsilonTransferStates(currentStates));
        currentStates.forEach(state -> {
            Set<EpsilonNFAState<S, T>> nextStates = state.getNextState(alpha);
            if (nextStates != null) nextStates.forEach(temp::add);
        });
        if (temp.size() == 0) return null;
        // temp.addAll(getEpsilonTransferStates(temp));
        return temp;
    }

    @Override
    public boolean isAccept(Set<EpsilonNFAState<S, T>> finalState) {
        finalState.addAll(getEpsilonTransferStates(finalState));
        Iterator<EpsilonNFAState<S, T>> it = finalState.iterator();
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
            sb.append(state.toString()).append('\n');
            state.getAcceptableAlphas().forEach(alpha -> {
                Set<EpsilonNFAState<S, T>> nextStates = state.getNextState(alpha);
                nextStates.forEach(nextState -> 
                    sb.append(state.getState())
                      .append(" --")
                      .append(alpha)
                      .append("--> ")
                      .append(nextState.getState())
                      .append('\n')
                );
            });
            state.getAllEpsilonTransferStates().forEach(epsilonTransferState -> {
                sb.append(state.getState())
                  .append(" --ε--> ")
                  .append(epsilonTransferState.getState())
                  .append('\n');
            });
        });
        return sb.toString();
    }

}
