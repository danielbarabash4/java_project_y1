import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class Department implements Serializable {
    private String DepartmentName;
    private int studentsNum;
    private ArrayList<Lecturer> lecturers;
    private int lecSize = 0; //logical variable

    public Department(String departmentName, int studentsNum) {
        setDepartmentName(departmentName);
        setStudentsNum(studentsNum);
        this.lecturers=new ArrayList<>();
        this.lecSize = 0;
    }

    public int getLecSize() {
        return lecSize;
    }

    public void setLecSize(int lecSize) {
        this.lecSize = lecSize;
    }

    private boolean lecInDep(Lecturer lecturer) {
        if(lecturers.contains(lecturer)){
            return true;
        }
        return false;
//        for (int i = 0; i <lecturers.size() ; i++) {
//            if(lecturers.get(i)!=null && lecturers.get(i).equals(lecturer)){
//                return true;
//            }
//        }
//        return false;
    }

    public void removeLec(Lecturer lec){
        lecturers.remove(lec);
//        if(lecInDep(lec)){
//            for (int i = 0; i <lecturers.size() ; i++) {
//                if(lecturers.get(i)!=null && lecturers.get(i)==lec){
//                    lecturers.remove();
//                    shiftLec(i);
//                    break;
//                }
//            }
//        }
    }

//    public void shiftLec(int lecIndex){
//        for(int i = lecIndex; i <lecturers.size();i++){
//            if(i==lecturers.size()-1){
//                lecturers.remove(i);
//            }
//            else {
//                lecturers[i] = lecturers[i + 1];
//            }
//        }
//        lecSize--;
//    }

    public void setDepartmentName(String departmentName) {
        DepartmentName = departmentName;
    }

    public void setStudentsNum(int studentsNum) {
        this.studentsNum = studentsNum;
    }

    public void setLecturers(ArrayList<Lecturer> lecturers) {
        this.lecturers = lecturers;
    }

    public String getDepartmentName() {
        return DepartmentName;
    }

    public ArrayList<Lecturer> getLecturers() {
        return lecturers;
    }

    @Override
    public String toString() {
        return "Department name:= '" + DepartmentName +
                ", Number of students= " + studentsNum +
                ", lecturers: =" + Arrays.toString(lecturers.toArray());
    }
}
