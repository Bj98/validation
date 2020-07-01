package np.bijay.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.*;

@Component
public class BookValidator implements Validator {

    private final BookRepository bookRepository;

    @Autowired
    public BookValidator(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == Book.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "name", "book.name.empty", "Name can't be empty");
        ValidationUtils.rejectIfEmpty(errors, "publication", "book.publication.empty", "Publication can't be empty!");
        ValidationUtils.rejectIfEmpty(errors, "price", "book.price.empty", "Price can't be empty!");
        ValidationUtils.rejectIfEmpty(errors, "quantity", "book.quantity.empty", "Quantity can't be empty!");
        checkError(errors);
    }

    public void validate(Object target, Errors errors, Long id) {
        validate(id);
        validate(target, errors);

    }

    public void validate(Long id) {
        bookRepository.findById(id).orElseThrow(() -> new ValidationFailedException("id", "Invalid book id:" + id));

    }

    private void checkError(Errors errors) {
        if (errors.hasErrors()) {
            throw new ValidationFailedException("Validation failed!", errors);
        }

    }
}
