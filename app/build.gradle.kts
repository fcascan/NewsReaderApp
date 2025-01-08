import org.jetbrains.kotlin.konan.properties.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.jetbrains.kotlin.serialization)
    alias(libs.plugins.kotlin.android.ksp)
    alias(libs.plugins.hilt.android)
}

android {
    namespace = "com.fcascan.newsreaderapp"
    compileSdk = 35

    //To enable custom BuildConfig fields:
    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        applicationId = "com.fcascan.newsreaderapp"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        // Load the API key from the credentials.properties file:
        val credentialsProperties = Properties()
        file("credentials.properties").inputStream().use {
            credentialsProperties.load(it)
        }
        val googleMapsApiKey: String = credentialsProperties["GOOGLE_MAPS_API_KEY"] as String
        manifestPlaceholders["GOOGLE_MAPS_API_KEY"] = googleMapsApiKey

        //Retrofit base URL:
        buildConfigField("String", "BASE_URL", "\"https://jsonplaceholder.org/\"")
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    //Stock dependencies:
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //Navigation:
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.kotlinx.serialization.json)

    //Dagger Hilt:
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    //Coil (Image loading from URL):
    implementation(libs.coil)

    //Google Maps Compose:
    implementation(libs.maps.compose)
    implementation(libs.play.services.maps)

    //Gson:
    implementation(libs.gson)

    //Retrofit:
    implementation(libs.okhttp3)
    implementation(libs.logging.interceptor)
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson.converter)

    //Room:
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)

    //SwipeRefresh:
    implementation(libs.accompanist.swiperefresh)
}