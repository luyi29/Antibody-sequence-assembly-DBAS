import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class getIdentity_try1 {
    public static void main(String[] args) {
        String line = null;
        String lines = null;
        String[] linesplit;

        List<Integer> getlist1 = new ArrayList<>();
        List<Double> getlist2 = new ArrayList<>();
        List<Integer> getlist3 = new ArrayList<>();
        List<Double> linesplitdou = new ArrayList<>();

        Integer countline = 0;

        try{
            //F:/blast-2.12.0+/doc/gene-db3w_6k.txt
            //C:/Users/木子/Desktop/test-file/gene-db3w_6k.txt
            //C:/Users/木子/Desktop/test-file/peptides_0.csv_blast6.fasta
            String filename1 = "C:/Users/木子/Desktop/ALPS/test-file/peptides_0.csv_cut6.fasta";
            BufferedReader readlinek = new BufferedReader(new FileReader(filename1));
            while ((lines = readlinek.readLine()) != null) {
                countline = countline + 1;
            }
            countline = countline / 2;

            String filename2 = "C:/Users/木子/Desktop/ALPS/test-file/gene-db3w_6k.txt";
            BufferedReader readtxt = new BufferedReader(new FileReader(filename2));
            while ((line = readtxt.readLine()) != null) {
                linesplit = line.split("\\s");
                String g1 = linesplit[0].substring(3);
                Integer g1_int = Integer.parseInt(g1);
                getlist1.add(g1_int);

                String g2 = linesplit[2];
                Double g2_double = Double.parseDouble(g2);
                getlist2.add(g2_double);

                String g3 = linesplit[3];
                Integer g3_int = Integer.parseInt(g3);
                getlist3.add(g3_int);
            }
            System.out.println("一共有多少个" + countline + "长度为6的k-mer切片");
            System.out.println(getlist1.size());

            //此循环是将重复出现的seq*的最后一个比对百分比进行存储
            int i = 0;
            while (i < getlist1.size()) {
                for(int j = linesplitdou.size(); j < countline; ++j){
                    if (getlist1.get(i) != j) {
                        if (getlist1.get(i) == j-1) {
                            if (getlist2.get(i) > getlist2.get(i-1)) {
                                linesplitdou.set(j-1, getlist2.get(i));
                                break;
                            }
                            break;
                        }
                        linesplitdou.add(j, 0.1);
                    }else if (getlist1.get(i) == j) {
                        linesplitdou.add(j, getlist2.get(i));
                        break;
                    }
                }
                i++;
            }
            System.out.println(linesplitdou.size());
            /*for (int s = 0; s < linesplitdou.size(); ++s) {
                System.out.println(linesplitdou.get(s));
            }*/
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
