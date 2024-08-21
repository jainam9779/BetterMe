package com.example.betterme.database;

import androidx.room.TypeConverter;
import java.util.Arrays;

public class Converters {

    @TypeConverter
    public static String fromBooleanArray(boolean[] array) {
        StringBuilder sb = new StringBuilder();
        for (boolean b : array) {
            sb.append(b ? 1 : 0);
        }
        return sb.toString();
    }

    @TypeConverter
    public static boolean[] toBooleanArray(String data) {
        boolean[] array = new boolean[data.length()];
        for (int i = 0; i < data.length(); i++) {
            array[i] = data.charAt(i) == '1';
        }
        return array;
    }
}
