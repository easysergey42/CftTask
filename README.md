## CFT Java test task
### Сергей Бурдэ
___

Сборка исполняемого util.jar:

1) для linux/macOS
```console
./gradlew shadowJar
```
2) для Windows
```console
./gradlew.bat shadowJar
```

util.jar будет лежать в ./build/libs

Для запуска утилиты:
```console
java -jar util.jar
```

Пример с аргументами командной строки:
```console
java -jar util.jar -o ./some/path -p sample- -s -a in1.txt in2.txt
```

Если какого-то из входных файлов не существует на диске - выведется сообщение об отсутствии этого файла на диске, при этом программа обработает остальные файлы.
