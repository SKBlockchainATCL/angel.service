package angel.service.program;

import java.time.LocalDate;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ServiceProgram")
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
  
  @XmlElement(name = "id")
  public String getId() {
    return this.id;
  }
  
  @XmlElement(name = "title")
  public String getTitle() {
    return this.title;
  }
  

}
