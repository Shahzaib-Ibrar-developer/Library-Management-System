package library;

import java.util.ArrayList;

class Libraries {
    private ArrayList<Book> availableBooks;

    public Libraries() {
        availableBooks = new ArrayList<>();
        availableBooks.add(new Book("Sample Book 1", "Sample Author 1"));
        availableBooks.add(new Book("Sample Book 2", "Sample Author 2"));
        availableBooks.add(new Book("Sample Book 3", "Sample Author 3"));
        availableBooks.add(new Book("Sample Book 4", "Sample Author 4"));
        availableBooks.add(new Book("Sample Book 5", "Sample Author 5"));
    }

    public ArrayList<Book> getAvailableBooks() {
        return availableBooks;
    }

    public void addBook(String title, String author) {
        Book newBook = new Book(title, author);
        availableBooks.add(newBook);
    }

    // public void addBook(Book book) {
    //     availableBooks.add(book);
    // }

    public boolean returnBook(String title, String author) {
        for (int i = 0; i < availableBooks.size(); i++) {
            Book book = availableBooks.get(i);
            if (book.getTitle().equalsIgnoreCase(title) && book.getAuthor().equalsIgnoreCase(author)) {
                availableBooks.remove(i);
                return true;
            }
        }
        return false;
    }

    public void borrowBook(String title, String author) {
        for (int i = 0; i < availableBooks.size(); i++) {
            Book book = availableBooks.get(i);
            if (book.getTitle().equalsIgnoreCase(title) && book.getAuthor().equalsIgnoreCase(author)) {
                availableBooks.remove(i);
                return;
            }
        }
    }

    // public void deleteBook(String title, String author) {
    //     Book book = new Book(title, author);
    //     availableBooks.remove(book);
    // }
    public boolean deleteBook(String title, String author) {
        for (int i = 0; i < availableBooks.size(); i++) {
            Book book = availableBooks.get(i);
            if (book.getTitle().equalsIgnoreCase(title) && book.getAuthor().equalsIgnoreCase(author)) {
                availableBooks.remove(i);
                return true;
            }
        }
        return false;
    }
}


class Book {
    private String title;
    private String author;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }
}
