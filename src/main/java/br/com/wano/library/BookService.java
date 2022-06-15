package br.com.wano.library;

import org.springframework.stereotype.Service;

@Service
public record BookService(BookRepository bookRepository) {

    public Book save(BookRequest request){
        return bookRepository.saveAndFlush(convertToBook(request));
    }
    private Book convertToBook(BookRequest request){
        return Book.builder().titulo(request.titulo()).preco(request.preco()).isbn(request.isbn()).build();
    }

    public Book findById(Long id){
        return bookRepository.findById(id).orElse(null);
    }
}
