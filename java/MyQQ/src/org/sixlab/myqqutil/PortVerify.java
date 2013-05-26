package org.sixlab.myqqutil;

public class PortVerify
{
	public static int verifyString(String input)
	{
		if(input!=null){
			if (input.length()==0)
			{
				return -1;
			}else{
				for (int i = 0; i < input.length(); i++)
				{
					if(!Character.isDigit(input.charAt(i))){
						return -1;
					}
				}
				int temp = Integer.parseInt(input);
				if (temp<=0|temp>=65536)
				{
					return -1;
				}
				return temp;
			}
		}
		return -1;
	}
}
