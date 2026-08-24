package hhhcosmetics.luggage.hhhtravellink.di

import androidx.room.Room
import hhhcosmetics.luggage.hhhtravellink.data.database.QJIOODatabase
import org.koin.dsl.module

private const val DB_NAME = "qjioo_db"

val databaseModule = module {
    single {
        Room.databaseBuilder(
            context = get(),
            klass = QJIOODatabase::class.java,
            name = DB_NAME
        ).build()
    }

    single { get<QJIOODatabase>().cartItemDao() }

    single { get<QJIOODatabase>().orderDao() }
}