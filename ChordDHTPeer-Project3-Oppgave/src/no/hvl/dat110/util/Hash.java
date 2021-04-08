package no.hvl.dat110.util;

/**
 * project 3
 *
 * @author tdoy
 */

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {

    private static BigInteger hashint;

    private static MessageDigest md5;

    public static BigInteger hashOf(String entity) {

        // Task: Hash a given string using MD5 and return the result as a BigInteger.

        // we use MD5 with 128 bits digest
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        md5.update(entity.getBytes()); // 128?

        // compute the hash of the input 'entity'

        byte[] digest = md5.digest();

        // convert the hash into hex format

        String hashed = toHex(digest);

        // convert the hex into BigInteger

        hashint = new BigInteger(hashed, 16);


        // return the BigInteger


        return hashint;
    }

    public static BigInteger addressSize() {

        // Task: compute the address size of MD5
        int length = 0;
        BigInteger size = null;

        // get the digest length
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        length = md5.getDigestLength();

        // compute the number of bits = digest length * 8
        length = length * 8;

        // compute the address size = 2 ^ number of bits
        size = BigInteger.valueOf((long) Math.pow(2, length));
        // return the address size

        return size;
    }

    public static int bitSize() {

        int digestlen = 0;

        // find the digest length

        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        digestlen = md5.getDigestLength();
        return digestlen * 8;
    }

    public static String toHex(byte[] digest) {
        StringBuilder strbuilder = new StringBuilder();
        for (byte b : digest) {
            strbuilder.append(String.format("%02x", b & 0xff));
        }
        return strbuilder.toString();
    }

}
