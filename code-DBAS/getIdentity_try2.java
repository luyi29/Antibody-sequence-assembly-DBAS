import java.io.*;
import java.nio.DoubleBuffer;
import java.util.ArrayList;
import java.util.List;

public class getIdentity_try2 {
    public static void main(String[] args) {
        String line = null;
        String lines = null;
        String[] linesplit;

        List<Integer> getlist1_L = new ArrayList<>();
        List<Integer> getlist1_H = new ArrayList<>();
        List<String> getlist2_L = new ArrayList<>();
        List<String> getlist2_H = new ArrayList<>();
        List<Double> getlist3_L = new ArrayList<>();
        List<Double> getlist3_H = new ArrayList<>();
        List<Double> linesplitdou_L = new ArrayList<>();
        List<Double> linesplitdou_H = new ArrayList<>();

        Integer countline = 0;

        try{
            String filename1 = "C:/Users/木子/Desktop/ALPS/test-file/peptides_0.csv_cut6.fasta";
            BufferedReader readlinek = new BufferedReader(new FileReader(filename1));
            while ((lines = readlinek.readLine()) != null) {
                countline = countline + 1;
            }
            countline = countline / 2;

            String filename2 = "C:/Users/木子/Desktop/ALPS/test-file/IMGT-Human-Light-Heavyblast6.txt";
            BufferedReader readtxt = new BufferedReader(new FileReader(filename2));
            while ((line = readtxt.readLine()) != null) {
                linesplit = line.split("\\s");
                String g1 = linesplit[0].substring(3);
                Integer g1_int = Integer.parseInt(g1);
                String g2_LH = linesplit[1];
                String g3 = linesplit[2];
                Double g3_double = Double.parseDouble(g3);

                //判别第二列为L或H，分别放入两组数组
                if (g2_LH.substring(0,1).equals("L")) {
                    getlist1_L.add(g1_int);       //存储seq*后面的*
                    getlist2_L.add(g2_LH);        //存储L*
                    getlist3_L.add(g3_double);    //存储匹配率，如100%
                } else {
                    getlist1_H.add(g1_int);
                    getlist2_H.add(g2_LH);
                    getlist3_H.add(g3_double);
                }
            }
            System.out.println("一共有" + countline + "个长度为6的k-mer切片");
            System.out.println("原来的getlist1_L.size为"+getlist1_L.size());
            System.out.println("原来的getlist1_H.size为"+getlist1_H.size());
            int getlist1_L_size = getlist1_L.size();
            int getlist1_H_size = getlist1_H.size();
            if (getlist1_L.get(getlist1_L_size - 1) != countline - 1) {
                getlist1_L.add(countline - 1);
                getlist2_L.add("add_L_value");
                getlist3_L.add(100.0);
            }
            if (getlist1_H.get(getlist1_H_size - 1) != countline - 1) {
                getlist1_H.add(countline - 1);
                getlist2_H.add("add_H_value");
                getlist3_H.add(100.0);
            }
            System.out.println("判别后getlist1_L.size为"+getlist1_L.size());
            System.out.println("判别后getlist1_H.size为"+getlist1_H.size());

            //整合轻链数值 准确率
            int i = 0;
            while (i < getlist1_L.size()) {
                for(int j = linesplitdou_L.size(); j < countline; ++j){
                    if (getlist1_L.get(i) != j) {
                        if (getlist1_L.get(i) == j-1) {
                            if (getlist3_L.get(i) > getlist3_L.get(i-1)) {
                                linesplitdou_L.set(j-1, getlist3_L.get(i));
                                break;
                            }
                            break;
                        }
                        linesplitdou_L.add(j, 0.1);
                    }else if (getlist1_L.get(i) == j) {
                        linesplitdou_L.add(j, getlist3_L.get(i));
                        break;
                    }
                }
                i++;
            }
            System.out.println("linesplitdou_L.size()为" + linesplitdou_L.size());

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
                        linesplitdou_H.add(s, 0.1);
                    }else if (getlist1_H.get(t) == s) {
                        linesplitdou_H.add(s, getlist3_H.get(t));
                        break;
                    }
                }
                t++;
            }
            System.out.println("linesplitdou_H.size()为" + linesplitdou_H.size());
/*            for(int num = 0; num <linesplitdou_H.size(); ++num){
                System.out.println(linesplitdou_H.get(num));
            }*/


        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
