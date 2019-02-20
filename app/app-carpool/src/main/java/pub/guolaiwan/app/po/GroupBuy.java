package pub.guolaiwan.app.po;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Table;

import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name = "group_buy")
public class GroupBuy extends AbstractBasePO {

	private boolean initiator;
	private long participantId;
	private long groupSellId;
	
	public boolean isInitiator() {
		return initiator;
	}
	public void setInitiator(boolean initiator) {
		this.initiator = initiator;
	}
	public long getParticipantId() {
		return participantId;
	}
	public void setParticipantId(long participantId) {
		this.participantId = participantId;
	}
	public long getGroupSellId() {
		return groupSellId;
	}
	public void setGroupSellId(long groupSellId) {
		this.groupSellId = groupSellId;
	}
	
}
