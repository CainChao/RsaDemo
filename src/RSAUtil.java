import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * @Author:CainChao
 * @Description:加密工具类
 * @Date:2019/3/15 14:12
 **/
public class RSAUtil {
    /**
     * 保存本地密钥
     */
    public static void saveKey(){
        KeyPair keyPair =   RSAUtil.getKeyPair();
        //获取公钥
        PublicKey publicKey = keyPair.getPublic();
        //获取私钥
        PrivateKey privateKey = keyPair.getPrivate();
        //保存公钥
        FileUtil.saveKey(publicKey);
        //保存si钥
        FileUtil.saveKey(privateKey);
    }
    /**
     * 获取keypair
     * @return
     */
    public static  KeyPair getKeyPair(){
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            return keyPair;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 加密数据
     * @param publicKey  公钥
     * @param originData 需要加密数据
     * @return
     */
    public static String encryptionData(PublicKey publicKey, String originData){
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] bytesEncrypt = cipher.doFinal(originData.getBytes());
            //Base64编码
            byte[] bytesEncryptBase64 = Base64.getEncoder().encode(bytesEncrypt);
            return new String(bytesEncryptBase64);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密数据
     * @param privateKey 私钥
     * @param encryptionData  密文
     * @return
     */
    public static String decryptData(PrivateKey privateKey, String encryptionData){
        try {
            //Base64解码
            byte[] bytesEncrypt = Base64.getDecoder().decode(encryptionData);
            //解密
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] bytesDecrypt = cipher.doFinal(bytesEncrypt);
            return new String(bytesDecrypt);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 生成公钥
     */
    public static PublicKey getPublicKey(String publicKeyPath) {
        //1.读取公钥文件,获取公钥数据
        byte[] bytesPublicBase64 = FileUtil.readKeyDatas(publicKeyPath);
        if(null == bytesPublicBase64){
            //获取数据为空重新保存密钥
            RSAUtil.saveKey();
            return null;
        }
        //2.对读取回来的数据进行Base64解码
        byte[] bytesPublic = Base64.getDecoder().decode(bytesPublicBase64);
        //3.把解码后的数据,重新封装成一个PublicKey对象
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(bytesPublic);
        KeyFactory keyFactory = null;
        try {
            keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(keySpec);
            return publicKey;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 生成私钥
     */
    public static PrivateKey getPrivateKey(String privateKeyPath) {
        //1.读取私钥文件,获取私钥数据
        byte[] bytesPrivateBase64 = FileUtil.readKeyDatas(privateKeyPath);
        if(null == bytesPrivateBase64){
            //获取数据为空重新保存密钥
            RSAUtil.saveKey();
            return null;
        }
        //2.对读取回来的数据进行Base64解码
        byte[] bytesPrivate = Base64.getDecoder().decode(bytesPrivateBase64);
        //3.把解码后的数据,重新封装成一个PrivateKey对象
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(bytesPrivate);
        KeyFactory keyFactory = null;
        try {
            keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
            return privateKey;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return null;
    }
}
