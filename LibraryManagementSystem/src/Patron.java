import java.util.ArrayList;
import java.util.List;

public class Patron {
    private final String name;
    private final String patronId;
    private final List<Book> borrowedBooks;

    public Patron(String name, String id){
        this.name = name;
         this.patronId = id;
         this.borrowedBooks = new ArrayList<>();
    }

    //Getters and Setters
    public String getName(){return name;}
    public String getPatronId(){return patronId;}
    public List<Book> getBorrowedBooks() {return borrowedBooks;}// You can now loop through and print books for this patron

    // borrowBook() and returnBook() methods

    public void borrowBook(Book book) {
        borrowedBooks.add(book);// Add book to patron's list
        book.borrow();// also updates the book's own status(Marks the book as borrowed)

    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);// Removes from patron's list
        book.returnBook(); // updates the book's own status
    }

    // toString() method

    @Override
    public String toString() {
        return "Patron:" + name + " (ID: " + patronId + ")";
    }
}

