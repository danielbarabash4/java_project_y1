import java.util.Comparator;

public class SortComByArc implements Comparator<Committee> {
    @Override
    public int compare(Committee o1, Committee o2) {
        int sum1 = runOnArray(o1);
        int sum2 = runOnArray(o2);
        return sum1-sum2;

    }

    public int runOnArray(Committee o) {
        int sum = 0;
        Doctor doc;
        for (int i = 0; i < o.getLecSize(); i++) {
            if (o.getCommitteeMembers().get(i) != null && o.getCommitteeMembers().get(i) instanceof Doctor) {
                doc=(Doctor)o.getCommitteeMembers().get(i);
                sum+=doc.getArticlesSize();
            }
        }
        return sum;
    }
}
