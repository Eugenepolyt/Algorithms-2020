@file:Suppress("UNUSED_PARAMETER")

package lesson2

import java.io.File

/**
 * Получение наибольшей прибыли (она же -- поиск максимального подмассива)
 * Простая
 *
 * Во входном файле с именем inputName перечислены цены на акции компании в различные (возрастающие) моменты времени
 * (каждая цена идёт с новой строки). Цена -- это целое положительное число. Пример:
 *
 * 201
 * 196
 * 190
 * 198
 * 187
 * 194
 * 193
 * 185
 *
 * Выбрать два момента времени, первый из них для покупки акций, а второй для продажи, с тем, чтобы разница
 * между ценой продажи и ценой покупки была максимально большой. Второй момент должен быть раньше первого.
 * Вернуть пару из двух моментов.
 * Каждый момент обозначается целым числом -- номер строки во входном файле, нумерация с единицы.
 * Например, для приведённого выше файла результат должен быть Pair(3, 4)
 *
 * В случае обнаружения неверного формата файла бросить любое исключение.
 */
fun optimizeBuyAndSell(inputName: String): Pair<Int, Int> {
    val inputList = File(inputName).readLines()
    inputList.forEach { require(it.matches(Regex("""\d+""")) && it.toInt() > 0) }
    val intInputList = inputList.map { it.toInt() }
    var firstIndexResult = 0
    var secondIndexResult = 0
    var min = 0

    for (i in 1 until intInputList.size) {
        if (intInputList[i] < intInputList[min]) {
            min = i
        }
        if (intInputList[i] - intInputList[min] > intInputList[secondIndexResult] - intInputList[firstIndexResult]) {
            firstIndexResult = min
            secondIndexResult = i
        }
    }
    return firstIndexResult + 1 to secondIndexResult + 1

    // Трудоемкость - O(N) // Ресурсоемкость - O(N)
}

/**
 * Задача Иосифа Флафия.
 * Простая
 *
 * Образовав круг, стоят menNumber человек, пронумерованных от 1 до menNumber.
 *
 * 1 2 3
 * 8   4
 * 7 6 5
 *
 * Мы считаем от 1 до choiceInterval (например, до 5), начиная с 1-го человека по кругу.
 * Человек, на котором остановился счёт, выбывает.
 *
 * 1 2 3
 * 8   4
 * 7 6 х
 *
 * Далее счёт продолжается со следующего человека, также от 1 до choiceInterval.
 * Выбывшие при счёте пропускаются, и человек, на котором остановился счёт, выбывает.
 *
 * 1 х 3
 * 8   4
 * 7 6 Х
 *
 * Процедура повторяется, пока не останется один человек. Требуется вернуть его номер (в данном случае 3).
 *
 * 1 Х 3
 * х   4
 * 7 6 Х
 *
 * 1 Х 3
 * Х   4
 * х 6 Х
 *
 * х Х 3
 * Х   4
 * Х 6 Х
 *
 * Х Х 3
 * Х   х
 * Х 6 Х
 *
 * Х Х 3
 * Х   Х
 * Х х Х
 *
 * Общий комментарий: решение из Википедии для этой задачи принимается,
 * но приветствуется попытка решить её самостоятельно.
 */
fun josephTask(menNumber: Int, choiceInterval: Int): Int {
    TODO()
}

/**
 * Наибольшая общая подстрока.
 * Средняя
 *
 * Дано две строки, например ОБСЕРВАТОРИЯ и КОНСЕРВАТОРЫ.
 * Найти их самую длинную общую подстроку -- в примере это СЕРВАТОР.
 * Если общих подстрок нет, вернуть пустую строку.
 * При сравнении подстрок, регистр символов *имеет* значение.
 * Если имеется несколько самых длинных общих подстрок одной длины,
 * вернуть ту из них, которая встречается раньше в строке first.
 */
fun longestCommonSubstring(first: String, second: String): String {
    val firstLen = first.length
    val secondLen = second.length
    val table = List(firstLen + 1) { MutableList(secondLen + 1) { 0 } }
    var maxSub = 0
    var secondIndex = 0
    for (i in 0..firstLen) {
        for (j in 0..secondLen) {
            if (i == 0 || j == 0) {
                table[i][j] = 0
            } else if (first[i - 1] == second[j - 1]) {
                table[i][j] = table[i - 1][j - 1] + 1
                if (maxSub < table[i][j]) {
                    maxSub = table[i][j]
                    secondIndex = j
                }
            } else table[i][j] = 0
        }
    }
    if (maxSub == 0) return ""
    return second.slice(secondIndex - maxSub until secondIndex) // O(K) ?
    //Трудоёмкость - O(N*M + K)
    //Ресурсоемкость - O(N*M)
}

/**
 * Число простых чисел в интервале
 * Простая
 *
 * Рассчитать количество простых чисел в интервале от 1 до limit (включительно).
 * Если limit <= 1, вернуть результат 0.
 *
 * Справка: простым считается число, которое делится нацело только на 1 и на себя.
 * Единица простым числом не считается.
 */
fun calcPrimesNumber(limit: Int): Int {
    TODO()
}
