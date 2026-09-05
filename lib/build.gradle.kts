import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.android.library")
    id("com.vanniktech.maven.publish")
}

val abiCodes = mapOf("armeabi-v7a" to 1, "arm64-v8a" to 2, "x86" to 3, "x86_64" to 4)
val universalBase = 8000

version = "0.1.15"
group = "io.github.secozzi"

android {
    namespace = "is.xyz.mpv"
    compileSdk = 36
    defaultConfig { minSdk = 23 }

    buildFeatures {
        buildConfig = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    packagingOptions {
        jniLibs {
            excludes.addAll(
               listOf(
                   "**/libavcodec.so",
                   "**/libavdevice.so",
                   "**/libavfilter.so",
                   "**/libavformat.so",
                   "**/libavutil.so",
                   "**/libswresample.so",
                   "**/libswscale.so",
                   "**/libc++_shared.so",
               )
            )
        }
    }

    tasks.withType<KotlinCompile> {
        compilerOptions.jvmTarget.set(JvmTarget.JVM_11)
    }
}

dependencies {
    implementation("androidx.appcompat:appcompat:1.7.1")
}
