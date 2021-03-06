package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.book.Address;
import seedu.address.model.book.Author;
import seedu.address.model.book.Email;
import seedu.address.model.book.Isbn;
import seedu.address.model.book.Name;
import seedu.address.model.book.Publisher;
import seedu.address.model.book.Stocking;
import seedu.address.model.book.Times;
import seedu.address.model.category.Category;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String isbn} into a {@code Isbn}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code isbn} is invalid.
     */
    public static Isbn parseIsbn(String isbn) throws ParseException {
        requireNonNull(isbn);
        String trimmedIsbn = isbn.trim();
        if (!Isbn.isValidIsbn(trimmedIsbn)) {
            throw new ParseException(Isbn.MESSAGE_CONSTRAINTS);
        }
        return new Isbn(trimmedIsbn);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Times parseTimes(String times) throws ParseException {
        requireNonNull(times);
        String trimmedTimes = times.trim();
        if (!Times.isValidTimes(trimmedTimes)) {
            throw new ParseException(Times.MESSAGE_CONSTRAINTS);
        }
        return new Times(times);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String category} into a {@code Category}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code category} is invalid.
     */
    public static Category parseCategory(String category) throws ParseException {
        requireNonNull(category);
        String trimmedCategory = category.trim();
        if (!Category.isValidCategoryName(trimmedCategory)) {
            throw new ParseException(Category.MESSAGE_CONSTRAINTS);
        }
        return new Category(trimmedCategory);
    }


    /**
     * Parses a {@code String author} into a {@code Author}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code author} is invalid.
     */
    public static Author parseAuthor(String author) throws ParseException {
        requireNonNull(author);
        String trimmedAuthor = author.trim();
        if (!Author.isValidAuthor(trimmedAuthor)) {
            throw new ParseException(Author.MESSAGE_CONSTRAINTS);
        }
        return new Author(trimmedAuthor);
    }

    /**
     * Parses a {@code String publisher} into a {@code Publisher}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code publisher} is invalid.
     */
    public static Publisher parsePublisher(String publisher) throws ParseException {
        requireNonNull(publisher);
        String trimmedPublisher = publisher.trim();
        if (!Publisher.isValidPublisher(trimmedPublisher)) {
            throw new ParseException(Publisher.MESSAGE_CONSTRAINTS);
        }
        return new Publisher(trimmedPublisher);
    }

    /**
     * Parses {@code Collection<String> categories} into a {@code Set<Category>}.
     */
    public static Set<Category> parseCategories(Collection<String> categories) throws ParseException {
        requireNonNull(categories);
        final Set<Category> categorySet = new HashSet<>();
        for (String categoryName : categories) {
            categorySet.add(parseCategory(categoryName));
        }
        return categorySet;
    }

    /**
     * Parses {@code String stocking} into a {@code Stocking}.
     */
    public static Stocking parseStocking(String stocking) throws ParseException {
        requireNonNull(stocking);
        Pattern pattern = Pattern.compile(Stocking.VALIDATION_REGEX);
        Matcher matcher = pattern.matcher(stocking);

        int count = matcher.groupCount();
        HashMap<String, Integer> stockingInLocation = new HashMap<>();

        try {
            if (matcher.find() && Stocking.isValidStocking(stocking)) {
                List<String> locations = Arrays.asList(Stocking.LOCATION);
                locations.forEach((location) -> {
                    stockingInLocation.put(location, 0);
                });

                for (int i = 1; i <= count; i = i + 2) {
                    //if (matcher.group(i).strip().toUpperCase().equals(Stocking.LOCATION[(i - 1) / 2].toUpperCase())) {
                    String currentLocation = matcher.group(i).strip();
                    int currentCount = Integer.parseInt(matcher.group(i + 1).strip());

                    locations.forEach((location) -> {
                        if (location.toUpperCase().equals(currentLocation.toUpperCase())) {
                            stockingInLocation.put(location, currentCount);
                        }
                    });
                }
            } else {
                throw new ParseException(Stocking.MESSAGE_CONSTRAINTS);
            }
        } catch (Exception exception) {
            throw new ParseException(Stocking.MESSAGE_CONSTRAINTS);
        }
        return new Stocking(stockingInLocation);
    }
}
