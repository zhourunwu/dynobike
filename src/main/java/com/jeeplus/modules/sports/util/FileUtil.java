package com.jeeplus.modules.sports.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileUtil {

	/**
	 * 
	 * @param destFileName
	 * @return
	 */
	public static boolean CreateFile(String destFileName) 
	{
		File file = new File(destFileName);
		
		if 	(file.exists()) 
		{
			System.out.println("鍒涘缓鍗曚釜鏂囦欢" + destFileName + "澶辫触锛岀洰鏍囨枃浠跺凡瀛樺湪锛�");
			return 	false;
		}
		
		if 	(destFileName.endsWith(File.separator)) 
		{
			System.out.println("鍒涘缓鍗曚釜鏂囦欢" + destFileName + "澶辫触锛岀洰鏍囦笉鑳芥槸鐩綍锛�");
			return 	false;
		}
		
		if 	(!file.getParentFile().exists()) 
		{			
			System.out.println("鐩爣鏂囦欢鎵�鍦ㄨ矾寰勪笉瀛樺湪锛屽噯澶囧垱寤恒�傘�傘��");
			if 	(!file.getParentFile().mkdirs()) 
			{
				System.out.println("鍒涘缓鐩綍鏂囦欢鎵�鍦ㄧ殑鐩綍澶辫触锛�");
				return 	false;
			}
		}
		
		// 	鍒涘缓鐩爣鏂囦欢
		try 
		{
			if 	(file.createNewFile()) 
			{
				System.out.println("鍒涘缓鍗曚釜鏂囦欢" + destFileName + "鎴愬姛锛�");				
				return true;
			} 
			else 
			{
				System.out.println("鍒涘缓鍗曚釜鏂囦欢" + destFileName + "澶辫触锛�");
				return false;
			}
		} 
		catch (IOException e) 
		{		
			e.printStackTrace();
		
			System.out.println("鍒涘缓鍗曚釜鏂囦欢" + destFileName + "澶辫触锛�");
			return 	false;
		}
	}

	/**
	 * 
	 * @param destDirName
	 * @return
	 */
	public static boolean IsDirExists(String destDirName)
	{
		File 	dir = new File(destDirName);
		
		if	(dir.exists()) 
		{
			return 	true;
		}
		
		return	false;
	}
	
	/**
	 * 
	 * @param destDirName
	 * @return
	 */
	public static boolean createDir(String destDirName) 
	{
		File 	dir = new File(destDirName);
		
		if	(dir.exists()) 
		{
			System.out.println("鍒涘缓鐩綍" + destDirName + "澶辫触锛岀洰鏍囩洰褰曞凡瀛樺湪锛�");
			return 	false;
		}
		
		if	(!destDirName.endsWith(File.separator))
			destDirName = destDirName + File.separator;

		//	鍒涘缓鍗曚釜鐩綍
		if	(dir.mkdirs()) 
		{		
			System.out.println("鍒涘缓鐩綍" + destDirName + "鎴愬姛锛�");
			return 	true;
		} 
		else {
			
			System.out.println("鍒涘缓鐩綍" + destDirName + "鎴愬姛锛�");
			return 	false;
		}
	}
	
	/**
	 * 鍙栧緱鏂囦欢澶у皬
	 * @param f
	 * @return
	 * @throws Exception
	 */
	public static long	getFileSizes(File f)
	{
		FileInputStream		fis = null;
		long 				s = 0;
		
		try
		{
			if 	(f.exists()) 
			{
				fis = new FileInputStream(f);
				s = fis.available();
				fis.close();
			} 
			else 
			{
				
			}
	    }
		catch(Exception ex)
		{
			
		}
	    
	    return 	s;
	}
	
	/**
	 * 
	 * @param prefix
	 * @param suffix
	 * @param dirName
	 * @return
	 */
	public static String createTempFile(String prefix, String suffix, String dirName) 
	{
		File tempFile = null;
		
		try
		{
			if	(dirName == null) 
			{				
				// 	鍦ㄩ粯璁ゆ枃浠跺す涓嬪垱寤轰复鏃舵枃浠�
				tempFile = File.createTempFile(prefix, suffix);
				return 	tempFile.getCanonicalPath();
			}
			else 
			{				
				File dir = new File(dirName);
				
				// 	濡傛灉涓存椂鏂囦欢鎵�鍦ㄧ洰褰曚笉瀛樺湪锛岄鍏堝垱寤�
				if	(!dir.exists()) 
				{					
					if	(!FileUtil.createDir(dirName))	
					{
						System.out.println("鍒涘缓涓存椂鏂囦欢澶辫触锛屼笉鑳藉垱寤轰复鏃舵枃浠舵墍鍦ㄧ洰褰曪紒");
						return 	null;
					}
				}
				
				tempFile = File.createTempFile(prefix, suffix, dir);
				return 	tempFile.getCanonicalPath();
			}
			
		} 
		catch(IOException e) 
		{			
			e.printStackTrace();
			System.out.println("鍒涘缓涓存椂鏂囦欢澶辫触" + e.getMessage());
			return 	null;
		}
	}
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) 
	{
		// 	鍒涘缓鐩綍
		String dirName = "c:/test/test0/test1";
		FileUtil.createDir(dirName);

		// 	鍒涘缓鏂囦欢
		String fileName = dirName + "/test2/testFile.txt";
		FileUtil.CreateFile(fileName);
		
		// 	鍒涘缓涓存椂鏂囦欢
		String prefix = "temp";
		String suffix = ".txt";
		for(int i = 0; i < 10; i++) 
		{
			System.out.println("鍒涘缓浜嗕复鏃舵枃浠�:" + FileUtil.createTempFile(prefix, suffix, dirName));
		}
	}
	
	/**
	 * 鍒犻櫎鏂囦欢 
	 * filePath 鏂囦欢璺緞
	 * 浼犲叆涓�涓� 鍦板潃
	 * fName 鏂囦欢path
	 * */
	public boolean deleteFileByPath(String filePath, String fName)
	{
		boolean	bOpResult;
		
		File file = new File(filePath, fName);
		if	(!file.exists())
		{
			return true; //鏂囦欢涓嶅瓨鍦�
		}
		else
		{
			try 
			{
				bOpResult = file.delete();
			} catch (Exception e) {
				bOpResult = false;
			}
			
			return	bOpResult;
		}
	}
}
