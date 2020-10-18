INSERT INTO datasource (id, name) VALUES (1, 'Facebook Ads');
INSERT INTO datasource (id, name) VALUES (2, 'Google Ads');

INSERT INTO campaign (id, name) VALUES (1, 'Retargeting');
INSERT INTO campaign (id, name) VALUES (2, 'Purchase');

INSERT INTO datasource_campaign (datasource_id, campaign_id) VALUES (1,1);
INSERT INTO datasource_campaign (datasource_id, campaign_id) VALUES (1,2);
INSERT INTO datasource_campaign (datasource_id, campaign_id) VALUES (2,2);

INSERT INTO metric (datasource_id, campaign_id, daily, clicks, impressions) VALUES (1,1,DATE '2019-06-01', 10, 1000);
INSERT INTO metric (datasource_id, campaign_id, daily, clicks, impressions) VALUES (1,1,DATE '2019-06-02', 5, 1000);
INSERT INTO metric (datasource_id, campaign_id, daily, clicks, impressions) VALUES (1,1,DATE '2019-06-03', 8, 1000);
INSERT INTO metric (datasource_id, campaign_id, daily, clicks, impressions) VALUES (1,1,DATE '2019-06-04', 9, 1000);
INSERT INTO metric (datasource_id, campaign_id, daily, clicks, impressions) VALUES (1,1,DATE '2019-06-05', 12, 1000);

INSERT INTO metric (datasource_id, campaign_id, daily, clicks, impressions) VALUES (1,2,DATE '2019-06-01', 10, 500);
INSERT INTO metric (datasource_id, campaign_id, daily, clicks, impressions) VALUES (1,2,DATE '2019-06-02', 5, 500);
INSERT INTO metric (datasource_id, campaign_id, daily, clicks, impressions) VALUES (1,2,DATE '2019-06-03', 8, 500);
INSERT INTO metric (datasource_id, campaign_id, daily, clicks, impressions) VALUES (1,2,DATE '2019-06-04', 9, 500);
INSERT INTO metric (datasource_id, campaign_id, daily, clicks, impressions) VALUES (1,2,DATE '2019-06-05', 12, 500);

INSERT INTO metric (datasource_id, campaign_id, daily, clicks, impressions) VALUES (2,2,DATE '2019-06-01', 20, 5000);
INSERT INTO metric (datasource_id, campaign_id, daily, clicks, impressions) VALUES (2,2,DATE '2019-06-02', 20, 5000);
INSERT INTO metric (datasource_id, campaign_id, daily, clicks, impressions) VALUES (2,2,DATE '2019-06-03', 40, 5000);
INSERT INTO metric (datasource_id, campaign_id, daily, clicks, impressions) VALUES (2,2,DATE '2019-06-04', 60, 5000);
INSERT INTO metric (datasource_id, campaign_id, daily, clicks, impressions) VALUES (2,2,DATE '2019-06-05', 40, 5000);
