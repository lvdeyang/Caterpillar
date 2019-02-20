package com.guolaiwan.bussiness.chapman.product.enumeration;

public enum ProductDetailStyle {

	//删除线
	DELETELINE("删除线", "text-decoration:line-through;"),
	
	//价格
	PRICEHIGHLIGHT("价格高亮", "font-size:18px; color:#d33724;");
	
	private String name;
	
	private String css;
	
	private ProductDetailStyle(String name, String css){
		this.name = name;
		this.css = css;
	}
	
	public String getName(){
		return this.name;
	}

	public String getCss(){
		return this.css;
	}
	
	public static ProductDetailStyle fromName(String name) throws Exception{
		if("删除线".equals(name)){
			return DELETELINE;
		}else if("价格高亮".equals(name)){
			return DELETELINE;
		}else{
			throw new Exception("错误的产品详情渲染类型："+name);
		}
	}
	
	public static ProductDetailStyle fromCss(String css) throws Exception{
		if("text-decoration:line-through;".equals(css)){
			return DELETELINE;
		}else if("font-size:18px; color:#d33724;".equals(css)){
			return DELETELINE;
		}else{
			throw new Exception("错误的产品详情渲染类型："+css);
		}
	}
	
	public static ProductDetailStyle fromString(String s) throws Exception{
		if("DELETELINE".equals(s)){
			return DELETELINE;
		}else if("PRICEHIGHLIGHT".equals(s)){
			return PRICEHIGHLIGHT;
		}else{
			throw new Exception("错误的产品详情渲染类型："+s);
		}
	}
	
}
