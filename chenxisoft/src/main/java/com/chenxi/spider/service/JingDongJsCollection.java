package com.chenxi.spider.service;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


import cn.edu.hfut.dmic.webcollector.crawldb.DBManager;
import cn.edu.hfut.dmic.webcollector.crawler.Crawler;
import cn.edu.hfut.dmic.webcollector.fetcher.Executor;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatum;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BerkeleyDBManager;

public class JingDongJsCollection {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 Executor executor=new Executor() {
	            @Override
	            public void execute(CrawlDatum datum, CrawlDatums next) throws Exception {
	            	System.getProperties().setProperty("webdriver.chrome.driver","D:\\迅雷下载\\chromedriver.exe");
	                WebDriver driver=new ChromeDriver();
	               
	                driver.get(datum.url());
	                WebElement element=driver.findElement(By.className("price"));
	               
	                System.out.println(element.getText());
	                driver.close();
	            }

				
	        };

	        //创建一个基于伯克利DB的DBManager
	        DBManager manager=new BerkeleyDBManager("crawl");
	        
	        //创建一个Crawler需要有DBManager和Executor
	        Crawler crawler= new Crawler(manager,executor);
	        crawler.addSeed("https://item.jd.com/1221472.html");
	        try {
				crawler.start(1);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}

}
