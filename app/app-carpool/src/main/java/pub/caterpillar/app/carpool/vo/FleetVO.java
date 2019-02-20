package pub.caterpillar.app.carpool.vo;

import pub.caterpillar.app.carpool.po.FleetPO;
import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class FleetVO extends AbstractBaseVO<FleetVO, FleetPO>{

	private String name;
	
	private String mobile;
	
	public String getName() {
		return name;
	}

	public FleetVO setName(String name) {
		this.name = name;
		return this;
	}

	public String getMobile() {
		return mobile;
	}

	public FleetVO setMobile(String mobile) {
		this.mobile = mobile;
		return this;
	}

	@Override
	public FleetVO set(FleetPO entity) throws Exception {
		this.setId(entity.getId())
		    .setUuid(entity.getUuid())
		    .setUpdateTime(DateUtil.format(entity.getUpdateTime(), DateUtil.dateTimePattern))
		    .setName(entity.getName()==null?"":entity.getName())
		    .setMobile(entity.getMobile()==null?"":entity.getMobile());
		return this;
	}
	
}
