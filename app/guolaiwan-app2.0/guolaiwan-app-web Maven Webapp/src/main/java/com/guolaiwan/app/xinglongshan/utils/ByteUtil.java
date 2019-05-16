package com.guolaiwan.app.xinglongshan.utils;

public final class ByteUtil{

    public ByteUtil(){
    }

    public static String bytes2String(byte bytes[]){
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        int b = 0;
        for(int i = 0; i < bytes.length; i++){
            b = bytes[i];
            if(b < 0)
                b &= 0xff;
            else
            if(b <= 15)
                sb.append('0');
            sb.append(Integer.toHexString(b));
        }
        return sb.toString();
    }
}
