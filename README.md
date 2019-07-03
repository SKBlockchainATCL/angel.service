### REST API Design

   | Resource | API | Method | URL | Remark |
   | -------- | --- | :----: | --- | ------ |
   | ServiceProgram          | List recent service programs             | `GET`  | `service/programs/recent?count={cnt}` | `N : max(n)` |
   |                         | List or search service programs          | `GET`  | `service/programs?from={from}&to={to}&title={search}&pageSize={size}&pageNo={no}` |   |
   | ServiceProgramEntry     | List entries for a service program       | `GET`  | `service/programs/{programId}/entries?sort={fields}&pageSize={size}&pageNo={no}` |   |
   | ServiceProgramEntryPost | List or search service program entry posts | `GET` | `service/entryPosts?from={from}&to={to}&pageSize={size}&pageNo={no}&sort={fields}` |
   |                         | List or search service program entry posts by a specific user | `GET` | `service/entryPosts/belongTo/{userId}?from={from}&to={to}&pageSize={size}&pageNo={no}&sort={fields}` |
   |                         | Add a new 'like' for a entry post        | `POST` | `service/entryPosts/{postId}/likes` | 
   |                         | Add a new service program entry post     | `POST` | `service/entryPosts/`            |              |

#### Design Detail

* `service/programs/recent?count={cnt}`

  | Parameter | Description | Datatype | Value Space | Is Mandatory | Default | Remarks |
  | --------- | ----------- | -------- | ----------- | ------------ | ------- | ------- |
  | `count` | The number of programs to list | integer | [`1`, `20`] | No | `10`  |   |

  
* `service/programs?from={from}&to={to}&title={search}&pageSize={size}&pageNo={no}`
  
  | Parameter | Description | Datatype | Value Space | Is Mandatory | Default | Remarks |
  | --------- | ----------- | -------- | ----------- | ------------ | ------- | ------- |
  | `from` | Starting date for the programs to list | string | `yyyyMMdd` form | No |   |   |
  | `to`   | Ending date for the programs to list   | string | `yyyyMMdd` form | No |   |   |
  | `title` | Search term for the program title     | string |            | No |   |   |
  | `pageSize` |                                    | integer | [`1`, `20`]   | No | `10` |   |
  | `pageNo`   |                                    | integer | `-1` \| [`1`, ] | No | `1` |   |
    
    * When `title` is not specified, the list would be orderted by program's start date decreasingly
    * When `title` is specified, the list would be ordered by relevance rank decreasingly.
    
    * Altenatives
        * `service/programs/keyworded/{search}?from={from}&to={to}&pageSize={size}&pageNo={no}`

* `service/entryPosts?from={from}&to={to}&pageSize={size}&pageNo={no}&sort={fields}`

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

* `service/entryPosts/belongTo/{userId}?from={from}&to={to}&pageSize={size}&pageNo={no}&sort={fields}`

  | Parameter | Description | Datatype | Value Space | Is Mandatory | Default | Remarks |
  | --------- | ----------- | -------- | ----------- | ------------ | ------- | ------- |
  | `userId`  | The user who writes the entry posts to list  | string |            | Yes |   |   |
  | `from` | Starting date for the entry posts to list | string | `yyyyMMdd` form | No |   |   |
  | `to`   | Ending date for the entry posts to list   | string | `yyyyMMdd` form | No |   |   |
  | `pageSize` |                                    | integer | [`1`, `20`]       | No | `10`  |   |
  | `pageNo`   |                                    | integer | `-1` \| [`1`, ] | No  | `1`  |   |
  | `sort`     | Sort critera                       | string  | `postedAt` \| `postedAt:desc` \| `postedAt:asc` | No | `postedAt:desc`  |    |

    * Alternatives
        * `service/entryPosts?from={from}&to={to}&by={userId}&pageSize={size}&pageNo={no}&sort={fields}`
  

#### References

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
