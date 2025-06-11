import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Collage implements Serializable  {
    private String name;
    static Scanner scn = new Scanner(System.in);
    private ArrayList<Lecturer> lecturers;
    private ArrayList<Committee> committees;
    private ArrayList<Department> studyDepartment;
    private int lecSize, comSize, depSize; //logical variables

    public Collage(String name) {
        this.name = name;
        lecturers = new ArrayList<>();
        committees = new ArrayList<>();
        studyDepartment = new ArrayList<>();
    }

    public void lecturerToCollage(String name, String id, int degree, String degName, double salary, String depName, int articleSize, String academy,ArrayList<String> artArray) {
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
        if (lecturers.get(lecInt).getDepartment() != null && lecturers.get(lecInt).getDepartment().equals(studyDepartment.get(lecInt))) {
            throw new AlreadyMemberException();
        } else {
            Department updateDep = studyDepartment.get(depInt);
            Lecturer updateLec = lecturers.get(lecInt);
            if (updateLec.getDepartment() != null) {
                updateDep.removeLec(updateLec);
            }
            updateLec.setDepartment(updateDep);
            updateDep.setLecturers(addLecToArr(updateLec, updateDep.getLecturers(), updateDep.getLecSize()));
            updateDep.setLecSize(updateDep.getLecSize() + 1);
        }
    }

    public boolean checkName(String checkLec) {
        for (int i = 0; i < lecturers.size(); i++) {
            if (lecturers.get(i) != null && lecturers.get(i).getName().equals(checkLec)) {
                return false;
            }
        }
        return true;
    }

    public Department AddDepartmentToLecturer(String s) {
        if (findDepIndex(s) != -1) {
            return studyDepartment.get(findDepIndex(s));
        } else {
            return null;
        }
    }

    public ArrayList<Lecturer> addLecToArr(Lecturer newLec, ArrayList<Lecturer> lecArr, int size) {
//        if (lecArr.size() <= size) {
////            lecArr = extendLecturer(lecArr);
//        }
////        lecArr[size] = newLec;
        lecArr.add(newLec);
        return lecArr;
    }

    public void committeeToCollage(String comName, String lecName,int lecLevel) throws CollageException {
        if (findLecIndex(lecName) == -1) {
            throw new LecNotExistException();
        }
        Lecturer headLec;
        try {
            headLec = addHeadOf(lecName);
        } catch (CollageException e) {
            throw new NotProfDocException();
        }
        Committee committee;
        if(lecLevel==1){
            committee=new Committee<Lecturer>(comName, headLec,Lecturer.class);
        } else if (lecLevel==2) {
            committee=new Committee<Doctor>(comName, headLec,Doctor.class);
        }
        else{
            committee=new Committee<Professor>(comName, headLec,Professor.class);
        }
        if (committee.getHeadOfCommittee() == null) {
            throw new NotProfDocException();
        } else if (committeeExist(committee)) {
            throw new ComExistException();
        } else {
            addNewCom(committee);
        }
    }

    public void addNewCom(Committee com) {
        committees.add(com);
//        if (committees.length <= comSize) {
//            extendCommittees();
//        }
//        committees[comSize++] = com;
    }

//    public void extendCommittees() {
//        Committee[] newArr = new Committee[committees.length * 2];
//        for (int i = 0; i < committees.length; i++) {
//            newArr[i] = committees[i];
//        }
//        committees = newArr;
//    }

    public boolean committeeExist(Committee c) {
        for (int i = 0; i < committees.size(); i++) {
            if (committees.get(i) != null && committees.get(i).getCommitteeName().equals(c.getCommitteeName())) {
                return true;
            }
        }
        return false;
    }

    public Lecturer addHeadOf(String s) throws CollageException {
        for (int i = 0; i < lecturers.size() && lecturers.get(i) != null; i++) {
            if (lecturers.get(i).getName().equals(s)) {
                if (lecturers.get(i).getDegree().equals(Degree.dr) || lecturers.get(i).getDegree().equals(Degree.prof)) {
                    return lecturers.get(i);
                } else {
                    throw new NotProfDocException();
                }
            }
        }
        return null;
    }

    public int findLecIndex(String lecName) {
        for (int i = 0; i < lecturers.size(); i++) {
            if (lecturers.get(i) != null && lecturers.get(i).getName().equals(lecName)) {
                return i;
            }
        }
        return -1;
    }

    public int findComIndex(String comName) {
        for (int i = 0; i < committees.size(); i++) {
            if (committees.get(i) != null && committees.get(i).getCommitteeName().equals(comName)) {
                return i;
            }
        }
        return -1;
    }

    public void lecturerToCommittee(String com, String lecName) throws CollageException {
        int comIndex = findComIndex(com);
        int lecIndex = findLecIndex(lecName);
        if (comIndex != -1 && lecIndex != -1) {
            if (committees.get(comIndex).getHeadOfCommittee().equals(lecturers.get(lecIndex))) {
                throw new HeadOfException();
            } else if (lecturers.get(lecIndex).existCommittee(committees.get(comIndex))) {
                throw new AlreadyMemberException();
            } else {
                committees.get(comIndex).addLecturer(lecturers.get(lecIndex));
               // committees.get(comIndex).setCommitteeMembers(addLecToArr(lecturers.get(lecIndex), committees.get(comIndex).getCommitteeMembers(), committees.get(comIndex).getLecSize()));
                //committees.get(comIndex).setLecSize(committees.get(comIndex).getLecSize() + 1);
                lecturers.get(lecIndex).addCom(committees.get(comIndex));
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
            Lecturer newHead = lecturers.get(findLecIndex(lecName));
            if (comIndex != -1 && newHead != null) { //com
                if (committees.get(comIndex).getHeadOfCommittee().equals(lecturers.get(lecIndex))) {
                    throw new HeadOfException();
                }
                if (newHead.getDegree().equals(Degree.dr) || newHead.getDegree().equals(Degree.prof)) {
                    committees.get(comIndex).setHeadOfCommittee(newHead);
                    if (hasLec(committees.get(comIndex), newHead) != -1) {
                        committees.get(comIndex).removeLecFromMembers(newHead);
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
                Lecturer removeLec = lecturers.get(lecIndex);
                Committee comToRemove = committees.get(comUpdate);
                int lecInCom = hasLec(comToRemove, removeLec);
                if (lecInCom == -1) { // lec is not in com
                    throw new LecNotMemberException();
                } else {// lec is in com
                    committees.get(comUpdate).removeLecFromMembers(removeLec);
                    removeLec.removeCom(committees.get(comUpdate));
                }
            }
        }
    }

    private int hasLec(Committee checkCom, Lecturer checkHead) {
        for (int i = 0; i < checkCom.getCommitteeMembers().size(); i++) {
            if (checkCom.getCommitteeMembers().get(i) != null && checkCom.getCommitteeMembers().get(i).equals(checkHead)) {
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
//        if (depSize >= studyDepartment.size()) {
//            extendDep();
//        }
        studyDepartment.add(dep);
    }

//    private void extendDep() {
//        Department[] newArr = new Department[studyDepartment.size() * 2];
//        for (int i = 0; i < studyDepartment.size(); i++) {
//            newArr[i] = studyDepartment.get(i);
//        }
//        studyDepartment = newArr;
//    }

    public int findDepIndex(String s) {
        for (int i = 0; i < studyDepartment.size(); i++) {
            if (studyDepartment.get(i) != null && studyDepartment.get(i).getDepartmentName().equals(s))
                return i;
        }
        return -1;
    }

    public double showAvgSalPerDep(Department department) {//option 7+8
        int counter = 0;
        double sum = 0;
        if (department != null) {
            for (int i = 0; i < department.getLecturers().size(); i++) {
                if (department.getLecturers().get(i) != null) {
                    sum += department.getLecturers().get(i).getSalary();
                    counter++;
                }
            }
        } else {
            for (int i = 0; i < lecturers.size(); i++) {
                sum += lecturers.get(i).getSalary();
                counter++;
            }
        }
        return sum == 0 ? 0 : sum / counter;
    }

    public String showAllLecturers() {//option 9
        String res = "";
        for (int i = 0; i < lecturers.size(); i++) {
            if (lecturers.get(i) != null)
                res += lecturers.get(i).toString() + "\n";
        }

        return res;
    }

    public String showAllCommittees() {//option 10
        String res = "";
        for (int i = 0; i < committees.size() && committees.get(i) != null; i++) {
            if (committees.get(i).getHeadOfCommittee() != null) {
                res += committees.get(i).toString() + "\n";
            }
        }
        return res;
    }

//    private Lecturer[] extendLecturer(Lecturer[] oldArr) {
//        Lecturer[] newArr = new Lecturer[oldArr.length * 2];
//        for (int i = 0; i < oldArr.length; i++) {
//            newArr[i] = oldArr[i];
//        }
//        return newArr;
//    }

    public Lecturer compareLec(String lecName1, String lecName2) throws CollageException {
        Lecturer lecturer1;
        Lecturer lecturer2;
        try {
            lecturer1 = lecturers.get(findLecIndex(lecName1));
            lecturer2 = lecturers.get(findLecIndex(lecName2));
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
            com1 = committees.get(findComIndex(firstCom));
            com2 = committees.get(findComIndex(secCom));
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
            com1 = committees.get(findComIndex(com));
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ComNotExistException();
        }
        try {
            Committee newCom = (Committee) com1.clone();
            addNewCom(newCom);
            for (int i = 0; i < newCom.getLecSize(); i++) {
                Lecturer lec = (Lecturer) newCom.getCommitteeMembers().get(i);
                lec.addCom(newCom);
            }
        } catch (CloneNotSupportedException e) {
            throw new CollageException(e.getMessage());
        }
    }
}


