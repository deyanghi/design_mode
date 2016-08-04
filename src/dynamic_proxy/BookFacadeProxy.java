package dynamic_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class BookFacadeProxy implements InvocationHandler{
	
	private Object object;
	
	/** 
     * ��ί�ж��󲢷���һ�������� 
     * @param target 
     * @return 
     */  
    public Object bind(Object target) {  
        this.object = target;  
        //ȡ�ô������
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),  
                target.getClass().getInterfaces(), this); 
    }  
	@Override
	//�����������������ʾ��ȥ����
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		Object result=null;  
        System.out.println("���￪ʼ");  
        //ִ�з���  
        result=method.invoke(object, args);  
        System.out.println("�������");  
        return result; 
	}

	public static void main(String[] args) {
		BookFacadeProxy proxy = new BookFacadeProxy();
		BookFacade bookFacade = (BookFacade) proxy.bind(new BookFacadeImpl());
		bookFacade.addBook();
	}
}
