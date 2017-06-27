package com.javarush.task.task20.task2002;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* 
Читаем и пишем в файл: JavaRush
*/
public class Solution {
    public static void main(String[] args) {
        //you can find your_file_name.tmp in your TMP directory or fix outputStream/inputStream according to your real file location
        //вы можете найти your_file_name.tmp в папке TMP или исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {
            String path = "C:\\Users\\Борозденец\\Documents\\JavaProjects\\JavaRushTasks\\2.JavaCore\\src\\com\\javarush\\task\\task20\\task2002";
            File your_file_name = File.createTempFile("2002", ".txt", new File(path));
            OutputStream outputStream = new FileOutputStream(your_file_name);
            InputStream inputStream = new FileInputStream(your_file_name);

            JavaRush javaRush = new JavaRush();
            //initialize users field for the javaRush object here - инициализируйте поле users для объекта javaRush тут
//            javaRush.users.add(new User());
//
//            User user = new User();
//            user.setFirstName("Ivan");
//            user.setLastName("Petrov");
//            user.setBirthDate(new Date());
//            user.setMale(true);
//            user.setCountry(User.Country.RUSSIA);
//            javaRush.users.add(user);

            javaRush.save(outputStream);
            outputStream.flush();

            JavaRush loadedObject = new JavaRush();
            loadedObject.load(inputStream);
            //check here that javaRush object equals to loadedObject object - проверьте тут, что javaRush и loadedObject равны
            if (javaRush.equals(loadedObject)) System.out.println("Равны");
            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with my file");
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with save/load method");
        }
    }

    public static class JavaRush {
        public List<User> users = new ArrayList<>();

        public void save(OutputStream outputStream) throws Exception {
            //implement this method - реализуйте этот метод
            PrintWriter printWriter = new PrintWriter(outputStream);
            for (int i = 0; i < users.size(); i++) {
                printWriter.print(users.get(i).getFirstName() + ";");
                printWriter.print(users.get(i).getLastName() + ";");
                printWriter.print(users.get(i).getBirthDate() != null ? users.get(i).getBirthDate().getTime() + ";" : null + ";");
                printWriter.print(users.get(i).isMale() + ";");
                printWriter.println(users.get(i).getCountry());
            }
            printWriter.flush();
        }

        public void load(InputStream inputStream) throws Exception {
            //implement this method - реализуйте этот метод
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            while (reader.ready()) {
                String str = reader.readLine();
                String[] splitstr = str.split(";");
                User user = new User();
                                                
                user.setFirstName(splitstr[0].equals("null") ? null : splitstr[0]);
                user.setLastName(splitstr[1].equals("null") ? null : splitstr[1]);
                user.setBirthDate(splitstr[2].equals("null") ? null : new Date(Long.parseLong(splitstr[2])));
                user.setMale(splitstr[3].equals("false") ? false : true);

                User.Country country = null;
                if (splitstr[4].equals("UKRAINE")) country = User.Country.UKRAINE;
                else if (splitstr[4].equals("RUSSIA")) country = User.Country.RUSSIA;
                else if (splitstr[4].equals("OTHER")) country = User.Country.OTHER;
                
                user.setCountry(splitstr[4].equals("null") ? null : country);

                users.add(user);
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            JavaRush javaRush = (JavaRush) o;

            return users != null ? users.equals(javaRush.users) : javaRush.users == null;

        }

        @Override
        public int hashCode() {
            return users != null ? users.hashCode() : 0;
        }
    }
}
