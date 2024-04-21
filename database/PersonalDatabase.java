import java.time.LocalDate;

import javax.swing.JFrame;

public class PersonalDatabase extends GeneralDatabase {
    JFrame f=new JFrame();
    String status = "Not started";
    Double timeSpent;
    LocalDate startTime, endTime;
    String userRating = "Add rating";
    String userReview = "Add review";
    Integer individualRating;
}
