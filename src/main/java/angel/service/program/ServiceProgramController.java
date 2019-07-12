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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/service/programs",
  produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},
  consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
@ParametersAreNonnullByDefault
@ThreadSafe
public class ServiceProgramController{
  
  public static final int PAGE_SIZE_MAX = 20;
  
  public static final int PAGE_SIZE_DEFAULT = 5;
  
  public static final String PAGE_SIZE_DEFAULT_STRING = "5";

  /**
   * List or search service programs
   * 
   * @return
   * @see GET service/programs
   */
  @GetMapping @Nonnull
  public List<ServiceProgramValue> findServicePrograms(
      @RequestParam("startAt") @Nullable String startAt,
      @RequestParam("endAt") @Nullable String endAt,
      @RequestParam("title") @Nullable String title,
      @RequestParam(name = "pageSize", defaultValue = PAGE_SIZE_DEFAULT_STRING) 
        @Min(1) @Max(PAGE_SIZE_MAX) int pageSize,
      @RequestParam(name = "pageNo", defaultValue = "1") @Positive int pageNo
  ){
    
    return new ArrayList<ServiceProgramValue>();
  }
  
  @GetMapping("/open") @Nonnull
  public List<ServiceProgramValue> findOpenServicePrograms(
  ){
    
    return new ArrayList<ServiceProgramValue>();
  }
  
  
  @GetMapping("/started") @Nonnull
  public List<ServiceProgramValue> findStartedServicePrograms(
  ){
    return new ArrayList<ServiceProgramValue>();
  }
  
  @GetMapping("/completed") @Nonnull
  public List<ServiceProgramValue> findCompletedServicePrograms(
  ){
    return new ArrayList<ServiceProgramValue>();
  }

}
