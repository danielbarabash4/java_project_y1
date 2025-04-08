import java.util.Scanner;

public class Collage {
    private String name;
    static Scanner scn = new Scanner(System.in);
    private static Lecturer[] lecturers = new Lecturer[1];
    private static Committee[] committee = new Committee[1];
    private static Department[] studyDepartment = new Department[1];

    private static void lecturerToCollage() {
        String lecName, comName;
        System.out.print("Choose a department: ");
        comName = scn.nextLine();
        Lecturer lecturer=new Lecturer(StringInput("lecturer name"),IntInput("id"),StringInput("degree"),
                StringInput("degree name"),DoubleInput("lecturer salary"),AddDepartmentToLecturer("Department name:"));



        if (checkForName(lecturers, lecName)) {
            System.out.println("this lecture does not exist");
        }
        if (checkForName(committee, comName)) {
            System.out.println("this committee does not exist");
        }
    }

    private static Department AddDepartmentToLecturer(String s) {
        if(findDepIndex(s)!=-1){
            return studyDepartment[findDepIndex(s)];
        }
        else{
            return studyDepartment=AddDepartment(s);
        }
    }
    public static void AddDepartment(String s){
        Lecturer[] lec=new Lecturer[1];
        Department res= new Department(StringInput("department"),IntInput("amount of sudents"),lec);

        ///  להוסיף לSTUDYDEPARTMENT

    }

    private static Object AddLecturer(String lecturerName) {
    }

    private static int findDepIndex( String s) {
        for(int i=0;i<studyDepartment.length;i++){
            if(studyDepartment[i]==null)
                continue;
            //return 2
            if(studyDepartment[i].getDepartmentName()==s)
                return i;
        }
        return -1;
    }


    private static double DoubleInput(String word) {
        System.out.println("Choose a "+word+":");
        double res= scn.nextDouble();
        scn.nextLine();
        return res;
    }

    public static String StringInput(String word){
        System.out.println("Choose a "+word+":");
        String res= scn.nextLine();
        return res;
    }
    public static int IntInput(String word){
        System.out.println("Choose a "+word+":");
        int res= scn.nextInt();
        scn.nextLine();
        return res;
    }

    private static String[] addArrName(String[] arr, String type) {
        System.out.print("Enter your " + type + " name: ");
        String theName = scn.nextLine();
        if (checkForName(arr, theName)) {
            if (lastPlace(arr) >= arr.length) {
                arr = extendArray(arr);
            }
            arr[lastPlace(arr)] = theName;
        } else {
            System.out.println("the name " + theName + " is already in use, please try again");
            arr = addArrName(arr, type);
        }
        return arr;
    }

    private static int lastPlace(String[] arr) {
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

    private static String[] extendArray(String[] arr) {
        String[] newArr = new String[arr.length * 2];
        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[i];
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
