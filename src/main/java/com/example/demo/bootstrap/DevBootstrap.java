package com.example.demo.bootstrap;

import com.example.demo.Repository.AuthorRepository;
import com.example.demo.Repository.BookRepository;
import com.example.demo.models.Author;
import com.example.demo.models.Book;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository){
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    private void initData(){
        Author author1 = new Author("Phil", "Willams");
        Author author2 = new Author("Mark", "Twain");
        Book book1 = new Book("Книга обо всем", "123-321", "Питер");
        Book book2 = new Book("Сто тысяч лье под водой", "333-321", "Литера");
        author1.getBooks().add(book1);
        author2.getBooks().add(book2);
        authorRepository.save(author1);
        authorRepository.save(author2);
        book1.getAuthors().add(author1);
        bookRepository.save(book1);
        bookRepository.save(book2);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }
}
