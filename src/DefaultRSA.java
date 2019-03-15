import java.security.*;

/**
 * @Author:CainChao
 * @Description:默认情况RSA加密
 * @Date:2019/3/15 14:11
 **/
public class DefaultRSA {

    public static void main(String []args){

        String data = "平安是幸，知足是福，清心是禄，寡欲是寿。";

        //1.构建公私钥匙对
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");

            KeyPair keyPair = keyPairGenerator.generateKeyPair();

            //2.获取钥匙对中的公钥
            PublicKey publicKey = keyPair.getPublic();

            //3.获取钥匙对中的私钥
            PrivateKey privateKey = keyPair.getPrivate();

            String  encryption = RSAUtil.encryptionData(publicKey,data);
            System.out.println("【加密后数据==========】"+encryption);

            String decrypt = RSAUtil.decryptData(privateKey,encryption);
            System.out.println("【解密数据=========】"+decrypt);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }
}
