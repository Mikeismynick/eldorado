# Test task

Реализовать веб приложение c помощью Spring:
    
1. Веб форма, которая на вход получает xml файл вид:

        <customers>
        		...
        		<customer>
        			<id>233658</id>
        			<name>Игорь Владимирович</name>
        			<orders>
        				...
        				<order>
        					<id>233658</id>
        					<positions>
        						...
        						<position>
        							<id>233658</id>
        							<price>30.0</price>
        							<count>5</count>
        						</position>
        						...
        					</positions>
        				</order>
        				...
        			</orders>
        		</customer>
        		...
        </customers>

2. Написать xsd схему по данному xml  
3. После загрузки xml на форму на сервере должно пройти расчеты и вывести:  
  3.1. Сумму всех заказов  
  3.2. Клиента с максимальной суммой заказов  
  3.3. Сумму максимального заказа  
  3.4. Сумму минимального заказа  
  3.5. Количество заказов  
  3.6. Средняя сумма заказа  
4. Реализовать многопоточный парсер xml  
5. Вывести клиентов с суммой по заказам больше N, где N настраиваемое число (поле в форме)  
_4, 5 - по желанию_  

** Notes: **  
XML для презентации `dao\src\main\resources\presentationXml.xml`  

** Screenshots: **  
веб форма с результатами парсинга, в том числе фильтр клиентов по сумме:  
![1.png](https://cloud.githubusercontent.com/assets/8762346/15284283/0c848d3a-1b59-11e6-9772-b3641664a83f.png)

**Kovarnev Michael**  
Training getJavaJob,  
http://www.getjavajob.com