CREATE TABLE `user_role` (
	`user_id`	bigint(20)	NOT NULL,
	`role_id`	bigint(20)	NOT NULL
);

CREATE TABLE `area_district_code` (
	`id`	varchar(255)	NOT NULL,
	`version`	bigint(20)	NOT NULL,
	`area_city_code_id`	varchar(255)	NOT NULL,
	`name`	varchar(255)	NOT NULL
);

CREATE TABLE `article` (
	`id`	bigint(20)	NOT NULL AUTO_INCREMENT,
	`version`	bigint(20)	NOT NULL,
	`author_id`	bigint(20)	NULL,
	`completed`	bit(1)	NULL,
	`content_id`	bigint(20)	NULL,
	`create_ip`	varchar(255)	NULL,
	`date_created`	datetime	NOT NULL,
	`enabled`	bit(1)	NOT NULL,
	`last_editor_id`	bigint(20)	NULL,
	`last_updated`	datetime	NOT NULL,
	`note_count`	int(11)	NOT NULL,
	`scrap_count`	int(11)	NOT NULL,
	`selected_note_id`	bigint(20)	NULL,
	`tag_string`	varchar(255)	NULL,
	`title`	varchar(255)	NOT NULL,
	`view_count`	int(11)	NOT NULL,
	`like_count`	int(11)	NOT NULL,
	`dislike_count`	int(11)	NOT NULL,
	`category_id`	varchar(255)	NOT NULL,
    CONSTRAINT PK_ARTICLE PRIMARY KEY(`id`)
);

CREATE TABLE `avatar_interest_tag` (
	`avtar_id`	bigint(20)	NOT NULL,
	`name`	varchar(255)	NOT NULL
);

CREATE TABLE `(optional)notification_read` (
	`id`	bigint(20)	NOT NULL,
	`avatar_id`	bigint(20)	NOT NULL,
	`version`	bigint(20)	NOT NULL,
	`last_read`	datetime	NOT NULL
);

CREATE TABLE `confirm_email` (
	`id`	bigint(20)	NOT NULL,
	`user_id`	bigint(20)	NOT NULL,
	`version`	bigint(20)	NOT NULL,
	`date_expired`	datetime	NOT NULL,
	`email`	varchar(255)	NOT NULL,
	`secured_key`	varchar(255)	NOT NULL
);

CREATE TABLE `banner_click` (
	`id`	bigint(20)	NOT NULL,
	`version`	bigint(20)	NOT NULL,
	`banner_id`	bigint(20)	NOT NULL,
	`click_count`	int(11)	NOT NULL,
	`date_created`	datetime	NOT NULL,
	`ip`	varchar(255)	NOT NULL
);

CREATE TABLE `content_file` (
	`content_files_id`	bigint(20)	NULL,
	`file_id`	bigint(20)	NULL,
	`Key`	VARCHAR(255)	NOT NULL
);

CREATE TABLE `banner` (
	`id`	bigint(20)	NOT NULL,
	`version`	bigint(20)	NOT NULL,
	`date_created`	datetime	NOT NULL,
	`image`	varchar(255)	NULL,
	`last_updated`	datetime	NOT NULL,
	`name`	varchar(255)	NOT NULL,
	`target`	varchar(255)	NULL,
	`type`	varchar(255)	NOT NULL,
	`url`	varchar(255)	NOT NULL,
	`visible`	bit(1)	NOT NULL
);

CREATE TABLE `scrap` (
	`avatar_id`	bigint(20)	NOT NULL,
	`article_id`	bigint(20)	NOT NULL,
	`version`	bigint(20)	NOT NULL,
	`date_created`	datetime	NOT NULL
);

CREATE TABLE `opinion` (
	`id`	bigint(20)	NOT NULL,
	`content_id`	bigint(20)	NOT NULL,
	`author_id`	bigint(20)	NOT NULL,
	`version`	bigint(20)	NOT NULL,
	`comment`	longtext	NOT NULL,
	`date_created`	datetime	NOT NULL,
	`last_updated`	datetime	NOT NULL,
	`like_count`	int(11)	NOT NULL,
	`dislike_count`	int(11)	NOT NULL
);

CREATE TABLE `oauthid` (
	`id`	bigint(20)	NOT NULL,
	`user_id`	bigint(20)	NOT NULL,
	`version`	bigint(20)	NOT NULL,
	`access_token`	varchar(255)	NOT NULL,
	`provider`	varchar(255)	NOT NULL
);

CREATE TABLE `category_old` (
	`code`	varchar(255)	NOT NULL,
	`version`	bigint(20)	NOT NULL,
	`date_created`	datetime	NOT NULL,
	`default_label`	varchar(255)	NOT NULL,
	`enabled`	bit(1)	NOT NULL,
	`external_link`	varchar(255)	NULL,
	`icon_css_names`	varchar(255)	NULL,
	`isurl`	bit(1)	NOT NULL,
	`label_code`	varchar(255)	NOT NULL,
	`last_updated`	datetime	NOT NULL,
	`level`	int(11)	NOT NULL,
	`parent_id`	varchar(255)	NULL,
	`require_tag`	bit(1)	NOT NULL,
	`sort_order`	int(11)	NOT NULL,
	`url`	varchar(255)	NULL,
	`use_evaluate`	bit(1)	NOT NULL,
	`use_note`	bit(1)	NOT NULL,
	`use_opinion`	bit(1)	NOT NULL,
	`use_tag`	bit(1)	NOT NULL,
	`writable`	bit(1)	NOT NULL,
	`write_by_external_link`	bit(1)	NULL
);

CREATE TABLE `article_tag` (
	`article_tags_id`	bigint(20)	NULL,
	`tag_id`	bigint(20)	NULL
);

CREATE TABLE `area_city_code` (
	`id`	varchar(255)	NOT NULL,
	`version`	bigint(20)	NOT NULL,
	`name`	varchar(255)	NOT NULL
);

CREATE TABLE `spam_word` (
	`id`	bigint(20)	NOT NULL,
	`version`	bigint(20)	NOT NULL,
	`text`	varchar(255)	NOT NULL
);

CREATE TABLE `file` (
	`id`	bigint(20)	NOT NULL,
	`version`	bigint(20)	NOT NULL,
	`attach_type`	varchar(255)	NOT NULL,
	`byte_size`	int(11)	NOT NULL,
	`height`	int(11)	NOT NULL,
	`width`	int(11)	NOT NULL,
	`name`	varchar(255)	NOT NULL,
	`org_name`	varchar(255)	NOT NULL,
	`type`	varchar(255)	NOT NULL
);

CREATE TABLE `avatar` (
	`id`	bigint(20)	NOT NULL,
	`version`	bigint(20)	NOT NULL,
	`activity_point`	int(11)	NOT NULL,
	`nickname`	varchar(20)	NOT NULL,
	`picture`	varchar(255)	NOT NULL,
	`picture_type`	int(11)	NOT NULL
);

CREATE TABLE `logged_in` (
	`id`	bigint(20)	NOT NULL,
	`user_id`	bigint(20)	NOT NULL,
	`version`	bigint(20)	NOT NULL,
	`date_created`	datetime	NOT NULL,
	`remote_addr`	varchar(255)	NULL
);

CREATE TABLE `follow` (
	`follower_id`	bigint(20)	NOT NULL,
	`following_id`	bigint(20)	NOT NULL,
	`date_created`	datetime	NOT NULL
);

CREATE TABLE `content_vote` (
	`id`	bigint(20)	NOT NULL,
	`article_id`	bigint(20)	NOT NULL,
	`voter_id`	bigint(20)	NOT NULL,
	`content_id`	bigint(20)	NOT NULL,
	`date_created`	datetime	NOT NULL,
	`point`	int(11)	NOT NULL
);

CREATE TABLE `user` (
	`id`	bigint(20)	NOT NULL,
	`avatar_id`	bigint(20)	NOT NULL,
	`version`	bigint(20)	NOT NULL,
	`account_expired`	bit(1)	NOT NULL,
	`account_locked`	bit(1)	NOT NULL,
	`create_ip`	varchar(255)	NULL,
	`date_created`	datetime	NOT NULL,
	`date_withdraw`	datetime	NULL,
	`last_password_changed`	datetime	NOT NULL,
	`last_update_ip`	varchar(255)	NULL,
	`last_updated`	datetime	NOT NULL,
	`password`	varchar(255)	NOT NULL,
	`password_expired`	bit(1)	NOT NULL,
	`username`	varchar(15)	NOT NULL,
	`withdraw`	bit(1)	NOT NULL,
	`status`	int(11)	NOT NULL
);

CREATE TABLE `tag` (
	`id`	bigint(20)	NOT NULL,
	`date_created`	datetime	NOT NULL,
	`description`	varchar(255)	NULL,
	`name`	varchar(255)	NOT NULL,
	`tagged_count`	int(11)	NOT NULL
);

CREATE TABLE `change_log` (
	`id`	bigint(20)	NOT NULL,
	`version`	bigint(20)	NOT NULL,
	`article_id`	bigint(20)	NOT NULL,
	`avatar_id`	bigint(20)	NULL,
	`content_id`	bigint(20)	NULL,
	`date_created`	datetime	NOT NULL,
	`md5`	varchar(255)	NOT NULL,
	`patch`	longtext	NOT NULL,
	`revision`	int(11)	NOT NULL,
	`type`	varchar(255)	NOT NULL
);

CREATE TABLE `activity` (
	`id`	bigint(20)	NOT NULL,
	`avatar_id`	bigint(20)	NOT NULL,
	`article_id`	bigint(20)	NOT NULL,
	`content_id`	bigint(20)	NOT NULL,
	`version`	bigint(20)	NOT NULL,
	`date_created`	datetime	NOT NULL,
	`last_updated`	datetime	NOT NULL,
	`point`	int(11)	NOT NULL,
	`point_type`	varchar(255)	NOT NULL,
	`type`	varchar(255)	NOT NULL
);

CREATE TABLE `role` (
	`id`	bigint(20)	NOT NULL,
	`version`	bigint(20)	NOT NULL,
	`authority`	varchar(255)	NOT NULL
);

CREATE TABLE `(optional)notification` (
	`id`	bigint(20)	NOT NULL,
	`article_id`	bigint(20)	NOT NULL,
	`sender_id`	bigint(20)	NOT NULL,
	`receiver_id`	bigint(20)	NOT NULL,
	`content_id`	bigint(20)	NOT NULL,
	`version`	bigint(20)	NOT NULL,
	`date_created`	datetime	NOT NULL,
	`last_updated`	datetime	NOT NULL,
	`type`	varchar(255)	NOT NULL
);

CREATE TABLE `content` (
    `id`	bigint(20)	NOT NULL AUTO_INCREMENT,
	`version`	bigint(20)	NOT NULL,
	`article_id`	bigint(20)	NULL,
	`author_id`	bigint(20)	NULL,
	`create_ip`	varchar(255)	NULL,
	`date_created`	datetime	NOT NULL,
	`last_editor_id`	bigint(20)	NULL,
	`last_updated`	datetime	NOT NULL,
	`text`	longtext	NOT NULL,
	`type`	int(11)	NOT NULL,
	`like_count`	int(11)	NOT NULL,
	`dislike_count`	int(11)	NOT NULL,
    CONSTRAINT PK_CONTENT PRIMARY KEY(`id`)
);

CREATE TABLE `category` (
	`code`	varchar(255)	NOT NULL,
	`name`	varchar(255)	NOT NULL,
	`sort_order`	int(11)	NOT NULL,
	`type`	int(11)	NOT NULL,
	`parent_id`	int(11)	NULL
);

CREATE TABLE `temp_article` (
	`id`	bigint(20)	NOT NULL,
	`avatar_id`	bigint(20)	NOT NULL,
	`title`	varchar(20)	NULL,
	`content`	longtext	NULL,
	`date_last_saved`	datetime	NULL,
	`status`	int(11)	NULL
);

ALTER TABLE `user_role` ADD CONSTRAINT `PK_USER_ROLE` PRIMARY KEY (
	`user_id`,
	`role_id`
);

ALTER TABLE `area_district_code` ADD CONSTRAINT `PK_AREA_DISTRICT_CODE` PRIMARY KEY (
	`id`
);

ALTER TABLE `(optional)notification_read` ADD CONSTRAINT `PK_(OPTIONAL)NOTIFICATION_READ` PRIMARY KEY (
	`id`
);

ALTER TABLE `confirm_email` ADD CONSTRAINT `PK_CONFIRM_EMAIL` PRIMARY KEY (
	`id`
);

ALTER TABLE `banner_click` ADD CONSTRAINT `PK_BANNER_CLICK` PRIMARY KEY (
	`id`
);

ALTER TABLE `banner` ADD CONSTRAINT `PK_BANNER` PRIMARY KEY (
	`id`
);

ALTER TABLE `scrap` ADD CONSTRAINT `PK_SCRAP` PRIMARY KEY (
	`avatar_id`,
	`article_id`
);

ALTER TABLE `opinion` ADD CONSTRAINT `PK_OPINION` PRIMARY KEY (
	`id`
);

ALTER TABLE `oauthid` ADD CONSTRAINT `PK_OAUTHID` PRIMARY KEY (
	`id`
);

ALTER TABLE `category_old` ADD CONSTRAINT `PK_CATEGORY_OLD` PRIMARY KEY (
	`code`
);

ALTER TABLE `area_city_code` ADD CONSTRAINT `PK_AREA_CITY_CODE` PRIMARY KEY (
	`id`
);

ALTER TABLE `spam_word` ADD CONSTRAINT `PK_SPAM_WORD` PRIMARY KEY (
	`id`
);

ALTER TABLE `file` ADD CONSTRAINT `PK_FILE` PRIMARY KEY (
	`id`
);

ALTER TABLE `avatar` ADD CONSTRAINT `PK_AVATAR` PRIMARY KEY (
	`id`
);

ALTER TABLE `logged_in` ADD CONSTRAINT `PK_LOGGED_IN` PRIMARY KEY (
	`id`
);

ALTER TABLE `follow` ADD CONSTRAINT `PK_FOLLOW` PRIMARY KEY (
	`follower_id`,
	`following_id`
);

ALTER TABLE `content_vote` ADD CONSTRAINT `PK_CONTENT_VOTE` PRIMARY KEY (
	`id`
);

ALTER TABLE `user` ADD CONSTRAINT `PK_USER` PRIMARY KEY (
	`id`
);

ALTER TABLE `tag` ADD CONSTRAINT `PK_TAG` PRIMARY KEY (
	`id`
);

ALTER TABLE `change_log` ADD CONSTRAINT `PK_CHANGE_LOG` PRIMARY KEY (
	`id`
);

ALTER TABLE `activity` ADD CONSTRAINT `PK_ACTIVITY` PRIMARY KEY (
	`id`
);

ALTER TABLE `role` ADD CONSTRAINT `PK_ROLE` PRIMARY KEY (
	`id`
);

ALTER TABLE `(optional)notification` ADD CONSTRAINT `PK_(OPTIONAL)NOTIFICATION` PRIMARY KEY (
	`id`
);

ALTER TABLE `category` ADD CONSTRAINT `PK_CATEGORY` PRIMARY KEY (
	`code`
);

ALTER TABLE `temp_article` ADD CONSTRAINT `PK_TEMP_ARTICLE` PRIMARY KEY (
	`id`
);

ALTER TABLE `user_role` ADD CONSTRAINT `FK_user_TO_user_role_1` FOREIGN KEY (
	`user_id`
)
REFERENCES `user` (
	`id`
);

ALTER TABLE `user_role` ADD CONSTRAINT `FK_role_TO_user_role_1` FOREIGN KEY (
	`role_id`
)
REFERENCES `role` (
	`id`
);

ALTER TABLE `scrap` ADD CONSTRAINT `FK_avatar_TO_scrap_1` FOREIGN KEY (
	`avatar_id`
)
REFERENCES `avatar` (
	`id`
);

ALTER TABLE `scrap` ADD CONSTRAINT `FK_article_TO_scrap_1` FOREIGN KEY (
	`article_id`
)
REFERENCES `article` (
	`id`
);

ALTER TABLE `follow` ADD CONSTRAINT `FK_avatar_TO_follow_1` FOREIGN KEY (
	`follower_id`
)
REFERENCES `avatar` (
	`id`
);

ALTER TABLE `follow` ADD CONSTRAINT `FK_avatar_TO_follow_2` FOREIGN KEY (
	`following_id`
)
REFERENCES `avatar` (
	`id`
);

