package Entity;

public class MyBook {
    int isbn;
    String title;
    String author;
    String publisher;
    int year;
    int totalPages;
    int currentPages = 0;
    boolean isArchived = false;

    public MyBook(int isbn, String title, String author, String publisher, int year, int totalPages) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.year = year;
        this.totalPages = totalPages;
    }
    public int getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getYear() {
        return year;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getCurrentPages() {
        return currentPages;
    }

    public void setCurrentPages(int currentPages) {
        this.currentPages = currentPages;
    }

    public boolean isArchived() {
        return isArchived;
    }

    public void setArchived(boolean archived) {
        isArchived = archived;
    }
}
