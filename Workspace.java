import java.util.Objects;

public class Workspace {
    private int id;
    private String type;
    private double price;
    private boolean isAvailable;

    // Constructor
    public Workspace(int id, boolean isAvailable, String type, double price) {
        this.id = id;
        this.type = type;
        this.price = price;
        this.isAvailable = isAvailable;
    }

    // Getters and setters
    public int getId() { return id; }
    public boolean isAvailable() { return isAvailable; }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public void setAvailable(boolean available) { isAvailable = available; }

    @Override
    public String toString() {
        return "Workspace{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", isAvailable=" + isAvailable +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Workspace workspace = (Workspace) o;
        return id ==workspace.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}