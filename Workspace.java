public class Workspace {
    private int id;
    private String type;
    private double price;
    private boolean isAvaliable;

    public Workspace(int id, boolean isAvaliable, String type, double price) {
        this.id = id;
        this.isAvaliable = isAvaliable;
        this.type = type;
        this.price = price;
    }

    public int getId() {
        return id;
    }


    public String getType() {
        return type;
    }


    public double getPrice() {
        return price;
    }


    public boolean isAvailable() {
        return isAvaliable;
    }

    public void setAvaliable(boolean avaliable) {
        isAvaliable = avaliable;
    }

    @Override
    public String toString() {
        return "Workspace{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", isAvaliable=" + isAvaliable +
                '}';
    }
}
