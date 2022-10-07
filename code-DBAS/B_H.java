import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class B_H {
    public Map<A, List<A>> I = null;
    public Map<A, List<A>> E = null;
    public Map<String, A> A = null;
    private Map<Integer, A> D = null;
    private A H = null;
    private A F = null;
    private List<Float> G = null;
    private int J = 0;
    private int C = 0;
    private int B = 0;

    public B_H(List<String> var1, List<float[]> var2, List<Float> var3, int var4, double[] var5, int var6) throws IOException {
        this.I = new HashMap();
        this.E = new HashMap();
        this.A = new HashMap();
        this.D = new HashMap();
        this.G = new ArrayList();
        int count = 0;

        //这里是将从blast比对出来的数据存入数组中---新代码，区分轻重链
        String line = null;
        String lines = null;
        String[] linesplit;

        List<Integer> getlist1_H = new ArrayList<>();
        List<String> getlist2_H = new ArrayList<>();
        List<Double> getlist3_H = new ArrayList<>();
        List<Double> linesplitdou_H = new ArrayList<>();

        Integer countline = 0;

        try{
            //String filename1 = "C:/Users/木子/Desktop/ALPS/test-file/peptides_0.csv_cut6.fasta";
            //F:/研究生/ALPS/test-file/测试数据/Human_WIgG1_(3db)-220530/WIgG1_LightHeavy_our/de_novo/WIgG1_denovo_LH.csv_cut6.fasta
            String filename1 = "F:/研究生/ALPS/test-file/测试数据/Human_WIgG1-k5/HumanLH_spider/Human_spider_LH.csv_cut5.fasta";
            BufferedReader readlinek = new BufferedReader(new FileReader(filename1));
            while ((lines = readlinek.readLine()) != null) {
                countline = countline + 1;
            }
            countline = countline / 2;

            //String filename2 = "C:/Users/木子/Desktop/ALPS/test-file/IMGT-Human-Light-Heavyblast6.txt";
            String filename2 = "F:/研究生/ALPS/test-file/测试数据/Human_WIgG1-k5/HumanLH_spider/abysis_IMGT_HumanLH_spider_cut5_blast.txt";
            BufferedReader readtxt = new BufferedReader(new FileReader(filename2));
            while ((line = readtxt.readLine()) != null) {
                linesplit = line.split("\\s");
                String g1 = linesplit[0].substring(3);
                Integer g1_int = Integer.parseInt(g1);
                String g2_LH = linesplit[1];
                String g3 = linesplit[2];
                Double g3_double = Double.parseDouble(g3);

                //判别第二列为L或H，分别放入两组数组
                if (g2_LH.substring(0,1).equals("H")) {
                    getlist1_H.add(g1_int);       //存储seq*后面的*
                    getlist2_H.add(g2_LH);        //存储H*
                    getlist3_H.add(g3_double);   //存储匹配率，如100%
                }
            }
            System.out.println("一共有" + countline + "个长度为6的k-mer切片");
            int getlist1_H_size = getlist1_H.size();
            System.out.println("原来的getlist1_H.size()为"+getlist1_H.get(getlist1_H_size - 1));
            if (getlist1_H.get(getlist1_H_size - 1) != countline - 1) {
                getlist1_H.add(countline - 1);
                getlist2_H.add("add_H_value");
                getlist3_H.add(80.0);
            }
            System.out.println("判别后getlist1_H.size()为"+getlist1_H.size());

            //整合重链数值 准确率
            int t = 0;
            while (t < getlist1_H.size()) {
                for(int s = linesplitdou_H.size(); s < countline; ++s){
                    if (getlist1_H.get(t) != s) {
                        if (getlist1_H.get(t) == s-1) {
                            if (getlist3_H.get(t) > getlist3_H.get(t-1)) {
                                linesplitdou_H.set(s-1, getlist3_H.get(t));
                                break;
                            }
                            break;
                        }
                        linesplitdou_H.add(s, 80.0);
                    }else if (getlist1_H.get(t) == s) {
                        linesplitdou_H.add(s, getlist3_H.get(t));
                        break;
                    }
                }
                t++;
            }
            System.out.println("最终重链linesplitdou_H.size()为" + linesplitdou_H.size());
            System.out.println();

        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }

        //输出value文件
        ArrayList outvalues = new ArrayList();

        for(int var7 = 0; var7 < var1.size(); ++var7) {
            String var8 = (String)var1.get(var7);
            float[] var9 = (float[])var2.get(var7);
            List var10 = A(var8, var9, var4, var5, count, linesplitdou_H);  //跳转到第229行，k-mers集合
            int num = var10.size();
            count = num + count;
            //这里也和value有关
            outvalues.add(var10);
            Iterator var11 = var10.iterator();

            while(var11.hasNext()) {         //没有指针下移操作，只是判断是否存在下一个元素
                C var12 = (C)var11.next();   //指针移到下一行，返回指针所指向的元素
                A var13 = this.A(var12.B);  //跳转到第100行  若存在var12.B，则直接返回
                A var14 = this.A(var12.E);
                ++var13.J;
                ++var14.I;
                var13.B += (Float)var3.get(var7);
                var14.B += (Float)var3.get(var7);
                var13.G += var12.A;
                var14.G += var12.D;
                if (!this.I.containsKey(var13)) {
                    this.I.put(var13, new ArrayList());
                }

                ((List)this.I.get(var13)).add(var14);
                if (!this.E.containsKey(var14)) {
                    this.E.put(var14, new ArrayList());
                }

                ((List)this.E.get(var14)).add(var13);
                if (var13.H.containsKey(var14)) {
                    var13.H.put(var14, (Double)var13.H.get(var14) + var12.A);
                } else {
                    var13.H.put(var14, var12.A);
                }

                if (var14.C.containsKey(var13)) {
                    var14.C.put(var13, (Double)var14.C.get(var13) + var12.D);
                } else {
                    var14.C.put(var13, var12.D);
                }

                var13.A.add(var7);
                var14.A.add(var7);
            }
        }
        //System.out.println("有" + count + "条数据（count）");

        Iterator var15;   //创建迭代器
        A var16;
        for(var15 = this.A.values().iterator(); var15.hasNext(); var16.F = var16.G) {
            var16 = (A)var15.next();   //将this.A中的value放入var16，同时令var16.F=var16.G
        }

        var15 = this.A.values().iterator();

        while(var15.hasNext()) {
            var16 = (A)var15.next();
            if (var16.A()) {   //跳转到A类中第26行  this.I=this.J
                ++this.C;
            } else if (var16.B()) {  //Math.abs(this.I - this.J) == 1
                if (var16.I == var16.J + 1) {
                    this.F = var16;
                }

                if (var16.I == var16.J - 1) {
                    this.H = var16;
                }

                ++this.J;
            } else {
                ++this.B;
            }
        }

        //File outfile = new File("C:/Users/木子/Desktop/ALPS/test-file/value0222_H.csv");
        File outfile = new File("F:/研究生/ALPS/test-file/测试数据/Human_WIgG1-k5/HumanLH_spider/value0616_H.csv");
        BufferedWriter writefile = new BufferedWriter(new FileWriter(outfile));
        Iterator outvalues_1 = outvalues.iterator();
        while(outvalues_1.hasNext()){
            List outvalues_2 = (List)outvalues_1.next();
            for(int nen = 0; nen < outvalues_2.size(); ++nen){
                C outvalues_3 = (C)(outvalues_2.get(nen));
                writefile.write(outvalues_3.C);
                writefile.write(",");
                writefile.write(String.format("%.8f",outvalues_3.A));
                writefile.write(",");
                writefile.write(String.format("%.8f",outvalues_3.D));
                writefile.write("\n");
            }
        }
        writefile.close();
        System.out.println("写入value_H文件csv");

    }

    public A A(String var1) {
        if (this.A.containsKey(var1)) {  //this.A中是否存在var1这个key值
            return (A)this.A.get(var1);  //返回指定键所映射的值
        } else {
            A var2 = new A(var1);
            this.A.put(var1, var2);  //HashMap.put(key值,value值)
            return var2;
        }
    }

    public int A() {
        return this.A.size();
    }

    public int E() {
        int var1 = 0;

        A var3;
        for(Iterator var2 = this.I.keySet().iterator(); var2.hasNext(); var1 += ((List)this.I.get(var3)).size()) {
            var3 = (A)var2.next();
        }

        return var1;
    }

    public boolean D() {
        System.out.println("self.nneither = " + this.B);
        System.out.println("self.nsemi = " + this.J);
        return this.B == 0 && this.J == 2;
    }

    public boolean B() {
        return this.B == 0 && this.J == 0;
    }

    public boolean C() {
        System.out.println(this.D());
        System.out.println(this.B());
        return this.D() || this.B();
    }

    public void A(A var1, List<A> var2) {
        label16:
        while(true) {
            if (((List)this.I.get(var1)).size() > 0) {
                List var3 = (List)this.I.get(var1);
                Iterator var4 = var3.iterator();

                while(true) {
                    if (!var4.hasNext()) {
                        continue label16;
                    }

                    A var5 = (A)var4.next();
                    this.A(var5, var2);
                }
            }

            var2.add(var1);
            return;
        }
    }

    public void A(FileWriter var1) {
        this.A(var1, false);
    }

    public void A(FileWriter var1, boolean var2) {
        try {
            var1.write("digraph \"Graph\" {\n");
            var1.write("  bgcolor=\"transparent\";\n");
            Iterator var3 = this.I.keySet().iterator();

            A var4;
            while(var3.hasNext()) {
                var4 = (A)var3.next();
                String var5 = var4.D;
                var1.write("  " + var5 + " [label=\"" + var5 + "\"] ;\n");
            }

            var3 = this.I.keySet().iterator();

            while(true) {
                while(var3.hasNext()) {
                    var4 = (A)var3.next();
                    List var13 = (List)this.I.get(var4);
                    String var6 = var4.D;
                    if (var2) {
                        HashMap var14 = new HashMap();
                        Iterator var15;
                        A var16;
                        if (var2) {
                            for(var15 = var13.iterator(); var15.hasNext(); var14.put(var16, (Integer)var14.get(var16) + 1)) {
                                var16 = (A)var15.next();
                                if (!var14.containsKey(var16)) {
                                    var14.put(var16, 0);
                                }
                            }
                        }

                        var15 = var14.keySet().iterator();

                        while(var15.hasNext()) {
                            var16 = (A)var15.next();
                            int var10 = (Integer)var14.get(var16);
                            String var11 = var16.D;
                            var1.write("  " + var6 + " -> " + var11 + " [label=\"" + var10 + "\"] ;\n");
                        }
                    } else {
                        Iterator var7 = var13.iterator();

                        while(var7.hasNext()) {
                            A var8 = (A)var7.next();
                            var6 = var4.D;
                            String var9 = var8.D;
                            var1.write("  " + var6 + " -> " + var9 + " [label=\"\"] ;\n");
                        }
                    }
                }

                var1.write("}\n");
                break;
            }
        } catch (IOException var12) {
            var12.printStackTrace();
        }

    }
    //对肽序列片段进行k-mer切片，计算值，返回C类参数名及对应的值
    public static List<C> A(String var0, float[] var1, int var2, double[] var3, int count, List<Double> linesplitdou) {
        ArrayList var4 = new ArrayList();
        Double getlinesplitdou = null;

        for(int var5 = 0; var5 < var0.length() - var2 + 1; ++var5) {
            String var6 = var0.substring(var5, var5 + var2);
            String var7 = var0.substring(var5, var5 + var2 - 1);
            String var8 = var0.substring(var5 + 1, var5 + var2);
            float var9 = 0.0F;
            float var10 = 0.0F;

            getlinesplitdou = linesplitdou.get(count);
            count++;

            for(int var11 = var5 + 1; var11 < var5 + var2 - 1; ++var11) {
                /*原公式如下
                var9 = (float)((double)var9 + Math.log((double)var1[var11]) * var3[var11 - var5]);
                */
                var9 = (float)((double)var9 + Math.log((double)var1[var11]) * var3[var11 - var5]);
                var10 = (float)((double)var10 + var3[var11 - var5]);
            }

            /*原公式如下
            float var13 = (float)Math.exp(((double)var9 + Math.log((double)var1[var5]) * var3[0]) / ((double)var10 + var3[0]));
            float var12 = (float)Math.exp(((double)var9 + Math.log((double)var1[var5 + var2 - 1]) * var3[var2 - 1]) / ((double)var10 + var3[var2 - 1]));
             * getlinesplitdou * 0.01*/
            double var9_d = (double)var9;
//            float var13 = (float)Math.exp(((var9_d + Math.log((double)var1[var5]) * var3[0]) * getlinesplitdou * 0.01) / ((double)var10 + var3[0]));
//            float var12 = (float)Math.exp(((var9_d + Math.log((double)var1[var5 + var2 - 1]) * var3[var2 - 1]) * getlinesplitdou * 0.01) / ((double)var10 + var3[var2 - 1]));
            float var13 = (float)Math.exp((var9_d * getlinesplitdou * 0.01 + Math.log((double)var1[var5]) * var3[0]) / (((double)var10 + var3[0]) * getlinesplitdou * 0.01));
            float var12 = (float)Math.exp((var9_d * getlinesplitdou * 0.01 + Math.log((double)var1[var5 + var2 - 1]) * var3[var2 - 1]) / (((double)var10 + var3[var2 - 1])* getlinesplitdou * 0.01));
            var4.add(new C(var6, var7, var8, (double)var13, (double)var12));  //这里调用c类里的public C(...)方法
        }

        return var4;
    }

    public static boolean A(String var0, String var1) {
        return var0.equalsIgnoreCase(var1);
    }
}
