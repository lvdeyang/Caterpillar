package com.chenxi.web.yueba.admin.po;

import javax.persistence.Entity;
import javax.persistence.Table;

import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name = "level")
public class LevelPo extends AbstractBasePO{

	private String name;
}
