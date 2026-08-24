package hhhcosmetics.luggage.hhhtravellink.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import hhhcosmetics.luggage.hhhtravellink.data.dao.CartItemDao
import hhhcosmetics.luggage.hhhtravellink.data.dao.OrderDao
import hhhcosmetics.luggage.hhhtravellink.data.database.converter.Converters
import hhhcosmetics.luggage.hhhtravellink.data.entity.CartItemEntity
import hhhcosmetics.luggage.hhhtravellink.data.entity.OrderEntity

@Database(
    entities = [CartItemEntity::class, OrderEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class QJIOODatabase : RoomDatabase() {

    abstract fun cartItemDao(): CartItemDao

    abstract fun orderDao(): OrderDao
}