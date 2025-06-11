import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Lecturer implements Cloneable {
    private String name;
    private String id;
    private Degree degree;
    private String degreeName;
    private double salary;
    private Department department;
    private ArrayList<Committee> committees;
    private int comSize = 0; //logical variables


    public Lecturer(String name, String id, Degree degree, String degreeName, double salary, Department department) {
        setId(id);
        setName(name);
        setDegree(degree);
        setDegreeName(degreeName);
        setSalary(salary);
        setDepartment(department);
        committees = new ArrayList<>();
    }


    public String getName() {
        return name;
    }

    public String getCommittees() {
        String res = "";
        for (int i = 0; i < committees.size(); i++) {
            if (committees.get(i) != null) {
                res += committees.get(i).getCommitteeName() + " ";
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

    public void addCom(Committee newCom){
//        if(comSize>= committees.length){
//            extendCommittees();
//        }
//        committees[comSize++] = newCom;
        committees.add(newCom);
    }

//    public void extendCommittees() {
//        Committee[] newArr = new Committee[committees.size() * 2];
//        for (int i = 0; i < committees.length; i++) {
//            newArr[i] = committees[i];
//        }
//        committees = newArr;
//    }

    public boolean existCommittee(Committee checkCom) {
        for (int i = 0; i < committees.size(); i++) {
            if (committees.get(i) != null && committees.get(i).equals(checkCom)) {
                return true;
            }
        }
        return false;
    }

//    public void shiftCom(int comIndex){
//        for(int i = comIndex; i<committees.length; i++){
//            if(i==committees.size()-1){
//                committees=null;
//            }
//            else{
//                committees[i] = committees[i+1];
//            }
//        }
//        comSize--;
//    }

    public void removeCom(Committee committee) {
//        for (int i = 0; i < committees.length; i++) {
//            if (committees[i] != null && committees[i].equals(committee)) {
//                committees[i] = null;
//                shiftCom(i);
//                break;
//            }
//        }
        committees.remove(committees);
    }

    public void setName(String name) {
        this.name = name;
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
        return
                "name= " + name +
                ", id= " + id +
                ", degree= " + degree +
                ", degree name= " + degreeName +
                ", salary= " + salary +"\n"+
                "department= " + getDep() +"\n"+
                 "committees= " + getCommittees() +"\n";
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


