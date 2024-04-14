package com.example.config

import com.example.controller.AdminRepository
import com.example.controller.AdminRepositoryImpl
import com.example.data.database.DatabaseClient
import com.example.data.implementation.ApiServiceRepositoryImpl
import com.example.data.implementation.AppRepositoryIml
import com.example.data.implementation.UserRepositoryImpl
import com.example.data.repository.ApiServiceRepository
import com.example.data.repository.AppRepository
import com.example.data.repository.UserRepository
import org.koin.core.KoinApplication
import org.koin.dsl.module

object InjectionConfig {
    operator fun invoke(koinApplication: KoinApplication) {
        koinApplication.apply {
            modules(
                module {
                    single { DatabaseClient.getDatabase() }
                },
                module {
                    single<UserRepository> { UserRepositoryImpl(get()) }
                    single<ApiServiceRepository> { ApiServiceRepositoryImpl(get()) }
                    single<AppRepository> { AppRepositoryIml(get(),get()) }
                    single<AdminRepository> { AdminRepositoryImpl(get()) }
                })
        }
    }
}