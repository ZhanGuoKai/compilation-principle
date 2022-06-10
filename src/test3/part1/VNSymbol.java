package test3.part1;

import java.util.ArrayList;
import java.util.List;

public class VNSymbol extends Symbol {

    private List<Symbol> children;

    public VNSymbol(String symbol) {
        super(symbol);
        children = new ArrayList<>();
    }

    public void addSymbol(Symbol child) {
        children.add(child);
    }

    public List<Symbol> getChildren() {
        return children;
    }

    @Override
    public String getLeafString() {
        if (children.size() == 0) return super.getLeafString();
        StringBuilder sb = new StringBuilder();
        children.forEach(child -> sb.append(child.getLeafString()));
        return sb.toString();
    }

    @Override
    public String toString() {
        if (children.size() == 0) return super.toString();
        StringBuilder sb = new StringBuilder().append(getSymbol()).append('(');
        children.forEach(sb::append);
        sb.append(')');
        return sb.toString();
    }

}
