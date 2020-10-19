## ads-service

Using H2 as in Memory Database for simplicity of this example.

For real world I would suggest something like https://www.timescale.com/ (relational db for time series). 

### Hot to build and run
```./gradlew clean bootRun```

or

```./gradlew clean build```

 ```java -jar ./build/libs/ads-service-0.0.1-SNAPSHOT.jar``` 

### How to answer possible queries like these:
- Total Clicks for a given Datasource for a given Date range
    - Refer to "Get summary" section  

- Click-Through Rate (CTR) per Datasource and Campaign
    - Refer to "Get summary" section   

- Impressions over time (daily)
    - Refer to "Get metrics" section

### API examples:
#### Get campaigns
```curl --location --request GET 'localhost:8080/campaigns'```
```
[
    {
        "id": "1",
        "name": "Adventmarkt Touristik"
    },
    {
        "id": "2",
        "name": "Firmen Mitgliedschaft"
    }
]
```

#### Get datasources
```curl --location --request GET 'localhost:8080/datasources'```

```
[
    {
        "id": "1",
        "name": "Facebook Ads",
        "campaigns": [
            {
                "id": "1",
                "name": "Adventmarkt Touristik"
            }
        ]
    }
]
```

#### Get campaigns related to a datasource
```curl --location --request GET 'localhost:8080/datasources/1/campaigns'```

```
[
    {
        "id": "1",
        "name": "Adventmarkt Touristik"
    }
]
```

#### Get metrics (query parameters are all optional)
```curl --location --request GET 'localhost:8080/metrics?datasourceId=1&campaignId=1&from=2019-11-12&until=2019-12-01'```

```
[
    {
        "datasource": "Facebook Ads",
        "campaign": "Adventmarkt Touristik",
        "date": "2019-11-12",
        "impressions": 40887,
        "clicks": 79
    }
]
```


#### Get summary
```curl --location --request GET 'localhost:8080/metrics/summary?datasourceId=1&campaignId=1&from=2019-11-12&until=2019-12-01'```

```
{
    "totalClicks": 585,
    "totalImpressions": 130882,
    "ctr": 0.004469674974404425
}
```
