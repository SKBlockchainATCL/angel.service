pragma solidity ^0.5.0;
//매우중요한내용!!!maven compile 할때 Experiment를 지원하지 않
contract EntryPostContract {
    
    struct EntryPost {
        
        string id;
        string contentsId;
        string photoId1;
        string photoId2;
        string photoId3;
        string postedAt;
        int likes;
        string serviceProgramId;
        string userId;
        
    }
    
    mapping(string => EntryPost) entryPosts;
    
    function getEntryPost(string memory id) view public returns(string memory) {
        
        return (entryPosts[id].contentsId);
        
    }
    
   function setEntryPost(string memory id,
        string memory contentsId,
        string memory photoId1,
        string memory photoId2,
        string memory photoId3,
        string memory postedAt,
        int likes,
        string memory serviceProgramId,
        string memory userId) payable public {

        EntryPost memory entryPost;
        entryPost.contentsId = contentsId;
        entryPost.photoId1 = photoId1;
        entryPost.photoId2 = photoId2;
        entryPost.photoId3 = photoId3;
        entryPost.postedAt = postedAt;
        entryPost.likes = likes;
        entryPost.serviceProgramId = serviceProgramId;
        entryPost.userId = userId;
        
        entryPosts[id] = entryPost;
        
    }
}