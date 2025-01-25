plugins {
    id("com.android.library") version "8.6.0" // השתמש בגרסה הישנה יותר שתומכת ב-JitPack
    id("maven-publish")
}

android {
    namespace = "com.example.triviasdk"
    compileSdk = 34

    defaultConfig {
        minSdk = 26
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
}

dependencies {
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.mockito:mockito-core:4.11.0")
    testImplementation("com.squareup.retrofit2:retrofit-mock:2.9.0")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                groupId = "com.github.AmitIshay"  // שנה לפי הצורך
                artifactId = "TriviaSDK"  // שנה לפי הצורך
                version = "1.0.0"  // שנה לפי הצורך
                artifact(tasks.getByName("bundleReleaseAar"))

                // הוסף תלותיות לקונפיגורציה של פרסום Maven (api או implementation)
            }
        }
    }
}
