Extremely minimal Compose Multiplatform sample that demonstrates use of on-device AI 
on iOS and Android.  Specifically it uses [Foundation Model Framework](https://developer.apple.com/documentation/FoundationModels) on iOS and [MLKit 
Prompt API](https://developers.google.com/ml-kit/genai/prompt/android/get-started) on Android.  

In following code`PromptApi` is a Kotlin interface that's implemented by a Swfit class on iOS and a Kotlin one on Android.  The Swift implementation is passed down
to shared KMP code and invoked from the Compose Mutlplatform code if running on iOS.  

Right now the respective iOS and Android implementations are assigned to a top level `promptApi` variable.  This is a somewhat hacky solution for now for the sake of simplicity....there's a similar requirement in many of the other samples I have and in those cases the implementation is set in such a way that it becomes part of the object graph of particular DI framework being used.

### iOS (Swift)

```swift
import FoundationModels

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
import com.google.mlkit.genai.prompt.Generation

class PromptApiAndroid: PromptApi {
    private val generativeModel = Generation.getClient()

    override suspend fun generateContent(prompt: String): String? {
        val response = generativeModel.generateContent(prompt)
        return response.candidates.firstOrNull()?.text
    }
}
```

