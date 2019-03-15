import sun.misc.BASE64Encoder;

import java.io.*;
import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @Author:CainChao
 * @Description:
 * @Date:2019/3/15 14:27
 **/
public class FileUtil {
    /**
     * 文件存放路径
     */
    public static final String FILE_PATH = "F:/test/";
    /**
     * 公钥文件名
     */
    public static final String PUBLIC_FILE_NAME ="rsa_public_key.pem";
    /**
     * 私钥文件名
     */
    public static final String PRIVATR_FILE_NAME="rsa_private_key.pem";
    /**
     * 保存密钥
     * @param key
     */
    public static void saveKey(Key key){
        //保存文件名
        String filename = "";
        String startstr = "";
        String endstr = "";

        if(key instanceof PublicKey){
            filename = PUBLIC_FILE_NAME;
            startstr = "-----BEGIN RSA PUBLIC KEY-----";
            endstr = "-----BEGIN RSA PUBLIC KEY-----";
        }else if(key instanceof PrivateKey){
            filename = PRIVATR_FILE_NAME;
            startstr = "-----BEGIN RSA PRIVATE KEY-----";
            endstr = "-----END RSA PRIVATE KEY-----";
        }
        File file = new File(FILE_PATH + filename);
        BufferedWriter bw = null;
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            bw = new BufferedWriter(new FileWriter(file));
            bw.write(startstr);
            bw.newLine();

            String encode = new BASE64Encoder().encode(key.getEncoded());
            bw.write(encode);
            bw.newLine();
            bw.write(endstr);
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != bw) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    /**
     * 读取文件
     *
     * @param filepath
     * @return
     */
    public static byte[] readKeyDatas(String filepath) {

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filepath));
            String str = null;
            StringBuilder buffer = new StringBuilder();

            while ((str = br.readLine()) != null) {
                if (str.contains("---")) {
                    continue;
                }
                buffer.append(str);
            }
            return buffer.toString().getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
