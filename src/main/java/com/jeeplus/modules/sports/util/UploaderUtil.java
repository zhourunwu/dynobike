package com.jeeplus.modules.sports.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.jeeplus.modules.sports.entity.UploadResult;

/**
 * �ϴ�������
 * @author xxzkid
 *
 */
public final class UploaderUtil {

    private static final Log log = LogFactory.getLog(UploaderUtil.class);

    /**
     * springmvc �ļ��ϴ�
     * @param request 
     * @param basePath ����·�� ��/��ͷ
     * @param formFileName ����file��nameֵ
     * @param dir �ϴ�Ŀ���ļ��� ��/��ͷ
     * @param allowedPattern ����ĸ�ʽ
     * @param fileSize �����ϴ��Ĵ�С ��λKB
     * @return 
     */
    public static UploaderResult uploader(
    		HttpServletRequest 	request, 
    		String 				basePath, 
    		String 				formFileName, 
    		String 				dir, 
    		List<String> 		allowedPattern, 
    		long 				fileSize) 
    {
        String successCode = UploaderResult.CODE_0;
        String successInfo = UploaderResult.DESC_0;

        UploaderResult result = new UploaderResult();

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

        File fileDir = new File(basePath + dir);
        if 	(!fileDir.exists() && !fileDir.mkdirs()) {
        	
            log.error("upload mkdirs failed.");
            throw new RuntimeException("upload mkdirs failed.");
        }

        /* �ļ�·������ */
        List<String> list = new ArrayList<String>();

        /* ҳ��ؼ����ļ��� */
        List<MultipartFile> multipartFileList = multipartRequest.getFiles(formFileName);
        if 	(multipartFileList.isEmpty()) {
        	
            result.setCode(successCode);
            
            // ���һ����·��
            list.add(UploaderResult.EMPTY_URL);
            result.setUrls(list);
            result.setDesc(successInfo);
            
            return 	result;
        }

        if 	(	allowedPattern == null || 
        		allowedPattern.size() == 0) {
        	
            allowedPattern = new ArrayList<String>();
            allowedPattern.add(".gif");
            allowedPattern.add(".jpg");
            allowedPattern.add(".jpeg");
            allowedPattern.add(".png");
        }

        if (fileSize <= 0) {
            fileSize = 1 * 1024 * 1024;
        } else {
            fileSize *= 1024;
        }

        try 
        {
            for (MultipartFile multipartFile : multipartFileList) {
            	
                String originalFilename = multipartFile.getOriginalFilename();
                if 	("".equals(originalFilename)) {
                	
                    result.setCode(successCode);
                    
                    // ���һ����·��
                    list.add(UploaderResult.EMPTY_URL);
                    result.setUrls(list);
                    result.setDesc(successInfo);
                    
                    return result;
                }
                
                /* ��ȡ�ļ��ĺ�׺ */
                String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
                if 	(!allowedPattern.contains(suffix.toLowerCase())) {
                	
                    result.setCode(UploaderResult.CODE_1);
                    result.setUrls(null);
                    result.setDesc(UploaderResult.DESC_1);
                    return result;
                }
                
                if 	(multipartFile.getSize() > fileSize) {
                	
                    result.setCode(UploaderResult.CODE_2);
                    result.setUrls(null);
                    result.setDesc(String.format(UploaderResult.DESC_2, fileSize));
                    return result;
                }
                
                /* ʹ��ʱ��������ļ����� */
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
                String nowTime = sdf.format(new Date());
                String filename = nowTime + suffix;// �����ļ�����

                /** ƴ���������ļ�����·�����ļ� **/
                String fullFilename = fileDir + "/" + filename;
                File file = new File(fullFilename);
                multipartFile.transferTo(file);
                list.add(dir + "/" + filename);
            }
            
            result.setCode(successCode);
            result.setUrls(list);
            result.setDesc(successInfo);
        } catch (IllegalStateException e) {
        	
            e.printStackTrace();
            
        } catch (IOException e) {
            
        	e.printStackTrace();
        }
        
        log.info("返回内容为：" + result.toString());
        return result;
    }

    /**
     * springmvc �ļ��ϴ�
     * @param request 
     * @param basePath ����·�� ��/��ͷ
     * @param formFileName ����file��nameֵ
     * @param dir �ϴ�Ŀ���ļ��� ��/��ͷ
     * @param allowedPattern ����ĸ�ʽ
     * @param fileSize �����ϴ��Ĵ�С ��λKB
     * @return 
     */
    public static UploaderResult uploader_qiniu(
    		HttpServletRequest 	request, 
    		String 				basePath, 
    		String 				formFileName, 
    		String 				dir, 
    		List<String> 		allowedPattern, 
    		long 				fileSize) 
    {
        String successCode = UploaderResult.CODE_0;
        String successInfo = UploaderResult.DESC_0;
        UploadResult	uploadResult;
        
        UploaderResult result = new UploaderResult();

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

        File fileDir = new File(basePath + dir);
        if 	(!fileDir.exists() && !fileDir.mkdirs()) {
        	
            log.error("upload mkdirs failed.");
            throw new RuntimeException("upload mkdirs failed.");
        }

        /* �ļ�·������ */
        List<String> 	list = new ArrayList<String>();

        /* file name List */
        List<String> 	filenameList = new ArrayList<String>();
        
        /* ҳ��ؼ����ļ��� */
        List<MultipartFile> multipartFileList = multipartRequest.getFiles(formFileName);
        if 	(multipartFileList.isEmpty()) {
        	
            result.setCode(successCode);
            
            // ���һ����·��
            list.add(UploaderResult.EMPTY_URL);
            filenameList.add(UploaderResult.EMPTY_URL);
            
            result.setUrls(list);
            result.setNames(filenameList);
            result.setDesc(successInfo);
            
            return 	result;
        }

        if 	(	allowedPattern == null || 
        		allowedPattern.size() == 0) {
        	
            allowedPattern = new ArrayList<String>();
            allowedPattern.add(".gif");
            allowedPattern.add(".jpg");
            allowedPattern.add(".jpeg");
            allowedPattern.add(".png");
        }

        if (fileSize <= 0) {
            fileSize = 1 * 1024 * 1024;
        } else {
            fileSize *= 1024;
        }

        try 
        {
            for (MultipartFile multipartFile : multipartFileList) {
            	
                String originalFilename = multipartFile.getOriginalFilename();
                if 	("".equals(originalFilename)) {
                	
                    result.setCode(successCode);
                    
                    // ���һ����·��
                    list.add(UploaderResult.EMPTY_URL);
                    filenameList.add(UploaderResult.EMPTY_URL);
                    
                    result.setUrls(list);
                    result.setNames(filenameList);
                    result.setDesc(successInfo);
                    
                    return result;
                }
                
                /* ��ȡ�ļ��ĺ�׺ */
                String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
                if 	(!allowedPattern.contains(suffix.toLowerCase())) {
                	
                    result.setCode(UploaderResult.CODE_1);
                    
                    result.setUrls(null);
                    result.setNames(null);
                    result.setDesc(UploaderResult.DESC_1);
                    return result;
                }
                
                if 	(multipartFile.getSize() > fileSize) {
                	
                    result.setCode(UploaderResult.CODE_2);
                    result.setUrls(null);
                    result.setNames(null);
                    result.setDesc(String.format(UploaderResult.DESC_2, fileSize));
                    return result;
                }
                
                /* ʹ��ʱ��������ļ����� */
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
                String nowTime = sdf.format(new Date());
                String filename = nowTime + suffix;// �����ļ�����

                /** ƴ���������ļ�����·�����ļ� **/
                String fullFilename = fileDir + "/" + filename;
                File file = new File(fullFilename);
                
                //multipartFile.transferTo(file);
                
                //------------------------------------------------------------------------------
                //	Call UploadUtil
                //------------------------------------------------------------------------------
                
                UploadUtil	uploadUtil = new UploadUtil();
                
                uploadResult = uploadUtil.uploadByStreamwithResult(multipartFile.getInputStream(), filename);
                
                //------------------------------------------------------------------------------
                
                //list.add(dir + "/" + filename);
                list.add(uploadResult.getUrl());
                
                filenameList.add(originalFilename);
            }
            
            result.setCode(successCode);
            result.setUrls(list);
            result.setNames(filenameList);
            result.setDesc(successInfo);
            
        } catch (IllegalStateException e) {
        	
            e.printStackTrace();
            
        } catch (IOException e) {
            
        	e.printStackTrace();
        }
        
        log.info("返回内容为：" + result.toString());
        return result;
    }
    
    /**
     * �ϴ��󷵻ؽ��
     * @author xxzkid
     */
    public static class UploaderResult {
        
        /** @see UploadResult#DESC_0 */
        public static final String CODE_0 = "0";
        
        /** @see UploadResult#DESC_1 */
        public static final String CODE_1 = "1";
        
        /** @see UploadResult#DESC_2 */
        public static final String CODE_2 = "2";
        
        /** �ɹ� success */
        public static final String DESC_0 = "成功";
        
        /** �ϴ��ļ���ʽ����ȷ upload pattern not true */
        public static final String DESC_1 = "上传文件格式不正确";
        
        /** �ϴ��ļ���С������%sKB upload file size gt %s KB */
        public static final String DESC_2 = "上传文件大小超过了%sKB";

        /** ��·�� */
        public static final String EMPTY_URL = "";
        
        private String 	code; // ������
        private String 	desc; // ����        
        List<String> 	names = new ArrayList<String>();
		List<String>	urls  = new ArrayList<String>(); // �ϴ��ļ�·���ļ���

		//	code
        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        //	desc
        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        //	names
        public List<String> getNames() {
			return names;
		}

		public void setNames(List<String> names) {
			this.names = names;
		}
		
		//	urls
        public void setUrls(List<String> urls) {
            this.urls = urls;
        }

        public List<String> getUrls() {
            return urls;
        }

        /**
         * 
         */
        @Override
        public String toString() {
            return "UploadResult [code=" + code + ", desc=" + desc + ", urls=" + urls + "]";
        }
    }

}
