package models;

public class Review {

    private int id;
    private String writtenBy;
    private int rating;
    private String content;
    private int id;
    private int restaurantId;

    public Review(String writtenBy, int rating, String content, int restaurantId) {
        this.writtenBy = writtenBy;
        this.rating = rating;

        this.content = content;
        this.restaurantId = restaurantId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Review review = (Review) o;

        if (id != review.id) return false;
        if (rating != review.rating) return false;
        if (id != review.id) return false;
        if (restaurantId != review.restaurantId) return false;
        if (writtenBy != null ? !writtenBy.equals(review.writtenBy) : review.writtenBy != null) return false;
        return content != null ? content.equals(review.content) : review.content == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (writtenBy != null ? writtenBy.hashCode() : 0);
        result = 31 * result + rating;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + id;
        result = 31 * result + restaurantId;
        return result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWrittenBy() {
        return this.writtenBy;
    }

    public void setWrittenBy(String writtenBy) {
        this.writtenBy = writtenBy;
    }

    public int getRating() {
        return this.rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

//    public String getCreatedAt() {
//      I will get created soon.
//    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getRestaurantId() {
        return this.restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

}
