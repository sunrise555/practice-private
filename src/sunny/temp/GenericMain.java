package sunny.temp;

public class GenericMain {
	public static void main(String args[]) {
		GenericMain gm = new GenericMain();
		gm.f(1);
		//gm.add(1, 12);

    }

    public static  <T extends Addable<T>> T add(T o1, T o2) {
        return o1.add(o2);
    }
    
    public <T> void f(T x) {
    	System.out.println(x.getClass().getName());
    }
}
