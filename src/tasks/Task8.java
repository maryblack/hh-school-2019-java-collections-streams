package tasks;

import common.Person;
import common.Task;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
А теперь о горьком
Всем придется читать код
А некоторым придется читать код, написанный мною
Сочувствую им
Спасите будущих жертв, и исправьте здесь все, что вам не по душе!
P.S. функции тут разные и рабочие (наверное), но вот их понятность и эффективность страдает (аж пришлось писать комменты)
P.P.S Здесь ваши правки желательно прокомментировать (можно на гитхабе в пулл реквесте)
 */
public class Task8 implements Task {

  //Не хотим выдывать апи нашу фальшивую персону, поэтому конвертим начиная со второй
  public List<String> getNames(List<Person> persons) {
    return persons.stream()
            .skip(1)
            .map(Person::getFirstName)
            .collect(Collectors.toList());
  }

  //ну и различные имена тоже хочется
  public Set<String> getDifferentNames(List<Person> persons) {
    return new HashSet<>(getNames(persons));
  }

  //Для фронтов выдадим полное имя, а то сами не могут
  public String convertPersonToString(Person person) {
     return Stream.of(person.getSecondName(), person.getFirstName(), person.getMiddleName())
            .filter(Objects::nonNull)
            .collect(Collectors.joining(" "));
  }

  // словарь id персоны -> ее имя
  public Map<Integer, String> getPersonNames(Collection<Person> persons) {
    Map<Integer, String> personsMap = new HashMap<>(1);
    persons.forEach(person -> {
      if (!personsMap.containsKey(person.getId())) {
        personsMap.put(person.getId(), convertPersonToString(person));
      }
    });
    return personsMap;
  }

  // есть ли совпадающие в двух коллекциях персоны?
  public boolean hasSamePersons(Collection<Person> persons1, Collection<Person> persons2) {
    return Collections.disjoint(new HashSet<>(persons1), persons2);
  }


  //Выглядит вроде неплохо...
  public long countEven(Collection<Integer> numbers) {
    return numbers.stream()
            .filter(num -> num % 2 == 0)
            .count();
  }

  @Override
  public boolean check() {
    System.out.println("Слабо дойти до сюда и исправить Fail этой таски?");
    boolean codeSmellsGood = true;
    boolean reviewerDrunk = false;
    return codeSmellsGood || reviewerDrunk;
  }
}
