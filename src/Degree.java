public enum Degree {
    first,
    second,
    dr,
    prof;

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



