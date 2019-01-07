object CoreLibraries {
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlinVersion}"
}

object SupportLibraries {
    val appCompat = "com.android.support:appcompat-v7:${Versions.appCompatVersion}"
}

object TestLibraries {
    val jUnit = "junit:junit:${Versions.jUnitVersion}"
    val runnner = "com.android.support.test:runner:${Versions.testRunnerVersion}"
    val espressoCore = "com.android.support.test.espresso:espresso-core:${Versions.espressoCoreVersion}"
}
