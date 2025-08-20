public class Book {
    private final String title;
    private final String author;
    private final String isbn;
    private boolean isBorrowed;

    public Book(String title, String author, String isbn){
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isBorrowed = false;
    }

    public String getTitle() {
        return title;
    }
    public String getIsbn() {
        return isbn;
    }
    public boolean getIsBorrowed() {
        return isBorrowed;
    }
    public void borrow() {
        this.isBorrowed = true;
    }
    public void returnBook() {
        this.isBorrowed = false;
    }


    /*public String toString() {
        return "[" + isbn + "] " + title + "by " + author + " " + (isBorrowed ? "(Borrowed)" : "(Available)");

    }*/

    public String toString() {
        return String.format("[%-10s] %-30s by %-20s %s", isbn, title, author, isBorrowed ? "(Borrowed)" : "(Available)");

    }
}
