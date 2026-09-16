use ssm_emp;
create table dept(
    -- 部门编号
                     deptno      int unsigned auto_increment primary key,
    -- 部门名称
                     dname       varchar(15)     ,
    -- 部门所在位置
                     loc         varchar(50)
)engine = InnoDB;

create table emp(
    -- 雇员编号
                    empno           int unsigned auto_increment primary key,
    -- 雇员姓名
                    ename           varchar(15)     ,
    -- 雇员职位
                    job             varchar(10)     ,
    -- 雇员对应的领导的编号
                    mgr             int unsigned    ,
    -- 雇员的雇佣日期
                    hiredate        date            ,
    -- 雇员的基本工资
                    sal             decimal(7,2)    ,
    -- 奖金
                    comm            decimal(7,2)    ,
    -- 所在部门
                    deptno          int unsigned    ,
                    foreign key(deptno) references dept(deptno)
)engine = innodb;


