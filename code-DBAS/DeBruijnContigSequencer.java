import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class DeBruijnContigSequencer {
    public DeBruijnContigSequencer() {
    }

    public static A C(List<A> var0) {            //static 修饰类方法，调用格式：类名 方法名。不需要new实例化，返回类型是A这个类的实例
        if (var0 != null && !var0.isEmpty()) {
            A var1 = (A)var0.get(0);             //获取var0数据中的序号为0的值
            Iterator var2 = var0.iterator();

            while(var2.hasNext()) {   //找出其A类变量F值最大的那个k-mer
                A var3 = (A)var2.next();
                if (var1.F < var3.F) {
                    var1 = var3;
                }
            }

            return var1;
        } else {
            return null;
        }
    }

    public static A A(List<A> var0) {
        if (var0 != null && !var0.isEmpty()) {
            A var1 = (A)var0.get(0);
            Iterator var2 = var0.iterator();

            while(var2.hasNext()) {
                A var3 = (A)var2.next();
                if (var1.F * var1.K < var3.F * var3.K) {
                    var1 = var3;
                }
            }

            return var1;
        } else {
            return null;
        }
    }

    public static A D(List<A> var0) {
        if (var0 != null && !var0.isEmpty()) {
            A var1 = (A)var0.get(0);
            Iterator var2 = var0.iterator();

            while(var2.hasNext()) {
                A var3 = (A)var2.next();
                if (var1.F * var1.E < var3.F * var3.E) {
                    var1 = var3;
                }
            }

            return var1;
        } else {
            return null;
        }
    }

    /*这块是轻链方法要用的---------------------------------------------------------------------------------------------*/
    public static void B(A var0, B_L var1, List<StringBuilder> var2, List<List<Double>> var3, List<List<HashSet<Integer>>> var4) {
        if (var1.I.containsKey(var0)) {
            List var5 = (List)var1.I.get(var0);
            A var6 = A(var5);  //第32行  var1.F * var1.K < var3.F * var3.K 算出I中以var0为键值下，value中F*K最大的值并返回
            int var7 = var2.size() - 1;
            int var8 = ((StringBuilder)var2.get(var7)).length();
            ((StringBuilder)var2.get(var7)).append(var6.D.charAt(var6.D.length() - 1));  //对var2进行后补充
            ((List)var3.get(var7)).add(var6.F);
            ((List)var4.get(var7)).add(var6.A.getClass().cast(var6.A.clone()));
            int var9 = ((List)var1.I.get(var0)).size();
            List var10 = (List)var1.I.get(var0);
            Iterator var11 = var10.iterator();

            while(var11.hasNext()) {
                if (var11.next() == var6) {
                    var11.remove();  //这里对应的var1.I.get(var0)也删除了
                }
            }

            var0.J -= var9 - ((List)var1.I.get(var0)).size();
            if (var0.J == 0) {
                var1.I.remove(var0);  //删除var0
            }

            var0.F -= (Double)var0.H.get(var6);
            var0.H.remove(var6);
            var9 = ((List)var1.E.get(var6)).size();  //接下来对var1.I进行操作
            List var12 = (List)var1.E.get(var6);
            var11 = var12.iterator();

            while(var11.hasNext()) {
                if (var11.next() == var0) {
                    var11.remove();
                }
            }

            var6.I -= var9 - ((List)var1.E.get(var6)).size();
            if (var6.I == 0) {
                var1.E.remove(var6);
            }

            var6.F -= (Double)var6.C.get(var0);
            var6.C.remove(var0);
            B(var6, var1, var2, var3, var4);  //第68行自循环
        }
    }

    /*这块是重链方法要用的*/
    public static void B(A var0, B_H var1, List<StringBuilder> var2, List<List<Double>> var3, List<List<HashSet<Integer>>> var4) {
        if (var1.I.containsKey(var0)) {
            List var5 = (List)var1.I.get(var0);
            A var6 = A(var5);
            int var7 = var2.size() - 1;
            int var8 = ((StringBuilder)var2.get(var7)).length();
            ((StringBuilder)var2.get(var7)).append(var6.D.charAt(var6.D.length() - 1));
            ((List)var3.get(var7)).add(var6.F);
            ((List)var4.get(var7)).add(var6.A.getClass().cast(var6.A.clone()));
            int var9 = ((List)var1.I.get(var0)).size();
            List var10 = (List)var1.I.get(var0);
            Iterator var11 = var10.iterator();

            while(var11.hasNext()) {
                if (var11.next() == var6) {
                    var11.remove();
                }
            }

            var0.J -= var9 - ((List)var1.I.get(var0)).size();
            if (var0.J == 0) {
                var1.I.remove(var0);
            }

            var0.F -= (Double)var0.H.get(var6);
            var0.H.remove(var6);
            var9 = ((List)var1.E.get(var6)).size();
            List var12 = (List)var1.E.get(var6);
            var11 = var12.iterator();

            while(var11.hasNext()) {
                if (var11.next() == var0) {
                    var11.remove();
                }
            }

            var6.I -= var9 - ((List)var1.E.get(var6)).size();
            if (var6.I == 0) {
                var1.E.remove(var6);
            }

            var6.F -= (Double)var6.C.get(var0);
            var6.C.remove(var0);
            B(var6, var1, var2, var3, var4);
        }
    }

    /*这块是轻链方法要用的*/
    public static void A(A var0, B_L var1, List<StringBuilder> var2, List<List<Double>> var3, List<List<HashSet<Integer>>> var4) {
        if (var1.E.containsKey(var0)) {
            List var5 = (List)var1.E.get(var0);
            A var6 = D(var5); //第50行主程序  var1.F * var1.K < var3.F * var3.K 算出I中以var0为键值下，value中F*K最大的值并返回
            var6.E = 0.0D;
            int var7 = var2.size() - 1;
            ((StringBuilder)var2.get(var7)).insert(0, var6.D.charAt(0));
            ((List)var3.get(var7)).add(0, var6.F);
            ((List)var4.get(var7)).add(0, var6.A.getClass().cast(var6.A.clone()));
            int var8 = ((List)var1.E.get(var0)).size();
            List var9 = (List)var1.E.get(var0);
            Iterator var10 = var9.iterator();

            while(var10.hasNext()) {
                if (var10.next() == var6) {
                    var10.remove();
                }
            }

            var0.I -= var8 - ((List)var1.E.get(var0)).size();
            if (var0.I == 0) {
                var1.E.remove(var0);
            }

            var0.F -= (Double)var0.C.get(var6);
            var0.C.remove(var6);
            var8 = ((List)var1.I.get(var6)).size();
            List var11 = (List)var1.I.get(var6);
            var10 = var11.iterator();

            while(var10.hasNext()) {
                if (var10.next() == var0) {
                    var10.remove();
                }
            }

            var6.J -= var8 - ((List)var1.I.get(var6)).size();
            if (var6.J == 0) {
                var1.I.remove(var6);
            }

            var6.F -= (Double)var6.H.get(var0);
            var6.H.remove(var0);
            A(var6, var1, var2, var3, var4);//第115行
        }
    }

    /*这块是重链所需要的方法*/
    public static void A(A var0, B_H var1, List<StringBuilder> var2, List<List<Double>> var3, List<List<HashSet<Integer>>> var4) {
        if (var1.E.containsKey(var0)) {
            List var5 = (List)var1.E.get(var0);
            A var6 = D(var5); //第50行主程序  var1.F * var1.K < var3.F * var3.K 算出I中以var0为键值下，value中F*K最大的值并返回
            var6.E = 0.0D;
            int var7 = var2.size() - 1;
            ((StringBuilder)var2.get(var7)).insert(0, var6.D.charAt(0));
            ((List)var3.get(var7)).add(0, var6.F);
            ((List)var4.get(var7)).add(0, var6.A.getClass().cast(var6.A.clone()));
            int var8 = ((List)var1.E.get(var0)).size();
            List var9 = (List)var1.E.get(var0);
            Iterator var10 = var9.iterator();

            while(var10.hasNext()) {
                if (var10.next() == var6) {
                    var10.remove();
                }
            }

            var0.I -= var8 - ((List)var1.E.get(var0)).size();
            if (var0.I == 0) {
                var1.E.remove(var0);
            }

            var0.F -= (Double)var0.C.get(var6);
            var0.C.remove(var6);
            var8 = ((List)var1.I.get(var6)).size();
            List var11 = (List)var1.I.get(var6);
            var10 = var11.iterator();

            while(var10.hasNext()) {
                if (var10.next() == var0) {
                    var10.remove();
                }
            }

            var6.J -= var8 - ((List)var1.I.get(var6)).size();
            if (var6.J == 0) {
                var1.I.remove(var6);
            }

            var6.F -= (Double)var6.H.get(var0);
            var6.H.remove(var0);
            A(var6, var1, var2, var3, var4);//第115行
        }
    }

    public static void A_L(List<String> var0, List<float[]> var1, List<Float> var2, int var3, double[] var4, int var5, int var6, List<StringBuilder> var7, List<List<Double>> var8, List<List<HashSet<Integer>>> var9) throws IOException {
        B_L var10 = new B_L(var0, var1, var2, var3, var4, var5); //跳转B类第21行，构建德布鲁因图
        System.out.println("DeBruijnGraph_L constructed");
        System.out.println("Number of nodes = " + var10.A());  //this.A.size()
        System.out.println("Number of edges = " + var10.E());  //this.I.get(var3).size()之和
        System.out.println();
        Iterator var11 = var10.A.values().iterator();

        while(true) {
            A var12;
            double var13;
            Iterator var15;
            A var25;
            do {
                if (!var11.hasNext()) {  //如果var11为空
                    HashMap var19 = new HashMap();
                    int var20 = 0;
                    int var21 = 0;

                    A var23;
                    for(Iterator var14 = var10.A.values().iterator(); var14.hasNext(); var21 = Math.max(var21, (Integer)var19.get(var23))) {
                        var23 = (A)var14.next();
                        var19.put(var23, var23.I + var23.J);
                        var20 += (Integer)var19.get(var23);
                    }

                    System.out.println("node_degree_sum = " + var20); //节点度总数量
                    System.out.println("node_degree_max = " + var21); //节点度的最大值
                    System.out.println();

                    while(var10.I.size() > 0 && var7.size() < var6) {  //var6=10  十条数据或者是输入的第三个值  这个循环为序列拼接
                        int var22 = var7.size();
                        var7.add(new StringBuilder());
                        var8.add(new ArrayList());
                        var9.add(new ArrayList());
                        ArrayList var24 = new ArrayList();
                        Iterator var26 = var10.A.values().iterator();

                        while(var26.hasNext()) {
                            A var17 = (A)var26.next();
                            var24.add(var17);  //以数组形式存储var10.A.values()
                        }

                        var25 = C(var24);  //第15行 在所有的k-mer中找出F值最大的，根据F值进行判别
                        ((StringBuilder)var7.get(var22)).append(var25.D);

                        for(int var27 = 0; var27 < var3 - 1; ++var27) {
                            ((List)var8.get(var22)).add(var25.F); //var7存储A类F值最大的k-mer名，var8存储相应的值，var9存储这个k-mer在哪一个肽段里
                            ((List)var9.get(var22)).add((HashSet)var25.A.clone());
                        }

                        A var18;
                        for(Iterator var28 = var10.A.values().iterator(); var28.hasNext(); var18.K = 1.0D) {
                            var18 = (A)var28.next();
                            var18.E = 1.0D;
                        }

                        B(var25, var10, var7, var8, var9);  //第68行 var25为A类F值最大的k-mer 删除I中与MMPVT与E中MPVTKK
                        A(var25, var10, var7, var8, var9);  //第115行
                    }

                    return;
                }

                var12 = (A)var11.next();
                var13 = 0.0D;

                Double var16;
                for(var15 = var12.H.values().iterator(); var15.hasNext(); var13 += var16) {
                    var16 = (Double)var15.next();
                }

                for(var15 = var12.C.values().iterator(); var15.hasNext(); var13 += var16) {
                    var16 = (Double)var15.next();
                }
            } while(Math.abs(var13 - var12.F) <= 1.0D); //验证每一个var10.A.values()中C与H相加是否与F相差为1.0D

            System.out.println("ERROR: node and edge weights not matched!");
            System.out.println("Node = " + var12.D + ", weight = " + var12.F);
            System.out.println("weight_sum = " + var13);
            System.out.println("Forward neighbors:");
            var15 = var12.H.keySet().iterator();

            while(var15.hasNext()) {
                var25 = (A)var15.next();
                System.out.println("Node = " + var25.D + ", weight = " + var12.H.get(var25));
            }

            System.out.println("Backward neighbors:");
            var15 = var12.C.keySet().iterator();

            while(var15.hasNext()) {
                var25 = (A)var15.next();
                System.out.println("Node = " + var25.D + ", weight = " + var12.C.get(var25));
            }

            System.exit(1);
        }
    }

    public static void A_H(List<String> var0, List<float[]> var1, List<Float> var2, int var3, double[] var4, int var5, int var6, List<StringBuilder> var7, List<List<Double>> var8, List<List<HashSet<Integer>>> var9) throws IOException {
        B_H var10 = new B_H(var0, var1, var2, var3, var4, var5); //跳转B类第21行，构建德布鲁因图
        System.out.println("DeBruijnGraph_H constructed");
        System.out.println("Number of nodes = " + var10.A());  //this.A.size()
        System.out.println("Number of edges = " + var10.E());  //this.I.get(var3).size()之和
        System.out.println();
        Iterator var11 = var10.A.values().iterator();

        while(true) {
            A var12;
            double var13;
            Iterator var15;
            A var25;
            do {
                if (!var11.hasNext()) {  //如果var11为空
                    HashMap var19 = new HashMap();
                    int var20 = 0;
                    int var21 = 0;

                    A var23;
                    for(Iterator var14 = var10.A.values().iterator(); var14.hasNext(); var21 = Math.max(var21, (Integer)var19.get(var23))) {
                        var23 = (A)var14.next();
                        var19.put(var23, var23.I + var23.J);
                        var20 += (Integer)var19.get(var23);
                    }

                    System.out.println("node_degree_sum = " + var20); //节点度总数量
                    System.out.println("node_degree_max = " + var21); //节点度的最大值
                    System.out.println();

                    while(var10.I.size() > 0 && var7.size() < var6) {  //var6=10  十条数据或者是输入的第三个值  这个循环为序列拼接
                        int var22 = var7.size();
                        var7.add(new StringBuilder());
                        var8.add(new ArrayList());
                        var9.add(new ArrayList());
                        ArrayList var24 = new ArrayList();
                        Iterator var26 = var10.A.values().iterator();

                        while(var26.hasNext()) {
                            A var17 = (A)var26.next();
                            var24.add(var17);  //以数组形式存储var10.A.values()
                        }

                        var25 = C(var24);  //第15行 在所有的k-mer中找出F值最大的，根据F值进行判别
                        ((StringBuilder)var7.get(var22)).append(var25.D);

                        for(int var27 = 0; var27 < var3 - 1; ++var27) {
                            ((List)var8.get(var22)).add(var25.F); //var7存储A类F值最大的k-mer名，var8存储相应的值，var9存储这个k-mer在哪一个肽段里
                            ((List)var9.get(var22)).add((HashSet)var25.A.clone());
                        }

                        A var18;
                        for(Iterator var28 = var10.A.values().iterator(); var28.hasNext(); var18.K = 1.0D) {
                            var18 = (A)var28.next();
                            var18.E = 1.0D;
                        }

                        B(var25, var10, var7, var8, var9);  //第68行 var25为A类F值最大的k-mer 删除I中与MMPVT与E中MPVTKK
                        A(var25, var10, var7, var8, var9);  //第115行
                    }

                    return;
                }

                var12 = (A)var11.next();
                var13 = 0.0D;

                Double var16;
                for(var15 = var12.H.values().iterator(); var15.hasNext(); var13 += var16) {
                    var16 = (Double)var15.next();
                }

                for(var15 = var12.C.values().iterator(); var15.hasNext(); var13 += var16) {
                    var16 = (Double)var15.next();
                }
            } while(Math.abs(var13 - var12.F) <= 1.0D); //验证每一个var10.A.values()中C与H相加是否与F相差为1.0D

            System.out.println("ERROR: node and edge weights not matched!");
            System.out.println("Node = " + var12.D + ", weight = " + var12.F);
            System.out.println("weight_sum = " + var13);
            System.out.println("Forward neighbors:");
            var15 = var12.H.keySet().iterator();

            while(var15.hasNext()) {
                var25 = (A)var15.next();
                System.out.println("Node = " + var25.D + ", weight = " + var12.H.get(var25));
            }

            System.out.println("Backward neighbors:");
            var15 = var12.C.keySet().iterator();

            while(var15.hasNext()) {
                var25 = (A)var15.next();
                System.out.println("Node = " + var25.D + ", weight = " + var12.C.get(var25));
            }

            System.exit(1);
        }
    }

    /*这块是不区分轻重链方法要用的------------------------------------------------------------------------------------*/
    public static void B(A var0, B var1, List<StringBuilder> var2, List<List<Double>> var3, List<List<HashSet<Integer>>> var4) {
        if (var1.I.containsKey(var0)) {
            List var5 = (List)var1.I.get(var0);
            A var6 = A(var5);  //第32行  var1.F * var1.K < var3.F * var3.K 算出I中以var0为键值下，value中F*K最大的值并返回
            int var7 = var2.size() - 1;
            int var8 = ((StringBuilder)var2.get(var7)).length();
            ((StringBuilder)var2.get(var7)).append(var6.D.charAt(var6.D.length() - 1));  //对var2进行后补充
            ((List)var3.get(var7)).add(var6.F);
            ((List)var4.get(var7)).add(var6.A.getClass().cast(var6.A.clone()));
            int var9 = ((List)var1.I.get(var0)).size();
            List var10 = (List)var1.I.get(var0);
            Iterator var11 = var10.iterator();

            while(var11.hasNext()) {
                if (var11.next() == var6) {
                    var11.remove();  //这里对应的var1.I.get(var0)也删除了
                }
            }

            var0.J -= var9 - ((List)var1.I.get(var0)).size();
            if (var0.J == 0) {
                var1.I.remove(var0);  //删除var0
            }

            var0.F -= (Double)var0.H.get(var6);
            var0.H.remove(var6);
            var9 = ((List)var1.E.get(var6)).size();  //接下来对var1.I进行操作
            List var12 = (List)var1.E.get(var6);
            var11 = var12.iterator();

            while(var11.hasNext()) {
                if (var11.next() == var0) {
                    var11.remove();
                }
            }

            var6.I -= var9 - ((List)var1.E.get(var6)).size();
            if (var6.I == 0) {
                var1.E.remove(var6);
            }

            var6.F -= (Double)var6.C.get(var0);
            var6.C.remove(var0);
            B(var6, var1, var2, var3, var4);  //第68行自循环
        }
    }

    public static void A(A var0, B var1, List<StringBuilder> var2, List<List<Double>> var3, List<List<HashSet<Integer>>> var4) {
        if (var1.E.containsKey(var0)) {
            List var5 = (List)var1.E.get(var0);
            A var6 = D(var5); //第50行主程序  var1.F * var1.K < var3.F * var3.K 算出I中以var0为键值下，value中F*K最大的值并返回
            var6.E = 0.0D;
            int var7 = var2.size() - 1;
            ((StringBuilder)var2.get(var7)).insert(0, var6.D.charAt(0));
            ((List)var3.get(var7)).add(0, var6.F);
            ((List)var4.get(var7)).add(0, var6.A.getClass().cast(var6.A.clone()));
            int var8 = ((List)var1.E.get(var0)).size();
            List var9 = (List)var1.E.get(var0);
            Iterator var10 = var9.iterator();

            while(var10.hasNext()) {
                if (var10.next() == var6) {
                    var10.remove();
                }
            }

            var0.I -= var8 - ((List)var1.E.get(var0)).size();
            if (var0.I == 0) {
                var1.E.remove(var0);
            }

            var0.F -= (Double)var0.C.get(var6);
            var0.C.remove(var6);
            var8 = ((List)var1.I.get(var6)).size();
            List var11 = (List)var1.I.get(var6);
            var10 = var11.iterator();

            while(var10.hasNext()) {
                if (var10.next() == var0) {
                    var10.remove();
                }
            }

            var6.J -= var8 - ((List)var1.I.get(var6)).size();
            if (var6.J == 0) {
                var1.I.remove(var6);
            }

            var6.F -= (Double)var6.H.get(var0);
            var6.H.remove(var0);
            A(var6, var1, var2, var3, var4);//第115行
        }
    }

    public static void A(List<String> var0, List<float[]> var1, List<Float> var2, int var3, double[] var4, int var5, int var6, List<StringBuilder> var7, List<List<Double>> var8, List<List<HashSet<Integer>>> var9) throws IOException {
        B var10 = new B(var0, var1, var2, var3, var4, var5); //跳转B类第21行，构建德布鲁因图
        System.out.println("DeBruijnGraph constructed");
        System.out.println("Number of nodes = " + var10.A());  //this.A.size()
        System.out.println("Number of edges = " + var10.E());  //this.I.get(var3).size()之和
        System.out.println();
        Iterator var11 = var10.A.values().iterator();

        while(true) {
            A var12;
            double var13;
            Iterator var15;
            A var25;
            do {
                if (!var11.hasNext()) {  //如果var11为空
                    HashMap var19 = new HashMap();
                    int var20 = 0;
                    int var21 = 0;

                    A var23;
                    for(Iterator var14 = var10.A.values().iterator(); var14.hasNext(); var21 = Math.max(var21, (Integer)var19.get(var23))) {
                        var23 = (A)var14.next();
                        var19.put(var23, var23.I + var23.J);
                        var20 += (Integer)var19.get(var23);
                    }

                    System.out.println("node_degree_sum = " + var20); //节点度总数量
                    System.out.println("node_degree_max = " + var21); //节点度的最大值
                    System.out.println();

                    while(var10.I.size() > 0 && var7.size() < var6) {  //var6=10  十条数据或者是输入的第三个值  这个循环为序列拼接
                        int var22 = var7.size();
                        var7.add(new StringBuilder());
                        var8.add(new ArrayList());
                        var9.add(new ArrayList());
                        ArrayList var24 = new ArrayList();
                        Iterator var26 = var10.A.values().iterator();

                        while(var26.hasNext()) {
                            A var17 = (A)var26.next();
                            var24.add(var17);  //以数组形式存储var10.A.values()
                        }

                        var25 = C(var24);  //第15行 在所有的k-mer中找出F值最大的，根据F值进行判别
                        ((StringBuilder)var7.get(var22)).append(var25.D);

                        for(int var27 = 0; var27 < var3 - 1; ++var27) {
                            ((List)var8.get(var22)).add(var25.F); //var7存储A类F值最大的k-mer名，var8存储相应的值，var9存储这个k-mer在哪一个肽段里
                            ((List)var9.get(var22)).add((HashSet)var25.A.clone());
                        }

                        A var18;
                        for(Iterator var28 = var10.A.values().iterator(); var28.hasNext(); var18.K = 1.0D) {
                            var18 = (A)var28.next();
                            var18.E = 1.0D;
                        }

                        B(var25, var10, var7, var8, var9);  //第68行 var25为A类F值最大的k-mer 删除I中与MMPVT与E中MPVTKK
                        A(var25, var10, var7, var8, var9);  //第115行
                    }

                    return;
                }

                var12 = (A)var11.next();
                var13 = 0.0D;

                Double var16;
                for(var15 = var12.H.values().iterator(); var15.hasNext(); var13 += var16) {
                    var16 = (Double)var15.next();
                }

                for(var15 = var12.C.values().iterator(); var15.hasNext(); var13 += var16) {
                    var16 = (Double)var15.next();
                }
            } while(Math.abs(var13 - var12.F) <= 1.0D); //验证每一个var10.A.values()中C与H相加是否与F相差为1.0D

            System.out.println("ERROR: node and edge weights not matched!");
            System.out.println("Node = " + var12.D + ", weight = " + var12.F);
            System.out.println("weight_sum = " + var13);
            System.out.println("Forward neighbors:");
            var15 = var12.H.keySet().iterator();

            while(var15.hasNext()) {
                var25 = (A)var15.next();
                System.out.println("Node = " + var25.D + ", weight = " + var12.H.get(var25));
            }

            System.out.println("Backward neighbors:");
            var15 = var12.C.keySet().iterator();

            while(var15.hasNext()) {
                var25 = (A)var15.next();
                System.out.println("Node = " + var25.D + ", weight = " + var12.C.get(var25));
            }

            System.exit(1);
        }
    }

    //对肽序列进行处理，计算文件中所有肽的数量，这些肽的长度之和---------------------------------------------------------------------
    public static void A(String var0, int var1, int var2, int var3, List<String> var4, List<float[]> var5, List<Float> var6) throws IOException {
        int var7 = 0;
        int var8 = 0;
        BufferedReader var9 = new BufferedReader(new FileReader(var0));
        //A(文件地址, 1, 2, 3, 空String数组, 空float数组, 空float数组)
        String var12;
        for(String var10 = var9.readLine(); (var10 = var9.readLine()) != null; var8 += var12.length()) {
            String[] var11 = var10.split(",");
            var12 = A(var11[var1]);  //跳转到306行左右的方法，这里var1=1，即var11[var1]为数组中的第二个，即ecxel中的第二列
            var12 = var12.replaceAll("I", "L");  //将所有I替换成L
            var4.add(var12);
            String var13 = var11[var2];  //获取置信度
            String[] var14 = var13.split(" ");
            if (var12.length() != var14.length) {
                System.out.println("Error: peptide & quality length not matched");
                System.out.println(var12);
                System.exit(1);
            }

            float[] var15 = new float[var14.length];

            for(int var16 = 0; var16 < var15.length; ++var16) {
                var15[var16] = Float.parseFloat(var14[var16]);  //以float型存储置信度到数组中
            }

            var5.add(var15);
            String var18 = var11[var3];  //获取area值
            if (var18.isEmpty()) {
                var18 = "1";
            }

            Float var17 = Float.valueOf(var18);
            var6.add(var17);
            ++var7;
        }

        var9.close();
        System.out.println();
        System.out.println("Input file: " + var0);
        System.out.println("peptideCount = " + var7);
        System.out.println("peptideLenSum = " + var8);
        System.out.println();
    }

    //将肽序列中（、）等非字母进行剔除
    private static String A(String var0) {
        StringBuilder var1 = new StringBuilder();
        boolean var2 = true;

        for(int var3 = 0; var3 < var0.length(); ++var3) {
            if (var0.charAt(var3) == '(') {
                var2 = false;
            } else if (var0.charAt(var3) == ')') {
                var2 = true;
            } else if (var2) {
                var1.append(var0.charAt(var3));
            }
        }

        return var1.toString();
    }

    public static void A() {
        System.out.println("Usage:");
        System.out.println("\tALPS <input_file> <k> [<c>]");
        System.out.println("\t\t- input_file: .csv files containing input data;");
        System.out.println("\t\t- k         : the length of k-mers, 6 or 7 is recommended.");
        System.out.println("\t\t- c         : the number of top contigs to use for assembly, default is 10.");
        System.exit(1);
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner=new Scanner(System.in);
        System.out.println("输入的参数个数为：");
        int num=scanner.nextInt();
        if (num == 3) {
            System.out.println("请输入文件地址、k值、测试文件情况：");
        }else if (num == 4) {
            System.out.println("请输入文件地址、k值、测试文件情况(L/H)和输出个数：");
        }else{
            System.out.println("输入的参数个数错误！");
            A();
        }
        String[] var0=new String[num];
        for (int i = 0; i < var0.length; i++) {
            var0[i]=scanner.next();
        }
        System.out.println("输入参数成功！");
        //C:\Users\Admin\Desktop\test-file\peptides_0.csv
        //C:\Users\Admin\Desktop\test-file\peptides_short-cs.csv
        //C:\Users\木子\Desktop\ALPS\test-file\peptides_0.csv

        if (var0.length < 2) {
            A();
        }

        //从这里开始进行判断，是混合了轻重链的文件
        String Judge_csv = var0[2];
        if(Judge_csv.equals("lh")) {
            String var1 = var0[0];
            int var2 = 0;

            try {
                var2 = Integer.parseInt(var0[1]);
            } catch (NumberFormatException var27) {
                A();
            } catch (NullPointerException var28) {
                A();
            }

            byte var3 = 1;
            byte var4 = 2;
            byte var5 = 3;
            ArrayList var6 = new ArrayList();
            ArrayList var7 = new ArrayList();
            ArrayList var8 = new ArrayList();

            try {
                //第263行，执行后，var6存放肽序列，var7存放肽对应的置信度，var8存放特征区域
                A(var1, var3, var4, var5, var6, var7, var8);
            } catch (IOException var26) {
                var26.printStackTrace();
            }

            int var9 = var2 - 1;
            double[] var10 = new double[var2];
            Arrays.fill(var10, 1.0D);
            var10[0] = (double) var9;
            var10[var2 - 1] = (double) var9;
            byte var11 = 0;
            int var12 = 10;
            if (var0.length > 3) {
                try {
                    var12 = Integer.parseInt(var0[3]);
                } catch (NumberFormatException var24) {
                    A();
                } catch (NullPointerException var25) {
                    A();
                }
            }

            System.out.println("-------------------------------------------");
            ArrayList var13 = new ArrayList();
            ArrayList var14 = new ArrayList();
            ArrayList var15 = new ArrayList();
            A_L(var6, var7, var8, var2, var10, var11, var12, var13, var14, var15);  //第162行  蒋切下来的k-mer再重新组装？
            B(var14);  //第473行 将var14里存储的数据进行处理，都除以最大值
            A(var13, var14, var15);  //第437行   肽段、对应数据，对应计数
            int var16 = var13.size();
            System.out.println("Number of assembled sequences = " + var16);
            System.out.println();
            File var17 = new File(var1 + ".blast5-0616_L-12.k" + var2 + ".fasta"); //创建.fasta文件
            BufferedWriter var18 = new BufferedWriter(new FileWriter(var17));
            int var19 = 0;

            //往.fasta文件里面写入肽段，此时的var13已经整合完毕？
            System.out.println("轻链组装结果如下：");
            for (int var20 = 0; var20 < var16; ++var20) {
                var18.write(">seq" + var20 + "\n");
                var18.write(((StringBuilder) var13.get(var20)).toString() + "\n");
                var19 += ((StringBuilder) var13.get(var20)).length();
                System.out.println("seq" + var20 + ", length = " + ((StringBuilder) var13.get(var20)).length() + ", lengthSum = " + var19);
            }

            var18.close();
            File var29 = new File(var1 + ".blast5-0616_L-12.k" + var2 + ".csv");  //创建.k值.csv文件
            var18 = new BufferedWriter(new FileWriter(var29));
            Iterator var21 = var14.iterator();

            while (var21.hasNext()) {
                List var22 = (List) var21.next();

                for (int var23 = 0; var23 < var22.size(); ++var23) {
                    var18.write(String.format("%.4f,", (Double) var22.get(var23)));
                }

                var18.write("\n");
            }

            var18.close();
            System.out.println();

            /*对重链进行处理
             * 这里是复制上面的轻链代码进行改造
             * */
            System.out.println("-------------------------------------------");
            ArrayList var13_H = new ArrayList();
            ArrayList var14_H = new ArrayList();
            ArrayList var15_H = new ArrayList();
            A_H(var6, var7, var8, var2, var10, var11, var12, var13_H, var14_H, var15_H);  //第162行  蒋切下来的k-mer再重新组装？
            B(var14_H);  //第473行 将var14里存储的数据进行处理，都除以最大值
            A(var13_H, var14_H, var15_H);  //第437行   肽段、对应数据，对应计数
            int var16_H = var13_H.size();
            System.out.println("Number of assembled sequences = " + var16_H);
            System.out.println();
            File var17_H = new File(var1 + ".blast5-0616_H-12.k" + var2 + ".fasta"); //创建.fasta文件
            BufferedWriter var18_H = new BufferedWriter(new FileWriter(var17_H));
            int var19_H = 0;

            System.out.println("重链组装结果如下：");
            for (int var20 = 0; var20 < var16_H; ++var20) {
                var18_H.write(">seq" + var20 + "\n");
                var18_H.write(((StringBuilder) var13_H.get(var20)).toString() + "\n");
                var19_H += ((StringBuilder) var13_H.get(var20)).length();
                System.out.println("seq" + var20 + ", length = " + ((StringBuilder) var13_H.get(var20)).length() + ", lengthSum = " + var19_H);
            }

            var18_H.close();
            File var29_H = new File(var1 + ".blast5-0616_H-12.k" + var2 + ".csv");  //创建.k值.csv文件
            var18_H = new BufferedWriter(new FileWriter(var29_H));
            Iterator var21_H = var14_H.iterator();

            while (var21_H.hasNext()) {
                List var22 = (List) var21_H.next();

                for (int var23 = 0; var23 < var22.size(); ++var23) {
                    var18_H.write(String.format("%.4f,", (Double) var22.get(var23)));
                }

                var18_H.write("\n");
            }

            var18_H.close();
        }

        //判断为已经区分轻重链的文件
        if(Judge_csv.equals("l") || Judge_csv.equals("h")){
            String var1 = var0[0];
            int var2 = 0;

            try {
                var2 = Integer.parseInt(var0[1]);
            } catch (NumberFormatException var27) {
                A();
            } catch (NullPointerException var28) {
                A();
            }

            byte var3 = 1;
            byte var4 = 2;
            byte var5 = 3;
            ArrayList var6 = new ArrayList();
            ArrayList var7 = new ArrayList();
            ArrayList var8 = new ArrayList();

            try {
                //第263行，执行后，var6存放肽序列，var7存放肽对应的置信度，var8存放特征区域
                A(var1, var3, var4, var5, var6, var7, var8);
            } catch (IOException var26) {
                var26.printStackTrace();
            }

            int var9 = var2 - 1;
            double[] var10 = new double[var2];
            Arrays.fill(var10, 1.0D);
            var10[0] = (double) var9;
            var10[var2 - 1] = (double) var9;
            byte var11 = 0;
            int var12 = 10;
            if (var0.length > 3) {
                try {
                    var12 = Integer.parseInt(var0[3]);
                } catch (NumberFormatException var24) {
                    A();
                } catch (NullPointerException var25) {
                    A();
                }
            }

            System.out.println("-------------------------------------------");
            ArrayList var13 = new ArrayList();
            ArrayList var14 = new ArrayList();
            ArrayList var15 = new ArrayList();
            A(var6, var7, var8, var2, var10, var11, var12, var13, var14, var15);  //第162行  蒋切下来的k-mer再重新组装？
            B(var14);  //第473行 将var14里存储的数据进行处理，都除以最大值
            A(var13, var14, var15);  //第437行   肽段、对应数据，对应计数
            int var16 = var13.size();
            System.out.println("Number of assembled sequences = " + var16);
            System.out.println();
            File var17 = new File(var1 + ".blast0602_yz2.k" + var2 + ".fasta"); //创建.fasta文件
            BufferedWriter var18 = new BufferedWriter(new FileWriter(var17));
            int var19 = 0;

            //往.fasta文件里面写入肽段，此时的var13已经整合完毕？
            System.out.println("组装结果如下：");
            for (int var20 = 0; var20 < var16; ++var20) {
                var18.write(">seq" + var20 + "\n");
                var18.write(((StringBuilder) var13.get(var20)).toString() + "\n");
                var19 += ((StringBuilder) var13.get(var20)).length();
                System.out.println("seq" + var20 + ", length = " + ((StringBuilder) var13.get(var20)).length() + ", lengthSum = " + var19);
            }

            var18.close();
            File var29 = new File(var1 + ".blast0602_yz2.k" + var2 + ".csv");  //创建.k值.csv文件
            var18 = new BufferedWriter(new FileWriter(var29));
            Iterator var21 = var14.iterator();

            while (var21.hasNext()) {
                List var22 = (List) var21.next();

                for (int var23 = 0; var23 < var22.size(); ++var23) {
                    var18.write(String.format("%.4f,", (Double) var22.get(var23)));
                }

                var18.write("\n");
            }

            var18.close();
            System.out.println();
        }
    }

    public static void A(List<StringBuilder> var0, List<List<Double>> var1, List<List<HashSet<Integer>>> var2) {
        byte var3 = 5;
        double var4 = 0.1D;
        double var6 = 0.1D;
        int var8 = var1.size();

        for(int var9 = 0; var9 < var8; ++var9) {
            int var10 = 0;

            int var11;  //Math.round()返回最接近它的整数，若有两个返回接近的整数，则取最大的那个
            for(var11 = 0; var11 < Math.round((float)((double)((StringBuilder)var0.get(var9)).length() * var4)) && (Double)((List)var1.get(var9)).get(var11) < var6; ++var11) {
                if ((Double)((List)var1.get(var9)).get(var11 + 1) > (double)var3 * (Double)((List)var1.get(var9)).get(var11)) {
                    var10 = var11 + 1;
                    break;
                }
            }

            int var12 = ((StringBuilder)var0.get(var9)).length() - 1;

             for(var11 = ((StringBuilder)var0.get(var9)).length() - 1; var11 > Math.round((float)((double)((StringBuilder)var0.get(var9)).length() * (1.0D - var4))) && (Double)((List)var1.get(var9)).get(var11) < var6; --var11) {
                if ((Double)((List)var1.get(var9)).get(var11 - 1) > (double)var3 * (Double)((List)var1.get(var9)).get(var11)) {
                    var12 = var11 - 1;
                    break;
                }
            }

            ((StringBuilder)var0.get(var9)).delete(var12 + 1, ((StringBuilder)var0.get(var9)).length());
            ((StringBuilder)var0.get(var9)).delete(0, var10);
            var1.add(var9 + 1, ((List)var1.get(var9)).subList(var10, var12 + 1));
            var1.remove(var9);
            var2.add(var9 + 1, ((List)var2.get(var9)).subList(var10, var12 + 1));
            var2.remove(var9);
        }

    }

    public static void B(List<List<Double>> var0) {
        double var1 = 0.0D;
        Iterator var3 = ((List)var0.get(0)).iterator();

        while(var3.hasNext()) {
            Double var4 = (Double)var3.next();
            if (var1 < var4) {
                var1 = var4;  //求最大的那一个
            }
        }

        var3 = var0.iterator();

        while(var3.hasNext()) {
            List var6 = (List)var3.next();

            for(int var5 = 0; var5 < var6.size(); ++var5) {
                var6.add(var5 + 1, (Double)var6.get(var5) / var1);  //蒋var6里的所有数据除以var1
                var6.remove(var5);
            }
        }

    }
}
