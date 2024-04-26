# Simple Custom Toast

An easy-to-use custom toast library for Android, designed to enrich your applications with beautifully styled toast messages.

## Credits

Design inspired by and credited to [Anastasia D](https://www.figma.com/@anastasia_d). 

## Table of Contents

- [Installation](#installation)
- [Usage](#usage)
- [Attributes](#attributes)
- [Contributing](#contributing)

## Installation

To integrate Simple Custom Toast into your Android project, add the following configuration to your `settings.gradle` file:

```gradle
maven {
    setUrl("https://jitpack.io")
}
```

## Usage
Follow these steps to use the Simple Custom Toast in your Android application.

1. Create an instance of `NalToast` You can create a new instance of NalToast by passing the context and the style type you want.
```kotlin
val customToast = NalToast(binding.root, Type.SUCCESS)
```

2. Display the toastTo show the toast, use the `show` method of the instance you created. Specify the message and the duration.
```kotlin
customToast.show("Hello World", Duration.SHORT)
```

## Attributes
Duration  |
------------- |
`Duration.SHORT`  |
`Duration.LONG`  |

Styles  | Image
------------- | -------------
`Type.SUCCESS`  | ![image](https://github.com/NallDev/Custom-Toast/assets/90769828/4f4f6f39-edfc-415e-aba7-333b4e2a9459)
`Type.FAIL`  | ![image](https://github.com/NallDev/Custom-Toast/assets/90769828/c4ce252f-ba15-457f-bdc7-f0c326ff04b4)
`Type.INFO`  | ![image](https://github.com/NallDev/Custom-Toast/assets/90769828/6ee81226-f026-46fd-8d7c-3f5a99f829ca)

## Contributing
Contributions are what make the open source community such an amazing place to learn, inspire, and create. Any contributions you make are greatly appreciated.
