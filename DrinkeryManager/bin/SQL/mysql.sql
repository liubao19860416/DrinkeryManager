drop table if exists desk_gsl;
create table desk_gsl(
  deskNo int(4),
  desktime TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
insert into desk_gsl(deskNo) values(8003);
insert into desk_gsl(deskNo) values(8004);


drop table if exists vigatableseries;
create table vigatableseries(
  id int(4) primary key auto_increment,
  seriesNo int(3) not null,
  series varchar(40)
);
insert into vigatableseries(seriesNo,series) values(10,'满汉全席');
insert into vigatableseries(seriesNo,series) values(5,'小菜一碟');


drop table if exists footopp_gsl;
create table footopp_gsl(
id int(4) primary key auto_increment,
productNo varchar(8),
productName varchar(20),
monad varchar(3),
unitPrice int(4),
seriesNo int(3)
);
insert into footopp_gsl(productNo,productName,monad,unitPrice,seriesNo) 
values('07122401','佛跳墙','盘',168,10);
insert into footopp_gsl(productNo,productName,monad,unitPrice,seriesNO) 
values('07133404','无脑泥猴桃','盘',205,10);


drop table if exists managerSystem_gsl;
create table managerSystem_gsl(
  system_id int(8) primary key auto_increment,
  accountno varchar(40) not null unique,
  pwd varchar(8) not null  check(length(pwd) between 3 and 8)
);
insert into managerSystem_gsl(accountno,pwd) values('Tsoft','111');

