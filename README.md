# Chucker Ktor

App Android de exemplo que lista posts da [JSONPlaceholder API](https://jsonplaceholder.typicode.com) com **Ktor Client** e inspeciona o tráfego HTTP via **Chucker**, seguindo a arquitetura do [Resonance](../../Resonance).

## Stack

| Tecnologia | Versão |
|------------|--------|
| Android Gradle Plugin | 9.4.0 |
| Kotlin | 2.2.10 |
| Compose BOM | 2026.02.01 |
| Koin | 4.2.2 |
| Navigation Compose | 2.9.3 |
| Ktor Client | 3.1.3 |
| Chucker | 4.3.1 |
| DataStore | 1.1.7 |
| compileSdk / targetSdk | 37 |
| minSdk | 29 |
| JVM | 21 |

## Arquitetura

MVVM por feature com Koin para injeção de dependências:

```
MainActivity → RoutesApp → PostRoute → PostViewModel → PostRepository → HttpService (Ktor + Chucker)
                ↓
           SettingRoute → SettingViewModel → SettingRepository → DataStore
```

### Estrutura de pacotes

```
src/
├── common/
│   ├── constants/       # ApiConstant, ValueConstant
│   ├── patterns/        # StatePattern, ResultPattern
│   └── services/        # HttpService (Ktor + ChuckerInterceptor)
├── di/                  # Módulo Koin
├── design/theme/        # Material 3
├── routes/              # NavHost e rotas
└── features/
    ├── posts/           # Lista de posts (API)
    └── settings/        # Tema escuro persistido (DataStore)
```

## Integração Chucker + Ktor

O Chucker opera no nível OkHttp. O Ktor usa o engine `ktor-client-okhttp` com o interceptor injetado:

```kotlin
OkHttp.create { addInterceptor(chuckerInterceptor) }
HttpClient(okhttpEngine) { install(Logging); install(ContentNegotiation) { json(...) } }
```

- **Debug:** `library:4.3.1` — notificação e UI de inspeção HTTP
- **Release:** `library-no-op:4.3.1` — zero overhead em produção

## Funcionalidades

- Lista de posts com estados Initial, Loading, Success e Error
- Pull-to-refresh e botão de atualização
- Inspeção de requests/responses via notificação do Chucker (build debug)
- Tela de settings com toggle de dark theme persistido via DataStore
- Dialog About com nome do app, versão e copyright

## Como usar o Chucker

1. Instale o app em modo **debug**
2. Abra a lista de posts (carrega automaticamente da API)
3. Toque em refresh para gerar nova requisição
4. Abra a **notificação do Chucker** ou o atalho na tela inicial para inspecionar o tráfego HTTP

## License

MIT License

Copyright (c) 2026 William Franco

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
