// Idan Amrani : 322205808
// Daniel Barbash : 322272683
// Daniel Liser : 325100196

import java.util.Comparator;
import java.util.Iterator;

public class SortComByArc implements Comparator<Committee> {
    @Override
    public int compare(Committee o1, Committee o2) {
        int sum1 = runOnArray(o1);
        int sum2 = runOnArray(o2);
        return sum1 - sum2;
    }

    public int runOnArray(Committee o) {
        int sum = 0;
        Iterator it = o.getCommitteeMembers().iterator();
        while (it.hasNext()) {
            Object member = it.next();
            if (member != null && member instanceof Doctor) {
                Doctor doc = (Doctor) member;
                sum += doc.getArticlesSize();
            }
        }
        return sum;
    }
}