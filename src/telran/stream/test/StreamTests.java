package telran.stream.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class StreamTest {
  int[] arr = {10, 13, 8 , 7, 3, 5, 6};
  @Test
  @Disabled //отключить тест
  void arrayStramTest() {
    //вычисление суммы четных чисел
    
    assertEquals(24, Arrays.stream(arr).filter(n -> n % 2 == 0).sum());//сделали поток, загнали в него только четные, получили сумму, сравнили с 24
    //максимальное значение нечетного числа
    assertEquals(13, Arrays.stream(arr).filter(n -> n % 2 != 0).max().getAsInt());
    //последний метод .getAsInt берет целое число из optional на случай пустого массива (но обязательно и для заполненного)
    //для реально пустого массива нужно использовать .orElse(0)
  }
  
  @Test
  @Disabled
  void displayStortloto() {
    Random gen = new Random();
    gen.ints(7, 1, 50).distinct().limit(7).forEach(n -> System.out.print(n + " "));
    //7 чисел от 1 до 49, .distinct() удаляет повторы, лимит останавливает на 7 не повторяющихся
    }
  
  @Test
  @Disabled
  void evenOddGrouping() {
    Map<String, List<Integer>> mapOddEven = Arrays.stream(arr).boxed().collect(Collectors.groupingBy(n -> n % 2 == 0 ? "even" : "odd"));
    //boxed преобразовывает int в Integer, чтобы построить map, collect превращает поток объектов во что-то одно
    //n in groupingBy - значение на входе, после -> идет критерий группировки
    /*
     * тринарный оператор воспроизводит такой код (используется, если левая часть кода в if и else одинаковая)
     * String res = "";
      if(n % 2 == 0) {
        res = "even";
      } else {
        res = "odd";
      }
      return res;
    }));
     */
    System.out.println(mapOddEven);
    }
  @Test
  @Disabled
  void displayOccurrenceSorted() { //отобразить повторяющиеся элементы в порядке убывания, как количества повторений так и количества символов в элементе
    String[] strings = {"lpm", "y", "a", "lpm", "aa", "yy", "yy", "aa", "lpm"};
    Map<String, Long> occurrencesMap = Arrays.stream(strings).collect(Collectors.groupingBy(s -> s, Collectors.counting()));
    //в двойном groupingBy первый параметр - критерий группировки, второй - значение
    //counting вместо списка повторяющихся элементов отображает число повторений
    occurrencesMap.entrySet().stream().sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
    .forEach(e -> System.out.printf("%s => %d\n", e.getKey(), e.getValue()));
    //распечатка с сортировкой по частоте встречаемости от большего к меньшему
  }
  @Test
	void stringStream() {
		String string = "Hello";
		//output: H,e,l,l,o
		//string.chars().forEach(c -> System.out.printf("%c,", c));
		string.chars().mapToObj(c -> "" + (char)c) //conversion to Stream<String>
		.forEach(s -> System.out.print(s + ","));
	}
  @Test
  	void splittingStringArray() {
	  String[] strings = {"Hello", "World"}
	  //output: H,e,l,l,o,W,o,r,l,d
  }
  
}
