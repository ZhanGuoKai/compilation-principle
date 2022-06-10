package test3.part3;

import java.util.HashSet;
import java.util.ListIterator;
import java.util.Set;

public class VTProcedure extends Procedure {

    private char symbol;

    public VTProcedure(char symbol) {
        this.symbol = symbol;
    }

    @Override
    public Set<Character> getFirst() {
        Set<Character> first = super.getFirst();
        if (first != null) return first;
        first = new HashSet<>();
        first.add(symbol);
        setFirst(first);
        return first;
    }

    @Override
    public String test(ListIterator<Character> input) {
        char ch = input.next();
        if (ch == symbol) return symbol + "";
        input.previous();
        return "";
    }

    @Override
    public String getSymbol() {
        return symbol + "";
    }

}
