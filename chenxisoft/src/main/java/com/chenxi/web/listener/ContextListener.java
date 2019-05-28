package com.chenxi.web.listener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.persistence.criteria.CriteriaBuilder.Case;
import pub.caterpillar.commons.context.SpringContext;
import pub.caterpillar.commons.util.binary.Sha1Util;
import pub.caterpillar.mvc.init.InitLoader;
import pub.caterpillar.weixin.constants.WXContants;

public class ContextListener extends InitLoader {


	@Override
	public void customInitialize() {
		System.out.println("context 初始化!");

	}

}
