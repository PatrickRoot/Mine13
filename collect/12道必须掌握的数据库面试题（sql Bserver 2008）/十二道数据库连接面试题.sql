--1����ѯ"1"�γ̱�"2"�γ̳ɼ��ߵ�����ѧ����ѧ�ţ�
select d.S# from 
(select b.S#,b.score as b_score,c.score as c_score from
(select S#,score from Sc WHERE C#=1) b inner join
(select S#,score  from Sc WHERE C#=2) c on b.S#=c.S#) d
where d.b_score>d.c_score
--2����ѯƽ���ɼ�����60�ֵ�ѧ�ź�ƽ���ɼ���
select S#,AVG(score) from Sc Group by S# having AVG(score)>60
--3����ѯ����ͬѧ��ѧ�š�������ѡ�������ܳɼ���
SELECT a.*,
		CASE 
		WHEN Num IS null THEN 0 
		ELSE Num END AS '�γ�����',
		CASE 
		WHEN Total IS NULL THEN 0
		ELSE Total END AS '�ܳɼ�'
FROM
(select S#,Sname from Student) a left join 
(select S#,COUNT(*) AS Num,SUM(score) as Total from Sc Group BY S#) b --������ͬѧ��ѡ�������ܳɼ�
on a.S#=b.S#
--4����ѯ��"��"����ʦ������
select COUNT(*) from Teacher where Tname LIKE '��%'
--5����ѯûѧ��"Ҷƽ"��ʦ�ε�ͬѧ��ѧ�š�������
--(��ѧ��ѧ����ѧ�����ҳ���)
SELECT d.* FROM Student d left join 
(
select distinct a.S# from Sc a inner join 
(select C# from Course where T#=
(select T# from Teacher where Tname='Ҷƽ')) b on a.C#=b.C#
) c on c.S#=d.S# where c.S# is null
--6����ѯѧ��"1"����Ҳѧ�����"2"�γ̵�ͬѧ��ѧ�š�������
select b.* from
(select Distinct S# from Sc where C#=1 or C#=2 group by S# having COUNT(*)=2) a inner join 
(select S#,Sname from Student) b on a.S#=b.S#
--7����ѯѧ��"Ҷƽ"��ʦ���������пγ̵�ͬѧ��ѧ�š�������
select a.S# from
(select * from Sc) a inner join 
(
select C# from Course where T#=
(select T# from Teacher where Tname='Ҷƽ')
) b on a.C#=b.C# group by a.S# having COUNT(*)=
(select COUNT(*) from Course where T#=
(select T# from Teacher where Tname='Ҷƽ'))
--9����ѯ���пγ̳ɼ�С��60��ͬѧ��ѧ�š�������
select b.* from
(select distinct S# from Sc WHERE score<60) a inner join
(select S#,Sname from Student) b on a.S#=b.S#
--10����ѯû��ѧ���пε�ͬѧ��ѧ�š�������
select a.* from
(select S#,Sname from Student) a left join
(select S# from Sc group by S# having COUNT(*)=(select COUNT(*) from Course)) b
on a.S#=b.S# where b.S# is null
--11����ѯ������һ�ſ���"����"��ͬѧ��ͬ��ͬѧ��ѧ�ź�������
select distinct a.S#,c.Sname from
(select S#,C# from Sc where S#!=(select S# from Student where Sname='����')) a inner join
(select C# from Sc where S#=(select S# from Student where Sname='����')) b on a.C#=b.C# inner join 
(select S#,Sname from Student) c on a.S#=c.S#
--12����ѯѧ��ѧ��Ϊ"1"ͬѧ���пε�����ͬѧѧ�ź�������
select d.* from
(
select S#  from
(select C# from Sc WHERE S#=1) a inner join
(select * from Sc where S#!=1) b on a.C#=b.C# 
group by b.S# having COUNT(*)=(select COUNT(*) from Sc WHERE S#=1)
) c inner join
(select  S#,Sname from Student) d on c.S#=d.S#

