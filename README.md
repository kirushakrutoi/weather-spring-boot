# Проект “Погода”

Проект был сделан по тз, но с использованием Spring: </br> https://zhukovsd.github.io/java-backend-learning-course/Projects/WeatherViewer/

Веб-приложение для просмотра текущей погоды. Пользователь может зарегистрироваться и добавить в коллекцию один или несколько локаций (городов, сёл, других пунктов), после чего главная страница приложения начинает отображать список локаций с их текущей погодой.

<h2>Функционал:</h2>

- Работа с пользователем
    - Регистрация
    - Авторизация
- Работа с локациями
    - Поиск
    - Добавление
    - Просмтор подробной информации о локакации
    - Удаление из списка

<h2>Используемые технологии</h2>
<ul>
  <li>Java</li>
  <li>Spring Boot</li>
  <li>Spring Web</li>
  <li>Spring Security</li>
  <li>Spring Data</li>
  <li>Thymeleaf</li>
  <li>PostgreSQL</li>
  <li>Hibernate</li>
  <li>HTML/CSS</li>
  <li>Bootstrap</li>
  <li>Maven</li>
  <li>Docker</li>
  <li>Docker compose</li>
  <li>Git</li>
</ul>

<h2>Деплой</h2>

<h3>Условие для запуска</h3>
<ul>
  <li> Установленый Docker, Docker-Сompose
</ul>

<h3>Запуск</h3>

<ul>
  <li> Скопировать репозиторий
  <li> В файле application.yml утсановить свой токен с сайта https://openweathermap.org/
  <li> Зайти в папку репозитория: cd Weather
  <li> Запустуть фаил docker-compose.yml: docker compose up</li>
</ul>


<h2>Интерфейс приложения</h2>
<h3>Главная страница для не авторизированных пользователей: /auth/registration</h3>

![alt-text](/img/sign_up_page.png)

<h3>Главная страница для авторизированных пользователей: /whether/mainmenu</h3>

![alt-text](/img/main_menu_page.png)

<h3>Страница поиска: /whether/search?city=Moscow</h3>

![alt-text](/img/search_page.png)

<h3>Страница с большей информацией: /whether/Brayton</h3>

![alt-text](/img/more_info_page.png)





