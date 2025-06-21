//students names: Daniel Liser, Daniel Barabash

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

// small change

public class Main {
    static Scanner scn = new Scanner(System.in);

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Collage collage;
        ObjectInputStream inFile;
        try {
             inFile = new ObjectInputStream(new FileInputStream("Collage.dat"));
            collage=(Collage)inFile.readObject();
            inFile.close();


        } catch (IOException e) {
            System.out.println("Please Choose college name: ");
            String collageName = scn.nextLine();
             collage = new Collage(collageName);

        }

        boolean toContinue = true;
        String val;
        while (toContinue) {
            printMenu();
            val = scn.nextLine();
            switch (val) {
                case "0":
                    toContinue = false;
                    ObjectOutputStream outFile=new ObjectOutputStream(new FileOutputStream("Collage.dat"));
                    outFile.writeObject(collage);
                    outFile.close();

                    break;
                case "1":
                    String name = stringInput("name");

                    while (!collage.checkName(name)) {
                        System.out.println("this name already exists");
                        name = stringInput("name");
                        collage.checkName(name);
                    }
                    String academy = null;
                    int articleSize = 0;
                    ArrayList<String> artArray = new ArrayList<>();
                    String id = stringInput("id");
                    System.out.println("1 - first degree ,2 - second degree ,3 - dr ,4 - professor");
                    int degInt = intInput("degree");
                    while (degInt < 1 || degInt > 4) {
                        System.out.println("this degree does not exist");
                        degInt = intInput("degree");
                    }
                    if (degInt == 3 || degInt == 4) {
                        articleSize = intInput("articles amount");
                        artArray = new ArrayList<>();
                        fillArt(articleSize, artArray);

                    }
                    if (degInt == 4) {
                        academy = stringInput("academy");
                    }

                    String degName = stringInput("degree name");
                    double salary = doubleInput("salary");

                    while (salary < 0) {
                        System.out.println("invalid salary");
                        salary = doubleInput("salary");
                    }
                    String depName = stringInput("department name");

                    collage.lecturerToCollage(name, id, degInt, degName, salary, depName, articleSize, academy, artArray);
                    System.out.println("Lecturer was added");
                    break;
                case "2":
                    try {
                        System.out.println("1 - first/second degree ,2 - dr ,3 - professor");
                        int degTemp = intInput("committee degree level");
                        collage.committeeToCollage(stringInput("committee name"), stringInput("head lecturer name"),degTemp);
                    } catch (CollageException e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                    System.out.println("Committee was added");
                    break;
                case "3":
                    String com = stringInput("committee to add a lecturer");
                    String lecName = stringInput("lecturer name to add");
                    try {
                        collage.lecturerToCommittee(com, lecName);
                    } catch (CollageException e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                    System.out.println("Lecturer was added to the Committee");
                    break;
                case "4":
                    String comUp = stringInput("committee to update: ");
                    String lecNameUpd = stringInput("Lecturer name");
                    try {
                        collage.updateComHead(comUp, lecNameUpd);
                    } catch (CollageException e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                    System.out.println("Committee was updated");
                    break;
                case "5":
                    String comName = stringInput("committee to update");
                    String removeLecName = stringInput("Lecturer to remove");
                    try {
                        collage.removeLecFromCom(comName, removeLecName);
                    } catch (CollageException e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                    System.out.println("lecturer was removed");
                    break;
                case "6":
                    String newDep = stringInput("department name");
                    int studentNum = intInput("number of students");
                    if (studentNum == -1) {
                        break;
                    }
                    while (true) {
                        try {
                            collage.addDepToCollege(studentNum, newDep);
                            break;
                        } catch (CollageException e) {
                            System.out.println(e.getMessage());
                        }
                        newDep = stringInput("department name");
                    }
                    System.out.println("Department was added");
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
                    System.out.println("--------------");
                    System.out.print(collage.showAllLecturers());
                    System.out.println("--------------");
                    break;
                case "10":
                    System.out.println("--------------");
                    System.out.print(collage.showAllCommittees());
                    System.out.println("--------------");

                    break;
                case "11":
                    int depInt = collage.findDepIndex(stringInput("department to update"));
                    int lecInt = collage.findLecIndex(stringInput("lecturer name"));
                    try {
                        collage.updateLecDep(lecInt, depInt);
                    } catch (CollageException e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                    System.out.println("lecturer was added to the department");
                    break;
                case "12":
                    System.out.println();
                    String msg12 = stringInput(" first lecturer (Dr/prof)");
                    String msg13 = stringInput(" second lecturer (Dr/prof)");
                    try {
                        Lecturer lec12 = collage.compareLec(msg12, msg13);
                        System.out.println(lec12.getName() + " has more articles");
                        break;
                    } catch (CollageException e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                case "13":
                    String com1 = stringInput("first committee");
                    String com2 = stringInput("second committee");
                    try {
                        System.out.println(collage.comByNumOfArt(com1, com2, false) + " has more members");
                    } catch (CollageException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case "14":
                    String firstCom = stringInput("first committee");
                    String secCom = stringInput("second committee");
                    try {
                        System.out.println(collage.comByNumOfArt(firstCom, secCom, true) + " has more articles");
                    } catch (CollageException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "15":
                    String cloneCom = stringInput("committee");
                    try {
                        collage.cloneCom(cloneCom);
                    } catch (CollageException e) {
                        System.out.println(e.getMessage());
                        ;
                    }
                    break;


                default:
                    System.out.println("Wrong input");
            }
        }
    }

    private static void fillArt(int articleSize, ArrayList<String> arr) {
        int num = 1;
        for (int i = 0; i < articleSize; i++) {
            arr.add(stringInput("article num [" + num + "]"));
            //arr[i] = stringInput("article num [" + num + "]");
            num++;

        }
    }

    private static double doubleInput(String word) {
        double res = 0;
        boolean running = true;
        while (running) {
            try {
                System.out.println("Choose a " + word + ":");
                res = scn.nextInt();
                running = false;
            } catch (InputMismatchException e) {
                System.out.println("this is not a number");
            }
            scn.nextLine();
        }
        return res;
    }

    public static String stringInput(String word) {
        System.out.println("Choose a " + word + ":");
        return scn.nextLine();
    }

    public static int intInput(String word) throws InputMismatchException {

        int res = 0;
        boolean running = true;
        while (running) {
            try {
                System.out.println("Choose a " + word + ":");
                res = scn.nextInt();
                running = false;
            } catch (InputMismatchException e) {
                System.out.println("this is not a number");
            }
            scn.nextLine();
        }
        return res;
    }

    private static void printMenu() {
        System.out.println("0 - Exit");
        System.out.println("1- Add lecturer to the collage");
        System.out.println("2- Add committee to the collage");
        System.out.println("3- Add a member to committee");
        System.out.println("4- Set head of committee");
        System.out.println("5- Remove member from committee");
        System.out.println("6- Add department to the collage");
        System.out.println("7- Show average salaries of all lecturers");
        System.out.println("8- Show average salaries of all lecturers at specific department");
        System.out.println("9- Show info about lecturers");
        System.out.println("10- Show info about all committees");
        System.out.println("11- Add lecturer to department");
        System.out.println("12- Compare Dr/prof by their number of articles");
        System.out.println("13- Compare committees by number of lecturers");
        System.out.println("14- Compare committees by number of articles written");
        System.out.println("15- clone committee");
    }
}

