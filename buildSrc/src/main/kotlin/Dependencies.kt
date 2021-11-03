object BuildPlugins {
    object Version {
        const val gradleVersion = "7.0.3"
        const val kotlinVersion = "1.5.31"
    }

    const val gradle = "com.android.tools.build:gradle:${Version.gradleVersion}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.kotlinVersion}"
    const val navigation =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Libraries.Version.navigationVersion}"
}

object AndroidSdk {
    const val min = 21
    const val compile = 31
    const val target = 31
    const val versionCode = 1
    const val versionName = "1.0"
}

object Libraries {
    object Version {
        const val coreKtxVersion = "1.7.0"
        const val appCompatVersion = "1.3.1"
        const val materialVersion = "1.4.0"
        const val constraintLayoutVersion = "2.1.1"
        const val dataBindingRuntimeVersion = "7.0.3"
        const val legacySupportV4Version = "1.0.0"
        const val junitVersion = "4.13.2"
        const val junitExtVersion = "1.1.3"
        const val espressoVersion = "3.4.0"
        const val fragmentKtxVersion = "1.3.6"
        const val rxJavaVersion = "2.1.1"

        // Timber
        const val timberVersion = "4.7.1"

        // Coroutine Lifecycle Scopes
        const val lifecycleVersion = "2.3.1"
        const val lifecycleExtVersion = "2.2.0"

        // Retrofit
        const val retrofitVersion = "2.9.0"
        const val okhttpVersion = "4.9.1"

        //Navigation
        const val navigationVersion = "2.3.5"

        //Room
        const val roomVersion = "2.3.0"
        const val sqliteJdbcVersion = "3.34.0"

    }

    const val coreKtx = "androidx.core:core-ktx:${Version.coreKtxVersion}"
    const val appCompat = "androidx.appcompat:appcompat:${Version.appCompatVersion}"
    const val material = "com.google.android.material:material:${Version.materialVersion}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Version.constraintLayoutVersion}"
    const val dataBindingRuntime =
        "androidx.databinding:databinding-runtime:${Version.dataBindingRuntimeVersion}"
    const val legacySupportV4 =
        "androidx.legacy:legacy-support-v4:${Version.legacySupportV4Version}"
    const val junit = "junit:junit:${Version.junitVersion}"
    const val junitExt = "androidx.test.ext:junit:${Version.junitExtVersion}"
    const val espresso = "androidx.test.espresso:espresso-core:${Version.espressoVersion}"
    const val fragmentKtx = "androidx.fragment:fragment-ktx:${Version.fragmentKtxVersion}"

    // Timber
    const val timber = "com.jakewharton.timber:timber:${Version.timberVersion}"

    //Lifecycle Scopes
    const val lifecycleViewModelKtx =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.lifecycleVersion}"
    const val lifecycleRuntimeKtx =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Version.lifecycleVersion}"
    const val lifecycleExt =
        "androidx.lifecycle:lifecycle-extensions:${Version.lifecycleExtVersion}"

    // Retrofit
    const val retrofit = "com.squareup.retrofit2:retrofit:${Version.retrofitVersion}"
    const val converterGson = "com.squareup.retrofit2:converter-gson:${Version.retrofitVersion}"
    const val okhttp = "com.squareup.okhttp3:okhttp:${Version.okhttpVersion}"
    const val loggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Version.okhttpVersion}"

    //Navigation
    const val navigationFragmentKtx =
        "androidx.navigation:navigation-fragment-ktx:${Version.navigationVersion}"
    const val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:${Version.navigationVersion}"

    //Room
    const val roomRuntime = "androidx.room:room-runtime:${Version.roomVersion}"
    const val roomCompiler = "androidx.room:room-compiler:${Version.roomVersion}"
    const val roomKtx = "androidx.room:room-ktx:${Version.roomVersion}"
    const val sqliteJdbc = "org.xerial:sqlite-jdbc:${Version.sqliteJdbcVersion}"

    //RxJava
    const val rxJava = "io.reactivex.rxjava2:rxjava:${Version.rxJavaVersion}"
    const val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Version.rxJavaVersion}"
}