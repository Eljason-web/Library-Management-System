import java.util.List;
import java.util.Scanner;

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();
        boolean running = true;


        while (running) {
            System.out.println("\n === Library Menu ===");
            System.out.println("1. Add Book");
            System.out.println("2. Remove a Book");
            System.out.println("3. List all Books");
            System.out.println("4. Search for a Book");
            System.out.println("5. Add a Patron");
            System.out.println("6. Remove a patron");
            System.out.println("7. List all patrons");
            System.out.println("8. Search for a Patron");
            System.out.println("9. Borrow a book");
            System.out.println("10. Return a book");
            System.out.println("11. Exit");
            System.out.println("12. View Borrowed Books by Patron");

            System.out.println("choose an option: ");

            // Validate numeric input
            String choiceInput = scanner.nextLine();
            int choice;
            try {
                choice = Integer.parseInt(choiceInput);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
                continue;// go back to menu
            }
            //scanner.nextLine();
            Patron foundPatron = null;
        switch (choice) {
            case 1: {
                String title;
                while (true) {
                    System.out.println("Enter Title; ");
                    title = scanner.nextLine().trim();
                    if (title.isEmpty()) {
                        System.out.println("Title cannot be empty. Please enter again:");
                    } else {
                        break;// exit loop once valid
                    }
                }

                String author;
                while (true) {
                    System.out.println("Enter Title: ");
                    author = scanner.nextLine().trim();
                    if (author.isEmpty()) {
                        System.out.println("Author cannot be empty. Please enter again:");
                    } else {
                        break;
                    }
                }

                String isbn;
                while (true) {
                    System.out.println("Enter ISBN (numbers only): ");
                    isbn = scanner.nextLine().trim();
                    if (isbn.isEmpty()) {
                        System.out.println("ISBN cannot be empty.Please enter again:");
                    } else if ((!isbn.matches("\\d+"))) {
                        System.out.println("Invalid ISBN! Only numeric values allowed.");
                    } else {
                        break;// for valid ISBN
                    }
                }
                Book book = new Book(title, author, isbn);
                library.addBook(book);
                System.out.println("Book added successfully");
                break;
            }

            case 2: // Remove Book
                System.out.println("Enter ISBN of the book to remove: ");
                String removeIsbn = scanner.nextLine();
                library.removeBook(removeIsbn);
                System.out.println("Book removed.");
                break;
            case 3:  // List all books

                library.listAllBooks();
                break;
            case 4://search for a Book
                System.out.println("Enter title of the book to search: ");
                String searchTitle = scanner.nextLine();
                Book foundBook = library.searchBookByTitle(searchTitle);
                if (foundBook != null) {
                    System.out.println("Book found: " + foundBook);
                } else {
                    System.out.println("Book not found. ");
                }
                break;
            case 5:// Add Patron
                String name;
                while (true) {
                    System.out.println("Enter Patron Name: ");
                    name = scanner.nextLine();
                    if (!name.matches("[a-zA-Z '-]+")) {
                        System.out.println("Invalid name! Only letters and space are allowed. ");
                    } else  {
                        break; // valid name, exit loop
                    }
                }

                String id;
                while (true) {
                    System.out.println("Enter Patron ID (numbers only): ");
                    id = scanner.nextLine().trim();
                    //Only numeric IDs
                    if (!id.matches("\\d+")) {
                        System.out.println("Invalid ID! Only numeric values are allowed.");
                    } else {
                        break; //valid ID, exit loop
                    }
                }

                library.addPatron(new Patron(name, id));
                System.out.println("Patron added successfully.");
                break;

            case 6:// Remove Patron
                System.out.println("Enter Patron ID to remove: ");
                String removeId = scanner.nextLine();
                library.removePatron(removeId);
                System.out.println("Patron removed. ");
                break;
            case 7:// List all Patron
                library.listAllPatrons();
                break;

            case 8:// search for Patron
                System.out.println("Enter Patron ID to search: ");
                String searchId = scanner.nextLine();
                foundPatron = library.searchPatron(searchId);
                if (foundPatron != null) {
                    System.out.println("Patron found: " + foundPatron);
                } else {
                    System.out.println("Patron not found.");
                }
                break;
            case 9://Borrow a Book
                System.out.println("Enter Patron ID: ");
                String pid = scanner.nextLine();
                System.out.println("Enter Book ISBN: ");
                String isbnToBorrow = scanner.nextLine();
                if (library.borrowBook(pid, isbnToBorrow)) {
                    System.out.println("Book borrowed. ");
                } else {
                    System.out.println("Borrow failed. Check if the Patron ID and ISBN exist, and that the book is already borrowed.");
                }
                break;
            case 10:// Return a Book
                System.out.println("Enter Patron ID: ");
                String rid = scanner.nextLine();
                System.out.println("Enter Book ISBN: ");
                String isbnToReturn = scanner.nextLine();
                if (library.returnBook(rid, isbnToReturn)) {
                    System.out.println("Book returned. ");
                } else {
                    System.out.println("Return failed. ");
                }
                break;
            case 11://Exit
                System.out.print("Are you sure you want to exit? (yes/no): ");
                String confirm = scanner.nextLine().trim();
                if (confirm.equalsIgnoreCase("yes")) {
                    running = false;
                    System.out.println("Exiting library System. Goodbye!");
                } else {
                    System.out.println("Returning to menu...");
                }
                //System.out.println("Exiting library System. Goodbye! ");
                break;
            case 12:
                System.out.println("Enter Patron ID to view borrowed books: ");
                String patronIdForView = scanner.nextLine();
                for (Patron p : library.getAllPatrons()) {
                    if (p.getPatronId().equals(patronIdForView)) {
                        foundPatron = p;
                        break;
                    }
                }

                if (foundPatron != null) {
                    List<Book> borrowed = foundPatron.getBorrowedBooks();
                    if (borrowed.isEmpty()) {
                        System.out.println("This patron has not borrowed any books. ");
                    } else {
                        System.out.println("Books borrowed by " + foundPatron.getName() + ":");
                        for (Book book : borrowed) {
                            System.out.println(book);
                        }
                    }
                } else {
                    System.out.println("Patron not found. ");
                }
                break;

            default:
                System.out.println("Invalid option. Please try again. ");
            }
        }

        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();

    }

}
