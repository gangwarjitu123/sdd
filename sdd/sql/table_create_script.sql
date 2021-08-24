create table if not exists country
(
    country_id int not null
    primary key,
    country_name varchar(150) not null
    );

create table if not exists flyway_schema_history
(
    installed_rank int not null
    primary key,
    version varchar(50) null,
    description varchar(200) not null,
    type varchar(20) not null,
    script varchar(1000) not null,
    checksum int null,
    installed_by varchar(100) not null,
    installed_on timestamp default CURRENT_TIMESTAMP not null,
    execution_time int not null,
    success tinyint(1) not null
    );

create index flyway_schema_history_s_idx
    on flyway_schema_history (success);

create table if not exists health_facility_type
(
    facility_type_id int auto_increment
    primary key,
    facility_type_name varchar(255) not null
    );

create table if not exists role
(
    role_id int auto_increment,
    role_name varchar(100) not null,
    constraint role_role_id_uindex
    unique (role_id),
    constraint role_role_name_uindex
    unique (role_name)
    );

alter table role
    add primary key (role_id);

create table if not exists state
(
    state_id int not null
    primary key,
    state_name varchar(100) null,
    country_id int null,
    constraint state_country_country_id_fk
    foreign key (country_id) references country (country_id)
    );

create table if not exists district
(
    district_id int not null
    primary key,
    district_name varchar(255) not null,
    district_code int not null,
    mdds_code int not null,
    state_id int not null,
    constraint district_state_state_id_fk
    foreign key (state_id) references state (state_id)
    );

create table if not exists health_block
(
    health_block_id int auto_increment
    primary key,
    health_block_code int not null,
    health_block_name varchar(255) not null,
    district_id int not null,
    taluka_id int not null,
    mdds_code int not null,
    constraint health_block_district_district_id_fk
    foreign key (district_id) references district (district_id)
    );

create table if not exists health_facility
(
    health_facility_id int auto_increment
    primary key,
    health_facility_code int not null,
    health_facility_name varchar(255) not null,
    district_id int not null,
    taluka_id int not null,
    health_block_id int not null,
    health_facility_type_id int not null,
    constraint health_facility_district_district_id_fk
    foreign key (district_id) references district (district_id),
    constraint health_facility_health_facility_type_facility_type_id_fk
    foreign key (health_facility_type_id) references health_facility_type (facility_type_id)
    );

create table if not exists user
(
    id int auto_increment,
    name varchar(255) not null,
    mobile_number varchar(12) not null,
    email varchar(100) not null,
    district_id int not null,
    state_id int not null,
    block_id int null,
    facility_type_id int null,
    facility_id int null,
    role_id int not null,
    password varchar(255) not null,
    constraint User_email_uindex
    unique (email),
    constraint User_id_uindex
    unique (id),
    constraint User_mobile_number_uindex
    unique (mobile_number)
    );

alter table user
    add primary key (id);
