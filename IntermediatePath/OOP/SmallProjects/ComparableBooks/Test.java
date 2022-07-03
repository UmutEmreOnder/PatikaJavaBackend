package ComparableBooks;

import java.util.*;

public class Test {

    public static void main(String[] args) {
        Set<Book> set = new TreeSet<>();

        Book book1 = new Book("Test1", 50, "Dummy1", 2022);
        Book book2 = new Book("Test2", 524, "Dummy2", 2000);
        Book book3 = new Book("Test3", 410, "Dummy3", 2001);
        Book book4 = new Book("Test4", 99, "Dummy4", 2011);
        Book book5 = new Book("Test5", 157, "Dummy5", 1999);

        set.add(book3);
        set.add(book2);
        set.add(book1);
        set.add(book4);
        set.add(book5);

        printMap(set);

        Set<Book> set1 = new TreeSet<>(Comparator.comparingInt(Book::getPageNumber).reversed());

        System.out.println();

        set1.add(book3);
        set1.add(book2);
        set1.add(book1);
        set1.add(book4);
        set1.add(book5);

        printMap(set1);
    }

    public static void printMap(Set<Book> set) {
        for (Book book : set) {
            System.out.println(book.getName() + " " + book.getPageNumber());
        }
    }
}
