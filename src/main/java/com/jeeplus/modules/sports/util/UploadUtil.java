package com.jeeplus.modules.sports.util;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.jeeplus.modules.sports.entity.UploadResult;
import com.jeeplus.modules.sports.entity.UploadToken;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;


public class UploadUtil {

	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 
	 * @return
	 */
	public UploadToken	getUploadToken()
	{
		long 			expireSeconds = 3600;
		UploadToken		uptoken;
		String			strToken;
		
		Date 		date 	= 	new Date(); 
		DateFormat 	format	=	new SimpleDateFormat("yyyyMMddHHmmss"); 
		String 		time	=	format.format(date);
		
		String 		accessKey 	= 	PublicConfig.ACCESS_KEY;
		String 		secretKey 	= 	PublicConfig.SECRET_KEY;
		String 		bucket 	 	= 	PublicConfig.BUCKET_NAME;
		Auth 		auth 		= 	Auth.create(accessKey, secretKey);
		
		StringMap putPolicy = new StringMap();
//		putPolicy.put("returnBody", "{\"key\":\"$(key)\",\"hash\":\"$(etag)\",\"bucket\":\"$(bucket)\",\"fsize\":$(fsize)}");
		putPolicy.put("saveKey", time);
//		putPolicy.put("bucket", bucket);
		
		uptoken 	= 	new UploadToken();
		
		strToken 	= 	auth.uploadToken(bucket, null, expireSeconds, putPolicy);
		
		uptoken.setUptoken(strToken);
		
		return	uptoken;
	}
	
	/**
	 * String localFilePath = "D:\\qiniu\\3.jpg";
	 * @param localFilePath
	 * @param fileName
	 * @return
	 */
	public int upload(String localFilePath, String fileName)
	{
		int		dwResult;
		
		//	����һ����ָ��Zone�����������
		Configuration cfg = new Configuration(Zone.zone2());
		
		//	...���������ο���ע��
		UploadManager uploadManager = new UploadManager(cfg);
		
		//...�����ϴ�ƾ֤��Ȼ��׼���ϴ�
		String accessKey = 	PublicConfig.ACCESS_KEY;
		String secretKey = 	PublicConfig.SECRET_KEY;
		String bucket 	 = 	PublicConfig.BUCKET_NAME;
		
		//	�����Windows����£���ʽ�� D:\\qiniu\\test.png
//		String localFilePath = "/home/qiniu/test.png";
//		String localFilePath = "D:\\qiniu\\3.jpg";
		
		//	Ĭ�ϲ�ָ��key������£����ļ����ݵ�hashֵ��Ϊ�ļ���
		String 	key 	= 	fileName;
		Auth 	auth   	= 	Auth.create(accessKey, secretKey);
		String 	upToken = 	auth.uploadToken(bucket);
		
		try 
		{
		    Response response = uploadManager.put(localFilePath, key, upToken);
		    
		    //	�����ϴ��ɹ��Ľ��
		    DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
		    System.out.println(putRet.key);
		    System.out.println(putRet.hash);	
		    
		    dwResult = 1;
		} 
		catch (QiniuException ex) {
			
		    Response r = ex.response;
		    System.err.println(r.toString());
		    
		    logger.debug("QiniuException.response", r.toString());
		    		    
		    try 
		    {
		        System.err.println(r.bodyString());
		        
		        logger.debug("QiniuException.response.bodyString", r.bodyString());
		    } 
		    catch (QiniuException ex2) {
		        //	ignore
		    }
		    
		    dwResult = 0;
		}
		
		return	dwResult;
	}
	
	/**
	 * 
	 * @param localFilePath
	 * @param fileName
	 * @return
	 */
	public UploadResult uploadwithResult(String localFilePath, String fileName)
	{	
		UploadResult	uploadResult = new UploadResult();
		
		//
		//	Set up default upload result
		//
		
		uploadResult.setName("");
    	uploadResult.setStatus(0);
    	uploadResult.setUrl("");
    	
		//	����һ����ָ��Zone�����������
		Configuration cfg = new Configuration(Zone.zone2());
		
		//	...���������ο���ע��
		UploadManager uploadManager = new UploadManager(cfg);
		
		//...�����ϴ�ƾ֤��Ȼ��׼���ϴ�
		String accessKey = 	PublicConfig.ACCESS_KEY;
		String secretKey = 	PublicConfig.SECRET_KEY;
		String bucket 	 = 	PublicConfig.BUCKET_NAME;
		
		//	Ĭ�ϲ�ָ��key������£����ļ����ݵ�hashֵ��Ϊ�ļ���
		String 	key 	= 	fileName;
		Auth 	auth   	= 	Auth.create(accessKey, secretKey);
		String 	upToken = 	auth.uploadToken(bucket);
		
		try 
		{
		    Response response = uploadManager.put(localFilePath, key, upToken);
		    
		    //	�����ϴ��ɹ��Ľ��
		    DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
		    System.out.println(putRet.key);
		    System.out.println(putRet.hash);			
		    
		    uploadResult.setName(fileName);
	    	uploadResult.setStatus(1);
	    	uploadResult.setUrl(PublicConfig.QINIU_STORAGE_LINK + fileName);	    	
		} 
		catch (QiniuException ex) {
			
		    Response r = ex.response;
		    System.err.println(r.toString());
		    
		    try 
		    {
		        System.err.println(r.bodyString());
		    } 
		    catch (QiniuException ex2) {
		        //	ignore
		    }
		}
		
		return	uploadResult;
	}	
	
	/**
	 * 
	 * @param InputStream inputStream
	 * @param String fileName
	 * @return
	 */
	public UploadResult uploadByStreamwithResult(InputStream inputStream, String fileName)
	{	
		String			qiniu_filename;
		String			suffix;
		
		UploadResult	uploadResult = new UploadResult();
		
		//
		//	Set up default upload result
		//
		
		uploadResult.setName("");
    	uploadResult.setStatus(0);
    	uploadResult.setUrl("");
    	
		//	����һ����ָ��Zone�����������
		Configuration cfg = new Configuration(Zone.zone2());
		
		//	...���������ο���ע��
		UploadManager uploadManager = new UploadManager(cfg);
		
		//...�����ϴ�ƾ֤��Ȼ��׼���ϴ�
		String accessKey = 	PublicConfig.ACCESS_KEY;
		String secretKey = 	PublicConfig.SECRET_KEY;
		String bucket 	 = 	PublicConfig.BUCKET_NAME;
		
		//	Ĭ�ϲ�ָ��key������£����ļ����ݵ�hashֵ��Ϊ�ļ���
		Auth 	auth   	= 	Auth.create(accessKey, secretKey);
		String 	upToken = 	auth.uploadToken(bucket);
		
		/* 获取文件的后缀 */
        suffix = fileName.substring(fileName.lastIndexOf("."));
        
        /* 使用时间戳生成文件名称 */
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String 	nowTime = sdf.format(new Date());
        qiniu_filename = nowTime + suffix;// 构建文件名称
        
		String 	key 	= 	qiniu_filename;
		
		try 
		{
			Response response = uploadManager.put(inputStream, key, upToken, null, null);
        
			//	解析上传成功的结果
			DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
			
			System.out.println(putRet.key);
			System.out.println(putRet.hash);
			
			uploadResult.setName(fileName);
	    	uploadResult.setStatus(1);
	    	uploadResult.setUrl(PublicConfig.QINIU_STORAGE_LINK + qiniu_filename);			
		} 
		catch (QiniuException ex) {
			
			Response r = ex.response;
			System.err.println(r.toString());
		
			try 
			{
				System.err.println(r.bodyString());
			} 
			catch (QiniuException ex2) {
				
				//	ignore
			}
		}

		return	uploadResult;
	}
}
