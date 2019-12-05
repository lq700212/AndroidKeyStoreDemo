package com.example.mykeystoredemo;

import android.annotation.TargetApi;
import android.content.Context;
import android.security.KeyPairGeneratorSpec;
import android.text.TextUtils;

import java.math.BigInteger;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.util.Calendar;
import java.util.Enumeration;

import javax.security.auth.x500.X500Principal;

/**
 * Author: ryan.lei
 * Date: 2019/12/4 17:04
 * Description:
 */
public class KeyStoreUtil {
    private static KeyStoreUtil instance;

    // 单位年
    private final int maxExpiredTime = 100;
    private String x500PrincipalName = "CN=MyKey, O=Android Authority";

    private KeyStore keyStore;

    public static KeyStoreUtil getInstance() {
        if (instance == null) {
            synchronized (KeyStoreUtil.class) {
                if (instance == null) {
                    instance = new KeyStoreUtil();
                }
            }
        }
        return instance;
    }

    private KeyStoreUtil() {
        try {
            keyStore = KeyStore.getInstance("AndroidKeyStore");
            keyStore.load((KeyStore.LoadStoreParameter) null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getKey(String valueKey) {
        try {
            Enumeration enumeration = keyStore.aliases();
            while (enumeration.hasMoreElements()){
                String alias = (String) enumeration.nextElement();
                if(!alias.isEmpty()){
                    if(alias.contains(valueKey)){
                        String splitedAlias[] = alias.split(":");
                        return splitedAlias[1];
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @TargetApi(18)
    public void createNewKeys(Context context, String alias) {
        if (TextUtils.isEmpty(alias)) {
            return;
        }
        try {
            if (keyStore.containsAlias(alias)) {
                return;
            }
            // Create new key
            Calendar start = Calendar.getInstance();
            Calendar end = Calendar.getInstance();
            end.add(Calendar.YEAR, maxExpiredTime);
            KeyPairGeneratorSpec spec = new KeyPairGeneratorSpec.Builder(context)
                    .setAlias(alias)
                    .setSubject(new X500Principal(x500PrincipalName))
                    .setSerialNumber(BigInteger.ONE)
                    .setStartDate(start.getTime())
                    .setEndDate(end.getTime())
                    .build();
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA", "AndroidKeyStore");
            generator.initialize(spec);
            generator.generateKeyPair();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
