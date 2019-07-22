package angel.service.entrypost;

import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class EntryPost {
	public String id;
	public String contents;
	public String photo1;
	public String photo2;
	public String photo3;
	public String postedAt;
	public int likes;
	public int serviceProgramId;
	public int userId;
	
	public EntryPost() {
	  
	}
	public EntryPost(String id, String contents, String photo1, String photo2, String photo3, String postedAt, int likes, int serviceProgramId, int userId){
    super();
    this.id = id;
    this.contents = contents;
    this.photo1 = photo1;
    this.photo2 = photo2;
    this.photo3 = photo3;
    this.postedAt = postedAt;
    this.likes = likes;
    this.serviceProgramId = serviceProgramId;
    this.userId = userId;
  }
	
  public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contects) {
	  contents = contects;
	}
	public String getPhoto1() {
		return photo1;
	}
	public void setPhoto1(String photo1) {
		this.photo1 = photo1;
	}
	public String getPhoto2() {
		return photo2;
	}
	public void setPhoto2(String photo2) {
		this.photo2 = photo2;
	}
	public String getPhoto3() {
		return photo3;
	}
	public void setPhoto3(String photo3) {
		this.photo3 = photo3;
	}
	public String getPostedAt() {
		return postedAt;
	}
	public void setPostedAt(String postedAt) {
		this.postedAt = postedAt;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public int getServiceProgramId() {
		return serviceProgramId;
	}
	public void setServiceProgramId(int serviceProgramId) {
		serviceProgramId = serviceProgramId;
	}
	public int getUserId() {
		return this.userId;
	}
	public void setUserId(int userId) {
	  this.userId = userId;
	}
	
	@Override
	public String toString() {
		
		return new StringBuffer()
				.append("id : " + id)
				.append("contents : " + contents)
				.append("photo1 : " + photo1)
				.append("photo2 : " + photo2)
				.append("photo3 : " + photo3)
				.append("postedAt : " + postedAt)
				.append("likes : " + likes)
				.append("ServiceProgramId : " + serviceProgramId)
				.append("UserId : " + userId)
				.toString();
	}
}
