package com.zline.zlogistics.biz.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


/**
 *  该监听器是用在项目发布的时候，对于配置项缺失等问题，是等到项目发布的时候才会发现<br/>
 *  后面开发出从工程中导入文件到配置文件的maven插件后，可以把该部分去掉（不用在web.xml中配置监听器）<br/>
 *  而是在pom.xml中配置一个导入配置文件的maven插件<br>
 *  同时利用maven打war包的一个配置项导入功能从外部配置项文件中读取配置项到项目中的xml中<br>
 *  
 * @author lilin
 *
 */
public class ZLResourceLoadingListener implements ServletContextListener {

	private final static String WINDOWS = "windows";

	private final static String DefaultWorkingProjectProperties = "_project.properties";

	private final static String DefaultProjectProperties = "project.properties";

	private static File findFirstFile(File parentFile, String fileName)
			throws Exception {
		if (parentFile.isFile()) {
			return DefaultProjectProperties.equals(parentFile.getName()) ? parentFile
					: null;
		}
		if (parentFile.isDirectory()) {
			File[] subFiles = parentFile.listFiles();
			for (File subFile : subFiles) {
				File subFile1 = findFirstFile(subFile, fileName);
				if (null != subFile1)
					return subFile1;
			}
		}
		return null;
	}

	private static String getSystem() {
		String osName = System.getProperty("os.name").toLowerCase();
		return osName;
	}

	private static String getUserHome() throws Exception {
		String osName = getSystem();
		String usrHome = System.getProperty("user.home");
		if (null != osName && osName.trim().toLowerCase().startsWith(WINDOWS)) {
			//if (usrHome.lastIndexOf("\\") < 0)
				usrHome += "\\";
		} else {
//			String fileDir = "/data/www/";
//			File file = new File(fileDir);
//			if (file.exists() && file.isDirectory()) { // 优先取/data/www/目录
//				return fileDir;
//			}
			//if (usrHome.trim().lastIndexOf("/") < 0) {
				usrHome += "/";
			//}
		}
		return usrHome;
	}

	private static Properties parseProperties(File file) throws Exception {
		Properties prop = new Properties();
		FileInputStream inputStream = new FileInputStream(file);
		prop.load(inputStream);
		return prop;
	}

	private void completePropertiesIntoFile() throws Exception {
		String clzzPath = ZLResourceLoadingListener.class.getResource("/")
				.getPath();
		File resourceFile = findFirstFile(new File(clzzPath),
				DefaultProjectProperties);
		if (null == resourceFile) {
			return;
		}
		Properties classpathProperties = parseProperties(resourceFile);
		String propertiesFilePath = getUserHome() + "project.properties";
		System.out.println(">>Read project.properties:"+propertiesFilePath);
		File propertiesFile = new File(propertiesFilePath);
		if (propertiesFile.exists() && !propertiesFile.isFile()) {
			// 用户目录下没有project.properties文件，
			// 则吧classpath中对应的project.properties文件拷贝到用户目录下
			throw new Exception(propertiesFilePath + " isn't an file..");
		}
		Properties newProperties = null;
		if (!propertiesFile.exists()) {
			propertiesFile.createNewFile();
			writeFile(propertiesFile, toPropertiesString(classpathProperties));
			newProperties = classpathProperties;
		} else {
			InputStream inStream = new FileInputStream(propertiesFile);
			Properties userpathProperties = new Properties();
			userpathProperties.load(inStream);
			classpathProperties.putAll(userpathProperties);
			newProperties = classpathProperties;
		}
		try {
			File workingPropertiesFile = new File(clzzPath + "/"
					+ DefaultWorkingProjectProperties);
			writeFile(workingPropertiesFile, toPropertiesString(newProperties));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private String toPropertiesString(Properties properties) {
		StringBuilder builder = new StringBuilder();
		for (Map.Entry<Object, Object> entry : properties.entrySet()) {
			builder.append(entry.getKey());
			builder.append("=");
			builder.append(entry.getValue());
			builder.append("\r\n");
		}
		return builder.toString();
	}

	private static void writeFile(File file, String content) throws Exception {
		FileWriter fileWriter = null;
		try {
			if (!file.exists())
				file.createNewFile();
			fileWriter = new FileWriter(file, false);
			fileWriter.write(content);
			fileWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != fileWriter)
				fileWriter.close();
		}
	}

	public void contextInitialized(ServletContextEvent scEvent) {
		try {
			completePropertiesIntoFile();
			System.out.println(">>create _project.properties finished.");
		} catch (Exception e) {
			System.out.println(">>create _project.properties failure, MSG:"+e.getMessage());
			e.printStackTrace();
		}
	}

	public void contextDestroyed(ServletContextEvent event) {
	}

}
