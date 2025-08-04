package Stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class StreamMethods {
	
	public static void main(String[] args) {
		
		
		//------for defineStream--------
		
		//  1. stream();  --> it's Use for Create Stream From Collection
		
		System.out.println("Normal Stream(); Stream Genration");
		ArrayList<String> arr= new ArrayList<String>();
		arr.add(" hi ");
		arr.add(" How Are You ");
		
		//way of traverse the stream 
		arr.stream().forEach(s -> System.out.println(s.toUpperCase()));
		/*OP: HI 
			  HOW ARE YOU */
		
		Stream<String> str= arr.stream();
		//it's another way of traverse the stream 
		str.forEach(System.out::println);
		/* OP:hi 
	     How Are You */
		
		//  2. Arrays.Stream is used for Array element  --> it's use for create Stream from Array
		
		System.out.println("Arrays.Stream(); for Stream Genration");
		int[] array= new int[2];
		array[0]=1;
		array[1]=2;
		Arrays.stream(array).forEach(s -> System.out.print(s));
		//OP: 12
		
		//3. Stream.of For Stream Generation  --> create Stream from Values
		System.out.println("Stream.of(); Stream Genration");
		Stream.of("x","y","z").forEach(System.out::print);
		//OP: xyz
		
		//4. Stream.iterate   --> it's using for loop in stream by lambda expression
		System.out.println("Stream.iterate(); Stream Genration");
		Stream.iterate(1,n -> n+1).limit(5).forEach(System.out::print);
		//OP: 12345
		
		//5. Stream.generate   --> it's create infinite stream so limit()method use is mandatory
		System.out.println("Stream.generate(); Stream Genration");
		Stream.generate(()->"Hello ").limit(3).forEach(System.out::print);
		//OP: Hello Hello Hello 
		
		//6. ParallelStream    --> it's Use for Generate ParrallelStream Which works like multiple threading
		System.out.println("ParallelStream(); Stream Genration");
		arr.parallelStream().forEach(System.out::print);
		//OP:  How Are You  hi 
		
		//------- intermediate Methods ----------
		
	
		//1.filter() --> filter is use for filter the values and by using forEach is iterate the Stream
		System.out.println("filter(); From Intermediate Methods");
		Stream.of(1,2,3,4,5,6,7,8,9,10).filter(n-> n%2 == 0).forEach(System.out::print);
		//OP: 246810
		
		List<String> names= Arrays.asList("Rahul","Samn","Krutik","Sahil","Salman");
		names.stream().filter(name -> name.startsWith("S")).forEach(System.out::println);
		/*OP:Samn
			Sahil
			Salman */
		
		
		//2.map()  --> it's use for modify the each and every element
		System.out.println("map(); From Intermediate Methods");
		List<String> name= Arrays.asList("dev ","Ditya ","daman ","Kamal ");
		name.stream().map(String::toUpperCase).forEach(System.out::print);
	    //OP: DEV DITYA DAMAN KAMAL 
		
		List<Integer> num= Arrays.asList(10,20,31,55,50);
		System.out.print("Half Of The Even Number is: ");
		num.stream().filter(n-> n%2 ==0).map(n-> n/2).forEach(n->System.out.print( n+" "));
		//OP: Half Of The Even Number is: 5 10 25 
		
		
		//3.sorted()  --> Which is use for sort any elements
		//sort in ascending
		System.out.println("sorted(); From Intermediate Methods");
		System.out.println("Ascending Order");
		Stream.of(1,5,3,4,2).sorted().forEach(System.out::print);
		//OP: Ascending Order: 12345
		
		//sort in descending
		System.out.println("Descending Order");
		Stream.of(1,5,3,4,2).sorted((a,b)-> b-a).forEach(System.out::print);
		//OP: Descending Order: 54321
		
		//4.distinct()   --> it's use for remove the duplicate elements
		System.out.println("distinct(); From Intermediate Methods");
		Stream.of("Ketan","kiran","Ketan").distinct().forEach(System.out::println);
		//OP: Ketan  kiran
		

		
		
		//5.flatMap()  -->  it's work like merge the data from multiple object / elements
		System.out.println("flatMap(); From Intermediate Methods");
		
		List<List<Integer>> list= Arrays.asList(
				Arrays.asList(1,2),
				Arrays.asList(3,4)
				);
		
		list.stream().flatMap(l->l.stream()).forEach(System.out::print);
		//OP: 
			
		//6.peek()  --> it's perform action during debugging with out braking pipe line
		System.out.println("peek(); From Intermediate Methods");
		 
		
		List<Integer> num1= Arrays.asList(1,2,3,4);
		List<Integer> result= num1.stream()
				.filter(n -> n%2==0)
				.peek(n -> System.out.println("After filter "+n + " is Even Number"))
				.map(n -> n*2)
				.peek(n-> System.out.println("After Map: "+n))
				.collect(Collectors.toList());//it's used for collect all the data and return to our list
				System.out.println(" final Result:" + result);
				/*OP:After filter 2 is Even Number
					 After Map: 4
				     After filter 4 is Even Number
					 After Map: 8
 					 final Result:[4, 8]   */
		
		//7.limit() --> put limit on loop and filter and map
		System.out.println("limit(); From Intermediate Methods");
		Stream.iterate(1,n-> n+1).limit(10).forEach(System.out::print);
		//OP: 12345678910
		
		//8. skip() --> it's use for skip the elements from beginning
		System.out.println("skip(); From Intermediate Methods");
		Stream.of(1,2,3,4,5).skip(2).forEach(System.out::print);
		//OP: 345
		
		//----------Terminal Methods------------//
		
		//1.ForEach(); --> Which is use for traverse the stream and collection it's also run the stream elements
		System.out.println("ForEach(); From Terminal Methods");
		Stream.of("Hii ", "Users ","How Are You.").forEach(System.out::print);
		//OP: Hii Users How Are You
		
		
		//2.toArray()  --> Converts Stream To An Array
		System.out.println("toArray(); From Terminal Methods");
		List<Integer> int1 = Arrays.asList(1,2,3,4);
		Integer[] array2 = int1.stream().toArray(Integer[]::new);
		System.out.println(Arrays.toString(array2));
		//OP: [1, 2, 3, 4]
		
		List<String> str1= Arrays.asList("Hi","By");
		String[] array3 = str1.stream().toArray(String[]::new);
		System.out.println(Arrays.toString(array3));
		//OP:[Hi, By]
 
		
		List<Double> db= Arrays.asList(30.1,21.35,25.12);
		Double[] array4 = db.stream().toArray(Double[]::new);
		System.out.println(Arrays.toString(array4));
		//OP: [30.1, 21.35, 25.12]
		
		
		//3.collect --> it will return data to the stream work like forEach() but for each is only use for traverse and printing perpose but collect method is use convert stream into any of the collection like "list , set, map" also it's use for joining single string 
		System.out.println("collect(); From Terminal Methods");
		List<Integer> collect = Stream.of(1,2,3,4,5).collect(Collectors.toList());
		System.out.println(collect);
		//OP: [1, 2, 3, 4, 5]
		
		List<String> names1 = Arrays.asList("John", "Jane", "Jack");
        List<String> result1 = names1.stream()
                                   .filter(n -> n.startsWith("J"))
                                   .collect(Collectors.toList()); //it's collect all the data from the streams and store inside list type of collection after that we print that list using printing statement.
        System.out.println(result1);
		//OP: [John, Jane, Jack]

		
        
		//into list
        List<String> ap= Stream.of("Stream to List Using Collect").collect(Collectors.toList());
        System.out.println(ap);//OP:[Stream to List Using Collect] 
        //into set
        Set<String> collect2 = Stream.of("Stream to Set Using Collect").collect(Collectors.toSet());
        System.out.println(collect2); //OP: [Stream to Set Using Collect]
        //into map
        Map<String, Integer>  name3=Stream.of("Heli","Dev","Mugdha","Tapan").collect(Collectors.toMap(name1 -> name1, String::length));
        System.out.println(name3); //OP: {Dev=3, Heli=4, Mugdha=6, Tapan=5}
        //joining to String
        String collect3 = Stream.of("Apple","Banana","Orange").collect(Collectors.joining(", "));
        System.out.println(collect3);//OP:  Apple, Banana, Orange
        //grouping
        List<String> names2 = Arrays.asList("John", "Jack", "Tom","John","Li");
        Map<Integer, List<String>> grouped = names2.stream()
                                                  .collect(Collectors.groupingBy(String::length));
        System.out.println(grouped); 
        //OP: {2=[Li], 3=[Tom], 4=[John, Jack, John]}
        
      //4.reduce()  --> reduce is use for reduce the element and do some task like sum ,concatenation and return to particular Data type 
        Integer reduce = Stream.of(1,2,3,4).reduce(0,Integer::sum);
        System.out.println(reduce);
        //OP: 10
		
		
        String reduce2 = Stream.of("Hi","by").reduce("",(a,b)-> a+" "+b);
        System.out.println(reduce2);
      //OP: 
		
        List<Employee> emp= Arrays.asList(
        		new Employee ("Sunny",5000),
        		new Employee("ramesh",4000)
        		);
        
        		Double reduce3 = emp.stream().map(ep -> ep.salary).reduce(0.0,Double::sum);
        		System.out.println(reduce3);
		//OP: 9000.0
		
		
       //5.max & min () --> which is use for find smallest or largest element from the stream
		int min = Stream.of(3,5,1).min(Integer::compare).get();
		System.out.println(min);
		//OP: 1
		
		int max = Stream.of(5,6,9).max(Integer::compare).get();
		System.out.println(max);
		//OP: 9
		
		//6.count() --> it is use for count the element from the stream
		long count = Stream.of(1,5,6,8,5,5,5,5,5,5,5,5).count();
		System.out.println(count);
		//OP: 12
		
		//7.anyMatch()  --> it check if any Element is match of given condition it return boolean value
		boolean anyMatch = Stream.of(1,2,3,4,5).anyMatch(n -> n>=5);
		System.out.println(anyMatch);
		//OP: true
		
		//8.allMatch()  -->it will check if all the statement is match with given condition if yes so it will return true otherwise false 
		boolean allMatch = Stream.of(40,20,64,80).allMatch(n -> n%2==0);
		System.out.println(allMatch);
		//OP: true
		
		
		//9.nonMatch()  --> it will check if all the elements is not match it return true otherwise it return false
		boolean noneMatch = Stream.of(5,6,3).noneMatch(n -> n<2);
		System.out.println(noneMatch);
		//OP: true
		
		//10.findFirst() --> it will find the first element from the stream
		Optional<Integer> first = Stream.of(5,8,6,4,32).findFirst();
		System.out.println(first.get());
		//OP: 5
		
		
		//11.findAny()  --> it will return first element when we use in normal stream but it returns any numbers when we use it into  Stream
		List<Integer> any = Arrays.asList(1,9,7,6,5);
		Optional<Integer> any2 = any.parallelStream().findAny();
		System.out.println(any2.get());
		//OP: 7(it print any number)
		
		//------Special Methods-------//
		
		//1.joining() --> concatenate all the element in single stream
		
		String collect1 = Stream.of("Ronamldo","Messi","Nymar").collect(Collectors.joining(", ","{ "," }"));
		System.out.println(collect1);
		//OP: { Ronamldo, Messi, Nymar }
		
		
		//2.grouingBy() -->it's work like SQL Group by Clause which is find group 
		
		Map<Integer, List<String>> collect7 = Stream.of("Dev","Rama","Tenali","Ram","Mugdha")
											.collect(Collectors.groupingBy(String::length));
		System.out.println(collect7);
		//OP: {3=[Dev, Ram], 4=[Rama], 6=[Tenali, Mugdha]}
		
		
		//3.partitioningBy() --> this method is divide stream elements based on condition and put in specific result like true and false
		
		Map<Boolean, List<Integer>> collect6 = Stream.of(1,2,3,4,5,6,7,8).collect(Collectors.partitioningBy(n -> n%2 ==0));
			System.out.println(collect6);
		
		//OP: {false=[1, 3, 5, 7], true=[2, 4, 6, 8]}
		
		
		//4.summarizingInt(), summarizingLong(), summarizingDouble()  --> this method is return count ,sum,avg,min ,max of the given stream element 
		
		IntSummaryStatistics collect5 = Stream.of(10,20,30,40,50).collect(Collectors.summarizingInt(n -> n));
		System.out.println(collect5);
		
		//OP: IntSummaryStatistics{count=5, sum=150, min=10, average=30.000000, max=50}
		
		//5.mapping()     -->Transforms data before collecting.
			List<String> student= Arrays.asList("Vikram","Vedha");
			Set<Integer> collect4 = student.stream().collect(Collectors.mapping(String::length, Collectors.toSet()));
			System.out.println(collect4);
			
			//op:- [5, 6]

	}

}
