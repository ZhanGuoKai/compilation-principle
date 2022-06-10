package test3.part1;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Production {

    private VNSymbol left;
    private Set<List<Symbol>> right;

    public Production(VNSymbol left) {
        this.left = left;
        right = new HashSet<>();
    }

    public void add(List<Symbol> list) {
        right.add(list);
    }

    public VNSymbol getLeft() {
        return left;
    }

    public Set<List<Symbol>> getRight() {
        return right;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        right.forEach(list -> {
            sb.append(left.getSymbol()).append(" --> ");
            list.forEach(symbol -> {
                sb.append(symbol.getSymbol());
            });
            sb.append("\n");
        });
        return sb.toString();
    }

}
