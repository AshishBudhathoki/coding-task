apply {
    from("$rootDir/base-module.gradle")
}

dependencies {
    "implementation"(project(Modules.core))
    "implementation"(project(Modules.weatherDomain))

    "implementation"(Retrofit.okHttp)
    "implementation"(Retrofit.retrofit)
    "implementation"(Retrofit.okHttpLoggingInterceptor)
    "implementation"(Retrofit.moshiConverter)
    "implementation"(Moshi.moshiKotlinVersion)

    "kapt"(Room.roomCompiler)
    "api"(Room.roomKtx)
    "api"(Room.roomRuntime)
}