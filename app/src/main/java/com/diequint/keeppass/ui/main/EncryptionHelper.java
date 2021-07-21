package com.diequint.keeppass.ui.main;

import android.util.Base64;
import java.security.MessageDigest;
import java.util.Random;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class EncryptionHelper {

    public EncryptionHelper() {
    }

    public String encryptMessage(String plainText, String keyword) {
        try {
            SecretKeySpec secretKey = generateKey(keyword);
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE,secretKey);
            byte[] datosEncriptadosBytes = cipher.doFinal(plainText.getBytes());
            return Base64.encodeToString(datosEncriptadosBytes, Base64.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Unexpected error during encryption process. Please try it again";
    }

    public String decryptMessage(String codedText, String keyword) {
        try {
            SecretKeySpec secretKey = generateKey(keyword);
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] datosDecodificados = Base64.decode(codedText,Base64.DEFAULT);
            byte[] datosDesencriptadosByte = cipher.doFinal(datosDecodificados);
            return new String(datosDesencriptadosByte);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Unexpected error during encryption process. Please try it again";
    }

    private SecretKeySpec generateKey(String password) throws Exception{
        MessageDigest sha =  MessageDigest.getInstance("SHA-256");
        byte[] key = password.getBytes("UTF-8");
        key = sha.digest(key);
        return new SecretKeySpec(key,"AES");
    }

    public String generateRandom (int complexity, int length) {
        Random generator = new Random();
        String password = "";
        char[] MINUS = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','x','y','z'};
        char[] MAYUS = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','X','Y','Z'};
        char[] NUMS = {'1','2','3','4','5','6','7','8','9','0'};
        char[] SYMB = {'*','+','-','/','%','@','_','?','!','&','#'};
        char[] workingSet;
        switch (complexity) {   //Boolean combinations in this order SYMB NUMS MAYUS MINUS
            case 1:             //                                     0    0    0    1
                workingSet = MINUS;
                break;
            case 2:             //                                     0    0    1    0
                workingSet = MAYUS;
                break;
            case 3:             //                                     0    0    1    1
                workingSet = concate(MINUS,MAYUS);
                break;
            case 4:             //                                     0    1    0    0
                workingSet = NUMS;
                break;
            case 5:
                workingSet = concate(MINUS,NUMS);
                break;
            case 6:
                workingSet = concate(MAYUS,NUMS);
                break;
            case 7:
                workingSet = concate(concate(MINUS,MAYUS),NUMS);
                break;
            case 8:
                workingSet = SYMB;
                break;
            case 9:
                workingSet = concate(MINUS,SYMB);
                break;
            case 10:
                workingSet = concate(MAYUS,SYMB);
                break;
            case 11:
                workingSet = concate(concate(MINUS,MAYUS),SYMB);
                break;
            case 12:
                workingSet = concate(NUMS,SYMB);
                break;
            case 13:
                workingSet = concate(concate(MINUS,NUMS),SYMB);
                break;
            case 14:
                workingSet = concate(concate(MAYUS,NUMS),SYMB);
                break;
            case 15:
                workingSet = concate(concate(MINUS,MAYUS),concate(NUMS,SYMB));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + complexity);
        }
        for (int i=0; i<length; i++){
            password = password+workingSet[generator.nextInt(workingSet.length-1)];
        }
        return password;
    }

    private char[] concate(char[] array1, char[] array2) {
        int position=0, count=0;
        char[] result = new char[(array1.length+array2.length)];
        while (position<array1.length){
            result[position]=array1[position];
            position++;
        }
        while (position<(array1.length+array2.length)) {
            result[position]=array2[count];
            position++;
            count++;
        }
        return result;
    }
}
