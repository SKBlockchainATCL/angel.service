## REST API Analysis from Workflow Funnel Design

* ServiceProgramEntryPost / List or search service program entry posts
    * Method : `GET`
    * URL : `service/entryPosts`
    
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
        
* ServiceProgram / List or search service programs 
    * Method : `GET`
    * URL : `service/programs`

* ServiceProgram / List or search open service programs
    * Method : `GET`
    * URL : `service/programs/open`

* ServiceProgram / List or search service programs in process
    * Method : `GET`
    * URL : `service/programs/started`
    
* ServiceProgram / List or search service programs reivewed
    * Method : `GET`
    * URL : `service/programs/completed`

* ServiceProgram / Find a service program
    * Method : `GET`
    * URL : `service/programs/{programId}`
    
* ServiceProgam / Find a open service program
    * Method : `GET`
    * URL : `service/programs/open/{programId}`
    
