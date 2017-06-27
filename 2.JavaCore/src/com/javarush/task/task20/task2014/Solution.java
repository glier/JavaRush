package com.javarush.task.task20.task2014;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/* 
Serializable Solution
*/
public class Solution implements Serializable {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        String path = "C:\\Users\\Борозденец\\Documents\\JavaProjects\\JavaRushTasks\\2.JavaCore\\src\\com\\javarush\\task\\task20\\task2014";
        File your_file_name = File.createTempFile("2014", ".txt", new File(path));
        ObjectOutput output = new ObjectOutputStream(new FileOutputStream(your_file_name));
        ObjectInput input = new ObjectInputStream(new FileInputStream(your_file_name));

        Solution saved = new Solution(10);
        Thread.sleep(1000);
        Solution loaded = new Solution(20);

        output.writeObject(saved);
        output.flush();
        output.close();

        loaded = (Solution) input.readObject();
        input.close();

        System.out.println(saved.string);
        System.out.println(loaded.string);
        System.out.println(saved.string.equals(loaded.string));
    }

    private transient final String pattern = "dd MMMM yyyy hh:mm:ss, EEEE";
    private transient Date currentDate;
    private transient int temperature;
    String string;

    public Solution(int temperature) {
        this.currentDate = new Date();
        this.temperature = temperature;

        string = "Today is %s, and current temperature is %s C";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        this.string = String.format(string, format.format(currentDate), temperature);
    }

    @Override
    public String toString() {
        return this.string;
    }
}
