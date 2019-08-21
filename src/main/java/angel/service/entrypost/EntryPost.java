package angel.service.entrypost;

import java.math.BigInteger;
import org.springframework.data.mongodb.core.mapping.Document;

//bytes32 contentsId;
//bytes32 photoId1;
//bytes32 photoId2;
//bytes32 photoId3;
//bytes32 postedAt;
//uint256 likes;
//bytes32 serviceProgramId;
//bytes32 userId;
@Document
public class EntryPost {
	public String id;
	public String contents;
	public String photo1;
	public String photo2;
	public String photo3;
	public String postedAt;
	public BigInteger likes;
	public String serviceProgramId;
	public String userId;
	
	public EntryPost() {
	  
	}
	public EntryPost(String id, String contents, String photo1, String photo2, String photo3, String postedAt, BigInteger likes, String serviceProgramId, String userId){
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
	public BigInteger getLikes() {
		return likes;
	}
	public void setLikes(BigInteger likes) {
		this.likes = likes;
	}
	public String getServiceProgramId() {
		return serviceProgramId;
	}
	public void setServiceProgramId(String serviceProgramId) {
		serviceProgramId = serviceProgramId;
	}
	public String getUserId() {
		return this.userId;
	}
	public void setUserId(String userId) {
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
