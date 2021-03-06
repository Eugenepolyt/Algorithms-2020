@file:Suppress("UNUSED_PARAMETER")

package lesson7

/**
 * Наибольшая общая подпоследовательность.
 * Средняя
 *
 * Дано две строки, например "nematode knowledge" и "empty bottle".
 * Найти их самую длинную общую подпоследовательность -- в примере это "emt ole".
 * Подпоследовательность отличается от подстроки тем, что её символы не обязаны идти подряд
 * (но по-прежнему должны быть расположены в исходной строке в том же порядке).
 * Если общей подпоследовательности нет, вернуть пустую строку.
 * Если есть несколько самых длинных общих подпоследовательностей, вернуть любую из них.
 * При сравнении подстрок, регистр символов *имеет* значение.
 */
// Трудоемкость O(l1 * l2)
// Ресурсоемкость O(l1 * l2)
fun longestCommonSubSequence(first: String, second: String): String {
    val l1: Int = first.length
    val l2: Int = second.length

    val arr = Array(l1 + 1) { IntArray(l2 + 1) }

    for (i in l1 - 1 downTo 0) {
        for (j in l2 - 1 downTo 0) {
            if (first[i] == second[j]) arr[i][j] = arr[i + 1][j + 1] + 1 else arr[i][j] =
                arr[i + 1][j].coerceAtLeast(arr[i][j + 1])
        }
    }

    var i = 0
    var j = 0
    val sb = StringBuffer()
    while (i < l1 && j < l2) {
        when {
            first[i] == second[j] -> {
                sb.append(first[i])
                i++
                j++
            }
            arr[i + 1][j] >= arr[i][j + 1] -> i++
            else -> j++
        }
    }
    return sb.toString()
}

/**
 * Наибольшая возрастающая подпоследовательность
 * Сложная
 *
 * Дан список целых чисел, например, [2 8 5 9 12 6].
 * Найти в нём самую длинную возрастающую подпоследовательность.
 * Элементы подпоследовательности не обязаны идти подряд,
 * но должны быть расположены в исходном списке в том же порядке.
 * Если самых длинных возрастающих подпоследовательностей несколько (как в примере),
 * то вернуть ту, в которой числа расположены раньше (приоритет имеют первые числа).  --------(*)----------
 * В примере ответами являются 2, 8, 9, 12 или 2, 5, 9, 12 -- выбираем первую из них.
 */
/* Алгоритм решения взят с http://e-maxx.ru/algo/longest_increasing_subseq_log и изменен под условие (*)
//Трудоёмкость - O(N*logN)
//Ресурсоемкость O(N)
*/

fun upperBound(ar: MutableList<Int>, k: Int): Int {
    var s = 0
    var e = ar.size - 1
    while (s != e) {
        val mid = s + e shr 1
        if (ar[mid] >= k) {
            s = mid + 1
        } else {
            e = mid
        }
    }
    return if (s == ar.size) {
        -1
    } else s
}

fun longestIncreasingSubSequence(list: List<Int>): List<Int> {
    if (list.isEmpty()) return list

    val d = MutableList(list.size + 1) { Int.MIN_VALUE }

    d[0] = Int.MAX_VALUE

    val p = MutableList(list.size + 1) { 0 }
    p[0] = -1
    val ancestor = MutableList(list.size) { 0 }
    var length = 0

    for (i in list.size - 1 downTo 0) {
        val j = upperBound(d, list[i])
        if (d[j - 1] > list[i] && d[j] < list[i]) {
            d[j] = list[i]
            p[j] = i
            ancestor[i] = p[j - 1]
            length = maxOf(length, j)
        }
    }

    if (length == 1) return listOf(list.first())
    val result: MutableList<Int> = mutableListOf()
    var pos = p[length]
    while (pos != -1) {
        result.add(list[pos])
        pos = ancestor[pos]
    }
    return result
}


/**
 * Самый короткий маршрут на прямоугольном поле.
 * Средняя
 *
 * В файле с именем inputName задано прямоугольное поле:
 *
 * 0 2 3 2 4 1
 * 1 5 3 4 6 2
 * 2 6 2 5 1 3
 * 1 4 3 2 6 2
 * 4 2 3 1 5 0
 *
 * Можно совершать шаги длиной в одну клетку вправо, вниз или по диагонали вправо-вниз.
 * В каждой клетке записано некоторое натуральное число или нуль.
 * Необходимо попасть из верхней левой клетки в правую нижнюю.
 * Вес маршрута вычисляется как сумма чисел со всех посещенных клеток.
 * Необходимо найти маршрут с минимальным весом и вернуть этот минимальный вес.
 *
 * Здесь ответ 2 + 3 + 4 + 1 + 2 = 12
 */
fun shortestPathOnField(inputName: String): Int {
    TODO()
}

// Задачу "Максимальное независимое множество вершин в графе без циклов"
// смотрите в уроке 5