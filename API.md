## REST API Analysis from Workflow Funnel Design

* ServiceProgramEntryPost / List or search service program entry posts
    * Method : `GET`
    * URL : `service/entryPosts`
    
* ServiceProgramEntryPostLike / Add a new 'Like' for a entry post
    * Method : `POST`
    * URL : `service/entryPosts/{postId}/likes`
    * Remarks :
        * imposes : the user who add a like = current session

* ServiceProgramEntryPostLike / Cancel a 'Like' for a entry post
    * Method : `DELETE`
    * URL : `service/entryPost/{postId}/likes`
    * Remarks :
        * imposes : the user who cancel a like = current session
        
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

* ServiceProgramEntryPost / Add a new service program entry post
    * Method : `POST`
    * URL : `service/entryPosts/`
    * Remarks :
        * imposes : the owner of the post = current session
            * No one can add other user's post

* ServiceProgramRequest / Add a new service program request
    * Method : `POST`
    * URL : `service/programRequests`
    
* ServiceProgram / List or search service programs owned (coordinated) by a coordinator
    * Method : `GET`
    * URL : `service/programs/ownedBy/{coordinatorId}

* ServiceProgramRequest / List or search service program requests
    * Method : `GET`
    * URL : `service/programRequests`

* ServiceProgram / Add a new service program answering to a request
    * Method : `POST`
    * URL : `service/programs/answering/{requestId}`
    * Input : 
        * body / `title`
        * body / `startAt`
        * body / `endAt`
        * body / `details`
    
* ServiceProgramNoti / Notify an open service program to (interested ?) users
    * Method : `POST`
    * URL : `service/programs/{programId}/notifications/open`
    * Remarks :
        * requires : the program's coordinator == current session
    
* ServiceProgramCloseNoti / Notify a servivce program closed to (interested or related ?) users
    * Method : `POST`
    * URL : `service/programs/{programId}/notifications/closed`
    * Remarks : 
        * requires : the program's coordinator == current session
    * Questions
        * When or how to close the service program.
        
* ServiceProgram / Get a coordinator of the service program
    * Method : `GET`
    * URL : `service/programs/{programId}/coordinator`

* ServiceProgram / Update the review of the service program
    * Method : `PUT`
    * URL : `service/programs/{programId}/`
    * Input :
        * body / `review`

* ServiceProgramEntry / Review a service program entry
    * Method : `PUT`
    * URL : `service/programs/{programId}/entries/{entryId}`
    * Input :
        * body / `comments`
        * body / `grade`

* ServiceProgramEntry / List or search my service program entries
    * Method : `GET`
    * URL : `service/programs/-/entries/belongToMe`
    * Remarks : 
        * imposes : the owner of the program entry = current session
        * No one can search or access other user's program entries
    
* ServiceProgramEntry / Find a single my service program entires
    * Method : `GET`
    * URL : `service/programs/-/entries/{entryId}`
    * Remarks :
        * requires : the owner of the program entry == current session

* ServiceProgramEntry / Cancel my service program entry
    * Method : `DELETE`
    * URL : `service/programs/-/entries/{entryId}`
    * Remarks :
        * requires : the owner of the program entry == current session


    