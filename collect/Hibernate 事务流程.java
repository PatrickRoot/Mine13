//		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction(); //开启事务
		
		try
		{
			session.save(person);
			tx.commit();
		}
		catch(Exception ex)
		{
			System.out.println("增加用户异常发生！");
			if(null != tx)
			{
				tx.rollback();
			}
		}
		finally
		{
//			HibernateUtil.closeSession(session);
		}

