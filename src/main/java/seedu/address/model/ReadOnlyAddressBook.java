package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.book.Book;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the books list.
     * This list will not contain any duplicate books.
     */
    ObservableList<Book> getBookList();

}
