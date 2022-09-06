package com.springbootmybatis.service;

public interface Stars {

        /*
            Rysuje kwadrat o podanym wymiarze n
            np. n = 2, ma zwrócić Stringa:
            **
            **
         */
        String drawSquare(int n);

        /*
            Rysuje prostokąt o wymiarach n na m
            np. n = 2, m = 3 ma zwrócić Stringa:
            **
            **
            **
         */
        String drawRectangle(int n, int m);

        /*
            Rysuje trójkąt równoramienny o wysokości równej n
            np. n = 3 ma zwrócić Stringa
            --*
            -***
            *****
         */
        String drawIsoscelesTriangle(int n);

        /*
            Rysuje diament, gdzie n oznacza wysokość diamentu
            n jest zawsze nieparzysta - w przeciwnym wypadku rzuca wyjątek IllegalArgumentException
            np. n = 5 ma zwrócić Stringa
             --*
             -***
             *****
             -***
             --*
         */
        String drawDiamond(int n);

        /*
            Rysuje trójkąt prostokątny równoramienny, gdzie n to długość ramienia
            np. dla n = 3 ma zwrócić Stringa:
            *
            **
            ***
         */
        String drawRectangleTriangle(int n);

}
