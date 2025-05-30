import java.util.Arrays;
import java.util.InputMismatchException;
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

    public void lecturerToCollage(String name, String id, int degree, String degName, double salary, String depName, int articleSize, String academy, String[] artArray) {
        Lecturer lecturer;
        if (degree == 3) {
            lecturer = new Doctor(name, id, Degree.degFromInt(degree), degName, salary, null, articleSize, artArray);

        } else if (degree == 4) {
            lecturer = new Professor(name, id, Degree.degFromInt(degree), degName, salary, null, articleSize, academy, artArray);
        } else {
            lecturer = new Lecturer(name, id, Degree.degFromInt(degree), degName, salary, null);
        }
        lecturers = addLecToArr(lecturer, lecturers, lecSize);
        try {
            updateLecDep(findLecIndex(name), findDepIndex(depName));
        } catch (CollageException e) {

        }
        lecSize++;
    }

    public void updateLecDep(int lecInt, int depInt) throws CollageException {
        if (depInt == -1 && lecInt != -1) {
            throw new DepNotExistException();
        }
        if (depInt != -1 && lecInt == -1) {
            throw new LecNotExistException();
        }
        if (lecInt == -1) {
            throw new DepAndLecNotExistException();
        }
        if (lecturers[lecInt].getDepartment() != null && lecturers[lecInt].getDepartment().equals(studyDepartment[depInt])) {
            throw new AlreadyMemberException();
        } else {
            Department updateDep = studyDepartment[depInt];
            Lecturer updateLec = lecturers[lecInt];
            if (updateLec.getDepartment() != null) {
                updateDep.removeLec(updateLec);
            }
            updateLec.setDepartment(updateDep);
            updateDep.setLecturers(addLecToArr(updateLec, updateDep.getLecturers(), updateDep.getLecSize()));
            updateDep.setLecSize(updateDep.getLecSize() + 1);
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

    public void committeeToCollage(String comName, String lecName) throws CollageException {
        if (findLecIndex(lecName) == -1) {
            throw new LecNotExistException();
        }
        Lecturer headLec;
        try {
            headLec = addHeadOf(lecName);
        } catch (CollageException e) {
            throw new NotProfDocException();
        }
        Committee committee = new Committee(comName, headLec);
        if (committee.getHeadOfCommittee() == null) {
            throw new NotProfDocException();
        } else if (committeeExist(committee)) {
            throw new ComExistException();
        } else {
            addNewCom(committee);
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

    public Lecturer addHeadOf(String s) throws CollageException {
        for (int i = 0; i < lecturers.length && lecturers[i] != null; i++) {
            if (lecturers[i].getName().equals(s)) {
                if (lecturers[i].getDegree().equals(Degree.dr) || lecturers[i].getDegree().equals(Degree.prof)) {
                    return lecturers[i];
                } else {
                    throw new NotProfDocException();
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

    public void lecturerToCommittee(String com, String lecName) throws CollageException {
        int comIndex = findComIndex(com);
        int lecIndex = findLecIndex(lecName);
        if (comIndex != -1 && lecIndex != -1) {
            if (committees[comIndex].getHeadOfCommittee().equals(lecturers[lecIndex])) {
                throw new HeadOfException();
            } else if (lecturers[lecIndex].existCommittee(committees[comIndex])) {
                throw new AlreadyMemberException();
            } else {
                committees[comIndex].setCommitteeMembers(addLecToArr(lecturers[lecIndex], committees[comIndex].getCommitteeMembers(), committees[comIndex].getLecSize()));
                committees[comIndex].setLecSize(committees[comIndex].getLecSize() + 1);
                lecturers[lecIndex].addCom(committees[comIndex]);
            }
        } else {
            if (comIndex == -1 && lecIndex != -1) {
                throw new ComNotExistException();
            }
            if (comIndex != -1) {
                throw new LecNotExistException();
            } else {
                throw new LecAndComNotExistException();
            }
        }
    }

    public void updateComHead(String com, String lecName) throws CollageException {
        int comIndex = findComIndex(com);
        int lecIndex = findLecIndex(lecName);


        if (lecIndex != -1) { // lec
            Lecturer newHead = lecturers[findLecIndex(lecName)];
            if (comIndex != -1 && newHead != null) { //com
                if (committees[comIndex].getHeadOfCommittee().equals(lecturers[lecIndex])) {
                    throw new HeadOfException();
                }
                if (newHead.getDegree().equals(Degree.dr) || newHead.getDegree().equals(Degree.prof)) {
                    committees[comIndex].setHeadOfCommittee(newHead);
                    if (hasLec(committees[comIndex], newHead) != -1) {
                        committees[comIndex].removeLecFromMembers(newHead);
                    }
                } else {
                    throw new NotProfDocException();
                }
            } else { // no com
                throw new ComNotExistException();
            }
        } else { //no lec
            if (comIndex != -1) { //com and no lec
                throw new LecNotExistException();
            } else { //no com and no lecturer
                throw new LecAndComNotExistException();
            }
        }
    }

    public void removeLecFromCom(String com, String name) throws CollageException {
        int comUpdate = findComIndex(com);
        int lecIndex = findLecIndex(name);
        if (comUpdate == -1) {// no com
            if (lecIndex == -1) { // no lec
                throw new LecAndComNotExistException();
            } else { // lec
                throw new ComNotExistException();
            }
        } else { // com
            if (lecIndex == -1) { // com no lec
                throw new LecNotExistException();
            } else {// lec and com
                Lecturer removeLec = lecturers[lecIndex];
                Committee comToRemove = committees[comUpdate];
                int lecInCom = hasLec(comToRemove, removeLec);
                if (lecInCom == -1) { // lec is not in com
                    throw new LecNotMemberException();
                } else {// lec is in com
                    committees[comUpdate].removeLecFromMembers(removeLec);
                    removeLec.removeCom(committees[comUpdate]);
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

    public void addDepToCollege(int numOf, String depName) throws CollageException {
        if (findDepIndex(depName) == -1) {
            Department newDep = new Department(depName, numOf);
            addDep(newDep);
        } else {
            throw new DepAlreadyExistException();
        }
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

    public Lecturer compareLec(String lecName1, String lecName2) throws CollageException {
        Lecturer lecturer1;
        Lecturer lecturer2;
        try {
            lecturer1 = lecturers[findLecIndex(lecName1)];
            lecturer2 = lecturers[findLecIndex(lecName2)];
            if (!(lecturer1 instanceof Doctor) || !(lecturer2 instanceof Doctor)) {
                throw new NotProfDocException();
            }
            return ((Doctor) lecturer1).compareTo((Doctor) lecturer2) > 0 ? lecturer1 : lecturer2;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new LecNotExistException(e.getMessage());
        }
    }

    public String comByNumOfArt(String firstCom, String secCom, boolean opt) throws CollageException {
        Committee com1;
        Committee com2;
        try {
            com1 = committees[findComIndex(firstCom)];
            com2 = committees[findComIndex(secCom)];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ComNotExistException();
        }
        int res;
        if (opt) {
            res = new SortComByArc().compare(com1, com2);
        } else {
            res = new SortComByNumOfLec().compare(com1, com2);

        }
        if (res == 0) {
            throw new EvenException();
        }
        return res > 0 ? com1.getCommitteeName() : com2.getCommitteeName();

    }

    public void cloneCom(String com) throws CollageException {
        Committee com1;
        try {
            com1 = committees[findComIndex(com)];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ComNotExistException();
        }
        try {
            Committee newCom = (Committee) com1.clone();
            addNewCom(newCom);
            for (int i = 0; i < newCom.getLecSize(); i++) {
                newCom.getCommitteeMembers()[i].addCom(newCom);
            }
        } catch (CloneNotSupportedException e) {
            throw new CollageException(e.getMessage());
        }
    }
}


