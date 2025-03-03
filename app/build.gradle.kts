plugins {
	alias(libs.plugins.androidApplication)
	alias(libs.plugins.jetbrainsKotlinAndroid)
	alias(libs.plugins.realm)
}

android {
	namespace = "com.zsoltbertalan.books"
	compileSdk = 34

	defaultConfig {
		applicationId = "com.zsoltbertalan.books"
		minSdk = 24
		targetSdk = 34
		versionCode = 1
		versionName = "1.0"

		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
		vectorDrawables {
			useSupportLibrary = true
		}
	}

	buildTypes {
		release {
			isMinifyEnabled = false
			proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
		}
	}
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_17
		targetCompatibility = JavaVersion.VERSION_17
	}

	buildFeatures {
		compose = true
	}
	composeOptions {
		kotlinCompilerExtensionVersion = libs.versions.androidx.compose.compiler.get()
	}
	packaging {
		resources {
			excludes += "/META-INF/{AL2.0,LGPL2.1}"
		}
	}
}

dependencies {

	implementation(libs.androidx.activity.compose)
	implementation(libs.androidx.appcompat)
	implementation(platform(libs.androidx.compose.bom))
	implementation(libs.androidx.compose.ui.ui)
	implementation(libs.androidx.compose.ui.graphics)
	implementation(libs.androidx.compose.ui.text)
	implementation(libs.androidx.compose.ui.unit)
	implementation(libs.androidx.compose.ui.tooling)
	implementation(libs.androidx.compose.ui.toolingPreview)
	implementation(libs.androidx.compose.material3)
	implementation(libs.androidx.coreKtx)
	implementation(libs.androidx.constraintLayout)
	implementation(libs.androidx.navigation.uiKtx)
	implementation(libs.androidx.lifecycle.runtime.compose)
	implementation(libs.androidx.navigation.compose)
	implementation(libs.androidx.hilt.navigation.compose)
	implementation(libs.google.material)
	implementation(libs.androidx.coreKtx)
	implementation(libs.kotlinResult.result)
	implementation(libs.kotlinResult.coroutines)
	implementation(libs.kotlin.parcelize.runtime)
	implementation(libs.squareUp.okhttp3.loggingInterceptor)
	implementation(libs.timber)
	implementation(libs.realm.base)

	implementation(libs.google.gson)
	implementation(libs.squareUp.retrofit2.retrofit)
	implementation(libs.squareUp.retrofit2.converterGson)

	androidTestImplementation(libs.squareUp.okhttp3.loggingInterceptor)

	implementation(libs.google.dagger.core)
//	add("ksp", libs.google.dagger.compiler)
//	kspTest(libs.google.dagger.compiler)
//	kspAndroidTest(libs.google.dagger.compiler)

	implementation(libs.google.dagger.hilt.android)
//	add("ksp", libs.androidx.hilt.compiler)
//	kspTest(libs.androidx.hilt.compiler)
//	add("ksp", libs.google.dagger.hilt.androidCompiler)
//	kspTest(libs.google.dagger.hilt.androidCompiler)
//	kspAndroidTest(libs.google.dagger.hilt.androidCompiler)

	implementation(libs.coil)

	implementation(libs.inject)

	testImplementation(libs.androidx.test.coreKtx)
	testImplementation(libs.androidx.test.ext.jUnit)
	testImplementation(libs.test.mockk.core)
	testImplementation(libs.kotlinx.coroutines.test)
	testImplementation(libs.test.kotest.assertions.core)

	debugRuntimeOnly(platform(libs.androidx.compose.bom))
	//Needed for createComposeRule, NOT ONLY for createAndroidComposeRule, as in the docs
	debugRuntimeOnly(libs.androidx.compose.ui.testManifest)

	androidTestImplementation(libs.test.mockk.android)
	androidTestImplementation(libs.androidx.test.espresso.core)
	androidTestImplementation(libs.androidx.test.ext.jUnit)
	androidTestImplementation(libs.androidx.test.rules)
	androidTestImplementation(libs.androidx.test.runner)
	androidTestImplementation(libs.androidx.compose.ui.test.android)
	androidTestImplementation(libs.androidx.compose.ui.test.junit4)
	androidTestImplementation(libs.androidx.compose.ui.test.junit4.android)
	androidTestImplementation(libs.google.gson)
	androidTestImplementation(libs.google.dagger.hilt.androidTesting)
	androidTestImplementation(libs.squareUp.retrofit2.retrofit)
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
	kotlinOptions.freeCompilerArgs += "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api"
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
	kotlinOptions.freeCompilerArgs += "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi"
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
	kotlinOptions.freeCompilerArgs += "-opt-in=androidx.compose.ui.test.ExperimentalTestApi"
}
