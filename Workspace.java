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
}