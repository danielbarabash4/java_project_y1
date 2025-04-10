public class Department {
    private String DepartmentName;
    private int studentsNum;
    private String lecturers;

    public Department(String departmentName, int studentsNum, String lecturers) {
        setDepartmentName(departmentName);
        setStudentsNum(studentsNum);
        setLecturers(lecturers);
    }
    public void AddNewLecturer(Lecturer lecturer){
        if(isFull(lecturers)){
            lecturers=extendArr();
        }
        lecturerAdd(lecturer);

    }

    private Lecturer[] extendArr() {
        Lecturer []newArr=new Lecturer[lecturers.length*2];
        for (int i = 0; i < lecturers.length; i++) {
            if(lecturers[i]!=null){
                newArr[i]=lecturers[i];
            }
        }
        return newArr;
    }

    private void lecturerAdd(Lecturer lecturer) {
        for (int i = 0; i < lecturers.length; i++) {
            if(lecturers[i]==null)
                lecturers[i]=lecturer;
                break;

        }
    }

    private boolean isFull(Lecturer[] lecturers) {
        for (int i = 0; i < lecturers.length; i++) {
            if(lecturers[i]==null){
                return true;
            }
        }
        return false;
    }

    public void setDepartmentName(String departmentName) {
        DepartmentName = departmentName;
    }

    public void setStudentsNum(int studentsNum) {
        this.studentsNum = studentsNum;
    }

    public void setLecturers(String lecturers) {
        this.lecturers = lecturers;
    }

    public String getDepartmentName() {
        return DepartmentName;
    }

    public int getStudentsNum() {
        return studentsNum;
    }

    public String getLecturers() {
        return lecturers;
    }

}
