Руководство к проекту
1. Настроить application.properties

![1](https://github.com/ilyakharenkov/egar-repository/assets/100045092/79a89722-03d2-4cb9-95c0-ce647c4c636b)

2. Запустить проект для создания таблиц БД

3. Инициализировать БД data.sql

![2](https://github.com/ilyakharenkov/egar-repository/assets/100045092/fce8e350-7e9f-4265-a762-5501e5891d68)


4. Поменять настройки application.properties, перезапустить проект

![3](https://github.com/ilyakharenkov/egar-repository/assets/100045092/583c2acc-2fc3-44a9-8cf5-7c431c1d7624)


5. На начальной странице, не авторизированный пользователь может только просматривать ассортимент

![4](https://github.com/ilyakharenkov/egar-repository/assets/100045092/26da1753-2edc-4f57-8968-351b43796177)


6. Для авторизации можно зарегестрироваться или воспользоваться существующими пользователями

Зайти как админ

      Логин: admin
      Пароль: 12345  
Зайти как пользователи

      Логин: client1
      Пароль: 12345    
      
      Логин: client2
      Пароль: 12345
      
![5](https://github.com/ilyakharenkov/egar-repository/assets/100045092/9b631d71-185a-4557-9e64-2e3260492be6)


7. Авторизированные пользователи с ролью USER могут арендовать инструмент и посмотреть список аренды инструмента который они уже взяли

![6](https://github.com/ilyakharenkov/egar-repository/assets/100045092/273eb193-33be-487e-8836-dad374764298)

![7](https://github.com/ilyakharenkov/egar-repository/assets/100045092/29edcbfa-1e5d-4df5-b762-8aa7a9ed4be8)

8. Пользователь с ролью ADMIN могут смотреть Выручку, Аренду, Обслуживание, Архив и после того как аренда инструмента закончилась отправить его обслуживание, Добавлять новый инструмент, Удалять.

![8](https://github.com/ilyakharenkov/egar-repository/assets/100045092/beb2064a-a044-4087-9fdb-7001d626f93f)

![9](https://github.com/ilyakharenkov/egar-repository/assets/100045092/d4600cc9-0acd-410a-aad1-88f90b7f00e6)

![10](https://github.com/ilyakharenkov/egar-repository/assets/100045092/5a262289-b9e5-46cd-85af-c64b9fe51c4c)

![11](https://github.com/ilyakharenkov/egar-repository/assets/100045092/bba340a9-3708-42bf-9424-4b218b78ad7d)


9. В разделе аренда есть сортировка

![12](https://github.com/ilyakharenkov/egar-repository/assets/100045092/0843f52f-dc2f-4d9a-bbb0-6e1b74089b43)

10. Инструмент находящийся на обслуживании

![13](https://github.com/ilyakharenkov/egar-repository/assets/100045092/c9d31eb2-822e-4459-b314-bfe0658212f3)

