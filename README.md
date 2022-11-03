**Задача 3.1.4**
Задание:
1. Изменить страницы вашего приложения в соответствии со скриншотами, используя Bootstrap
2. Ссылки: https://drive.google.com/file/d/1g32R1BmNASW5MRcLtwJWTn6ZROTMOVGJ/
   https://bootstrap-4.ru/docs/4.3.1/getting-started/introduction/

Ход решения:
1. Меняем авторизацию пользователя с имени на e-mail:
- Добавляем .usernameParameter("email") в WebSecurityConfigurerAdapter; 
- Изменяем параметр поиска в методе loadUserByUsername() в MyUserDetailsService; 
2. Дописываем возможность выбора роли в формах Edit и NewUser;
3. Прикручиваем-подкручиваем представления и контроллеры согласно приложенных скриншотов.

Замечания 02/11/2022:
- закомментированный код и неиспользуемые классы - устранено;
- методы equals(), hasCode() переопределены в models/User, models/Role.