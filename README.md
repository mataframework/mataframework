# MATA Framework—Mobile Application Testing Automation Framework

<!-- TOC -->
* [MATA Framework—Mobile Application Testing Automation Framework](#mata-frameworkmobile-application-testing-automation-framework)
  * [Introduction](#introduction)
  * [Getting Started](#getting-started)
    * [Prerequisites](#prerequisites)
    * [Installation](#installation)
    * [Running Tests](#running-tests)
    * [Environments](#environments)
  * [Contributing](#contributing)
  * [License](#license)
  * [Contact](#contact)
<!-- TOC -->

## Introduction

MATA Framework is a Mobile Application Testing Automation framework designed to simplify the process of automating tests
for mobile applications across Android and iOS platforms.
This framework supports end-to-end (E2E) UI tests, using
the Page Object Model (POM) to enhance code maintainability and readability.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) at least 17
- Android SDK / Xcode (for iOS)

### Installation

1. Clone the repository:
   ```sh
   git clone https://github.com/mataframework/mataframework.git
   ```
2. Navigate to the project directory:
   ```sh
   cd mataframework
   ```
3. Build the project using Gradle:
   ```sh
   ./gradlew build
   ```

### Running Tests

1. Configure the desired platform in `Configuration`.
2. Use the following command to execute tests:
   ```sh
   ./gradlew test
   ```

### Environments

| Key                      | Default value            | Descriptions                                    |
|--------------------------|--------------------------|-------------------------------------------------|
| `platform`               | `android`                | Active platform, one of: <br/>-android<br/>-ios |
| `appiumUrl`              | `http://localhost:4723/` | Appium server url                               |
| `androidVersion`         | `8.1`                    | Active Android version                          |
| `iosVersion`             | `16.4`                   | Active iOS version                              |
| `androidAppLocation`     | `android.apk`            | Android APK file location                       |
| `iosAppLocation`         | `ios.app`                | iOS application file location                   |
| `iosDeviceName`          | `iPhone 14`              | iOS device name                                 |
| `elementPollingTimeout`  | `7000`                   | Element polling default timeout                 |
| `elementPollingInterval` | `500`                    | Element polling default interval                |

## Contributing

We welcome contributions! Please fork the repository, create a feature branch, and submit a pull request.

## License

This project is licensed under the MIT License—see the [LICENSE](LICENSE) file for details.

## Contact

For any inquiries or feedback, please contact the maintainers:

- Maksim Drobyshev (sibmaks)
- Anton Lachugin (lachugin-al)