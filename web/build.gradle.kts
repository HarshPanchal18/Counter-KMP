plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose") version "1.4.3"
}

kotlin {

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
        val jsMain by getting {
            dependencies {
                implementation(compose.html.core)
                implementation(compose.runtime)
                implementation(project(":shared"))
            }
        }
    }
}
