package angel.service.entrypost;

import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class EntryPost {
	public String id;
	public String Contents;
	public String photo1;
	public String photo2;
	public String photo3;
	public String postedAt;
	public int likes;
	public int ServiceProgramId;
	public int UserId;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContents() {
		return Contents;
	}
	public void setContents(String contects) {
		Contents = contects;
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
		return ServiceProgramId;
	}
	public void setServiceProgramId(int serviceProgramId) {
		ServiceProgramId = serviceProgramId;
	}
	public int getUserId() {
		return UserId;
	}
	public void setUserId(int userId) {
		UserId = userId;
	}
	
	@Override
	public String toString() {
		
		return new StringBuffer()
				.append("id : " + id)
				.append("Contents : " + Contents)
				.append("photo1 : " + photo1)
				.append("photo2 : " + photo2)
				.append("photo3 : " + photo3)
				.append("postedAt : " + postedAt)
				.append("likes : " + likes)
				.append("ServiceProgramId : " + ServiceProgramId)
				.append("UserId : " + ServiceProgramId)
				.toString();
	}
}
