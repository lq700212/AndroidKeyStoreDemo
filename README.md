# AndroidKeyStoreDemo
因为公司的特殊业务需求，需要在传统的应用cache目录(/data目录)、sd卡、网络存储之外，再开辟另一种存储方式<br>
所以对AndroidKeyStore进行了一下研究<br>
在github上记录一下自己的研究成果<br>

## 利用AndroidKeyStore系统进行存储<br>
AndroidKeyStore主要是用来加密一些私密信息用的，好像暂时未找到存储私密信息的方法<br>
这里其实是利用了一种取巧的方式，KeyStore有保存alias的功能(KeyPairGeneratorSpec的setAlias方法)<br>
所以可以把想存储的信息当成alias存入KeyStore<br>
要取出的时候，对KeyStore的alias进行遍历，根据条件找到要取出的alias即可<br>

#### 测试的时候发现目前这种KeyStore存储，无法做到像存sd卡那样持久，即应用卸载重装后，存储的信息还在<br>

## 参考:<br>
[Android Keystore加解密以及遇到的坑](https://www.jianshu.com/p/06775ddf435f)<br>
[Android保存私密信息－强大的keyStore（译）](https://www.jianshu.com/p/dc5a9f906eb8)<br>
