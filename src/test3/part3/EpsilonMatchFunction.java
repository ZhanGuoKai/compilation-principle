package test3.part3;

import java.util.HashSet;
import java.util.ListIterator;
import java.util.Set;

public class EpsilonMatchFunction extends Procedure {

    private static final EpsilonMatchFunction INSTANCE = new EpsilonMatchFunction();

    private EpsilonMatchFunction() {
        super();
        Set<Character> first = new HashSet<>();
        first.add('ε');
        setFirst(first);
    }

    public static EpsilonMatchFunction getInstance() {
        return INSTANCE;
    }

    @Override
    public String test(ListIterator<Character> input) {
        return "ε";
    }

    @Override
    public String getSymbol() {
        return "ε";
    }

}
