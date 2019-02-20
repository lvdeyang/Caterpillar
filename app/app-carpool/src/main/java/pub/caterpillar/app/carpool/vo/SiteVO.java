package pub.caterpillar.app.carpool.vo;

import pub.caterpillar.app.carpool.po.SitePO;
import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class SiteVO extends AbstractBaseVO<SiteVO, SitePO>{

	private String name;
	
	public String getName() {
		return name;
	}

	public SiteVO setName(String name) {
		this.name = name;
		return this;
	}

	@Override
	public SiteVO set(SitePO entity) throws Exception {
		this.setId(entity.getId())
		    .setUuid(entity.getUuid())
		    .setUpdateTime(DateUtil.format(entity.getUpdateTime(), DateUtil.dateTimePattern))
		    .setName(entity.getName()==null?"":entity.getName());
		return this;
	}
	
}
