create table vehicle (
	id bigint not null auto_increment,
	owner_id bigint not null,
	model varchar(20) not null,
	brand varchar(20) not null,
	licence_plate varchar(7) not null,
	status varchar(20) not null,
	created_at datetime not null  DEFAULT CURRENT_TIMESTAMP,
	seized_at datetime,

	primary key (id)
);

alter table vehicle add constraint fk_vehicle_owner
foreign key (owner_id) references owner (id);

alter table vehicle add constraint uk_vehicle unique (licence_plate);