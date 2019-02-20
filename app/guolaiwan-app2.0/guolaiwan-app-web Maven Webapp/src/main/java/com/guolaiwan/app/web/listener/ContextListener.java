package com.guolaiwan.app.web.listener;

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

import org.bytedeco.javacpp.RealSense.intrinsics;

import com.guolaiwan.app.web.admin.vo.CommercialSettlementVO;
import com.guolaiwan.bussiness.admin.dao.AdminDAO;
import com.guolaiwan.bussiness.admin.dao.BalanceDAO;
import com.guolaiwan.bussiness.admin.dao.CompanyDAO;
import com.guolaiwan.bussiness.admin.dao.MenuDAO;
import com.guolaiwan.bussiness.admin.dao.MerchantDAO;
import com.guolaiwan.bussiness.admin.dao.OrderInfoDAO;
import com.guolaiwan.bussiness.admin.dao.ProductDAO;
import com.guolaiwan.bussiness.admin.dao.RoleDAO;
import com.guolaiwan.bussiness.admin.dao.RoleMenuDAO;
import com.guolaiwan.bussiness.admin.dao.SysConfigDAO;
import com.guolaiwan.bussiness.admin.dao.UserInfoDAO;
import com.guolaiwan.bussiness.admin.enumeration.CompanyType;
import com.guolaiwan.bussiness.admin.enumeration.OrderStateType;
import com.guolaiwan.bussiness.admin.po.AdminPO;
import com.guolaiwan.bussiness.admin.po.BalancePO;
import com.guolaiwan.bussiness.admin.po.CompanyPO;
import com.guolaiwan.bussiness.admin.po.MenuPO;
import com.guolaiwan.bussiness.admin.po.MerchantPO;
import com.guolaiwan.bussiness.admin.po.OrderInfoPO;
import com.guolaiwan.bussiness.admin.po.ProductPO;
import com.guolaiwan.bussiness.admin.po.RoleMenuPO;
import com.guolaiwan.bussiness.admin.po.RolePO;
import com.guolaiwan.bussiness.admin.po.SysConfigPO;

import pub.caterpillar.commons.context.SpringContext;
import pub.caterpillar.commons.util.binary.Sha1Util;
import pub.caterpillar.mvc.init.InitLoader;
import pub.caterpillar.weixin.constants.WXContants;

public class ContextListener extends InitLoader {

	private AdminDAO conn_admin;
	private MenuDAO conn_menu;
	private RoleMenuDAO conn_roleMenu;
	private RoleDAO conn_role;
	private MerchantDAO conn_Merchant;
	private ProductDAO conn_Product;
	private OrderInfoDAO conn_OrderInfo;
	private BalanceDAO conn_Balance;
	private UserInfoDAO conn_userInfo;

	private CompanyDAO conn_company;

	private SysConfigDAO conn_sysConfig;

	@Override
	public void customInitialize() {

		conn_admin = SpringContext.getBean("com.guolaiwan.bussiness.admin.dao.AdminDAO");
		AdminPO admin = conn_admin.getByField("adminName", "admin");
		if (admin == null) {
			AdminPO adminInit = new AdminPO();
			adminInit.setAdminName("admin");
			adminInit.setPassword(Sha1Util.getSha1("guolaiwan2018"));
			adminInit.setCreatedDate(new Date());
			adminInit.setRoleId(1);
			adminInit.setCityCode("0001");
			adminInit.setCityName("遵化");
			adminInit.setComId(1l);
			adminInit.setComName("过来玩总公司");
			conn_admin.saveOrUpdate(adminInit);
		}

		conn_menu = SpringContext.getBean("com.guolaiwan.bussiness.admin.dao.MenuDAO");
		MenuPO menuPO = conn_menu.getByField("name", "管理员管理");

		if (menuPO == null) {

			MenuPO menu = new MenuPO();
			// menu.setId(1L);
			menu.setName("管理员管理");
			menu.setParentId(0);
			menu.setShowis(1);
			menu.setSort(1);
			menu.setUrl("");
			conn_menu.saveOrUpdate(menu);

			MenuPO adminList = new MenuPO();
			// menu.setId(2L);
			adminList.setName("管理员列表");
			adminList.setParentId(1);
			adminList.setShowis(1);
			adminList.setSort(1);
			adminList.setUrl("/admin/adminInfo");
			conn_menu.saveOrUpdate(adminList);

			MenuPO roleMgr = new MenuPO();
			// menu.setId(3L);
			roleMgr.setName("角色管理");
			roleMgr.setParentId(1);
			roleMgr.setShowis(1);
			roleMgr.setSort(1);
			roleMgr.setUrl("/admin/role/role");
			conn_menu.saveOrUpdate(roleMgr);

			MenuPO menuList = new MenuPO();
			// menu.setId(4L);
			menuList.setName("权限分类");
			menuList.setParentId(1);
			menuList.setShowis(1);
			menuList.setSort(1);
			menuList.setUrl("/admin/menu/list");
			conn_menu.saveOrUpdate(menuList);
			// 日志管理
			MenuPO menuLog = new MenuPO();
			// menu.setId(4L);
			menuLog.setName("日志管理");
			menuLog.setParentId(0);
			menuLog.setShowis(1);
			menuLog.setSort(1);
			menuLog.setUrl("");
			conn_menu.saveOrUpdate(menuLog);

			MenuPO logList = new MenuPO();
			// menu.setId(4L);
			logList.setName("日志列表");
			logList.setParentId(5);
			logList.setShowis(1);
			logList.setSort(1);
			logList.setUrl("/admin/log/logInfo");
			conn_menu.saveOrUpdate(logList);

			// 文件管理
			MenuPO wenjian = new MenuPO();
			wenjian.setName("文件管理");
			wenjian.setParentId(0);
			wenjian.setShowis(1);
			wenjian.setSort(1);
			wenjian.setUrl("");
			conn_menu.saveOrUpdate(wenjian);
			// 文件列表
			MenuPO wenjianlist = new MenuPO();
			wenjianlist.setName("图片列表");
			wenjianlist.setParentId(7);
			wenjianlist.setShowis(1);
			wenjianlist.setSort(1);
			wenjianlist.setUrl("/admin/picture/upload");
			conn_menu.saveOrUpdate(wenjianlist);

			// 商家管理
			MenuPO merchant = new MenuPO();
			merchant.setName("商户管理");
			merchant.setParentId(0);
			merchant.setShowis(1);
			merchant.setSort(1);
			merchant.setUrl("");
			conn_menu.saveOrUpdate(merchant);
			// 商家列表
			MenuPO merchantList = new MenuPO();
			merchantList.setName("商户列表");
			merchantList.setParentId(9);
			merchantList.setShowis(1);
			merchantList.setSort(1);
			merchantList.setUrl("/admin/merchant/list");
			conn_menu.saveOrUpdate(merchantList);
			// /admin/merchant/addv
			MenuPO addmerchant = new MenuPO();
			addmerchant.setName("添加商户");
			addmerchant.setParentId(9);
			addmerchant.setShowis(1);
			addmerchant.setSort(1);
			addmerchant.setUrl("/admin/merchant/addv");
			conn_menu.saveOrUpdate(addmerchant);
			// /admin/merchant/checklist
			MenuPO checkmerchant = new MenuPO();
			checkmerchant.setName("审核商户");
			checkmerchant.setParentId(9);
			checkmerchant.setShowis(1);
			checkmerchant.setSort(1);
			checkmerchant.setUrl("/admin/merchant/checklist");
			conn_menu.saveOrUpdate(checkmerchant);
			// /admin/merchant/passlist
			MenuPO passmerchant = new MenuPO();
			passmerchant.setName("通过商户");
			passmerchant.setParentId(9);
			passmerchant.setShowis(1);
			passmerchant.setSort(1);
			passmerchant.setUrl("/admin/merchant/passlist");
			conn_menu.saveOrUpdate(passmerchant);

			// 模块管理
			MenuPO modu = new MenuPO();
			modu.setName("模块管理");
			modu.setParentId(0);
			modu.setShowis(1);
			modu.setSort(1);
			modu.setUrl("");
			conn_menu.saveOrUpdate(modu);

			MenuPO modulist = new MenuPO();
			modulist.setName("模块列表");
			modulist.setParentId(14);
			modulist.setShowis(1);
			modulist.setSort(1);
			modulist.setUrl("/admin/modular/list");
			conn_menu.saveOrUpdate(modulist);

			MenuPO submodu = new MenuPO();
			submodu.setName("子模块管理");
			submodu.setParentId(14);
			submodu.setShowis(1);
			submodu.setSort(1);
			submodu.setUrl("/admin/modularclass/list");
			conn_menu.saveOrUpdate(submodu);

			// 产品管理17
			MenuPO product = new MenuPO();
			product.setName("产品管理");
			product.setParentId(0);
			product.setShowis(1);
			product.setSort(1);
			product.setUrl("");
			conn_menu.saveOrUpdate(product);
			// 18 /admin/product/list
			MenuPO productlist = new MenuPO();
			productlist.setName("产品列表");
			productlist.setParentId(17);
			productlist.setShowis(1);
			productlist.setSort(1);
			productlist.setUrl("/admin/product/list");
			conn_menu.saveOrUpdate(productlist);
			// 19 /admin/product/addv
			MenuPO addproduct = new MenuPO();
			addproduct.setName("添加产品");
			addproduct.setParentId(17);
			addproduct.setShowis(1);
			addproduct.setSort(1);
			addproduct.setUrl("/admin/product/addv");
			conn_menu.saveOrUpdate(addproduct);
			// 20
			MenuPO checkproduct = new MenuPO();
			checkproduct.setName("审核产品");
			checkproduct.setParentId(17);
			checkproduct.setShowis(1);
			checkproduct.setSort(1);
			checkproduct.setUrl("/admin/product/checklist");
			conn_menu.saveOrUpdate(checkproduct);
			// 21
			MenuPO passproduct = new MenuPO();
			passproduct.setName("通过产品");
			passproduct.setParentId(17);
			passproduct.setShowis(1);
			passproduct.setSort(1);
			passproduct.setUrl("/admin/product/passlist");
			conn_menu.saveOrUpdate(passproduct);

			// 用户管理 22
			MenuPO user = new MenuPO();
			user.setName("用户管理");
			user.setParentId(0);
			user.setShowis(1);
			user.setSort(1);
			user.setUrl("");
			conn_menu.saveOrUpdate(user);
			// 23
			MenuPO userlist = new MenuPO();
			userlist.setName("用户列表");
			userlist.setParentId(22);
			userlist.setShowis(1);
			userlist.setSort(1);
			userlist.setUrl("/admin/userinfo/list");
			conn_menu.saveOrUpdate(userlist);

			// 订单中心 24
			MenuPO order = new MenuPO();
			order.setName("订单中心");
			order.setParentId(0);
			order.setShowis(1);
			order.setSort(1);
			order.setUrl("");
			conn_menu.saveOrUpdate(order);
			// 25
			MenuPO orderInfo = new MenuPO();
			orderInfo.setName("订单信息");
			orderInfo.setParentId(24);
			orderInfo.setShowis(1);
			orderInfo.setSort(1);
			orderInfo.setUrl("/admin/orderinfo/list");
			conn_menu.saveOrUpdate(orderInfo);

			// 城市管理 26
			MenuPO city = new MenuPO();
			city.setName("城市管理");
			city.setParentId(0);
			city.setShowis(1);
			city.setSort(1);
			city.setUrl("");
			conn_menu.saveOrUpdate(city);
			// 27
			MenuPO cityInfo = new MenuPO();
			cityInfo.setName("城市信息");
			cityInfo.setParentId(26);
			cityInfo.setShowis(1);
			cityInfo.setSort(1);
			cityInfo.setUrl("/admin/cityinfo/list");
			conn_menu.saveOrUpdate(cityInfo);

			// 底部向导 28
			MenuPO foot = new MenuPO();
			foot.setName("底部向导");
			foot.setParentId(0);
			foot.setShowis(1);
			foot.setSort(1);
			foot.setUrl("");
			conn_menu.saveOrUpdate(foot);
			// 29
			MenuPO footm = new MenuPO();
			footm.setName("向导管理");
			footm.setParentId(28);
			footm.setShowis(1);
			footm.setSort(1);
			footm.setUrl("/admin/class/list");
			conn_menu.saveOrUpdate(footm);
			// 30
			MenuPO foota = new MenuPO();
			foota.setName("文章管理");
			foota.setParentId(28);
			foota.setShowis(1);
			foota.setSort(1);
			foota.setUrl("/admin/article/list");
			conn_menu.saveOrUpdate(foota);

			// 轮播图 31
			MenuPO pics = new MenuPO();
			pics.setName("轮播图片");
			pics.setParentId(0);
			pics.setShowis(1);
			pics.setSort(1);
			pics.setUrl("");
			conn_menu.saveOrUpdate(pics);
			// 32
			MenuPO picsm = new MenuPO();
			picsm.setName("轮播图管理 ");
			picsm.setParentId(31);
			picsm.setShowis(1);
			picsm.setSort(1);
			picsm.setUrl("/admin/carousel/list");
			conn_menu.saveOrUpdate(picsm);

			//////////// 33
			MenuPO adminListdo = new MenuPO();
			adminListdo.setName("读取管理员列表");
			adminListdo.setParentId(1);
			adminListdo.setShowis(0);
			adminListdo.setSort(33);
			adminListdo.setUrl("/admin/list.do");
			conn_menu.saveOrUpdate(adminListdo);

			MenuPO adminaddv = new MenuPO();
			adminaddv.setName("添加管理员页面");
			adminaddv.setParentId(1);
			adminaddv.setShowis(0);
			adminaddv.setSort(34);
			adminaddv.setUrl("/admin/addv");
			conn_menu.saveOrUpdate(adminaddv);

			MenuPO adminadddo = new MenuPO();
			adminadddo.setName("添加管理员按钮");
			adminadddo.setParentId(1);
			adminadddo.setShowis(0);
			adminadddo.setSort(35);
			adminadddo.setUrl("/admin/add.do");
			conn_menu.saveOrUpdate(adminadddo);

			MenuPO adminmodi = new MenuPO();
			adminmodi.setName("编辑管理员页面");
			adminmodi.setParentId(1);
			adminmodi.setShowis(0);
			adminmodi.setSort(36);
			adminmodi.setUrl("/admin/updatev");
			conn_menu.saveOrUpdate(adminmodi);

			MenuPO adminupdate = new MenuPO();
			adminupdate.setName("编辑管理员按钮");
			adminupdate.setParentId(1);
			adminupdate.setShowis(0);
			adminupdate.setSort(37);
			adminupdate.setUrl("/admin/update.do");
			conn_menu.saveOrUpdate(adminupdate);

			// 38
			MenuPO admindel = new MenuPO();
			admindel.setName("删除管理员按钮");
			admindel.setParentId(1);
			admindel.setShowis(0);
			admindel.setSort(38);
			admindel.setUrl("/admin/del.do");
			conn_menu.saveOrUpdate(admindel);

			///////////// 39
			MenuPO roleMgrlist = new MenuPO();
			roleMgrlist.setName("读取角色管理列表");
			roleMgrlist.setParentId(1);
			roleMgrlist.setShowis(0);
			roleMgrlist.setSort(1);
			roleMgrlist.setUrl("/admin/role/roleList.do");
			conn_menu.saveOrUpdate(roleMgrlist);
			// 40
			MenuPO roleMgradd = new MenuPO();
			roleMgradd.setName("添加角色页面");
			roleMgradd.setParentId(1);
			roleMgradd.setShowis(0);
			roleMgradd.setSort(1);
			roleMgradd.setUrl("/admin/role/addv");
			conn_menu.saveOrUpdate(roleMgradd);
			// 41
			MenuPO roleMgradddo = new MenuPO();
			roleMgradddo.setName("添加角色按钮");
			roleMgradddo.setParentId(1);
			roleMgradddo.setShowis(0);
			roleMgradddo.setSort(1);
			roleMgradddo.setUrl("/admin/role/add.do");
			conn_menu.saveOrUpdate(roleMgradddo);

			// 42
			MenuPO roleMgruv = new MenuPO();
			roleMgruv.setName("编辑角色页面");
			roleMgruv.setParentId(1);
			roleMgruv.setShowis(0);
			roleMgruv.setSort(1);
			roleMgruv.setUrl("/admin/role/updatev");
			conn_menu.saveOrUpdate(roleMgruv);
			// 43
			MenuPO roleMgrupdate = new MenuPO();
			roleMgrupdate.setName("编辑角色按钮");
			roleMgrupdate.setParentId(1);
			roleMgrupdate.setShowis(0);
			roleMgrupdate.setSort(1);
			roleMgrupdate.setUrl("/admin/role/update.do");
			conn_menu.saveOrUpdate(roleMgrupdate);
			// 44
			MenuPO roleMgrdel = new MenuPO();
			roleMgrdel.setName("删除角色按钮");
			roleMgrdel.setParentId(1);
			roleMgrdel.setShowis(0);
			roleMgrdel.setSort(1);
			roleMgrdel.setUrl("/admin/role/del.do");
			conn_menu.saveOrUpdate(roleMgrdel);

			// 45
			MenuPO roleMenuli = new MenuPO();
			roleMenuli.setName("读取权限分类列表");
			roleMenuli.setParentId(1);
			roleMenuli.setShowis(0);
			roleMenuli.setSort(1);
			roleMenuli.setUrl("/admin/menu/pageList.do");
			conn_menu.saveOrUpdate(roleMenuli);
			// 46
			MenuPO roleMenuadd = new MenuPO();
			roleMenuadd.setName("添加权限分类页面");
			roleMenuadd.setParentId(1);
			roleMenuadd.setShowis(0);
			roleMenuadd.setSort(1);
			roleMenuadd.setUrl("/admin/menu/addv");
			conn_menu.saveOrUpdate(roleMenuadd);
			// 47
			MenuPO roleMenuaddo = new MenuPO();
			roleMenuaddo.setName("添加权限分类按钮");
			roleMenuaddo.setParentId(1);
			roleMenuaddo.setShowis(0);
			roleMenuaddo.setSort(1);
			roleMenuaddo.setUrl("/admin/menu/add.do");
			conn_menu.saveOrUpdate(roleMenuaddo);
			// 48
			MenuPO roleMenumodi = new MenuPO();
			roleMenumodi.setName("编辑权限分类页面");
			roleMenumodi.setParentId(1);
			roleMenumodi.setShowis(0);
			roleMenumodi.setSort(1);
			roleMenumodi.setUrl("/admin/menu/updatev");
			conn_menu.saveOrUpdate(roleMenumodi);
			// 49
			MenuPO roleMenumodify = new MenuPO();
			roleMenumodify.setName("编辑权限分类按钮");
			roleMenumodify.setParentId(1);
			roleMenumodify.setShowis(0);
			roleMenumodify.setSort(1);
			roleMenumodify.setUrl("/admin/menu/update.do");
			conn_menu.saveOrUpdate(roleMenumodify);
			// 50
			MenuPO roleMenudel = new MenuPO();
			roleMenudel.setName("删除权限分类按钮");
			roleMenudel.setParentId(1);
			roleMenudel.setShowis(0);
			roleMenudel.setSort(1);
			roleMenudel.setUrl("/admin/menu/del.do");
			conn_menu.saveOrUpdate(roleMenudel);
			// 51
			MenuPO pquery = new MenuPO();
			pquery.setName("查询权限分类父类计数");
			pquery.setParentId(1);
			pquery.setShowis(0);
			pquery.setSort(1);
			pquery.setUrl("/admin/menu/pCount.do");
			conn_menu.saveOrUpdate(pquery);

			// 52
			MenuPO paquery = new MenuPO();
			paquery.setName("通过父类查询权限分类");
			paquery.setParentId(1);
			paquery.setShowis(0);
			paquery.setSort(1);
			paquery.setUrl("/admin/menu/getMenuByP.do");
			conn_menu.saveOrUpdate(paquery);

			// 53 城市
			MenuPO cityInfold = new MenuPO();
			cityInfold.setName("读取城市信息列表");
			cityInfold.setParentId(26);
			cityInfold.setShowis(0);
			cityInfold.setSort(1);
			cityInfold.setUrl("/admin/cityinfo/list.do");
			conn_menu.saveOrUpdate(cityInfold);
			// 54
			MenuPO cityInfoadv = new MenuPO();
			cityInfoadv.setName("添加城市页面");
			cityInfoadv.setParentId(26);
			cityInfoadv.setShowis(0);
			cityInfoadv.setSort(1);
			cityInfoadv.setUrl("/admin/cityinfo/addv");
			conn_menu.saveOrUpdate(cityInfoadv);
			// 55
			MenuPO cityInfoado = new MenuPO();
			cityInfoado.setName("添加城市按钮");
			cityInfoado.setParentId(26);
			cityInfoado.setShowis(0);
			cityInfoado.setSort(1);
			cityInfoado.setUrl("/admin/cityinfo/add.do");
			conn_menu.saveOrUpdate(cityInfoado);
			// 56
			MenuPO cityInfouv = new MenuPO();
			cityInfouv.setName("编辑城市信息页面");
			cityInfouv.setParentId(26);
			cityInfouv.setShowis(0);
			cityInfouv.setSort(1);
			cityInfouv.setUrl("/admin/cityinfo/updatev");
			conn_menu.saveOrUpdate(cityInfouv);
			// 57
			MenuPO cityInfoud = new MenuPO();
			cityInfoud.setName("编辑城市信息按钮");
			cityInfoud.setParentId(26);
			cityInfoud.setShowis(0);
			cityInfoud.setSort(1);
			cityInfoud.setUrl("/admin/cityinfo/update.do");
			conn_menu.saveOrUpdate(cityInfoud);
			// 58
			MenuPO cityInfode = new MenuPO();
			cityInfode.setName("删除城市信息按钮");
			cityInfode.setParentId(26);
			cityInfode.setShowis(0);
			cityInfode.setSort(1);
			cityInfode.setUrl("/admin/cityinfo/del.do");
			conn_menu.saveOrUpdate(cityInfode);

			// 权限管理 59
			MenuPO rolemenuv = new MenuPO();
			rolemenuv.setName("权限管理页面");
			rolemenuv.setParentId(1);
			rolemenuv.setShowis(0);
			rolemenuv.setSort(1);
			rolemenuv.setUrl("/admin/rolemenu/list");
			conn_menu.saveOrUpdate(rolemenuv);
			// 60
			MenuPO rolemenud = new MenuPO();
			rolemenud.setName("权限管理操作");
			rolemenud.setParentId(1);
			rolemenud.setShowis(0);
			rolemenud.setSort(1);
			rolemenud.setUrl("/admin/rolemenu/add.do");
			conn_menu.saveOrUpdate(rolemenud);
		}

		conn_roleMenu = SpringContext.getBean("com.guolaiwan.bussiness.admin.dao.RoleMenuDAO");
		List<RoleMenuPO> roleMenuList = conn_roleMenu.findByField("roleId", 1);// getByField("roleId",
																				// 1);
		if (roleMenuList == null || roleMenuList.size() <= 0) {
			RoleMenuPO roleMenu = new RoleMenuPO();
			roleMenu.setRoleId(1);
			roleMenu.setMenuId(1);
			roleMenu.setMenuFid(0);
			conn_roleMenu.saveOrUpdate(roleMenu);

			RoleMenuPO roleMenu2 = new RoleMenuPO();
			roleMenu2.setRoleId(1);
			roleMenu2.setMenuId(2);
			roleMenu2.setMenuFid(1);
			conn_roleMenu.saveOrUpdate(roleMenu2);

			RoleMenuPO roleMenu3 = new RoleMenuPO();
			roleMenu3.setRoleId(1);
			roleMenu3.setMenuId(3);
			roleMenu3.setMenuFid(1);
			conn_roleMenu.saveOrUpdate(roleMenu3);

			RoleMenuPO roleMenu4 = new RoleMenuPO();
			roleMenu4.setRoleId(1);
			roleMenu4.setMenuId(4);
			roleMenu4.setMenuFid(1);
			conn_roleMenu.saveOrUpdate(roleMenu4);

			RoleMenuPO menuLog = new RoleMenuPO();
			menuLog.setRoleId(1);
			menuLog.setMenuId(5);
			menuLog.setMenuFid(0);
			conn_roleMenu.saveOrUpdate(menuLog);

			RoleMenuPO logList = new RoleMenuPO();
			logList.setRoleId(1);
			logList.setMenuId(6);
			logList.setMenuFid(5);
			conn_roleMenu.saveOrUpdate(logList);

			RoleMenuPO wenjian = new RoleMenuPO();
			wenjian.setRoleId(1);
			wenjian.setMenuId(7);
			wenjian.setMenuFid(0);
			conn_roleMenu.saveOrUpdate(wenjian);

			RoleMenuPO wenjianlist = new RoleMenuPO();
			wenjianlist.setRoleId(1);
			wenjianlist.setMenuId(8);
			wenjianlist.setMenuFid(7);
			conn_roleMenu.saveOrUpdate(wenjianlist);

			// 商家管理
			RoleMenuPO merchant = new RoleMenuPO();
			merchant.setRoleId(1);
			merchant.setMenuId(9);
			merchant.setMenuFid(0);
			conn_roleMenu.saveOrUpdate(merchant);

			RoleMenuPO merchantlist = new RoleMenuPO();
			merchantlist.setRoleId(1);
			merchantlist.setMenuId(10);
			merchantlist.setMenuFid(9);
			conn_roleMenu.saveOrUpdate(merchantlist);

			RoleMenuPO addmerchant = new RoleMenuPO();
			addmerchant.setRoleId(1);
			addmerchant.setMenuId(11);
			addmerchant.setMenuFid(9);
			conn_roleMenu.saveOrUpdate(addmerchant);

			RoleMenuPO checkmerchant = new RoleMenuPO();
			checkmerchant.setRoleId(1);
			checkmerchant.setMenuId(12);
			checkmerchant.setMenuFid(9);
			conn_roleMenu.saveOrUpdate(checkmerchant);

			RoleMenuPO passmerchant = new RoleMenuPO();
			passmerchant.setRoleId(1);
			passmerchant.setMenuId(13);
			passmerchant.setMenuFid(9);
			conn_roleMenu.saveOrUpdate(passmerchant);

			// 模块管理
			RoleMenuPO modu = new RoleMenuPO();
			modu.setRoleId(1);
			modu.setMenuId(14);
			modu.setMenuFid(0);
			conn_roleMenu.saveOrUpdate(modu);

			RoleMenuPO modulist = new RoleMenuPO();
			modulist.setRoleId(1);
			modulist.setMenuId(15);
			modulist.setMenuFid(14);
			conn_roleMenu.saveOrUpdate(modulist);

			RoleMenuPO submodu = new RoleMenuPO();
			submodu.setRoleId(1);
			submodu.setMenuId(16);
			submodu.setMenuFid(14);
			conn_roleMenu.saveOrUpdate(submodu);

			// 产品管理
			RoleMenuPO product = new RoleMenuPO();
			product.setRoleId(1);
			product.setMenuId(17);
			product.setMenuFid(0);
			conn_roleMenu.saveOrUpdate(product);

			RoleMenuPO productlist = new RoleMenuPO();
			productlist.setRoleId(1);
			productlist.setMenuId(18);
			productlist.setMenuFid(17);
			conn_roleMenu.saveOrUpdate(productlist);

			RoleMenuPO addproduct = new RoleMenuPO();
			addproduct.setRoleId(1);
			addproduct.setMenuId(19);
			addproduct.setMenuFid(17);
			conn_roleMenu.saveOrUpdate(addproduct);

			RoleMenuPO chickproduct = new RoleMenuPO();
			chickproduct.setRoleId(1);
			chickproduct.setMenuId(20);
			chickproduct.setMenuFid(17);
			conn_roleMenu.saveOrUpdate(chickproduct);

			RoleMenuPO passproduct = new RoleMenuPO();
			passproduct.setRoleId(1);
			passproduct.setMenuId(21);
			passproduct.setMenuFid(17);
			conn_roleMenu.saveOrUpdate(passproduct);

			// 用户管理
			RoleMenuPO user = new RoleMenuPO();
			user.setRoleId(1);
			user.setMenuId(22);
			user.setMenuFid(0);
			conn_roleMenu.saveOrUpdate(user);

			RoleMenuPO userlist = new RoleMenuPO();
			userlist.setRoleId(1);
			userlist.setMenuId(23);
			userlist.setMenuFid(22);
			conn_roleMenu.saveOrUpdate(userlist);

			// 订单中心
			RoleMenuPO order = new RoleMenuPO();
			order.setRoleId(1);
			order.setMenuId(24);
			order.setMenuFid(0);
			conn_roleMenu.saveOrUpdate(order);

			RoleMenuPO orderInfo = new RoleMenuPO();
			orderInfo.setRoleId(1);
			orderInfo.setMenuId(25);
			orderInfo.setMenuFid(24);
			conn_roleMenu.saveOrUpdate(orderInfo);

			// 城市管理
			RoleMenuPO city = new RoleMenuPO();
			city.setRoleId(1);
			city.setMenuId(26);
			city.setMenuFid(0);
			conn_roleMenu.saveOrUpdate(city);

			RoleMenuPO cityInfo = new RoleMenuPO();
			cityInfo.setRoleId(1);
			cityInfo.setMenuId(27);
			cityInfo.setMenuFid(26);
			conn_roleMenu.saveOrUpdate(cityInfo);

			// 底部向导
			RoleMenuPO foot = new RoleMenuPO();
			foot.setRoleId(1);
			foot.setMenuId(28);
			foot.setMenuFid(0);
			conn_roleMenu.saveOrUpdate(foot);

			RoleMenuPO footm = new RoleMenuPO();
			footm.setRoleId(1);
			footm.setMenuId(29);
			footm.setMenuFid(28);
			conn_roleMenu.saveOrUpdate(footm);

			RoleMenuPO foota = new RoleMenuPO();
			foota.setRoleId(1);
			foota.setMenuId(30);
			foota.setMenuFid(28);
			conn_roleMenu.saveOrUpdate(foota);

			// 轮播图
			RoleMenuPO pics = new RoleMenuPO();
			pics.setRoleId(1);
			pics.setMenuId(31);
			pics.setMenuFid(0);
			conn_roleMenu.saveOrUpdate(pics);

			RoleMenuPO picsm = new RoleMenuPO();
			picsm.setRoleId(1);
			picsm.setMenuId(32);
			picsm.setMenuFid(31);
			conn_roleMenu.saveOrUpdate(picsm);

			///////// 33
			RoleMenuPO adminlistdo = new RoleMenuPO();
			adminlistdo.setRoleId(1);
			adminlistdo.setMenuId(33);
			adminlistdo.setMenuFid(1);
			conn_roleMenu.saveOrUpdate(adminlistdo);
			// 34
			RoleMenuPO adminaddv = new RoleMenuPO();
			adminaddv.setRoleId(1);
			adminaddv.setMenuId(34);
			adminaddv.setMenuFid(1);
			conn_roleMenu.saveOrUpdate(adminaddv);
			// 35
			RoleMenuPO adminadddo = new RoleMenuPO();
			adminadddo.setRoleId(1);
			adminadddo.setMenuId(35);
			adminadddo.setMenuFid(1);
			conn_roleMenu.saveOrUpdate(adminadddo);
			// 36
			RoleMenuPO adminmodi = new RoleMenuPO();
			adminmodi.setRoleId(1);
			adminmodi.setMenuId(36);
			adminmodi.setMenuFid(1);
			conn_roleMenu.saveOrUpdate(adminmodi);
			// 37
			RoleMenuPO adminupdate = new RoleMenuPO();
			adminupdate.setRoleId(1);
			adminupdate.setMenuId(37);
			adminupdate.setMenuFid(1);
			conn_roleMenu.saveOrUpdate(adminupdate);
			// 38
			RoleMenuPO admindel = new RoleMenuPO();
			admindel.setRoleId(1);
			admindel.setMenuId(38);
			admindel.setMenuFid(1);
			conn_roleMenu.saveOrUpdate(admindel);

			///////////// 39
			RoleMenuPO rolelist = new RoleMenuPO();
			rolelist.setRoleId(1);
			rolelist.setMenuId(39);
			rolelist.setMenuFid(1);
			conn_roleMenu.saveOrUpdate(rolelist);

			RoleMenuPO rolelist40 = new RoleMenuPO();
			rolelist40.setRoleId(1);
			rolelist40.setMenuId(40);
			rolelist40.setMenuFid(1);
			conn_roleMenu.saveOrUpdate(rolelist40);

			RoleMenuPO rolelist41 = new RoleMenuPO();
			rolelist41.setRoleId(1);
			rolelist41.setMenuId(41);
			rolelist41.setMenuFid(1);
			conn_roleMenu.saveOrUpdate(rolelist41);

			RoleMenuPO rolelist42 = new RoleMenuPO();
			rolelist42.setRoleId(1);
			rolelist42.setMenuId(42);
			rolelist42.setMenuFid(1);
			conn_roleMenu.saveOrUpdate(rolelist42);

			RoleMenuPO rolelist43 = new RoleMenuPO();
			rolelist43.setRoleId(1);
			rolelist43.setMenuId(43);
			rolelist43.setMenuFid(1);
			conn_roleMenu.saveOrUpdate(rolelist43);

			RoleMenuPO rolelist44 = new RoleMenuPO();
			rolelist44.setRoleId(1);
			rolelist44.setMenuId(44);
			rolelist44.setMenuFid(1);
			conn_roleMenu.saveOrUpdate(rolelist44);

			RoleMenuPO rolelist45 = new RoleMenuPO();
			rolelist45.setRoleId(1);
			rolelist45.setMenuId(45);
			rolelist45.setMenuFid(1);
			conn_roleMenu.saveOrUpdate(rolelist45);

			RoleMenuPO rolelist46 = new RoleMenuPO();
			rolelist46.setRoleId(1);
			rolelist46.setMenuId(46);
			rolelist46.setMenuFid(1);
			conn_roleMenu.saveOrUpdate(rolelist46);

			RoleMenuPO rolelist47 = new RoleMenuPO();
			rolelist47.setRoleId(1);
			rolelist47.setMenuId(47);
			rolelist47.setMenuFid(1);
			conn_roleMenu.saveOrUpdate(rolelist47);

			RoleMenuPO rolelist48 = new RoleMenuPO();
			rolelist48.setRoleId(1);
			rolelist48.setMenuId(48);
			rolelist48.setMenuFid(1);
			conn_roleMenu.saveOrUpdate(rolelist48);

			RoleMenuPO rolelist49 = new RoleMenuPO();
			rolelist49.setRoleId(1);
			rolelist49.setMenuId(49);
			rolelist49.setMenuFid(1);
			conn_roleMenu.saveOrUpdate(rolelist49);

			RoleMenuPO rolelist50 = new RoleMenuPO();
			rolelist50.setRoleId(1);
			rolelist50.setMenuId(50);
			rolelist50.setMenuFid(1);
			conn_roleMenu.saveOrUpdate(rolelist50);

			RoleMenuPO pquery = new RoleMenuPO();
			pquery.setRoleId(1);
			pquery.setMenuId(51);
			pquery.setMenuFid(1);
			conn_roleMenu.saveOrUpdate(pquery);

			RoleMenuPO paquery = new RoleMenuPO();
			paquery.setRoleId(1);
			paquery.setMenuId(52);
			paquery.setMenuFid(1);
			conn_roleMenu.saveOrUpdate(paquery);

			// 53~58
			RoleMenuPO rolemenu53 = new RoleMenuPO();
			rolemenu53.setRoleId(1);
			rolemenu53.setMenuId(53);
			rolemenu53.setMenuFid(26);
			conn_roleMenu.saveOrUpdate(rolemenu53);

			RoleMenuPO rolemenu54 = new RoleMenuPO();
			rolemenu54.setRoleId(1);
			rolemenu54.setMenuId(54);
			rolemenu54.setMenuFid(26);
			conn_roleMenu.saveOrUpdate(rolemenu54);

			RoleMenuPO rolemenu55 = new RoleMenuPO();
			rolemenu55.setRoleId(1);
			rolemenu55.setMenuId(55);
			rolemenu55.setMenuFid(26);
			conn_roleMenu.saveOrUpdate(rolemenu55);

			RoleMenuPO rolemenu56 = new RoleMenuPO();
			rolemenu56.setRoleId(1);
			rolemenu56.setMenuId(56);
			rolemenu56.setMenuFid(26);
			conn_roleMenu.saveOrUpdate(rolemenu56);

			RoleMenuPO rolemenu57 = new RoleMenuPO();
			rolemenu57.setRoleId(1);
			rolemenu57.setMenuId(57);
			rolemenu57.setMenuFid(26);
			conn_roleMenu.saveOrUpdate(rolemenu57);

			RoleMenuPO rolemenu58 = new RoleMenuPO();
			rolemenu58.setRoleId(1);
			rolemenu58.setMenuId(58);
			rolemenu58.setMenuFid(26);
			conn_roleMenu.saveOrUpdate(rolemenu58);

			// 权限管理
			RoleMenuPO rolemenuv = new RoleMenuPO();
			rolemenuv.setRoleId(1);
			rolemenuv.setMenuId(59);
			rolemenuv.setMenuFid(1);
			conn_roleMenu.saveOrUpdate(rolemenuv);

			RoleMenuPO rolemenud = new RoleMenuPO();
			rolemenud.setRoleId(1);
			rolemenud.setMenuId(60);
			rolemenud.setMenuFid(1);
			conn_roleMenu.saveOrUpdate(rolemenud);
		}
		conn_Merchant = SpringContext.getBean("com.guolaiwan.bussiness.merchant.dao.MerchantDAO");
		conn_Product = SpringContext.getBean("com.guolaiwan.bussiness.admin.dao.ProductDAO");
		conn_OrderInfo = SpringContext.getBean("com.guolaiwan.bussiness.admin.dao.OrderInfoDAO");
		conn_Balance = SpringContext.getBean("com.guolaiwan.bussiness.admin.dao.BalanceDAO");
		conn_userInfo = SpringContext.getBean("com.guolaiwan.bussiness.admin.dao.UserInfoDAO");

		// 定时商家结算
		TimerTask task = new TimerTask() {
			@Override
			public void run() {

				SimpleDateFormat sdy = new SimpleDateFormat("yyyy");
				SimpleDateFormat sdm = new SimpleDateFormat("MM");
				SimpleDateFormat sdd = new SimpleDateFormat("dd");
				Date date = new Date();
				int year = Integer.parseInt(sdy.format(date));
				int month = Integer.parseInt(sdm.format(date));
				int day = Integer.parseInt(sdd.format(date));
				Calendar cal = Calendar.getInstance();
				cal.set(year, month, day);
				int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);

				cal = Calendar.getInstance();
				cal.add(Calendar.MONTH, 1);
				cal.set(Calendar.DAY_OF_MONTH, 0);
				int lastday = Integer.parseInt(sdd.format(cal.getTime()));

				if (day != 10 && day != 20 && day != lastday) {
					return;
				}

				if (date.getHours() != 23) {
					return;
				}

				try {
					List<BalancePO> chksBalancePOs = conn_Balance.getByToday();
					if (chksBalancePOs != null && !chksBalancePOs.isEmpty()) {
						return;
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				List<MerchantPO> merchantPOs = new ArrayList<MerchantPO>();
				List<ProductPO> productPOs = new ArrayList<ProductPO>();
				List<OrderInfoPO> orderInfoPOs = new ArrayList<OrderInfoPO>();
				List<CommercialSettlementVO> volist = new ArrayList<CommercialSettlementVO>();
				String num = "";
				int count;
				merchantPOs = conn_Merchant.findAll();

				if (merchantPOs != null) {

					for (int i = 0; i < merchantPOs.size(); i++) {
						// Mr黄擦涛的屁股
						List<OrderInfoPO> balanceOrders = conn_OrderInfo
								.getOrdersByMerBalanced(merchantPOs.get(i).getId());
						double totalBalance = 0;
						double accured = 0;
						for (OrderInfoPO order : balanceOrders) {
							totalBalance += order.getPayMoney();
							if (order.getProductId() > 0) {
								ProductPO productPO = conn_Product.get(order.getProductId());
								if (order.getActivityId() != 0) {

								} else if (productPO.getProductCommissionCode() == 1) {
									accured += productPO.getProductCommissionPrice() * order.getProductNum();
								} else {
									accured += order.getPayMoney() * productPO.getProductCommissionPrice() / 100;
								}
							}else{
								List<ProductPO> productPOs2=conn_Product.findByMerchantId(order.getShopId());
								if(!productPOs2.isEmpty()){
									ProductPO productPO = productPOs2.get(0);
									if (productPO.getProductCommissionCode() == 1) {
										accured += productPO.getProductCommissionPrice() * order.getProductNum();
									} else {
										accured += order.getPayMoney() * productPO.getProductCommissionPrice() / 100;
									}
								}
								
							}
							order.setBalance(1);
							order.setSettleDate(new Date());
							conn_OrderInfo.saveOrUpdate(order);
						}
						if (balanceOrders.size() != 0) {
							BalancePO balancePO = new BalancePO();
							balancePO.setAmount(totalBalance - accured);
							balancePO.setBankNo(merchantPOs.get(i).getShopBankId());
							balancePO.setMerchantId(merchantPOs.get(i).getId());
							balancePO.setMerchantName(merchantPOs.get(i).getShopName());
							balancePO.setAccrued(accured);
							balancePO.setSettleDate(new Date());
							conn_Balance.save(balancePO);
						}
					}

				}
			}
		};
		Timer timer = new Timer();
		long delay = 0;
		long intevalPeriod = 3600 * 1000;
		timer.scheduleAtFixedRate(task, delay, intevalPeriod);

		conn_role = SpringContext.getBean("com.guolaiwan.bussiness.admin.dao.RoleDAO");
		RolePO rolePO = conn_role.getByField("roleName", "超级管理员");
		if (rolePO == null) {
			RolePO role = new RolePO();
			// role.setId(1L);
			role.setRoleName("超级管理员");
			role.setDescription("权限分配");

			conn_role.saveOrUpdate(role);
		}

		conn_sysConfig = SpringContext.getBean("com.guolaiwan.bussiness.admin.dao.SysConfigDAO");
		SysConfigPO sysConfigPO = conn_sysConfig.getSysConfig();
		if (sysConfigPO == null) {
			SysConfigPO sConfig = new SysConfigPO();
			sConfig.setWebUrl("http://" + WXContants.Website + "/file/");
			sConfig.setFolderUrl("/usr/sbin/guolaiwan/tomcat/apache-tomcat-7.0.42/webapps/file/");

			conn_sysConfig.saveOrUpdate(sConfig);
		}
		// 公司总部初始化
		conn_company = SpringContext.getBean("com.guolaiwan.bussiness.admin.dao.CompanyDAO");
		List<CompanyPO> companys = conn_company.findByField("cType", CompanyType.HEAD);
		if (companys == null || companys.size() == 0) {
			CompanyPO company = new CompanyPO();
			company.setAddress("遵化市万乘晟象大厦12层");
			company.setComName("过来玩总公司");
			company.setComCode("0001");
			company.setcType(CompanyType.HEAD);
			company.setEnable(1);
			company.setUpdateTime(new Date());
			conn_company.saveOrUpdate(company);
		}
		// 直播线程
		// LiveThread live = new LiveThread();
		// live.start();
		System.out.println("context 初始化!");

	}

}
