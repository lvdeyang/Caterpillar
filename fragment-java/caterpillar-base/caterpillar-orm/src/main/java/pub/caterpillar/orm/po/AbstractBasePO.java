package pub.caterpillar.orm.po;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import pub.caterpillar.commons.model.AbstractBaseBean;

@MappedSuperclass
public class AbstractBasePO extends AbstractBaseBean implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    /** 表前缀 */
    public static final String PREFIX = "CATERPILLAR_";
    
    /** 主键 */
	private Long id;
	
	/** uuid */
	public String uuid = UUID.randomUUID().toString().replaceAll("-", "");
	
	/** 修改时间 */
	private Date updateTime;
	
	/** 主键 */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id",insertable = true,unique=true,nullable=false)
    public Long getId(){
    	return this.id;
    }

    public void setId(Long id){
    	this.id = id;
    }
  
    /** uuid */
	public String getUuid(){
		return uuid;
	}
	
	public void setUuid(String uuid){
		this.uuid = uuid;
	}
	
	/** 修改时间 */
	@Temporal(TemporalType.TIMESTAMP)
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? super.hashCode() : id.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractBasePO other = (AbstractBasePO) obj;
		if (id != null && other.id != null) {
			return id.longValue() == other.id.longValue();
		} else {
			return super.equals(obj);
		}		
	}
	  
}