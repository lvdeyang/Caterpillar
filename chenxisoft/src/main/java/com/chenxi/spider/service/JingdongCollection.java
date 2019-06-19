package com.chenxi.spider.service;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chenxi.web.classes.ContentSource;
import com.chenxi.web.classes.Moudular;
import com.chenxi.web.dao.ArticleContentDao;
import com.chenxi.web.dao.ArticleDao;
import com.chenxi.web.dao.ProductDao;
import com.chenxi.web.po.ArticleContentPo;
import com.chenxi.web.po.ArticlePo;
import com.chenxi.web.po.ProductPo;

import cn.edu.hfut.dmic.webcollector.example.DemoAutoNewsCrawler;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.rocks.BreadthCrawler;
@Service
public class JingdongCollection extends BreadthCrawler {

	@Autowired
	private ProductDao conn_product;
	
	
	public JingdongCollection(){
		super("crawl", true);
		init();
	}
	
	
	public void init() {
		this.addSeed("https://search.jd.com/Search?keyword=%E8%BD%AE%E8%83%8E&enc=utf-8&wq=&pvid=b2160a1bc78b4897827700e1dba8e242");
		this.addRegex("https://item.jd.com/[0-9]{7}.html");
		setThreads(1);
		getConf().setTopN(100);
	}

	@Override
	public void visit(Page page, CrawlDatums next) {
		String url = page.url();
		/**
		 * 如果此页面地址 确实是要求爬取网址，则进行取值
		 */
		if (page.matchUrl("https://item.jd.com/[0-9]{7}.html")) {	
			
			
	
			/*WebDriver driver =
			driver.get(url);
			String html = driver.getPageSource();
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);*/
			
			
			System.getProperties().setProperty("webdriver.chrome.driver","D:\\迅雷下载\\chromedriver.exe");
            WebDriver driver=new ChromeDriver();
           
            driver.get(url);
            WebElement welement=driver.findElement(By.className("price"));
            String price=welement.getText();
            System.out.println(welement.getText());
            driver.close();
			
			
			
		    String title=page.selectText(".sku-name");
		    
		   
		    Elements images=page.select("#spec-img");
		    String image="";
		    for (Element element : images) {
		    	
		    	image="https:"+element.attr("data-origin");
			}
		 
			if(conn_product.ifhasBysContent(title)){
				return;
			}
		    ProductPo productPo=new ProductPo();
		    productPo.setMoudular(Moudular.WEIXIU);
		    productPo.setSource(ContentSource.JINGDONG);
		    productPo.setShortContent(title);
		    productPo.setPic(image);
		    productPo.setUrl(url);
		    productPo.setPrice(Double.parseDouble(price));
		    conn_product.save(productPo);
		
		}
		
	}

	public static void main(String[] args) throws Exception {
		
		JingdongCollection crawler = new JingdongCollection();
		crawler.start(4);
	}

}
