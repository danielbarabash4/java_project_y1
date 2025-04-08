import java.util.Scanner;

public class Collage {
    private String name;
    static Scanner scn = new Scanner(System.in);
    private static String[] lecturers = new String[1];
    private static String[] committee = new String[1];
    private static String[] studyDepartment = new String[1];

    private static void lectureToCommittee() {
        String lecName, comName;
        System.out.print("Choose a lecture: ");
        lecName = scn.nextLine();
        System.out.print("Choose a committee: ");
        comName = scn.nextLine();



        if (checkForName(lecturers, lecName)) {
            System.out.println("this lecture does not exist");
        }
        if (checkForName(committee, comName)) {
            System.out.println("this committee does not exist");
        }
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
