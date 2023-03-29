# Engeto Java Academy - Project RESTAURANT
### The first project for Engeto Java Academy
---
## **Project description**
This project is designed to be part of the application backend for the 
terminals controlled by the waiters and chefs for restaurant. Recording 
pending and completed orders, the system also provides information for
accounting.
The whole evidence is stored in 3 txt files: Dishes.txt, Menu.txt, Orders.txt
So you can load all the dishes and orders you created previously.
You can also just start the program having txt files empty and create your dishes and orders and save them into txt files.
## **Preview of data stored in txt files**
Dishes.txt:
```
Kuřecí řízek obalovaný 150 g;156;20;[kureci_rizek_obalovany_1, kureci_rizek_obalovany_2];MAIN_DISH
Hranolky 150 g;50;15;[Hranolky_1, Hranolky_2, Hranolky_3];SIDE
Pstruh na víně 200 g;195;35;[Pstruh_na_vine_1, Pstruh_na_vine_2, Pstruh_na_vine_3];MAIN_DISH
Česnečka;65;5;[Cesnecka_1];SOUP
Jablečný závin 110 g;95;10;[blank];DESSERT
Guláš 150 g;145;25;[Gulas_1, Gulas_2];MAIN_DISH
```
Menu.txt:
```
Kuřecí řízek obalovaný 150 g;156;20;[kureci_rizek_obalovany_1, kureci_rizek_obalovany_2];MAIN_DISH
Pstruh na víně 200 g;195;35;[Pstruh_na_vine_1, Pstruh_na_vine_2, Pstruh_na_vine_3];MAIN_DISH
Guláš 150 g;145;25;[Gulas_1, Gulas_2];MAIN_DISH 
```
Orders:
```
15;1;Tom;Kuřecí řízek obalovaný 150 g;156;20;[kureci_rizek_obalovany_1, kureci_rizek_obalovany_2];MAIN_DISH;2;13:45;14:05;
15;1;Tom;Pstruh na víně 200 g;195;35;[Pstruh_na_vine_1, Pstruh_na_vine_2, Pstruh_na_vine_3];MAIN_DISH;2;13:55;00:00;
15;1;Tom;Guláš 150 g;145;25;[Gulas_1, Gulas_2];MAIN_DISH;3;13:58;14:35;
2;2;Lenka;Pstruh na víně 200 g;195;35;[Pstruh_na_vine_1, Pstruh_na_vine_2, Pstruh_na_vine_3];MAIN_DISH;5;14:30;00:00;
1;1;Tom;Pstruh na víně 200 g;195;35;[Pstruh_na_vine_1, Pstruh_na_vine_2, Pstruh_na_vine_3];MAIN_DISH;5;12:30;00:00;
1;1;Tom;Pstruh na víně 200 g;195;35;[Pstruh_na_vine_1, Pstruh_na_vine_2, Pstruh_na_vine_3];MAIN_DISH;5;11:45;00:00;
```
### Statistics preview example:
Today's menu is: 
```
Kuřecí řízek obalovaný 150 g
Pstruh na víně 200 g
Guláš 150 g
```
Number of all orders:
```
6
```
Number of currently open ( not finished ) orders:
```
4
```
List of currently open orders:
```
Currently open orders:
2. Pstruh na víně 200 g 2x (195Kč):	13:55-00:00	číšník č. 1
4. Pstruh na víně 200 g 5x (195Kč):	14:30-00:00	číšník č. 2
5. Pstruh na víně 200 g 5x (195Kč):	12:30-00:00	číšník č. 1
6. Pstruh na víně 200 g 5x (195Kč):	11:45-00:00	číšník č. 1
```
Sort orders by waiter:
```
1. Kuřecí řízek obalovaný 150 g 2x (156Kč):	13:45-14:05	číšník č. 1
2. Pstruh na víně 200 g 2x (195Kč):	13:55-00:00	číšník č. 1
3. Guláš 150 g 3x (145Kč):	13:58-14:35	číšník č. 1
5. Pstruh na víně 200 g 5x (195Kč):	12:30-00:00	číšník č. 1
6. Pstruh na víně 200 g 5x (195Kč):	11:45-00:00	číšník č. 1
4. Pstruh na víně 200 g 5x (195Kč):	14:30-00:00	číšník č. 2
```
Sort orders by ordered time:
```
6. Pstruh na víně 200 g 5x (195Kč):	11:45-00:00	číšník č. 1
5. Pstruh na víně 200 g 5x (195Kč):	12:30-00:00	číšník č. 1
1. Kuřecí řízek obalovaný 150 g 2x (156Kč):	13:45-14:05	číšník č. 1
2. Pstruh na víně 200 g 2x (195Kč):	13:55-00:00	číšník č. 1
3. Guláš 150 g 3x (145Kč):	13:58-14:35	číšník č. 1
4. Pstruh na víně 200 g 5x (195Kč):	14:30-00:00	číšník č. 2
```
Total price for all orders for each waiter:
```
Lenka Waiter number_2 : 975,- Kč
Tom Waiter number_1 : 3087,- Kč
```
Number of orders for each waiter:
```
Lenka Waiter number_2 : 1
Tom Waiter number_1 : 5
```
Average process time for all orders in specific time frame ( 8:00 - 16:00 ):
```
30
```
Dishes that were ordered during day:
```
Kuřecí řízek obalovaný 150 g
Guláš 150 g
Pstruh na víně 200 g
```
Orders for specific table (example table 15)::
```
** Orders for table no. 15 **
****

1. Kuřecí řízek obalovaný 150 g 2x (156Kč):	13:45-14:05	číšník č. 1
2. Pstruh na víně 200 g 2x (195Kč):	13:55-00:00	číšník č. 1
3. Guláš 150 g 3x (145Kč):	13:58-14:35	číšník č. 1

******
Notes for table 15 :
Great meal and Tom was supper friendly
```
