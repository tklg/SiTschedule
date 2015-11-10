package net.tkluge.schedule;

import android.os.Environment;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by kluget on 9/2/2015.
 */
public class ManagerItem {

    public String name = "Name";
    private static File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/scheduler/");

    public ManagerItem(String name) {
        this.name = name;
    }
    public static ArrayList<ManagerItem> schedulesToList() {
        ArrayList a = new ArrayList();
        for (File f : dir.listFiles()) {
            if (f.isFile()) {
                a.add(new ManagerItem(f.getName()));
            }
        }
        return a;
    }

}
