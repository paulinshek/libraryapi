package library_project.models;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by pshek on 15/08/2016.
 */
@ResponseStatus(value= HttpStatus.NOT_FOUND, reason = "Book is already out")
public class BookIsOutException extends Exception {
}
