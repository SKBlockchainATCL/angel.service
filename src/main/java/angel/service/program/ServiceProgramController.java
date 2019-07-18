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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/service/programs",
  produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},
  consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
@Api("'Service Program' API")
@ParametersAreNonnullByDefault
@ThreadSafe
public class ServiceProgramController{
  
  public static final int PAGE_SIZE_MAX = 20;
  
  public static final int PAGE_SIZE_DEFAULT = 5;
  
  public static final String PAGE_SIZE_DEFAULT_STRING = "5";
  
  //@Autowired
  private ServiceProgramService service;

  /**
   * List or search service programs
   * 
   * @return
   * @see GET service/programs
   */
  @GetMapping @Nonnull
  @ApiOperation(value="List or search service programs")
  public List<ServiceProgramValue> findServicePrograms(
    @RequestParam(name = "startAfter", required = false) @Nullable
      @ApiParam("service programs start after or at this date : 'yyyyMMdd' format")
      @Pattern(regexp = "[1-9][0-9]{7}") LocalDate startAfter,
    @RequestParam(name = "startBefore", required = false) @Nullable 
      @ApiParam("service programs start before or at this date :  'yyyyMMdd' format")
      @Pattern(regexp = "[1-9][0-9]{7}") LocalDate startBefore,
    @RequestParam(name = "title", required = false) @Nullable 
      @ApiParam("Search keyword for service program title")
      @Size(min = 1, max = 32) String title,
    @RequestParam(name = "pageSize", required = false, defaultValue = PAGE_SIZE_DEFAULT_STRING)
      @Min(1) @Max(PAGE_SIZE_MAX) int pageSize,
    @RequestParam(name = "pageNo", required = false, defaultValue = "1") 
      @Min(1) int pageNo
  ){
    
    List<ServiceProgramValue> programs = service.findServicePrograms(
        title, null, startAfter, startBefore, pageSize, pageNo);
    
    return programs;
  }
  
  @GetMapping("/open") @Nonnull
  @ApiOperation(value="List or search service programs open but not closed")
  public List<ServiceProgramValue> findOpenServicePrograms(
    @RequestParam(name = "startAfter", required = false) @Nullable
      @ApiParam("service programs start after or at this date : 'yyyyMMdd' format")
      @Pattern(regexp = "\\d{8}") LocalDate startAfter,
    @RequestParam(name = "startBefore", required = false) @Nullable 
      @ApiParam("service programs start before or at this date :  'yyyyMMdd' format")
      @Pattern(regexp = "\\d{8}") LocalDate startBefore,
    @RequestParam(name = "title", required = false) @Nullable 
      @ApiParam("Search keyword for service program title")
      @Size(min = 1, max = 32) String title,
    @RequestParam(name = "pageSize", required = false, defaultValue = PAGE_SIZE_DEFAULT_STRING)
      @Min(1) @Max(PAGE_SIZE_MAX) int pageSize,
    @RequestParam(name = "pageNo", required = false, defaultValue = "1") 
      @Min(1) int pageNo
  ){
    
    List<ServiceProgramValue> programs = service.findServicePrograms(
        title, new ServiceProgramStatus[] { ServiceProgramStatus.OPEN }, startAfter, startBefore, pageSize, pageNo);
    
    return programs;
  }
  
  
  @GetMapping("/started") @Nonnull
  @ApiOperation(value="List or search service programs started but not yet ended")
  public List<ServiceProgramValue> findStartedServicePrograms(
    @RequestParam(name = "startAfter", required = false) @Nullable
      @ApiParam("service programs start after or at this date : 'yyyyMMdd' format")
      @Pattern(regexp = "[1-9][0-9]{7}") LocalDate startAfter,
    @RequestParam(name = "startBefore", required = false) @Nullable 
      @ApiParam("service programs start before or at this date :  'yyyyMMdd' format")
      @Pattern(regexp = "[1-9][0-9]{7}") LocalDate startBefore,
    @RequestParam(name = "title", required = false) @Nullable 
      @ApiParam("Search keyword for service program title")
      @Size(min = 1, max = 32) String title,
    @RequestParam(name = "pageSize", required = false, defaultValue = PAGE_SIZE_DEFAULT_STRING)
      @Min(1) @Max(PAGE_SIZE_MAX) int pageSize,
    @RequestParam(name = "pageNo", required = false, defaultValue = "1") 
      @Min(1) int pageNo
  ){
    List<ServiceProgramValue> programs = service.findServicePrograms(
        title, new ServiceProgramStatus[] { ServiceProgramStatus.STARTED }, startAfter, startBefore, pageSize, pageNo);
    
    return programs;
  }
  
  @GetMapping("/completed") @Nonnull
  @ApiOperation(value="List or search completed service programs")
  public List<ServiceProgramValue> findCompletedServicePrograms(
    @RequestParam(name = "startAfter", required = false) @Nullable
      @ApiParam("service programs start after or at this date : 'yyyyMMdd' format")
      @Pattern(regexp = "[1-9][0-9]{7}") LocalDate startAfter,
    @RequestParam(name = "startBefore", required = false) @Nullable 
      @ApiParam("service programs start before or at this date :  'yyyyMMdd' format")
      @Pattern(regexp = "[1-9][0-9]{7}") LocalDate startBefore,
    @RequestParam(name = "title", required = false) @Nullable 
      @ApiParam("Search keyword for service program title")
      @Size(min = 1, max = 32) String title,
    @RequestParam(name = "pageSize", required = false, defaultValue = PAGE_SIZE_DEFAULT_STRING)
      @Min(1) @Max(PAGE_SIZE_MAX) int pageSize,
    @RequestParam(name = "pageNo", required = false, defaultValue = "1") 
      @Min(1) int pageNo
  ){
    List<ServiceProgramValue> programs = service.findServicePrograms(
        title, new ServiceProgramStatus[] { ServiceProgramStatus.REVIEWED }, startAfter, startBefore, pageSize, pageNo);
    
    return programs;
  }

  @GetMapping("/{programId}") @Nullable
  @ApiOperation(value="Find a service program for the specified ID")
  public ServiceProgramValue findServiceProgramById(
    @PathVariable(name = "programId", required = true) 
      @ApiParam("Identifier for service program to find") String id){
      
    return service.findServiceProgramById(id, null);
  }
  
  @GetMapping("/open/{programId}") @Nullable
  @ApiOperation(value="Find a open service program for the specified ID")
  public ServiceProgramValue findOpenServiceProgramById(    
   @PathVariable(name = "programId", required = true) 
      @ApiParam("Identifier for service program to find") String id){
    
    return service.findServiceProgramById(id, new ServiceProgramStatus[] { ServiceProgramStatus.REVIEWED });
  }
  
  @GetMapping("/coordinatedBy/{coordinatorId}") @Nonnull
  @ApiOperation(value="List service programs coordinated by a coordinator")
  public List<ServiceProgramValue> findServiceProgramsByCoordinator(
    @PathVariable(name = "coordinatorId", required = true)
      @ApiParam("Identifier of coordinator who owned the service programs to list") String coordinatorId,
    @RequestParam(name = "pageSize", required = false, defaultValue = PAGE_SIZE_DEFAULT_STRING)
      @Min(1) @Max(PAGE_SIZE_MAX) int pageSize,
    @RequestParam(name = "pageNo", required = false, defaultValue = "1") 
      @Min(1) int pageNo
  ){
    
    List<ServiceProgramValue> programs = service.findServiceProgramsByCoordinator(
      coordinatorId, null, null, null, null, 10, 1);  
    
    return programs;
  }

  @GetMapping("/{programId}/coordinator") @Nullable
  @ApiOperation(value="Get a coordinator of the specified service program")
  public CoordinatorValue getServiceProgramCoordinator(
    @PathVariable(name = "programId", required = true)
      @ApiParam("Identifier of service program") String programId) {
    
    return new CoordinatorValue();
  }

  
  @PostMapping @Nonnull
  @ApiOperation(value="Add a new service program")
  public ServiceProgramValue addServiceProgram() {
    return new ServiceProgramValue();
  }

  @PostMapping("/answering/{requestId}") @Nonnull
  @ApiOperation(value="Add a new service program based on the specified request")
  public ServiceProgramValue addServiceProgramForRequest() {
    return new ServiceProgramValue();
  }
  
  
  @PutMapping("/{programId}") @Nonnull
  @ApiOperation(value="Update the review of the service program")
  public ServiceProgramValue updateServiceProgramReview(
    @PathVariable(name = "programId", required = true)
      @ApiParam("Identifier of service program") String programId){
   
    return new ServiceProgramValue();    
  }
  
  
  @DeleteMapping("/{programId}")
  @ApiOperation(value="Cancel a service program")
  public void cancelServiceProgram(
    @PathVariable(name = "programId", required = true) @NotBlank
      @ApiParam("Identifier of service program to cancel") String programId) {
    
  }
  
  
}
