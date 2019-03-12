package ch.develop.milord.comparator;

import java.util.Comparator;
import java.util.Map;

import ch.develop.milord.Member;

public class ResultMapComparator implements Comparator<Member> {
    Map<Member, Double> base;

    public ResultMapComparator(Map<Member, Double> base) {
        this.base = base;
    }

    // Note: this comparator imposes orderings that are inconsistent with equals.
    public int compare(Member a, Member b) {
    	Double aVal = base.get(a);
    	Double bVal = base.get(b);
        if (base.get(a) >= base.get(b)) {
            return 1;
        } else {
            return -1;
        } // returning 0 would merge keys
    }

}
