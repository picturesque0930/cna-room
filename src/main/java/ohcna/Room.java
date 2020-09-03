package ohcna;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="Room_table")
public class Room {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String name;
    private Long floor;
    private String status;

    @PostPersist
    public void onPostPersist(){
        //이벤트 인스턴스 생성
        RoomCreated roomCreated = new RoomCreated();
        //속성값 할당
        BeanUtils.copyProperties(this, roomCreated);
        roomCreated.publishAfterCommit();

        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

        // 포인트 구현하지 않음.
        // ohcna.external.Point point = new ohcna.external.Point();
        // // mappings goes here
        // RoomApplication.applicationContext.getBean(ohcna.external.PointService.class)
        //     .pointCreate(point);


    }

    @PostUpdate
    public void onPostUpdate(){
        // 이벤트 인스턴스 생성
        if(this.getStatus().equals("Y"))
        {
        RoomChangeed roomChangeed = new RoomChangeed();
        //속성값 할당
        BeanUtils.copyProperties(this, roomChangeed);
        roomChangeed.publishAfterCommit();
        }
        
        // disable
        else if(this.getStatus().equals("N"))
        {
             RoomDeleted roomDeleted = new RoomDeleted();

        // 속성값 할당
        BeanUtils.copyProperties(this, roomDeleted);
        roomDeleted.publishAfterCommit();

        RoomApplication.applicationContext.getBean(ohcna.external.BookingService.class)
                .bookingCancel(this.getFloor());
        }
         // Exception Error
        else{
            System.out.println("Error");
        }


    }

    // @PreRemove
    // public void onPreRemove(){
    //     // 이벤트 인스턴스 생성
    //     RoomDeleted roomDeleted = new RoomDeleted();

    //     // 속성값 할당
    //     BeanUtils.copyProperties(this, roomDeleted);
    //     RoomApplication.applicationContext.getBean(ohcna.external.BookingService.class)
    //             .bookingCancel(this.getFloor());
    //     roomDeleted.publishAfterCommit();

                

    // }

 



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
