package com.chenxi.spider;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chenxi.web.dao.ArticleContentDao;
import com.chenxi.web.dao.ArticleDao;

import cn.edu.hfut.dmic.webcollector.example.DemoAutoNewsCrawler;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.rocks.BreadthCrawler;
@Service
public class XinlangCepingCollection extends BreadthCrawler {

	@Autowired
	private ArticleDao conn_article;
	@Autowired
	private ArticleContentDao conn_articlecontent;
	
	public XinlangCepingCollection(){
		super("crawl", true);
		init();
	}
	
	
	public void init() {
		this.addSeed("http://auto.sina.com.cn/review/");
		this.addRegex("http://auto.sina.com.cn/review/shipai/[0-9]{4}-[0-9]{2}-[0-9]{2}/detail-[a-z]{8}[0-9]{7}.shtml");
		setThreads(1);
		getConf().setTopN(100);
	}

	@Override
	public void visit(Page page, CrawlDatums next) {
		String url = page.url();
		/**
		 * 如果此页面地址 确实是要求爬取网址，则进行取值
		 */
		if (page.matchUrl("http://auto.sina.com.cn/review/shipai/[0-9]{4}-[0-9]{2}-[0-9]{2}/detail-[a-z]{8}[0-9]{7}.shtml")) {	
			Elements contents = page.select(".article");
			for (Element element : contents) {
				System.out.println(element.html());
			}
		
		}
		
	}

	public static void main(String[] args) throws Exception {
		
		XinlangCepingCollection crawler = new XinlangCepingCollection();
		crawler.start(4);
	}

}
