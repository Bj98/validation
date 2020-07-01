package np.bijay.validation;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<BookDto> getAll();

    BookDto findOne(Long id);

    BookDto createBook(BookDto bookDto);

    BookDto updateBook(BookDto bookDto, Long id);

    void deleteBook(Long id);
}
