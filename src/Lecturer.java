import java.util.Objects;

public class Lecturer {
        private String name;
        private int id;
        private String degree;
        private String degreeName;
        private double salary;
        private Department department;
        private Committee[]commitees;
        public Lecturer(String name,int id,String degree,String degreeName,double salary,Department department){
            setId(id);
            setName(name);
            setDegree(degree);
            setDegreeName(degreeName);
            setSalary(salary);
            setDepartment(department);
            this.commitees=new Committee[1];
        }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getDegreeName() {
        return degreeName;
    }

    public void setDegreeName(String degreeName) {
        this.degreeName = degreeName;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "lecturer{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", degree='" + degree + '\'' +
                ", degreeName='" + degreeName + '\'' +
                ", salary=" + salary +
                ", department=" + department +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Lecturer lecturer = (Lecturer) o;
        return id == lecturer.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}


