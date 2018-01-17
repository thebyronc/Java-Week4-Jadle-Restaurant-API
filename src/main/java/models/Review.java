package models;

public class Review {

    private int id;
    private String writtenBy;
    private int rating;
    private String content;
    private int restaurantId;

    public Review(String writtenBy, int rating, String content,int restaurantId) {
        this.writtenBy = writtenBy;
        this.rating = rating;
        this.content = content;
        this.restaurantId = restaurantId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWrittenBy() {
        return writtenBy;
    }

    public void setWrittenBy(String writtenBy) {
        this.writtenBy = writtenBy;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    //please generate hashCode and equals so they are correct for your project
}
