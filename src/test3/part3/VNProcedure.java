package test3.part3;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

public class VNProcedure extends Procedure {

    private String symbol;
    private Set<List<Procedure>> matchSet;
    private Set<Procedure> previousSet;
    private Map<Character, List<Procedure>> analysisTable;

    public VNProcedure(String symbol) {
        this.symbol = symbol;
        matchSet = new HashSet<>();
        previousSet = new HashSet<>();
        analysisTable = new HashMap<>();
    }

    public void add(List<Procedure> matchList) {
        matchSet.add(matchList);
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    public static Set<Character> getFirst(List<Procedure> list) {
        return list.get(0).getFirst();
    }

    public void addPrevious(Procedure previous) {
        previousSet.add(previous);
    }

    public void setEpsilon() {
        List<Procedure> list;
        if ((list = analysisTable.get(EPSILON)) == null) return;
        getFollow().forEach(ch -> analysisTable.put(ch, list));
    }

    public Map<Character, List<Procedure>> getAnalysisTable() {
        return analysisTable;
    }

    @Override
    public Set<Character> getFirst() {
        Set<Character> first = super.getFirst();
        if (first != null) return first;
        Set<Character> set = new HashSet<>();
        matchSet.forEach(list -> {
            Set<Character> listFirst = getFirst(list);
            listFirst.forEach(ch -> analysisTable.put(ch, list));
            set.addAll(listFirst);
        });
        setFirst(set);
        return set;
    }

    @Override
    public boolean addFollow(Collection<Character> follow) {
        boolean changed = super.addFollow(follow);
        if (changed) {
            previousSet.forEach(previous -> previous.addFollow(follow));
        }
        return changed;
    }

    @Override
    public void setFollow(Set<Character> follow) {
        super.setFollow(follow);
        matchSet.forEach(list -> {
            ListIterator<Procedure> it = list.listIterator(list.size());
            Procedure previous = it.previous();
            Procedure current;
            Set<Character> currentFirst;
            Set<Character> previousFollow;
            previousFollow = previous.getFollow();
            if (previousFollow == null) {
                previousFollow = new HashSet<>();
                previousFollow.addAll(getFollow());
                addPrevious(previous);
                previous.setFollow(previousFollow);
            } else previous.addFollow(getFollow());
            while (it.hasPrevious()) {
                current = previous;
                previous = it.previous();
                currentFirst = current.getFirst();
                previousFollow = previous.getFollow();
                if (previousFollow == null) {
                    previousFollow = new HashSet<>();
                    previousFollow.addAll(currentFirst);
                    previous.setFollow(previousFollow);
                } else previous.addFollow(currentFirst);
                if (currentFirst.contains(EPSILON)) {
                    previous.addFollow(current.getFollow());
                    if (current instanceof VNProcedure) {
                        ((VNProcedure) current).addPrevious(previous);
                    }
                }
            }
        });
    }

    @Override
    public String test(ListIterator<Character> input) {
        char ch = input.next();
        input.previous();
        List<Procedure> list = analysisTable.get(ch);
        if (list == null) return "";
        Iterator<Procedure> it = list.iterator();
        StringBuilder sb = new StringBuilder(symbol).append('(');
        String s;
        while (it.hasNext()) {
            s = it.next().test(input);
            if (s.equals("")) return "";
            sb.append(s);
        }
        sb.append(')');
        s = sb.toString();
        if (s.equals(symbol + "(ε)")) return symbol;
        return s;
    }

}
