package angel.service.program;

import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.ThreadSafe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/servicePrograms",
  produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},
  consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
@ParametersAreNonnullByDefault
@ThreadSafe
public class ServiceProgramController{
  
  @PostMapping
  public void addServiceProgram(String title, LocalDate startAt, LocalDate endAt, String detail, @Nullable String requestId) {

  }

  
  
  
  
}
