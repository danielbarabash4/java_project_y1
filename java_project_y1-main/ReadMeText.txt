================================================================
ReadMe.txt
================================================================

----------------------------------------------------------------
SUBMITTERS
----------------------------------------------------------------
Daniel Liser          ID: 325100196
Daniel Barabash  ID: 322272683
Idan Amrani        ID: 322205808

----------------------------------------------------------------
ABOUT THE SOFTWARE
----------------------------------------------------------------
Name: College Management System

Description:
A management system for a college that allows managing
lecturers, committees, and departments. The system supports adding,
updating, and removing entities, as well as comparing and sorting
them by various criteria. Data is saved and loaded from a file 
so that information persists between runs.

----------------------------------------------------------------
FEATURES / FUNCTIONS
----------------------------------------------------------------

1.  Add a lecturer to the college
      Supports three types: Lecturer, Doctor, Professor.
      Doctor and Professor include articles list.
      Professor also includes academy name.

2.  Add a committee to the college
      A committee must have a head lecturer who is a Doctor or Professor.
      Supports three committee types by lecturer level:
      Lecturer-level, Doctor-level, Professor-level.

3.  Add a member to a committee
      Adds a lecturer to a committee.
      The lecturer type must match the committee type.

4.  Set head of committee
      Updates the head of an existing committee.
      New head must be a Doctor or Professor.

5.  Remove a member from a committee
      Removes a lecturer from a committee's member list.

6.  Add a department to the college
      Creates a new department with a name and number of students.

7.  Show average salary of all lecturers
      Calculates and displays the average salary across all lecturers.

8.  Show average salary by department
      Calculates and displays the average salary of lecturers
      in a specific department.

9.  Show info about all lecturers
      Displays all lecturers with their details.
      Supports sorting by:
        1 - Unsorted
        2 - Sort by name
        3 - Sort by salary
        4 - Sort by degree

10. Show info about all committees
      Displays all committees with their details.
      Supports sorting by:
        1 - Unsorted
        2 - Sort by name
        3 - Sort by number of members

11. Add lecturer to department
      Assigns an existing lecturer to an existing department.

12. Compare two doctors/professors by number of articles
      Returns which of the two has more articles.

13. Compare two committees by number of members
      Returns which committee has more members.

14. Compare two committees by number of articles
      Returns which committee has more total articles
      written by its Doctor/Professor members.

15. Clone a committee
      Creates a copy of an existing committee with "-new" appended
      to its name, and updates all cloned members accordingly.

0.  Exit
      Saves all data to Collage.dat and exits the program.

----------------------------------------------------------------
HOW TO RUN
----------------------------------------------------------------

Requirements:
  - Java JDK 8 or higher
  - Any Java IDE (IntelliJ, Eclipse) or command line

Steps:
  1. Open the project in your IDE or navigate to the project folder.
  2. Compile all .java files.
  3. Run Main.java as the entry point.
  4. On first run, you will be asked to enter a college name.
     On subsequent runs, data will be loaded automatically from Collage.dat.

----------------------------------------------------------------
SPECIAL NOTES
----------------------------------------------------------------

- Data is persisted using Java Serialization (Collage.dat).
  If you want to start fresh, delete the Collage.dat file.

- All collections in the system use HashSet (no List implementations).
  Printing is done exclusively via Iterator.

- Sorted printing uses a temporary TreeSet with a lambda Comparator
  based on the user's chosen criterion.

- A lecturer name must be unique in the system.

- Only Doctors and Professors can be head a committee.

- A lecturer can only be added to a committee if their type
  matches the committee's defined type.

----------------------------------------------------------------
SOLID PRINCIPLES REFACTORING
----------------------------------------------------------------

S - Single Responsibility Principle:
  - Collage.java: Extracted private createLecturer() method.
    Lecturer creation logic (the if/else on degree type) is now
    isolated from lecturerToCollage(), which only orchestrates.
  - Collage.java: Extracted private createCommittee() method.
    Committee creation logic is now isolated from committeeToCollage().
  - SortComByArc.java: Renamed runOnArray() to sumArticles() and made
    it private. A Comparator has one job: comparing. The helper is an
    internal implementation detail and must not be part of the public API.

O - Open/Closed Principle:
  - Collage.java: createLecturer() and createCommittee() centralize
    the branching for new types. Adding a new degree or committee level
    requires changing only these private factory methods, not the public
    orchestrating methods.

L - Liskov Substitution Principle:
  - Lecturer.equals(): Added instanceof check before casting.
    Previously a ClassCastException could be thrown when comparing
    against a non-Lecturer object.
  - Department.equals(): Same instanceof fix applied.
  - Committee.equals(): Replaced getClass() != o.getClass() with
    instanceof check for correct substitutability. Also removed the
    lecSize comparison that was always 0 == 0 (dead field never
    maintained), which made the equals contract incorrect.

I - Interface Segregation Principle:
  - Committee.java: Removed the dead private int lecSize field.
    getLecSize() already derived its value from committeeMembers.size();
    the field was never maintained and polluted the class state.
  - Lecturer.java: Removed the dead private int comSize field.
    It was declared but never set or used anywhere in the class.
  - Department.java: Removed the unused private lecInDep(Lecturer)
    method. Unused private methods widen the implementation surface
    without serving any consumer.

D - Dependency Inversion Principle:
  - SortComByArc.java: The internal sumArticles() helper is now
    private, keeping the concrete Doctor dependency contained and
    not exposed through the public Comparator interface.

Additional improvements:
  - All string-building loops across the project replaced with
    StringBuilder (Collage, Lecturer, Department, Committee, Doctor)
    to avoid repeated String object allocation in loops.
  - Removed unused static Scanner field from Collage.java.
  - Removed unused Iterator import from Department.java after
    toString() was updated (re-added when iterator was restored).

================================================================