import java.util.Arrays;
import java.util.Objects;

public class Lecturer {
    private String name;
    private String id;
    private Degree degree;
    private String degreeName;
    private double salary;
    private Department department;
    private Committee[] committees;

    public Lecturer(String name, String id, Degree degree, String degreeName, double salary, Department department) {
        setId(id);
        setName(name);
        setDegree(degree);
        setDegreeName(degreeName);
        setSalary(salary);
        setDepartment(department);
        committees = new Committee[1];
    }

    public String getName() {
        return name;
    }

    public void setCommittees() {

    }

    public String getCommittees() {
        String res = "";
        for (int i = 0; i < committees.length; i++) {
            if (committees[i] != null) {
                res += committees[i].getCommitteeName() + " ";
            }
        }
        if(res.equals("")){
            return null;
        }
        return res;
    }

    public String getDep() {
        String res = "";
        if (department != null) {
            return department.getDepartmentName();
        } else {
            return null;
        }
    }

    public void addCommittee(Committee newCom) {
        int i = 0;
        for (; i < committees.length && committees[i] != null; ) {
            i++;
        }
        if (i >= committees.length) {
            extendCommittees();
        }

        committees[i] = newCom;
    }

    public void extendCommittees() {
        Committee[] newArr = new Committee[committees.length * 2];
        for (int i = 0; i < committees.length; i++) {
            newArr[i] = committees[i];
        }
        committees = newArr;
    }

    public boolean existCommittee(Committee checkCom) {
        for (int i = 0; i < committees.length; i++) {
            if (committees[i] != null && committees[i].equals(checkCom)) {
                return true;
            }
        }
        return false;
    }

    public void removeCom(Committee committee) {
        for (int i = 0; i < committees.length; i++) {
            if (committees[i] != null && committees[i].equals(committee)) {
                committees[i] = null;
            }
        }
        System.out.println("lecturer is not a member of the committee");
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Degree getDegree() {
        return degree;
    }

    public void setDegree(Degree degree) {
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
        return "|" +
                "name= " + name +
                ", id= " + id +
                ", degree= " + degree +
                ", degree name= " + degreeName +
                ", salary= " + salary +
                ", department= " + getDep() +
                ' ' + "committees= " + getCommittees() + "|";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        Lecturer lecturer = (Lecturer) o;
        return name.equals(lecturer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}


