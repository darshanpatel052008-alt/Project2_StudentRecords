public class Student {
    private int id;
    private String name;
    private double gpa;
    private String major;

    public Student(int id, String name, double gpa, String major) {
        this.id = id;
        this.name = name;
        setGpa(gpa); 
        this.major = major;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public double getGpa() { return gpa; }
    public String getMajor() { return major; }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setMajor(String major) { this.major = major; }
     
    public void setGpa(double gpa) {
        if (gpa >= 0.0 && gpa <= 4.0) {
            this.gpa = gpa;
        } else {
            throw new IllegalArgumentException("GPA must be between 0.0 and 4.0");
        }
    }

    
    @Override
    public String toString() {
        return "ID: " + id + " | Name: " + name + " | Major: " + major + " | GPA: " + gpa;
    }

    
    public String toCsvString() {
        return id + "," + name + "," + gpa + "," + major;
    }
}

























}