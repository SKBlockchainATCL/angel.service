### REST API Design

   | Resource | API | Method | URL | Remark |
   | -------- | --- | :----: | --- | ------ |
   | ServiceProgram            | List recent service programs             | `GET`  | `service/programs/recent?count={cnt}` | `N : max(n)` |
   |                           | List or search service programs          | `GET`  | `service/programs?from={from}&to={to}&title={search}&pageSize={size}&pageNo={no}` |   | 
   | ServiceProgramEntry       | List entries for a service program       | `GET`  | `service/programs/{programId}/entries?sort={fields}&pageSize={size}&pageNo={no}` |   |
   | ServiceProgramEntryPost   | Add a new service program entry post     | `POST` | `service/entryPosts/`            |              |
   |                           | Add a new 'like' for a entry post        | `POST` | `service/entryPosts/{postId}/likes` | 
   
#### Design Detail

* `service/programs?from={from}&to={to}&title={search}&pageSize={size}&pageNo={no}`
  
  | Parameter | Description | Datatype | Value Space | Is Mandatory | Default | Remarks |
  | --------- | ----------- | -------- | ----------- | ------------ | ------- | ------- |
  | `from` | Starting date for the programs to list | string | `yyyyMMdd` | No |   |   |
  | `to`   | Ending date for the programs to list   | string | `yyyyMMdd` | No |   |   |
  | `title` | Search term for the program title     | string |            | No |   |   |
  | `pageSize` |                                    | integer | >= 1      | 10 |   |   |
  | `pageNo`   |                                    | integer | -1 or >= 1 | 1  |   |   |
  
    * Without search on title, the result collections would be orderted by program date decreasingly

#### References

* [Google Cloud APIs / API Design Guide](https://cloud.google.com/apis/design/)