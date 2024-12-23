public class Reservation {
    private int reservationId;
    private int workspaceId;
    private String customerName;
    private String date;
    private String startTime;
    private String endTime;

    // Constructor
    public Reservation(int reservationId, int workspaceId, String customerName, String date, String startTime, String endTime) {
        this.reservationId = reservationId;
        this.workspaceId = workspaceId;
        this.customerName = customerName;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // Getters
    public int getReservationId() { return reservationId; }
    public int getWorkspaceId() { return workspaceId; }

    @Override
    public String toString() {
        return "Reservation{" +
                "Reservation ID=" + reservationId +
                ", Workspace ID=" + workspaceId +
                ", Customer Name='" + customerName + '\'' +
                ", Date='" + date + '\'' +
                ", Time='" + startTime + " - " + endTime + '\'' +
                '}';
    }
}
