package com.sumavision.tetris.commons.util.audio;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

/**
 * TTS 文字转语音文件
 *
 * <p>
 * detailed comment
 * 
 * @author lxw 2015年1月29日
 * @see
 * @since 1.0
 * @author lzp 2019年7月31日 修改
 */
public class DoTTSUtil {

	public static final Logger LOGGER = Logger.getLogger(DoTTSUtil.class);

	// TODO 暂定目录
	// public static String webRootDir =
	// DoTTSUtil.class.getClassLoader().getResource("/").getPath();

	public static synchronized String doTTS(String fileUrl, String text) {
		if (null == text || text.trim().isEmpty()) {
			LOGGER.warn("DoTTS Fail: null text");
			return null;
		}
		if (null == fileUrl || fileUrl.trim().isEmpty()) {
			LOGGER.warn("DoTTS Fail: null fileName");
			return null;
		}

		BufferedReader bReader = null;
		try {
			// linux
			File ttsFile = new File(fileUrl);

			// TODO 将linux的tts默认放在/usr/sbin下
			String rootDir = "/usr/sbin/";
			String ttsCmd = new File(rootDir, "LTTS").getAbsolutePath();
			String[] cmdLinux = { "/bin/sh", "-c",
					"cd " + "\"" + ttsCmd + "\" ;" + // 进入tts文件目录
							"cp -i -n -r res / ;" + // 不覆盖迭代复制
							"chmod 777 ./TTS;" + // 设置执行权限
							"./TTS " + "\"" + text.replaceAll("[\n\r]+", ",").replaceAll("\"", "'") + "\"" + " \""
							+ ttsFile.getAbsolutePath() + "\"" };
			String[] cmd = cmdLinux;
			LOGGER.info(cmd[0]);

			LOGGER.info("starting text to Speech...");

			Process process = null;
			ProcessBuilder pb = new ProcessBuilder(cmd);
			pb.redirectErrorStream(true);

			process = pb.start();
			bReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line = null;
			while ((line = bReader.readLine()) != null) {
				LOGGER.info("TTS > " + line);
			}
			process.waitFor();
			return ttsFile.getAbsolutePath();
		} catch (Exception e) {
			LOGGER.error("DoTTS Fail,", e);
			return null;
		} finally {
			if (null != bReader) {
				try {
					bReader.close();
				} catch (IOException e) {
					LOGGER.error("", e);
				}
			}
		}
	}
}
