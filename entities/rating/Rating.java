package entities.rating;

import entities.other.UserOpinion;

public class Rating extends UserOpinion {

    private double rate;

    public Rating(String user, String book, double rate) {
        super(user, book);
        this.rate = rate;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double newRate) {
        this.rate = newRate;
    }
}
