## Склад крупы. Реализация функциональности и покрытие unit тестами JUnit5
* Заведи аккаунт на GitHub если его ещё нет.
* Сделай форк этого репозитория в свой.
* Клонируй на свой компьютер проект из своего форка (не из моего репозитория).
* Рассмотри интерфейс CerealStorage, он содержит подробное описание функциональности будущего склада.
* Дополни класс CerealStorageImpl недостающими методами.
* Перейди в тестовый класс для CerealStorageImpl. Для выбранного метода напиши набор unit тестов, которые будут покрывать те требования к методу, что перечислены в документации к методу интерфейса.
* После написания тестов к методу напиши его реализацию и выполни тесты. Если тесты найдут ошибку - исправь.
* Реализуй тесты на все методы и работающий код для всех методов.
* В этом упражнении старайся придерживаться подхода Test Driven Development - сначала тесты, потом реализация. Но это не является фактором засчитывания работы.
* Обратите внимание при сравнении двух дробных значений: рассчётное значение может быть 1.10038 вместо 1.1 - это особенность расчёта значений с плавающей точкой. Чтобы такие значения сравнить нужно использовать проверку на равенство с дельтой, которая передаётся третьим аргументом в assertEquals. Для дельты достаточно значения 0.01

## Метрики оценивания работы
* Пиши код с отключенным AI помощником. Можешь спрашивать у GPT как сделать какую-то конкретную вещь, но не поручай ему решение задачи. Этот тест нужен для того, что бы ты сам понял, сможешь ли ты учиться на курсе и своевременно делать работу. Иначе просто отнимешь время у себя и у меня.
* Функциональность всех методов должна быть реализована.
* Тесты должны покрывать все требования перечисленные к методам интерфейса.
* Все тесты должны быть зелёными.
* Код должен быть отформатирован средой разработки. Избавься от лишних пробелов, не допускай длинных строк.
* Старайся использовать все возможности Kotlin, которые позволяют сократить количество кода.
* Код не должен дублироваться. Дублирующимся будет считаться два и более аналогичных блока кода размером в 4 строки и более.
