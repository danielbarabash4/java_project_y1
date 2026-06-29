// Idan Amrani : 322205808
// Daniel Barbash : 322272683
// Daniel Liser : 325100196

import java.util.Comparator;
import java.util.Iterator;

public class SortComByArc implements Comparator<Committee> {
    @Override
    public int compare(Committee o1, Committee o2) {
        return sumArticles(o1) - sumArticles(o2);
    }

    private int sumArticles(Committee committee) {
        int sum = 0;
        Iterator it = committee.getCommitteeMembers().iterator();
        while (it.hasNext()) {
            Object member = it.next();
            if (member instanceof Doctor) {
                sum += ((Doctor) member).getArticlesSize();
            }
        }
        return sum;
    }
}