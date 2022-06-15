package br.com.wano.library;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/books")
public record BookController(BookService bookService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book saveBook(@Valid @RequestBody BookRequest request){
        return bookService.save(request);
    }

    @GetMapping("/{id}")
    public Book findBook(@PathVariable Long id){
        return bookService.findById(id);
    }
}
