package org.sixlab.myqqtop.util;

public class UrlUtil
{
	public static String getUrl()
	{
		String url = "";
		
		StringBuffer sbUrl = new StringBuffer();
		// oauth 1.0
		// sbUrl.append("http://open.t.qq.com/api/trends/t");
		// sbUrl.append("reqnum=10&pos=0&type=0&format=json");
		// sbUrl.append("&oauth_token=82075fecd93e4b049ca5c1d5bd5fd6e0");
		// sbUrl.append("&oauth_nonce=73dc9e73bfac443a67eccb078502d487");
		// sbUrl.append("&oauth_consumer_key=801058005&oauth_signature_method=HMAC-SHA1");
		// sbUrl.append("&oauth_version=1.0&oauth_timestamp="+(new
		// Date().getTime())/1000);
		// sbUrl.append("&oauth_signature=mmMfNTIWmMj4MNIoeJ8f6QhBH0A%3D");
		
		// oauth 2.0
		sbUrl.append("https://open.t.qq.com/api/trends/t?");
		sbUrl.append("reqnum=10&pos=0&type=0&format=json");
		// sbUrl.append("&access_token=d7c2946b57f90dae0c4e9938b2217ed7");
		sbUrl.append("&oauth_consumer_key=801058005");
		// sbUrl.append("&openid=94F7CFE86029D94D337F306FC2F2C058");
		// sbUrl.append("&oauth_version=2.a&clientip=221.203.22.243&scope=all");
		// sbUrl.append("&appfrom=php-sdk2.0beta&seqid=1372238656&serverip=183.60.10.172");
		url = sbUrl.toString();
		return url;
	}
}
