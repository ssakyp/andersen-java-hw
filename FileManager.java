import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class FileManager {
    private String filePath;

    public FileManager(String filePath) {this.filePath = filePath;}

    // read from file
    public <T> Set<T> readItems (Class<T> clazz) {
        Set<T> itemSet = new HashSet<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if(clazz == Workspace.class) {
                    int id = Integer.parseInt(fields[0]);
                    String type = fields[1];
                    double price = Double.parseDouble(fields[2] + "." + fields[3]);
                    boolean isAvailable = Boolean.parseBoolean(fields[4]);
                    itemSet.add(clazz.cast(new Workspace(id, isAvailable, type, price)));
                }

            }
        } catch (IOException e) {
            System.out.println("Error while reading file");
        }
        return itemSet;
    }

    // writing to the file
    public <T> void writeItems(Set<T> itemSet, Class<T> itemType) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath))){
            for(T item : itemSet) {
                if(itemType == Workspace.class) {
                    Workspace workspace = (Workspace) item;
                    String line = String.format("%d,%s,%.2f,%b",
                            workspace.getId(),
                            workspace.getType(),
                            workspace.getPrice(),
                            workspace.isAvailable());
                    bufferedWriter.write(line);
                    bufferedWriter.newLine();
                }
            }
            System.out.println("Successfully saved workspaces");
        } catch (IOException e) {
            System.out.println("Error while saving workspaces " + e.getMessage());
        }
    }
}
