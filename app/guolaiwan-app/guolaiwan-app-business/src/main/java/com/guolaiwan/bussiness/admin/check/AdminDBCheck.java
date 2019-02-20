package com.guolaiwan.bussiness.admin.check;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guolaiwan.bussiness.admin.dao.MenuDAO;
import com.guolaiwan.bussiness.admin.dao.PageDAO;
import com.guolaiwan.bussiness.admin.dao.PageLayoutDAO;
import com.guolaiwan.bussiness.admin.dao.SectionDAO;
import com.guolaiwan.bussiness.admin.exception.LayoutNoPermissionForPageException;
import com.guolaiwan.bussiness.admin.exception.MenuNotFoundException;
import com.guolaiwan.bussiness.admin.exception.PageLayoutNotFoundException;
import com.guolaiwan.bussiness.admin.exception.PageNoPermissionForSectionException;
import com.guolaiwan.bussiness.admin.exception.PageNotFoundException;
import com.guolaiwan.bussiness.admin.exception.SectionNotFoundException;
import com.guolaiwan.bussiness.admin.po.MenuPO;
import com.guolaiwan.bussiness.admin.po.PageLayoutPO;
import com.guolaiwan.bussiness.admin.po.PagePO;
import com.guolaiwan.bussiness.admin.po.SectionPO;

import pub.caterpillar.orm.po.PageObject;

@Service("com.guolaiwan.bussiness.admin.check.AdminDBCheck")
public class AdminDBCheck {

	@Autowired
	private PageLayoutDAO conn_page_layout;
	
	@Autowired
	private PageDAO conn_page;
	
	@Autowired
	private SectionDAO conn_section;
	
	@Autowired
	private MenuDAO conn_menu;
	
	//布局是否存在
	public PageLayoutPO pageLayoutExist(Long layoutId) throws Exception{
		PageLayoutPO layout = conn_page_layout.get(layoutId);
		if(layout == null){
			throw new PageLayoutNotFoundException(layoutId);
		}
		return layout;
	}
	
	//菜单是否存在--重载
	public MenuPO menuExist(String menuId) throws Exception{
		return menuExist(Long.valueOf(menuId));
	}
	
	//菜单是否存在
	public MenuPO menuExist(Long menuId) throws Exception{
		MenuPO menu = conn_menu.get(menuId);
		if(menu == null){
			throw new MenuNotFoundException(menuId);
		}
		return menu;
	}
	
	//页面是否存在
	public PagePO pageExist(Long pageId) throws Exception{
		PagePO page = conn_page.get(pageId);
		if(page == null){
			throw new PageNotFoundException(pageId);
		}
		return page;
	}
	
	//页面布局是否匹配
	public void layoutPageMatching(PageLayoutPO layout, PagePO page) throws Exception{
		PageLayoutPO pLayout = page.getPageLayout();
		if(pLayout==null || !pLayout.getId().equals(layout.getId())){
			throw new LayoutNoPermissionForPageException(layout.getName(), page.getName());
		}
	}
	
	//版块是否存在
	public SectionPO sectionExist(Long sectionId) throws Exception{
		SectionPO section = conn_section.get(sectionId);
		if(section == null){
			throw new SectionNotFoundException(sectionId);
		}
		return section;
	}
	
	//版块页面是否匹配
	public void sectionPageMatching(PagePO page, SectionPO section) throws Exception{
		PagePO sPage = section.getPage();
		if(sPage==null || !sPage.getId().equals(page.getId())){
			throw new PageNoPermissionForSectionException(page.getName(), section.getTitle());
		}
	}
	
}
