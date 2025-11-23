Extremely inimal Compose Multiplatform sample that demonstrates use of on-device AI 
on iOS and Android.  Specifically it uses [Foundation Model Framework](https://developer.apple.com/documentation/FoundationModels) on iOS and [MLKit 
Prompt API](https://developers.google.com/ml-kit/genai/prompt/android/get-started) on Android.  

In following code`PromptApi` is a Kotlin interface that's implemented by a Swfit class on iOS and a Kotlin one on Android.  The Swift implementation is passed down
to shared KMP code and invoked from the shared Compose Mutlplatform code if running on iOS.

### iOS (Swift)

```swift
class PromptApiIos: PromptApi {
    
    func generateContent(prompt: String) async throws -> String? {
        let session = LanguageModelSession()
        let response = try await session.respond(to: prompt)
        return response.content
    }
}
```


### Android (Kotlin)

```kotlin
class PromptApiAndroid: PromptApi {
    private val generativeModel = Generation.getClient()

    override suspend fun generateContent(prompt: String): String? {
        val response = generativeModel.generateContent(prompt)
        return response.candidates.firstOrNull()?.text
    }
}
```

