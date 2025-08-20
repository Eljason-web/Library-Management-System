import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Library {
   private Map<String, Book> books = new HashMap<>();
   private Map<String, Patron> patrons = new HashMap<>();

    public Library() {
        books = new HashMap<>();
        patrons = new HashMap<>();
    }
    //Book methods
    public void addBook(Book book) {
       if (books.containsKey(book.getIsbn())) {
           System.out.println("Book with ISBN already exists.");
           return;
       }
       books.put(book.getIsbn(), book);
        System.out.println("Book added successfully.");

      }

    public void removeBook(String isbn) {
         books.remove(isbn);
    }

/*    public void listBooks() {
        for (Book b : books.values()) {
            System.out.println(b);
        }
    }*/

    public Book searchBook(String isbn) {
        return books.get(isbn);
    }

    public void addPatron(Patron patron) {
      if (patrons.containsKey(patron.getPatronId())) {
          System.out.println("Patron with this ID already exists.");
          return;
      }
      patrons.put(patron.getPatronId(), patron);
        System.out.println("Patron registered successfully.");
    }

    public void removePatron(String id) {
        patrons.remove(id);
    }

    public Patron searchPatron(String id) {
        return patrons.get(id);
    }

    public Book searchBookByTitle(String title) {
        for (Book book : books.values()) {
            if (book.getTitle().equalsIgnoreCase(title.trim())){
                return book;
            }
        }
        return null;
    }

    public Patron searchPatronByName(String name) {
        for (Patron p : patrons.values()) {
            if (p.getName().equalsIgnoreCase(name)) {
                return p;
            }
        }
        return null;
    }

    public boolean borrowBook(String patronId, String isbn) {
        Patron patron = patrons.get(patronId);
        Book book = books.get(isbn);
        if (patron != null && book != null && !book.getIsBorrowed()) {
            patron.borrowBook(book);
            return true;
        }
        return false;
    }

    public boolean returnBook(String patronId, String isbn) {
        Patron patron = patrons.get(patronId);
        Book book = books.get(isbn);
        if (patron != null && book != null) {
            patron.returnBook(book);
            return true;
        }
        return false;
    }

    public void listAllBooks() {
        books.values().forEach(System.out::println);
    }

    public void listAllPatrons() {
        patrons.values().forEach(System.out::println);
    }

    public List<Patron> getAllPatrons() {
        return new ArrayList<>(patrons.values());
    }


}
