package angel.service.program;

import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServiceProgramServiceImpl implements ServiceProgramService{


  @Override
  public ServiceProgramValue findServiceProgramById(
    @NotBlank String id, 
    @Nullable ServiceProgramStatus[] statuses){
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<ServiceProgramValue> findServicePrograms(
    String titleSearchKeyword, 
    ServiceProgramStatus[] statuses, 
    LocalDate startAfter, 
    LocalDate startBefore,
    @Positive int pageSize, 
    @Positive int pageNo){
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<ServiceProgramValue> findServiceProgramsByCoordinator(
    @NotBlank String coordinatorId, 
    String titleSearchKeyword, 
    ServiceProgramStatus[] statuses,
    LocalDate startAfter, 
    LocalDate startBefore, 
    @Positive int pageSize, 
    @Positive int pageNo){
    // TODO Auto-generated method stub
    return null;
  }
}
