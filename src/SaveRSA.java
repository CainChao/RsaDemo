import java.security.*;

/**
 * @Author:CainChao
 * @Description:使用保存方式RSA加密
 * @Date:2019/3/15 15:15
 **/
public class SaveRSA {

    public static void main(String []args){
        String data = "平安是幸，知足是福，清心是禄，寡欲是寿。";
        //保存公钥和私钥
        RSAUtil.saveKey();
        //获取保存公钥
        PublicKey savPublic = RSAUtil.getPublicKey(FileUtil.FILE_PATH+FileUtil.PUBLIC_FILE_NAME);
        //获取保存私钥
        PrivateKey savPrivate = RSAUtil.getPrivateKey(FileUtil.FILE_PATH+FileUtil.PRIVATR_FILE_NAME);
        //加密后的数据
        String encryp = RSAUtil.encryptionData(savPublic,data);
        System.out.println("【加密后数据==========】"+encryp);
        //解密后的数据
        String  decrypt = RSAUtil.decryptData(savPrivate,encryp);
        System.out.println("【解密数据=========】"+decrypt);




    }
}
