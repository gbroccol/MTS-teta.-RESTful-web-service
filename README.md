# RESTful-Web-Service

## Запуск:
1. http://127.0.0.1:8080/course

## Технический стек:
- [x] **HTML, CSS, SCSS**
- [x] **Java**
- [x] **Spring**
- [x] **MVC** (http)
- [ ] **PostgreSQL**
- [ ] **Hibernate/JPA** - взаимодействие с БД
- [ ] **Spring security** - авторизация и аутентификация
- [ ] **minIO** для хранения файлов видео
- [ ] **clickhouse** (собирать и логировать статистику по движениям по сайту)

<!-- - [x] **Spring Core**
- [x] **Spring Boot** -->


## Цель
разработать образовательную платформу (как, например, Coursera, edX, Udacity, etc.)

## Задачи:

### На платформе будут доступны три группы пользователей с разными use-case'ами взаимодействия с платформой:

Возможности для взаимодействия с платформой расширяются от *ученика* к *администратору*, причем возможности каждой из групп включают в себя возможности предыдущей.

- [x] Ученики
- [ ] Преподаватели
- [ ] Администраторы

### **Глоссарий**

- [ ] Каждый **курс** - это последовательность упражнений. Также у курса есть описание (про что этот курс, оглавление (список упражнений), вступительное видео).
- [ ] Каждое **упражнение** - текст для прочтения / видео / тест. Интерпретатор кода с проверкой решения задачи.
- [ ] **Библиотека** — набор курсов ученика, которые он положил в закладки; студент не обязан проходить курс, находящийся в его библиотеке.

### **Ученик**

- [ ] Регистрируется на платформе.
- [x] Может пользоваться поиском по курсам.
- [ ] Добавляет курсы в свою библиотеку.
- [ ] Просматривает курс (описание, оглавление, вступительное видео).
- [ ] Статистика прохождения студентом его курсов из библиотеки.
- [ ] Отправляет сообщения преподавателю чтобы спросить что-то, etc.
- [ ] Получает сертификат о прохождении после завершения курса.

### **Преподаватель**

- [ ] Регистрируется как ученик, по запросу верифицируется администратором как преподаватель.
- [ ] Редактирует курс — создает его, описывает его структуру (создает/удаляет/редактирует упражнения).
- [ ] Смотрит информацию (статистику) о прохождении курса: какие ученики прошли курс, на сколько они его прошли, как именно — то есть такая статистика должна собираться. Каждый преподаватель должен иметь доступ только к своим курсам
- [ ] Отправляет сообщения ученикам в качестве обратной связи.

### **Администратор**

- [ ] Может удалять контент, выкладываемый преподователями и генерируемый учениками. (например, в случае нарушения правил платформы или законодательства страны).
- [ ] Может блокировать (и разблокировывать), удалять пользователей — как учеников, так и преподавателей (по тем же причинам).
- [ ] Подтверждает статус преподователей.


## Useful links
1. Архитектура приложения
   * ["Чистая архитектура" Роберт Мартин](https://vk.com/doc44301783_469642449?hash=2e7f405cf8d7e96a43&dl=5af840b9982acd79a9)
   * ["Информационная архитектура"]()
   * ["Архитектура программного обеспечения на практике"](https://www.ozon.ru/context/detail/id/2456415/)
2. Spring
3. MVC
4. IDE
   * [Антон Архипов — Эффективная работа с IDE(A)](https://www.youtube.com/watch?v=_rj7dx6c5R8)

