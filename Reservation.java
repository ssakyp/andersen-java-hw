public class Reservation {
    private int reservationId;
    private int workspaceId;
    private String clientName;
    private String date;
    private String startTime;
    private String endTime;

    public Reservation(int reservationId, int workspaceId, String clientName, String date, String startTime, String endTime) {
        this.reservationId = reservationId;
        this.workspaceId = workspaceId;
        this.clientName = clientName;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }


    public int getReservationId() {
        return reservationId;
    }

    public int getWorkspaceId() {
        return workspaceId;
    }

    public String getClientName() {
        return clientName;
    }

    public String getDate() {
        return date;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "Reservation ID=" + reservationId +
                ", Workspace ID=" + workspaceId +
                ", Client Name='" + clientName + '\'' +
                ", Date='" + date + '\'' +
                ", Time='" + startTime + " - " + endTime + '\'' +
                '}';
    }
}
