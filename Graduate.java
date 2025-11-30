package project3;

public class Graduate extends Student {

    public Graduate(String id, String name, int numCourses) {
        super(id, name, numCourses);
    }

    @Override
    public double getPassingMark() {
        return 80;
    }
}