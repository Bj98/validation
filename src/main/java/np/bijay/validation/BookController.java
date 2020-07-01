package np.bijay.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.util.List;



@Controller
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;
    private final BookValidator bookValidator;

    @Autowired
    public BookController(BookService bookService, BookValidator bookValidator) {
        this.bookService = bookService;
        this.bookValidator = bookValidator;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    @ResponseBody
    public List<BookDto> getAll() {
        return bookService.getAll();


    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    @ResponseBody
    public BookDto findOne(@PathVariable("id") Long id) {
        return bookService.findOne(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @ResponseBody
    public BookDto createBook(@RequestBody BookDto bookDto, BindingResult bindingResult) {
        bookValidator.validate(bookDto, bindingResult);
        return bookService.createBook(bookDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    @ResponseBody
    public BookDto updateBook(@RequestBody BookDto bookDto, @PathVariable Long id, BindingResult bindingResult) {
        bookValidator.validate(bookDto, bindingResult, id);
        return bookService.updateBook(bookDto, id);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    @ResponseBody
    public void deleteBook(@PathVariable("id") Long id) {
        bookValidator.validate(id);
        bookService.deleteBook(id);
    }


}



