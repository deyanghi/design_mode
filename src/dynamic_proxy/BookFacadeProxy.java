package dynamic_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class BookFacadeProxy implements InvocationHandler{
	
	private Object object;
	
	/** 
     * 绑定委托对象并返回一个代理类 
     * @param target 
     * @return 
     */  
    public Object bind(Object target) {  
        this.object = target;  
        //取得代理对象
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),  
                target.getClass().getInterfaces(), this); 
    }  
	@Override
	//这个方法不是我们显示的去调用
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		Object result=null;  
        System.out.println("事物开始");  
        //执行方法  
        result=method.invoke(object, args);  
        System.out.println("事物结束");  
        return result; 
	}

	public static void main(String[] args) {
		BookFacadeProxy proxy = new BookFacadeProxy();
		BookFacade bookFacade = (BookFacade) proxy.bind(new BookFacadeImpl());
		bookFacade.addBook();
	}
}
