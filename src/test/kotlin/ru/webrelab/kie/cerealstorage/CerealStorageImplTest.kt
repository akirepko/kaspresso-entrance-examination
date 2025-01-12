package ru.webrelab.kie.cerealstorage

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class CerealStorageImplTest {

    private val storage = CerealStorageImpl(10f, 20f)

    @Test
    fun `should throw if containerCapacity is negative`() {
        assertThrows(IllegalArgumentException::class.java) {
            CerealStorageImpl(-4f, 10f)
        }
    }

    @Test
    fun `should throw if storageCapacity is lower than containerCapacity`() {
        assertThrows(IllegalArgumentException::class.java) {
            CerealStorageImpl(10f, 9.9f)
        }
    }

    @Test
    fun addCerealTest() = with(storage) {
        addCereal(Cereal.RICE, 2f)
        assertEquals(2f, getAmount(Cereal.RICE))
    }

    @Test
    fun addExtraCereal() = with(storage) {
        addCereal(Cereal.RICE, 2.2f)
        addCereal(Cereal.RICE, 1.3f)
        assertEquals(3.5f, getAmount(Cereal.RICE))
    }

    @Test
    fun addMultipleCereal() = with(storage) {
        addCereal(Cereal.RICE, 1.1f)
        addCereal(Cereal.PEAS, 2.7f)
        assertAll(
            { assertEquals(1.1f, getAmount(Cereal.RICE)) },
            { assertEquals(2.7f, getAmount(Cereal.PEAS)) }
        )
    }

    @Test
    fun `should return 0 if container not full`() = with(storage) {
        assertEquals(0f, addCereal(Cereal.BUCKWHEAT, 9.9f))
    }

    @Test
    fun `should return rest if added cereal is biggest than container empty space`() = with(storage) {
        assertEquals(0.1f, addCereal(Cereal.PEAS, 10.1f), 0.01f)
    }

    @Test
    fun `should throw if cereal amount is negative`(): Unit = with(storage) {
        assertThrows(IllegalArgumentException::class.java) {
            addCereal(Cereal.RICE, -1f)
        }
    }

    @Test
    fun `should throw when storage don't have space for new container`(): Unit = with(storage) {
        addCereal(Cereal.RICE, 0.1f)
        addCereal(Cereal.PEAS, 0.1f)
        assertThrows(IllegalStateException::class.java) {
            addCereal(Cereal.BUCKWHEAT, 0.1f)
        }
    }

    @Test
    fun getCerealTest() = with(storage) {
        addCereal(Cereal.BULGUR, 2f)
        val takeAmount = getCereal(Cereal.BULGUR, 1f)
        assertEquals(1f, takeAmount)
    }

    @Test
    fun `negative test get cereal`(): Unit = with(storage) {
        assertThrows(IllegalArgumentException::class.java) {
            getCereal(Cereal.BUCKWHEAT, -1f)
        }
    }

    @Test
    fun `should 0  if don't have cereal`() = with(storage) {
        val takeamount = getCereal(Cereal.MILLET, 13f)
        assertEquals(0f, takeamount)
    }

    @Test
    fun removeFullContainerTest() = with(storage) {
        addCereal(Cereal.MILLET, 3f)
        assertFalse(removeContainer(Cereal.MILLET))
    }

    @Test
    fun removeNullContainerTest() = with(storage) {
        assertFalse(removeContainer(Cereal.MILLET))
    }

    @Test
    fun removeEmptyContainerTest() = with(storage) {
        addCereal(Cereal.MILLET, 0f)
        assertTrue(removeContainer(Cereal.MILLET))
    }

    @Test
    fun getSpace() = with(storage){
        addCereal(Cereal.MILLET, 5f)
        assertEquals(5f, getSpace(Cereal.MILLET))
    }

    @Test
    fun `string to test`(): Unit = with(storage){
        addCereal(Cereal.RICE, 2.2f)
        assertEquals("Крупа: RICE у нас столько крупы 2.2", toString())
    }


}
