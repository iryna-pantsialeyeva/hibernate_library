import model.Address;
import model.Author;
import model.Book;
import model.Library;
import model.Magazine;
import model.Newspaper;
import model.Printable;
import model.Publisher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Scanner;

public class MainMenu {

    private static SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    private static Library library;

    public static void main(String[] args) {
        MainMenu.start();
    }

    static void start() {
        System.err.println("Hello, dear user.");
        Scanner scInt = new Scanner(System.in);
        Scanner scLine = new Scanner(System.in);

        while (true) {
            System.err.println("Please select an action: \n 1) Add a printable item \n 2) Show all printable items \n " +
                    "3) Delete a printable item \n 4) Update a printable item  \n 5) Exit");
            System.err.print("Insert number here: ");

            int choice = scInt.nextInt();
            switch (choice) {
                case 1:
                    System.err.println("Please select the printable item: \n 1) Book \n 2) Magazine \n " +
                            "3) Newspaper");
                    System.err.print("Insert number here: ");
                    int printableChoice = scInt.nextInt();
                    System.err.print("Please insert the name of the printable item: ");
                    String name = scLine.nextLine();
                    System.err.print("Please insert the name of the publisher: ");
                    String publisherName = scLine.nextLine();
                    System.err.print("Please insert the address of the publisher: ");
                    String address = scLine.nextLine();
                    Publisher publisher = new Publisher(publisherName, new Address(address));

                    switch (printableChoice) {
                        case 1 -> {
                            System.err.print("Please insert the author: ");
                            String authorName = scLine.nextLine();
                            try (Session session = sessionFactory.openSession()) {
                                session.beginTransaction();
                                Author author = new Author(authorName);
                                Printable book = new Book(name, publisher, author);
                                session.save(book);
                                session.getTransaction().commit();
                                System.out.println("Book was added. \n");
                            }
                        }
                        case 2 -> {
                            System.err.print("Please insert the issue: ");
                            int issue = scInt.nextInt();
                            try (Session session = sessionFactory.openSession()) {
                                session.beginTransaction();
                                Printable magazine = new Magazine(name, publisher, issue);
                                session.save(magazine);
                                session.getTransaction().commit();
                                System.out.println("Magazine was added. \n");
                            }
                        }
                        case 3 -> {
                            System.err.print("Please insert the type (paper/online): ");
                            String type = scLine.nextLine();
                            try (Session session = sessionFactory.openSession()) {
                                session.beginTransaction();
                                Printable newspaper = new Newspaper(name, publisher, type);
                                session.save(newspaper);
                                session.getTransaction().commit();
                                System.out.println("Newspaper was added. \n");
                            }
                        }
                        default ->
                                System.out.println("Chosen option can not be performed. Please insert the number within 1-3.");
                    }
                case 2:
                    try (Session session = sessionFactory.openSession()) {
                        session.beginTransaction();
                        List<Printable> printableList = session.createQuery("from Printable").getResultList(); // hql
                        session.getTransaction().commit();
                        library = new Library(printableList);
                        Library.printLibrary(library);
                        break;
                    }
                case 3:
                    try (Session session = sessionFactory.openSession()) {
                        System.err.print("Please insert the printable item's id to delete: ");
                        int id = scInt.nextInt();
                        session.beginTransaction();
                        Printable printable = session.get(Printable.class, id);
                        session.delete(printable);
                        //session.createQuery("delete from Printable p where p.id=id").executeUpdate(); // hql
                        session.getTransaction().commit();
                        System.out.println("model.Library element was deleted. \n");
                        break;
                    }
                case 4:
                    try (Session session = sessionFactory.openSession()) {
                        System.err.print("Please insert the printable item's id to update: ");
                        int id = scInt.nextInt();
                        session.beginTransaction();
                        Printable printable = session.get(Printable.class, id);

                        if (printable.getClass().getSimpleName().equals("Book")) {
                            System.err.print("Please insert a new author: ");
                            String authorName = scLine.nextLine();
                            ((Book) printable).setAuthor(new Author(authorName));
                            session.getTransaction().commit();
                        } else if (printable.getClass().getSimpleName().equals("Magazine")) {
                            System.err.print("Please insert a new issue: ");
                            ((Magazine) printable).setIssue(scInt.nextInt());
                            session.getTransaction().commit();
                        } else if (printable.getClass().getSimpleName().equals("Newspaper")) {
                            System.err.print("Please insert a new type: ");
                            ((Newspaper) printable).setType(scLine.nextLine());
                        }
                        break;
                    }
                case 5:
                    System.err.print("Thank you for using library. Good bye!");
                    return;
                default:
                    System.out.println("Chosen option can not be performed. Please insert the number within 1-5. \n");
            }
        }
    }
}
