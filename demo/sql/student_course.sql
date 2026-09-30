create database ssm_edu;
use ssm_edu;

-- 学生表
create table student(
    sid         int unsigned auto_increment primary key comment '学生编号',
    sname       varchar(20)     not null comment '学生姓名',
    gender      char(1)         default '男' comment '性别',
    age         tinyint unsigned comment '年龄',
    major       varchar(30) comment '专业',
    phone       varchar(15) comment '手机号'
) engine = InnoDB default charset = utf8mb4;

-- 课程表
create table course(
    cid         int unsigned auto_increment primary key comment '课程编号',
    cname       varchar(30)     not null comment '课程名称',
    teacher     varchar(20) comment '授课老师',
    credit      decimal(3,1)    default 0 comment '学分',
    hours       int unsigned    default 0 comment '课时'
) engine = InnoDB default charset = utf8mb4;

-- 选课表（中间表，多对多关联）
create table student_course(
    sid         int unsigned not null comment '学生编号',
    cid         int unsigned not null comment '课程编号',
    score       decimal(5,2) comment '成绩',
    primary key (sid, cid),
    foreign key (sid) references student(sid) on delete cascade,
    foreign key (cid) references course(cid) on delete cascade
) engine = InnoDB default charset = utf8mb4;

-- 初始化数据
insert into student (sname, gender, age, major, phone) values
('张三', '男', 20, '计算机科学与技术', '13800001111'),
('李四', '女', 21, '软件工程',        '13800002222'),
('王五', '男', 19, '人工智能',        '13800003333');

insert into course (cname, teacher, credit, hours) values
('Java 程序设计',   '赵老师', 4.0, 64),
('MySQL 数据库',    '孙老师', 3.0, 48),
('数据结构',        '周老师', 3.5, 56);

insert into student_course (sid, cid, score) values
(1, 1, 85.0), (1, 2, 90.5),
(2, 1, 78.5), (2, 3, 82.0),
(3, 2, 92.0);
