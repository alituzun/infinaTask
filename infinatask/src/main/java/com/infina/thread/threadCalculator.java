package com.infina.thread;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class threadCalculator {

    public static double standartSapmaHesapla(short[] sayilar, int threadSayisi){
        ExecutorService executorService = Executors.newFixedThreadPool(threadSayisi-1);

        int chunkSize = sayilar.length / threadSayisi;
        System.out.println(chunkSize);
        if(sayilar.length % threadSayisi !=0){
            chunkSize++;
        }
        List<Callable<Short>> listOfCallable = null;
        for(int i=0;i<sayilar.length ;i++){
            int counter = 0;
            short[] temp = new short[chunkSize];
            for(int j = 0; j<chunkSize; j++){
                temp[j]=sayilar[counter];
                counter++;
            }
            int finalChunkSize = chunkSize;
            listOfCallable= Arrays.asList(()->{
                return calcAvg(new short[]{temp[2]}, finalChunkSize);
            });
        }

        try {
            List<Future<Short>> results=executorService.invokeAll(listOfCallable);
            results.forEach(r ->{
                try {
                    System.out.println("Result" + r.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
        return 1;
    }

    public  static short calcAvg(short[] tempArray, int chunkSize){
        short tempToplam = 0;
        short result=0;
        for (int i=0;i<tempArray.length;i++){
            tempToplam+=tempArray[i];
        }
        short tempOrtalama= (short) (tempToplam/chunkSize);
        for (int j=0;j<tempArray.length;j++){
            result+=Math.pow((tempArray[j]-tempOrtalama),2);
        }

        return result;
    }
}
