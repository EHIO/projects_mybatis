package db;

import com.ffzx.commerce.framework.common.persistence.DataEntity;

/**
 * 
 * @author 
 * @date 
 * @version 1.0
 */
public class SysDict extends DataEntity<SysDict>{

    private static final long serialVersionUID = 1L;
    
    /** 姓名*/
    private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    
    
    
}