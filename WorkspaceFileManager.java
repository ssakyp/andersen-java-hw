import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class WorkspaceFileManager {
    private String filePath;

    public WorkspaceFileManager(String filePath) {this.filePath = filePath;}

    // read from file
    public List<Workspace> readWorkspaces () {
        List<Workspace> workspaceList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                int id = Integer.parseInt(fields[0]);
                String type = fields[1];
                double price = Double.parseDouble(fields[2]);
                boolean isAvailable = Boolean.parseBoolean(fields[3]);
                workspaceList.add(new Workspace(id, isAvailable, type, price));
            }
        } catch (IOException e) {
            System.out.println("Error while reading file");
        }
        return workspaceList;
    }

    // writing to the file
    public void writeWorkspaces(List<Workspace> workspaceList){
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath))){
            for(Workspace workspace : workspaceList) {
                String line = String.format("%d,%s,%.2f,%b",
                        workspace.getId(),
                        workspace.getType(),
                        workspace.getPrice(),
                        workspace.isAvailable());
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
            System.out.println("Successfully saved workspaces");
        } catch (IOException e) {
            System.out.println("Error while saving workspaces " + e.getMessage());
        }
    }
}
