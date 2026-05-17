// Idan Amrani : 322205808
// Daniel Barbash : 322272683
// Daniel Liser : 325100196

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

public class Collage implements Serializable {
    private String name;
    static Scanner scn = new Scanner(System.in);
    private HashSet<Lecturer> lecturers;
    private HashSet<Committee> committees;
    private HashSet<Department> studyDepartment;

    public Collage(String name) {
        this.name = name;
        lecturers = new HashSet<>();
        committees = new HashSet<>();
        studyDepartment = new HashSet<>();
    }

    // option 1: add lecturer
    public void lecturerToCollage(String name, String id, int degree, String degName, double salary, String depName, int articleSize, String academy, HashSet<String> artSet) {
        Lecturer lecturer;
        if (degree == 3) {
            lecturer = new Doctor(name, id, Degree.degFromInt(degree), degName, salary, null, articleSize, artSet);
        } else if (degree == 4) {
            lecturer = new Professor(name, id, Degree.degFromInt(degree), degName, salary, null, articleSize, academy, artSet);
        } else {
            lecturer = new Lecturer(name, id, Degree.degFromInt(degree), degName, salary, null);
        }
        lecturers.add(lecturer);
        try {
            updateLecDep(findLec(name), findDep(depName));
        } catch (CollageException e) {
        }
    }

    public boolean checkName(String checkLec) {
        Iterator<Lecturer> it = lecturers.iterator();
        while (it.hasNext()) {
            Lecturer lec = it.next();
            if (lec != null && lec.getName().equals(checkLec)) {
                return false;
            }
        }
        return true;
    }

    // option 11: add lecturer to department
    public void updateLecDep(Lecturer lec, Department dep) throws CollageException {
        if (dep == null && lec != null) {
            throw new DepNotExistException();
        }
        if (dep != null && lec == null) {
            throw new LecNotExistException();
        }
        if (lec == null) {
            throw new DepAndLecNotExistException();
        }
        if (lec.getDepartment() != null && lec.getDepartment().equals(dep)) {
            throw new AlreadyMemberException();
        } else {
            if (lec.getDepartment() != null) {
                dep.removeLec(lec);
            }
            lec.setDepartment(dep);
            dep.getLecturers().add(lec);
            dep.setLecSize(dep.getLecSize() + 1);
        }
    }

    public Department findDep(String s) {
        Iterator<Department> it = studyDepartment.iterator();
        while (it.hasNext()) {
            Department dep = it.next();
            if (dep != null && dep.getDepartmentName().equals(s)) {
                return dep;
            }
        }
        return null;
    }

    public Lecturer findLec(String lecName) {
        Iterator<Lecturer> it = lecturers.iterator();
        while (it.hasNext()) {
            Lecturer lec = it.next();
            if (lec != null && lec.getName().equals(lecName)) {
                return lec;
            }
        }
        return null;
    }

    public Committee findCom(String comName) {
        Iterator<Committee> it = committees.iterator();
        while (it.hasNext()) {
            Committee com = it.next();
            if (com != null && com.getCommitteeName().equals(comName)) {
                return com;
            }
        }
        return null;
    }

    // option 2: committee to collage
    public void committeeToCollage(String comName, String lecName, int lecLevel) throws CollageException {
        if (findLec(lecName) == null) {
            throw new LecNotExistException();
        }
        Lecturer headLec;
        try {
            headLec = addHeadOf(lecName);
        } catch (CollageException e) {
            throw new NotProfDocException();
        }
        Committee committee;
        if (lecLevel == 1) {
            committee = new Committee<Lecturer>(comName, headLec, Lecturer.class);
        } else if (lecLevel == 2) {
            committee = new Committee<Doctor>(comName, headLec, Doctor.class);
        } else {
            committee = new Committee<Professor>(comName, headLec, Professor.class);
        }
        if (committee.getHeadOfCommittee() == null) {
            throw new NotProfDocException();
        } else if (committeeExist(committee)) {
            throw new ComExistException();
        } else {
            committees.add(committee);
        }
    }

    public boolean committeeExist(Committee c) {
        Iterator<Committee> it = committees.iterator();
        while (it.hasNext()) {
            Committee com = it.next();
            if (com != null && com.getCommitteeName().equals(c.getCommitteeName())) {
                return true;
            }
        }
        return false;
    }

    public Lecturer addHeadOf(String s) throws CollageException {
        Iterator<Lecturer> it = lecturers.iterator();
        while (it.hasNext()) {
            Lecturer lec = it.next();
            if (lec.getName().equals(s)) {
                if (lec.getDegree().equals(Degree.dr) || lec.getDegree().equals(Degree.prof)) {
                    return lec;
                } else {
                    throw new NotProfDocException();
                }
            }
        }
        return null;
    }

    // option 3: add member to committee
    public void lecturerToCommittee(String com, String lecName) throws CollageException {
        Committee updateCom = findCom(com);
        Lecturer lecToAdd = findLec(lecName);
        if (updateCom != null && lecToAdd != null) {
            if (updateCom.getHeadOfCommittee().equals(lecToAdd)) {
                throw new HeadOfException();
            } else if (lecToAdd.existCommittee(updateCom)) {
                throw new AlreadyMemberException();
            } else if (lecToAdd.getClass().equals(updateCom.getType())) {
                updateCom.addLecturer(lecToAdd);
                lecToAdd.addCom(updateCom);
            } else {
                throw new CollageException("Lecturer Does not fit to the Committee type");
            }
        } else {
            if (updateCom == null && lecToAdd != null) {
                throw new ComNotExistException();
            }
            if (updateCom != null) {
                throw new LecNotExistException();
            } else {
                throw new LecAndComNotExistException();
            }
        }
    }

    // option 4: update head of committee
    public void updateComHead(String com, String lecName) throws CollageException {
        Committee updateCom = findCom(com);
        Lecturer newHead = findLec(lecName);
        if (newHead != null) {
            if (updateCom != null) {
                if (updateCom.getHeadOfCommittee().equals(newHead)) {
                    throw new HeadOfException();
                }
                if (newHead.getDegree().equals(Degree.dr) || newHead.getDegree().equals(Degree.prof)) {
                    updateCom.setHeadOfCommittee(newHead);
                    updateCom.removeLecFromMembers(newHead);
                } else {
                    throw new NotProfDocException();
                }
            } else {
                throw new ComNotExistException();
            }
        } else {
            if (updateCom != null) {
                throw new LecNotExistException();
            } else {
                throw new LecAndComNotExistException();
            }
        }
    }

    // option 5: remove a member from the committee
    public void removeLecFromCom(String com, String name) throws CollageException {
        Committee comToRemove = findCom(com);
        Lecturer removeLec = findLec(name);
        if (comToRemove == null) {
            if (removeLec == null) {
                throw new LecAndComNotExistException();
            } else {
                throw new ComNotExistException();
            }
        } else {
            if (removeLec == null) {
                throw new LecNotExistException();
            } else {
                if (!comToRemove.getCommitteeMembers().contains(removeLec)) {
                    throw new LecNotMemberException();
                } else {
                    comToRemove.removeLecFromMembers(removeLec);
                    removeLec.removeCom(comToRemove);
                }
            }
        }
    }

    // option 6: add dep to collage
    public void addDepToCollege(int numOf, String depName) throws CollageException {
        if (findDep(depName) == null) {
            Department newDep = new Department(depName, numOf);
            studyDepartment.add(newDep);
        } else {
            throw new DepAlreadyExistException();
        }
    }

    // options 7/8
    public double showAvgSalPerDep(Department department) {
        int counter = 0;
        double sum = 0;
        if (department != null) {
            Iterator<Lecturer> it = department.getLecturers().iterator();
            while (it.hasNext()) {
                Lecturer lec = it.next();
                if (lec != null) {
                    sum += lec.getSalary();
                    counter++;
                }
            }
        } else {
            Iterator<Lecturer> it = lecturers.iterator();
            while (it.hasNext()) {
                sum += it.next().getSalary();
                counter++;
            }
        }
        return sum == 0 ? 0 : sum / counter;
    }

    // option 9
    public String showAllLecturers() {
        String res = "";
        Iterator<Lecturer> it = lecturers.iterator();
        while (it.hasNext()) {
            Lecturer lec = it.next();
            if (lec != null) {
                res += lec.toString() + "\n";
            }
        }
        return res;
    }

    // option 9 sorted
    public String showAllLecturersSorted(java.util.Comparator<Lecturer> comparator) {
        TreeSet<Lecturer> sorted = new TreeSet<>(comparator);
        sorted.addAll(lecturers);
        String res = "";
        Iterator<Lecturer> it = sorted.iterator();
        while (it.hasNext()) {
            res += it.next().toString() + "\n";
        }
        return res;
    }

    // option 10
    public String showAllCommittees() {
        String res = "";
        Iterator<Committee> it = committees.iterator();
        while (it.hasNext()) {
            Committee com = it.next();
            if (com != null && com.getHeadOfCommittee() != null) {
                res += com.toString() + "\n";
            }
        }
        return res;
    }

    // option 10 sorted
    public String showAllCommitteesSorted(java.util.Comparator<Committee> comparator) {
        TreeSet<Committee> sorted = new TreeSet<>(comparator);
        sorted.addAll(committees);
        String res = "";
        Iterator<Committee> it = sorted.iterator();
        while (it.hasNext()) {
            res += it.next().toString() + "\n";
        }
        return res;
    }

    public Department AddDepartmentToLecturer(String s) {
        return findDep(s);
    }

    // option 12
    public Lecturer compareLec(String lecName1, String lecName2) throws CollageException {
        Lecturer lecturer1 = findLec(lecName1);
        Lecturer lecturer2 = findLec(lecName2);
        if (lecturer1 == null || lecturer2 == null) {
            throw new LecNotExistException();
        }
        if (!(lecturer1 instanceof Doctor) || !(lecturer2 instanceof Doctor)) {
            throw new NotProfDocException();
        }
        return ((Doctor) lecturer1).compareTo((Doctor) lecturer2) > 0 ? lecturer1 : lecturer2;
    }

    // option 13/14
    public String comByNumOfArt(String firstCom, String secCom, boolean opt) throws CollageException {
        Committee com1 = findCom(firstCom);
        Committee com2 = findCom(secCom);
        if (com1 == null || com2 == null) {
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
        Committee com1 = findCom(com);
        if (com1 == null) {
            throw new ComNotExistException();
        }
        try {
            Committee newCom = (Committee) com1.clone();
            committees.add(newCom);
            Iterator it = newCom.getCommitteeMembers().iterator();
            while (it.hasNext()) {
                Lecturer lec = (Lecturer) it.next();
                lec.addCom(newCom);
            }
        } catch (CloneNotSupportedException e) {
            throw new CollageException(e.getMessage());
        }
    }
}