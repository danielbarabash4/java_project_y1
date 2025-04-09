import java.util.Scanner;

public class Collage {
    private String name;
    static Scanner scn = new Scanner(System.in);
    private static Lecturer[] lecturers = new Lecturer[1];
    private static Committee[] committee = new Committee[1];
    private static Department[] studyDepartment = new Department[1];

    private static void lecturerToCollage() {
        Lecturer lecturer=new Lecturer(StringInput("lecturer name"),IntInput("id"),StringInput("degree"),
                StringInput("degree name"),DoubleInput("lecturer salary"),AddDepartmentToLecturer("Department name:"));
       if( isLecturerExist(lecturer)){
           System.out.println("Lecturer is already in the system");
       }
       else {
           addLecturerToDepartment(lecturer);
           lecturer.getDepartment().AddNewLecturer(lecturer);
       }
    }

    private static boolean isLecturerExist(Lecturer lecturer) {
        for (int i = 0; i < lecturers.length; i++) {
            if(lecturers[i].equals(lecturer))
                return true;
        }
        return false;
    }

    private static Lecturer[] addLecturerToDepartment(Lecturer lecturer) {
        int temp=lecturers.length;
        for (int i=0;i<lecturers.length;i++){
            if(lecturers[i]==null){
                lecturers[i]=lecturer;
                return lecturers;
            }
        }
        lecturers=extendLecturer();
        lecturers[temp+1]=lecturer;

        return lecturers;
    }

    private static Department AddDepartmentToLecturer(String s) {
        if(findDepIndex(s)!=-1){
            return studyDepartment[findDepIndex(s)];
        }
        else{
            System.out.println("Department does'nt exist");
            return null;
        }
    }

    private static int findDepIndex( String s) {
        for(int i=0;i<studyDepartment.length;i++){
            if(studyDepartment[i]==null)
                continue;
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
