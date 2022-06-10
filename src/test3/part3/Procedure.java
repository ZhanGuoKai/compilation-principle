package test3.part3;

import java.util.Collection;
import java.util.HashSet;
import java.util.ListIterator;
import java.util.Set;

public abstract class Procedure {

    public static final char EPSILON = 'ε';

    private Set<Character> first;
    private Set<Character> follow;

    public Procedure() {
        first = null;
        follow = null;
    }

    public void setFirst(Set<Character> first) {
        this.first = first;
    }

    public Set<Character> getFirst() {
        return first;
    }

    public boolean addFollow(Collection<Character> follow) {
        if (this.follow == null) this.follow = new HashSet<>();
        boolean changed = this.follow.addAll(follow);
        this.follow.remove('ε');
        return changed;
    }

    public Set<Character> getFollow() {
        return follow;
    }

    public void setFollow(Set<Character> follow) {
        this.follow = follow;
    }

    public abstract String test(ListIterator<Character> input);

    public abstract String getSymbol();

}
