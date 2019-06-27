package angel.service.program;

import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServiceProgramValue{
  
  
  private String id;
  
  private String title;
  
  private LocalDate startAt;
  
  private LocalDate endAt;
  
  private ServiceProgramStatus status;
  
  private String details;
  
  private String review;
  
  private String requestId;
  
  private String coordiId;

}
