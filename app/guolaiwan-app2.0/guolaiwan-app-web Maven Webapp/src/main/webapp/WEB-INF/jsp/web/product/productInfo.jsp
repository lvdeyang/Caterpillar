<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!doctype html>
<html>
 <head>
	<meta charset="UTF-8">
	<meta name="Generator" content="EditPlus®">
	<meta name="Author" content="">
	<meta name="Keywords" content="">
	<meta name="Description" content="">
	<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE"> 
	<meta name="renderer" content="webkit">
	<title>${product.productName}</title>
    <link rel="shortcut icon" type="image/x-icon" href="<%=path %>/webtheme/theme/icon/favicon.ico">
    <link rel="stylesheet" type="text/css" href="<%=path %>/webtheme/theme/css/base.css">
    <link rel="stylesheet" type="text/css" href="<%=path %>/webtheme/theme/css/home.css">
   
   
</head>
 <body>

<!--- header begin-->
<c:import url="${path}/web/top"></c:import>
<!-- header End -->

<!-- 商品详情 begin -->
<section>
    <div class="pc-details" >
        <div class="containers">
            <div class="pc-nav-item"><a class="pc-title" href="<%=path %>/product/productList?m=${product.productModularCode}">${product.productModularCodeName}</a> &gt; <a href="<%=path %>/product/productList?m=${product.productModularCode}&mc=${product.productClassCode}">${product.productClassName}</a> &gt; <a href="#">${product.productName}</a> </div>
            <div class="pc-details-l">
                <div class="pc-product clearfix">
                    <div class="pc-product-h" >
                        <div class="pc-product-top" style="position:relative;">
                        	<img src="${sysConfig.webUrl}${pics[0]}" id="big_img" width="418" height="418" >
                        	<div class="sm"></div>
                        	<div class="rig"><img id="big_img2" src="${sysConfig.webUrl}${pics[0]}"  width="1079" height="1079"></div>
                        </div>
                        <div class="pc-product-bop clearfix" id="pro_detail">
                            <ul class="moreImage">
                            	<c:forEach items="${pics}" var="pic">
                                <li><a href="javascript:void(0);" simg="${sysConfig.webUrl}${pic}"><img src="${sysConfig.webUrl}${pic}" width="58" height="58"></a> </li>
                            	</c:forEach>
                            
                            	
                            </ul>
                            
                        </div>
                    </div>
                   
                    <div class="pc-product-t">
                        <div class="pc-name-info">
                            <h1>${product.productSubtitle}</h1>
                            
                             <c:choose>
                    		<c:when test="${isDistributor==1}">
                    			<p class="clearfix pc-rate"><strong>￥${distributor.distributorPrice}</strong> <!-- <span><em>限时抢购</em>抢购将于<b class="reds">18</b>小时<b class="reds">57</b>分<b class="reds">34</b>秒后结束</span> --></p>
                            	<p><font size="3px"><strike>￥${product.productOldPrice}</strike></font></p>
                            	<p>由<a href="#" class="reds">${distributor.shopName}</a> 负责发货，并提供售后服务。</p>
                    		</c:when>
                    		<c:otherwise>
                    			<p class="clearfix pc-rate"><strong>￥${product.productPrice}</strong> <!-- <span><em>限时抢购</em>抢购将于<b class="reds">18</b>小时<b class="reds">57</b>分<b class="reds">34</b>秒后结束</span> --></p>
                           		<p><font size="3px"><strike>￥${product.productOldPrice}</strike></font></p>
                           	 	<p>由<a href="#" class="reds">${product.productMerchantName}</a> 负责发货，并提供售后服务。</p>
                    		</c:otherwise>
                    		</c:choose>
                            
                        </div>
                        <div class="pc-dashed clearfix">
                            <span>累计销量：<em class="reds">${product.productSaleNum}</em> 件</span><b>|</b><span>累计评价：<em class="reds">3888</em></span>
                        </div>
                        </br>
                        </br>
                        
                        <div class="pc-size">
                            
                            <div class="pc-telling clearfix">
                                <div class="pc-version2" style="display:inline" >数量：</div>
                                <div class="pc-adults clearfix" style="display:inline">
                                    <div class="pc-adults-p clearfix fl" style="width:auto">
                                        <input type="button" style="width:20px" id="cut" value="-">
										<input type="text" style="width:40px; text-align:center" id="pcount" disabled="disabled" value=1>
										<input type="button" style="width:20px" id="add" value="+">
                                    </div>
                                    <div class="fl pc-letter2">件</div>
                                    <div class="fl pc-stock2">库存<em class="stock">${product.productStock}</em>件</div>
                                </div>
                            </div>
                            <div class="pc-number clearfix"><span class="fl">商品编号：${product.id}</span> <span class="fr">分享 收藏</span></div>
                        </div>
                        </br>
                        </br>
                        </br>
                        </br>
                        </br>
                        </br>
                        
                        <div class="pc-emption">
                            <a href="javascript:buyNow();" class="buy">立即购买</a>
                        </div>
                    </div>
                    <div class="pc-product-s">
                    <c:choose>
                    <c:when test="${isDistributor==1}">
                          <div class="pc-shoplo"><a href="#"><img src="${sysConfig.webUrl}${distributor.shopPic}" width="175px" height="105px"></a> </div>
                        <div class="pc-shopti">
                            <h2>${distributor.shopName}</h2>
                            <p>公司名称：${distributor.shopName}</p>
                            <p>所在地：${distributor.shopAddress}</p>
                        </div>
                        <div class="pc-custom"><a href="http://wpa.qq.com/msgrd?v=3&amp;uin=${merchant.shopQQ}&amp;site=qq&amp;menu=yes" target="_blank">联系客服</a> </div>
                        <div class="pc-trigger">
                            <a href="<%=basePath%>web/distributor/productList?distributorId=${distributor.id}">分销商店铺</a>
                            <a href="#" style="margin:0;">关注店铺</a>
                        </div>
                    </c:when>
                    <c:otherwise>
                       <div class="pc-shoplo"><a href="#"><img src="${sysConfig.webUrl}${merchant.shopPic}" width="175px" height="105px"></a> </div>
                        <div class="pc-shopti">
                            <h2>${merchant.shopName}</h2>
                            <p>公司名称：${merchant.shopName}</p>
                            <p>所在地：${merchant.shopAddress}</p>
                        </div>
                        <div class="pc-custom"><a href="http://wpa.qq.com/msgrd?v=3&amp;uin=${merchant.shopQQ}&amp;site=qq&amp;menu=yes" target="_blank">联系客服</a> </div>
                        <div class="pc-trigger">
                            <a href="<%=basePath%>web/merchant/productList?merchant=${merchant.uuid}">进入店铺</a>
                            <a href="#" style="margin:0;">关注店铺</a>
                        </div>
                    </c:otherwise>
                    </c:choose>
                  
                    </div>
                </div>
            </div>
        </div>
    </div><!-- 
    <div class="huanghelou" style="width:100%;">
        <div class="baisha" style="width:1200px; margin:1px auto; margin-top:10x;">
         <div class="fenxiaoshang" style="width:1200px;margin-top:10px;margin-bottom:10px;">
  		<h1>分销商列表</h1></div>
   <c:forEach items="${distributors }" var="distributor">
        	<ul>
        	<li>
        	<div style="height:205px; width:208px; float:left; margin-left:3px;">
        	<a href="<%=basePath%>product/productInfo?info=${product.uuid}&distributor=${distributor.id}">
            <img width="208px" height="140px" src="${distributor.shopPic}"></a>
            <div class="yuxi" style="width:205px;height:15px;float:left;margin-left:3px;">
            	<div style="width:205px;height:30px;"><span style="font-size:16px;color:black;">${distributor.shopName}</span></span></div>
             	<div style="width:205px;height:20px;">现价:<span style="color:#b71d1d;font-size:20px;">￥${distributor.distributorPrice}</span></div>
        	</div>
        	<div><a href="#=${distributo.shopName}"></a></div>
        	</li>
   </c:forEach></ul>
   
  
   </div>
    </div> 
   <c:choose>
   <c:when test="${isDistributor==1}">
   		<div style="height:205px; width:208px; float:left; margin-left:3px;">
        	<a href="<%=basePath%>product/productInfo?info=${product.uuid}">
            <img width="208px" height="140px" src="${sysConfig.webUrl}${merchant.shopPic}"></a>
            <span style="font-size:20px;letter-spacing:1px;font-weight:bold;color:red;"><a href="<%=basePath%>product/productInfo?info=${product.uuid}">返回商家</a></span>
   		</div>
   </c:when>
   </c:choose> 
    -->
   
    <div class="containers clearfix" style="margin-top:20px;">
 
        <div class="fl">
        
            <!-- <div class="menu_list" id="firstpane">
                <h2>店内分类</h2>
                <h3 class="menu_head current">电玩</h3>
                <div class="menu_body" style="display: none;">
                    <a href="#">耳机耳麦</a>
                    <a href="#">游戏机</a>
                </div>
                <h3 class="menu_head">手机</h3>
                <div class="menu_body" style="display: none;">
                    <a href="#">手机</a>
                    <a href="#">手机</a>
                    <a href="#">手机</a>
                </div>

                <h3 class="menu_head">耳机耳麦</h3>
                <div class="menu_body" style="display: none;">
                    <a href="#">耳机耳麦</a>
                    <a href="#">耳机耳麦</a>
                    <a href="#">耳机耳麦</a>
                    <a href="#">耳机耳麦</a>
                </div>
            </div> -->
        </div>
        <div class="pc-info fr">
            <div class="pc-overall">
                <ul id="H-table1" class="brand-tab H-table1 H-table-shop clearfix ">
                    <li class="cur"><a href="javascript:void(0);">商品介绍</a></li>
                    <li><a href="javascript:void(0);">商品评价<em class="reds">(91)</em></a></li>
                    <li><a href="javascript:void(0);">售后保障</a></li>
                </ul>
                <div class="pc-term clearfix">
                   <div class="H-over1 pc-text-word clearfix">
                       <ul class="clearfix">
                               <p>${product.productIntroduce}</p>
                       </ul>
                       <div class="pc-line"></div>
                       <!-- <div>
                           <div><img src="theme/pa/ad-4.jpg" width="100%"></div>
                           <div><img src="theme/pa/ad-2.jpg" width="100%"></div>
                           <div><img src="theme/pa/ad-3.jpg" width="100%"></div>
                           <div><img src="theme/pa/ad-4.jpg" width="100%"></div>
                           <div><img src="theme/pa/ad-5.jpg" width="100%"></div>
                           <div><img src="theme/pa/ad-6.jpg" width="100%"></div>
                           <div><img src="theme/pa/ad-7.jpg" width="100%"></div>
                       </div> -->
                   </div>
                   <div class="H-over1" style="display:none">
                       <div class="pc-comment fl"><strong>86<span>%</span></strong><br> <span>好评度</span></div>
                       <div class="pc-percent fl">
                           <dl>
                               <dt>好评<span>(86%)</span></dt>
                               <dd><div style="width:86px"></div></dd>
                           </dl>
                           <dl>
                               <dt>中评<span>(86%)</span></dt>
                               <dd><div style="width:86px"></div></dd>
                           </dl>
                           <dl>
                               <dt>好评<span>(86%)</span></dt>
                               <dd><div style="width:86px"></div></dd>
                           </dl>
                       </div>
                   </div>
                   <div class="H-over1 pc-text-title" style="display:none">
                       <p>本产品全国联保，享受三包服务，质保期为：一年质保
                           如因质量问题或故障，凭厂商维修中心或特约维修点的质量检测证明，享受7日内退货，15日内换货，15日以上在质保期内享受免费保修等三包服务！
                           (注:如厂家在商品介绍中有售后保障的说明,则此商品按照厂家说明执行售后保障服务。) 您可以查询本品牌在各地售后服务中心的联系方式，请点击这儿查询......</p>
                       <div class="pc-line"></div>
                   </div>
                </div>
            </div>
            <div class="pc-overall">
                <ul class="brand-tab H-table H-table-shop clearfix " id="H-table" style="margin-left:0;">
                    <li class="cur"><a href="javascript:void(0);">全部评价（199）</a></li>
                    <!-- <li><a href="javascript:void(0);">好评<em class="reds">（33）</em></a></li>
                    <li><a href="javascript:void(0);">中评<em class="reds">（02）</em></a></li>
                    <li><a href="javascript:void(0);">差评<em class="reds">（01）</em></a></li> -->
                </ul>
                <!-- <div class="pc-term clearfix">
                    <div class="pc-column">
                        <span class="column1">评价心得</span>
                        <span class="column2">顾客满意度</span>
                        <span class="column3">购买信息</span>
                        <span class="column4">评论者</span>
                    </div>
                    <div class="H-over  pc-comments clearfix">
                        <ul class="clearfix">
                            <li class="clearfix">
                                <div class="column1">
                                    <p class="clearfix"><a href="#">回复<em>（90）</em></a> <a href="#">赞<em>（90）</em></a> </p>
                                    <p>一次用三星，不是很顺手，但咨询客服后终于上手了，感觉性价比相当不错，值得购买。但最想说的是京东客服更好，产品信得过，正品行货，买的放心。</p>
                                    <p class="column5">2014-11-25 14:32</p>
                                </div>
                                <div class="column2"><img src="theme/icon/star.png"></div>
                                <div class="column3">颜色：云石白</div>
                                <div class="column4">
                                    <p><img src="theme/icon/user.png"></p>
                                    <p>2014-11-23 22:37 购买</p>
                                </div>
                            </li>
                            <li class="clearfix">
                                <div class="column1">
                                    <p class="clearfix"><a href="#">回复<em>（90）</em></a> <a href="#">赞<em>（90）</em></a> </p>
                                    <p>一次用三星，不是很顺手，但咨询客服后终于上手了，感觉性价比相当不错，值得购买。但最想说的是京东客服更好，产品信得过，正品行货，买的放心。</p>
                                    <p class="column5">2014-11-25 14:32</p>
                                </div>
                                <div class="column2"><img src="theme/icon/star.png"></div>
                                <div class="column3">颜色：云石白</div>
                                <div class="column4">
                                    <p><img src="theme/icon/user.png"></p>
                                    <p>2014-11-23 22:37 购买</p>
                                </div>
                            </li>
                            <li class="clearfix">
                                <div class="column1">
                                    <p class="clearfix"><a href="#">回复<em>（90）</em></a> <a href="#">赞<em>（90）</em></a> </p>
                                    <p>一次用三星，不是很顺手，但咨询客服后终于上手了，感觉性价比相当不错，值得购买。但最想说的是京东客服更好，产品信得过，正品行货，买的放心。</p>
                                    <p class="column5">2014-11-25 14:32</p>
                                </div>
                                <div class="column2"><img src="theme/icon/star.png"></div>
                                <div class="column3">颜色：云石白</div>
                                <div class="column4">
                                    <p><img src="theme/icon/user.png"></p>
                                    <p>2014-11-23 22:37 购买</p>
                                </div>
                            </li>
                            <li class="clearfix">
                                <div class="column1">
                                    <p class="clearfix"><a href="#">回复<em>（90）</em></a> <a href="#">赞<em>（90）</em></a> </p>
                                    <p>一次用三星，不是很顺手，但咨询客服后终于上手了，感觉性价比相当不错，值得购买。但最想说的是京东客服更好，产品信得过，正品行货，买的放心。</p>
                                    <p class="column5">2014-11-25 14:32</p>
                                </div>
                                <div class="column2"><img src="theme/icon/star.png"></div>
                                <div class="column3">颜色：云石白</div>
                                <div class="column4">
                                    <p><img src="theme/icon/user.png"></p>
                                    <p>2014-11-23 22:37 购买</p>
                                </div>
                            </li>
                            <li class="clearfix">
                                <div class="column1">
                                    <p class="clearfix"><a href="#">回复<em>（90）</em></a> <a href="#">赞<em>（90）</em></a> </p>
                                    <p>一次用三星，不是很顺手，但咨询客服后终于上手了，感觉性价比相当不错，值得购买。但最想说的是京东客服更好，产品信得过，正品行货，买的放心。</p>
                                    <p class="column5">2014-11-25 14:32</p>
                                </div>
                                <div class="column2"><img src="theme/icon/star.png"></div>
                                <div class="column3">颜色：云石白</div>
                                <div class="column4">
                                    <p><img src="theme/icon/user.png"></p>
                                    <p>2014-11-23 22:37 购买</p>
                                </div>
                            </li>
                            <li class="clearfix">
                                <div class="column1">
                                    <p class="clearfix"><a href="#">回复<em>（90）</em></a> <a href="#">赞<em>（90）</em></a> </p>
                                    <p>一次用三星，不是很顺手，但咨询客服后终于上手了，感觉性价比相当不错，值得购买。但最想说的是京东客服更好，产品信得过，正品行货，买的放心。</p>
                                    <p class="column5">2014-11-25 14:32</p>
                                </div>
                                <div class="column2"><img src="theme/icon/star.png"></div>
                                <div class="column3">颜色：云石白</div>
                                <div class="column4">
                                    <p><img src="theme/icon/user.png"></p>
                                    <p>2014-11-23 22:37 购买</p>
                                </div>
                            </li>
                        </ul>
                    </div> -->
                    <!-- <div style="display:none" class="H-over pc-comments">
                        <ul class="clearfix">
                            <li class="clearfix">
                                <div class="column1">
                                    <p class="clearfix"><a href="#">回复<em>（90）</em></a> <a href="#">赞<em>（90）</em></a> </p>
                                    <p>一次用三星，不是很顺手，但咨询客服后终于上手了，感觉性价比相当不错，值得购买。但最想说的是京东客服更好，产品信得过，正品行货，买的放心。</p>
                                    <p class="column5">2014-11-25 14:32</p>
                                </div>
                                <div class="column2"><img src="theme/icon/star.png"></div>
                                <div class="column3">颜色：云石白</div>
                                <div class="column4">
                                    <p><img src="theme/icon/user.png"></p>
                                    <p>2014-11-23 22:37 购买</p>
                                </div>
                            </li>
                            <li class="clearfix">
                                <div class="column1">
                                    <p class="clearfix"><a href="#">回复<em>（90）</em></a> <a href="#">赞<em>（90）</em></a> </p>
                                    <p>一次用三星，不是很顺手，但咨询客服后终于上手了，感觉性价比相当不错，值得购买。但最想说的是京东客服更好，产品信得过，正品行货，买的放心。</p>
                                    <p class="column5">2014-11-25 14:32</p>
                                </div>
                                <div class="column2"><img src="theme/icon/star.png"></div>
                                <div class="column3">颜色：云石白</div>
                                <div class="column4">
                                    <p><img src="theme/icon/user.png"></p>
                                    <p>2014-11-23 22:37 购买</p>
                                </div>
                            </li>
                            <li class="clearfix">
                                <div class="column1">
                                    <p class="clearfix"><a href="#">回复<em>（90）</em></a> <a href="#">赞<em>（90）</em></a> </p>
                                    <p>一次用三星，不是很顺手，但咨询客服后终于上手了，感觉性价比相当不错，值得购买。但最想说的是京东客服更好，产品信得过，正品行货，买的放心。</p>
                                    <p class="column5">2014-11-25 14:32</p>
                                </div>
                                <div class="column2"><img src="theme/icon/star.png"></div>
                                <div class="column3">颜色：云石白</div>
                                <div class="column4">
                                    <p><img src="theme/icon/user.png"></p>
                                    <p>2014-11-23 22:37 购买</p>
                                </div>
                            </li>
                            <li class="clearfix">
                                <div class="column1">
                                    <p class="clearfix"><a href="#">回复<em>（90）</em></a> <a href="#">赞<em>（90）</em></a> </p>
                                    <p>一次用三星，不是很顺手，但咨询客服后终于上手了，感觉性价比相当不错，值得购买。但最想说的是京东客服更好，产品信得过，正品行货，买的放心。</p>
                                    <p class="column5">2014-11-25 14:32</p>
                                </div>
                                <div class="column2"><img src="theme/icon/star.png"></div>
                                <div class="column3">颜色：云石白</div>
                                <div class="column4">
                                    <p><img src="theme/icon/user.png"></p>
                                    <p>2014-11-23 22:37 购买</p>
                                </div>
                            </li>
                            <li class="clearfix">
                                <div class="column1">
                                    <p class="clearfix"><a href="#">回复<em>（90）</em></a> <a href="#">赞<em>（90）</em></a> </p>
                                    <p>一次用三星，不是很顺手，但咨询客服后终于上手了，感觉性价比相当不错，值得购买。但最想说的是京东客服更好，产品信得过，正品行货，买的放心。</p>
                                    <p class="column5">2014-11-25 14:32</p>
                                </div>
                                <div class="column2"><img src="theme/icon/star.png"></div>
                                <div class="column3">颜色：云石白</div>
                                <div class="column4">
                                    <p><img src="theme/icon/user.png"></p>
                                    <p>2014-11-23 22:37 购买</p>
                                </div>
                            </li>
                            <li class="clearfix">
                                <div class="column1">
                                    <p class="clearfix"><a href="#">回复<em>（90）</em></a> <a href="#">赞<em>（90）</em></a> </p>
                                    <p>一次用三星，不是很顺手，但咨询客服后终于上手了，感觉性价比相当不错，值得购买。但最想说的是京东客服更好，产品信得过，正品行货，买的放心。</p>
                                    <p class="column5">2014-11-25 14:32</p>
                                </div>
                                <div class="column2"><img src="theme/icon/star.png"></div>
                                <div class="column3">颜色：云石白</div>
                                <div class="column4">
                                    <p><img src="theme/icon/user.png"></p>
                                    <p>2014-11-23 22:37 购买</p>
                                </div>
                            </li>
                        </ul>
                    </div> -->
                    <div style="display:none" class="H-over pc-comments">
                        <ul class="clearfix">
                        <c:forEach items="${comments}" var="comment">
                               <li class="clearfix">
                                <div class="column1">
                                    <p class="clearfix"><a href="#"><em></em></a> <a href="#">评分<em>${comment.start}</em></a> </p>
                                    <p>${comment.content}</p>
                                    <p class="column5">${comment.userDate}</p>
                                </div>
                                <div class="column2"><img src="theme/icon/star.png"></div>
                                <div class="column3">颜色：云石白</div>
                                <div class="column4">
                                    <p><img src="${sysConfig.webUrl}${comment.userHeadimg} "></p>
                                    <p>2014-11-23 22:37 购买</p>
                                </div>
                            </li>
                          </c:forEach>
                           <!--  
                            <li class="clearfix">
                                <div class="column1">
                                    <p class="clearfix"><a href="#">回复<em>（90）</em></a> <a href="#">赞<em>（90）</em></a> </p>
                                    <p>一次用三星，不是很顺手，但咨询客服后终于上手了，感觉性价比相当不错，值得购买。但最想说的是京东客服更好，产品信得过，正品行货，买的放心。</p>
                                    <p class="column5">2014-11-25 14:32</p>
                                </div>
                                <div class="column2"><img src="theme/icon/star.png"></div>
                                <div class="column3">颜色：云石白</div>
                                <div class="column4">
                                    <p><img src="theme/icon/user.png"></p>
                                    <p>2014-11-23 22:37 购买</p>
                                </div>
                            </li>
                            <li class="clearfix">
                                <div class="column1">
                                    <p class="clearfix"><a href="#">回复<em>（90）</em></a> <a href="#">赞<em>（90）</em></a> </p>
                                    <p>一次用三星，不是很顺手，但咨询客服后终于上手了，感觉性价比相当不错，值得购买。但最想说的是京东客服更好，产品信得过，正品行货，买的放心。</p>
                                    <p class="column5">2014-11-25 14:32</p>
                                </div>
                                <div class="column2"><img src="theme/icon/star.png"></div>
                                <div class="column3">颜色：云石白</div>
                                <div class="column4">
                                    <p><img src="theme/icon/user.png"></p>
                                    <p>2014-11-23 22:37 购买</p>
                                </div>
                            </li>
                            <li class="clearfix">
                                <div class="column1">
                                    <p class="clearfix"><a href="#">回复<em>（90）</em></a> <a href="#">赞<em>（90）</em></a> </p>
                                    <p>一次用三星，不是很顺手，但咨询客服后终于上手了，感觉性价比相当不错，值得购买。但最想说的是京东客服更好，产品信得过，正品行货，买的放心。</p>
                                    <p class="column5">2014-11-25 14:32</p>
                                </div>
                                <div class="column2"><img src="theme/icon/star.png"></div>
                                <div class="column3">颜色：云石白</div>
                                <div class="column4">
                                    <p><img src="theme/icon/user.png"></p>
                                    <p>2014-11-23 22:37 购买</p>
                                </div>
                            </li>
                            <li class="clearfix">
                                <div class="column1">
                                    <p class="clearfix"><a href="#">回复<em>（90）</em></a> <a href="#">赞<em>（90）</em></a> </p>
                                    <p>一次用三星，不是很顺手，但咨询客服后终于上手了，感觉性价比相当不错，值得购买。但最想说的是京东客服更好，产品信得过，正品行货，买的放心。</p>
                                    <p class="column5">2014-11-25 14:32</p>
                                </div>
                                <div class="column2"><img src="theme/icon/star.png"></div>
                                <div class="column3">颜色：云石白</div>
                                <div class="column4">
                                    <p><img src="theme/icon/user.png"></p>
                                    <p>2014-11-23 22:37 购买</p>
                                </div>
                            </li>
                            <li class="clearfix">
                                <div class="column1">
                                    <p class="clearfix"><a href="#">回复<em>（90）</em></a> <a href="#">赞<em>（90）</em></a> </p>
                                    <p>一次用三星，不是很顺手，但咨询客服后终于上手了，感觉性价比相当不错，值得购买。但最想说的是京东客服更好，产品信得过，正品行货，买的放心。</p>
                                    <p class="column5">2014-11-25 14:32</p>
                                </div>
                                <div class="column2"><img src="theme/icon/star.png"></div>
                                <div class="column3">颜色：云石白</div>
                                <div class="column4">
                                    <p><img src="theme/icon/user.png"></p>
                                    <p>2014-11-23 22:37 购买</p>
                                </div>
                            </li>
                            <li class="clearfix">
                                <div class="column1">
                                    <p class="clearfix"><a href="#">回复<em>（90）</em></a> <a href="#">赞<em>（90）</em></a> </p>
                                    <p>一次用三星，不是很顺手，但咨询客服后终于上手了，感觉性价比相当不错，值得购买。但最想说的是京东客服更好，产品信得过，正品行货，买的放心。</p>
                                    <p class="column5">2014-11-25 14:32</p>
                                </div>
                                <div class="column2"><img src="theme/icon/star.png"></div>
                                <div class="column3">颜色：云石白</div>
                                <div class="column4">
                                    <p><img src="theme/icon/user.png"></p>
                                    <p>2014-11-23 22:37 购买</p>
                                </div>
                            </li> -->
                        </ul>
                    </div>
                    <div style="display:none" class="H-over pc-comments">
                        <ul class="clearfix">
                            <li class="clearfix">
                                <div class="column1">
                                    <p class="clearfix"><a href="#">回复<em>（90）</em></a> <a href="#">赞<em>（90）</em></a> </p>
                                    <p>一次用三星，不是很顺手，但咨询客服后终于上手了，感觉性价比相当不错，值得购买。但最想说的是京东客服更好，产品信得过，正品行货，买的放心。</p>
                                    <p class="column5">2014-11-25 14:32</p>
                                </div>
                                <div class="column2"><img src="theme/icon/star.png"></div>
                                <div class="column3">颜色：云石白</div>
                                <div class="column4">
                                    <p><img src="theme/icon/user.png"></p>
                                    <p>2014-11-23 22:37 购买</p>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="clearfix">
                <div class="fr pc-search-g pc-search-gs">
                    <a href="#" class="fl " style="display:none">上一页</a>
                    <a class="current" href="#">1</a>
                    <a href="javascript:;">2</a>
                    <a href="javascript:;">3</a>
                    <a href="javascript:;">4</a>
                    <a href="javascript:;">5</a>
                    <a href="javascript:;">6</a>
                    <a href="javascript:;">7</a>
                    <span class="pc-search-di">…</span>
                    <a href="javascript:;">1088</a>
                    <a href="javascript:;" class="" title="使用方向键右键也可翻到下一页哦！">下一页</a>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- 商品详情 End -->

<!--- footer begin-->
<c:import url="${path}/web/foot"></c:import>
<!-- footer End -->

<script type="text/javascript" src="<%=request.getContextPath() %>/webtheme/theme/js/head.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/webtheme/theme/js/js-tab.js"></script>
<script>
var basePath = '<%=basePath%>';
var isDistributor = '${isDistributor}';
         $(function(){
         	console.log(isDistributor);
             $(".yScrollListInList1 ul").css({width:$(".yScrollListInList1 ul li").length*(160+84)+"px"});
             $(".yScrollListInList2 ul").css({width:$(".yScrollListInList2 ul li").length*(160+84)+"px"});
             var numwidth=(160+84)*4;
             $(".yScrollListInList .yScrollListbtnl").click(function(){
                 var obj=$(this).parent(".yScrollListInList").find("ul");
                 if (!(obj.is(":animated"))) {
                     var lefts=parseInt(obj.css("left").slice(0,-4));
                     if(lefts<60){
                         obj.animate({left:lefts+numwidth},1000);
                     }
                 }
             })
             $(".yScrollListInList .yScrollListbtnr").click(function(){
                 var obj=$(this).parent(".yScrollListInList").find("ul");
                 var objcds=-(60+(Math.ceil(obj.find("li").length/4)-4)*numwidth);
                 if (!(obj.is(":animated"))) {
                     var lefts=parseInt(obj.css("left").slice(0,-4));
                     if(lefts>objcds){
                         obj.animate({left:lefts-numwidth},1000);
                     }
                 }
             })
         })
     </script>
    <script>
         $(function(){
         	$("#pro_detail a").hover(function(){
         		$("#pro_detail a").removeClass("cur");
         		$(this).addClass("cur");
         		$("#big_img").attr("src",$(this).attr("simg"));
         		$("#big_img2").attr("src",$(this).attr("simg"));
         	});
         	
         	$(".attrdiv a").click(function(){
         		$(".attrdiv a").removeClass("cur");
				$(this).addClass("cur");
         	});
         	
         	$('.amount2').click(function(){
		        var num_input = $("#subnum");
		        var buy_num = (num_input.val()-1)>0?(num_input.val()-1):1;
		        num_input.val(buy_num);
		    });
		
		    $('.amount1').click(function(){
		        var num_input = $("#subnum");
		        var buy_num = Number(num_input.val())+1;
		        num_input.val(buy_num);
		    });
			
             $("#H-table li").each(function(i){
                 $(this).click((function(k){
                     var _index = k;
                     return function(){
                         $(this).addClass("cur").siblings().removeClass("cur");
                         $(".H-over").hide();
                         $(".H-over:eq(" + _index + ")").show();
                     }
                 })(i));
             });
             $("#H-table1 li").each(function(i){
                 $(this).click((function(k){
                     var _index = k;
                     return function(){
                         $(this).addClass("cur").siblings().removeClass("cur");
                         $(".H-over1").hide();
                         $(".H-over1:eq(" + _index + ")").show();
                     }
                 })(i));
             });
         });
     </script>
    <script type="text/javascript">
         (function(a){
             a.fn.hoverClass=function(b){
                 var a=this;
                 a.each(function(c){
                     a.eq(c).hover(function(){
                         $(this).addClass(b)
                     },function(){
                         $(this).removeClass(b)
                     })
                 });
                 return a
             };
         })(jQuery);

         $(function(){
             $("#pc-nav").hoverClass("current");
         });
     </script>
     <script type="text/javascript">
         $(document).ready(function(){

             $("#firstpane .menu_body:eq(0)").show();
             $("#firstpane h3.menu_head").click(function(){
                 $(this).addClass("current").next("div.menu_body").slideToggle(300).siblings("div.menu_body").slideUp("slow");
                 $(this).siblings().removeClass("current");
             });

             $("#secondpane .menu_body:eq(0)").show();
             $("#secondpane h3.menu_head").mouseover(function(){
                 $(this).addClass("current").next("div.menu_body").slideDown(500).siblings("div.menu_body").slideUp("slow");
                 $(this).siblings().removeClass("current");
             });
             
             //产品数量的增加和减少
            $("#cut").click(function(){
            	var pcount = $("#pcount").val();
            	if(pcount==1){
            		$("#cut").attr("display","display");
            		return false;
            	}else{
            		pcount--;
            		$("#pcount").val(pcount);
            	}
            	
            })
            $("#add").click(function(){
            	var pcount = $("#pcount").val();
            	var stock = $("em.stock").html();
            	if(pcount==stock){
            		$("#add").attr("display","display");
            		return false;
            	}else{
            		pcount++;
            		$("#pcount").val(pcount);
            	}
            })

			//放大镜
            $(".pc-product-top").mousemove(function(e){
                $('.sm').show();
                $('.rig').show();
                var x = e.clientX;
                var y = e.clientY;
                var x1 = $(".pc-product-top")[0].getBoundingClientRect().left;
                var y1 = $(".pc-product-top")[0].getBoundingClientRect().top;
                var xx = x - x1;
                var yy = y - y1;
                if (xx <= 75) {
                    xx = 75
                } else if (xx >= 343) {
                    xx = 343
                }
                if (yy <= 75) {
                    yy = 75
                } else if (yy >= 343) {
                    yy = 343
                }
                $('.sm')[0].style.left = xx - 75+ 'px';
                $('.sm')[0].style.top = yy - 75 + 'px';
                $('.rig img')[0].style.left = -(xx - 75) * (380 / 150) + 'px';
                $('.rig img')[0].style.top = -(yy - 75) * (370 / 150) + 'px';

            })
            $(".pc-product-top").mouseout(
                function(){
                $('.rig').hide();
                $('.sm').hide()
            })

         });
         
         function buyNow(){
         	 var count = $("#pcount").val();
         	 console.log(count);
         	 var i=0;
         	 if( !/^\+?[1-9][0-9]*$/.test(count)||count>${product.productStock}){
         		$("#subnum").css({"border":"1px solid #ff4646"});
         		alert("库存不足，无法购买！");
         		i+=1;
         	 }
         	 if(i!=0){
         	 	return false;
         	 }
         	 window.location.href =  basePath+"user/order/add?pro=${product.uuid}&count="+count+"&type=MERCHANT";
      	 
         }





       
     </script>

</body>
</html>
