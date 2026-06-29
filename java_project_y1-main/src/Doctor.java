// Idan Amrani : 322205808
// Daniel Barbash : 322272683
// Daniel Liser : 325100196

import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;

public class Doctor extends Lecturer implements Comparable<Doctor> {
    private HashSet<String> Articles;
    private int ArticlesSize;

    public Doctor(String name, String id, Degree degree, String degreeName, double salary, Department department, int articlesSize, HashSet<String> artSet) {
        super(name, id, degree, degreeName, salary, department);
        this.ArticlesSize = articlesSize;
        this.Articles = artSet;
    }

    @Override
    public boolean isQualifiedForHead() {
        return true;
    }

    @Override
    public int compareTo(Doctor o) {
        return this.ArticlesSize - o.ArticlesSize;
    }

    public HashSet<String> getArticles() {
        return Articles;
    }

    public void setArticles(HashSet<String> articles) {
        Articles = articles;
    }

    public int getArticlesSize() {
        return ArticlesSize;
    }

    public void setArticlesSize(int articlesSize) {
        ArticlesSize = articlesSize;
    }

    @Override
    public String toString() {
        StringBuilder articlesStr = new StringBuilder();
        Iterator<String> it = Articles.iterator();
        while (it.hasNext()) {
            articlesStr.append(it.next()).append(" ");
        }
        return super.toString() +
                "amount of articles= " + ArticlesSize + "\n" +
                "Articles= " + articlesStr;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Doctor doctor = (Doctor) o;
        return ArticlesSize == doctor.ArticlesSize && Objects.equals(Articles, doctor.Articles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), Articles, ArticlesSize);
    }
}