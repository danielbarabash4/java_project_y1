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
                    String academy = null;
                    int articleSize=0;
                    String id = stringInput("id");
                    System.out.println("1 - first degree ,2 - second degree ,3 - dr ,4 - professor");
                    int degInt = intInput("degree");
                    if(degInt==3|| degInt==4){
                        articleSize=intInput("articles amount");
                        String artArray[]=new String[articleSize];
                        artArray=fillArt(articleSize,artArray);

                    }
                    if(degInt == 4){
                        academy = stringInput("academy");
                    }

                    String degName = stringInput("degree name");
                    double salary = doubleInput("salary");

                    if (salary < 0) {
                        System.out.println("invalid salary");
                        continue;
                    }

                    String depName = stringInput("department name");

                    collage.lecturerToCollage(name, id, degInt, degName, salary, depName,articleSize,academy);
                    System.out.println("Lecturer was added");
                    break;
                case "2":
                    try {
                        collage.committeeToCollage(stringInput("committee name"), stringInput("head lecturer name"));
                    } catch (CollageException e){
                        System.out.println(e.getMessage());
                        break;
                    }
                    System.out.println("Committee was added");
                    break;
                case "3":
                    String com = stringInput("committee to add a lecturer");
                    String lecName = stringInput("lecturer name to add");
                    int msg3 = collage.lecturerToCommittee(com, lecName);
                    if (msg3 == 1) {
                        System.out.println("This lecturer already is the head of the committee");
                    } else if (msg3 == 2) {
                        System.out.println("This lecturer is already a member of the committee");
                    } else if (msg3 == 3) {
                        System.out.println("Lecturer was added to the committee");
                    } else if (msg3 == 4) {
                        System.out.println("Committee name was not found");
                    } else if (msg3 == 5) {
                        System.out.println("Lecturer name was not found");
                    } else {
                        System.out.println("committee name was not found \n Lecturer name was not found");
                    }
                    break;
                case "4":
                    String comUp = stringInput("committee to update: ");
                    String lecNameUpd = stringInput("Lecturer name");

                    int msg4 = collage.updateComHead(comUp, lecNameUpd);
                    if (msg4 == 1) {
                        System.out.println("Committee was updated");
                    } else if (msg4 == 2) {
                        System.out.println("Lecturer degree is not high enough");

                    } else if (msg4 == 3) {
                        System.out.println("Lecturer was not found");

                    } else if (msg4 == 4) {
                        System.out.println("Lecturer was not found \nCommittee was not found");
                    } else if (msg4 == 5) {
                        System.out.println("Committee was not found");
                    } else {
                        System.out.println("Lecturer is already the head of the committee");
                    }
                    break;
                case "5":
                    String comName = stringInput("committee to update");
                    String removeLecName = stringInput("Lecturer to remove");
                    int msg5 = collage.removeLecFromCom(comName, removeLecName);
                    if (msg5 == 1) {
                        System.out.println("Lecturer was removed");
                    } else if (msg5 == 2) {
                        System.out.println("Committee was not found");
                    } else if (msg5 == 3) {
                        System.out.println("Lecturer is not a member of the committee");
                    } else if (msg5 == 4) {
                        System.out.println("Lecturer was not found");
                    } else {
                        System.out.println("Lecturer was not found \nCommittee was not found");
                    }
                    break;
                case "6":
                    while (true) {
                        System.out.println("choose department name: ");
                        String newDep = scn.nextLine();
                        int res6=collage.addDepToCollege(intInput("number of sutdents"), newDep);
                        if(res6==2){
                            System.out.println("Name already exists");
                        }
                        else{
                            break;
                        }
                    }
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
                    int msg11 = collage.updateLecDep(lecInt,depInt);
                    if (msg11 == 1) {
                        System.out.println("Department doesn't exist");
                    } else if (msg11 == 2) {
                        System.out.println("Lecturer doesn't exist");
                    } else if (msg11 == 3) {
                        System.out.println("Department doesn't exist \n Lecturer doesn't exist");
                    } else if (msg11 == 4) {
                        System.out.println("Lecturer is already a member of the department");
                    } else {
                        System.out.println("Lecturer was added to the department");
                    }
                    break;
                case "12":
                    System.out.println();
                    String msg12=stringInput(" first lecturer (Dr/prof)");
                    String msg13=stringInput(" second lecturer (Dr/prof)");
                    try{
                        Lecturer lec12=collage.compareLec(msg12,msg13);
                        System.out.println(lec12.getName()+ " has more articles");
                        break;
                    }
                    catch (DoesntExistException e){
                        System.out.println("Lecturer was not found");
                        break;
                    }
                    catch (NotProfDocException e){
                        System.out.println("Lecturer degree is not high enough");
                        break;
                    }
                case "13":
                    String com1=stringInput("first committee");
                    String com2=stringInput("second committee");
                    System.out.println(collage.comByNumOfLec(com1,com2)+" has more members");
                default:
                    System.out.println("Wrong input");
            }
        }
    }

    private static String[] fillArt(int articleSize,String[]arr) {
        int num=1;
        for (int i = 0; i <articleSize ; i++) {
            arr[i]=stringInput("article num ["+num+"]" );
            num++;

        }
        return arr;
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

    }
}

