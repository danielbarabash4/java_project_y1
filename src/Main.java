//students names: Daniel Liser, Daniel Barabash

import java.util.Scanner;

public class Main {
    static Scanner scn = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("Please Choose college name: ");
        String collageName = scn.nextLine();
        Collage collage=new Collage(collageName);
        boolean toContinue = true;
        String val;
        while (toContinue) {
            printMenu();
            val = scn.nextLine();
            switch (val) {
                case "0":
                    toContinue = false;
                    break;
                case "1":
                    collage.lecturerToCollage();
                    break;
                case "2":
                    collage.committeeToCollage();
                    break;
                case "3":
                    collage.lecturerToCommittee();
                    break;
                case "4":
                    collage.updateComHead();
                    break;
                case "5":
                    collage.removeLecFromCom();
                    break;
                case "6":
                    collage.addDepToCollege();
                    break;
                case "7":
                    System.out.print("Average salary is: ");
                    System.out.println(collage.showAvgSalPerDep(null));
                    break;
                case "8":
                    System.out.println("Choose a deparment");
                    String res=scn.nextLine();
                    if(collage.AddDepartmentToLecturer(res)!=null){
                        System.out.print("Average department salary is: ");
                        System.out.println(collage.showAvgSalPerDep(collage.AddDepartmentToLecturer(res)));
                    }
                    break;
                case "9":
                    collage.showAllLecturers();
                    break;
                case "10":
                    collage.showAllCommittees();
                    break;
                case "11":
                    collage.AddLecToDep();
                    break;
                case  "12":
                    collage.printDep();
                    break;
                default:
                    System.out.println("Wrong input");
            }
        }
    }

    private static void printMenu() {
        System.out.println("0 - Exit");
        System.out.println("1- Add lecturer to the collage");
        System.out.println("2- Add committee to the collage");
        System.out.println("3- Add member to committee");
        System.out.println("4- Set head of committee");
        System.out.println("5- Remove member from committee");
        System.out.println("6- Add department to the collage");
        System.out.println("7- Show average salaries of all lecturers");
        System.out.println("8- Show average salaries of all lecturers at specific department");
        System.out.println("9- Show info about lecturers");
        System.out.println("10- Show info about all committees");
        System.out.println("11- Add lecturer to department");
    }

//    private static int lastPlace(String[] arr) {
//        int i = 0;
//        for (; i < arr.length && arr[i] != null; ) {
//            i++;
//        }
//        return i;
//    }
//
//    private static boolean checkForName(String[] arr, String name) {
//        for (int i = 0; i < arr.length; i++) {
//            if (arr[i] != null && arr[i].equals(name)) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    private static String[] extendArray(String[] arr) {
//        String[] newArr = new String[arr.length * 2];
//        for (int i = 0; i < arr.length; i++) {
//            newArr[i] = arr[i];
//        }
//        return newArr;
//    }
//
//    private static void printStringArray(String[] arr) {
//        for (int i = 0; i < arr.length; i++) {
//            if (arr[i] != null) {
//                System.out.print(arr[i] + " ");
//            }
//        }
//        System.out.println();
//    }
}