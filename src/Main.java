import java.sql.SQLOutput;
import java.util.Scanner;
//Daniel Liser and Daniel Barabash
public class Main {
    static Scanner s = new Scanner(System.in);
    static String[] lecturers = new String[1];
    static String[] committee = new String[1];
    static String[] departments = new String[1];

    public static void main(String[] args) {
        System.out.println("Pick the college name: ");
        String collegeName = s.nextLine();
        boolean run = true;
        int val;//User's choice
        while (run) {
            printMenu();
            val = s.nextInt();
            s.nextLine();  //Clears the buffer
            switch (val){
                case 0:
                    run=false;
                    break;
                case 1:
                    lecturers = addtoArr(lecturers,"lecturer");
                    break;
                case 2:
                    committee = addtoArr(committee,"committee");
                    break;
                case 3:
                    departments = addtoArr(departments,"department");
                    break;
                case 4:
                    lectureToCommitee();
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    System.out.println("lecturers: ");
                    printArr(lecturers);
                    break;
                case 8:
                    System.out.println("commites: ");
                    printArr(committee);
                    break;
                default:
                    System.out.println("wrong input");
                    break;
            }
        }
    }

    private static void lectureToCommitee() {// Checks if a lecturer or a committee name exists
        System.out.println("Pick a lecturer name:");
        String lecturer = s.nextLine();
        System.out.println("Pick a commitee name:");
        String committeePick = s.nextLine();
        if (!isInsideArr(lecturer, lecturers)) {
            System.out.println("lecturer name does'nt exist");
        }
        if (!isInsideArr(committeePick, committee)) {
            System.out.println("committee name does'nt exist");
        }
    }

    private static String[] addtoArr(String[] arr,String type) {// Add a value to the array
        while (true) {
            System.out.println("Choose a "+type+" to add:");
            String name = s.nextLine();
            if (isInsideArr(name, arr)) {
                System.out.println(type+" name is already in use, try again: ");
                continue;
            }
            if (isFull(arr)) {
                arr = extendArr(arr);
            }
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == null) {
                    arr[i] = name;
                    break;
                }
            }
            break;
        }
        return arr;
    }

    private static boolean isInsideArr(String name, String[] arr) {// Check if the val is inside the arr
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == null)
                continue;
            if (arr[i].equals(name))
                return true;
        }
        return false;
    }

    private static String[] extendArr(String[] arr) {// Make the array 2 times bigger
        String[] temp = new String[arr.length * 2];
        for (int i = 0; i < arr.length; i++) {

            temp[i] = arr[i];
        }
        return temp;
    }

    private static boolean isFull(String[] arr) {// Check if the array is full
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == null) {
                return false;
            }
        }
        return true;
    }

    private static void printArr(String[] arr) {// prints the array without the nulls
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == null)
                break;
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    private static void printMenu() {// prints the menu options
        System.out.println("0- Exit");
        System.out.println("1- Add lecturer");
        System.out.println("2- Add committee");
        System.out.println("3- Add learning department");
        System.out.println("4- Lecturer placement to committee");
        System.out.println("5- Show salaries avg");
        System.out.println("6- Show salaries avg of spesific department");
        System.out.println("7- Show lecturers info");
        System.out.println("8- Show committee info");
    }
}


