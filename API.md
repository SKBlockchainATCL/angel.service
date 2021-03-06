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


* EnteranceApplicant / List or search applicants to join the company or institue
    * Method : `GET`
    * URL : `service/organizations/{orgId}/applicants`
    * Input : 
        * query / `name`
        * query / `birthday`
        * query / `pageNo`
        * query / `pageSize`

    * Remarks :
        * requires : the organization of current session == `orgId`
        

* ServiceProgramEntry / List or search a program entries by a certain user
    * Method : `GET`
    * URL : `service/programs/-/entries/belongTo/{userId}`
    * Input :
        * query / `pageNo`
        * query / `pageSize`
    * Remarks :
        * requires :  user(userId).accessibleOrgs.inlcudes.org(currentSession.orgId)
        
* ServiceProgramEntry / Find a program entry
    * Method : `GET`
    * URL : `service/programs/-/entries/{entryId}`
    * Remarks : 
        * requires : entry(entryId).user.accessibleOrgs.includes.org(currentSession.orgId)


## REST API Refined

   | Resource | API | Method | URL | Privileged | Remark |
   | -------- | --- | :----: | --- | ---------- | ------ |
   | ServiceProgram          | List recent service programs             | `GET`  | `service/programs/recent?count={cnt}` |    |   |
   |                         | List or search service programs          | `GET`  | `service/programs?from={from}&to={to}&title={search}&pageSize={size}&pageNo={no}` |   |   |
   |                         | List or search open service programs     | `GET`  | `service/programs/open?from={from}&to={to}&title={search}&pageSize={size}&pageNo={no}` |   |   |
   |                         | List or search service programs in process | `GET` | `service/programs/started?from={from}&to={to}&title={search}&pageSize={size}&pageNo={no}` |   |   |
   |                         | List or search service programs reivewed   | `GET` | `service/programs/reviwed?from={from}&to={to}&title={search}&pageSize={size}&pageNo={no}` |   |   |
   |                         | Find a service program                    | `GET`  | `service/programs/{programId}` |   |   |
   | ServiceProgramEntry     | List entries of a service program       | `GET`  | `service/programs/{programId}/entries?sort={fields}&pageSize={size}&pageNo={no}` |   |   |
   |                         | Add service program entry for current session  | `POST` | `service/programs/{programId}/entries/belongToMe`  |   |   |
   |                         | Add service program entry for the specified user | `POST` | `service/programs/{programId}/entries/belongTo/{userId}` | Yes |   |
   | ServiceProgramEntryPost | List or search service program entry posts | `GET` | `service/entryPosts?from={from}&to={to}&pageSize={size}&pageNo={no}&sort={fields}` |   |   |
   |                         | List or search service program entry posts by a specific user | `GET` | `service/entryPosts/belongTo/{userId}?from={from}&to={to}&pageSize={size}&pageNo={no}&sort={fields}` |   |   |
   |                         | Add a new 'like' for a entry post        | `POST` | `service/entryPosts/{postId}/likes` |   |   |
   |                         | Add a new service program entry post     | `POST` | `service/entryPosts/` | Yes  |   |
   |                         | Add a new service program entry post for current session | `POST` | `service/entryPosts/belongToMe` |   |   | 
   | ServiceProgramRequest   | List or search service program requests  | `GET` | `service/programRequests?from={from}&to={to}&title={search}&pageSize={size}&pageNo={no}` |   |   |
   |                         | Add a new service program request        | `POST` | `service/programRequests` |   |   |

### Design Detail

#### List recent service programs

* **`GET service/programs/recent?count={cnt}`**

    * Equivalent
        * `service/programs?pageSize={cnt}&pageNo=1`
  
* Input

    | Parameter | Description | Datatype | Value Space | Is Mandatory | Default | Remarks |
    | --------- | ----------- | -------- | ----------- | ------------ | ------- | ------- |
    | `count` | The number of programs to list | integer | [`1`, `20`] | No | `10`  |   |

#### List or search service programs
  
* **`GET service/programs?from={from}&to={to}&title={search}&pageSize={size}&pageNo={no}`**

    * Altenatives
        * `service/programs/keyworded/{search}?from={from}&to={to}&pageSize={size}&pageNo={no}`

* Input
 
    | Parameter | Description | Datatype | Value Space | Is Mandatory | Default | Remarks |
    | --------- | ----------- | -------- | ----------- | ------------ | ------- | ------- |
    | `from` | Starting date for the programs to list | string | `yyyyMMdd` form | No |   |   |
    | `to`   | Ending date for the programs to list   | string | `yyyyMMdd` form | No |   |   |
    | `title` | Search term for the program title     | string |            | No |   |   |
    | `pageSize` |                                    | integer | [`1`, `20`]   | No | `10` |   |
    | `pageNo`   |                                    | integer | `-1` \| [`1`, ] | No | `1` |   |
    
    * When `title` is not specified, the list would be orderted by program's start date decreasingly
    * When `title` is specified, the list would be ordered by relevance rank decreasingly.


#### Get a service program

* **`GET service/programs/{programId}`**

* Input

    | Parameter | Description | Datatype | Value Space | Is Mandatory | Default | Remarks |
    | --------- | ----------- | -------- | ----------- | ------------ | ------- | ------- |
    | `programId` | Unique identifier for a service program | string |  | Yes |   |   |

* Output

    `programId`, `title`, `startAt`, `endAt`, `status`, `details`, `review`, `programReqId`, `coordiId`


#### List or search service program entry posts

* **`GET service/entryPosts?from={from}&to={to}&pageSize={size}&pageNo={no}&sort={fields}`**

* Input

    | Parameter | Description | Datatype | Value Space | Is Mandatory | Default | Remarks |
    | --------- | ----------- | -------- | ----------- | ------------ | ------- | ------- |
    | `from` | Starting date for the entry posts to list | string | `yyyyMMdd` form | No |   |   |
    | `to`   | Ending date for the entry posts to list   | string | `yyyyMMdd` form | No |   |   |
    | `by`   | The user who writes the entry posts to list  | string |            | No |   |   |
    | `pageSize` |                                    | integer | [`1`, `20`]    | No | `10`  |   |
    | `pageNo`   |                                    | integer | `-1` or [`1`, ] | No  | `1` |   |
    | `sort`     | Sort critera                       | string  | `postedAt` \| `postedAt:desc` \| `postedAt:asc` | No | `postedAt:desc`  |    |

    * When both `from` and `to` are not specified, there's no restcition on the posted date for the entry posts to list.
    * When only `from` is specified, entry posts from the specified date upto now would be listed.
    * When only `to` is specified, entry posts from the 1st one upto the specified date would be listed.
    * When `sort` parameter is not specified, the list would be ordered by 'postedAt' field decreasingly.
    * For `sort` parater, 'postedAt' is equal to 'postedAt:desc'

#### List or search service program entry posts by a specific user

* **`GET service/entryPosts/belongTo/{userId}?from={from}&to={to}&pageSize={size}&pageNo={no}&sort={fields}`**

    * Alternatives
        * `service/entryPosts?from={from}&to={to}&by={userId}&pageSize={size}&pageNo={no}&sort={fields}`

* Input

    | Parameter | Description | Datatype | Value Space | Is Mandatory | Default | Remarks |
    | --------- | ----------- | -------- | ----------- | ------------ | ------- | ------- |
    | `userId`  | The user who writes the entry posts to list  | string |            | Yes |   |   |
    | `from` | Starting date for the entry posts to list | string | `yyyyMMdd` form | No |   |   |
    | `to`   | Ending date for the entry posts to list   | string | `yyyyMMdd` form | No |   |   |
    | `pageSize` |                                    | integer | [`1`, `20`]       | No | `10`  |   |
    | `pageNo`   |                                    | integer | `-1` \| [`1`, ] | No  | `1`  |   |
    | `sort`     | Sort critera                       | string  | `postedAt` \| `postedAt:desc` \| `postedAt:asc` | No | `postedAt:desc`  |    |

#### Add a new service program entry post

* `POST service/entryPosts/belongToMe`

* Input

    | Type | Parameter | Description | Datatype | Value Space | Is Mandatory | Default | Remarks |
    | ---- | --------- | ----------- | -------- | ----------- | ------------ | ------- | ------- |
    | Body | `programId` |           |          |             | Yes          |         |         |
    |      | `content`   |           | string   | MaxLen = 1000 | Yes        |         |         |


## References

* [Representational state transfer (on Wikipedia)](https://en.wikipedia.org/wiki/Representational_state_transfer)
* [Google Cloud APIs API Design Guide](https://cloud.google.com/apis/design/)
* [Microsoft REST API Guidelines](https://github.com/microsoft/api-guidelines)
* [Best Practices for Designing a Pragmatic RESTful API](https://www.vinaysahni.com/best-practices-for-a-pragmatic-restful-api)
* [Understanding REST Parameters](https://www.soapui.org/rest-testing/understanding-rest-parameters.html)
* [REST API Syntax](https://github.com/Esri/geoportal-server/wiki/REST-API-Syntax)

* [Gmail API Reference](https://developers.google.com/gmail/api/v1/reference/)
* [Twitter REST APIs](https://developer.twitter.com/en/docs)
* [Instagram APIs](https://www.instagram.com/developer/endpoints/)
* [GitHub API v3](https://developer.github.com/v3/)
        