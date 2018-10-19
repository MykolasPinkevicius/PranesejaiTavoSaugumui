package lt.vilnius.saugus.saugusvilnius.reporting;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Disturbance resource not found")
public class DisturbanceNotFoundException extends RuntimeException {
}
