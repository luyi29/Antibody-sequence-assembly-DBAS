import java.io.*;
import java.util.*;

public class k_1_mertofasta {

    public static void error_info() {
        System.out.println("Usage:");
        System.out.println("\tALPS <input_file> <k> [<c>]");
        System.out.println("\t\t- input_file: .csv files containing input data;");
        System.out.println("\t\t- k         : the length of k-mers, 6 or 7 is recommended.");
        System.out.println("\t\t- c         : the number of top contigs to use for assembly, default is 10.");
        System.exit(1);
    }

    //对肽序列进行处理，计算文件中所有肽的数量，这些肽的长度之和
    public static void peptides_deal(String var0, int var1, int var2, int var3, List<String> var4, List<float[]> var5, List<Float> var6) throws IOException {
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

    public static ArrayList tok_1_mer(List<String> var0, List<float[]> var1, List<Float> var2, int var3, double[] var4, int var5, int var6, ArrayList var7) throws IOException {
        B_L var10 = new B_L(var0, var1, var2, var3, var4, var5); //跳转B类第21行，构建德布鲁因图
        System.out.println("DeBruijnGraph constructed");
        System.out.println("Number of nodes = " + var10.A());  //this.A.size()
        System.out.println("Number of edges = " + var10.E());  //this.I.get(var3).size()之和
        System.out.println();

        ArrayList alist=new ArrayList();
        Iterator var12 = var10.A.values().iterator();
        while(var12.hasNext()){
            A var13 = (A)var12.next();
            alist.add(var13);
        }


        Iterator var11 = var10.A.values().iterator();
        var7 = new ArrayList();
        for(int i = 0;i<alist.size();i++){
            A alist_s = (A) alist.get(i);
            var7.add(alist_s.D);
        }
        System.out.println("输出数据有" + var7.size() + "行");
        return var7;
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner=new Scanner(System.in);
        System.out.println("输入的参数个数为：");
        int num=scanner.nextInt();
        if(num==2){
            System.out.println("请输入文件地址和k值：");
        }else if(num==3){
            System.out.println("请输入文件地址、k值和显示个数：");
        }else{
            System.out.println("输入的参数个数错误！");
        }
        String[] var0=new String[num];
        for(int i=0;i<var0.length;i++){
            var0[i]=scanner.next();
        }
        System.out.println("输入参数成功！");
        //C:\Users\Admin\Desktop\test-file\peptides_0.csv
        //C:\Users\Admin\Desktop\test-file\peptides_0_test.csv

        if (var0.length < 2) {
            error_info();
        }

        String var1 = var0[0];
        int var2 = 0;

        try {
            var2 = Integer.parseInt(var0[1]);
        } catch (NumberFormatException var27) {
            error_info();
        } catch (NullPointerException var28) {
            error_info();
        }

        byte var3 = 1;
        byte var4 = 2;
        byte var5 = 3;
        ArrayList var6 = new ArrayList();
        ArrayList var7 = new ArrayList();
        ArrayList var8 = new ArrayList();

        try {
            //第263行，执行后，var6存放肽序列，var7存放肽对应的置信度，var8存放特征区域
            peptides_deal(var1, var3, var4, var5, var6, var7, var8);
        } catch (IOException var26) {
            var26.printStackTrace();
        }

        int var9 = var2 - 1;
        double[] var10 = new double[var2];
        Arrays.fill(var10, 1.0D);
        var10[0] = (double)var9;
        var10[var2 - 1] = (double)var9;
        byte var11 = 0;
        int var12 = 10;
        if (var0.length > 2) {
            try {
                var12 = Integer.parseInt(var0[2]);
            } catch (NumberFormatException var24) {
                error_info();
            } catch (NullPointerException var25) {
                error_info();
            }
        }

        ArrayList var13 = new ArrayList();

        /*new k_1_mertofasta();
        System.gc();//提醒jvm的垃圾回收器执行gc，但是不确定是否马上执行gc
        //与Runtime.getRuntime().gc();的作用一样。
        System.runFinalization();//强制调用，失去引用的对象的finalize()方法*/

        ArrayList result_out = tok_1_mer(var6, var7, var8, var2, var10, var11, var12, var13);

        int result_num = result_out.size();
        File f1 = new File(var1 + "_blast" + var2 + ".fasta");
        BufferedWriter BW1 = new BufferedWriter(new FileWriter(f1));
        int var19 = 0;
        for(int var20 = 0; var20 < result_num; ++var20) {
            BW1.write(">seq" + var20 + "\n");
            BW1.write(result_out.get(var20).toString() + "\n");
        }
        System.out.println("测试测试测试，写入成功");
        BW1.close();
    }

    /*@Override
    protected void finalize() throws Throwable{
        super.finalize();
        System.out.println("SystemGCTest重写了finalize()");
        System.out.println();
    }*/
}
