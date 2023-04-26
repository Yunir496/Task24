import java.util.*;

public class PhoneBookSet {
    public static Map<String, TreeSet<String>> mapPhoneBook = new TreeMap<>();
    private static final String NAME_REGEX = "[А-Яа-я]+";
    private static final String NUMBER_REGEX = "[\\d]{11}";

    public static void main(String[] args) {

        while (true) {
            System.out.println("Введите номер,имя или команду:");
            String text = new Scanner(System.in).nextLine();
            if (text.toLowerCase().startsWith("list")) {
                printAllContacts();
            }
            else if (text.toLowerCase().matches(NUMBER_REGEX)){
                addContactByPhone(text);
               }
            else if (text.toLowerCase().matches(NAME_REGEX)) {
                addContactByName(text);
            } else {
                System.out.println("Неверный формат ввода");
                return;
            }
        }
    }
       public static void addContactByPhone(String phone) {
           for (Map.Entry<String, TreeSet<String>> entry : mapPhoneBook.entrySet()) {
               if (entry.getValue() != null && entry.getValue().contains(phone)) {
                   System.out.println("Такой номер уже есть у другого контакта");
                   System.out.println(entry.getKey());
                   System.out.println(phone);
                   return;
               }
           }
           System.out.println("Такого номера нет в телефонной книге.Введите имя контакта для номера "+phone);
           String name = new Scanner(System.in).nextLine();
           TreeSet<String> phones = new TreeSet();
           if(name.matches(NAME_REGEX)){
               mapPhoneBook.put(name, phones);
               phones.add(phone);
               System.out.println("Контакт сохранен");
           }else {
               System.out.println("Неправильно введено имя");
           }
       }
    public static void addContactByName(String name) {
        if (mapPhoneBook.containsKey(name)) {
            System.out.print(name + ": ");
            for (String s : mapPhoneBook.get(name)) {
                System.out.println(s + " ");
            }
            return;
        } else {
            System.out.println("Такого имени в телефонной книге нет.\n" + "Введите номер телефона для контакта " + name + ":");
            String phone = new Scanner(System.in).nextLine();
            for (Map.Entry<String, TreeSet<String>> entry : mapPhoneBook.entrySet()) {
                if (entry.getValue() != null && entry.getValue().contains(phone)) {
                    System.out.println("Такой номер уже есть у другого контакта");
                    System.out.println(entry.getKey());
                    System.out.println(phone);
                    return;
                }
            }
        TreeSet<String> phones = new TreeSet();
        if (phone.matches(NUMBER_REGEX)) {
            mapPhoneBook.put(name, phones);
            phones.add(phone);
            System.out.println("Контакт сохранен");
        } else {
            System.out.println("Неправильно введен номер");
        }
    }
}
    public static void printAllContacts() {

        if(mapPhoneBook.isEmpty()){
            System.out.println("Книга контактов пуста");
            return;
        }
        mapPhoneBook.forEach((key,value)-> System.out.println(key+" - "+value));
    }
}
