package test2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DFA<S, T> extends FA<S, T, DFA.DFAState<S, T>, DFA.DFAState<S, T>> {

    public static class DFAState<S, T> extends State<S, T, DFAState<S, T>> {

        public DFAState(S state) {
            super(state);
        }

    }

    public DFA(FA.FATask<DFAState<S, T>> task) {
        this(false, task);
    }

    public DFA(Boolean loop, FATask<DFAState<S, T>> task) {
        super(loop, task);
    }

    public static DFA<String, String> loadFromFile(String fileName,
            FATask<DFAState<String, String>> task) throws IOException {
        return loadFromFile(fileName, false, task);
    }

    public static DFA<String, String> loadFromFile(String fileName, boolean loop,
            FATask<DFAState<String, String>> task) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        DFA<String, String> dfa = new DFA<>(loop, task);
        String line;
        Pattern transferPattern = Pattern.compile("f\\((\\S+),\\s*(\\S+)\\)=(\\S+)");
        Matcher transferMatcher;
        dfa.setInitState(reader.readLine());
        for (String finalState : reader.readLine().split("\\s")) {
            dfa.addFinalState(finalState);
        }
        while ((line = reader.readLine()) != null) {
            transferMatcher = transferPattern.matcher(line);
            if (!transferMatcher.find()) continue;
            dfa.addTransfer(transferMatcher.group(1), transferMatcher.group(2),
                    transferMatcher.group(3));
        }
        reader.close();
        return dfa;
    }

    @Override
    public void addTransfer(S from, T alpha, S to) {
        DFAState<S, T> state, nextState;
        state = addState(from);
        nextState = addState(to);
        state.setNextState(alpha, nextState);
    }

    public void addTransfer(S from, T[] alphas, S to) {
        DFAState<S, T> state, nextState;
        state = addState(from);
        nextState = addState(to);
        for (T alpha : alphas) {
            state.setNextState(alpha, nextState);
        }
    }

    @Override
    protected DFAState<S, T> addState(S state) {
        DFAState<S, T> addedState = getState(state);
        if (addedState != null) return addedState;
        addedState = new DFAState<>(state);
        getStates().add(addedState);
        return addedState;
    }

    @Override
    protected DFAState<S, T> init() {
        return getInitState();
    }

    @Override
    protected DFAState<S, T> transfer(DFAState<S, T> currentState, T alpha) {
        return currentState.getNextState(alpha);
    }

    @Override
    protected boolean isAccept(DFAState<S, T> finalState) {
        return getFinalStates().contains(finalState);
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
                DFAState<S, T> nextState = state.getNextState(alpha);
                sb.append(state.getState())
                  .append(" --\"")
                  .append(alpha.toString())
                  .append("\"--> ")
                  .append(nextState.getState())
                  .append('\n');
            });
        });
        return sb.toString();
    }

}
