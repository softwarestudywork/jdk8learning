package com.jdk.features.main;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.jdk.features.beans.Product;


public class Jdk8StreamProgramming {
	
	
	public static void main(String args[]) {
		streamOperation();
	}
	
	public static void streamCreation() {
		
		/*
		 * ####Stream Creation####
		 * There are many ways to create a stream instance of different sources. 
		 * Once created, the instance will not modify its source, therefore allowing 
		 * the creation of multiple instances from a single source.
		 */
		
		
		/*
		 * Empty Stream creation
		 * The empty() method should be used in case of a creation of an empty stream:
		 * Its often the case that the empty() method is used upon creation to avoid returning null for streams with no element:
		 * 
		 */
		
		
		Stream<String> emptyStream = Stream.empty();
		
		/*
		 * STREAM OF COLLECTION
		 * Stream can also be created of any type of Collection (Collection, List, Set):
		 */
		
		Stream<String> alphabateStream = Arrays.asList("a","b","c","d").stream();
		
		//---OR---
		
		Collection<String> alphabateCollection = Arrays.asList("a","b","c","d");
		alphabateStream = alphabateCollection.stream();
		
		
		/*
		 * CREATING STREAM USING of operator
		 */
		alphabateStream = Stream.of("a","b","c","d");
		
		
		
		
		/*
		 * CREATING STREAM USING EXISTING ARRAY
		 */
		
		
		String[] strAlphaArray = {"a","b","c","d,e,fg,h,i,j,k,l,m,n,o,p,q,r,s,t"};
		alphabateStream = Arrays.stream(strAlphaArray);
		alphabateStream = Arrays.stream(strAlphaArray, 2,7);
	
		
		/*
		 * STREAM CAN BE CREATED USING BUILDER (Stream.buider())
		 * You can create stream using builder method of stream but in that case you have
		 * to specify type of stream to builder method
		 */
		
		alphabateStream = Stream.<String>builder().add("").add("").add("").build();
		Stream<Integer> streamOfInteger = Stream.<Integer>builder().add(1).add(10).add(100).build();
		
		//--------------OR--------------------
		
		//ToDo://
		
		/*
		 * 
		 * CREATING STREAM USING Stream.generate() method
         *
		 *The generate() method accepts a Supplier<T> for element generation. As the resulting stream is infinite, 
		 *developer should specify the desired size or the generate() method will work until it reaches the memory limit
		 */
		
		Stream<String> strStream1 = Stream.generate(()->"Element").limit(10);
		//The code above creates a sequence of ten strings with the value – “element”.
		
		Stream<Integer> intStream = Stream.generate(()->0).limit(10);
		//The code above creates a sequence of ten integers with the value – 0.
		
		
		
		/* CREATING STREAM WITH Stream.iterate() method
		 * Another way of creating an infinite stream is by using the iterate() method:
		 * 
		 * The first element of the resulting stream is a first parameter of the iterate() method. 
		 * For creating every following element the specified function is applied to the previous element.
		 * In the example below the second element will be 2.
		 * 
		 */
		Stream<Integer> streamInt2 = Stream.iterate(1, (n)->n++).limit(10);
		
		
		/*
		 * CREATING STREAM OF PRIMITIVE
		 * 
		 * Java 8 offers a possibility to create streams out of three primitive types: int, long and double. 
		 * As Stream<T> is a generic interface and there is no way to use primitives as a type parameter with generics, 
		 * Three new special interfaces were created: IntStream, LongStream, DoubleStream.
		 * Using the new interfaces alleviates unnecessary auto-boxing allows increased productivity:
		 */
		
		IntStream intStream3 = IntStream.range(1, 3);
		IntStream intStream4 = IntStream.rangeClosed(1, 3);
		
		
		/*
		 * The range(int startInclusive, int endExclusive) method creates an ordered stream from 
		 * the first parameter to the second parameter. It increments the value of subsequent elements with the step equal to 1. 
		 * The result doesn't include the last parameter, it is just an upper bound of the sequence.
		 * The rangeClosed(int startInclusive, int endInclusive) method does the same with only one difference
		 *  – the second element is included. These two methods can be used to generate any of the three types of streams of primitives.
		 *  Since Java 8 the Random class provides a wide range of methods for generation streams of primitives. For example, 
		 *  the following code creates a DoubleStream, which has three elements:
		 */
		
		/*
		 * CREATING STREAM USING Random class
		 * In the following stream will have 4 element 5 will be exclusive
		 */
		
		Random random = new Random();
		DoubleStream doubleStream = random.doubles(5);
		
		
		/*
		 * Creating Stream of Chars from String
		 */
		IntStream intStream5 = "abcdefghijk".chars();
		
		
		
		
		/*  Stream of File
		 * Stream of File Java NIO class Files allows to generate a Stream<String> of a
		 * text file through the lines() method. Every line of the text becomes an
		 * element of the stream:
		 */

		
		try {
			
			Path path = Paths.get("C:\\file.txt");
			Stream<String> streamOfStrings = Files.lines(path);
			
			@SuppressWarnings("resource")
			Stream<String> streamWithCharset = 
			  Files.lines(path, Charset.forName("UTF-8"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void streamOperation() {
		
		/*
		 * This brings us up to the rule: intermediate operations which reduce the 
		 * size of the stream should be placed before operations which are applying to 
		 * each element. So, keep such methods as skip(), filter(), distinct() at the top of your stream pipeline.
		 */
		
		/*
		 * Stream Reduction
		 * The API has many terminal operations which aggregate a stream to a 
		 * type or to a primitive, for example, count(), max(), min(), sum(),
		 *  but these operations work according to the predefined implementation. 
		 *  And what if a developer needs to customize a Stream's reduction mechanism?
		 *   There are two methods which allow to do this – the reduce() 
		 *   and the collect() methods.
		 */
		
		
		
		
		List<Product> productList = getProducts();
		
		
		/*
		 * Getting names of products from list of products. 
		 * 
		 * First we get Stream of product from productList list. Afterwards
		 * we call map method on this stream. map method gives new stream of 
		 * String element type because we specify to map that we want each element
		 * to be string representing name of product. Now we have newly
		 * generated new stream. From this stream of string we create list of 
		 * String using collect() method. This collect method perform terminal
		 * operation and gives us result as List of product name. Here we say we want List as result
		 * by specifying Collectors.toList() as argument to collect(Collectors.toList()) method 
		 */
		List<String> productNames= productList.stream().map(Product::getName)
						.collect(Collectors.toList());
		
		System.out.println("Looping through the names of product: ");
		
		/*
		 * Looping through productName list element and printing them
		 */
		productNames.forEach(name->System.out.printf("%s ", name));
		
		System.out.println();
		
		List<String> productNamesPrices = productList.stream()
						.map((product)->product.getName() + "=" + product.getPrice())
						.collect(Collectors.toList());
		
		
		System.out.print("Looping through the pair of product name and price: ");
		
		/*
		 * Looping through productNamesPrices list element and printing them
		 */
		productNamesPrices.forEach(name->System.out.printf("%s ", name));
		
		System.out.println();
		
		
		String CompanyNamesString = productList.stream().map((element)->element.getCompany())
						.collect(Collectors.joining(", ", "(", ")"));
		

		System.out.print("String representation of ProductList list: " + CompanyNamesString);
		
		Double totalPrice = productList.stream()
						.collect(Collectors.summingDouble(Product::getPrice));
		
		System.out.println("Total amount of all product in Product List: " + totalPrice);
		
		Double averagePrice = productList.stream()
					.collect(Collectors.averagingDouble(Product::getPrice));
		
		System.out.println("Average price of all product in Product List: " + averagePrice);
		
		
		DoubleSummaryStatistics doubleSummaryStatistics = productList.stream()
					.collect(Collectors.summarizingDouble(Product::getPrice));
		
		StringBuffer strBuff = new StringBuffer();
		strBuff.append("{Max Price: ").append(doubleSummaryStatistics.getMax())
			.append("\nMin Price: ").append(doubleSummaryStatistics.getMin())
			.append("\nAverage Price: ").append(doubleSummaryStatistics.getAverage())
			.append("\nTotal Product List Cost: ").append(doubleSummaryStatistics.getSum())
			.append("\nTotal number of products: ").append(doubleSummaryStatistics.getCount())
			.append("}");
			
		System.out.println(strBuff.toString());
		
		Map<Double, List<Product>> productPriceGroup = productList.stream()
				.collect(Collectors.groupingBy(Product::getPrice));
		
		System.out.println("\n{");
		productPriceGroup.forEach((key, value)->{
			System.out.print(key + " : ");
			value.forEach(element->System.out.print(element + " "));
			System.out.println();
		});
		System.out.println("}");
		
		Map<Boolean, List<Product>> productPricePartitioned = productList.stream()
				.collect(Collectors.partitioningBy(product->product.getWarrantyInDays() >365));
		
		System.out.println("\n{");
		productPricePartitioned.forEach((key,value)->{
			System.out.print(key + " : " );
			value.forEach(element->System.out.print(element + " "));
			System.out.println();
		});
		System.out.println("}");
		
		/*
		 * Getting unmodifiable Set from Product List
		 */
		
		Set<Product> productSet = productList.stream()
				.collect(Collectors
						.collectingAndThen(Collectors.toSet(), Collections::unmodifiableSet));
		
		/*
		 * Getting unmodifiable List from List of String
		 */
		List<String> namesList = Arrays.asList("Farhan","Lubna","Ahzam","Ibrahim","Fetus","ok");
		
		List<String> unmodifableList = namesList.stream().collect(
					Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
	}
	
	
	private static List<Product> getProducts() {
		
		return Arrays.asList(
				new Product(1001L,"Car", "Toyota", 876000D, 700),
				new Product(1002L,"Bike", "Honda", 120000D, 700),
				new Product(1003L,"Laptop", "Lenevo", 47000D, 365),
				new Product(1004L,"Mobile", "OPPO", 25000D, 365),
				new Product(1005L,"Television", "Relience", 18000D, 300),
				new Product(1006L,"Wall Clock", "China Made", 450D, 0)
		);
		
	}

}
