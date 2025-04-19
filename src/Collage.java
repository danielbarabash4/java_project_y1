import java.util.Scanner;

public class Collage {
    private String name;
    static Scanner scn = new Scanner(System.in);
    private Lecturer[] lecturers;
    private Committee[] committees;
    private Department[] studyDepartment;

    public Collage(String name) {
        this.name = name;
        lecturers = new Lecturer[1];
        committees = new Committee[1];
        studyDepartment = new Department[1];
    }

    public void lecturerToCollage() {
        Lecturer lecturer = new Lecturer(stringInput("name"), checkId(stringInput("id")), stringInput("degree"),
                stringInput("degree name"), doubleInput("lecturer salary"), AddDepartmentToLecturer(stringInput("department name")));
        if (isLecturerExist(lecturer)) {
            System.out.println("Lecturer is already in the system");
            lecturerToCollage();
        } else if (lecturer.getSalary() < 0) {
            System.out.println("Invalid salary input");

        } else {
            lecturers = addLecturerToLecArr(lecturer, lecturers);
            if (lecturer.getDepartment() != null) {
                lecturer.getDepartment().AddNewLecturer(lecturer);
                System.out.println("Lecturer was added");
            }
        }
    }

    private String checkId(String id) {
        for (int i = 0; i < lecturers.length; i++) {
            if (lecturers[i] != null && lecturers[i].getId().equals(id)) {
                System.out.println("this id is already in use");
                return checkId(stringInput("id"));
            }
        }
        return id;
    }

    public boolean isLecturerExist(Lecturer lecturer) {
        for (int i = 0; i < lecturers.length; i++) {
            if (lecturers[i] != null && lecturers[i].equals(lecturer))
                return true;
        }
        return false;
    }

    public Lecturer[] addLecturerToLecArr(Lecturer lecturer, Lecturer[] lecArr) {
        int temp = lecArr.length;
        for (int i = 0; i < lecArr.length; i++) {
            if (lecArr[i] == null) {
                lecArr[i] = lecturer;
                return lecArr;
            }
        }
        lecArr = extendLecturer(lecArr);
        lecArr[temp] = lecturer;

        return lecArr;
    }

    public void committeeToCollage() {
        Committee committee = new Committee(stringInput("committee name"), addHeadOf(stringInput("head lecturer id")));
        if (committeeExist(committee)) {
            System.out.println("Committee is already in the system");
        } else if(committee.getHeadOfCommittee()!=null){
            addNewCommittee(committee);
            System.out.println("Committee was added");
        }
    }

    public void addNewCommittee(Committee committee) {
        int i = 0;
        for (; i < committees.length && committees[i] != null; ) {
            i++;
        }
        if (i >= committees.length) {
            extendCommittees();
        }
        committees[i] = committee;
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
            if (lecturers[i].getId().equals(s)) {
                if (lecturers[i].getDegree().equals("prof") || lecturers[i].getDegree().equals("dr")) {
                    return lecturers[i];
                } else {
                    System.out.println("Lecturer doesn't meet the criterion");
                    return null;
                }
            }
        }
        System.out.println("Lecturer doesn't exit");
        return null;
    }

    public int findLecIndex(String lecID) {
        for (int i = 0; i < lecturers.length; i++) {
            if (lecturers[i] != null && lecturers[i].getId().equals(lecID)) {
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

    public void lecturerToCommittee() {
        int comIndex = findComIndex(stringInput("committee to add a lecturer"));
        int lecIndex = findLecIndex(stringInput("lecturer id to add"));
        if (comIndex != -1 && lecIndex != -1) {
            if (committees[comIndex].getHeadOfCommittee().equals(lecturers[lecIndex])) {
                System.out.println("This lecturer already is the head of the committee");
            }else if(lecturers[lecIndex].existCommittee(committees[comIndex])){
                System.out.println("This lecturer is already a member of the committee");
            }else{
                committees[comIndex].setCommitteeMembers(addLecturerToLecArr(lecturers[lecIndex], committees[comIndex].getCommitteeMembers()));
                lecturers[lecIndex].addCommittee(committees[comIndex]);
                System.out.println("Lecturer was added to the committee");

            }
        }
    }

    public void updateComHead() {
        int comIndex = findComIndex(stringInput("committee to update: "));
        Lecturer newHead = lecturers[findLecIndex(stringInput("new head of committee id: "))];
        if (comIndex != -1 && newHead != null) {
            if (newHead.getDegree().equals("dr") || newHead.getDegree().equals("prof")) {
                committees[comIndex].setHeadOfCommittee(newHead);
                if (hasLec(committees[comIndex], newHead) != -1) {
                    committees[comIndex].removeLecFromMembers(newHead);
                }
                System.out.println("Head of committee was updated");
            }
            else{
                System.out.println("Lecturer degree is not high enough");
            }
        }
    }

    public void removeLecFromCom() {
        int comUpdate = findComIndex(stringInput("committee to update"));
        Lecturer removeLec = lecturers[findLecIndex(stringInput("id of a lecturer to remove: "))];
        if (comUpdate != -1 && removeLec != null) {
            committees[comUpdate].removeLecFromMembers(removeLec);
            removeLec.removeCom(committees[comUpdate]);
            System.out.println("Lecturer was removed from the committee");
        }
        else if(comUpdate == -1){
            System.out.println("Committee doesn't exist");
        }
        else{
            System.out.println("Lecturer is not a member of the committee");
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

    public void lecturerToDep(){

    };

//    public Lecturer findLec(String lecId) {
//        for (int i = 0; i < lecturers.length; i++) {
//            if (lecturers[i] != null && lecturers[i].getId().equals(lecId)) {
//                return lecturers[i];
//            }
//        }
//        return null;
//    }

    public void addDepToCollege() {
        Department newDep = new Department(chooseDepName(), intInput("number of students"));
        addDepartment(newDep);
    }

    private void addDepartment(Department newDep) {
        int i = 0;
        for (; i < studyDepartment.length && studyDepartment[i] != null; ) {
            i++;
        }
        if (i >= studyDepartment.length) {
            extendDep();
        }
        studyDepartment[i] = newDep;
    }

    private void extendDep() {
        Department[] newArr = new Department[studyDepartment.length * 2];
        for (int i = 0; i < studyDepartment.length; i++) {
            newArr[i] = studyDepartment[i];
        }
        studyDepartment = newArr;
    }

    private String chooseDepName() {
        String name;
        while (true) {
            System.out.println("choose department name: ");
            name = scn.nextLine();
            if (findDepIndex(name) == -1) {
                return name;
            } else {
                System.out.println("name already exist");
            }
        }
    }

    public Department AddDepartmentToLecturer(String s) {
        if (findDepIndex(s) != -1) {
            return studyDepartment[findDepIndex(s)];
        } else {
            System.out.println("Department doesn't exist");
            return null;
        }
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
        return sum / counter;
    }

    public void showAllLecturers() {//option 9
        System.out.println("--------------");
        for (int i = 0; i < lecturers.length; i++) {
            if (lecturers[i] != null)
                System.out.println(lecturers[i]);
        }
        System.out.println("--------------");
    }

    public void showAllCommitees() {//option 10
        System.out.println("--------------");
        for (int i = 0; i < committees.length && committees[i] != null; i++) {
            if( committees[i].getHeadOfCommittee()!=null) {
                System.out.println(committees[i]);
                System.out.println("--------------");
            }
        }
    }

    private double doubleInput(String word) {
        System.out.println("Choose a " + word + ":");
        double res = scn.nextDouble();
        scn.nextLine();
        return res;
    }

    public String stringInput(String word) {
        System.out.println("Choose a " + word + ":");
        String res = scn.nextLine();
        return res;
    }

    public int intInput(String word) {
        System.out.println("Choose a " + word + ":");
        int res = scn.nextInt();
        scn.nextLine();
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
