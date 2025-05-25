public class Doctor extends Lecturer implements Comparable<Doctor> {
    private String[] Articles;
    private int ArticlesSize;

    public Doctor(String name, String id, Degree degree, String degreeName, double salary, Department department,int articlesSize,String[] artArray) {
        super(name, id, degree, degreeName, salary, department);
        this.ArticlesSize=articlesSize;
        setArticles(artArray);
        Articles = new String[articlesSize];
    }


    @Override
    public int compareTo(Doctor o) {
        return this.ArticlesSize-o.ArticlesSize;
    }

    public String[] getArticles() {
        return Articles;
    }

    public void setArticles(String[] articles) {
        Articles = articles;
    }

    public int getArticlesSize() {
        return ArticlesSize;
    }

    public void setArticlesSize(int articlesSize) {
        ArticlesSize = articlesSize;
    }
}
