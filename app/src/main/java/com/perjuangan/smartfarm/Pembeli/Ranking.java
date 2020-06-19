package com.perjuangan.smartfarm.Pembeli;

import java.util.Comparator;

// digunkan untuk melakukan perurutan array
public class Ranking implements Comparable {
    private int num;
    private double nil;

    public Ranking(int number, double nilai) {
        this.num    = number;
        this.nil    = nilai;
    }

    public void setNum(int number) {
        this.num    = number;
    }

    public void setNil(double nilai) {
        this.nil    = nilai;
    }

    public int getNum() {
        return num;
    }

    public double getNil() {
        return  nil;
    }

    public static Comparator<Ranking> RanCompare = new Comparator<Ranking>() {
        @Override
        public int compare(Ranking ni1, Ranking ni2) {
            double delta = ni2.getNil() - ni1.getNil();
            if(delta > 0.00001) {
                return 1;
            } else if(delta < -0.00001) {
                return -1;
            } else {
                return 0;
            }
        }
    };

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}