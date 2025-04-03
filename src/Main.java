//students names: Daniel Liser, Daniel Barabash

import java.util.Scanner;

public class Main {
    static Scanner scn = new Scanner(System.in);
    private static String[] lecturers = new String[1];
    private static String[] committee = new String[1];
    private static String[] studyDepartment = new String[1];

    public static void main(String[] args) {
        System.out.println("Please Choose college name: ");
        String collageName = scn.nextLine();
        boolean toContinue = true;
        char val;
        while (toContinue) {
            printMenu();
            val = scn.nextLine().charAt(0);
            switch (val) {
                case '0':
                    toContinue = false;
                    break;
                case '1':
                    lecturers = addArrName(lecturers, "Lecture");
                    break;
                case '2':
                    committee = addArrName(committee, "Committee");
                    break;
                case '3':
                    studyDepartment = addArrName(studyDepartment, "Study Department");
                    break;
                case '4':
                    lectureToCommittee();
                    break;
                case '5':
                    //empty for now
                    break;
                case '6':
                    //empty for now
                    break;
                case '7':
                    System.out.println("Lecturers:");
                    printStringArray(lecturers);
                    break;
                case '8':
                    System.out.println("Committees:");
                    printStringArray(committee);
                default:
                    System.out.println("Wrong input");
            }
        }
    }

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

    private static void printMenu() {
        System.out.println("0 - Exit");
        System.out.println("1 - Add a lecture");
        System.out.println("2 - Add a committee");
        System.out.println("3 - Add a study department");
        System.out.println("4 - Add lecture to committee");
        System.out.println("5 - Average lecturers salary");
        System.out.println("6 - Average lecturers salary in a department");
        System.out.println("7 - Show the lecturers information");
        System.out.println("8 - Show the committee");
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