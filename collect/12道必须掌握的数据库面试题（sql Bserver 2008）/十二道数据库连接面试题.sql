--1、查询"1"课程比"2"课程成绩高的所有学生的学号；
select d.S# from 
(select b.S#,b.score as b_score,c.score as c_score from
(select S#,score from Sc WHERE C#=1) b inner join
(select S#,score  from Sc WHERE C#=2) c on b.S#=c.S#) d
where d.b_score>d.c_score
--2、查询平均成绩大于60分的学号和平均成绩；
select S#,AVG(score) from Sc Group by S# having AVG(score)>60
--3、查询所有同学的学号、姓名、选课数、总成绩；
SELECT a.*,
		CASE 
		WHEN Num IS null THEN 0 
		ELSE Num END AS '课程总数',
		CASE 
		WHEN Total IS NULL THEN 0
		ELSE Total END AS '总成绩'
FROM
(select S#,Sname from Student) a left join 
(select S#,COUNT(*) AS Num,SUM(score) as Total from Sc Group BY S#) b --找所有同学的选课数和总成绩
on a.S#=b.S#
--4、查询姓"李"的老师个数；
select COUNT(*) from Teacher where Tname LIKE '李%'
--5、查询没学过"叶平"老师课的同学的学号、姓名；
--(把学过学生的学号先找出来)
SELECT d.* FROM Student d left join 
(
select distinct a.S# from Sc a inner join 
(select C# from Course where T#=
(select T# from Teacher where Tname='叶平')) b on a.C#=b.C#
) c on c.S#=d.S# where c.S# is null
--6、查询学过"1"并且也学过编号"2"课程的同学的学号、姓名；
select b.* from
(select Distinct S# from Sc where C#=1 or C#=2 group by S# having COUNT(*)=2) a inner join 
(select S#,Sname from Student) b on a.S#=b.S#
--7、查询学过"叶平"老师所交的所有课程的同学的学号、姓名；
select a.S# from
(select * from Sc) a inner join 
(
select C# from Course where T#=
(select T# from Teacher where Tname='叶平')
) b on a.C#=b.C# group by a.S# having COUNT(*)=
(select COUNT(*) from Course where T#=
(select T# from Teacher where Tname='叶平'))
--9、查询所有课程成绩小于60的同学的学号、姓名；
select b.* from
(select distinct S# from Sc WHERE score<60) a inner join
(select S#,Sname from Student) b on a.S#=b.S#
--10、查询没有学所有课的同学的学号、姓名；
select a.* from
(select S#,Sname from Student) a left join
(select S# from Sc group by S# having COUNT(*)=(select COUNT(*) from Course)) b
on a.S#=b.S# where b.S# is null
--11、查询至少有一门课与"张三"的同学相同的同学的学号和姓名；
select distinct a.S#,c.Sname from
(select S#,C# from Sc where S#!=(select S# from Student where Sname='张三')) a inner join
(select C# from Sc where S#=(select S# from Student where Sname='张三')) b on a.C#=b.C# inner join 
(select S#,Sname from Student) c on a.S#=c.S#
--12、查询学过学号为"1"同学所有课的其他同学学号和姓名；
select d.* from
(
select S#  from
(select C# from Sc WHERE S#=1) a inner join
(select * from Sc where S#!=1) b on a.C#=b.C# 
group by b.S# having COUNT(*)=(select COUNT(*) from Sc WHERE S#=1)
) c inner join
(select  S#,Sname from Student) d on c.S#=d.S#

