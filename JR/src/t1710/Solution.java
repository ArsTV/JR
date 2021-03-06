package t1710;
/* 
CrUD — Create, Update, Delete

Программа запускается с одним из следующих наборов параметров:

-c name sex bd

-u id name sex bd

-d id

-i id

Значения параметров:

name — имя, String

sex — пол, «м» или «ж», одна буква

bd — дата рождения в следующем формате 15/04/1990

-c — добавляет человека с заданными параметрами в конец allPeople, выводит id (index) на экран

-u — обновляет данные человека с данным id

-d — производит логическое удаление человека с id, заменяет все его данные на null

-i — выводит на экран информацию о человеке с id: name sex (м/ж) bd (формат 15-Apr-1990)

id соответствует индексу в списке

Все люди должны храниться в allPeople

Используйте Locale.ENGLISH в качестве второго параметра для SimpleDateFormat

Пример параметров:

-c Миронов м 15/04/1990

Пример вывода для параметра -і:

Миронов м 15-Apr-1990

Требования:

1. Класс Solution долж
*/

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class Solution {
    public static List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) throws ParseException {

        if(args[0].contains("-c")) {
            DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
            Date bd = format.parse(args[3]);

            if (args[2].contains("м"))
                allPeople.add(Person.createMale(args[1], bd));
            else
                allPeople.add(Person.createFemale(args[1], bd));

            int id = allPeople.size() - 1;

            System.out.println(id);
        }

        if(args[0].contains("-u")) {
            String sex = args[3];
            int id = Integer.parseInt(args[1]);
            DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
            Date bd = format.parse(args[4]);

            allPeople.get(id).setName(args[2]);

            if(sex.contains("м"))
                allPeople.get(id).setSex(Sex.MALE);
            else
                allPeople.get(id).setSex(Sex.FEMALE);

            allPeople.get(id).setBirthDay(bd);
        }

        if(args[0].contains("-d")) {
            int id = Integer.parseInt(args[1]);

            allPeople.get(id).setBirthDay(null);
            allPeople.get(id).setSex(null);
            allPeople.get(id).setName(null);

        }

        if(args[0].contains("-i")) {
            int id = Integer.parseInt(args[1]);
            String name = allPeople.get(id).getName();
            String sex = "ж";

            if(allPeople.get(id).getSex().equals(Sex.MALE))
                sex = "м";

            DateFormat format2 = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

            String bd = format2.format(allPeople.get(id).getBirthDay());

            System.out.println(name + " " + sex + " " + bd);
        }

    }
}
