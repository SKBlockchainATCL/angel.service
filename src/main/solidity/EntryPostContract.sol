pragma solidity ^0.5.0;
//매우중요한내용!!!maven compile 할때 Experiment를 지원하지 않
contract EntryPostContract {
    
    struct EntryPost {
        
        bytes32 contentsId;
        bytes32 photoId1;
        bytes32 photoId2;
        bytes32 photoId3;
        bytes32 postedAt;
        uint256 likes;
        bytes32 serviceProgramId;
        bytes32 userId;
    }
    
    mapping(bytes32 => EntryPost) entryPosts;
    
    function getEntryPost(bytes32  hashId) view external 
    returns( bytes32 
            , bytes32 
            , bytes32 
            , bytes32 
            , bytes32 
            , uint256 
            , bytes32 
            , bytes32 
            ) {
        EntryPost memory entry = entryPosts[hashId];
        
        return ( entry.contentsId
                ,entry.photoId1
                ,entry.photoId2
                ,entry.photoId3
                ,entry.postedAt
                ,entry.likes
                ,entry.serviceProgramId
                ,entry.userId
                );
        
    }
    
   function setEntryPost(bytes32 hashId,
        bytes32 contentsId,
        bytes32 photoId1,
        bytes32 photoId2,
        bytes32 photoId3,
        bytes32 postedAt,
        uint256 likes,
        bytes32 serviceProgramId,
        bytes32 userId
        ) public {

        EntryPost memory entryPost;
        entryPost.contentsId = contentsId;
        entryPost.photoId1 = photoId1;
        entryPost.photoId2 = photoId2;
        entryPost.photoId3 = photoId3;
        entryPost.postedAt = postedAt;
        entryPost.likes = likes;
        entryPost.serviceProgramId = serviceProgramId;
        entryPost.userId = userId;
        
        entryPosts[hashId] = entryPost;
        
    }
}