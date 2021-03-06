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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@ParametersAreNonnullByDefault
@ThreadSafe
public interface ServiceProgramService{
  
  
  ServiceProgramValue findServiceProgramById(
      @NotBlank String id,
      @Nullable ServiceProgramStatus[] statuses
  );
  
  List<ServiceProgramValue> findServicePrograms(
      @Nullable String titleSearchKeyword,
      @Nullable ServiceProgramStatus[] statuses,
      @Nullable LocalDate startAfter,
      @Nullable LocalDate startBefore,
      @Nonnull @Positive int pageSize,
      @Nonnull @Positive int pageNo);

  List<ServiceProgramValue> findServiceProgramsByCoordinator(
      @NotBlank String coordinatorId,
      @Nullable String titleSearchKeyword,
      @Nullable ServiceProgramStatus[] statuses,
      @Nullable LocalDate startAfter,
      @Nullable LocalDate startBefore,
      @Nonnull @Positive int pageSize,
      @Nonnull @Positive int pageNo);
  
}
