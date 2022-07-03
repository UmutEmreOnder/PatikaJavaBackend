package StreamAPI;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book("foo1", 100, "bar1", new Date(2001)));
        bookList.add(new Book("foo2", 72, "bar2", new Date(2011)));
        bookList.add(new Book("foo3", 254, "bar3", new Date(2021)));
        bookList.add(new Book("foo4", 99, "bar4", new Date(2000)));
        bookList.add(new Book("foo5", 35, "bar5", new Date(1999)));
        bookList.add(new Book("foo6", 154, "bar6", new Date(1942)));
        bookList.add(new Book("foo7", 133, "bar7", new Date(1989)));
        bookList.add(new Book("foo8", 454, "bar8", new Date(1969)));
        bookList.add(new Book("foo9", 28, "bar9", new Date(1987)));
        bookList.add(new Book("foo10", 387, "bar10", new Date(1995)));

        Map<String,String> mappedList = new HashMap<>();


        bookList
                .stream()
                .forEach(book -> mappedList.put(book.getName(), book.getAuthor()));

        bookList
                .stream()
                .filter(book -> book.getPage() > 100)
                .forEach(book -> System.out.println(book.getName() + " " + mappedList.get(book.getName())));
    }
}
