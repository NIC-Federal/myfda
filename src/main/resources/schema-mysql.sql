CREATE TABLE IF NOT EXISTS UserConnection (userId varchar(255) not null,
	providerId varchar(255) not null,
	providerUserId varchar(255),
	rank int not null,
	displayName varchar(255),
	profileUrl varchar(512),
	imageUrl varchar(512),
	accessToken varchar(255) not null,
	secret varchar(255),
	refreshToken varchar(255),
	expireTime bigint,
	primary key (userId, providerId, providerUserId));

set @exist := (select count(*) from information_schema.statistics where table_name = 'UserConnection' and index_name = 'UserConnectionRank' and table_schema = database());
set @sqlstmt := if( @exist > 0, 'select ''INFO: Index already exists.''', 'create unique index UserConnectionRank on UserConnection(userId, providerId, rank)');
PREPARE stmt FROM @sqlstmt;
EXECUTE stmt;
