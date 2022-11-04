package model;

import java.util.List;

public class Library {
    private List<Printable> library;

    public Library(List<Printable> library) {
        this.library = library;
    }

    public static void printLibrary(Library library) {
        for (int i = 0; i < library.getSize(); i++) {
            System.out.println(library.getElement(i));
        }
    }

    public int getSize() {
        return library.size();
    }

    public Printable getElement(int i) {
        return library.get(i);
    }
}
