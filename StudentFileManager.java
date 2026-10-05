import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentFileManager {

    private String filePath;

    public StudentFileManager(String filePath) {
        this.filePath = filePath; 
    }

    public List<Student> loadStudents() {
        File file = new File(filePath);
        List<Student> students = new ArrayList<>();

        if(!file.exists()) {
            return students; 
        }

        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine(); // Skip CSV header

            while((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                if(parts.length != 4) {
                    System.out.println("Skipping malformed row (invalid number of fields): " + line);
                    continue; 
                }
                
                try {
                    int id = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    double gpa = Double.parseDouble(parts[2].trim());
                    String major = parts[3].trim();

                    students.add(new Student(id, name, gpa, major));
                }
                catch(NumberFormatException e) {
                    System.out.println("Skipping malformed number in row: " + line);
                }
                catch(IllegalArgumentException e) {
                    System.out.println("Skipping invalid student attributes: " + e.getMessage());
                }
            }
        }
        catch(IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        
        return students;
    }

    public void saveStudents(List<Student> students) {
        try(FileWriter writer = new FileWriter(filePath)) {
            writer.write("id,name,gpa,major\n");

            for(Student s : students) {
                writer.write(s.toCsvString() + "\n"); // Added \n here
            }

            System.out.println("Data saved successfully.");
        }
        catch(IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }
}