import java.util.Objects;
import java.util.Scanner;

public class Collage {
    private String name;
    static Scanner scn = new Scanner(System.in);
    private static Lecturer[] lecturers = new Lecturer[1];
    private static Committee[] committees = new Committee[1];
    private static Department[] studyDepartment = new Department[1];

    private static void lecturerToCollage() {
        Lecturer lecturer = new Lecturer(stringInput("lecturer name"), stringInput("id"), stringInput("degree"),
                stringInput("degree name"), doubleInput("lecturer salary"), AddDepartmentToLecturer("Department name:"));
        if (isLecturerExist(lecturer)) {
            System.out.println("Lecturer is already in the system");
        } else {
            lecturers = addLecturerToDepartment(lecturer);
            if (lecturer.getDepartment() != null) {
                lecturer.getDepartment().AddNewLecturer(lecturer);
            }
        }
    }

    private static boolean isLecturerExist(Lecturer lecturer) {
        for (int i = 0; i < lecturers.length; i++) {
            if (lecturers[i].equals(lecturer))
                return true;
        }
        return false;
    }

    private static Lecturer[] addLecturerToDepartment(Lecturer lecturer) {
        int temp = lecturers.length;
        for (int i = 0; i < lecturers.length; i++) {
            if (lecturers[i] == null) {
                lecturers[i] = lecturer;
                return lecturers;
            }
        }
        lecturers = extendLecturer();
        lecturers[temp] = lecturer;

        return lecturers;
    }

    private static void committeeToCollage() {
        Committee committee = new Committee(stringInput("committee name"), addHeadOf(stringInput("lecturer id")));
        if (committeeExist(committee)) {
            System.out.println("Committee is already in the system");
        } else {
            addNewCommittee(committee);
        }
    }

    private static void addNewCommittee(Committee committee) {
        int i = 0;
        for (; i < committees.length && committees[i] != null; ) {
            i++;
        }
        if( i >= committees.length){
            extendCommittees();
        }
    committees[i] = committee;
    }

    private static void extendCommittees() {
        Committee[] newArr = new Committee[committees.length*2];
        for(int i = 0;i< committees.length;i++){
            newArr[i] = committees[i];
        }
        committees = newArr;
    }

    private static boolean committeeExist(Committee c) {
        for (int i = 0; i < committees.length && committees[i] != null; i++) {
            if (committees[i].equals(c)) {
                return true;
            }
        }
        return false;
    }

    private static Lecturer addHeadOf(String s) {
        for (int i = 0; i < lecturers.length && lecturers[i] != null; i++) {
            if (lecturers[i].getId().equals(s)) {
                return lecturers[i];
            }
        }
        System.out.println("Lecturer doesn't exit");
        return null;
    }

    private static Department AddDepartmentToLecturer(String s) {
        if (findDepIndex(s) != -1) {
            return studyDepartment[findDepIndex(s)];
        } else {
            System.out.println("Department doesn't exist");
            return null;
        }
    }

    private static int findDepIndex(String s) {
        for (int i = 0; i < studyDepartment.length; i++) {
            if (studyDepartment[i] != null && studyDepartment[i].getDepartmentName() == s)
                return i;
        }
        return -1;
    }

    public static double showAvgSalPerDep(Department department) {//option 8
        int counter = 0;
        double sum = 0;
        for (int i = 0; i < lecturers.length; i++) {
            sum += lecturers[i].getSalary();
            counter++;
        }
        return sum / counter;
    }

    public static void showAllLecturers() {//option 9
        System.out.println("--------------");
        for (int i = 0; i < lecturers.length; i++) {
            if (lecturers[i] != null)
                System.out.println(lecturers[i]);
        }
        System.out.println("--------------");
    }

    public static void showAllDepartments() {//option 10
        System.out.println("--------------");
        for (int i = 0; i < studyDepartment.length; i++) {
            if (studyDepartment[i] != null)
                System.out.println(studyDepartment[i]);
            System.out.println("--------------");
        }
    }

    private static double doubleInput(String word) {
        System.out.println("Choose a " + word + ":");
        double res = scn.nextDouble();
        scn.nextLine();
        return res;
    }

    public static String stringInput(String word) {
        System.out.println("Choose a " + word + ":");
        String res = scn.nextLine();
        return res;
    }

    public static int intInput(String word) {
        System.out.println("Choose a " + word + ":");
        int res = scn.nextInt();
        scn.nextLine();
        return res;
    }
// old way to extand an array, not working now
//    private static String[] addArrName(String[] arr, String type) {
//        System.out.print("Enter your " + type + " name: ");
//        String theName = scn.nextLine();
//        if (checkForName(arr, theName)) {
//            if (lastPlace(arr) >= arr.length) {
//                arr = extendArray(arr);
//            }
//            arr[lastPlace(arr)] = theName;
//        } else {
//            System.out.println("the name " + theName + " is already in use, please try again");
//            arr = addArrName(arr, type);
//        }
//        return arr;
//    }

    private static int lastPlace(Objects[] arr) {
        int i = 0;
        for (; i < arr.length && arr[i] != null; ) {
            i++;
        }
        return i;
    }

    private static boolean checkForName(String[] arr, String name) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null && arr[i].equals(name)) {
                return false;
            }
        }
        return true;
    }

    private static Lecturer[] extendLecturer() {
        Lecturer[] newArr = new Lecturer[lecturers.length * 2];
        for (int i = 0; i < lecturers.length; i++) {
            newArr[i] = lecturers[i];
        }
        return newArr;
    }

    private static void printStringArray(String[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null) {
                System.out.print(arr[i] + " ");
            }
        }
        System.out.println();
    }

}
