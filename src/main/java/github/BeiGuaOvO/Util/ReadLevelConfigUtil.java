package github.BeiGuaOvO.Util;

import github.BeiGuaOvO.PlayTimeRank;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ReadLevelConfigUtil {
    private static Properties properties;
    public static Map<String,String> getLevels(){
        Map<String,String>res=new HashMap<>();
        String fileName = PlayTimeRank.instance.getConfig().getString("level-config");
        String path = PlayTimeRank.mainPath + "/plugins/PlayTimeRank/prefix/" + fileName;
        if (!init(path))return null;
        properties.forEach((k,v)->{
            res.put((String) k, (String) v);
        });
        return res;
    }
    private static boolean init(String path){
        FileInputStream stream = null;
        try{
            try {
                stream = new FileInputStream(path);
                properties=new Properties();
                properties.load(stream);
            }finally {
                if (stream!=null)stream.close();
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
