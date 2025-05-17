public class Doctor extends Lecturer {
    private String[] Articles;
    private int ArticlesSize;

    public Doctor(String name, String id, Degree degree, String degreeName, double salary, Department department) {
        super(name, id, degree, degreeName, salary, department);
        Articles = new String[1];
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
