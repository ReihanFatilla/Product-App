plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.altech.reift.productapp"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.altech.reift.productapp"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures{
        viewBinding = true
    }
}

dependencies {
    implementation(project(":core"))

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.firebase:firebase-auth-ktx:22.1.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // RxJava
    implementation ("io.reactivex.rxjava3:rxjava:3.0.0")
    implementation("io.reactivex.rxjava3:rxandroid:3.0.0")
    implementation("com.squareup.retrofit2:adapter-rxjava3:2.9.0")

    // Koin
    implementation("org.koin:koin-core:2.1.6")
    implementation("org.koin:koin-android:2.1.6")
    implementation("org.koin:koin-android-viewmodel:2.1.6")

    // Toasting
    implementation("com.github.ReihanFatilla:Toasting:0.1.1")

    // Glide
    implementation("com.github.bumptech.glide:glide:4.14.2")

    // LiveData ReactiveStreams
    implementation("androidx.lifecycle:lifecycle-reactivestreams-ktx:2.5.1")

    // Work Manager
    implementation("androidx.work:work-runtime-ktx:2.7.1")
}