package com.kenshiro.concurrent.jvm;

import com.kenshiro.concurrent.unit.Sleeper;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class Test15 {

    static boolean stop = true;
    private List<GraphImage.GraphImages> graphImages;

    public static void main(String[] args) {
        new Thread(() -> {
            while (stop) {
                System.out.println(1);
            }
        }).start();

        Sleeper.sleep(1);
        stop = false;
    }

}
