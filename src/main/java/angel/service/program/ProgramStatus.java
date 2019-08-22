package angel.service.program;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum ProgramStatus{
  
  /**
   * Open for application (applicable) 
   */
  OPEN,
  
  /**
   * Closed for application but not started yet
   * 
   */
  CLOSED,
  
  /**
   * Service program started and in process
   */
  STARTED,
  
  /**
   * Service program ended but not evaluated or reviewed
   */
  ENDED,
  
  REVIEWED,
  
  CANCELED

}
