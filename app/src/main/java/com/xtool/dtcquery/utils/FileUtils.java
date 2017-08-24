package com.xtool.dtcquery.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by liu on 2017/6/13.
 */

/**
 * 复制，删除文件及文件夹utils
 */
public class FileUtils {

    public void save(String fileName, String toFilePath) {

    }


        public void copy(String fromFile, String toFile)
    {
        //要复制的文件目录
        File[] currentFiles;
        File root = new File(fromFile);
        String name = root.getName();
        //如同判断SD卡是否存在或者文件是否存在
        //如果不存在则 return出去
        if(!root.exists())
        {
            return ;
        }
        
        //目标目录
        File targetDir = new File(toFile);
        //创建目录
        if(!targetDir.exists())
        {
            targetDir.mkdirs();
        }
        if(root.isFile()) {
        	CopySdcardFile(fromFile, toFile + "/" +name);
        }else {
	        //如果存在则获取当前目录下的全部文件 填充数组
	        currentFiles = root.listFiles();
	        //遍历要复制该目录下的全部文件
	        for(int i= 0;i<currentFiles.length;i++)
	        {
	            if(currentFiles[i].isDirectory())//如果当前项为子目录 进行递归
	            {
	                copy(currentFiles[i].getPath() + "/", toFile + "/"+ currentFiles[i].getName() + "/");
	            }else//如果当前项为文件则进行文件拷贝
	            {
	                CopySdcardFile(currentFiles[i].getPath(), toFile + "/"+ currentFiles[i].getName());
	            }
	        }
        }
    }


    //文件拷贝
    //要复制的目录下的所有非子目录(文件夹)文件拷贝
    private void CopySdcardFile(String fromFile, String toFile)
    {
        try
        {
            InputStream fosfrom = new FileInputStream(fromFile);
			String dirPath = toFile.substring(0, toFile.lastIndexOf("/"));
            //创建文件夹
    		File dir = new File(dirPath);
    		if(!dir.exists()) {
    			dir.mkdirs();
    		}
            OutputStream fosto = new FileOutputStream(toFile);
            byte bt[] = new byte[1024];
            int c;
            while ((c = fosfrom.read(bt)) > 0)
            {
                fosto.write(bt, 0, c);
            }
            fosfrom.close();
            fosto.close();

        } catch (Exception ex)
        {
        	 System.out.println("复制单个文件操作出错");   
             ex.printStackTrace();   
        }
    }
    /**
 * 删除单个文件
 * @param   filePath    被删除文件的文件名
 * @return 文件删除成功返回true，否则返回false
 */
public boolean deleteFile(String filePath) {
File file = new File(filePath);
    if (file.isFile() && file.exists()) {
    return file.delete();
    }
    return false;
}

/**
 * 删除文件夹以及目录下的文件
 * @param   filePath 被删除目录的文件路径
 * @return  目录删除成功返回true，否则返回false
 */
public boolean deleteDirectory(String filePath) {
boolean flag = false;
    //如果filePath不以文件分隔符结尾，自动添加文件分隔符
    if (!filePath.endsWith(File.separator)) {
        filePath = filePath + File.separator;
    }
    File dirFile = new File(filePath);
    if (!dirFile.exists() || !dirFile.isDirectory()) {
        return false;
    }
    flag = true;
    File[] files = dirFile.listFiles();
    //遍历删除文件夹下的所有文件(包括子目录)
    for (int i = 0; i < files.length; i++) {
        if (files[i].isFile()) {
        //删除子文件
            flag = deleteFile(files[i].getAbsolutePath());
            if (!flag) break;
        } else {
        //删除子目录
            flag = deleteDirectory(files[i].getAbsolutePath());
            if (!flag) break;
        }
    }
    if (!flag) return false;
    //删除当前空目录
    return dirFile.delete();
}

/**
 *  根据路径删除指定的目录或文件，无论存在与否
 *@param filePath  要删除的目录或文件
 *@return 删除成功返回 true，否则返回 false。
 */
public boolean DeleteFolder(String filePath) {
File file = new File(filePath);
    if (!file.exists()) {
        return false;
    } else {
        if (file.isFile()) {
        // 为文件时调用删除文件方法
            return deleteFile(filePath);
        } else {
        // 为目录时调用删除目录方法
            return deleteDirectory(filePath);
        }
    }
}
}

