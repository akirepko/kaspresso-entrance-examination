package ru.webrelab.kie.cerealstorage

import kotlin.math.min

class CerealStorageImpl(
    override val containerCapacity: Float,
    override val storageCapacity: Float
) : CerealStorage {

    /**
     * Блок инициализации класса.
     * Выполняется сразу при создании объекта
     */
    init {
        require(containerCapacity >= 0) {
            "Ёмкость контейнера не может быть отрицательной"
        }
        require(storageCapacity >= containerCapacity) {
            "Ёмкость хранилища не должна быть меньше ёмкости одного контейнера"
        }
    }

    private val storage = mutableMapOf<Cereal, Float>()

    /**
     * Добавляет крупу к существующему контейнеру соответствующего типа, либо добавляет новый контейнер
     * если его ещё не было в хранилище и добавляет в него предоставленную крупу.
     * @param cereal крупа для добавления в контейнер
     * @param amount количество добавляемой крупы
     * @return количество оставшейся крупы если контейнер заполнился
     * @throws IllegalArgumentException если передано отрицательное значение
     * @throws IllegalStateException если хранилище не позволяет разместить ещё один контейнер для новой крупы
     */
    override fun addCereal(cereal: Cereal, amount: Float): Float {
        require(amount >= 0) {
            "Крупы не  должно быть меньше нуля"
        }
        checkStorageCapacity(cereal)
        val currentAmount = storage.getOrDefault(cereal, 0f)
        val amountForAdding = min(getSpace(cereal), amount)
        storage[cereal] = currentAmount + amountForAdding
        return amount - amountForAdding

    }

    /**
     * Вынимает крупу из контейнера (после этого в контейнере крупы должно стать меньше)
     * @param cereal крупа, которую нужно взять из контейнера
     * @param amount количество крупы
     * @return количество полученной крупы или остаток, если в контейнере было меньше
     * @throws IllegalArgumentException если передано отрицательное значение
     */
    override fun getCereal(cereal: Cereal, amount: Float): Float {
        require(amount >= 0) {
            "Крупы не  должно быть меньше нуля"
        }
        val currentAmount = getAmount(cereal)
        return if (currentAmount >= amount) {
            storage[cereal] = currentAmount - amount
            amount
        } else {
            storage[cereal] = 0f
            currentAmount
        }
    }

    /**
     * @param cereal уничтожает пустой контейнер
     * @return true если контейнер уничтожен и false если контейнер не пуст
     */
    override fun removeContainer(cereal: Cereal): Boolean {
        return if (storage.isEmpty()) {
            storage.clear()
            true
        } else {
            false
        }
    }

    /**
     * @param cereal крупа, количество которой нужно узнать
     * @return количество крупы, которое хранится в контейнере
     */
    override fun getAmount(cereal: Cereal): Float {
        return storage.getOrDefault(cereal, 0f)
    }

    override fun getSpace(cereal: Cereal): Float {
        return containerCapacity - getAmount(cereal)
    }

    /**
     * @return текстовое представление
     */
    override fun toString(): String {
        return this.storage.map { "Крупа: ${it.key} у нас столько крупы ${it.value}" }.joinToString(", ")
    }

    private fun checkStorageCapacity(cereal: Cereal) {
        if (storage.contains(cereal)) return
        check(storageCapacity >= (storage.size + 1) * containerCapacity) {
            "Недостаточно места в хранилище для нового контейнера"
        }
    }
}
