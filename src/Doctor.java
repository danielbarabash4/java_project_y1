import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Doctor extends Lecturer implements Comparable<Doctor> {
    private ArrayList<String> Articles;
    private int ArticlesSize;

    public Doctor(String name, String id, Degree degree, String degreeName, double salary, Department department,int articlesSize,ArrayList<String> artArray) {
        super(name, id, degree, degreeName, salary, department);
        this.ArticlesSize=articlesSize;
        setArticles(artArray);
    }


    @Override
    public int compareTo(Doctor o) {
        return this.ArticlesSize-o.ArticlesSize;
    }

    public ArrayList<String> getArticles() {
        return Articles;
    }

    public void setArticles(ArrayList<String> articles) {
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
        return super.toString()+
                "amount of articles= " + ArticlesSize+"\n"+
                "Articles= " + Arrays.toString(Articles.toArray()) ;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Doctor doctor = (Doctor) o;
        return ArticlesSize == doctor.ArticlesSize && Objects.deepEquals(Articles, doctor.Articles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), Arrays.hashCode(Articles.toArray()), ArticlesSize);
    }
}

