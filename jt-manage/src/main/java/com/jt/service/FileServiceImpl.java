package com.jt.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jt.vo.FileVo;

@Service	//默认对象是单例的,所以不要修改成员变量.
@PropertySource("classpath:/properties/image.properties")
public class FileServiceImpl implements FileService{
	//定义本地磁盘路径
	@Value("${image.localDirPath}")
	private String localPath;
	@Value("${image.localUrlPath}")
	private String urlpath;
	
	@Override
	public FileVo upload(MultipartFile uploadFile) {
		FileVo fileVo = new FileVo();
		//1.获取文件名称	abc.jpg/Jpg
		String fileName = uploadFile.getOriginalFilename();
		//2.将文件名称通通小写,方便以后判断
		fileName = fileName.toLowerCase();
		//3.利用正则表达式判断.
		//^开始,$结束,  . 除了回车换行之外的任意单个字符
		//+至少一个,*0个或者多个
		if(!fileName.matches("^.+\\.(png|jpg|gif)$")) {
			//表示文件类型不匹配
			fileVo.setError(1);
			return fileVo;
		}
		//4.判断是否为恶意程序
		try {
			BufferedImage image = 
					ImageIO.read(uploadFile.getInputStream());
			//4.1获取宽度和高度
			int width = image.getWidth();
			int height = image.getHeight();
			
			//4.2判断属性是否为0
			if(width == 0 || height == 0) {
				fileVo.setError(1);
				return fileVo;
			}
			
			//5.根据时间生成文件夹
			String dataDir = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			String localDir = localPath+dataDir;
			File dirFile = new File(localDir);
			if(!dirFile.exists()) {
				dirFile.mkdirs();
			}
			//表示文件夹已经生成!!!
			
			//6.防止文件名称重复...
			//6.1生成UUID
			String uuidName = UUID.randomUUID()
					.toString().replace("-", "");
			//6.2获取问价难顶类型 进行拼接
			String fileType = 
					fileName.substring(fileName.lastIndexOf("."));
			String realName = uuidName + fileType;
			//6.3实现文件上传.D:/eclipse_web_project/jt-software/jt-upload/2020/1/10/134354
			File realFile = new File(localDir+"/"+realName);
			uploadFile.transferTo(realFile);
			fileVo.setHeight(height);
			fileVo.setWidth(width);
			//设置图片虚拟访问路径 回显
			String realUruPath = urlpath + dataDir + "/" + realName;
			fileVo.setUrl(realUruPath);
		} catch (Exception e) {
			e.printStackTrace();
			//表示恶意程序
			fileVo.setError(1);
			return fileVo;
		}
		return fileVo;
	}

	
}
