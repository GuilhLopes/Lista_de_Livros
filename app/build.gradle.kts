plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.lista_de_livros"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.lista_de_livros"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.9.1")

    implementation(libs.androidx.core.ktx)                // Dependência do Core KTX (extensões Kotlin)
    implementation(libs.androidx.appcompat)               // Dependência para AppCompat
    implementation(libs.material)                         // Dependência para o Material Design

    testImplementation(libs.junit)                       // Dependência para testes unitários
    androidTestImplementation(libs.androidx.junit)       // Dependência para testes no Android
    androidTestImplementation(libs.androidx.espresso.core) // Dependência para testes de UI
}
