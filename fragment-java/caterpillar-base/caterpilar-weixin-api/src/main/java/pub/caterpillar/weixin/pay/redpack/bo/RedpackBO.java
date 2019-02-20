package pub.caterpillar.weixin.pay.redpack.bo;
/**
 * @author ldy
 * 红包描述
 */
public class RedpackBO {
	
	//商户名称
	private String send_name;
	
	//红包金额   最多200
	private Integer total_amount;
	
	//红包数量
	private Integer total_num;
	
	//红包祝福语
	private String wishing;
	
	//活动名称
	private String act_name;
	
	//备注信息
	private String remark;

	public String getSend_name() {
		return send_name;
	}

	public RedpackBO setSend_name(String send_name) {
		this.send_name = send_name;
		return this;
	}

	public Integer getTotal_amount() {
		return total_amount;
	}

	public RedpackBO setTotal_amount(Integer total_amount) {
		this.total_amount = total_amount;
		return this;
	}

	public Integer getTotal_num() {
		return total_num;
	}

	public RedpackBO setTotal_num(Integer total_num) {
		this.total_num = total_num;
		return this;
	}

	public String getWishing() {
		return wishing;
	}

	public RedpackBO setWishing(String wishing) {
		this.wishing = wishing;
		return this;
	}

	public String getAct_name() {
		return act_name;
	}

	public RedpackBO setAct_name(String act_name) {
		this.act_name = act_name;
		return this;
	}

	public String getRemark() {
		return remark;
	}

	public RedpackBO setRemark(String remark) {
		this.remark = remark;
		return this;
	}
	
}
