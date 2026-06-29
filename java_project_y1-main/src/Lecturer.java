// Idan Amrani : 322205808
// Daniel Barbash : 322272683
// Daniel Liser : 325100196

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;

public class Lecturer implements Cloneable, Serializable {
    private String name;
    private String id;
    private Degree degree;
    private String degreeName;
    private double salary;
    private Department department;
    private HashSet<Committee> committees;

    public Lecturer(String name, String id, Degree degree, String degreeName, double salary, Department department) {
        setId(id);
        setName(name);
        setDegree(degree);
        setDegreeName(degreeName);
        setSalary(salary);
        setDepartment(department);
        committees = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public String getCommittees() {
        StringBuilder res = new StringBuilder();
        Iterator<Committee> it = committees.iterator();
        while (it.hasNext()) {
            Committee com = it.next();
            if (com != null) {
                res.append(com.getCommitteeName()).append(" ");
            }
        }
        if (res.length() == 0) {
            return null;
        }
        return res.toString();
    }

    public String getDep() {
        if (department != null) {
            return department.getDepartmentName();
        } else {
            return null;
        }
    }

    public void addCom(Committee newCom) {
        committees.add(newCom);
    }

    public boolean existCommittee(Committee checkCom) {
        Iterator<Committee> it = committees.iterator();
        while (it.hasNext()) {
            Committee com = it.next();
            if (com != null && com.equals(checkCom)) {
                return true;
            }
        }
        return false;
    }

    public void removeCom(Committee committee) {
        committees.remove(committee);
    }

    public boolean isQualifiedForHead() {
        return false;
    }

    public void setName(String name) { this.name = name; }
    public void setId(String id) { this.id = id; }
    public Degree getDegree() { return degree; }
    public void setDegree(Degree degree) { this.degree = degree; }
    public void setDegreeName(String degreeName) { this.degreeName = degreeName; }
    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }
    public Department getDepartment() { return department; }
    public void setDepartment(Department department) { this.department = department; }

    @Override
    public String toString() {
        return "name= " + name +
                ", id= " + id +
                ", degree= " + degree +
                ", degree name= " + degreeName +
                ", salary= " + salary + "\n" +
                "department= " + getDep() + "\n" +
                "committees= " + getCommittees() + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Lecturer)) return false;
        Lecturer lecturer = (Lecturer) o;
        return name.equals(lecturer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}