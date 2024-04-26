package entities.review;

import entities.other.UserOpinion;

public class Review extends UserOpinion {

    private String content;

    public Review(String user, String book, String content) {
        super(user, book);
        setContent(content);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String newContent) {
        if (newContent != null) {
            this.content = newContent;
        }
    }
}
