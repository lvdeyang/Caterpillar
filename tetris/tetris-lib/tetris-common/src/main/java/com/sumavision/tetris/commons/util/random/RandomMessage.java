package com.sumavision.tetris.commons.util.random;

import java.util.Date;
import java.util.Random;
import org.springframework.stereotype.Component;
import com.sumavision.tetris.commons.util.wrapper.StringBufferWrapper;

@Component
public class RandomMessage {
	
	//字体只显示大写，去掉了1,0,i,o几个容易混淆的字符
	private final String NUMBERANDLETTER = "23456789ABCDEFGHJKLMNPQRSTUVWXYZ";
	
	/**
	 * 根据数位生成随机数字串<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年3月12日 上午10:21:45
	 * @param int digitCount 数位
	 * @return String 随机的数字串
	 */
	public String onlyNumber(int digitCount) throws Exception{
		Random rand = new Random(System.currentTimeMillis());
		StringBufferWrapper number = new StringBufferWrapper();
		for(int i=0; i<digitCount; i++){
			number.append(rand.nextInt(10));
		}
		return number.toString();
	}
	
	/**
	 * 生成数字字母组合<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年3月12日 上午10:55:38
	 * @param int digitCount 数位
	 * @return String 随机的数字串
	 */
	public String numberAndLetter(int digitCount) throws Exception{
        int codesLen = NUMBERANDLETTER.length();
        Random rand = new Random(System.currentTimeMillis());
        StringBuilder verifyCode = new StringBuilder(digitCount);
        for(int i = 0; i <digitCount; i++){
            verifyCode.append(NUMBERANDLETTER.charAt(rand.nextInt(codesLen-1)));
        }
        return verifyCode.toString();
	}
	
}
