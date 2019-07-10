## REST API Analysis from Workflow Funnel Design

* ServiceProgramEntryPost / List or search service program entry posts
    * Method : `GET`
    * URL : `service/entryPosts?from={from}&to={to}&pageSize={size}&pageNo={no}&sort={fields}`
    
* ServiceProgramEntryPostLike / Add a new 'Like' for a entry post
    * Method : `POST`
    * URL : `service/entryPosts/{postId}/likes`
    * Remarks :
        * The user who add a like = current session

* ServiceProgramEntryPostLike / Cancel a 'Like' for a entry post
    * Method : `DELETE`
    * URL : `service/entryPost/{postId}/likes`
    * Remarks :
        * The user who cancel a like = current session
        
* ServiceProgram