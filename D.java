import java.util.ArrayList;
import java.util.List;

public class D {

    public static ArrayList getK(List<String> var1, int var4) {

        //主要用于k_mertofasta.java
        ArrayList getkmer = new ArrayList();
        String var3 = null;
        String var6 = null;
        for (int var2 = 0; var2 < var1.size(); ++var2) {
            var3 = (String) var1.get(var2);

            for(int var5 = 0; var5 < var3.length() - var4 + 1; ++var5) {
                var6 = var3.substring(var5, var5 + var4);
                getkmer.add(var6);
            }
        }
        return getkmer;
    }
}
