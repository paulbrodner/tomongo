# tomongo

Reverse engineer objects that exists on Alfresco Server

_Example:_

* Get list of all sites for a desired user and create those sites in MongoDB for further use on benchmark tests. This will create the following documents in mongoDB:
    ```yml
    /* 0 */
    {
        "_id" : ObjectId("561bddb84bdd3f6094c5447d"),
        "siteId" : "jmuser01610site",
        "guid" : "/alfresco/service/api/node/workspace/SpacesStore/1ef2d548-a7ac-458a-b24c-bee59171f1f8",
        "domain" : "default",
        "path" : null,
        "preset" : "preset",
        "title" : "jmuser01610site",
        "description" : "",
        "visibility" : "PRIVATE",
        "randomizer" : 954995,
        "creationState" : "Created"
    }
    
    /* 1 */
    {
        "_id" : ObjectId("561bddb84bdd3f6094c5447e"),
        "siteId" : "jmuser01742site",
        "guid" : "/alfresco/service/api/node/workspace/SpacesStore/730622c6-4635-4af8-a701-c853f1af9adb",
        "domain" : "default",
        "path" : null,
        "preset" : "preset",
        "title" : "jmuser01742site",
        "description" : "",
        "visibility" : "PRIVATE",
        "randomizer" : 331263,
        "creationState" : "Created"
    }
    ```

Alfresco Rest API

* RESTful-Repository: http://docs.alfresco.com/5.0/references/RESTful-Repository.html?m=2