// Idan Amrani : 322205808
// Daniel Barbash : 322272683
// Daniel Liser : 325100196

import java.util.HashSet;

public enum Degree {
    first {
        @Override
        public Lecturer createLecturer(String name, String id, String degName, double salary, int articleSize, String academy, HashSet<String> artSet) {
            return new Lecturer(name, id, Degree.first, degName, salary, null);
        }
    },
    second {
        @Override
        public Lecturer createLecturer(String name, String id, String degName, double salary, int articleSize, String academy, HashSet<String> artSet) {
            return new Lecturer(name, id, Degree.second, degName, salary, null);
        }
    },
    dr {
        @Override
        public Lecturer createLecturer(String name, String id, String degName, double salary, int articleSize, String academy, HashSet<String> artSet) {
            return new Doctor(name, id, Degree.dr, degName, salary, null, articleSize, artSet);
        }
    },
    prof {
        @Override
        public Lecturer createLecturer(String name, String id, String degName, double salary, int articleSize, String academy, HashSet<String> artSet) {
            return new Professor(name, id, Degree.prof, degName, salary, null, articleSize, academy, artSet);
        }
    };

    public abstract Lecturer createLecturer(String name, String id, String degName, double salary, int articleSize, String academy, HashSet<String> artSet);

    public static Degree degFromInt(int degNum) {
        switch (degNum) {
            case 1:
                return first;
            case 2:
                return second;
            case 3:
                return dr;
            case 4:
                return prof;
            default:
                return null;
        }
    }
}
