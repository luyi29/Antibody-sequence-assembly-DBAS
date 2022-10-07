import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class A {
    public String D = null;
    public int I = 0;
    public int J = 0;
    public double G = 0.0D;
    public float B = 0.0F;
    public double F = 0.0D;
    public double E = 1.0D;
    public double K = 1.0D;
    public Map<A, Double> H = new HashMap();  //HashMap存储键值对的结构，即散列表，A为键对象的类型，Double为值对象的类型
    public Map<A, Double> C = new HashMap();
    HashSet<Integer> A = new HashSet();   //集合，包含数据类型为Integer的值，无序，不重复

    public A(String var1) {   //A实例化时可自动调用
        this.D = var1;
    }

    public boolean B() {
        return Math.abs(this.I - this.J) == 1;
    }

    public boolean A() {
        return this.I == this.J;
    }

    public int C() {
        System.out.println(C);
        return this.hashCode();   //返回字符串的哈希码
    }

    public String toString() {
        return this.D;
    }
}
