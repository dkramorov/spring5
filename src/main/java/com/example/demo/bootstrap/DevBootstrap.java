package com.example.demo.bootstrap;

import com.example.demo.Repository.AuthorRepository;
import com.example.demo.Repository.BookRepository;
import com.example.demo.Repository.PublisherRepository;
import com.example.demo.models.Author;
import com.example.demo.models.Book;
import com.example.demo.models.Publisher;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository,
                        BookRepository bookRepository,
                        PublisherRepository publisherRepository){
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    private void initData(){
        Publisher publisher1 = new Publisher("Питер", "Санкт-Питербург, Лермонтова 126");
        Publisher publisher2 = new Publisher("Литера", "Москва, Ленина 5");
        publisherRepository.save(publisher1);
        publisherRepository.save(publisher2);

        Book book1 = new Book("Книга обо всем", "123-321", publisher1);
        Book book2 = new Book("Сто тысяч лье под водой", "333-321", publisher1);
        Author author1 = new Author("Phil", "Willams");
        Author author2 = new Author("Mark", "Twain");
        author1.getBooks().add(book1);
        author2.getBooks().add(book2);
        authorRepository.save(author1);
        authorRepository.save(author2);

        book1.getAuthors().add(author1);
        book2.getAuthors().add(author2);
        bookRepository.save(book1);
        bookRepository.save(book2);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }
}
