package entities.other;

public class UserOpinion {
    private String user;
    private String book;

    public UserOpinion(String user, String book) {
        this.user = user;
        this.book = book;
    }

    public String getWriter() {
        return user;
    }

    public String getBookTo() {
        return book;
    }
}
