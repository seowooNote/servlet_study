package com.study.servlet_study.test;

import java.util.function.Consumer;

import com.study.servlet_study.entity.Author;

// Lamda 클래스화  
class Print<T> implements Consumer<T> {
	@Override
	public void accept(T t) {
		System.out.println(t);
	}
}


public class LamdaMain {

	// Lamda 복습
	public static void main(String[] args) {
		// Lamda 클래스화로 객체 생성
		Consumer<Author> print0 = new Print<Author>();
		print0.accept(Author.builder().authorId(1).authorName("홍길동1").build());
		
		// 익명함수로 생성과 동시에 재할당
		Consumer<Author> print1 = new Consumer<Author>() {
			@Override
			public void accept(Author author) {
				System.out.println(author);
			}
		};
		print1.accept(Author.builder().authorId(2).authorName("홍길동2").build());
		
		// Lamda 사용
		Consumer<Author> print2 = (author) -> System.out.println(author);
		print2.accept(Author.builder().authorId(3).authorName("홍길동3").build());
		
		forEach(print2);
		forEach(author -> {
			System.out.println("<<< test >>>");
			System.out.println(author);
		});
	}
	
	public static void forEach(Consumer<Author> action) {
		action.accept(Author.builder().authorId(4).authorName("홍길동4").build());
	}

}
