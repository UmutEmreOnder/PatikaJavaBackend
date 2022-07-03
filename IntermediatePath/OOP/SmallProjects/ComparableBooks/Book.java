package ComparableBooks;

public class Book implements Comparable<Book>{
    private int pageNumber, publishYear;
    private String name, authorName;

    public Book(String name, int pageNumber, String authorName, int publishYear) {
        this.name = name;
        this.pageNumber = pageNumber;
        this.authorName = authorName;
        this.publishYear = publishYear;
    }

    @Override
    public int compareTo(Book book) {
        return this.getName().compareTo(book.getName());
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
