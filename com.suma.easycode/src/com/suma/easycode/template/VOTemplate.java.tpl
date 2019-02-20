package com.suma.easycode.template;

import com.sumavision.bvc.system.po.AvtplPO;
import com.sumavision.bvc.system.po.DictionaryPO;
import com.sumavision.tetris.commons.util.date.DateUtil;
import com.sumavision.tetris.mvc.converter.AbstractBaseVO;

public class ${voName} extends AbstractBaseVO<${voName}, ${poName}>{
	
	<#list properList as propers>
		   
		    private String ${propers.proName};

			public String get${propers.proMethodName}() {
				return ${propers.proName};
			}
			
			public ${voName} set${propers.proMethodName}(String ${propers.proName}) {
				this.${propers.proName} = ${propers.proName};
			}
	</#list>
	@Override
	public ${voName} set(${poName} entity) throws Exception {
		// TODO Auto-generated method stub
		this<#list properList as propers>
		.set${propers.proMethodName}(entity.get${propers.proMethodName}())
		</#list>;
		return this;
	}
	
}
