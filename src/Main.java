//students names: Daniel Liser, Daniel Barabash

import java.util.Scanner;

// small change

public class Main {
    static Scanner scn = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Please Choose college name: ");
        String collageName = scn.nextLine();
        Collage collage = new Collage(collageName);
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
                    String name = stringInput("name");

                    while (!collage.checkName(name)) {
                        System.out.println("this name already exists");
                        name = stringInput("name");
                        collage.checkName(name);
                    }

                    String id = stringInput("id");
                    System.out.println("1 - first degree ,2 - second degree ,3 - dr ,4 - professor");
                    int degInt = intInput("degree");
                    String degName = stringInput("degree name");
                    double salary = doubleInput("salary");

                    if(salary<0){
                        System.out.println("invalid salary");
                        continue;
                    }

                    String depName = stringInput("department name");

                    collage.lecturerToCollage(name, id, degInt, degName, salary, depName);
                    System.out.println("Lecturer was added");
                    break;
                case "2":
                    int msg2=collage.committeeToCollage(stringInput("committee name"),stringInput("head lecturer id"));
                    if(msg2==1){
                        System.out.println("Lecturer doesn't meet the criterion");
                    }
                    else if(msg2==2){
                        System.out.println("Committee is already in the system");
                    }
                    else if(msg2==5){
                        System.out.println("Lecturer doesn't exist");
                    }
                    else{
                        System.out.println("Committee was added");
                    }
                    break;
                case "3":
                    String com=stringInput("committee to add a lecturer");
                    String lecName=stringInput("lecturer name to add");
                    int msg3=collage.lecturerToCommittee(com,lecName);
                    if (msg3==1){
                        System.out.println("This lecturer already is the head of the committee");
                    } else if (msg3==2) {
                        System.out.println("This lecturer is already a member of the committee");
                    }
                    else if(msg3==3){
                        System.out.println("Lecturer was added to the committee");
                    }
                    else if(msg3==4){
                        System.out.println("Committee name was not found");
                    }
                    else if(msg3==5){
                        System.out.println("Lecturer name was not found");
                    }
                    else {
                        System.out.println("committee name was not found \n Lecturer name was not found");
                    }
                    break;
                case "4":
                    String comUp= stringInput("committee to update: ");
                    String lecNameUpd=stringInput("Lecturer name");

                    int msg4=collage.updateComHead(comUp,lecNameUpd);
                    if(msg4==1){

                    }
                    else if(msg4==2){

                    }
                    else if(msg4==3){

                    }
                    else if(msg4==4){

                    }
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
                    System.out.println("Choose a department");
                    String res = scn.nextLine();
                    if (collage.AddDepartmentToLecturer(res) != null) {
                        System.out.print("Average department salary is: ");
                        System.out.println(collage.showAvgSalPerDep(collage.AddDepartmentToLecturer(res)));
                    }
                    System.out.println("Department doesn't exist");
                    break;
                case "9":
                    collage.showAllLecturers();
                    break;
                case "10":
                    collage.showAllCommittees();
                    break;
                case "11":
                    int depInt = collage.findDepIndex(stringInput("department to update"));
                    int lecInt = collage.findLecIndex(stringInput("lecturer name"));
                    int msg11=collage.updateLecDep(lecInt,depInt);
                    if(msg11==1){
                        System.out.println("Department doesn't exist");
                    }
                    else if(msg11==2){
                        System.out.println("Lecturer doesn't exist");
                    }
                    else if(msg11==3){
                        System.out.println("Department doesn't exist \n Lecturer doesn't exist");
                    }
                    else if(msg11==4){
                        System.out.println("Lecturer is already a member of the department");
                    }
                    else{
                        System.out.println("Lecturer was added to the department");
                    }
                    break;
                case "12":
                    collage.printDep();
                    break;
                default:
                    System.out.println("Wrong input");
            }
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
        return scn.nextLine();
    }

    public static int intInput(String word) {
        System.out.println("Choose a " + word + ":");
        int res = scn.nextInt();
        scn.nextLine();
        return res;
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