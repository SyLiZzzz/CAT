package com.demo.util;//�ӿ����ͣ��������ߴ������Žӿڣ�֧�ַ�����֤����š�����֪ͨ���ŵȡ�
// �˻�ע�᣺��ͨ���õ�ַ��ͨ�˻�http://sms.ihuyi.com/register.html
// ע�����
//��1�������ڼ䣬����Ĭ�ϵ�ģ����в��ԣ�Ĭ��ģ������ӿ��ĵ���
//��2����ʹ��APIID���鿴APIID���¼�û�����->��֤�����->��Ʒ����->APIID���� APIkey�����ýӿڣ�
//��3���ô���������뻥�����߶��Žӿڲο�ʹ�ã��ͻ��ɸ���ʵ����Ҫ���б�д��

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import org.dom4j.Document;   
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;   
import org.dom4j.Element;   

import com.demo.util.StringUtil;
public class sendsms {
	private static String Url = "http://106.ihuyi.cn/webservice/sms.php?method=Submit";
	public static String SMS(String phone) throws HttpException, IOException{

		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod(Url);

		client.getParams().setContentCharset("utf-8");
		method.setRequestHeader("ContentType","application/x-www-form-urlencoded;charset=utf-8");
		//随机编号
		int mobile_code = (int)((Math.random()*9+1)*100000);

		String content = new String("您的验证码是：" + mobile_code + "。请不要把验证码泄露给其他人。");

		NameValuePair[] data = {//提交短信
				new NameValuePair("account", ""), //查看用户名是登录用户中心->验证码短信->产品总览->APIID
				new NameValuePair("password", ""),  //查看密码请登录用户中心->验证码短信->产品总览->APIKEY
				//new NameValuePair("password", util.StringUtil.MD5Encode("密码")),
				new NameValuePair("mobile", phone),
				new NameValuePair("content", content),
		};
		method.setRequestBody(data);

		try {
			client.executeMethod(method);

			String SubmitResult =method.getResponseBodyAsString();

			//System.out.println(SubmitResult);+

			Document doc = DocumentHelper.parseText(SubmitResult);
			Element root = doc.getRootElement();

			String code = root.elementText("code");
			String msg = root.elementText("msg");
			String smsid = root.elementText("smsid");

			System.out.println(code);
			System.out.println(msg);
			System.out.println(smsid);
			System.out.println(mobile_code);

			if("2".equals(code)){
				System.out.println("短信提交成功");
				System.out.println(mobile_code);
				return mobile_code+"";
			}

		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";


	}
	
}