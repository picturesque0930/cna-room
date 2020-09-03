package ohcna;

public class RoomChangeed extends AbstractEvent {

    private Long id;
    private String name;
    private Long floor;
    private String status;

    public RoomChangeed(){
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    // public String getFloor() {
    //     return floor;
    // }

    // public void setFloor(String floor) {
    //     this.floor = floor;
    // }

    public Long getFloor() {
        return floor;
    }

    public void setFloor(Long floor) {
        this.floor = floor;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
