package com.mygdx.MyScreen;

import java.util.List;

public class Trajectory {
    //quy dao la tap hop cac diem
    private List<Point0> listPoint0Des;
    public Trajectory(){};
    public Trajectory(List<Point0> listPoint0Des1) {
        listPoint0Des = listPoint0Des1;
    }
    public List<Point0> getListPoint0Des() {
        return listPoint0Des;
    }
    public void setListPoint0Des(List<Point0> listPoint0Des) {
        this.listPoint0Des = listPoint0Des;
    }
}
