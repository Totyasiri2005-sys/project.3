package project3;

public class Undergraduate extends Student {

    public Undergraduate(String id, String name, int numCourses) {
        super(id, name, numCourses);
    }

    @Override
    public double getPassingMark() {
        return 60;
    }
}                      