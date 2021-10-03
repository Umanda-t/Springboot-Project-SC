package com.example.projectsc;
import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class BookRepositoryTests {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BookRepository repo;
    @Test
    public void testCreateBook() {
        Book book = new Book();
        book.setEmail("ouytf@gmail.com");
        book.setVehicleno("uttr4WE");
        book.setVehicletype("Car");
        book.setServicetype("Car wash");
        book.setDate("13");
        book.setTime("10.30 a.m");


        Book savedBook = repo.save(book);

        Book existBook = entityManager.find(Book.class, savedBook.getId());

        assertThat(book.getEmail()).isEqualTo(existBook.getEmail());

    }
    @Test
    public void testListAll()
    {
        Iterable<Book> book=repo.findAll();
        Assertions.assertThat(book).hasSizeGreaterThan(0);

        for(Book b:book)
        {
            System.out.println(b);
        }
    }

@Test
    public void testGet()
{
    Long id= Long.valueOf(1);
    Optional<Book> optionalBook= repo.findById(id);
    Assertions.assertThat(optionalBook).isPresent();
    System.out.println(optionalBook.get());
}

@Test
public void testDelete()
{
    Long id= Long.valueOf(2);
    repo.deleteById(id);
    Optional<Book> optionalBook= repo.findById(id);
    Assertions.assertThat(optionalBook).isNotPresent();


}
}
