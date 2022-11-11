import model.Address;
import model.Author;
import model.Book;
import model.Magazine;
import model.Newspaper;
import model.Printable;
import model.Publisher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MainMenu {

    private static final SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
            .buildSessionFactory();

    public static void main(String[] args) {

        try (sessionFactory; Session session = sessionFactory.getCurrentSession()) {
            Address address = new Address("Kuprevicha32");
            Publisher publisher = new Publisher("Iboh");
            publisher.setAddress(address);
            Author author = new Author("Roman");
            Author author1 = new Author("Dima");
            Printable book = new Book("Java for beginners");
            ((Book) book).saveAuthor(author);
            ((Book) book).saveAuthor(author1);
            Printable magazine = new Magazine("Java Magazine", 15);
            Printable newspaper = new Newspaper("The Java News", "paper");
            publisher.savePrintable(book);
            publisher.savePrintable(magazine);
            publisher.savePrintable(newspaper);
            session.beginTransaction();
            session.save(author);
            session.save(author1);
            session.save(publisher);
            session.save(magazine);
            session.save(book);
            session.save(newspaper);
            session.getTransaction().commit();
        }
    }
}
