buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose") version "1.4.3"
    id("com.android.library")
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    android()
    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }

    ios()
    iosSimulatorArm64 {
        // TODO: remove after 1.5 release
        binaries.forEach {
            it.freeCompilerArgs += listOf(
                "-linker-option", "-framework", "-linker-option", "Metal",
                "-linker-option", "-framework", "-linker-option", "CoreText",
                "-linker-option", "-framework", "-linker-option", "CoreGraphics",
            )
        }
    }

    jvm("desktop") {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
    }

    js(IR) {
        browser {
            testTask {
                testLogging.showStandardStreams = true
                useKarma {
                    useChromeHeadless()
                    useFirefox()
                }
            }
        }
        binaries.executable()
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                //put your multiplatform dependencies here
                api(compose.runtime)
                api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
                api("org.kodein.di:kodein-di-framework-compose:7.20.1")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                api("androidx.appcompat:appcompat:1.6.1")
                api("androidx.core:core-ktx:1.10.1")
            }
        }
        val iosMain by getting
        val iosSimulatorArm64Main by getting {
            dependsOn(iosMain)
        }
        val desktopMain by getting {
            dependencies {
                api(compose.preview)
            }
        }
        val desktopTest by getting
    }
}

android {
    namespace = "com.example.counter_kmp"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    composeOptions.kotlinCompilerExtensionVersion="1.4.3"
    compileOptions.sourceCompatibility = JavaVersion.VERSION_1_8
    compileOptions.targetCompatibility = JavaVersion.VERSION_1_8
}
