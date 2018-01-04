package models;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Review {

    private String writtenBy;
    private int rating;
    private int id;
    private int restaurantId;

    public Review(String writtenBy, int rating, int restaurantId) {
        this.writtenBy = writtenBy;
        this.rating = rating;
        this.restaurantId = restaurantId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Review review = (Review) o;

        if (rating != review.rating) return false;
        if (id != review.id) return false;
        if (restaurantId != review.restaurantId) return false;
        return writtenBy != null ? writtenBy.equals(review.writtenBy) : review.writtenBy == null;
    }

    @Override
    public int hashCode() {
        int result = writtenBy != null ? writtenBy.hashCode() : 0;
        result = 31 * result + rating;
        result = 31 * result + id;
        result = 31 * result + restaurantId;
        return result;
    }
}
