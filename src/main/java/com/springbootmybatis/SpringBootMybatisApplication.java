package com.springbootmybatis;

import com.springbootmybatis.service.Stars;
import com.springbootmybatis.service.impl.Draw;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.springbootmybatis.mapper")
@SpringBootApplication
public class SpringBootMybatisApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMybatisApplication.class, args);


		Draw draw = new Draw();

		System.out.println("Square: n = 2");
		System.out.println(draw.drawSquare(2));
		System.out.println("Rectangle: n = 2, m = 3");
		System.out.println(draw.drawRectangle(2, 3));
		System.out.println("Draw Isosceles Triangle: n = 4");
		System.out.println(draw.drawIsoscelesTriangle(4));
		System.out.println("Draw Diamond: n = 5");
		System.out.println(draw.drawDiamond(5));
		System.out.println("Draw Rectangle Triangle: n = 4");
		System.out.println(draw.drawRectangleTriangle(4))   ;
	}

}
