exec proc_dropifexists('desk_gsl');

--oracle
create table desk_gsl(
  deskNo number(4),
  desktime date sysdate
);

insert into desk_gsl(deskNo) values(8003);


exec proc_dropifexists('vigatableseries');
create table vigatableseries(
  id number(4),
  seriesNo number(3) not null,
  series varchar2(40)
);

alter table vigatableseries add(
  constraint pk_vig_id_gsl primary key(id)
);

create sequence se_vig_gsl;

insert into vigatableseries(id,seriesNo,series) values(se_vig_gsl.nextval,10,'满汉全席');

select * from vigatableseries;



﻿exec proc_dropifexists('footopp_gsl');
--oracle
create table footopp_gsl(
id number(4),
productNo varchar2(8),
productName varchar2(20),
monad varchar2(3),
unitPrice number(4),
seriesNo varchar2(40)
);


alter table footopp_gsl add series varchar(40);
update footopp_gsl set series='满汉全席';

insert into footopp_gsl(productNo,productName,monad,unitPrice,seriesNo) values('07122401','佛跳墙','盘',168,10);
insert into footopp_gsl(productNo,productName,monad,unitPrice,seriesNO) values('07133404','无脑泥猴桃','盘',205,10);
alter table footopp_gsl add(
constraint pk_productid_gsl primary key(id)
);

exec proc_dropifexists('managerSystem_gsl');
create table managerSystem_gsl(
  system_id number(8) not null,
  accountno varchar2(8) not null,
  pwd varchar2(8) not null
);

alter table managerSystem_gsl add(
  constraint pk_id_gsl primary key(system_id),
  constraint un_acno_gsl unique(accountno),
  constraint ck_pwd_gsl check(length(pwd) between 3 and 8)
);
drop sequence se_sys_gsl;
create sequence se_sys_gsl;

insert into managerSystem_gsl(system_id,accountno,pwd) values(se_sys_gsl.nextval,'Tsoft','111');
select * from managerSystem_gsl;

commit;


