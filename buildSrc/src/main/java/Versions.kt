object Versions {

    // BASE DEPENDENCIES
    const val CORE_KTX_VERSION = "1.7.0"
    const val APP_COMPAT_VERSION = "1.4.1"
    const val ANDROID_MATERIAL_VERSION = "1.5.0"
    const val JUNIT_VERSION = "4.13.2"
    const val EXT_JUNIT_VERSION = "1.1.3"
    const val ESPRESSO_VERSION = "3.4.0"

    // ADDITIONAL DEPENDENCIES
    const val GSON_VERSION = "2.8.9"
    const val COROUTINES_CORE = "1.3.9"
    const val COROUTINES_VIEWMODEL_VERSION = "2.4.0"
    const val COROUTINES_ANDROID_VERSION = "1.3.9"
    const val COROUTINES_LIVE_DATA_VERSION = "2.4.0"
    const val ROOM_KTX_VERSION = "2.4.2"
    const val RETROFIT_VERSION = "2.9.0"
    const val OKHTTP_VERSION = "4.9.0"
    const val OKHTTP_LOGGING_VERSION = "4.9.3"
    const val COMPOSE_VERSION = "1.1.1"
    const val NAVIGATION_COMPOSE_VERSION = "2.4.1"
    const val COMPOSE_VIEWMODEL_VERSION = "2.4.1"
    const val HILT_NAVIGATION_COMPOSE_VERSION = "1.0.0"
    const val ACTIVITY_COMPOSE_VERSION = "1.4.0"
    const val DAGGER2_VERSION = "2.40.5"
    const val HILT_VERSION = "2.40.5"
    const val SERIALIZATION_VERSION = "1.3.2"
}

object Project_BuildScript_Dependencies {
    const val TOOLS_BUILD_GRADLE = "com.android.tools.build:gradle:7.0.0"
    const val KOTLIN_GRADLE_PLUGIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.31"
    const val HILT_ANDROID_GRADLE_PLUGIN = "com.google.dagger:hilt-android-gradle-plugin:2.38.1"
    const val GRADLE_SECRETS_PLUGIN =
        "com.google.android.libraries.mapsplatform.secrets-gradle-plugin:secrets-gradle-plugin:2.0.0"
}

object AdditionalDependencies {
    const val ROOM_KTX = "androidx.room:room-ktx:${Versions.ROOM_KTX_VERSION}"
    const val ROOM_ANNOTATION_PROCESSOR = "androidx.room:room-compiler:${Versions.ROOM_KTX_VERSION}"
    const val SERIALIZATION =
        "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.SERIALIZATION_VERSION}"
}

object DaggerDependencies {
    const val DAGGER_ANDROID = "com.google.dagger:dagger:${Versions.DAGGER2_VERSION}"
    const val DAGGER_COMPILER = "com.google.dagger:dagger-compiler:${Versions.DAGGER2_VERSION}"
    const val HILT = "com.google.dagger:hilt-android:${Versions.HILT_VERSION}"
    const val HILT_COMPILER = "com.google.dagger:hilt-android-compiler:${Versions.HILT_VERSION}"
    const val HILT_COMPOSE_NAVIGATION =
        "androidx.hilt:hilt-navigation-compose:${Versions.HILT_NAVIGATION_COMPOSE_VERSION}"
}

object RetroFitDependencies {
    const val GSON = "com.google.code.gson:gson:${Versions.GSON_VERSION}"
    const val RETROFIT2 = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT_VERSION}"
    const val GSON_CONVERTER = "com.squareup.retrofit2:converter-gson:${Versions.RETROFIT_VERSION}"
    const val OKHTTP = "com.squareup.okhttp3:okhttp:${Versions.OKHTTP_VERSION}"
    const val OKHTTP_LOGGING =
        "com.squareup.okhttp3:logging-interceptor:${Versions.OKHTTP_LOGGING_VERSION}"
}

object CoroutinesDependencies {
    const val COROUTINES_CORE =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.COROUTINES_CORE}"

    const val COROUTINES_ANDROID =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.COROUTINES_ANDROID_VERSION}"

    const val COROUTINES_LIVE_DATA =
        "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.COROUTINES_LIVE_DATA_VERSION}"

    const val COROUTINES_VIEWMODEL_KTX =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.COROUTINES_VIEWMODEL_VERSION}"

    const val COROUTINES_LIVE_DATA_RUNTIME =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.COROUTINES_LIVE_DATA_VERSION}"
}

object ComposeDependencies {
    const val COMPOSE_UI = "androidx.compose.ui:ui:${Versions.COMPOSE_VERSION}"
    const val COMPOSE_MATERIAL = "androidx.compose.material:material:${Versions.COMPOSE_VERSION}"
    const val COMPOSE_UI_TOOLING = "androidx.compose.ui:ui-tooling:${Versions.COMPOSE_VERSION}"
    const val COMPOSE_ACTIVITY =
        "androidx.activity:activity-ktx:${Versions.ACTIVITY_COMPOSE_VERSION}"
    const val COMPOSE_NAVIGATION =
        "androidx.navigation:navigation-compose:${Versions.NAVIGATION_COMPOSE_VERSION}"
    const val COMPOSE_VIEWMODEL =
        "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.COMPOSE_VIEWMODEL_VERSION}"
}

object BaseDependencies {
    const val CORE_KTX = "androidx.core:core-ktx:${Versions.CORE_KTX_VERSION}"
    const val APP_COMPAT = "androidx.appcompat:appcompat:${Versions.APP_COMPAT_VERSION}"
    const val ANDROID_MATERIAL =
        "com.google.android.material:material:${Versions.ANDROID_MATERIAL_VERSION}"
    const val JUNIT = "junit:junit:${Versions.JUNIT_VERSION}"
    const val EXT_JUNIT = "androidx.test.ext:junit:${Versions.EXT_JUNIT_VERSION}"
    const val ESPRESSO = "androidx.test.espresso:espresso-core:${Versions.ESPRESSO_VERSION}"
}