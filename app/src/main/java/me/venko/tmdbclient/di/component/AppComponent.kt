package me.venko.tmdbclient.di.component

import dagger.Component
import me.venko.tmdbclient.TmdbClientApp
import me.venko.tmdbclient.di.module.ApiModule
import me.venko.tmdbclient.di.module.AppModule
import me.venko.tmdbclient.di.module.CoreModule
import me.venko.tmdbclient.di.module.NavigationModule
import me.venko.tmdbclient.navigation.navigator.NavigatorFactory
import me.venko.tmdbclient.presentation.discovery.DiscoveryViewModel
import me.venko.tmdbclient.presentation.root.AppRootViewModel
import me.venko.tmdbclient.presentation.root.RootActivity
import javax.inject.Singleton

/**
 * @author Victor Kosenko
 *
 */
@Singleton
@Component(modules = [AppModule::class, ApiModule::class, NavigationModule::class, CoreModule::class])
interface AppComponent {

    fun appNavigatorsFactories(): Map<Int, NavigatorFactory>

    fun inject(app: TmdbClientApp)

    fun inject(viewModel: AppRootViewModel)
    fun inject(activity: RootActivity)
    fun inject(viewModel: DiscoveryViewModel)
}
