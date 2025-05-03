import java.util.Scanner;

public class Collage {
    private String name;
    static Scanner scn = new Scanner(System.in);
    private Lecturer[] lecturers;
    private Committee[] committees;
    private Department[] studyDepartment;
    private int lecSize, comSize, depSize; //logical variables

    public Collage(String name) {
        this.name = name;
        lecturers = new Lecturer[1];
        committees = new Committee[1];
        studyDepartment = new Department[1];
    }

    public void lecturerToCollage(String name, String id, int degree, String degName, double salary, String depName) {
        Lecturer lecturer = new Lecturer(name, id, Degree.degFromInt(degree), degName, salary, null);
        lecturers = addLecToArr(lecturer, lecturers, lecSize);
        updateLecDep(findLecIndex(name), findDepIndex(depName));
        lecSize++;
    }

    public int updateLecDep(int lecInt, int depInt) {
        if (depInt == -1 && lecInt != -1) {
            return 1;
        }
        if (depInt != -1 && lecInt == -1) {
            return 2;
        }
        if (lecInt == -1 && depInt == -1) {
            return 3;
        }
        if (lecturers[lecInt].getDepartment().equals(studyDepartment[depInt])) {
            return 4;
        } else {
            Department updateDep = studyDepartment[depInt];
            Lecturer updateLec = lecturers[lecInt];
            if (updateLec.getDepartment() != null) {
                updateDep.removeLec(updateLec);
            }
            updateLec.setDepartment(updateDep);
            updateDep.setLecturers(addLecToArr(updateLec, updateDep.getLecturers(), updateDep.getLecSize()));
            updateDep.setLecSize(updateDep.getLecSize() + 1);

            return 5;
        }
    }

    public boolean checkName(String checkLec) {
        for (int i = 0; i < lecturers.length; i++) {
            if (lecturers[i] != null && lecturers[i].getName().equals(checkLec)) {
                return false;
            }
        }
        return true;
    }

    public Department AddDepartmentToLecturer(String s) {
        if (findDepIndex(s) != -1) {
            return studyDepartment[findDepIndex(s)];
        } else {
            return null;
        }
    }

    public Lecturer[] addLecToArr(Lecturer newLec, Lecturer[] lecArr, int size) {
        if (lecArr.length <= size) {
            lecArr = extendLecturer(lecArr);
        }
        lecArr[size] = newLec;
        return lecArr;
    }

    public int committeeToCollage(String comName, String lecName) {
        if (findLecIndex(lecName) == -1) {
            return 5;
        }
        Committee committee = new Committee(comName, addHeadOf(lecName));
        if (committee.getHeadOfCommittee() == null) {
            return 1;
        } else if (committeeExist(committee)) {
            return 2;
        } else {
            addNewCom(committee);
            return 3;
        }
    }

    public void addNewCom(Committee com) {
        if (committees.length <= comSize) {
            extendCommittees();
        }
        committees[comSize++] = com;
    }

    public void extendCommittees() {
        Committee[] newArr = new Committee[committees.length * 2];
        for (int i = 0; i < committees.length; i++) {
            newArr[i] = committees[i];
        }
        committees = newArr;
    }

    public boolean committeeExist(Committee c) {
        for (int i = 0; i < committees.length; i++) {
            if (committees[i] != null && committees[i].getCommitteeName().equals(c.getCommitteeName())) {
                return true;
            }
        }
        return false;
    }

    public Lecturer addHeadOf(String s) {
        for (int i = 0; i < lecturers.length && lecturers[i] != null; i++) {
            if (lecturers[i].getName().equals(s)) {
                if (lecturers[i].getDegree().equals(Degree.dr) || lecturers[i].getDegree().equals(Degree.prof)) {
                    return lecturers[i];
                } else {

                    return null;
                }
            }
        }
        return null;
    }

    public int findLecIndex(String lecName) {
        for (int i = 0; i < lecturers.length; i++) {
            if (lecturers[i] != null && lecturers[i].getName().equals(lecName)) {
                return i;
            }
        }
        return -1;
    }

    public int findComIndex(String comName) {
        for (int i = 0; i < committees.length; i++) {
            if (committees[i] != null && committees[i].getCommitteeName().equals(comName)) {
                return i;
            }
        }
        return -1;
    }

    public int lecturerToCommittee(String com, String lecName) {
        int comIndex = findComIndex(com);
        int lecIndex = findLecIndex(lecName);
        if (comIndex != -1 && lecIndex != -1) {
            if (committees[comIndex].getHeadOfCommittee().equals(lecturers[lecIndex])) {
                return 1;
            } else if (lecturers[lecIndex].existCommittee(committees[comIndex])) {
                return 2;
            } else {
                committees[comIndex].setCommitteeMembers(addLecToArr(lecturers[lecIndex], committees[comIndex].getCommitteeMembers(), committees[comIndex].getLecSize()));
                committees[comIndex].setLecSize(committees[comIndex].getLecSize() + 1);
                lecturers[lecIndex].addCom(committees[comIndex]);
                return 3;
            }
        } else {
            if (comIndex == -1 && lecIndex != -1) {
                return 4;
            }
            if (comIndex != -1) {
                return 5;
            } else {
                return 6;
            }
        }
    }

    public int updateComHead(String com, String lecName) {
        int comIndex = findComIndex(com);
        int lecIndex = findLecIndex(lecName);


        if (lecIndex != -1) { // lec
            Lecturer newHead = lecturers[findLecIndex(lecName)];
            if (comIndex != -1 && newHead != null) { //com
                if (committees[comIndex].getHeadOfCommittee().equals(lecturers[lecIndex])) {
                    return 6;
                }
                if (newHead.getDegree().equals(Degree.dr) || newHead.getDegree().equals(Degree.prof)) {
                    committees[comIndex].setHeadOfCommittee(newHead);
                    if (hasLec(committees[comIndex], newHead) != -1) {
                        committees[comIndex].removeLecFromMembers(newHead);
                    }
                    return 1;
                } else {
                    return 2;
                }
            } else { // no com
                return 5;
            }
        } else { //no lec
            if (comIndex != -1) { //com and no lec
                return 3;
            } else { //no com and no lecturer
                return 4;
            }
        }
    }

    public int removeLecFromCom(String com, String name) {
        int comUpdate = findComIndex(com);
        int lecIndex = findLecIndex(name);
        if (comUpdate == -1) {// no com
            if (lecIndex == -1) { // no lec
                return 5;
            } else { // lec
                return 2;
            }
        } else { // com
            if (lecIndex == -1) { // com no lec
                return 4;
            } else {// lec and com
                Lecturer removeLec = lecturers[lecIndex];
                Committee comToRemove = committees[comUpdate];
                int lecInCom = hasLec(comToRemove, removeLec);
                if (lecInCom == -1) { // lec is not in com
                    return 3;
                } else {// lec is in com
                    committees[comUpdate].removeLecFromMembers(removeLec);
                    removeLec.removeCom(committees[comUpdate]);
                    return 1;
                }
            }
        }
    }

    private int hasLec(Committee checkCom, Lecturer checkHead) {
        for (int i = 0; i < checkCom.getCommitteeMembers().length; i++) {
            if (checkCom.getCommitteeMembers()[i] != null && checkCom.getCommitteeMembers()[i].equals(checkHead)) {
                return i;
            }
        }
        return -1;
    }

    public int addDepToCollege(int numOf, String depName) {
        String name;
        if (findDepIndex(depName) == -1) {
            Department newDep = new Department(depName, numOf);
            addDep(newDep);
            return 1;
        }

        return 2;
    }

    private void addDep(Department dep) {
        if (depSize >= studyDepartment.length) {
            extendDep();
        }
        studyDepartment[depSize++] = dep;
    }

    private void extendDep() {
        Department[] newArr = new Department[studyDepartment.length * 2];
        for (int i = 0; i < studyDepartment.length; i++) {
            newArr[i] = studyDepartment[i];
        }
        studyDepartment = newArr;
    }

    public int findDepIndex(String s) {
        for (int i = 0; i < studyDepartment.length; i++) {
            if (studyDepartment[i] != null && studyDepartment[i].getDepartmentName().equals(s))
                return i;
        }
        return -1;
    }

    public double showAvgSalPerDep(Department department) {//option 7+8
        int counter = 0;
        double sum = 0;
        if (department != null) {
            for (int i = 0; i < department.getLecturers().length; i++) {
                if (department.getLecturers()[i] != null) {
                    sum += department.getLecturers()[i].getSalary();
                    counter++;
                }
            }
        } else {
            for (int i = 0; i < lecturers.length; i++) {
                sum += lecturers[i].getSalary();
                counter++;
            }
        }
        return sum == 0 ? 0 : sum / counter;
    }

    public String showAllLecturers() {//option 9
        String res = "";
        for (int i = 0; i < lecturers.length; i++) {
            if (lecturers[i] != null)
                res += lecturers[i].toString() + "\n";
        }

        return res;
    }

    public String showAllCommittees() {//option 10
        String res = "";
        for (int i = 0; i < committees.length && committees[i] != null; i++) {
            if (committees[i].getHeadOfCommittee() != null) {
                res += committees[i].toString() + "\n";
            }
        }
        return res;
    }

    private Lecturer[] extendLecturer(Lecturer[] oldArr) {
        Lecturer[] newArr = new Lecturer[oldArr.length * 2];
        for (int i = 0; i < oldArr.length; i++) {
            newArr[i] = oldArr[i];
        }
        return newArr;
    }

}


