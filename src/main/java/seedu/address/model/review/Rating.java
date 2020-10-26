package seedu.address.model.review;

import static java.util.Objects.requireNonNull;

public class Rating {
    public static final String MESSAGE_CONSTRAINTS = "rating can only be an integer from 0 to 5";

    public static final String VALIDATION_REGEX = "\\s*(\\d{1})\\s*";

    public final Integer ratingNumber;

    /**
     * Constructs an {@code Rating}.
     *
     * @param rating A valid rating.
     */
    public Rating(Integer rating) {
        requireNonNull(rating);
        this.ratingNumber = rating;
    }


    /**
     * Returns true if a given string is a valid rating.
     */
    public static boolean isValidRating(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return ratingNumber.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Rating // instanceof handles nulls
                && ratingNumber.equals(((Rating) other).ratingNumber)); // state check
    }

    @Override
    public int hashCode() {
        return ratingNumber.hashCode();
    }
}