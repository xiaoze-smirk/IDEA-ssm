create table tbl_course_type(
   type_id int primary key,
   type_name varchar2(30) not null
 );

create table tbl_course(
   course_no varchar2(4) primary key,
   course_name varchar2(30) not null,
   course_hours int not null,
   type_id int not null,
   course_status varchar2(1) not null,
   course_reqs varchar2(20) not null,
   course_point decimal(3,1) check(course_point>=0.0),
   course_memo varchar2(300),
   course_textbook_pic blob,
   constraint FK_COURSE_TYPE FOREIGN KEY (type_id) references tbl_course_type(type_id) 
);


 
 insert into tbl_course_type(type_name) values('רҵ����');
 insert into tbl_course_type(type_name) values('רҵ��ѡ');
 insert into tbl_course_type(type_name) values('Уѡ��');
 insert into tbl_course_type(type_name) values('ר�ҽ���');
 
 
create table tbl_users(
   user_no    varchar2(6) primary key,
   user_pwd   varchar2(6) not null,
   user_name  varchar2(30) not null
);

insert into tbl_users values('000101','123456','������');
insert into tbl_users values('000102','123456','����');

alter table tbl_course add course_textbook_pic mediumblob;

