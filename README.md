#RSA加密
**RSA公开密钥密码体制。所谓的公开密钥体制就是使用不同的加密密钥与解密密钥，是一种“由已知加密密钥推导出解密密钥在计算上是不可行的密码体制。**

**在公开密钥密码体制中，加密密钥（即公开密钥）PK是公开信息，而解密密钥（即秘密密钥）SK是需要保密的。加密算法E和解密算法D也都是公开的。
虽然解密密钥SK是由公开密钥PK决定的，但却不能根据PK计算出SK。**

**RSA算法是一种非对称密码算法，所谓非对称，就是指该算法需要一对密钥，使用其中一个加密，则需要用另一个才能解密。**


**公钥和私钥本身存储的信息是乱码，在实际使用中，我们还可以通过Base64将这些乱码编码为可识别的ASCII码，
然后将公钥和私钥信息持久化存储到文件中，在以后需要使用时，可以从文件中读取公钥和私钥信息。
为此，我们可以写一个RSA的工具类，从一个储存公钥和私钥信息的文件里读取公钥和私钥信息，
然后定义获取公钥和私钥的方法，以及加密和解密数据的方法。**

