import java.util.Comparator;

public class SortComByNumOfLec implements Comparator<Committee> {
    @Override
    public int compare(Committee o1, Committee o2) {
        return o1.getLecSize()-o2.getLecSize();
    }
}
