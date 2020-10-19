CREATE TABLE `campaign` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `datasource` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `datasource_campaign` (
  `datasource_id` INT NOT NULL,
  `campaign_id` INT NOT NULL,
  PRIMARY KEY (`datasource_id`,`campaign_id`),
  CONSTRAINT `datasource_id` FOREIGN KEY (`datasource_id`) REFERENCES `datasource` (`id`),
  CONSTRAINT `campaign_id` FOREIGN KEY (`campaign_id`) REFERENCES `campaign` (`id`)
);
CREATE INDEX datasource_id_idx ON datasource_campaign(datasource_id);
CREATE INDEX campaign_id_idx ON datasource_campaign(campaign_id);


CREATE TABLE `metric` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `datasource_id` INT NULL,
  `campaign_id` INT NULL,
  `daily` DATE NULL,
  `clicks` INT NULL,
  `impressions` INT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `metric_datasource_id` FOREIGN KEY (`datasource_id`) REFERENCES `datasource_campaign` (`datasource_id`),
  CONSTRAINT `metric_campaign_id` FOREIGN KEY (`campaign_id`) REFERENCES `datasource_campaign` (`campaign_id`)
);
CREATE INDEX metric_datasource_id_idx ON metric(datasource_id);
CREATE INDEX metric_campaign_id_idx ON metric(campaign_id);
CREATE INDEX metric_daily_idx ON metric(daily);
