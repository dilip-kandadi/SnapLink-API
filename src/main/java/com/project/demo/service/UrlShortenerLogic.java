package com.project.demo.service;
import org.springframework.stereotype.Component;
@Component
public class UrlShortenerLogic {
    private static final String ALLOWED_CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private final char[] allowedChars = ALLOWED_CHARACTERS.toCharArray();
    private final int base = allowedChars.length;
    public String encode(long id) {
        if (id == 0) {
            return String.valueOf(allowedChars[0]);
        } else {
            StringBuilder encodedString = new StringBuilder();
            while (id > 0) {

                int remainder = (int) id % base;
                encodedString.append(allowedChars[remainder]);
                id = id / base;
            }
            encodedString = encodedString.reverse();
            return encodedString.toString();
        }
    }
    public long decode(String encodedString) {
        char[] chars  =  encodedString.toCharArray();
        int length = chars.length;
        long result = 0;
        for(int i=length-1;i>=0;i--){
            int val = ALLOWED_CHARACTERS.indexOf(chars[i]);
            result += val * (long) (Math.pow(base,length-i-1));
        }
        return result;
    }
}
