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
    
    mapping( bytes32 => EntryPost[]) entryPosts;
    uint idx;

    function getEntryPostLengthByUser(bytes32 _userId) view external returns( uint ) {
        return entryPosts[_userId].length;
    }
    
    function getEntryPost(bytes32 _user, uint _idx) view external 
    returns( bytes32 
            , bytes32 
            , bytes32 
            , bytes32 
            , bytes32 
            , uint256 
            , bytes32 
            , bytes32 
            ) {
        EntryPost memory entry = entryPosts[_user][_idx];
        
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
    
   function setEntryPost(
        bytes32 _contentsId,
        bytes32 _photoId1,
        bytes32 _photoId2,
        bytes32 _photoId3,
        bytes32 _postedAt,
        uint256 _likes,
        bytes32 _serviceProgramId,
        bytes32 _userId
        ) external {

        EntryPost memory entryPost;
        entryPost.contentsId = _contentsId;
        entryPost.photoId1 = _photoId1;
        entryPost.photoId2 = _photoId2;
        entryPost.photoId3 = _photoId3;
        entryPost.postedAt = _postedAt;
        entryPost.likes = _likes;
        entryPost.serviceProgramId = _serviceProgramId;
        entryPost.userId = _userId;
        
        entryPosts[_userId].push(entryPost);
        
    }
}