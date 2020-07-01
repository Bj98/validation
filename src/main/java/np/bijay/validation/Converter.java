package np.bijay.validation;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class Converter {

    private final BookRepository bookRepository;

    public Converter(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookDto toDto(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setName(book.getName());
        bookDto.setPrice(book.getPrice());
        bookDto.setPublication(book.getPublication());
        bookDto.setQuantity(book.getQuantity());
        return bookDto;
    }

    public Book toEntity(BookDto bookDto){
       return toEntity(new Book(),bookDto);
    }

    public Book toEntity(BookDto bookDto,Long id){
        Book book=bookRepository.findById(id).orElseThrow(()->new ValidationFailedException("id","Invalid book id:"+id));
        return toEntity(book,bookDto);

    }

    private Book toEntity(Book book,BookDto bookDto){
        book.setName(bookDto.getName());
        book.setPrice(bookDto.getPrice());
        book.setPublication(bookDto.getPublication());
        book.setQuantity(bookDto.getQuantity());
        return book;

    }
    
    public List<BookDto> toDtoList(Iterable<Book> bookList){
        return StreamSupport.stream(bookList.spliterator(),false).map(this::toDto).collect(Collectors.toList());

    }
}

