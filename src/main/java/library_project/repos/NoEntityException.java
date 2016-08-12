package library_project.Repos;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by pshek on 11/08/2016.
 */
@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such Entity")  // 404
public class NoEntityException extends Throwable {
}
