package pub.caterpillar.app.carpool.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import pub.caterpillar.app.carpool.enumeration.SystemPropertiesName;
import pub.caterpillar.app.carpool.enumeration.SystemPropertiesType;
import pub.caterpillar.app.carpool.po.metadata.Metadata;
import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name = Metadata.PREFIX + "system_properties")
public class SystemPropertiesPO extends AbstractBasePO{

	private static final long serialVersionUID = 1L;

	private SystemPropertiesName name;
	
	private String value;
	
	private SystemPropertiesType type;

	@Column(name = "NAME")
	@Enumerated(EnumType.STRING)
	public SystemPropertiesName getName() {
		return name;
	}

	public void setName(SystemPropertiesName name) {
		this.name = name;
	}

	@Column(name = "VALUE")
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Column(name = "YTPE")
	@Enumerated(EnumType.STRING)
	public SystemPropertiesType getType() {
		return type;
	}

	public void setType(SystemPropertiesType type) {
		this.type = type;
	}
	
}
