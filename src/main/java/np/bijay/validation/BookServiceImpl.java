package np.bijay.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;
    private final Converter converter;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, Converter converter) {
        this.bookRepository = bookRepository;
        this.converter = converter;
    }

    @Override
    public List<BookDto> getAll() {

        return converter.toDtoList(bookRepository.findAll());
    }

    @Override
    public BookDto findOne(Long id) {
        Book book=bookRepository.findById(id).orElseThrow(()->new ValidationFailedException("id","Book with invalid id:"+id));
        return converter.toDto(book);
    }

    @Override
    public BookDto createBook(BookDto bookDto) {
        return converter.toDto(bookRepository.save(converter.toEntity(bookDto)));


    }

    @Override
    public BookDto updateBook(BookDto bookDto, Long id) {
        return converter.toDto(bookRepository.save(converter.toEntity(bookDto,id)));
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
