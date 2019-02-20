package pub.caterpillar.commons.model;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.BeanUtils;



public abstract class AbstractBaseBean implements Cloneable
{
  @SuppressWarnings("unchecked")
	public <T extends AbstractBaseBean> T simpleClone(){ 
	  	try {
			return (T)super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		} 
		return null;
  	} 
  @SuppressWarnings("unchecked")
public <T extends AbstractBaseBean> T deepClone(Map<Object, Object> cloneMap){ 
	  Object o = null; 
	  try{ 
		  if (cloneMap == null){
			  cloneMap = new HashMap<Object, Object>();
		  }
		  if (cloneMap.containsKey(this)){
			  o = cloneMap.get(this);
			  return (T)o;
		  }else{
			  o = super.clone(); 
//			  if (clearId){
//				  o.setId(null);
//			  }
			  
			  cloneMap.put(this, o);
			  cloneMap.put(o, o);
		  }
		  
		  Class<?> clazz = o.getClass();
	      BeanInfo info = Introspector.getBeanInfo(clazz);
	      //System.out.println("正在克隆"+o.getClass()+"的属性......");
	      PropertyDescriptor[] props = info.getPropertyDescriptors();
          for (PropertyDescriptor prop : props) {
			//String name = prop.getName();
            Method getMethod = prop.getReadMethod();
            Method setMethod = prop.getWriteMethod();
			Object value = getMethod.invoke(o);
			//System.out.println(name+":"+value);
            if (value instanceof AbstractBaseBean){
            	setMethod.invoke(o, ((AbstractBaseBean) value).deepClone(cloneMap));
            }else if (value instanceof Set) {
            	Set<Object> collection  = new HashSet<>();
            	for (Object object:(Set<?>)value){
            		if (object instanceof AbstractBaseBean){
            			AbstractBaseBean cloneBean = ((AbstractBaseBean)object).deepClone(cloneMap);
            			collection.add(cloneBean);
            		}else {
            			collection.add(object);
					}
            	}
            	setMethod.invoke(o, collection);
			}else if (value instanceof List) {
				List<Object> collection  = new LinkedList<>();
            	for (Object object:(List<?>)value){
            		if (object instanceof AbstractBaseBean){
            			AbstractBaseBean cloneBean = ((AbstractBaseBean)object).deepClone(cloneMap);
            			collection.add(cloneBean);
            		}else {
            			collection.add(object);
					}
            	}
            	setMethod.invoke(o, collection);
			}
          }
	  }catch(Exception e){ 
		  e.printStackTrace(); 
	  } 
	  return (T)o; 
  }
  
  protected Method findBaseAccessor(Class<?> clazz, Method accessor) {
      Method baseAccessor = null;
      if (clazz.getName().contains("$$EnhancerByCGLIB$$")) {
          try {
              baseAccessor = Thread.currentThread().getContextClassLoader().loadClass(
                      clazz.getName().substring(0, clazz.getName().indexOf("$$"))).getMethod(
                      accessor.getName(), accessor.getParameterTypes());
          } catch (Exception ex) {
              ex.printStackTrace();
          }
      } else if (clazz.getName().contains("$$_javassist")) {
          try {
              baseAccessor = Class.forName(
                      clazz.getName().substring(0, clazz.getName().indexOf("_$$")))
                      .getMethod(accessor.getName(), accessor.getParameterTypes());
          } catch (Exception ex) {
        	  ex.printStackTrace();
          }
      } else {
          return accessor;
      }
      return baseAccessor;
  }
  
  
  public void transFrom(AbstractBaseBean obj){
	BeanUtils.copyProperties(obj, this);
  }
  
  public void transTo(AbstractBaseBean obj){
	BeanUtils.copyProperties(this, obj);
  }
  
  @SuppressWarnings("unchecked")
public <T> T transTo(Class<?> clazz){
	  try {
		Object o = clazz.newInstance();
		BeanUtils.copyProperties(this, o);
		return (T)o;
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null; 
  }
  
}