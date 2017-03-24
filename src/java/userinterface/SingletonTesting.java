package userinterface;

import models.Worker;

/**
 * Created by Ders on 3/23/2017.
 */
public class SingletonTesting {

    private static SingletonTesting singelton = null;

    private static Worker modWorkerBanner;

    private SingletonTesting() {
        // Prevent instantiation
    }

    public static SingletonTesting getInstance() {

        if (singelton == null) {
            singelton = new SingletonTesting();
        }

        return singelton;

    }

    public static Worker getModWorkerBanner() {
        return modWorkerBanner;
    }

    public static void setModWorkerBanner(Worker w) {
        modWorkerBanner = w;
    }
















}
