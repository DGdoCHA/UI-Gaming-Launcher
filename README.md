# UI Gaming Launcher 🎮

Um launcher de jogos moderno com interface baseada em Leanback para transformar seu smartphone Android em um console portátil com suporte a modo TV fullscreen.

## ✨ Características

- 📺 **Interface Leanback** - UI otimizada para TV e gamepads
- 🎮 **Detecção Automática de Jogos** - Identifica todos os jogos instalados
- 🖼️ **Capas/Thumbnails** - Exibição de imagens dos jogos
- 📂 **Categorização** - Organize seus jogos em categorias
- 💾 **Histórico** - Rastreie os jogos mais jogados
- 🖥️ **Fullscreen TV Mode** - Modo imersivo para gaming
- 🎮 **Suporte a Gamepad** - Controle total com joystick/gamepad
- 📱 **Responsive Design** - Funciona em phones e tablets

## 🚀 Tecnologias

- **Kotlin** - Linguagem principal
- **Android Leanback Library** - Framework para TV
- **Jetpack Compose** (opcional) - UI moderna
- **Room Database** - Persistência de dados
- **Coroutines** - Operações assíncronas
- **Coil/Glide** - Carregamento de imagens

## 📋 Requisitos

- Android Studio Dolphin ou superior
- Android SDK 28+
- Kotlin 1.8+

## 🛠️ Setup

1. Clone o repositório:
```bash
git clone https://github.com/DGdoCHA/UI-Gaming-Launcher.git
cd UI-Gaming-Launcher
```

2. Abra no Android Studio:
```bash
android-studio .
```

3. Sincronize o Gradle

4. Compile e execute no seu dispositivo/emulador

## 📁 Estrutura do Projeto

```
UI-Gaming-Launcher/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/dgdocha/gamelauncher/
│   │   │   │   ├── ui/
│   │   │   │   │   ├── fragments/
│   │   │   │   │   ├── adapters/
│   │   │   │   │   └── presenters/
│   │   │   │   ├── data/
│   │   │   │   │   ├── models/
│   │   │   │   │   ├── database/
│   │   │   │   │   └── repository/
│   │   │   │   ├── domain/
│   │   │   │   │   └── usecases/
│   │   │   │   ├── util/
│   │   │   │   └── MainActivity.kt
│   │   │   ├── res/
│   │   │   └── AndroidManifest.xml
│   │   └── test/
│   └── build.gradle.kts
├── settings.gradle.kts
└── build.gradle.kts
```

## 🎮 Controles

- **Gamepad D-Pad** - Navegação
- **Gamepad A** - Selecionar/Abrir jogo
- **Gamepad B** - Voltar
- **Gamepad Y** - Histórico
- **Gamepad X** - Categorias

## 📖 Documentação

- [Android Leanback](https://developer.android.com/reference/androidx/leanback/app/package-summary)
- [Android TV](https://developer.android.com/training/tv)
- [Game Controller Support](https://developer.android.com/training/game-controllers)

## 👨‍💻 Desenvolvimento

Contribuições são bem-vindas! Sinta-se livre para:
- Reportar bugs
- Sugerir novas funcionalidades
- Enviar pull requests

## 📄 Licença

MIT License - veja LICENSE para detalhes

## 📞 Contato

Para dúvidas e sugestões, abra uma issue no repositório.

---

**Desenvolvido com ❤️ para gamers móveis**
