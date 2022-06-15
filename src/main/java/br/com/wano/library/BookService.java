package br.com.wano.library;

import br.com.wano.library.exception.ErrorCode;
import br.com.wano.library.exception.IsbnAlreadyExistsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public record BookService(BookRepository bookRepository) {

    public Book save(BookRequest request){
        log.info("Save {}", request);
        if (isnbExists(request.isbn())){
            log.info("Isbn already exists");
            throw new IsbnAlreadyExistsException(ErrorCode.ISBN_ALREADY_EXISTS.name());
        }
        return bookRepository.save(convertToBook(request));
    }
    private Book convertToBook(BookRequest request){
        return Book.builder().titulo(request.titulo()).preco(request.preco()).isbn(request.isbn()).build();
    }

    public Book findById(Long id){
        log.info("FindById {}", id);
        return bookRepository.findById(id).orElse(null);
    }
    private Boolean isnbExists(String isbn){
        return bookRepository.findByIsbn(isbn).isPresent();
    }
}
