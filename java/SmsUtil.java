package com.sh.sendsms;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;



import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

/**
 * 	�о� ����    http://goinsms.com/
 *  ����jar��  
 *   httpcore-4.2.2.jar
 *   httpclient-4.2.1.jar
 * 	 commons-io-2.0.1.jar
 * 
  * ����: SmsUtil.java
  * ����: 
  * ����: JAVA
  * ����޸�ʱ��:2014-3-5 ����4:47:14
  * @since  2014-3-5
  * @author liaokn
 */

public class SmsUtil {
	String url = "http://api.goinsms.com/sms";
	// api�˺�(����ϵ���ۻ�ȡ,������վ��½�˺�)
	String userId = "a0e72f36-e80d-4931-b70a-b2bd41d8ce2b";
	// api����(������վ��½����)
	String passwd = "ebbfd3b1da";

	public String sendSms(String content, String mobiles) {
		String result = null;
		String posturl = url + "/http/submit";
		String message = "{'content':'" + content + "'," + "'mobiles':'"
				+ mobiles + "'," + "'passwd':'" + passwd + "'," + "'userId':'"
				+ userId + "'}";
	
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(posturl);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("message", message));
		try {
			httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				InputStream instream = null;
				try {
					instream = entity.getContent();
					result = IOUtils.toString(instream, "utf-8");
				} finally {
					if (instream != null)
						instream.close();
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public String getReport() {
		String result = null;
		String posturl = url + "/http/report";
		String message = "{\"passwd\":\"" + passwd + "\",\"userId\":\""
				+ userId + "\"}";
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(posturl);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("message", message));
		try {
			httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				InputStream instream = null;
				try {
					instream = entity.getContent();
					result = IOUtils.toString(instream, "utf-8");
				} finally {
					if (instream != null)
						instream.close();
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public String getDeliver() {
		String result = null;
		String posturl = url + "/http/deliver";
		String message = "{\"passwd\":\"" + passwd + "\",\"userId\":\""
				+ userId + "\"}";
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(posturl);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("message", message));
		try {
			httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				InputStream instream = null;
				try {
					instream = entity.getContent();
					result = IOUtils.toString(instream, "utf-8");
				} finally {
					if (instream != null)
						instream.close();
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public String getBalance() {
		String result = null;
		String posturl = url + "/http/balance";
		String message = "{\"passwd\":\"" + passwd + "\",\"userId\":\""
				+ userId + "\"}";
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(posturl);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("message", message));
		try {
			httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				InputStream instream = null;
				try {
					instream = entity.getContent();
					result = IOUtils.toString(instream, "utf-8");
				} finally {
					if (instream != null)
						instream.close();
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SmsUtil api = new SmsUtil();
		System.out.println("*************���Ͷ���*************");
		String content = "ף�����տ��֣����쿪�ģ�";
		String mobiles = "18616131909";
		System.out.println("����:" + mobiles + " ����:" + content);
		System.out.println("���:" + api.sendSms(content, mobiles));
		System.out.println("*************״̬����*************");
		System.out.println("���:" + api.getReport());
		System.out.println("*************��ȡ����*************");
		System.out.println("���:" + api.getDeliver());
		System.out.println("*************��ȡ���*************");
		System.out.println("���:" + api.getBalance());

	}

}